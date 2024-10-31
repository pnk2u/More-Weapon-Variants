package de.pnku.mstv_mweaponv;

import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;


public class MoreWeaponVariants implements ModInitializer {

	public static final String MOD_ID = "mstv-mweaponv";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Boolean isMtoolvLoaded;
	
	@Override
	public void onInitialize() {
		isMtoolvLoaded = FabricLoader.getInstance().isModLoaded("mstv-mtoolv");
		MoreWeaponVariantItems.registerWeaponItems();
		for (Item tippableArrowItem : more_tippable_arrows.keySet()) {
			registerDispenseArrowBehavior(tippableArrowItem);
			Item tippedArrowItem = more_tippable_arrows.get(tippableArrowItem);
			registerDispenseArrowBehavior(tippedArrowItem, true);
		}
	}

	public static ResourceLocation asId(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

}
