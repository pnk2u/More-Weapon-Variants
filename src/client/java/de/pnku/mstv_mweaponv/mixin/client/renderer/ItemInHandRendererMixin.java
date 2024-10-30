package de.pnku.mstv_mweaponv.mixin.client.renderer;

import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_crossbows;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void injectedIsChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (more_crossbows.contains(stack.getItem()) && CrossbowItem.isCharged(stack)) {cir.setReturnValue(true);}
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private boolean redirectedItemStackIs(ItemStack itemStack, Item originalItem){
        if (originalItem.equals(Items.CROSSBOW)) {
            return itemStack.getItem() instanceof CrossbowItem;
        } else {
            return itemStack.is(originalItem);
        }
    }
}
