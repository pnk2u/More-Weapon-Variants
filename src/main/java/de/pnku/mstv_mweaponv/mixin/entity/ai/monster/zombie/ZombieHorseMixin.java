package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
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

@Mixin(ZombieHorse.class)
public class ZombieHorseMixin {

    @Unique
    private static final Map<Item, Item> SPEAR_BY_STICK = Map.ofEntries(
            Map.entry(ACACIA_STICK,   ACACIA_IRON_SPEAR),
            Map.entry(BIRCH_STICK,    BIRCH_IRON_SPEAR),
            Map.entry(Items.BAMBOO,   BAMBOO_IRON_SPEAR),
            Map.entry(JUNGLE_STICK,   JUNGLE_IRON_SPEAR),
            Map.entry(CHERRY_STICK,   CHERRY_IRON_SPEAR),
            Map.entry(CRIMSON_STICK,  CRIMSON_IRON_SPEAR),
            Map.entry(DARK_OAK_STICK, DARK_OAK_IRON_SPEAR),
            Map.entry(PALE_OAK_STICK, PALE_OAK_IRON_SPEAR),
            Map.entry(MANGROVE_STICK, MANGROVE_IRON_SPEAR),
            Map.entry(SPRUCE_STICK,   SPRUCE_IRON_SPEAR),
            Map.entry(WARPED_STICK,   WARPED_IRON_SPEAR)
    );

    @WrapOperation(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    protected void wrappedSetItemSlotFromFinalizeSpawn(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        Item stickItem = chooseStickForSpawnBiome(zombie);
        Item spearItem = stickItem != null ? SPEAR_BY_STICK.get(stickItem) : null;

        if (spearItem != null) {
            original.call(zombie, slot, new ItemStack(spearItem));
        } else {
            original.call(zombie, slot, stack);
        }
    }
}
