package de.pnku.mstv_mweaponv.mixin.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CraftingBookCategory;
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
    public TippedArrowRecipeMixin(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Inject(method = "matches*", at = @At("HEAD"), cancellable = true)
    private void injectedMatches(CraftingContainer craftingContainer, Level level, CallbackInfoReturnable<Boolean> cir) {
        if (more_tippable_arrows.containsKey(craftingContainer.getItem(0).getItem())) {
            if (craftingContainer.getWidth() == 3 && craftingContainer.getHeight() == 3) {
                for (int i = 0; i < craftingContainer.getWidth(); ++i) {
                    for (int j = 0; j < craftingContainer.getHeight(); ++j) {
                        ItemStack itemStack = craftingContainer.getItem(i + j * craftingContainer.getWidth());
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
    private void injectAssemble(CraftingContainer craftingContainer, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> cir) {
        Item tippableArrowItem = craftingContainer.getItem(0).getItem();
        if (more_tippable_arrows.containsKey(tippableArrowItem)) {
            ItemStack lingeringPotionStack = craftingContainer.getItem(1 + craftingContainer.getWidth());
            if (!lingeringPotionStack.is(Items.LINGERING_POTION)) {
                cir.setReturnValue(ItemStack.EMPTY);
            } else {
                Item tippedArrowItem = more_tippable_arrows.get(tippableArrowItem);
                ItemStack tippedArrowStack = new ItemStack(tippedArrowItem, 8);
                PotionUtils.setPotion(tippedArrowStack, PotionUtils.getPotion(lingeringPotionStack));
                PotionUtils.setCustomEffects(tippedArrowStack, PotionUtils.getCustomEffects(lingeringPotionStack));
                cir.setReturnValue(tippedArrowStack);
            }
        }
    }
}
