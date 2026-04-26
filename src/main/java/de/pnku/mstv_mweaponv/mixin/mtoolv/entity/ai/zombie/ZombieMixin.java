package de.pnku.mstv_mweaponv.mixin.mtoolv.entity.ai.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;
import static de.pnku.mstv_mtoolv.item.MoreToolVariantItems.*;
import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.chooseStickForSpawnBiome;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {

    @Unique
    private static final Map<Item, Item> SHOVEL_BY_STICK = Map.ofEntries(
            Map.entry(ACACIA_STICK,   ACACIA_IRON_SHOVEL),
            Map.entry(BIRCH_STICK,    BIRCH_IRON_SHOVEL),
            Map.entry(Items.BAMBOO,   BAMBOO_IRON_SHOVEL),
            Map.entry(JUNGLE_STICK,   JUNGLE_IRON_SHOVEL),
            Map.entry(CHERRY_STICK,   CHERRY_IRON_SHOVEL),
            Map.entry(CRIMSON_STICK,  CRIMSON_IRON_SHOVEL),
            Map.entry(DARK_OAK_STICK, DARK_OAK_IRON_SHOVEL),
            Map.entry(PALE_OAK_STICK, PALE_OAK_IRON_SHOVEL),
            Map.entry(MANGROVE_STICK, MANGROVE_IRON_SHOVEL),
            Map.entry(SPRUCE_STICK,   SPRUCE_IRON_SHOVEL),
            Map.entry(WARPED_STICK,   WARPED_IRON_SHOVEL)
    );

    protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 2))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots(
            Zombie zombie,
            EquipmentSlot slot,
            ItemStack stack,
            Operation<Void> original) {
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SHOVEL)) {
            Item stickItem = chooseStickForSpawnBiome(this);
            Item shovelItem = stickItem != null ? SHOVEL_BY_STICK.get(stickItem) : null;

            if (shovelItem != null) {
                this.setItemSlot(slot, new ItemStack(shovelItem));
            } else {
                original.call(zombie, slot, stack);
            }
        } else {
            original.call(zombie, slot, stack);
        }
    }
}
