package ru.beniocoder.kitstart.kit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.inventory.ItemStack;
import ru.abstractcoder.benioapi.item.ItemData;
import ru.abstractcoder.benioapi.util.ColorUtils;
import ru.beniocoder.kitstart.kit.prize.KitPrize;

import java.util.List;

public class Kit {

    private final String name;
    private final ItemStack icon;
    private final List<KitPrize> prizes;

    @JsonCreator
    public Kit(@JsonProperty("icon") ItemData iconData, List<KitPrize> prizes) {
        this.name = iconData.getName() != null ? ColorUtils.color(iconData.getName()) : "???";
        this.icon = iconData.toItemStack();
        this.prizes = prizes;
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<KitPrize> getPrizes() {
        return prizes;
    }

}