package de.pnku.mstv_mweaponv.client.renderer;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

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
        ItemProperties.register(bowItem, ResourceLocation.tryParse("bowarrow"),
            (itemStack, clientLevel, livingEntity, seed) -> {
            String arrowStickWood;
            // Check from PullingPredicate
            if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) {
                Item arrowItem = livingEntity.getProjectile(itemStack).getItem();
                if (arrowItem.equals(Items.ARROW)||arrowItem.equals(Items.TIPPED_ARROW)) {return 0.09F;}
                if (more_arrows.contains(arrowItem)) {
                    Item stickItem = more_weapon_sticks.get(arrowItem);
                    if (stickItem.equals(Items.BAMBOO)) {arrowStickWood = "bamboo";}
                    else if (stickItem.equals(Items.STICK)) {arrowStickWood = "oak";}
                    else { arrowStickWood = ((MoreStickVariantItem) (more_weapon_sticks.get(arrowItem))).mstvWoodType;
                    }
                } else {arrowStickWood = null;}
            } else {arrowStickWood = null;}
                if (arrowStickWood == null) {return 0.0F;} else {
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
                        default -> {return 0.0F;}
                    }
                }
            });
    }
    private static void registerCrossbowPullPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("pull"),
                (itemStack, clientLevel, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(itemStack) ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(itemStack);
                    }
                });
    }
    private static void registerCrossbowPullingPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("pulling"),
                (itemStack, clientLevel, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
    }
    private static void registerCrossbowChargedPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("charged"),
                (itemStack, clientLevel, livingEntity, i) -> {
            return CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
    }
    private static void registerCrossbowChargedProjectilesPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("charged_projectiles"),
                (itemStack, clientLevel, livingEntity, i) -> {
            if (CrossbowItem.isCharged(itemStack)) {
                if (CrossbowItem.containsChargedProjectile(itemStack, ACACIA_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, ACACIA_TIPPED_ARROW)) {return 0.01F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, BAMBOO_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, BAMBOO_TIPPED_ARROW)) {return 0.02F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, BIRCH_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, BIRCH_TIPPED_ARROW)) {return 0.03F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, CHERRY_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, CHERRY_TIPPED_ARROW)) {return 0.04F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, CRIMSON_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, CRIMSON_TIPPED_ARROW)) {return 0.05F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, DARK_OAK_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, DARK_OAK_TIPPED_ARROW)) {return 0.06F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, JUNGLE_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, JUNGLE_TIPPED_ARROW)) {return 0.07F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, MANGROVE_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, MANGROVE_TIPPED_ARROW)) {return 0.08F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, Items.ARROW) || CrossbowItem.containsChargedProjectile(itemStack, Items.TIPPED_ARROW)) {return 0.09F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, SPRUCE_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, SPRUCE_TIPPED_ARROW)) {return 0.10F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, WARPED_ARROW) || CrossbowItem.containsChargedProjectile(itemStack, WARPED_TIPPED_ARROW)) {return 0.11F;}
                else if (CrossbowItem.containsChargedProjectile(itemStack, Items.FIREWORK_ROCKET)) {return 0.12F;}
                }
            return 0.0f;}
        );
    }
}
