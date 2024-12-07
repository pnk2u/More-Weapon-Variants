package de.pnku.mstv_mweaponv.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.LOGGER;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin implements IArrow {

    @Inject(method = "createProjectile", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    protected void injectedCreateProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit, CallbackInfoReturnable<Projectile> cir, @Local AbstractArrow abstractArrow) {
        Item arrowVariantItem = ammo.getItem();
        Item stickItem;
        String arrowVariant;
        LOGGER.info("Weapon trying to createProjectile: " + weapon.getDescriptionId());
        if (more_arrows.contains(arrowVariantItem)){
            stickItem = more_weapon_sticks.get(arrowVariantItem);
            if (stickItem.equals(Items.BAMBOO)) {arrowVariant = "bamboo";} else if (stickItem.equals(Items.STICK)) {arrowVariant = "oak";} else { arrowVariant = ((MoreStickVariantItem) stickItem).mstvWoodType;}
            ((IArrow) abstractArrow).mweaponv$setVariant(arrowVariant);
            cir.setReturnValue(abstractArrow);
        }
    }
}
