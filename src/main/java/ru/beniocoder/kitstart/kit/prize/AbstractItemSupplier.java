package ru.beniocoder.kitstart.kit.prize;

import org.bukkit.inventory.ItemStack;

public abstract class AbstractItemSupplier implements ItemSupplier {

    protected final ItemStack itemStack;

    protected AbstractItemSupplier(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack get() {
        return itemStack;
    }

}