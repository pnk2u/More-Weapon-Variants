package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.JUNGLE_IRON_SPEAR;

@Mixin(Husk.class)
public class HuskMixin {
    @WrapOperation(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Husk;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    protected void wrappedSetItemSlotFromFinalizeSpawn(Husk husk, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (stack.getItem() == Items.IRON_SPEAR) {
            original.call(husk, slot, new ItemStack(JUNGLE_IRON_SPEAR));
        } else {
            original.call(husk, slot, stack);
        }
    }
}
