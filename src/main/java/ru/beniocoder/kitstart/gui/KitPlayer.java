package ru.beniocoder.kitstart.gui;

import org.bukkit.entity.Player;
import ru.abstractcoder.benioapi.gui.template.issuer.GuiUser;

public class KitPlayer implements GuiUser {

    private final Player player;
    private boolean joined = false;

    public KitPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

}
