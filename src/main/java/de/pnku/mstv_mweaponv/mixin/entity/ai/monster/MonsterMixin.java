package de.pnku.mstv_mweaponv.mixin.entity.ai.monster;

import de.pnku.mstv_mweaponv.util.ArrowUtil;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Monster.class)
public abstract class MonsterMixin {

    @Inject(method = "getProjectile", at = @At(value = "RETURN"), cancellable = true)
    public void injectedGetProjectile(ItemStack weaponStack, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack returnStack = cir.getReturnValue();
        boolean isTipped = returnStack.is(Items.TIPPED_ARROW);
        if (returnStack.is(Items.ARROW) || returnStack.is(Items.TIPPED_ARROW)) {
            cir.setReturnValue(new ItemStack(ArrowUtil.arrowFromProjectileWeapon(weaponStack.getItem(), isTipped), returnStack.getCount()));
        }
    }
}
