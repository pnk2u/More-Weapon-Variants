package de.pnku.mstv_mweaponv.mixin.client.player;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_bows;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {

    @Redirect(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean redirectedStackIsBow(ItemStack itemStack, Item item){
        return more_bows.contains(itemStack.getItem()) || itemStack.is(item);
    }
}
