package de.pnku.mstv_mweaponv.mixin.client.renderer;

import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.asId;

@Mixin(TippableArrowRenderer.class)
public abstract class TippableArrowRendererMixin<T extends AbstractArrow> extends EntityRenderer<T> implements IArrow {

    protected TippableArrowRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }


    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/projectile/Arrow;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    public void injectedGetTextureLocation(Arrow arrowEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        String arrowVariant = ((IArrow) arrowEntity).mweaponv$getVariant();
        ResourceLocation arrowLocation;
        if (!arrowVariant.equals("oak") && !arrowVariant.isEmpty()) {
        arrowLocation = asId("textures/entity/arrow/" + arrowVariant + "_arrow.png");
        cir.setReturnValue(arrowLocation);}
    }
}
