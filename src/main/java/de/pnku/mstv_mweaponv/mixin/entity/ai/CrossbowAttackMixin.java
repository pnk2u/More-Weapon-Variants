package de.pnku.mstv_mweaponv.mixin.entity.ai;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.CrossbowAttack;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_crossbows;

@Mixin(CrossbowAttack.class)
public abstract class CrossbowAttackMixin<E extends Mob & CrossbowAttackMob, T extends LivingEntity> extends Behavior<E> {

    public CrossbowAttackMixin(Map<MemoryModuleType<?>, MemoryStatus> entryCondition) {
        super(entryCondition);
    }

    @Shadow public CrossbowAttack.CrossbowState crossbowState;

    @WrapOperation(method = "checkExtraStartConditions(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Mob;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    protected boolean wrappedIsHoldingItemFromCheckExtraStartConditions(Mob mob, Item item, Operation<Boolean> original) {
        if ((mob.getType().getBaseClass().equals(Piglin.class) && item.equals(MoreWeaponVariantItems.WARPED_CROSSBOW))) {return false;} else {return more_crossbows.contains(item) || item.equals(Items.CROSSBOW);}
    }

    @WrapOperation(method = "stop(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Mob;J)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    protected boolean wrappedIsHoldingItemFromStop(Mob mob, Item item, Operation<Boolean> original){
        {return more_crossbows.contains(item) || item.equals(Items.CROSSBOW);}
    }

    @Inject(method = "crossbowAttack", at = @At("HEAD"))
    private void injectedCrossbowAttack(E shooter, LivingEntity target, CallbackInfo ci) {
        if (this.crossbowState == CrossbowAttack.CrossbowState.UNCHARGED && (more_crossbows.contains(shooter.getMainHandItem().getItem()) || more_crossbows.contains(shooter.getOffhandItem().getItem()))) {
            Item crossbowItem;
            if (more_crossbows.contains(shooter.getMainHandItem().getItem())) {crossbowItem = shooter.getMainHandItem().getItem();} else {crossbowItem = shooter.getOffhandItem().getItem();}
            shooter.startUsingItem(ProjectileUtil.getWeaponHoldingHand(shooter, crossbowItem));
            this.crossbowState = CrossbowAttack.CrossbowState.CHARGING;
            shooter.setChargingCrossbow(true);
        }
    }
}
