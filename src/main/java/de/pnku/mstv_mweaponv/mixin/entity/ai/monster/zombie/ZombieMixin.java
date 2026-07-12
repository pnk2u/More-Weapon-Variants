package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.SWORD_AND_SPEAR_BY_STICK;
import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.chooseStickForSpawnBiome;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {

    protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 0))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots_ord0(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SWORD)) {
            Item stickItem = chooseStickForSpawnBiome(this);
            Item swordItem = stickItem != null ? SWORD_AND_SPEAR_BY_STICK.get(stickItem)[0] : null;

            if (swordItem != null) {
                this.setItemSlot(slot, new ItemStack(swordItem));
            } else {
                original.call(zombie, slot, stack);
            }
        } else {
            original.call(zombie, slot, stack);
        }
    }
    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 1))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots_ord1(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SPEAR)) {
            Item stickItem = chooseStickForSpawnBiome(this);
            Item spearItem = stickItem != null ? SWORD_AND_SPEAR_BY_STICK.get(stickItem)[1] : null;

            if (spearItem != null) {
                this.setItemSlot(slot, new ItemStack(spearItem));
            } else {
                original.call(zombie, slot, stack);
            }
        } else {
            original.call(zombie, slot, stack);
        }
    }
}
