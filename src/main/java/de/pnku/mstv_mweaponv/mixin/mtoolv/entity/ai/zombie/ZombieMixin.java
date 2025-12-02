package de.pnku.mstv_mweaponv.mixin.mtoolv.entity.ai.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mtoolv.item.MoreToolVariantItems.*;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.DARK_OAK_IRON_SWORD;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.PALE_OAK_IRON_SWORD;

@Mixin(Zombie.class)
public class ZombieMixin {

    @Unique
    Zombie thisZombie = (Zombie)(Object)this;
    
    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 1))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original){
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SHOVEL)) {
            BlockPos zombiePos = thisZombie.blockPosition();
            String spawnBiomeName = thisZombie.level().getBiome(zombiePos).getRegisteredName();
            Item spawnShovelItem;
            Item spawnShovelItemAlt;
            double spawnShovelVariantProb;
            double spawnShovelVariantAltProb;
            switch (spawnBiomeName) {
                case ("minecraft:savanna"), ("minecraft:savanna_plateau"), ("minecraft:windswept_savanna") -> {
                    spawnShovelItem = ACACIA_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.95;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:birch_forest"), ("minecraft:old_growth_birch_forest") -> {
                    spawnShovelItem = BIRCH_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.9;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:forest"), ("minecraft:meadow") -> {
                    spawnShovelItem = BIRCH_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.2;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:bamboo_jungle") -> {
                    spawnShovelItem = BAMBOO_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.6;
                    spawnShovelItemAlt = JUNGLE_IRON_SHOVEL;
                    spawnShovelVariantAltProb = 0.3;
                }
                case ("minecraft:jungle") -> {
                    spawnShovelItem = JUNGLE_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.8;
                    spawnShovelItemAlt = BAMBOO_IRON_SHOVEL;
                    spawnShovelVariantAltProb = 0.1;
                }
                case ("minecraft:sparse_jungle"), ("minecraft:desert") -> {
                    spawnShovelItem = JUNGLE_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.8;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:cherry_grove") -> {
                    spawnShovelItem = CHERRY_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.925;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:crimson_forest") -> {
                    spawnShovelItem = CRIMSON_IRON_SHOVEL;
                    spawnShovelVariantProb = 1;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:nether_wastes") -> {
                    spawnShovelItem = CRIMSON_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.25;
                    spawnShovelItemAlt = WARPED_IRON_SHOVEL;
                    spawnShovelVariantAltProb = 0.05;
                }
                case ("minecraft:dark_forest") -> {
                    spawnShovelItem = DARK_OAK_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.8;
                    spawnShovelItemAlt = BIRCH_IRON_SHOVEL;
                    spawnShovelVariantAltProb = 0.1;
                }
                case ("minecraft:pale_garden") -> {
                    spawnShovelItem = PALE_OAK_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.95;
                    spawnShovelItemAlt = DARK_OAK_IRON_SHOVEL;
                    spawnShovelVariantAltProb = 0.05;
                }
                case ("minecraft:mangrove_swamp") -> {
                    spawnShovelItem = MANGROVE_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.975;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:old_growth_spruce_taiga"), ("minecraft:old_growth_pine_taiga"), ("minecraft:taiga"),
                     ("minecraft:snowy_taiga") -> {
                    spawnShovelItem = SPRUCE_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.85;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:windswept_forest"), ("minecraft:snowy_plains") -> {
                    spawnShovelItem = SPRUCE_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.3;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:warped_forest") -> {
                    spawnShovelItem = WARPED_IRON_SHOVEL;
                    spawnShovelVariantProb = 1;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                case ("minecraft:soul_sand_valley") -> {
                    spawnShovelItem = WARPED_IRON_SHOVEL;
                    spawnShovelVariantProb = 0.125;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
                default -> {
                    spawnShovelItem = null;
                    spawnShovelVariantProb = 0;
                    spawnShovelItemAlt = null;
                    spawnShovelVariantAltProb = 0;
                }
            }
            if (spawnShovelItem != null) {
                if (spawnShovelItemAlt == null) {
                    if (Math.random() < spawnShovelVariantProb) {
                        thisZombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnShovelItem));
                    } else {
                        original.call(zombie, slot, stack);
                    }
                } else {
                    if (Math.random() < spawnShovelVariantProb) {
                        thisZombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnShovelItem));
                    } else if (Math.random() < (spawnShovelVariantProb + spawnShovelVariantAltProb)) {
                        thisZombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnShovelItemAlt));
                    } else {
                        original.call(zombie, slot, stack);
                    }
                }
            } else {
                original.call(zombie, slot, stack);
            }
        }
    }
}
