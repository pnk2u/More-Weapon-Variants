package de.pnku.mstv_mweaponv.mixin.client.compat.nea;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.tr7zw.notenoughanimations.util.AnimationUtil;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnimationUtil.class)
public class AnimationUtilMixin {

    @Shadow private static Item crossbow;

    @WrapOperation(method = "getArmPose", at = @At(value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"))
    private static boolean wrappedGetArmPoseAtObjectEquals(Object stackItem, Object item, Operation<Boolean> original) {
        return  original.call(stackItem, item)
                || item.equals(crossbow) && item instanceof CrossbowItem;
    }
}
