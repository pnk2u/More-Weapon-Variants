package de.pnku.mstv_mweaponv.util;

import net.minecraft.world.item.ItemStack;

public interface IArrow {

    String mweaponv$getVariant();
    void mweaponv$setVariant(String variant);
    ItemStack mweaponv$arrowItemStackFromVariant(String arrowVariant, boolean isTipped);
}
