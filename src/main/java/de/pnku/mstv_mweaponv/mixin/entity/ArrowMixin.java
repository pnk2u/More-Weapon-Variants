package de.pnku.mstv_mweaponv.mixin.entity;

import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(Arrow.class)
public abstract class ArrowMixin extends AbstractArrow implements IArrow {

    @Unique
    private static final EntityDataAccessor<String> DATA_ID_TYPE;

    protected ArrowMixin(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void injectedDefineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_ID_TYPE, "oak");
    }

    @Unique
    public String mweaponv$getVariant() {
        return this.entityData.get(DATA_ID_TYPE);
    }

    @Unique
    public void mweaponv$setVariant(String variant) {
        this.entityData.set(DATA_ID_TYPE, variant);
    }

    @Unique
    public ItemStack arrowItemStackFromVariant(String arrowVariant) {
        switch (arrowVariant) {
            case "oak" -> {return new ItemStack(Items.ARROW);}
            case "acacia" -> {return new ItemStack(ACACIA_ARROW);}
            case "bamboo" -> {return new ItemStack(BAMBOO_ARROW);}
            case "birch" -> {return new ItemStack(BIRCH_ARROW);}
            case "cherry" -> {return new ItemStack(CHERRY_ARROW);}
            case "crimson" -> {return new ItemStack(CRIMSON_ARROW);}
            case "dark_oak" -> {return new ItemStack(DARK_OAK_ARROW);}
            case "jungle" -> {return new ItemStack(JUNGLE_ARROW);}
            case "mangrove" -> {return new ItemStack(MANGROVE_ARROW);}
            case "spruce" -> {return new ItemStack(SPRUCE_ARROW);}
            case "warped" -> {return new ItemStack(WARPED_ARROW);}
            case null, default -> {return new ItemStack(Items.ARROW);}
        }
    }

    @Inject(method = "getDefaultPickupItem", at = @At("HEAD"), cancellable = true)
    protected void injectedGetDefaultPickupItem(CallbackInfoReturnable<ItemStack> cir) {
        String arrowVariant = this.mweaponv$getVariant();
        if (!arrowVariant.equals("oak")) {
            cir.setReturnValue(arrowItemStackFromVariant(this.mweaponv$getVariant()));
        }
    }

    @Inject(method = "setPickupItemStack", at = @At("HEAD"), cancellable = true)
    protected void injectedSetPickupItemStack(ItemStack pickupItemStack, CallbackInfo ci) {
        String arrowVariant = this.mweaponv$getVariant();
        if (!arrowVariant.equals("oak")) {
            ItemStack pickupVariantItemStack = arrowItemStackFromVariant(this.mweaponv$getVariant());
            super.setPickupItemStack(pickupVariantItemStack);
        }

    }

    static {
        DATA_ID_TYPE = SynchedEntityData.defineId(ArrowMixin.class, EntityDataSerializers.STRING);
    }
}
