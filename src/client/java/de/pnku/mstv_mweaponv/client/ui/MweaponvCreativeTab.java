package de.pnku.mstv_mweaponv.client.ui;

import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.LOGGER;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

public class MweaponvCreativeTab extends CreativeModeTabs {

    public static CreativeModeTab WEAPON_VARIANTS;

    public static final CreativeModeTab.Builder MWEAPONV_CMT_BUILDER = FabricCreativeModeTab.builder().title(Component.translatable("itemGroup.weaponVariants")).icon(() -> new ItemStack(WARPED_GOLDEN_SWORD)).displayItems(((displayContext, entries) -> {
        for (Item weaponItem : more_weapons)
        {
            if (!more_tippable_arrows.containsValue(weaponItem)) {
                entries.accept(weaponItem);
            }
        }
    }));

    public static void registerMweaponvCreativeTab() {
        WEAPON_VARIANTS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MoreWeaponVariants.withModId("weapon_variants"), MWEAPONV_CMT_BUILDER.build());
        LOGGER.info("Creative Mode Item Tab registered.");
    }

    private static void addWeaponItemListToVanillaCMT(List<Item> weaponItemList, Item vanillaItem) {
        for (int i = 9; i >= 0; i--) {
            Item weaponItem = weaponItemList.get(i);
            if (i != 0 && i != 9) {
                int j = i + 1;
                CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(entries -> entries.insertBefore(weaponItemList.get(j), weaponItem));
            } else {
                CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(entries -> entries.insertAfter(vanillaItem, weaponItem));
            }
        }
    }

    public static void addWeaponItemListsToVanillaCMT(){
        addWeaponItemListToVanillaCMT(more_bows, Items.BOW);
        addWeaponItemListToVanillaCMT(more_crossbows, Items.CROSSBOW);
        addWeaponItemListToVanillaCMT(more_tippable_arrows.keySet().stream().toList(), Items.ARROW);
    }
}