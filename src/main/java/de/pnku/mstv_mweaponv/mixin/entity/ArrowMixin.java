package de.pnku.mstv_mweaponv.mixin.entity;

import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

@Mixin(Arrow.class)
public abstract class ArrowMixin extends AbstractArrow implements IArrow {

    @Shadow @Final private static ItemStack DEFAULT_ARROW_STACK;

    @Shadow public Potion potion;

    @Unique
    private static final EntityDataAccessor<String> DATA_ID_TYPE;

    protected ArrowMixin(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level, DEFAULT_ARROW_STACK);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void injectedDefineSynchedData(CallbackInfo ci) {
        this.entityData.define(DATA_ID_TYPE, "oak");
    }

    @Unique
    public String mweaponv$getVariant() {
        return this.entityData.get(DATA_ID_TYPE);
    }

    @Unique
    public void mweaponv$setVariant(String variant) {
        this.entityData.set(DATA_ID_TYPE, variant);
    }

    @Redirect(method = "setEffectsFromItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    public boolean redirectedStackIsTipped(ItemStack arrowStack, Item item){
        if (more_tippable_arrows.containsValue(arrowStack.getItem())) {
            return true;
        } else {
            return arrowStack.is(Items.TIPPED_ARROW);
        }
    }

    @Redirect(method = "setEffectsFromItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    public boolean redirectedStackIsTippable(ItemStack arrowStack, Item item){
        if (more_tippable_arrows.containsKey(arrowStack.getItem())) {
            return true;
        } else {
            return arrowStack.is(Items.ARROW);
        }
    }

    // Added mweaponv$ prefix for interMixin usage not needed in newer versions
    @Unique
    public ItemStack mweaponv$arrowItemStackFromVariant(String arrowVariant, boolean isTipped) {
        switch (arrowVariant) {
            case "oak" -> {return isTipped ? new ItemStack(Items.TIPPED_ARROW) : new ItemStack(Items.ARROW);}
            case "acacia" -> {return isTipped ? new ItemStack(ACACIA_TIPPED_ARROW) : new ItemStack(ACACIA_ARROW);}
            case "bamboo" -> {return isTipped ? new ItemStack(BAMBOO_TIPPED_ARROW) : new ItemStack(BAMBOO_ARROW);}
            case "birch" -> {return isTipped ? new ItemStack(BIRCH_TIPPED_ARROW) : new ItemStack(BIRCH_ARROW);}
            case "cherry" -> {return isTipped ? new ItemStack(CHERRY_TIPPED_ARROW) : new ItemStack(CHERRY_ARROW);}
            case "crimson" -> {return isTipped ? new ItemStack(CRIMSON_TIPPED_ARROW) : new ItemStack(CRIMSON_ARROW);}
            case "dark_oak" -> {return isTipped ? new ItemStack(DARK_OAK_TIPPED_ARROW) : new ItemStack(DARK_OAK_ARROW);}
            case "jungle" -> {return isTipped ? new ItemStack(JUNGLE_TIPPED_ARROW) : new ItemStack(JUNGLE_ARROW);}
            case "mangrove" -> {return isTipped ? new ItemStack(MANGROVE_TIPPED_ARROW) : new ItemStack(MANGROVE_ARROW);}
            case "spruce" -> {return isTipped ? new ItemStack(SPRUCE_TIPPED_ARROW) : new ItemStack(SPRUCE_ARROW);}
            case "warped" -> {return isTipped ? new ItemStack(WARPED_TIPPED_ARROW) : new ItemStack(WARPED_ARROW);}
            default -> {return isTipped ? new ItemStack(Items.TIPPED_ARROW) : new ItemStack(Items.ARROW);}
        }
    }

    @Redirect(method = "getPickupItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;getPickupItem()Lnet/minecraft/world/item/ItemStack;"))
    protected ItemStack redirectedGetPickupItem(AbstractArrow instance) {
        String arrowVariant = this.mweaponv$getVariant();
        if (!arrowVariant.equals("oak")) {
            boolean isTipped = (this.potion != Potions.EMPTY);
            return mweaponv$arrowItemStackFromVariant(this.mweaponv$getVariant(), isTipped);
        } else {
            return super.getPickupItem();
        }
    }

    static {
        DATA_ID_TYPE = SynchedEntityData.defineId(ArrowMixin.class, EntityDataSerializers.STRING);
    }
}
