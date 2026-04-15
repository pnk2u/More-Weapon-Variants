package de.pnku.mstv_mweaponv.mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.withModId;

@Mixin(BowItem.class)
public abstract class BowItemMixin {
    @WrapOperation(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean wrappedReleasUsingItemStackIs(ItemStack stack, Item item, Operation<Boolean> original) {
        if (stack.getItem() instanceof ArrowItem) {
            return stack.is(TagKey.create(Registries.ITEM, withModId("all_untipped_arrows")));
        }
        return original.call(stack, item);
    }
}
