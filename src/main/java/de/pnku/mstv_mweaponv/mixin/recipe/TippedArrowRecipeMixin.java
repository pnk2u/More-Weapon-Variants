package de.pnku.mstv_mweaponv.mixin.recipe;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.TippedArrowRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_tippable_arrows;

@Mixin(TippedArrowRecipe.class)
public abstract class TippedArrowRecipeMixin extends CustomRecipe {
    public TippedArrowRecipeMixin(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    // by Linguardium
    @WrapOperation(
            method="matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z",
            slice = @Slice(
                    from=@At(
                            value="FIELD",
                            target="Lnet/minecraft/world/item/Items;ARROW:Lnet/minecraft/world/item/Item;"
                    )
            ),
            at= @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z",
                    ordinal = 0
            )
    )
    private boolean wrappedMatchesStackIsArrow(ItemStack instance, Item ARROW, Operation<Boolean> original) {
        return original.call(instance,ARROW) || more_tippable_arrows.containsKey(instance.getItem());
    }

    @Inject(
            method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;I)V"
            ),
            cancellable = true)
    private void injectedAssemble(CraftingContainer input, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> cir){
        Item tippedArrowVariantItem = more_tippable_arrows.get(input.getItem(0).getItem());
        ItemStack tippedArrowStack = new ItemStack(tippedArrowVariantItem, 8);
        ItemStack lingeringPotionStack = input.getItem(4);
        PotionUtils.setPotion(tippedArrowStack, PotionUtils.getPotion(lingeringPotionStack));
        PotionUtils.setCustomEffects(tippedArrowStack, PotionUtils.getCustomEffects(lingeringPotionStack));
        cir.setReturnValue(tippedArrowStack);
    }
}
