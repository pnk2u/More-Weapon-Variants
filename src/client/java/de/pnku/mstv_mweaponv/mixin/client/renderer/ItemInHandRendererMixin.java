package de.pnku.mstv_mweaponv.mixin.client.renderer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void injectedIsChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (more_crossbows.contains(stack.getItem()) && CrossbowItem.isCharged(stack)) {cir.setReturnValue(true);}
    }

    @WrapOperation(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z",
                    ordinal = 0))
    private boolean wrappedItemStackIsFromRenderArmWithItem(ItemStack itemStack, Object originalItem, Operation<Boolean> original) {
        if (originalItem.equals(Items.CROSSBOW)) {
            return itemStack.getItem() instanceof CrossbowItem;
        } else {
            return original.call(itemStack, originalItem);
        }
    }

    @WrapOperation(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getUseItem()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack wrappedGetUseItemFromSelectionUsingItemWhileHoldingBowLike(LocalPlayer player, Operation<ItemStack> original){
        if (more_bows.contains(player.getUseItem().getItem())) {return new ItemStack(Items.BOW);}
        else if (more_crossbows.contains(player.getUseItem().getItem())){return new ItemStack(Items.CROSSBOW);}
        else {return original.call(player);}
    }
    @WrapOperation(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getMainHandItem()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack wrappedGetMainHandItemFromEvaluateWhichHandsToRender(LocalPlayer player, Operation<ItemStack> original){
        if (more_bows.contains(player.getUseItem().getItem())) {return new ItemStack(Items.BOW);}
        else if (more_crossbows.contains(player.getUseItem().getItem())){return new ItemStack(Items.CROSSBOW);}
        else {return original.call(player);}
    }
    @WrapOperation(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack wrappedGetOffhandItemFromEvaluateWhichHandsToRender(LocalPlayer player, Operation<ItemStack> original){
        if (more_bows.contains(player.getUseItem().getItem())) {return new ItemStack(Items.BOW);}
        else if (more_crossbows.contains(player.getUseItem().getItem())){return new ItemStack(Items.CROSSBOW);}
        else {return original.call(player);}
    }
}
