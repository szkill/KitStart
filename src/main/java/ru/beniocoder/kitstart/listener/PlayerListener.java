package ru.beniocoder.kitstart.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import ru.beniocoder.kitstart.gui.KitMenu;
import ru.beniocoder.kitstart.join.JoinRepository;

public class PlayerListener implements Listener {

    private final JoinRepository joinRepository;
    private final KitMenu kitMenu;
    private final Plugin plugin;

    public PlayerListener(JoinRepository joinRepository, KitMenu kitMenu, Plugin plugin) {
        this.joinRepository = joinRepository;
        this.kitMenu = kitMenu;
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        joinRepository.isJoinedBefore(player.getName()).thenAccept(joinedBefore -> {
            if (joinedBefore) {
                return;
            }
            plugin.getServer().getScheduler().runTaskLater(plugin,
                    () -> kitMenu.open(player),
                    1L);
        });
    }

}