package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.skeleton;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import de.pnku.mstv_mweaponv.util.ArrowUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Random;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;
import static de.pnku.mstv_base.item.MoreStickVariantItems.CHERRY_STICK;
import static de.pnku.mstv_base.item.MoreStickVariantItems.CRIMSON_STICK;
import static de.pnku.mstv_base.item.MoreStickVariantItems.DARK_OAK_STICK;
import static de.pnku.mstv_base.item.MoreStickVariantItems.MANGROVE_STICK;
import static de.pnku.mstv_base.item.MoreStickVariantItems.SPRUCE_STICK;
import static de.pnku.mstv_base.item.MoreStickVariantItems.WARPED_STICK;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;
import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.chooseStickForSpawnBiome;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Monster {

    protected AbstractSkeletonMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static final Map<Item, Item> BOW_BY_STICK = Map.ofEntries(
            Map.entry(ACACIA_STICK,   ACACIA_BOW),
            Map.entry(BIRCH_STICK,    BIRCH_BOW),
            Map.entry(Items.BAMBOO,   BAMBOO_BOW),
            Map.entry(JUNGLE_STICK,   JUNGLE_BOW),
            Map.entry(CHERRY_STICK,   CHERRY_BOW),
            Map.entry(CRIMSON_STICK,  CRIMSON_BOW),
            Map.entry(DARK_OAK_STICK, DARK_OAK_BOW),
            Map.entry(PALE_OAK_STICK, PALE_OAK_BOW),
            Map.entry(MANGROVE_STICK, MANGROVE_BOW),
            Map.entry(SPRUCE_STICK,   SPRUCE_BOW),
            Map.entry(WARPED_STICK,   WARPED_BOW)
    );

    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/skeleton/AbstractSkeleton;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 0))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots(AbstractSkeleton abstractSkeleton, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (!stack.isEmpty() && stack.getItem().equals(Items.BOW)) {
            Item stickItem = chooseStickForSpawnBiome(this);
            Item bowItem = stickItem != null ? BOW_BY_STICK.get(stickItem) : null;

            if (bowItem != null) {
                this.setItemSlot(slot, new ItemStack(bowItem));
            } else {
                original.call(abstractSkeleton, slot, stack);
            }
        } else {
            original.call(abstractSkeleton, slot, stack);
        }
    }

    @WrapOperation(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand wrappedGetWeaponHoldingHandFromReassessWeaponGoal(LivingEntity shooter, Item weapon, Operation<InteractionHand> original) {
        if (!shooter.getType().getBaseClass().equals(WitherSkeleton.class)) {
            if (more_bows.contains(shooter.getMainHandItem().getItem())) {
                return InteractionHand.MAIN_HAND;
            } else if (more_bows.contains(shooter.getOffhandItem().getItem())) {
                return InteractionHand.OFF_HAND;
            } else return original.call(shooter, weapon);
        } else return original.call(shooter, weapon);
    }

    @Inject(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/skeleton/AbstractSkeleton;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;", shift = At.Shift.BY, by = 2), cancellable = true)
    public void injectedReassessWeaponGoal(CallbackInfo ci, @Local LocalRef<ItemStack> localItemStack) {
        Item weaponItem = localItemStack.get().getItem();
        if (more_bows.contains(weaponItem) || weaponItem.equals(Items.BOW))
        {localItemStack.set(new ItemStack(Items.BOW));}
    }


    @WrapOperation(method = "performRangedAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
    public InteractionHand wrappedGetWeaponHoldingHandFromPerformRangedAttack(LivingEntity shooter, Item weapon, Operation<InteractionHand> original) {
        if (more_bows.contains(shooter.getMainHandItem().getItem())){return InteractionHand.MAIN_HAND;}
        else if (more_bows.contains(shooter.getOffhandItem().getItem())){return InteractionHand.OFF_HAND;}
        else return original.call(shooter, weapon);
    }

    @Inject(method = "canUseNonMeleeWeapon", at = @At("HEAD"), cancellable = true)
    public void injectedCanUseNonMeleeWeapon(ItemStack projectileWeaponStack, CallbackInfoReturnable<Boolean> cir){
        if (more_bows.contains(projectileWeaponStack.getItem())){cir.setReturnValue(true);}
    }

    @Unique
    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean hitByPlayer) {
        super.dropCustomDeathLoot(level, damageSource, hitByPlayer);
        Item mainHandItem = this.getMainHandItem().getItem();
        Item offhandItem = this.getOffhandItem().getItem();
        boolean isBogged = this.getType().getBaseClass().equals(Bogged.class);
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
                this.spawnAtLocation(level, arrowStack, rand.nextInt(3) * (1 + looting));
            }
        } else {
            this.spawnAtLocation(level, new ItemStack(Items.ARROW, rand.nextInt(3) * (1 + looting)));
        }
    }
}
