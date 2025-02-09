package de.pnku.mstv_mweaponv.mixin.entity.ai.piglin;

import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_crossbows;

@Mixin(Piglin.class)
public class PiglinMixin {
    @Inject(method = "createSpawnWeapon", at = @At(value = "HEAD"), cancellable = true)
    private void injectedCreateSpawnWeapon(CallbackInfoReturnable<ItemStack> cir){
        double rand = Math.random();
        if (rand < 0.45){ cir.setReturnValue(new ItemStack(MoreWeaponVariantItems.CRIMSON_CROSSBOW)); }
        else if (rand < 0.85){cir.setReturnValue(new ItemStack(MoreWeaponVariantItems.CRIMSON_GOLDEN_SWORD));}
    }

    @Redirect(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/piglin/Piglin;isHolding(Lnet/minecraft/world/item/Item;)Z"))
    private boolean redirectedGetArmPoseIsHolding(Piglin piglin, Item item) {
        if ((item.equals(MoreWeaponVariantItems.WARPED_CROSSBOW))) {return false;} else {return more_crossbows.contains(item) || item.equals(Items.CROSSBOW);}
    }

    @Inject(method = "canFireProjectileWeapon", at = @At("HEAD"), cancellable = true)
    public void injectedCanFireProjectileWeapon(ProjectileWeaponItem projectileWeapon, CallbackInfoReturnable<Boolean> cir){
        if (more_crossbows.contains(projectileWeapon)){cir.setReturnValue(true);}
    }
}
