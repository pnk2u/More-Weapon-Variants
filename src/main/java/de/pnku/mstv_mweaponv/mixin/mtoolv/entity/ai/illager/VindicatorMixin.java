package de.pnku.mstv_mweaponv.mixin.mtoolv.entity.ai.illager;

import de.pnku.mstv_mtoolv.item.MoreToolVariantItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Vindicator.class)
public class VindicatorMixin {

    @Redirect(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Vindicator;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    public void redirectedPopulateDefaultEquipmentSlotSetItemSlot(Vindicator vindicator, EquipmentSlot equipmentSlot, ItemStack itemStack) {
        if (itemStack.is(Items.IRON_AXE)) {
            if (Math.random() < 0.95) {
                vindicator.setItemSlot(equipmentSlot, new ItemStack(MoreToolVariantItems.DARK_OAK_IRON_AXE));
            } else if (Math.random() < 0.9625) {
                vindicator.setItemSlot(equipmentSlot, new ItemStack(MoreToolVariantItems.DARK_OAK_DEEPSLATE_AXE));
            } else {
                vindicator.setItemSlot(equipmentSlot, itemStack);
            }
        }
    }
}
