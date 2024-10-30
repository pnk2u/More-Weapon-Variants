package de.pnku.mstv_mweaponv.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.TippedArrowRecipe;

import java.util.concurrent.CompletableFuture;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

public class MoreWeaponVariantRecipeGenerator extends FabricRecipeProvider {
    public MoreWeaponVariantRecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        for (Item bowItem : more_bows) {
            Item stickItem = more_weapon_sticks.get(bowItem);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bowItem)
                    .define('/', stickItem)
                    .define('S', Items.STRING)
                    .pattern(" /S")
                    .pattern("/ S")
                    .pattern(" /S")
                    .unlockedBy("has_stick", has(stickItem))
                    .save(exporter);
        }
        for (Item crossbowItem : more_crossbows) {
            Item stickItem = more_weapon_sticks.get(crossbowItem);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, crossbowItem)
                    .define('/', stickItem)
                    .define('S', Items.STRING)
                    .define('H', Items.TRIPWIRE_HOOK)
                    .define('I', Items.IRON_INGOT)
                    .pattern("/I/")
                    .pattern("SHS")
                    .pattern(" / ")
                    .unlockedBy("has_stick", has(stickItem))
                    .unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
                    .save(exporter);
        }
        for (Item arrowItem : more_arrows) {
            if (more_tippable_arrows.containsKey(arrowItem)) {
                Item stickItem = more_weapon_sticks.get(arrowItem);
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, arrowItem)
                        .define('#', Items.FLINT)
                        .define('/', stickItem)
                        .define('F', Items.FEATHER)
                        .pattern("#")
                        .pattern("/")
                        .pattern("F")
                        .unlockedBy("has_stick", has(stickItem))
                        .save(exporter);
            }
            if (more_tippable_arrows.containsValue(arrowItem)) {
                SpecialRecipeBuilder.special(TippedArrowRecipe::new)
                        .save(exporter, BuiltInRegistries.ITEM.getKey(arrowItem));
            }
        }
    }
}
