package de.pnku.mstv_mweaponv.mixin.client.player;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_bows;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {

    @WrapOperation(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean redirectedStackIsBow(ItemStack itemStack, Object o, Operation<Boolean> original){
        return o instanceof Item item ? more_bows.contains(itemStack.getItem()) || itemStack.is(item) : original.call(itemStack, o);
    }
}
