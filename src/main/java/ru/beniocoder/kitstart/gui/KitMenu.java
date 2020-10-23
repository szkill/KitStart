package ru.beniocoder.kitstart.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.abstractcoder.benioapi.config.HoconConfig;
import ru.abstractcoder.benioapi.config.msg.MsgConfig;
import ru.abstractcoder.benioapi.gui.template.MenuApi;
import ru.abstractcoder.benioapi.gui.template.MenuTemplate;
import ru.abstractcoder.benioapi.gui.template.item.DynamicMenuIcon;
import ru.abstractcoder.benioapi.gui.template.item.FixedMenuIcon;
import ru.abstractcoder.benioapi.gui.template.item.MenuItem;
import ru.abstractcoder.benioapi.gui.template.item.MenuItemFactory;
import ru.abstractcoder.benioapi.gui.template.loader.HoconPaginatedMenuLoader;
import ru.abstractcoder.benioapi.gui.template.loader.PaginatedMenuLoader;
import ru.abstractcoder.benioapi.gui.template.session.PaginatedMenuSession;
import ru.beniocoder.kitstart.join.config.GeneralConfig;
import ru.beniocoder.kitstart.join.config.Messages;
import ru.beniocoder.kitstart.join.JoinRepository;

import java.util.stream.Collectors;

public class KitMenu {


    private final Plugin plugin;
    private MenuTemplate<KitPlayer, PaginatedMenuSession<KitPlayer>> template;

    public KitMenu(MenuApi menuApi, ObjectMapper objectMapper, HoconConfig guiConfig,
                   MsgConfig<Messages> msgConfig, GeneralConfig generalConfig,
                   JoinRepository joinRepository, Plugin plugin) {

        this.plugin = plugin;
        guiConfig.addOnReloadAction(() -> {
            PaginatedMenuLoader<KitPlayer, PaginatedMenuSession<KitPlayer>> menuLoader;
            menuLoader = new HoconPaginatedMenuLoader<>(guiConfig.getHandle().getConfig("kitSelecting"), objectMapper);

            template = menuLoader
                    .setItem('x', (slot, itemData) -> MenuItemFactory.create(builder -> builder
                            .cachedIcon(new FixedMenuIcon(slot, itemData.toItemStack()))
                            .onClick(click -> click.getIssuer().getPlayer()
                                    .kickPlayer(msgConfig.get(Messages.KICK_MESSAGE).asSingleLine())
                            )
                    ))
                    .setItem('i', (slot, itemData) -> MenuItemFactory.create(builder -> builder
                            .cachedIcon(new FixedMenuIcon(slot, itemData.toItemStack()))
                    ))
                    .load(menuApi.getTemplateBuilder().customPaginatedTemplate(KitPlayer.class))
                    .cachedDynamicContent(generalConfig.getKits().stream()
                            .<MenuItem<KitPlayer, PaginatedMenuSession<KitPlayer>, DynamicMenuIcon>>map(kit -> MenuItemFactory.create(builder -> builder
                                    .cachedIcon(new DynamicMenuIcon(kit.getIcon()))
                                    .onClick(click -> {
                                        Player player = click.getIssuer().getPlayer();

                                        kit.getPrizes().forEach(prize -> prize.giveTo(player));
                                        click.getIssuer().setJoined(true);
                                        player.closeInventory();

                                        msgConfig.get(Messages.KIT_SELECTED, kit.getName()).send(player);
                                        joinRepository.setJoinedBefore(player.getName());
                                    })
                            ))
                            .collect(Collectors.toList())
                    )
                    .onClose((session, guiUser) -> {
                        if (!guiUser.isJoined()) {
                            this.plugin.getServer().getScheduler().runTaskLater(this.plugin,
                                    () -> template.createSession(guiUser),
                                    10L);
                        }
                    })
                    .build();
        });
    }

    public void open(Player player) {
        template.createSession(new KitPlayer(player));
    }

}