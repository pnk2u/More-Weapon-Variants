package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.zombie;

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

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(Zombie.class)
public class ZombieMixin {

    @Unique
    Zombie thisZombie = (Zombie)(Object)this;
    
    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 0))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original){
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SWORD)) {
            BlockPos zombiePos = thisZombie.blockPosition();
            String spawnBiomeName = thisZombie.level().getBiome(zombiePos).getRegisteredName();
            Item spawnSwordItem;
            Item spawnSwordItemAlt;
            double spawnSwordVariantProb;
            double spawnSwordVariantAltProb;
            switch (spawnBiomeName) {
                case ("minecraft:savanna"), ("minecraft:savanna_plateau"), ("minecraft:windswept_savanna") -> {
                    spawnSwordItem = ACACIA_IRON_SWORD;
                    spawnSwordVariantProb = 0.95;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:birch_forest"), ("minecraft:old_growth_birch_forest") -> {
                    spawnSwordItem = BIRCH_IRON_SWORD;
                    spawnSwordVariantProb = 0.9;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:forest"), ("minecraft:meadow") -> {
                    spawnSwordItem = BIRCH_IRON_SWORD;
                    spawnSwordVariantProb = 0.2;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:bamboo_jungle") -> {
                    spawnSwordItem = BAMBOO_IRON_SWORD;
                    spawnSwordVariantProb = 0.6;
                    spawnSwordItemAlt = JUNGLE_IRON_SWORD;
                    spawnSwordVariantAltProb = 0.3;
                }
                case ("minecraft:jungle") -> {
                    spawnSwordItem = JUNGLE_IRON_SWORD;
                    spawnSwordVariantProb = 0.8;
                    spawnSwordItemAlt = BAMBOO_IRON_SWORD;
                    spawnSwordVariantAltProb = 0.1;
                }
                case ("minecraft:sparse_jungle"), ("minecraft:desert") -> {
                    spawnSwordItem = JUNGLE_IRON_SWORD;
                    spawnSwordVariantProb = 0.8;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:cherry_grove") -> {
                    spawnSwordItem = CHERRY_IRON_SWORD;
                    spawnSwordVariantProb = 0.925;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:crimson_forest") -> {
                    spawnSwordItem = CRIMSON_IRON_SWORD;
                    spawnSwordVariantProb = 1;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:nether_wastes") -> {
                    spawnSwordItem = CRIMSON_IRON_SWORD;
                    spawnSwordVariantProb = 0.25;
                    spawnSwordItemAlt = WARPED_IRON_SWORD;
                    spawnSwordVariantAltProb = 0.05;
                }
                case ("minecraft:dark_forest") -> {
                    spawnSwordItem = DARK_OAK_IRON_SWORD;
                    spawnSwordVariantProb = 0.8;
                    spawnSwordItemAlt = BIRCH_IRON_SWORD;
                    spawnSwordVariantAltProb = 0.1;
                }
                case ("minecraft:pale_garden") -> {
                    spawnSwordItem = PALE_OAK_IRON_SWORD;
                    spawnSwordVariantProb = 0.95;
                    spawnSwordItemAlt = DARK_OAK_IRON_SWORD;
                    spawnSwordVariantAltProb = 0.05;
                }
                case ("minecraft:mangrove_swamp") -> {
                    spawnSwordItem = MANGROVE_IRON_SWORD;
                    spawnSwordVariantProb = 0.975;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:old_growth_spruce_taiga"), ("minecraft:old_growth_pine_taiga"), ("minecraft:taiga"),
                     ("minecraft:snowy_taiga") -> {
                    spawnSwordItem = SPRUCE_IRON_SWORD;
                    spawnSwordVariantProb = 0.85;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:windswept_forest"), ("minecraft:snowy_plains") -> {
                    spawnSwordItem = SPRUCE_IRON_SWORD;
                    spawnSwordVariantProb = 0.3;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:warped_forest") -> {
                    spawnSwordItem = WARPED_IRON_SWORD;
                    spawnSwordVariantProb = 1;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                case ("minecraft:soul_sand_valley") -> {
                    spawnSwordItem = WARPED_IRON_SWORD;
                    spawnSwordVariantProb = 0.125;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
                default -> {
                    spawnSwordItem = null;
                    spawnSwordVariantProb = 0;
                    spawnSwordItemAlt = null;
                    spawnSwordVariantAltProb = 0;
                }
            }
            if (spawnSwordItem != null) {
                if (spawnSwordItemAlt == null) {
                    if (Math.random() < spawnSwordVariantProb) {
                        thisZombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnSwordItem));
                    } else {
                        original.call(zombie, slot, stack);
                    }
                } else {
                    if (Math.random() < spawnSwordVariantProb) {
                        thisZombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnSwordItem));
                    } else if (Math.random() < (spawnSwordVariantProb + spawnSwordVariantAltProb)) {
                        thisZombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnSwordItemAlt));
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
