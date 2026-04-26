package de.pnku.mstv_mweaponv.mixin.client.player;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_bows;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {

    @WrapOperation(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean wrappedGetFieldOfViewModifierAtStackIs(ItemStack stack, Item item, Operation<Boolean> original){
        return  original.call(stack, item)
                || more_bows.contains(stack.getItem());
    }
}
