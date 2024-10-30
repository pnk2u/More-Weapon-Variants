//package de.pnku.mstv_mweaponv.datagen;
//
//import de.pnku.mstv_base.MoreStickVariants;
//import de.pnku.mstv_base.item.MoreStickVariantItem;
//import de.pnku.mstv_mweaponv.MoreWeaponVariants;
//import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
//import net.minecraft.data.models.BlockModelGenerators;
//import net.minecraft.data.models.ItemModelGenerators;
//import net.minecraft.data.models.model.ModelLocationUtils;
//import net.minecraft.data.models.model.ModelTemplates;
//import net.minecraft.data.models.model.TextureMapping;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.Items;
//
//import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;
//
//public class MoreWeaponVariantModelGenerator extends FabricModelProvider {
//    public MoreWeaponVariantModelGenerator(FabricDataOutput dataOutput) {
//        super(dataOutput);
//    }
//
//    @Override
//    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
//
//    }
//
//    @Override
//    public void generateItemModels(ItemModelGenerators weaponItemModelGenerator) {
////        for (Item swordItem : more_swords) {
////            weaponItemModelGenerator.generateFlatItem(swordItem, ModelTemplates.FLAT_HANDHELD_ITEM);
////        }
////        for (Item bowItem : more_bows) {
////            weaponItemModelGenerator.generateFlatItem(bowItem, ModelTemplates.FLAT_ITEM);
////            for (int i = 0; i < 12; i++) {
////                String stickWood;
////                switch (i) {
////                    case 1 -> stickWood = "acacia"; case 2 -> stickWood = "bamboo"; case 3 -> stickWood = "birch"; case 4 -> stickWood = "cherry"; case 5 -> stickWood = "crimson"; case 6 -> stickWood = "dark_oak"; case 7 -> stickWood = "jungle"; case 8 -> stickWood = "mangrove"; case 9 -> stickWood = "oak"; case 10 -> stickWood = "spruce"; case 11 -> stickWood = "warped";
////                    default -> stickWood = "";
////                }
////                if (!stickWood.isEmpty()) {
////                    for (int j = 0; j < 3; j++) {
////                        weaponItemModelGenerator.generateFlatItem(bowItem, "_pulling_" + j + "_" + stickWood + "_arrow", ModelTemplates.FLAT_ITEM);
////                    }
////                }
////            }
////        }
//        for (Item crossbowItem : more_crossbows) {
//            weaponItemModelGenerator.generateFlatItem(crossbowItem, ModelTemplates.FLAT_ITEM);
//            weaponItemModelGenerator.generateFlatItem(crossbowItem, "_standby", ModelTemplates.FLAT_ITEM);
//            weaponItemModelGenerator.generateFlatItem(crossbowItem, "_charged", ModelTemplates.FLAT_ITEM);
//            weaponItemModelGenerator.generateFlatItem(crossbowItem, "_pulling_0", ModelTemplates.FLAT_ITEM);
//            weaponItemModelGenerator.generateFlatItem(crossbowItem, "_pulling_1", ModelTemplates.FLAT_ITEM);
//            weaponItemModelGenerator.generateFlatItem(crossbowItem, "_pulling_2", ModelTemplates.FLAT_ITEM);
//            for (int i = 0; i < 13; i++) {
//                String stickWood;
//                switch (i) {
//                    case 1 -> stickWood = "acacia"; case 2 -> stickWood = "bamboo"; case 3 -> stickWood = "birch"; case 4 -> stickWood = "cherry"; case 5 -> stickWood = "crimson"; case 6 -> stickWood = "dark_oak"; case 7 -> stickWood = "jungle"; case 8 -> stickWood = "mangrove"; case 9 -> stickWood = "oak"; case 10 -> stickWood = "spruce"; case 11 -> stickWood = "warped"; case 12 -> stickWood = "firework";
//                    default -> stickWood = "";
//                }
//                if (!stickWood.isEmpty()) {
//                        weaponItemModelGenerator.generateFlatItem(crossbowItem,  "_" + stickWood + "_arrow", ModelTemplates.FLAT_ITEM);
//                }
//            }
//        }
////        for (Item tippableArrowItem  : more_tippable_arrows.keySet()) {
////            Item tippedArrowItem = more_tippable_arrows.get(tippableArrowItem);
//////            Item stickItem = more_weapon_sticks.get(tippedArrowItem);
//////            String stickWood;
//////            if (stickItem.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickItem.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickItem).mstvWoodType;}
////            weaponItemModelGenerator.generateFlatItem(tippableArrowItem, ModelTemplates.FLAT_ITEM);
////            weaponItemModelGenerator.generateLayeredItem(ModelLocationUtils.getModelLocation(tippedArrowItem), TextureMapping.getItemTexture(Items.TIPPED_ARROW, "_head"), TextureMapping.getItemTexture(tippedArrowItem, "_base"));
////        }
//    }
//}