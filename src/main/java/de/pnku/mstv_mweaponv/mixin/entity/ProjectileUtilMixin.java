package de.pnku.mstv_mweaponv.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_arrows;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_weapon_sticks;

@Mixin(ProjectileUtil.class)
public abstract class ProjectileUtilMixin {

    @Inject(method = "getMobArrow", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private static void injectedGetMobArrow(LivingEntity shooter, ItemStack arrowStack, float velocity, CallbackInfoReturnable<AbstractArrow> cir, @Local AbstractArrow abstractArrow) {
        Item arrowVariantItem = arrowStack.getItem();
        Item stickItem;
        String arrowVariant;
        if (more_arrows.contains(arrowVariantItem)){
            stickItem = more_weapon_sticks.get(arrowVariantItem);
            if (stickItem.equals(Items.BAMBOO)) {arrowVariant = "bamboo";} else if (stickItem.equals(Items.STICK)) {arrowVariant = "oak";} else { arrowVariant = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            ((IArrow) abstractArrow).mweaponv$setVariant(arrowVariant);
            cir.setReturnValue(abstractArrow);
        }
    }
}
