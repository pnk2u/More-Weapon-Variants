package de.pnku.mstv_mweaponv.mixin.entity.ai.monster.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.SWORD_AND_SPEAR_BY_STICK;
import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.chooseStickForSpawnBiome;

@Mixin(ZombieHorse.class)
public class ZombieHorseMixin {

    @WrapOperation(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    protected void wrappedSetItemSlotFromFinalizeSpawn(Zombie zombie, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        Item stickItem = chooseStickForSpawnBiome(zombie);
        Item spearItem = stickItem != null ? SWORD_AND_SPEAR_BY_STICK.get(stickItem)[1] : null;

        if (spearItem != null) {
            original.call(zombie, slot, new ItemStack(spearItem));
        } else {
            original.call(zombie, slot, stack);
        }
    }
}
