package ru.beniocoder.kitstart.kit.prize;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bukkit.entity.Player;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @Type(name = "perm", value = PermissionKitPrize.class),
        @Type(name = "item", value = ItemKitPrize.class)
})
public interface KitPrize {

    void giveTo(Player player);

}