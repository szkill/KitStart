package ru.beniocoder.kitstart.kit.prize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.beniocoder.kitstart.util.Materials;

public class ItemKitPrize implements KitPrize {

    private final ItemStack prizeItem;

    @JsonCreator
    protected ItemKitPrize(@JsonProperty("item") ItemSupplier itemSupplier) {
        prizeItem = itemSupplier.get();
    }

    @Override
    public void giveTo(Player player) {
        Material material = prizeItem.getType();
        if (Materials.HELMETS.contains(material)) {
            player.getInventory().setHelmet(prizeItem);
        } else if (Materials.CHESTPLATES.contains(material)) {
            player.getInventory().setChestplate(prizeItem);
        } else if (Materials.LEGGINGS.contains(material)) {
            player.getInventory().setLeggings(prizeItem);
        } else if (Materials.BOOTS.contains(material)) {
            player.getInventory().setBoots(prizeItem);
        } else {
            player.getInventory().addItem(prizeItem);
        }
    }

}