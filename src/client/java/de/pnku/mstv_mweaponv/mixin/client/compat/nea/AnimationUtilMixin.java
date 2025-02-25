package de.pnku.mstv_mweaponv.mixin.client.compat.nea;

import dev.tr7zw.notenoughanimations.util.AnimationUtil;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnimationUtil.class)
public class AnimationUtilMixin {

    @Shadow private static Item crossbow;

    @Redirect(method = "getArmPose", at = @At(value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"))
    private static boolean redirectedGetArmPoseObjectEquals(Object original, Object other) {
        if (original instanceof Item originalItem && other.equals(crossbow)) {
            return originalItem instanceof CrossbowItem;
        } else {
            return original.equals(other);
        }
    }
}
