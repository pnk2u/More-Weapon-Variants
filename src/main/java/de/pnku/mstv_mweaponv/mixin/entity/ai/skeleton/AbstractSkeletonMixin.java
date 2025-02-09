package de.pnku.mstv_mweaponv.mixin.entity.ai.skeleton;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import de.pnku.mstv_mweaponv.util.ArrowUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Monster {

    @Unique
    AbstractSkeleton thisAbstractSkeleton = (AbstractSkeleton)(Object)this;

    protected AbstractSkeletonMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/AbstractSkeleton;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    protected void redirectedPopulateDefaultEquipmentSlots(AbstractSkeleton skeleton, EquipmentSlot slot, ItemStack stack){
        BlockPos skeletonPos = thisAbstractSkeleton.blockPosition();
        String spawnBiomeName = thisAbstractSkeleton.level().getBiome(skeletonPos).getRegisteredName();
        Item spawnBowItem;
        Item spawnBowItemAlt;
        double spawnBowVariantProb;
        double spawnBowVariantAltProb;
        switch (spawnBiomeName) {
            case ("minecraft:savanna"), ("minecraft:savanna_plateau"), ("minecraft:windswept_savanna") -> {spawnBowItem = ACACIA_BOW; spawnBowVariantProb = 0.95; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:birch_forest"), ("minecraft:old_growth_birch_forest") -> {spawnBowItem = BIRCH_BOW; spawnBowVariantProb = 0.9; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:forest"), ("minecraft:meadow") -> {spawnBowItem = BIRCH_BOW; spawnBowVariantProb = 0.2; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:bamboo_jungle") -> {spawnBowItem = BAMBOO_BOW; spawnBowVariantProb = 0.6; spawnBowItemAlt = JUNGLE_BOW; spawnBowVariantAltProb = 0.3;}
            case ("minecraft:jungle") -> {spawnBowItem = JUNGLE_BOW; spawnBowVariantProb = 0.8; spawnBowItemAlt = BAMBOO_BOW; spawnBowVariantAltProb = 0.1;}
            case ("minecraft:sparse_jungle") -> {spawnBowItem = JUNGLE_BOW; spawnBowVariantProb = 0.8; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:cherry_grove") -> {spawnBowItem = CHERRY_BOW; spawnBowVariantProb = 0.925; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:crimson_forest") -> {spawnBowItem = CRIMSON_BOW; spawnBowVariantProb = 1; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:nether_wastes") -> {spawnBowItem = CRIMSON_BOW; spawnBowVariantProb = 0.25; spawnBowItemAlt = WARPED_BOW; spawnBowVariantAltProb = 0.05;}
            case ("minecraft:dark_forest") -> {spawnBowItem = DARK_OAK_BOW; spawnBowVariantProb = 0.8; spawnBowItemAlt = BIRCH_BOW; spawnBowVariantAltProb = 0.1;}
            case ("minecraft:mangrove_swamp") -> {spawnBowItem = MANGROVE_BOW; spawnBowVariantProb = 0.975; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:old_growth_spruce_taiga"), ("minecraft:old_growth_pine_taiga"), ("minecraft:taiga"), ("minecraft:snowy_taiga") -> {spawnBowItem = SPRUCE_BOW; spawnBowVariantProb = 0.85; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:windswept_forest"), ("minecraft:snowy_plains") -> {spawnBowItem = SPRUCE_BOW; spawnBowVariantProb = 0.3; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:warped_forest") -> {spawnBowItem = WARPED_BOW; spawnBowVariantProb = 1; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            case ("minecraft:soul_sand_valley") -> {spawnBowItem = WARPED_BOW; spawnBowVariantProb = 0.125; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
            default -> {spawnBowItem = null; spawnBowVariantProb = 0; spawnBowItemAlt = null; spawnBowVariantAltProb = 0;}
        }
        if (spawnBowItem != null) {
            if (spawnBowItemAlt == null) {
                if (Math.random() < spawnBowVariantProb) {
                    thisAbstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnBowItem));
                } else {
                    thisAbstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));}
            } else {
                if (Math.random() < spawnBowVariantProb) {
                    thisAbstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnBowItem));
                } else if (Math.random() < (spawnBowVariantProb + spawnBowVariantAltProb)) {
                    thisAbstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnBowItemAlt));
                } else {
                    thisAbstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));}
            }
        } else {
            thisAbstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));}
    }

    @Redirect(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand redirectedReassessWeaponGoalGetWeaponHoldingHand(LivingEntity shooter, Item weapon) {
        if (!shooter.getType().equals(EntityType.WITHER_SKELETON)) {
            if (more_bows.contains(shooter.getMainHandItem().getItem())) {
                return InteractionHand.MAIN_HAND;
            } else if (more_bows.contains(shooter.getOffhandItem().getItem())) {
                return InteractionHand.OFF_HAND;
            } else return ProjectileUtil.getWeaponHoldingHand(shooter, weapon);
        } else return !shooter.getMainHandItem().isEmpty() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
    }

    @Inject(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/AbstractSkeleton;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;", shift = At.Shift.BY, by = 2), cancellable = true)
    public void injectedReassessWeaponGoal(CallbackInfo ci, @Local LocalRef<ItemStack> localItemStack) {
        Item weaponItem = localItemStack.get().getItem();
        if (more_bows.contains(weaponItem) || weaponItem.equals(Items.BOW))
        {localItemStack.set(new ItemStack(Items.BOW));}
    }


    @Redirect(method = "performRangedAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand redirectedperformRangedAttackGetWeaponHoldingHand(LivingEntity shooter, Item weapon) {
        if (more_bows.contains(shooter.getMainHandItem().getItem())){return InteractionHand.MAIN_HAND;}
        else if (more_bows.contains(shooter.getOffhandItem().getItem())){return InteractionHand.OFF_HAND;}
        else return ProjectileUtil.getWeaponHoldingHand(shooter, Items.BOW);
    }

    @Inject(method = "canFireProjectileWeapon", at = @At("HEAD"), cancellable = true)
    public void injectedCanFireProjectileWeapon(ProjectileWeaponItem projectileWeapon, CallbackInfoReturnable<Boolean> cir){
        if (more_bows.contains(projectileWeapon)){cir.setReturnValue(true);}
    }

    @Unique
    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean hitByPlayer) {
        super.dropCustomDeathLoot(level, damageSource, hitByPlayer);
        Item mainHandItem = thisAbstractSkeleton.getMainHandItem().getItem();
        Item offhandItem = thisAbstractSkeleton.getOffhandItem().getItem();
        boolean isBogged = thisAbstractSkeleton.getType() == EntityType.BOGGED;
        ItemStack arrowStack;
        int looting;
        if (damageSource.getWeaponItem() != null) {
            looting = damageSource.getWeaponItem().getEnchantments().getLevel(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING));}
        else {looting = 0;}
        Random rand = new Random();
        if (!mainHandItem.equals(Items.BOW) && !offhandItem.equals(Items.BOW)) {
            if (mainHandItem instanceof BowItem || offhandItem instanceof BowItem) {
                arrowStack = new ItemStack(ArrowUtil.arrowFromProjectileWeapon(mainHandItem instanceof BowItem ? mainHandItem : offhandItem instanceof BowItem ? offhandItem : Items.BOW, isBogged));
                if (isBogged){arrowStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.POISON));}
                this.spawnAtLocation(arrowStack, rand.nextInt(3) * (1 + looting));
            }
        } else {
            this.spawnAtLocation(new ItemStack(Items.ARROW, rand.nextInt(3) * (1 + looting)));
        }
    }



    // TBD: protected AbstractArrow getArrow
}
