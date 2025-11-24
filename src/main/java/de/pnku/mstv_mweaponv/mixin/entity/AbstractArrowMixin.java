package de.pnku.mstv_mweaponv.mixin.entity;

import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin implements IArrow {

    @Unique
    AbstractArrow abstractArrow = (AbstractArrow) (Object) this;

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void injectedAddAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        if (abstractArrow instanceof Arrow) {
            valueOutput.putString("Type", this.mweaponv$getVariant());
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        if (abstractArrow instanceof Arrow) {
            this.mweaponv$setVariant(valueInput.getStringOr("Type", "oak"));
        }
    }
}
