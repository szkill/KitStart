package ru.beniocoder.kitstart.kit.prize;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;

public class PermissionKitPrize implements KitPrize {

    private final LuckPermsApi lpApi;
    private final Node perm;

    @JsonCreator
    public PermissionKitPrize(@JacksonInject LuckPermsApi lpApi, @JsonProperty("perm") String perm) {
        this.lpApi = lpApi;
        this.perm = lpApi.getNodeFactory().newBuilder(perm).build();
    }

    @Override
    public void giveTo(Player player) {
        User user =  lpApi.getUserSafe(player.getUniqueId())
                .orElseThrow(() -> new IllegalStateException("Unknown player for LP: " + player.getName()));
        user.setPermission(perm);
        lpApi.getUserManager().saveUser(user);
    }

}