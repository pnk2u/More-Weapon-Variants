package de.pnku.mstv_mweaponv.mixin.inventory;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @WrapOperation(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean wrappedCreateResultItemStackIs(ItemStack firstInputStack, Item secondInputItem, Operation<Boolean> original) {
        if (original.call(firstInputStack, secondInputItem)) {
            return true;
        }

        Item firstInputItem = firstInputStack.getItem();

        if (!(firstInputItem instanceof TieredItem firstTiered) || !(secondInputItem instanceof TieredItem secondTiered)) {
            return false;
        }

        // Require same tool class to avoid cross-type merges (e.g., sword + axe).
        if (firstInputItem.getClass() != secondInputItem.getClass()) {
            return false;
        }

        return firstTiered.getTier() == secondTiered.getTier();
    }
}
