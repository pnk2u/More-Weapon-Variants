package de.pnku.mstv_mweaponv.mixin.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.TippedArrowRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_tippable_arrows;

@Mixin(TippedArrowRecipe.class)
public abstract class TippedArrowRecipeMixin extends CustomRecipe {
    public TippedArrowRecipeMixin(CraftingBookCategory category) {
        super(category);
    }

    @Inject(method = "matches*", at = @At("HEAD"), cancellable = true)
    private void injectedMatches(CraftingInput craftingInput, Level level, CallbackInfoReturnable<Boolean> cir) {
        if (more_tippable_arrows.containsKey(craftingInput.getItem(0).getItem())) {
            if (craftingInput.width() == 3 && craftingInput.height() == 3) {
                for (int i = 0; i < craftingInput.width(); ++i) {
                    for (int j = 0; j < craftingInput.height(); ++j) {
                        ItemStack itemStack = craftingInput.getItem(i + j * craftingInput.width());
                        if (itemStack.isEmpty()) {
                            cir.setReturnValue(false);
                        }

                        if (i == 1 && j == 1) {
                            if (!itemStack.is(Items.LINGERING_POTION)) {
                                cir.setReturnValue(false);
                            }
                        } else if (!more_tippable_arrows.containsKey(itemStack.getItem())) {
                            cir.setReturnValue(false);
                        }
                    }
                }

                cir.setReturnValue(true);
            } else {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "assemble*", at = @At("HEAD"), cancellable = true)
    private void injectAssemble(CraftingInput craftingInput, HolderLookup.Provider provider, CallbackInfoReturnable<ItemStack> cir) {
        Item tippableArrowItem = craftingInput.getItem(0).getItem();
        if (more_tippable_arrows.containsKey(tippableArrowItem)) {
            ItemStack lingeringPotionStack = craftingInput.getItem(1 + craftingInput.width());
            if (!lingeringPotionStack.is(Items.LINGERING_POTION)) {
                cir.setReturnValue(ItemStack.EMPTY);
            } else {
                Item tippedArrowItem = more_tippable_arrows.get(tippableArrowItem);
                ItemStack tippedArrowStack = new ItemStack(tippedArrowItem, 8);
                tippedArrowStack.set(DataComponents.POTION_CONTENTS, (PotionContents) lingeringPotionStack.get(DataComponents.POTION_CONTENTS));
                cir.setReturnValue(tippedArrowStack);
            }
        }
    }
}
