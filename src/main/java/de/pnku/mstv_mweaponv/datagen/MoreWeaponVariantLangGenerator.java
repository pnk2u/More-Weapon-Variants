package de.pnku.mstv_mweaponv.datagen;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.apache.commons.text.WordUtils;

import java.util.concurrent.CompletableFuture;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

public class MoreWeaponVariantLangGenerator extends FabricLanguageProvider {
    public MoreWeaponVariantLangGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        for (Item swordItem : more_swords) {
            Item stickItem = more_weapon_sticks.get(swordItem);
            String stickWood;
            if (stickItem.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickItem.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            String swordTier = more_sword_tiers.get(swordItem).toString();
            String swordName = WordUtils.capitalizeFully(stickWood + " " + swordTier + " Sword");
            translationBuilder.add(swordItem, swordName);
        }
        for (Item bowItem : more_bows) {
            Item stickItem = more_weapon_sticks.get(bowItem);
            String stickWood;
            if (stickItem.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickItem.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            String bowName = WordUtils.capitalizeFully(stickWood  + " Bow");
            translationBuilder.add(bowItem, bowName);
        }
        for (Item crossbowItem : more_crossbows) {
            Item stickItem = more_weapon_sticks.get(crossbowItem);
            String stickWood;
            if (stickItem.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickItem.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            String crossbowName = WordUtils.capitalizeFully(stickWood + " Crossbow");
            translationBuilder.add(crossbowItem, crossbowName);
        }
        for (Item arrowItem : more_arrows) {
            Item stickItem = more_weapon_sticks.get(arrowItem);
            String stickWood;
            if (stickItem.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickItem.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            String tippedPrefix = "";
            if (more_tippable_arrows.containsValue(arrowItem)) {tippedPrefix = "Tipped ";}
            String arrowName = WordUtils.capitalizeFully(tippedPrefix + stickWood + " Arrow");
            translationBuilder.add(arrowItem, arrowName);
        }
    }
}