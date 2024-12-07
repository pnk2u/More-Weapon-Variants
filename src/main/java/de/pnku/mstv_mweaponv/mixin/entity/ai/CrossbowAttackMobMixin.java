package de.pnku.mstv_mweaponv.mixin.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_crossbows;

@Mixin(CrossbowAttackMob.class)
public interface CrossbowAttackMobMixin {

    @Redirect(method = "performCrossbowAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    default InteractionHand redirectedPerformCrossbowAttackGetWeaponHoldingHand(LivingEntity shooter, Item weapon){
        if (more_crossbows.contains(shooter.getMainHandItem().getItem())){return InteractionHand.MAIN_HAND;}
        else if (more_crossbows.contains(shooter.getOffhandItem().getItem())){return InteractionHand.OFF_HAND;}
        else return ProjectileUtil.getWeaponHoldingHand(shooter, Items.CROSSBOW);
    }
}
