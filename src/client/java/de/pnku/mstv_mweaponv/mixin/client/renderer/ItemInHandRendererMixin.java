package de.pnku.mstv_mweaponv.mixin.client.renderer;

import net.minecraft.client.player.LocalPlayer;
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

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void injectedIsChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (more_crossbows.contains(stack.getItem()) && CrossbowItem.isCharged(stack)) {cir.setReturnValue(true);}
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private boolean redirectedItemStackIs(ItemStack itemStack, Item originalItem){
        if (originalItem.equals(Items.CROSSBOW)) {
            return itemStack.getItem() instanceof CrossbowItem;
        } else {
            return itemStack.is(originalItem);
        }
    }

    @Redirect(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getUseItem()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack redirectedGetUseItem(LocalPlayer player){
        if (more_bows.contains(player.getUseItem().getItem())) {return new ItemStack(Items.BOW);}
        else if (more_crossbows.contains(player.getUseItem().getItem())){return new ItemStack(Items.CROSSBOW);}
        else {return player.getUseItem();}
    }
    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getMainHandItem()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack redirectedGetMainHandItem(LocalPlayer player){
        if (more_bows.contains(player.getUseItem().getItem())) {return new ItemStack(Items.BOW);}
        else if (more_crossbows.contains(player.getUseItem().getItem())){return new ItemStack(Items.CROSSBOW);}
        else {return player.getMainHandItem();}
    }
    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack redirectedGetOffhandItem(LocalPlayer player){
        if (more_bows.contains(player.getUseItem().getItem())) {return new ItemStack(Items.BOW);}
        else if (more_crossbows.contains(player.getUseItem().getItem())){return new ItemStack(Items.CROSSBOW);}
        else {return player.getOffhandItem();}
    }
}
