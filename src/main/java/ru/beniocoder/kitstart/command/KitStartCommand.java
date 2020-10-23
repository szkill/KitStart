package ru.beniocoder.kitstart.command;

import org.bukkit.command.CommandSender;
import ru.abstractcoder.benioapi.command.availability.CommandAvailabilities;
import ru.abstractcoder.benioapi.command.impl.ParentCommand;
import ru.abstractcoder.benioapi.config.HoconConfig;
import ru.abstractcoder.benioapi.config.msg.MsgConfig;
import ru.beniocoder.kitstart.join.config.GeneralConfig;
import ru.beniocoder.kitstart.join.config.Messages;

public class KitStartCommand extends ParentCommand {

    public KitStartCommand(GeneralConfig generalConfig, HoconConfig guiConfig, MsgConfig<Messages> msgConfig) {
        super("kitstart");

        executingStrategy()
                .aliases("ks")
                .withSubcommand(new GeneralSubcommand("reload") {
                    {
                        executingStrategy()
                                .addAvailability(CommandAvailabilities.bukkitPerm("kitstart.reload"));
                    }
                    @Override
                    protected void execute(CommandSender sender, String[] args, String label) {
                        generalConfig.reload();
                        msgConfig.reload();
                        guiConfig.reload();

                        msgConfig.get(Messages.CONFIG_RELOAD).send(sender);
                    }
                });
    }

}