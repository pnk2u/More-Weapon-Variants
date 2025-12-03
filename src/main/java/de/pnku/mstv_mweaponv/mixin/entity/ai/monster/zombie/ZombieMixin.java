package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Map;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;
import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.chooseStickForSpawnBiome;

@Mixin(Zombie.class)
public class ZombieMixin {

    @Unique
    Zombie thisZombie = (Zombie)(Object)this;

    @Unique
    private static final Map<Item, Item[]> SWORD_AND_SPEAR_BY_STICK = Map.ofEntries(
            Map.entry(ACACIA_STICK,   new Item[]{ACACIA_IRON_SWORD, ACACIA_IRON_SPEAR}),
            Map.entry(BIRCH_STICK,    new Item[]{BIRCH_IRON_SWORD, BIRCH_IRON_SPEAR}),
            Map.entry(Items.BAMBOO,   new Item[]{BAMBOO_IRON_SWORD, BAMBOO_IRON_SPEAR}),
            Map.entry(JUNGLE_STICK,   new Item[]{JUNGLE_IRON_SWORD, JUNGLE_IRON_SPEAR}),
            Map.entry(CHERRY_STICK,   new Item[]{CHERRY_IRON_SWORD, CHERRY_IRON_SPEAR}),
            Map.entry(CRIMSON_STICK,  new Item[]{CRIMSON_IRON_SWORD, CRIMSON_IRON_SPEAR}),
            Map.entry(DARK_OAK_STICK, new Item[]{DARK_OAK_IRON_SWORD, DARK_OAK_IRON_SPEAR}),
            Map.entry(PALE_OAK_STICK, new Item[]{PALE_OAK_IRON_SWORD, PALE_OAK_IRON_SPEAR}),
            Map.entry(MANGROVE_STICK, new Item[]{MANGROVE_IRON_SWORD, MANGROVE_IRON_SPEAR}),
            Map.entry(SPRUCE_STICK,   new Item[]{SPRUCE_IRON_SWORD, SPRUCE_IRON_SPEAR}),
            Map.entry(WARPED_STICK,   new Item[]{WARPED_IRON_SWORD, WARPED_IRON_SPEAR})
    );

    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 0))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots_ord0(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SWORD)) {
            Item stickItem = chooseStickForSpawnBiome(thisZombie);
            Item swordItem = stickItem != null ? SWORD_AND_SPEAR_BY_STICK.get(stickItem)[0] : null;

            if (swordItem != null) {
                thisZombie.setItemSlot(slot, new ItemStack(swordItem));
            } else {
                original.call(zombie, slot, stack);
            }
        } else {
            original.call(zombie, slot, stack);
        }
    }
    @WrapOperation(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", ordinal = 1))
    protected void wrappedSetItemSlotFromPopulateDefaultEquipmentSlots_ord1(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (!stack.isEmpty() && stack.getItem().equals(Items.IRON_SPEAR)) {
            Item stickItem = chooseStickForSpawnBiome(thisZombie);
            Item spearItem = stickItem != null ? SWORD_AND_SPEAR_BY_STICK.get(stickItem)[1] : null;

            if (spearItem != null) {
                thisZombie.setItemSlot(slot, new ItemStack(spearItem));
            } else {
                original.call(zombie, slot, stack);
            }
        } else {
            original.call(zombie, slot, stack);
        }
    }
}
