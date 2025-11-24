package de.pnku.mstv_mweaponv.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
            compound.putString("Type", this.mweaponv$getVariant());
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

    @WrapOperation(method = "shotFromCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean wrappedStackIsFromShotFromCrossbow(ItemStack originalStack, Item item, Operation<Boolean> original) {
        return originalStack.getItem() instanceof CrossbowItem;
    }
}
