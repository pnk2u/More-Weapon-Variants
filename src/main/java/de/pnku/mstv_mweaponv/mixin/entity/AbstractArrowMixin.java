package de.pnku.mstv_mweaponv.mixin.entity;

import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
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
    protected void injectedAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (abstractArrow instanceof Arrow) {
            if (!this.mweaponv$getVariant().equals("oak")) {
                boolean isTipped = (((Arrow) abstractArrow).potion == Potions.EMPTY);
                String woodVariant = this.mweaponv$getVariant();
                ItemStack arrowStack = mweaponv$arrowItemStackFromVariant(woodVariant, isTipped);
                compound.putString("Type", woodVariant);
                compound.put("item", arrowStack.save(new CompoundTag()));
            }
    }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (abstractArrow instanceof Arrow) {
            if (compound.contains("Type", 8)) {
                this.mweaponv$setVariant(compound.getString("Type"));
            }
        }
    }
}
