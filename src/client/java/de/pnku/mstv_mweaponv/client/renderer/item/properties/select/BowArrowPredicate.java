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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import org.jetbrains.annotations.Nullable;

import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_arrows;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.more_weapon_sticks;

@Environment(EnvType.CLIENT)
public record BowArrowPredicate() implements SelectItemModelProperty<String> {
    public static final SelectItemModelProperty.Type<BowArrowPredicate, String> TYPE;

    public BowArrowPredicate() {
    }

    @Nullable
    public String get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int seed, ItemDisplayContext itemDisplayContext) {
        String arrowStickWood;
        // Check from PullingPredicate
        if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) {
            Item arrowItem = livingEntity.getProjectile(itemStack).getItem();
            if (arrowItem.equals(Items.ARROW)||arrowItem.equals(Items.TIPPED_ARROW)) {return "oak";}
            if (more_arrows.contains(arrowItem)) {
                Item stickItem = more_weapon_sticks.get(arrowItem);
                if (stickItem.equals(Items.BAMBOO)) {arrowStickWood = "bamboo";}
                else if (stickItem.equals(Items.STICK)) {arrowStickWood = "oak";}
                else { arrowStickWood = ((MoreStickVariantItem) (more_weapon_sticks.get(arrowItem))).mstvWoodType;
                }
            } else {arrowStickWood = null;}
        } else {arrowStickWood = null;}
        return arrowStickWood;
    }

    public SelectItemModelProperty.Type<BowArrowPredicate, String> type() {
        return TYPE;
    }

    static {
        TYPE = Type.create(MapCodec.unit(new BowArrowPredicate()), Codec.STRING);
    }
}