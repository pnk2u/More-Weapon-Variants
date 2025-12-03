package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.skeleton;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WitherSkeleton.class)
public abstract class WitherSkeletonMixin {

    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/WitherSkeleton;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    public void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots(WitherSkeleton skeleton, EquipmentSlot equipmentSlot, ItemStack itemStack, Operation<Void> original) {
        if (itemStack.is(Items.STONE_SWORD)){
            if (Math.random() < 0.4) {
                skeleton.setItemSlot(equipmentSlot, new ItemStack(MoreWeaponVariantItems.CRIMSON_STONE_SWORD));
            } else if (Math.random() < 0.825) {
                skeleton.setItemSlot(equipmentSlot, new ItemStack(MoreWeaponVariantItems.CRIMSON_BLACKSTONE_SWORD));
            } else {
                original.call(skeleton, equipmentSlot, itemStack);
            }
        }
    }
}
