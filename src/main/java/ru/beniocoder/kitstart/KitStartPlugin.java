package ru.beniocoder.kitstart;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import net.Indyuce.mmoitems.api.Type;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import ru.abstractcoder.benioapi.BenioPlugin;
import ru.abstractcoder.benioapi.config.HoconConfig;
import ru.abstractcoder.benioapi.config.msg.MsgConfig;
import ru.abstractcoder.benioapi.database.util.QueryFactory;
import ru.beniocoder.kitstart.command.KitStartCommand;
import ru.beniocoder.kitstart.join.config.GeneralConfig;
import ru.beniocoder.kitstart.join.config.Messages;
import ru.beniocoder.kitstart.gui.KitMenu;
import ru.beniocoder.kitstart.jackson.MMOTypeMixin;
import ru.beniocoder.kitstart.join.JoinRepository;
import ru.beniocoder.kitstart.join.MysqlJoinRepository;
import ru.beniocoder.kitstart.listener.PlayerListener;

@Plugin(name = "KitStart", version = "1.0")
@Author("BenioCoder")
@Dependency("BenioApi")
@Dependency("MMOItems")
@Dependency("LuckPerms")
@ApiVersion(ApiVersion.Target.v1_13)
public class KitStartPlugin extends BenioPlugin {

    private GeneralConfig generalConfig;

    @Override
    protected void onPluginEnable() throws Throwable {
        ObjectMapper objectMapper = benioApiInstance.objectMapperService().createAdaptedObjectMapper();
        objectMapper
                .addMixIn(Type.class, MMOTypeMixin.class)
                .setInjectableValues(new InjectableValues.Std()
                        .addValue(LuckPermsApi.class, LuckPerms.getApi())
                );

        generalConfig = new GeneralConfig(this, objectMapper);
        MsgConfig<Messages> msgConfig = benioApiInstance.configBuilder().msgConfig(Messages.class).build();
        HoconConfig guiConfig = benioApiInstance.configBuilder().baseConfig().setFileName("gui").buildHocon();


        QueryFactory queryFactory = benioApiInstance.createQueryFactory(() -> generalConfig.getMysql().getDataSource());
        JoinRepository joinRepository = new MysqlJoinRepository(queryFactory);

        KitMenu kitMenu = new KitMenu(benioApiInstance.getMenuApi(), objectMapper, guiConfig,
                msgConfig, generalConfig, joinRepository, this);

        registerListener(new PlayerListener(joinRepository, kitMenu, this));

        new KitStartCommand(generalConfig, guiConfig, msgConfig).register(this);



    }

    @Override
    protected void onPluginDisable() throws Throwable {
        generalConfig.getMysql().close();
    }

}
