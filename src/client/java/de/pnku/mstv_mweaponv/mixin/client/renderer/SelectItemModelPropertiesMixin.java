package de.pnku.mstv_mweaponv.mixin.client.renderer;

import de.pnku.mstv_mweaponv.client.renderer.item.properties.select.BowArrowPredicate;
import de.pnku.mstv_mweaponv.client.renderer.item.properties.select.CrossbowArrowPredicate;
import net.minecraft.client.renderer.item.properties.select.ItemBlockState;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.asId;

@Mixin(SelectItemModelProperties.class)
public class SelectItemModelPropertiesMixin {

    @Shadow @Final private static ExtraCodecs.LateBoundIdMapper<ResourceLocation, SelectItemModelProperty.Type<?, ?>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void bootstrap(CallbackInfo ci) {
        ID_MAPPER.put(asId("bowarrow"), BowArrowPredicate.TYPE);
        ID_MAPPER.put(asId("crossbowarrow"), CrossbowArrowPredicate.TYPE);
    }
}
