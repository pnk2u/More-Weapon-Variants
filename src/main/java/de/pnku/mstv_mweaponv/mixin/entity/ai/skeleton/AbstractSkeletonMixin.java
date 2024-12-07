package de.pnku.mstv_mweaponv.mixin.entity.ai.skeleton;

import de.pnku.mstv_mweaponv.util.ArrowUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Random;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Monster {

    @Unique
    AbstractSkeleton abstractSkeleton = (AbstractSkeleton)(Object)this;

    protected AbstractSkeletonMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/AbstractSkeleton;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    protected void redirectedPopulateDefaultEquipmentSlots(AbstractSkeleton skeleton, EquipmentSlot slot, ItemStack stack){
        BlockPos skeletonPos = abstractSkeleton.blockPosition();
        String spawnBiomeName = abstractSkeleton.level().getBiome(skeletonPos).getRegisteredName();
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
                    abstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnBowItem));
                }
            } else {
                if (Math.random() < spawnBowVariantProb) {
                    abstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnBowItem));
                } else if (Math.random() < (spawnBowVariantProb + spawnBowVariantAltProb)) {
                    abstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(spawnBowItemAlt));
                }
            }
        } else {abstractSkeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));}
    }

    @Redirect(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand redirectedReassessWeaponGoalGetWeaponHoldingHand(LivingEntity shooter, Item weapon) {
        if (more_bows.contains(shooter.getMainHandItem().getItem())){return InteractionHand.MAIN_HAND;}
        else if (more_bows.contains(shooter.getOffhandItem().getItem())){return InteractionHand.OFF_HAND;}
        else return ProjectileUtil.getWeaponHoldingHand(shooter, Items.BOW);
    }

    @Redirect(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    public boolean redirectedReassessWeaponGoalStackIs(ItemStack stack, Item item) {
        {return more_bows.contains(item) || item.equals(Items.BOW);}
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
        Item mainHandItem = abstractSkeleton.getMainHandItem().getItem();
        Item offhandItem = abstractSkeleton.getOffhandItem().getItem();
        int looting = Objects.requireNonNull(damageSource.getWeaponItem()).getEnchantments().getLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOOTING));
        Random rand = new Random();
        if (!mainHandItem.equals(Items.BOW) && !offhandItem.equals(Items.BOW)) {
            if (mainHandItem instanceof BowItem) {
                this.spawnAtLocation(new ItemStack(ArrowUtil.arrowFromProjectileWeapon(mainHandItem, false), rand.nextInt(3) * (1 + looting)));
            } else if (offhandItem instanceof BowItem) {
                this.spawnAtLocation(new ItemStack(ArrowUtil.arrowFromProjectileWeapon(offhandItem, false), rand.nextInt(3) * (1 + looting)));
            }
        } else {
            this.spawnAtLocation(new ItemStack(Items.ARROW, rand.nextInt(3) * (1 + looting)));
        }
    }



    // TBD: protected AbstractArrow getArrow
}
