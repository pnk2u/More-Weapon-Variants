package de.pnku.mstv_mweaponv.mixin.mtoolv.entity.ai.illager;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mtoolv.item.MoreToolVariantItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Vindicator.class)
public class VindicatorMixin {

    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/illager/Vindicator;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    public void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots(Vindicator vindicator, EquipmentSlot equipmentSlot, ItemStack itemStack, Operation<Void> original) {
        if (itemStack.is(Items.IRON_AXE)) {
            if (Math.random() < 0.95) {
                vindicator.setItemSlot(equipmentSlot, new ItemStack(MoreToolVariantItems.DARK_OAK_IRON_AXE));
            } else if (Math.random() < 0.9625) {
                vindicator.setItemSlot(equipmentSlot, new ItemStack(MoreToolVariantItems.DARK_OAK_DEEPSLATE_AXE));
            } else {
                original.call(vindicator, equipmentSlot, itemStack);
            }
        }
    }
}
