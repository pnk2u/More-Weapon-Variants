package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.piglin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_crossbows;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    @WrapOperation(method = "hasCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean wrappedHasCrossbowAtLivingEntityIsHolding(LivingEntity entity, Item item, Operation<Boolean> original) {
        return item.equals(MoreWeaponVariantItems.WARPED_CROSSBOW)
                || original.call(entity, item)
                || more_crossbows.contains(item);
    }
}
