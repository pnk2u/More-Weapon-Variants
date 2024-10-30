package de.pnku.mstv_mweaponv.mixin.client.color;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(ItemColors.class)
public class ItemColorsMixin {

    @Inject(method = "createDefault", at = @At(value = "RETURN", shift = At.Shift.BEFORE))
    private static void createDefault(BlockColors colors, CallbackInfoReturnable<ItemColors> cir, @Local ItemColors itemColors) {
        itemColors.register((itemStack, i) -> {return i == 0 ? PotionUtils.getColor(itemStack) : -1;},
                more_tippable_arrows.values().toArray(Item[]::new)
        );
    }

}
