package de.pnku.mstv_mweaponv.mixin.entity.ai;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_bows;

@Mixin(RangedBowAttackGoal.class)
public class RangedBowAttackGoalMixin {

    @WrapOperation(method = "isHoldingBow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Monster;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    public boolean wrappedIsHoldingItemFromIsHoldingBow(Monster instance, Item item, Operation<Boolean> original) {
        {return more_bows.contains(item) || item.equals(Items.BOW);}
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand wrappedGetWeaponHoldingHandFromTick(LivingEntity shooter, Item weapon, Operation<InteractionHand> original) {
        if (more_bows.contains(shooter.getMainHandItem().getItem())){return InteractionHand.MAIN_HAND;}
        else if (more_bows.contains(shooter.getOffhandItem().getItem())){return InteractionHand.OFF_HAND;}
        else return original.call(shooter, weapon);
    }
}
