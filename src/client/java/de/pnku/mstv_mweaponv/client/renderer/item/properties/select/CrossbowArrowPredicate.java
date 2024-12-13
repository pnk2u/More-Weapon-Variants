package de.pnku.mstv_mweaponv.client.renderer.item.properties.select;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import org.jetbrains.annotations.Nullable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.WARPED_TIPPED_ARROW;

@Environment(EnvType.CLIENT)
public record CrossbowArrowPredicate() implements SelectItemModelProperty<String> {
    public static final Type<CrossbowArrowPredicate, String> TYPE;

    public CrossbowArrowPredicate() {
    }

    public String get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int seed, ItemDisplayContext itemDisplayContext) {
        // Not checking for Firework as this predicate only gets checked when ChargeType predicate doesn't return ChargeType.ROCKET
        if (CrossbowItem.isCharged(itemStack)) {
            ChargedProjectiles chargedProjectiles = (ChargedProjectiles) itemStack.get(DataComponents.CHARGED_PROJECTILES);
            if (chargedProjectiles != null) {
                if (chargedProjectiles.contains(ACACIA_ARROW) || chargedProjectiles.contains(ACACIA_TIPPED_ARROW)) {return "acacia";}
                else if (chargedProjectiles.contains(BAMBOO_ARROW) || chargedProjectiles.contains(BAMBOO_TIPPED_ARROW)) {return "bamboo";}
                else if (chargedProjectiles.contains(BIRCH_ARROW) || chargedProjectiles.contains(BIRCH_TIPPED_ARROW)) {return "birch";}
                else if (chargedProjectiles.contains(CHERRY_ARROW) || chargedProjectiles.contains(CHERRY_TIPPED_ARROW)) {return "cherry";}
                else if (chargedProjectiles.contains(CRIMSON_ARROW) || chargedProjectiles.contains(CRIMSON_TIPPED_ARROW)) {return "crimson";}
                else if (chargedProjectiles.contains(DARK_OAK_ARROW) || chargedProjectiles.contains(DARK_OAK_TIPPED_ARROW)) {return "dark_oak";}
                else if (chargedProjectiles.contains(PALE_OAK_ARROW) || chargedProjectiles.contains(PALE_OAK_TIPPED_ARROW)) {return "pale_oak";}
                else if (chargedProjectiles.contains(JUNGLE_ARROW) || chargedProjectiles.contains(JUNGLE_TIPPED_ARROW)) {return "jungle";}
                else if (chargedProjectiles.contains(MANGROVE_ARROW) || chargedProjectiles.contains(MANGROVE_TIPPED_ARROW)) {return "mangrove";}
                else if (chargedProjectiles.contains(Items.ARROW) || chargedProjectiles.contains(Items.TIPPED_ARROW)) {return "oak";}
                else if (chargedProjectiles.contains(SPRUCE_ARROW) || chargedProjectiles.contains(SPRUCE_TIPPED_ARROW)) {return "spruce";}
                else if (chargedProjectiles.contains(WARPED_ARROW) || chargedProjectiles.contains(WARPED_TIPPED_ARROW)) {return "warped";}
                else {return "oak";}
            }
        }
        return "oak";
    }

    public Type<CrossbowArrowPredicate, String> type() {
        return TYPE;
    }

    static {
        TYPE = Type.create(MapCodec.unit(new CrossbowArrowPredicate()), Codec.STRING);
    }
}