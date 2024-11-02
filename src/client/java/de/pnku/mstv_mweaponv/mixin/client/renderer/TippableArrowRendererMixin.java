package de.pnku.mstv_mweaponv.mixin.client.renderer;

import de.pnku.mstv_mweaponv.client.renderer.MweaponvTippableArrowRenderState;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.client.renderer.entity.state.TippableArrowRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.asId;

@Mixin(TippableArrowRenderer.class)
public abstract class TippableArrowRendererMixin extends EntityRenderer<AbstractArrow, MweaponvTippableArrowRenderState> implements IArrow {

    protected TippableArrowRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/TippableArrowRenderState;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    public void injectedGetTextureLocation(TippableArrowRenderState tippableArrowRenderState, CallbackInfoReturnable<ResourceLocation> cir) {
        MweaponvTippableArrowRenderState mweaponvTippableArrowRenderState = (MweaponvTippableArrowRenderState) tippableArrowRenderState;
        if (mweaponvTippableArrowRenderState.arrowVariant != null) {
            String arrowVariant = mweaponvTippableArrowRenderState.arrowVariant;
            if (!arrowVariant.equals("oak")) {
                ResourceLocation arrowLocation = asId("textures/entity/arrow/" + arrowVariant + "_arrow.png");
                cir.setReturnValue(arrowLocation);
            }
        }
    }

    @Inject(method = "createRenderState()Lnet/minecraft/client/renderer/entity/state/TippableArrowRenderState;", at = @At("HEAD"), cancellable = true)
    public void injectedCreateRenderState(CallbackInfoReturnable<TippableArrowRenderState> cir){
        cir.setReturnValue(new MweaponvTippableArrowRenderState());
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/projectile/Arrow;Lnet/minecraft/client/renderer/entity/state/TippableArrowRenderState;F)V", at = @At("HEAD"))
    public void injectedExtractRenderState(Arrow arrow, TippableArrowRenderState tippableArrowRenderState, float f, CallbackInfo ci) {
        MweaponvTippableArrowRenderState mweaponvTippableArrowRenderState = (MweaponvTippableArrowRenderState) tippableArrowRenderState;
        mweaponvTippableArrowRenderState.arrowVariant = ((IArrow) arrow).mweaponv$getVariant();
    }
}
