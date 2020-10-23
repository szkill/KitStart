package ru.beniocoder.kitstart.util;

import org.bukkit.Material;

import java.util.EnumSet;
import java.util.Set;

public class Materials {

    public static final Set<Material> HELMETS = EnumSet.of(
            Material.CHAINMAIL_HELMET,
            Material.DIAMOND_HELMET,
            Material.GOLDEN_HELMET,
            Material.IRON_HELMET,
            Material.LEATHER_HELMET,
            Material.TURTLE_HELMET
    );

    public static final Set<Material> CHESTPLATES = EnumSet.of(
            Material.CHAINMAIL_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.LEATHER_CHESTPLATE
    );

    public static final Set<Material> LEGGINGS = EnumSet.of(
            Material.CHAINMAIL_LEGGINGS,
            Material.DIAMOND_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.LEATHER_LEGGINGS
    );

    public static final Set<Material> BOOTS = EnumSet.of(
            Material.CHAINMAIL_BOOTS,
            Material.DIAMOND_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.IRON_BOOTS,
            Material.LEATHER_BOOTS
    );

    public static final Set<Material> ARMORS = EnumSet.noneOf(Material.class);

    static {
        ARMORS.addAll(HELMETS);
        ARMORS.addAll(CHESTPLATES);
        ARMORS.addAll(LEGGINGS);
        ARMORS.addAll(BOOTS);
    }

}