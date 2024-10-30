package de.pnku.mstv_mweaponv;

import de.pnku.mstv_mweaponv.datagen.MoreWeaponVariantLangGenerator;
import de.pnku.mstv_mweaponv.datagen.MoreWeaponVariantModelGenerator;
import de.pnku.mstv_mweaponv.datagen.MoreWeaponVariantRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class MoreWeaponVariantDatagen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(MoreWeaponVariantRecipeGenerator::new);
		pack.addProvider(MoreWeaponVariantLangGenerator::new);
		pack.addProvider(MoreWeaponVariantModelGenerator::new);
	}

}
