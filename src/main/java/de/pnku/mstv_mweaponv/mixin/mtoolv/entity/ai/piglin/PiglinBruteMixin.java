package de.pnku.mstv_mweaponv.mixin.mtoolv.entity.ai.piglin;

import de.pnku.mstv_mtoolv.item.MoreToolVariantItems;
import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PiglinBrute.class)
public class PiglinBruteMixin {

    @Redirect(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/piglin/PiglinBrute;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    public void redirectedPopulateDefaultEquipmentSlotSetItemSlot(PiglinBrute piglinBrute, EquipmentSlot equipmentSlot, ItemStack itemStack) {
        if (itemStack.is(Items.GOLDEN_AXE)) {
            if (Math.random() < 0.8) {
                piglinBrute.setItemSlot(equipmentSlot, new ItemStack(MoreToolVariantItems.CRIMSON_GOLDEN_AXE));
            } else if (Math.random() < 0.825) {
                piglinBrute.setItemSlot(equipmentSlot, new ItemStack(MoreToolVariantItems.CRIMSON_BLACKSTONE_AXE));
            } else {
                piglinBrute.setItemSlot(equipmentSlot, itemStack);
            }
        }
    }
}
