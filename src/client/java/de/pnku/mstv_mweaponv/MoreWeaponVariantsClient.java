package de.pnku.mstv_mweaponv;

import de.pnku.mstv_mweaponv.client.renderer.MweaponvModelPredicates;
import de.pnku.mstv_mweaponv.client.ui.MweaponvCreativeTab;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value = EnvType.CLIENT)
public class MoreWeaponVariantsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MweaponvCreativeTab.registerMweaponvCreativeTab();
		MweaponvCreativeTab.addWeaponItemListsToVanillaCMT();
		MweaponvModelPredicates.registerWeaponVariantPredicates();
	}
}
