package de.pnku.mstv_mweaponv.client.ui;

import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.*;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

public class MweaponvCreativeTab extends CreativeModeTabs {

    public static CreativeModeTab WEAPON_VARIANTS;

    public static final CreativeModeTab.Builder MWEAPONV_CMT_BUILDER = FabricItemGroup.builder().title(Component.translatable("itemGroup.weaponVariants")).icon(() -> new ItemStack(WARPED_GOLDEN_SWORD)).displayItems(((displayContext, entries) -> {
        for (Item weaponItem : more_weapons)
        {
            if (!more_tippable_arrows.containsValue(weaponItem)) {
                entries.accept(weaponItem);
            }
        }
    }));

    public static void registerMweaponvCreativeTab() {
        WEAPON_VARIANTS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MoreWeaponVariants.asId("weapon_variants"), MWEAPONV_CMT_BUILDER.build());
        LOGGER.info("Creative Mode Item Tab registered.");
    }
}