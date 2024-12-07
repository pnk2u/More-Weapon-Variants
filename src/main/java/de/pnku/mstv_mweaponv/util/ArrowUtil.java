package de.pnku.mstv_mweaponv.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.LOGGER;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

public class ArrowUtil {

    public static Item arrowFromProjectileWeapon(Item weaponItem, boolean isTipped) {
        LOGGER.info("Checking weaponItem-descId to get arrow: " + weaponItem.getDescriptionId());
        switch (weaponItem.getDescriptionId()) {
            case ("item.mstv-mweaponv.acacia_bow"), ("item.mstv-mweaponv.acacia_crossbow") -> {return !isTipped ? ACACIA_ARROW : ACACIA_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.bamboo_bow"), ("item.mstv-mweaponv.bamboo_crossbow") -> {return !isTipped ? BAMBOO_ARROW : BAMBOO_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.birch_bow"), ("item.mstv-mweaponv.birch_crossbow") -> {return !isTipped ? BIRCH_ARROW : BIRCH_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.cherry_bow"), ("item.mstv-mweaponv.cherry_crossbow") -> {return !isTipped ? CHERRY_ARROW : CHERRY_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.crimson_bow"), ("item.mstv-mweaponv.crimson_crossbow") -> {return !isTipped ? CRIMSON_ARROW : CRIMSON_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.dark_oak_bow"), ("item.minecraft.crossbow") -> {return !isTipped ? DARK_OAK_ARROW : DARK_OAK_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.jungle_bow"), ("item.mstv-mweaponv.jungle_crossbow") -> {return !isTipped ? JUNGLE_ARROW : JUNGLE_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.mangrove_bow"), ("item.mstv-mweaponv.mangrove_crossbow") -> {return !isTipped ? MANGROVE_ARROW : MANGROVE_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.spruce_bow"), ("item.mstv-mweaponv.spruce_crossbow") -> {return !isTipped ? SPRUCE_ARROW : SPRUCE_TIPPED_ARROW;}
            case ("item.mstv-mweaponv.warped_bow"), ("item.mstv-mweaponv.warped_crossbow") -> {return !isTipped ? WARPED_ARROW : WARPED_TIPPED_ARROW;}
            default -> {return !isTipped ? Items.ARROW : Items.TIPPED_ARROW;}
        }
    }
}
