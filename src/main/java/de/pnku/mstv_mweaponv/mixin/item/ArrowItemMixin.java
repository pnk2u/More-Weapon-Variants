package de.pnku.mstv_mweaponv.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(ArrowItem.class)
public abstract class ArrowItemMixin implements IArrow {

    @Inject(method = "createArrow", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    protected void injectedCreateProjectile(Level level, ItemStack ammo, LivingEntity shooter, CallbackInfoReturnable<AbstractArrow> cir, @Local Arrow arrow) {
        Item arrowVariantItem = ammo.getItem();
        Item stickItem;
        String arrowVariant;
        if (more_arrows.contains(arrowVariantItem)){
            stickItem = more_weapon_sticks.get(arrowVariantItem);
            if (stickItem.equals(Items.BAMBOO)) {arrowVariant = "bamboo";} else if (stickItem.equals(Items.STICK)) {arrowVariant = "oak";} else { arrowVariant = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            ((IArrow) arrow).mweaponv$setVariant(arrowVariant);
            cir.setReturnValue(arrow);
        }
    }
}
