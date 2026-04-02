package de.pnku.mstv_mweaponv.mixin.client.player;

import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {

    @Redirect(method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectedGetArmPoseStackIs(ItemStack stack, Object o) {
        return stack.getItem() instanceof CrossbowItem;
    }

}
