package de.pnku.mstv_mweaponv.mixin.entity.ai;

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

    @Redirect(method = "isHoldingBow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Monster;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    public boolean redirectedIsHoldingBow(Monster monster, Item item) {
        {return more_bows.contains(item) || item.equals(Items.BOW);}
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand redirectedTickGetWeaponHoldingHand(LivingEntity shooter, Item weapon) {
        if (more_bows.contains(shooter.getMainHandItem().getItem())){return InteractionHand.MAIN_HAND;}
        else if (more_bows.contains(shooter.getOffhandItem().getItem())){return InteractionHand.OFF_HAND;}
        else return ProjectileUtil.getWeaponHoldingHand(shooter, Items.BOW);
    }
}
