package de.pnku.mstv_mweaponv.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.LOGGER;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_arrows;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_weapon_sticks;

@Mixin(ArrowItem.class)
public class ArrowItemMixin {
    @Inject(method = "createArrow", at = @At("HEAD"), cancellable = true)
    public void injectedCreateArrow(Level level, ItemStack ammo, LivingEntity shooter, CallbackInfoReturnable<AbstractArrow> cir) {
        Item arrowVariantItem = ammo.getItem();
        Item stickItem;
        String arrowVariant;
        AbstractArrow abstractArrow = new Arrow(level, shooter, ammo.copyWithCount(1));
        if (more_arrows.contains(arrowVariantItem)) {
            stickItem = more_weapon_sticks.get(arrowVariantItem);
            if (stickItem.equals(Items.BAMBOO)) {
                arrowVariant = "bamboo";
            } else if (stickItem.equals(Items.STICK)) {
                arrowVariant = "oak";
            } else {
                arrowVariant = ((MoreStickVariantItem) stickItem).mstvWoodType;
            }
            ((IArrow) abstractArrow).mweaponv$setVariant(arrowVariant);
            cir.setReturnValue(abstractArrow);
        }
    }

    @Inject(method = "asProjectile", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    public void injectedAsProjectile(Level level, Position pos, ItemStack ammo, Direction direction, CallbackInfoReturnable<Projectile> cir, @Local Arrow arrow) {
        Item arrowVariantItem = ammo.getItem();
        Item stickItem;
        String arrowVariant;
        if (more_arrows.contains(arrowVariantItem)) {
            stickItem = more_weapon_sticks.get(arrowVariantItem);
            if (stickItem.equals(Items.BAMBOO)) {
                arrowVariant = "bamboo";
            } else if (stickItem.equals(Items.STICK)) {
                arrowVariant = "oak";
            } else {
                arrowVariant = ((MoreStickVariantItem) stickItem).mstvWoodType;
            }
            ((IArrow) arrow).mweaponv$setVariant(arrowVariant);
            cir.setReturnValue(arrow);
        }
    }
}
