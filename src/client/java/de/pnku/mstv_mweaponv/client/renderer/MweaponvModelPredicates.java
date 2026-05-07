package de.pnku.mstv_mweaponv.client.renderer;

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

    public static void registerBowPullPredicate(Item bowItem) {
        ItemProperties.register(bowItem, new ResourceLocation("pull"),
                (itemStack, clientLevel, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return livingEntity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
                    }
                });
    }

    public static void registerBowArrowPredicate(Item bowItem) {
        ItemProperties.register(bowItem, ResourceLocation.parse("bowarrow"),
                (itemStack, clientLevel, livingEntity, seed) -> {
                    if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) {
                        Item arrowItem = livingEntity.getProjectile(itemStack).getItem();
                        return more_arrow_ids.getOrDefault(arrowItem, 0) / 100.0F;
                    } else return 0.0F;
                }
        );
    }

    public static void registerCrossbowPullPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("pull"),
                (itemStack, clientLevel, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(itemStack) ? 0.0F : (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / (float) CrossbowItem.getChargeDuration(itemStack);
                    }
                }
        );
    }
    public static void registerCrossbowPullingPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("pulling"),
                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
    }
    public static void registerCrossbowChargedPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("charged"),
                (itemStack, clientLevel, livingEntity, i) -> CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
    }
    public static void registerCrossbowChargedProjectilesPredicate(Item crossbowItem) {
        ItemProperties.register(crossbowItem, ResourceLocation.tryParse("charged_projectiles"),
                (itemStack, clientLevel, livingEntity, i) -> {
            if (CrossbowItem.isCharged(itemStack)) {
                ChargedProjectiles chargedProjectiles = itemStack.get(DataComponents.CHARGED_PROJECTILES);
                if (chargedProjectiles != null) {
                  for (Item arrowItem : more_arrow_ids.keySet()) {
                    if (CrossbowItem.containsChargedProjectile(itemStack, arrowItem)) {
                        return more_arrow_ids.get(arrowItem)/100.0F;
                    }
                  }
                }
            return 0.0f;}
        );
    }
}
