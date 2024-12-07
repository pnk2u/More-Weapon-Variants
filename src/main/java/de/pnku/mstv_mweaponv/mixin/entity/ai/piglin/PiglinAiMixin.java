package de.pnku.mstv_mweaponv.mixin.entity.ai.piglin;

import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_crossbows;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    @Redirect(method = "hasCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean redirectedHasCrossbow(LivingEntity entity, Item item) {
        if (item.equals(MoreWeaponVariantItems.WARPED_CROSSBOW)) {return false;} else {return more_crossbows.contains(item) || item.equals(Items.CROSSBOW);}
    }
}
