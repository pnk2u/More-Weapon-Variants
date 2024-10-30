package de.pnku.mstv_mweaponv.client.renderer;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

import java.util.HashMap;
import java.util.Map;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;
import static de.pnku.mstv_mweaponv.MoreWeaponVariants.*;

public class MweaponvModelPredicates {

    public static void registerWeaponVariantPredicates() {
        for (Item bowItem : more_bows) {
            registerBowPullPredicate(bowItem);
            registerBowArrowPredicate(bowItem);
        }
        registerBowArrowPredicate(Items.BOW);
        for (Item crossbowItem : more_crossbows) {
            registerCrossbowPullPredicate(crossbowItem);
            registerCrossbowPullingPredicate(crossbowItem);
            registerCrossbowChargedPredicate(crossbowItem);
            registerCrossbowChargedProjectilesPredicate(crossbowItem);
        }
        registerCrossbowChargedProjectilesPredicate(Items.CROSSBOW);
    }

    //No pulling check as it is included in bowarrow
//    private static void registerBowPullingPredicate(Item bowItem) {
//        ItemProperties.register(bowItem, new ResourceLocation("pulling"),
//                (itemStack, clientLevel, livingEntity, seed) -> {
//                    return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
//                });
//    }

    private static void registerBowPullPredicate(Item bowItem) {
        ItemProperties.register(bowItem, new ResourceLocation("pull"),
                (itemStack, clientLevel, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return livingEntity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
                    }
                });
    }
    private static void registerBowArrowPredicate(Item bowItem) {
        ItemProperties.register(bowItem, new ResourceLocation("bowarrow"),
            (itemStack, clientLevel, livingEntity, seed) -> {
            String arrowStickWood;
            // Check from PullingPredicate
            if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) {
                Item arrowItem = livingEntity.getProjectile(itemStack).getItem();
                if (arrowItem.equals(Items.ARROW)||arrowItem.equals(Items.TIPPED_ARROW)) {return 0.09F;}
                if (more_arrows.contains(arrowItem)) {
                    Item stickItem = more_weapon_sticks.get(arrowItem);
                    if (stickItem.equals(Items.BAMBOO)) {arrowStickWood = "bamboo";}
                    if (stickItem.equals(Items.STICK)) {arrowStickWood = "oak";}
                    else { arrowStickWood = ((MoreStickVariantItem) (more_weapon_sticks.get(arrowItem))).mstvWoodType;
                    }
                } else {arrowStickWood = null;}
            } else {arrowStickWood = null;}
            switch (arrowStickWood) {
                case ("acacia") -> {return 0.01F;}
                case ("bamboo") -> {return 0.02F;}
                case ("birch") -> {return 0.03F;}
                case ("cherry") -> {return 0.04F;}
                case ("crimson") -> {return 0.05F;}
                case ("dark_oak") -> {return 0.06F;}
                case ("jungle") -> {return 0.07F;}
                case ("mangrove") -> {return 0.08F;}
                case ("oak") -> {return 0.09F;}
                case ("spruce") -> {return 0.10F;}
                case ("warped") -> {return 0.11F;}
                case null, default -> {return 0.0F;}
            }
            });
    }
    private static void registerCrossbowPullPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, new ResourceLocation("pull"),
                (itemStack, clientLevel, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(itemStack) ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(itemStack);
                    }
                });
    }
    private static void registerCrossbowPullingPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, new ResourceLocation("pulling"),
                (itemStack, clientLevel, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
    }
    private static void registerCrossbowChargedPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, new ResourceLocation("charged"),
                (itemStack, clientLevel, livingEntity, i) -> {
            return CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
    }
    private static void registerCrossbowChargedProjectilesPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, new ResourceLocation("charged_projectiles"),
                (itemStack, clientLevel, livingEntity, i) -> {
            if (CrossbowItem.isCharged(itemStack)) {
                ChargedProjectiles chargedProjectiles = (ChargedProjectiles) itemStack.get(DataComponents.CHARGED_PROJECTILES);
                if (chargedProjectiles != null) {
                if (chargedProjectiles.contains(ACACIA_ARROW) || chargedProjectiles.contains(ACACIA_TIPPED_ARROW)) {return 0.01F;}
                else if (chargedProjectiles.contains(BAMBOO_ARROW) || chargedProjectiles.contains(BAMBOO_TIPPED_ARROW)) {return 0.02F;}
                else if (chargedProjectiles.contains(BIRCH_ARROW) || chargedProjectiles.contains(BIRCH_TIPPED_ARROW)) {return 0.03F;}
                else if (chargedProjectiles.contains(CHERRY_ARROW) || chargedProjectiles.contains(CHERRY_TIPPED_ARROW)) {return 0.04F;}
                else if (chargedProjectiles.contains(CRIMSON_ARROW) || chargedProjectiles.contains(CRIMSON_TIPPED_ARROW)) {return 0.05F;}
                else if (chargedProjectiles.contains(DARK_OAK_ARROW) || chargedProjectiles.contains(DARK_OAK_TIPPED_ARROW)) {return 0.06F;}
                else if (chargedProjectiles.contains(JUNGLE_ARROW) || chargedProjectiles.contains(JUNGLE_TIPPED_ARROW)) {return 0.07F;}
                else if (chargedProjectiles.contains(MANGROVE_ARROW) || chargedProjectiles.contains(MANGROVE_TIPPED_ARROW)) {return 0.08F;}
                else if (chargedProjectiles.contains(Items.ARROW) || chargedProjectiles.contains(Items.TIPPED_ARROW)) {return 0.09F;}
                else if (chargedProjectiles.contains(SPRUCE_ARROW) || chargedProjectiles.contains(SPRUCE_TIPPED_ARROW)) {return 0.10F;}
                else if (chargedProjectiles.contains(WARPED_ARROW) || chargedProjectiles.contains(WARPED_TIPPED_ARROW)) {return 0.11F;}
                else if (chargedProjectiles.contains(Items.FIREWORK_ROCKET)) {return 0.12F;}
                }
            }
            return 0.0F;
        });
    }
}
