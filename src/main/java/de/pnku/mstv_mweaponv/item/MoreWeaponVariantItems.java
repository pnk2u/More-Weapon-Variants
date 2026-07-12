package de.pnku.mstv_mweaponv.item;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ChargedProjectiles;

import java.util.*;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.withModId;
import static de.pnku.mstv_mweaponv.MoreWeaponVariants.*;
import static net.minecraft.world.item.ToolMaterial.*;

public class MoreWeaponVariantItems {
    private static final int swordAD = 3;
    private static final float swordAS = -2.4F;

    private static Item.Properties applyFireRes(String woodType, String toolMaterialString, Item.Properties properties){
        if (woodType.matches("crimson|warped") || toolMaterialString.equals("netherite")) {
            properties.fireResistant();
        }
        return properties;
    }

    private static String getStickName(Item stickIngredient) {
        if (stickIngredient.equals(Items.BAMBOO)) return "bamboo";
        if (stickIngredient.equals(Items.STICK)) return "oak";
        return ((MoreStickVariantItem) stickIngredient).mstvWoodType;
    }

        private static String swordName(Item stickIngredient, String toolMaterialName) {
            return getStickName(stickIngredient) + "_" + toolMaterialName + "_sword";
        }

    private static Item.Properties swordProperties(ToolMaterial toolMaterial, String toolMaterialName, Item stickIngredient) {
        Item.Properties properties = new Item.Properties().sword(toolMaterial, swordAD, swordAS).setId(ResourceKey.create(Registries.ITEM, withModId(swordName(stickIngredient, toolMaterialName))));
        return applyFireRes(getStickName(stickIngredient), toolMaterialName, properties);
    }

    private static String spearName(String toolMaterialName, Item stickIngredient) {
        return getStickName(stickIngredient) + "_" + toolMaterialName + "_spear";
    }

    private static Item.Properties spearProperties(ToolMaterial toolMaterial, String toolMaterialName, Item stickIngredient) {
        float swingTimeFactor; float kineticDamageMultiplier; float lungeDelaySeconds;
        float dismountWindowSeconds; float dismountMinSpeed; float knockbackWindowSeconds;
        float knockbackMinSpeed = 5.1F; float damageWindowSeconds; float damageMinRelativeSpeed = 4.6F;
        switch (toolMaterialName) {
            case "wooden" -> {
                swingTimeFactor = 0.65F; kineticDamageMultiplier = 0.7F; lungeDelaySeconds = 0.75F; dismountWindowSeconds = 5.0F;
                dismountMinSpeed = 14.0F; knockbackWindowSeconds = 6.0F; damageWindowSeconds = 15.0F;}
            case "stone", "deepslate", "blackstone" -> {
                swingTimeFactor = 0.75F; kineticDamageMultiplier = 0.82F; lungeDelaySeconds = 0.7F; dismountWindowSeconds = 4.5F;
                dismountMinSpeed = 10.0F; knockbackWindowSeconds = 5.5F; damageWindowSeconds = 13.75F;}
            case "copper" -> {
                swingTimeFactor = 0.85F; kineticDamageMultiplier = 0.82F; lungeDelaySeconds = 0.65F; dismountWindowSeconds = 4.0F;
                dismountMinSpeed = 9.0F; knockbackWindowSeconds = 5.0F; damageWindowSeconds = 12.5F;}
            case "iron" -> {
                swingTimeFactor = 0.95F; kineticDamageMultiplier = 0.95F; lungeDelaySeconds = 0.6F; dismountWindowSeconds = 2.5F;
                dismountMinSpeed = 8.0F; knockbackWindowSeconds = 4.5F; damageWindowSeconds = 11.25F;}
            case "golden" -> {
                swingTimeFactor = 0.95F; kineticDamageMultiplier = 0.7F; lungeDelaySeconds = 0.7F; dismountWindowSeconds = 3.5F;
                dismountMinSpeed = 10.0F; knockbackWindowSeconds = 5.5F; damageWindowSeconds = 13.75F;}
            case "diamond" -> {
                swingTimeFactor = 1.05F; kineticDamageMultiplier = 1.075F; lungeDelaySeconds = 0.5F; dismountWindowSeconds = 3.0F;
                dismountMinSpeed = 7.5F; knockbackWindowSeconds = 4.0F; damageWindowSeconds = 10.0F;}
            case "netherite" -> {
                swingTimeFactor = 1.15F; kineticDamageMultiplier = 1.2F; lungeDelaySeconds = 0.4F; dismountWindowSeconds = 2.5F;
                dismountMinSpeed = 7.0F; knockbackWindowSeconds = 3.5F; damageWindowSeconds = 8.75F;}
            default -> {
                swingTimeFactor = 0.1F; kineticDamageMultiplier = 0.1F; lungeDelaySeconds = 1.0F; dismountWindowSeconds = 15.0F;
                dismountMinSpeed = 15.0F; knockbackWindowSeconds = 15.0F; damageWindowSeconds = 15.0F;
                LOGGER.warn("Unknown tool material string '{}' found for a spear variant item '_{}_spear'. This should not happen!", toolMaterialName, getStickName(stickIngredient));}
        }
        Item.Properties properties = new Item.Properties().spear(toolMaterial, swingTimeFactor, kineticDamageMultiplier, lungeDelaySeconds,
                        dismountWindowSeconds, dismountMinSpeed, knockbackWindowSeconds, knockbackMinSpeed, damageWindowSeconds, damageMinRelativeSpeed)
                .setId(ResourceKey.create(Registries.ITEM, withModId(spearName(toolMaterialName, stickIngredient))));
        return applyFireRes(getStickName(stickIngredient), toolMaterialName, properties);
    }

    private static String bowName(Item stickIngredient) {
        return getStickName(stickIngredient) + "_bow";
    }

    private static Item.Properties bowProperties(Item stickIngredient) {
        return applyFireRes(getStickName(stickIngredient), "", new Item.Properties()
                .durability(384)
                .enchantable(1)
                .setId(ResourceKey.create(Registries.ITEM, withModId(bowName(stickIngredient)))));
    }

    private static String crossbowName(Item stickIngredient) {
        return getStickName(stickIngredient) + "_crossbow";
    }

    private static Item.Properties crossbowProperties(Item stickIngredient) {
        return applyFireRes(getStickName(stickIngredient), "", new Item.Properties()
                .durability(465)
                .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                .enchantable(1)).setId(ResourceKey.create(Registries.ITEM, withModId(crossbowName(stickIngredient))));
    }

    private static String arrowName(Item stickIngredient, boolean tipped) {
        return (tipped ? "tipped_" : "") + getStickName(stickIngredient) + ("_arrow");
    }

    private static Item.Properties arrowProperties(Item stickIngredient) {return arrowProperties(stickIngredient, false);}

    private static Item.Properties arrowProperties(Item stickIngredient, boolean tipped) {
        Item.Properties properties = applyFireRes(getStickName(stickIngredient), "", new Item.Properties());
        properties.setId(ResourceKey.create(Registries.ITEM, withModId(arrowName(stickIngredient, tipped))));
        if (tipped) properties.component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        return properties;
    }

            // Spears
            public static final Item ACACIA_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", ACACIA_STICK));
            public static final Item BAMBOO_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", Items.BAMBOO));
            public static final Item BIRCH_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", BIRCH_STICK));
            public static final Item CHERRY_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", CHERRY_STICK));
            public static final Item CRIMSON_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", CRIMSON_STICK));
            public static final Item DARK_OAK_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", DARK_OAK_STICK));
            public static final Item PALE_OAK_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", PALE_OAK_STICK));
            public static final Item JUNGLE_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", JUNGLE_STICK));
            public static final Item MANGROVE_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", MANGROVE_STICK));
            public static final Item SPRUCE_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", SPRUCE_STICK));
            public static final Item WARPED_WOODEN_SPEAR = new Item(spearProperties(WOOD, "wooden", WARPED_STICK));

                    public static final Item ACACIA_STONE_SPEAR = new Item(spearProperties(STONE, "stone", ACACIA_STICK));
                    public static final Item BAMBOO_STONE_SPEAR = new Item(spearProperties(STONE, "stone", Items.BAMBOO));
                    public static final Item BIRCH_STONE_SPEAR = new Item(spearProperties(STONE, "stone", BIRCH_STICK));
                    public static final Item CHERRY_STONE_SPEAR = new Item(spearProperties(STONE, "stone", CHERRY_STICK));
                    public static final Item CRIMSON_STONE_SPEAR = new Item(spearProperties(STONE, "stone", CRIMSON_STICK));
                    public static final Item DARK_OAK_STONE_SPEAR = new Item(spearProperties(STONE, "stone", DARK_OAK_STICK));
                    public static final Item PALE_OAK_STONE_SPEAR = new Item(spearProperties(STONE, "stone", PALE_OAK_STICK));
                    public static final Item JUNGLE_STONE_SPEAR = new Item(spearProperties(STONE, "stone", JUNGLE_STICK));
                    public static final Item MANGROVE_STONE_SPEAR = new Item(spearProperties(STONE, "stone", MANGROVE_STICK));
                    public static final Item SPRUCE_STONE_SPEAR = new Item(spearProperties(STONE, "stone", SPRUCE_STICK));
                    public static final Item WARPED_STONE_SPEAR = new Item(spearProperties(STONE, "stone", WARPED_STICK));

                    public static final Item ACACIA_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", ACACIA_STICK));
                    public static final Item BAMBOO_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", Items.BAMBOO));
                    public static final Item BIRCH_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", BIRCH_STICK));
                    public static final Item CHERRY_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", CHERRY_STICK));
                    public static final Item CRIMSON_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", CRIMSON_STICK));
                    public static final Item DARK_OAK_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", DARK_OAK_STICK));
                    public static final Item PALE_OAK_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", PALE_OAK_STICK));
                    public static final Item JUNGLE_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", JUNGLE_STICK));
                    public static final Item MANGROVE_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", MANGROVE_STICK));
                    public static final Item OAK_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", Items.STICK));
                    public static final Item SPRUCE_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", SPRUCE_STICK));
                    public static final Item WARPED_DEEPSLATE_SPEAR = new Item(spearProperties(STONE, "deepslate", WARPED_STICK));

                    public static final Item ACACIA_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", ACACIA_STICK));
                    public static final Item BAMBOO_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", Items.BAMBOO));
                    public static final Item BIRCH_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", BIRCH_STICK));
                    public static final Item CHERRY_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", CHERRY_STICK));
                    public static final Item CRIMSON_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", CRIMSON_STICK));
                    public static final Item DARK_OAK_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", DARK_OAK_STICK));
                    public static final Item PALE_OAK_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", PALE_OAK_STICK));
                    public static final Item JUNGLE_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", JUNGLE_STICK));
                    public static final Item MANGROVE_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", MANGROVE_STICK));
                    public static final Item OAK_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", Items.STICK));
                    public static final Item SPRUCE_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", SPRUCE_STICK));
                    public static final Item WARPED_BLACKSTONE_SPEAR = new Item(spearProperties(STONE, "blackstone", WARPED_STICK));

            public static final Item ACACIA_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", ACACIA_STICK));
            public static final Item BAMBOO_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", Items.BAMBOO));
            public static final Item BIRCH_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", BIRCH_STICK));
            public static final Item CHERRY_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", CHERRY_STICK));
            public static final Item CRIMSON_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", CRIMSON_STICK));
            public static final Item DARK_OAK_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", DARK_OAK_STICK));
            public static final Item PALE_OAK_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", PALE_OAK_STICK));
            public static final Item JUNGLE_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", JUNGLE_STICK));
            public static final Item MANGROVE_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", MANGROVE_STICK));
            public static final Item SPRUCE_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", SPRUCE_STICK));
            public static final Item WARPED_GOLDEN_SPEAR = new Item(spearProperties(GOLD, "golden", WARPED_STICK));

            public static final Item ACACIA_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", ACACIA_STICK));
            public static final Item BAMBOO_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", Items.BAMBOO));
            public static final Item BIRCH_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", BIRCH_STICK));
            public static final Item CHERRY_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", CHERRY_STICK));
            public static final Item CRIMSON_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", CRIMSON_STICK));
            public static final Item DARK_OAK_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", DARK_OAK_STICK));
            public static final Item PALE_OAK_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", PALE_OAK_STICK));
            public static final Item JUNGLE_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", JUNGLE_STICK));
            public static final Item MANGROVE_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", MANGROVE_STICK));
            public static final Item SPRUCE_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", SPRUCE_STICK));
            public static final Item WARPED_COPPER_SPEAR = new Item(spearProperties(COPPER, "copper", WARPED_STICK));

            public static final Item ACACIA_IRON_SPEAR = new Item(spearProperties(IRON, "iron", ACACIA_STICK));
            public static final Item BAMBOO_IRON_SPEAR = new Item(spearProperties(IRON, "iron", Items.BAMBOO));
            public static final Item BIRCH_IRON_SPEAR = new Item(spearProperties(IRON, "iron", BIRCH_STICK));
            public static final Item CHERRY_IRON_SPEAR = new Item(spearProperties(IRON, "iron", CHERRY_STICK));
            public static final Item CRIMSON_IRON_SPEAR = new Item(spearProperties(IRON, "iron", CRIMSON_STICK));
            public static final Item DARK_OAK_IRON_SPEAR = new Item(spearProperties(IRON, "iron", DARK_OAK_STICK));
            public static final Item PALE_OAK_IRON_SPEAR = new Item(spearProperties(IRON, "iron", PALE_OAK_STICK));
            public static final Item JUNGLE_IRON_SPEAR = new Item(spearProperties(IRON, "iron", JUNGLE_STICK));
            public static final Item MANGROVE_IRON_SPEAR = new Item(spearProperties(IRON, "iron", MANGROVE_STICK));
            public static final Item SPRUCE_IRON_SPEAR = new Item(spearProperties(IRON, "iron", SPRUCE_STICK));
            public static final Item WARPED_IRON_SPEAR = new Item(spearProperties(IRON, "iron", WARPED_STICK));

            public static final Item ACACIA_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", ACACIA_STICK));
            public static final Item BAMBOO_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", Items.BAMBOO));
            public static final Item BIRCH_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", BIRCH_STICK));
            public static final Item CHERRY_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", CHERRY_STICK));
            public static final Item CRIMSON_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", CRIMSON_STICK));
            public static final Item DARK_OAK_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", DARK_OAK_STICK));
            public static final Item PALE_OAK_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", PALE_OAK_STICK));
            public static final Item JUNGLE_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", JUNGLE_STICK));
            public static final Item MANGROVE_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", MANGROVE_STICK));
            public static final Item SPRUCE_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", SPRUCE_STICK));
            public static final Item WARPED_DIAMOND_SPEAR = new Item(spearProperties(DIAMOND, "diamond", WARPED_STICK));

            public static final Item ACACIA_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", ACACIA_STICK));
            public static final Item BAMBOO_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", Items.BAMBOO));
            public static final Item BIRCH_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", BIRCH_STICK));
            public static final Item CHERRY_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", CHERRY_STICK));
            public static final Item CRIMSON_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", CRIMSON_STICK));
            public static final Item DARK_OAK_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", DARK_OAK_STICK));
            public static final Item PALE_OAK_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", PALE_OAK_STICK));
            public static final Item JUNGLE_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", JUNGLE_STICK));
            public static final Item MANGROVE_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", MANGROVE_STICK));
            public static final Item SPRUCE_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", SPRUCE_STICK));
            public static final Item WARPED_NETHERITE_SPEAR = new Item(spearProperties(NETHERITE, "netherite", WARPED_STICK));

            // Swords
            public static final Item ACACIA_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", ACACIA_STICK));
            public static final Item BAMBOO_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", Items.BAMBOO));
            public static final Item BIRCH_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", BIRCH_STICK));
            public static final Item CHERRY_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", CHERRY_STICK));
            public static final Item CRIMSON_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", CRIMSON_STICK));
            public static final Item DARK_OAK_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", DARK_OAK_STICK));
            public static final Item PALE_OAK_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", PALE_OAK_STICK));
            public static final Item JUNGLE_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", JUNGLE_STICK));
            public static final Item MANGROVE_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", MANGROVE_STICK));
            public static final Item SPRUCE_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", SPRUCE_STICK));
            public static final Item WARPED_WOODEN_SWORD = new Item(swordProperties(WOOD, "wooden", WARPED_STICK));

                    public static final Item ACACIA_STONE_SWORD = new Item(swordProperties(STONE, "stone", ACACIA_STICK));
                    public static final Item BAMBOO_STONE_SWORD = new Item(swordProperties(STONE, "stone", Items.BAMBOO));
                    public static final Item BIRCH_STONE_SWORD = new Item(swordProperties(STONE, "stone", BIRCH_STICK));
                    public static final Item CHERRY_STONE_SWORD = new Item(swordProperties(STONE, "stone", CHERRY_STICK));
                    public static final Item CRIMSON_STONE_SWORD = new Item(swordProperties(STONE, "stone", CRIMSON_STICK));
                    public static final Item DARK_OAK_STONE_SWORD = new Item(swordProperties(STONE, "stone", DARK_OAK_STICK));
                    public static final Item PALE_OAK_STONE_SWORD = new Item(swordProperties(STONE, "stone", PALE_OAK_STICK));
                    public static final Item JUNGLE_STONE_SWORD = new Item(swordProperties(STONE, "stone", JUNGLE_STICK));
                    public static final Item MANGROVE_STONE_SWORD = new Item(swordProperties(STONE, "stone", MANGROVE_STICK));
                    public static final Item SPRUCE_STONE_SWORD = new Item(swordProperties(STONE, "stone", SPRUCE_STICK));
                    public static final Item WARPED_STONE_SWORD = new Item(swordProperties(STONE, "stone", WARPED_STICK));

                    public static final Item ACACIA_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", ACACIA_STICK));
                    public static final Item BAMBOO_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", Items.BAMBOO));
                    public static final Item BIRCH_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", BIRCH_STICK));
                    public static final Item CHERRY_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", CHERRY_STICK));
                    public static final Item CRIMSON_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", CRIMSON_STICK));
                    public static final Item DARK_OAK_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", DARK_OAK_STICK));
                    public static final Item PALE_OAK_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", PALE_OAK_STICK));
                    public static final Item JUNGLE_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", JUNGLE_STICK));
                    public static final Item MANGROVE_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", MANGROVE_STICK));
                    public static final Item OAK_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", Items.STICK));
                    public static final Item SPRUCE_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", SPRUCE_STICK));
                    public static final Item WARPED_DEEPSLATE_SWORD = new Item(swordProperties(STONE, "deepslate", WARPED_STICK));

                    public static final Item ACACIA_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", ACACIA_STICK));
                    public static final Item BAMBOO_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", Items.BAMBOO));
                    public static final Item BIRCH_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", BIRCH_STICK));
                    public static final Item CHERRY_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", CHERRY_STICK));
                    public static final Item CRIMSON_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", CRIMSON_STICK));
                    public static final Item DARK_OAK_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", DARK_OAK_STICK));
                    public static final Item PALE_OAK_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", PALE_OAK_STICK));
                    public static final Item JUNGLE_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", JUNGLE_STICK));
                    public static final Item MANGROVE_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", MANGROVE_STICK));
                    public static final Item OAK_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", Items.STICK));
                    public static final Item SPRUCE_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", SPRUCE_STICK));
                    public static final Item WARPED_BLACKSTONE_SWORD = new Item(swordProperties(STONE, "blackstone", WARPED_STICK));

            public static final Item ACACIA_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", ACACIA_STICK));
            public static final Item BAMBOO_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", Items.BAMBOO));
            public static final Item BIRCH_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", BIRCH_STICK));
            public static final Item CHERRY_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", CHERRY_STICK));
            public static final Item CRIMSON_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", CRIMSON_STICK));
            public static final Item DARK_OAK_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", DARK_OAK_STICK));
            public static final Item PALE_OAK_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", PALE_OAK_STICK));
            public static final Item JUNGLE_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", JUNGLE_STICK));
            public static final Item MANGROVE_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", MANGROVE_STICK));
            public static final Item SPRUCE_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", SPRUCE_STICK));
            public static final Item WARPED_GOLDEN_SWORD = new Item(swordProperties(GOLD, "golden", WARPED_STICK));

            public static final Item ACACIA_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", ACACIA_STICK));
            public static final Item BAMBOO_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", Items.BAMBOO));
            public static final Item BIRCH_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", BIRCH_STICK));
            public static final Item CHERRY_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", CHERRY_STICK));
            public static final Item CRIMSON_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", CRIMSON_STICK));
            public static final Item DARK_OAK_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", DARK_OAK_STICK));
            public static final Item PALE_OAK_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", PALE_OAK_STICK));
            public static final Item JUNGLE_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", JUNGLE_STICK));
            public static final Item MANGROVE_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", MANGROVE_STICK));
            public static final Item SPRUCE_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", SPRUCE_STICK));
            public static final Item WARPED_COPPER_SWORD = new Item(swordProperties(COPPER, "copper", WARPED_STICK));

            public static final Item ACACIA_IRON_SWORD = new Item(swordProperties(IRON, "iron", ACACIA_STICK));
            public static final Item BAMBOO_IRON_SWORD = new Item(swordProperties(IRON, "iron", Items.BAMBOO));
            public static final Item BIRCH_IRON_SWORD = new Item(swordProperties(IRON, "iron", BIRCH_STICK));
            public static final Item CHERRY_IRON_SWORD = new Item(swordProperties(IRON, "iron", CHERRY_STICK));
            public static final Item CRIMSON_IRON_SWORD = new Item(swordProperties(IRON, "iron", CRIMSON_STICK));
            public static final Item DARK_OAK_IRON_SWORD = new Item(swordProperties(IRON, "iron", DARK_OAK_STICK));
            public static final Item PALE_OAK_IRON_SWORD = new Item(swordProperties(IRON, "iron", PALE_OAK_STICK));
            public static final Item JUNGLE_IRON_SWORD = new Item(swordProperties(IRON, "iron", JUNGLE_STICK));
            public static final Item MANGROVE_IRON_SWORD = new Item(swordProperties(IRON, "iron", MANGROVE_STICK));
            public static final Item SPRUCE_IRON_SWORD = new Item(swordProperties(IRON, "iron", SPRUCE_STICK));
            public static final Item WARPED_IRON_SWORD = new Item(swordProperties(IRON, "iron", WARPED_STICK));

            public static final Item ACACIA_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", ACACIA_STICK));
            public static final Item BAMBOO_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", Items.BAMBOO));
            public static final Item BIRCH_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", BIRCH_STICK));
            public static final Item CHERRY_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", CHERRY_STICK));
            public static final Item CRIMSON_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", CRIMSON_STICK));
            public static final Item DARK_OAK_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", DARK_OAK_STICK));
            public static final Item PALE_OAK_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", PALE_OAK_STICK));
            public static final Item JUNGLE_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", JUNGLE_STICK));
            public static final Item MANGROVE_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", MANGROVE_STICK));
            public static final Item SPRUCE_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", SPRUCE_STICK));
            public static final Item WARPED_DIAMOND_SWORD = new Item(swordProperties(DIAMOND, "diamond", WARPED_STICK));

            public static final Item ACACIA_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", ACACIA_STICK));
            public static final Item BAMBOO_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", Items.BAMBOO));
            public static final Item BIRCH_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", BIRCH_STICK));
            public static final Item CHERRY_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", CHERRY_STICK));
            public static final Item CRIMSON_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", CRIMSON_STICK));
            public static final Item DARK_OAK_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", DARK_OAK_STICK));
            public static final Item PALE_OAK_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", PALE_OAK_STICK));
            public static final Item JUNGLE_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", JUNGLE_STICK));
            public static final Item MANGROVE_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", MANGROVE_STICK));
            public static final Item SPRUCE_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", SPRUCE_STICK));
            public static final Item WARPED_NETHERITE_SWORD = new Item(swordProperties(NETHERITE, "netherite", WARPED_STICK));


            // Bows
            public static final Item ACACIA_BOW = new BowItem(bowProperties(ACACIA_STICK));
            public static final Item BAMBOO_BOW = new BowItem(bowProperties(Items.BAMBOO));
            public static final Item BIRCH_BOW = new BowItem(bowProperties(BIRCH_STICK));
            public static final Item CHERRY_BOW = new BowItem(bowProperties(CHERRY_STICK));
            public static final Item CRIMSON_BOW = new BowItem(bowProperties(CRIMSON_STICK));
            public static final Item DARK_OAK_BOW = new BowItem(bowProperties(DARK_OAK_STICK));
            public static final Item PALE_OAK_BOW = new BowItem(bowProperties(PALE_OAK_STICK));
            public static final Item JUNGLE_BOW = new BowItem(bowProperties(JUNGLE_STICK));
            public static final Item MANGROVE_BOW = new BowItem(bowProperties(MANGROVE_STICK));
            public static final Item SPRUCE_BOW = new BowItem(bowProperties(SPRUCE_STICK));
            public static final Item WARPED_BOW = new BowItem(bowProperties(WARPED_STICK));
            // Crossbows
            public static final Item ACACIA_CROSSBOW = new CrossbowItem(crossbowProperties(ACACIA_STICK));
            public static final Item BAMBOO_CROSSBOW = new CrossbowItem(crossbowProperties(Items.BAMBOO));
            public static final Item BIRCH_CROSSBOW = new CrossbowItem(crossbowProperties(BIRCH_STICK));
            public static final Item CHERRY_CROSSBOW = new CrossbowItem(crossbowProperties(CHERRY_STICK));
            public static final Item CRIMSON_CROSSBOW = new CrossbowItem(crossbowProperties(CRIMSON_STICK));
            public static final Item JUNGLE_CROSSBOW = new CrossbowItem(crossbowProperties(JUNGLE_STICK));
            public static final Item MANGROVE_CROSSBOW = new CrossbowItem(crossbowProperties(MANGROVE_STICK));
            public static final Item OAK_CROSSBOW = new CrossbowItem(crossbowProperties(Items.STICK));
            public static final Item PALE_OAK_CROSSBOW = new CrossbowItem(crossbowProperties(PALE_OAK_STICK));
            public static final Item SPRUCE_CROSSBOW = new CrossbowItem(crossbowProperties(SPRUCE_STICK));
            public static final Item WARPED_CROSSBOW = new CrossbowItem(crossbowProperties(WARPED_STICK));
            // Arrows
            public static final Item ACACIA_ARROW = new ArrowItem(arrowProperties(ACACIA_STICK));
            public static final Item BAMBOO_ARROW = new ArrowItem(arrowProperties(Items.BAMBOO));
            public static final Item BIRCH_ARROW = new ArrowItem(arrowProperties(BIRCH_STICK));
            public static final Item CHERRY_ARROW = new ArrowItem(arrowProperties(CHERRY_STICK));
            public static final Item CRIMSON_ARROW = new ArrowItem(arrowProperties(CRIMSON_STICK));
            public static final Item DARK_OAK_ARROW = new ArrowItem(arrowProperties(DARK_OAK_STICK));
            public static final Item PALE_OAK_ARROW = new ArrowItem(arrowProperties(PALE_OAK_STICK));
            public static final Item JUNGLE_ARROW = new ArrowItem(arrowProperties(JUNGLE_STICK));
            public static final Item MANGROVE_ARROW = new ArrowItem(arrowProperties(MANGROVE_STICK));
            public static final Item SPRUCE_ARROW = new ArrowItem(arrowProperties(SPRUCE_STICK));
            public static final Item WARPED_ARROW = new ArrowItem(arrowProperties(WARPED_STICK));
            // Tipped Arrows
            public static final Item ACACIA_TIPPED_ARROW = new TippedArrowItem(arrowProperties(ACACIA_STICK, true));
            public static final Item BAMBOO_TIPPED_ARROW = new TippedArrowItem(arrowProperties(Items.BAMBOO, true));
            public static final Item BIRCH_TIPPED_ARROW = new TippedArrowItem(arrowProperties(BIRCH_STICK, true));
            public static final Item CHERRY_TIPPED_ARROW = new TippedArrowItem(arrowProperties(CHERRY_STICK, true));
            public static final Item CRIMSON_TIPPED_ARROW = new TippedArrowItem(arrowProperties(CRIMSON_STICK, true));
            public static final Item DARK_OAK_TIPPED_ARROW = new TippedArrowItem(arrowProperties(DARK_OAK_STICK, true));
            public static final Item PALE_OAK_TIPPED_ARROW = new TippedArrowItem(arrowProperties(PALE_OAK_STICK, true));
            public static final Item JUNGLE_TIPPED_ARROW = new TippedArrowItem(arrowProperties(JUNGLE_STICK, true));
            public static final Item MANGROVE_TIPPED_ARROW = new TippedArrowItem(arrowProperties(MANGROVE_STICK, true));
            public static final Item SPRUCE_TIPPED_ARROW = new TippedArrowItem(arrowProperties(SPRUCE_STICK, true));
            public static final Item WARPED_TIPPED_ARROW = new TippedArrowItem(arrowProperties(WARPED_STICK, true));


    public static final List<Item> more_weapons = new ArrayList<>();
    public static final List<Item> more_swords = new ArrayList<>();
    public static final List<Item> more_spears = new ArrayList<>();
    public static final List<Item> more_bows = new ArrayList<>();
    public static final List<Item> more_crossbows = new ArrayList<>();
    public static final List<Item> more_arrows = new ArrayList<>();
    public static final Map<Item, Item> more_weapon_sticks = new HashMap<>();
    public static final Map<Item, Item> more_tippable_arrows = new HashMap<>();

    public static void registerWeaponItems() {

      //Acacia Weapons
       registerSwordItem(ACACIA_WOODEN_SWORD, ACACIA_STICK, "wooden");
       registerSwordItem(ACACIA_STONE_SWORD, ACACIA_STICK, "stone");
       registerSwordItem(ACACIA_DEEPSLATE_SWORD, ACACIA_STICK, "deepslate");
       registerSwordItem(ACACIA_BLACKSTONE_SWORD, ACACIA_STICK, "blackstone");
       registerSwordItem(ACACIA_COPPER_SWORD, ACACIA_STICK, "copper");
       registerSwordItem(ACACIA_IRON_SWORD, ACACIA_STICK, "iron");
       registerSwordItem(ACACIA_GOLDEN_SWORD, ACACIA_STICK, "golden");
       registerSwordItem(ACACIA_DIAMOND_SWORD, ACACIA_STICK, "diamond");
       registerSwordItem(ACACIA_NETHERITE_SWORD, ACACIA_STICK, "netherite");
       registerSpearItem(ACACIA_WOODEN_SPEAR, ACACIA_STICK, "wooden");
       registerSpearItem(ACACIA_STONE_SPEAR, ACACIA_STICK, "stone");
       registerSpearItem(ACACIA_DEEPSLATE_SPEAR, ACACIA_STICK, "deepslate");
       registerSpearItem(ACACIA_BLACKSTONE_SPEAR, ACACIA_STICK, "blackstone");
       registerSpearItem(ACACIA_COPPER_SPEAR, ACACIA_STICK, "copper");
       registerSpearItem(ACACIA_IRON_SPEAR, ACACIA_STICK, "iron");
       registerSpearItem(ACACIA_GOLDEN_SPEAR, ACACIA_STICK, "golden");
       registerSpearItem(ACACIA_DIAMOND_SPEAR, ACACIA_STICK, "diamond");
       registerSpearItem(ACACIA_NETHERITE_SPEAR, ACACIA_STICK, "netherite");
       registerBowItem(ACACIA_BOW, ACACIA_STICK);
       registerCrossbowItem(ACACIA_CROSSBOW, ACACIA_STICK);
       registerArrowItem(ACACIA_ARROW, ACACIA_STICK, ACACIA_TIPPED_ARROW);


        //Bamboo Weapons
       registerSwordItem(BAMBOO_WOODEN_SWORD, Items.BAMBOO, "wooden");
       registerSwordItem(BAMBOO_STONE_SWORD, Items.BAMBOO, "stone");
       registerSwordItem(BAMBOO_DEEPSLATE_SWORD, Items.BAMBOO, "deepslate");
       registerSwordItem(BAMBOO_BLACKSTONE_SWORD, Items.BAMBOO, "blackstone");
       registerSwordItem(BAMBOO_COPPER_SWORD, Items.BAMBOO, "copper");
       registerSwordItem(BAMBOO_IRON_SWORD, Items.BAMBOO, "iron");
       registerSwordItem(BAMBOO_GOLDEN_SWORD, Items.BAMBOO, "golden");
       registerSwordItem(BAMBOO_DIAMOND_SWORD, Items.BAMBOO, "diamond");
       registerSwordItem(BAMBOO_NETHERITE_SWORD, Items.BAMBOO, "netherite");
       registerSpearItem(BAMBOO_WOODEN_SPEAR, Items.BAMBOO, "wooden");
       registerSpearItem(BAMBOO_STONE_SPEAR, Items.BAMBOO, "stone");
       registerSpearItem(BAMBOO_DEEPSLATE_SPEAR, Items.BAMBOO, "deepslate");
       registerSpearItem(BAMBOO_BLACKSTONE_SPEAR, Items.BAMBOO, "blackstone");
       registerSpearItem(BAMBOO_COPPER_SPEAR, Items.BAMBOO, "copper");
       registerSpearItem(BAMBOO_IRON_SPEAR, Items.BAMBOO, "iron");
       registerSpearItem(BAMBOO_GOLDEN_SPEAR, Items.BAMBOO, "golden");
       registerSpearItem(BAMBOO_DIAMOND_SPEAR, Items.BAMBOO, "diamond");
       registerSpearItem(BAMBOO_NETHERITE_SPEAR, Items.BAMBOO, "netherite");
       registerBowItem(BAMBOO_BOW, Items.BAMBOO);
       registerCrossbowItem(BAMBOO_CROSSBOW, Items.BAMBOO);
       registerArrowItem(BAMBOO_ARROW, Items.BAMBOO, BAMBOO_TIPPED_ARROW);


      //Birch Weapons
       registerSwordItem(BIRCH_WOODEN_SWORD, BIRCH_STICK, "wooden");
       registerSwordItem(BIRCH_STONE_SWORD, BIRCH_STICK, "stone");
       registerSwordItem(BIRCH_DEEPSLATE_SWORD, BIRCH_STICK, "deepslate");
       registerSwordItem(BIRCH_BLACKSTONE_SWORD, BIRCH_STICK, "blackstone");
       registerSwordItem(BIRCH_COPPER_SWORD, BIRCH_STICK, "copper");
       registerSwordItem(BIRCH_IRON_SWORD, BIRCH_STICK, "iron");
       registerSwordItem(BIRCH_GOLDEN_SWORD, BIRCH_STICK, "golden");
       registerSwordItem(BIRCH_DIAMOND_SWORD, BIRCH_STICK, "diamond");
       registerSwordItem(BIRCH_NETHERITE_SWORD, BIRCH_STICK, "netherite");
       registerSpearItem(BIRCH_WOODEN_SPEAR, BIRCH_STICK, "wooden");
       registerSpearItem(BIRCH_STONE_SPEAR, BIRCH_STICK, "stone");
       registerSpearItem(BIRCH_DEEPSLATE_SPEAR, BIRCH_STICK, "deepslate");
       registerSpearItem(BIRCH_BLACKSTONE_SPEAR, BIRCH_STICK, "blackstone");
       registerSpearItem(BIRCH_COPPER_SPEAR, BIRCH_STICK, "copper");
       registerSpearItem(BIRCH_IRON_SPEAR, BIRCH_STICK, "iron");
       registerSpearItem(BIRCH_GOLDEN_SPEAR, BIRCH_STICK, "golden");
       registerSpearItem(BIRCH_DIAMOND_SPEAR, BIRCH_STICK, "diamond");
       registerSpearItem(BIRCH_NETHERITE_SPEAR, BIRCH_STICK, "netherite");
       registerBowItem(BIRCH_BOW, BIRCH_STICK);
       registerCrossbowItem(BIRCH_CROSSBOW, BIRCH_STICK);
       registerArrowItem(BIRCH_ARROW, BIRCH_STICK, BIRCH_TIPPED_ARROW);


      //Cherry Weapons
       registerSwordItem(CHERRY_WOODEN_SWORD, CHERRY_STICK, "wooden");
       registerSwordItem(CHERRY_STONE_SWORD, CHERRY_STICK, "stone");
       registerSwordItem(CHERRY_DEEPSLATE_SWORD, CHERRY_STICK, "deepslate");
       registerSwordItem(CHERRY_BLACKSTONE_SWORD, CHERRY_STICK, "blackstone");
       registerSwordItem(CHERRY_COPPER_SWORD, CHERRY_STICK, "copper");
       registerSwordItem(CHERRY_IRON_SWORD, CHERRY_STICK, "iron");
       registerSwordItem(CHERRY_GOLDEN_SWORD, CHERRY_STICK, "golden");
       registerSwordItem(CHERRY_DIAMOND_SWORD, CHERRY_STICK, "diamond");
       registerSwordItem(CHERRY_NETHERITE_SWORD, CHERRY_STICK, "netherite");
       registerSpearItem(CHERRY_WOODEN_SPEAR, CHERRY_STICK, "wooden");
       registerSpearItem(CHERRY_STONE_SPEAR, CHERRY_STICK, "stone");
       registerSpearItem(CHERRY_DEEPSLATE_SPEAR, CHERRY_STICK, "deepslate");
       registerSpearItem(CHERRY_BLACKSTONE_SPEAR, CHERRY_STICK, "blackstone");
       registerSpearItem(CHERRY_COPPER_SPEAR, CHERRY_STICK, "copper");
       registerSpearItem(CHERRY_IRON_SPEAR, CHERRY_STICK, "iron");
       registerSpearItem(CHERRY_GOLDEN_SPEAR, CHERRY_STICK, "golden");
       registerSpearItem(CHERRY_DIAMOND_SPEAR, CHERRY_STICK, "diamond");
       registerSpearItem(CHERRY_NETHERITE_SPEAR, CHERRY_STICK, "netherite");
       registerBowItem(CHERRY_BOW, CHERRY_STICK);
       registerCrossbowItem(CHERRY_CROSSBOW, CHERRY_STICK);
       registerArrowItem(CHERRY_ARROW, CHERRY_STICK, CHERRY_TIPPED_ARROW);


      //Crimson Weapons
       registerSwordItem(CRIMSON_WOODEN_SWORD, CRIMSON_STICK, "wooden");
       registerSwordItem(CRIMSON_STONE_SWORD, CRIMSON_STICK, "stone");
       registerSwordItem(CRIMSON_DEEPSLATE_SWORD, CRIMSON_STICK, "deepslate");
       registerSwordItem(CRIMSON_BLACKSTONE_SWORD, CRIMSON_STICK, "blackstone");
       registerSwordItem(CRIMSON_COPPER_SWORD, CRIMSON_STICK, "copper");
       registerSwordItem(CRIMSON_IRON_SWORD, CRIMSON_STICK, "iron");
       registerSwordItem(CRIMSON_GOLDEN_SWORD, CRIMSON_STICK, "golden");
       registerSwordItem(CRIMSON_DIAMOND_SWORD, CRIMSON_STICK, "diamond");
       registerSwordItem(CRIMSON_NETHERITE_SWORD, CRIMSON_STICK, "netherite");
       registerSpearItem(CRIMSON_WOODEN_SPEAR, CRIMSON_STICK, "wooden");
       registerSpearItem(CRIMSON_STONE_SPEAR, CRIMSON_STICK, "stone");
       registerSpearItem(CRIMSON_DEEPSLATE_SPEAR, CRIMSON_STICK, "deepslate");
       registerSpearItem(CRIMSON_BLACKSTONE_SPEAR, CRIMSON_STICK, "blackstone");
       registerSpearItem(CRIMSON_COPPER_SPEAR, CRIMSON_STICK, "copper");
       registerSpearItem(CRIMSON_IRON_SPEAR, CRIMSON_STICK, "iron");
       registerSpearItem(CRIMSON_GOLDEN_SPEAR, CRIMSON_STICK, "golden");
       registerSpearItem(CRIMSON_DIAMOND_SPEAR, CRIMSON_STICK, "diamond");
       registerSpearItem(CRIMSON_NETHERITE_SPEAR, CRIMSON_STICK, "netherite");
       registerBowItem(CRIMSON_BOW, CRIMSON_STICK);
       registerCrossbowItem(CRIMSON_CROSSBOW, CRIMSON_STICK);
       registerArrowItem(CRIMSON_ARROW, CRIMSON_STICK, CRIMSON_TIPPED_ARROW);


      //Dark_oak Weapons
       registerSwordItem(DARK_OAK_WOODEN_SWORD, DARK_OAK_STICK, "wooden");
       registerSwordItem(DARK_OAK_STONE_SWORD, DARK_OAK_STICK, "stone");
       registerSwordItem(DARK_OAK_DEEPSLATE_SWORD, DARK_OAK_STICK, "deepslate");
       registerSwordItem(DARK_OAK_BLACKSTONE_SWORD, DARK_OAK_STICK, "blackstone");
       registerSwordItem(DARK_OAK_COPPER_SWORD, DARK_OAK_STICK, "copper");
       registerSwordItem(DARK_OAK_IRON_SWORD, DARK_OAK_STICK, "iron");
       registerSwordItem(DARK_OAK_GOLDEN_SWORD, DARK_OAK_STICK, "golden");
       registerSwordItem(DARK_OAK_DIAMOND_SWORD, DARK_OAK_STICK, "diamond");
       registerSwordItem(DARK_OAK_NETHERITE_SWORD, DARK_OAK_STICK, "netherite");
       registerSpearItem(DARK_OAK_WOODEN_SPEAR, DARK_OAK_STICK, "wooden");
       registerSpearItem(DARK_OAK_STONE_SPEAR, DARK_OAK_STICK, "stone");
       registerSpearItem(DARK_OAK_DEEPSLATE_SPEAR, DARK_OAK_STICK, "deepslate");
       registerSpearItem(DARK_OAK_BLACKSTONE_SPEAR, DARK_OAK_STICK, "blackstone");
       registerSpearItem(DARK_OAK_COPPER_SPEAR, DARK_OAK_STICK, "copper");
       registerSpearItem(DARK_OAK_IRON_SPEAR, DARK_OAK_STICK, "iron");
       registerSpearItem(DARK_OAK_GOLDEN_SPEAR, DARK_OAK_STICK, "golden");
       registerSpearItem(DARK_OAK_DIAMOND_SPEAR, DARK_OAK_STICK, "diamond");
       registerSpearItem(DARK_OAK_NETHERITE_SPEAR, DARK_OAK_STICK, "netherite");
       registerBowItem(DARK_OAK_BOW, DARK_OAK_STICK);
        more_weapons.add(Items.CROSSBOW);
       registerArrowItem(DARK_OAK_ARROW, DARK_OAK_STICK, DARK_OAK_TIPPED_ARROW);


        //Pale_oak Weapons
        registerSwordItem(PALE_OAK_WOODEN_SWORD, PALE_OAK_STICK, "wooden");
        registerSwordItem(PALE_OAK_STONE_SWORD, PALE_OAK_STICK, "stone");
        registerSwordItem(PALE_OAK_DEEPSLATE_SWORD, PALE_OAK_STICK, "deepslate");
        registerSwordItem(PALE_OAK_BLACKSTONE_SWORD, PALE_OAK_STICK, "blackstone");
        registerSwordItem(PALE_OAK_COPPER_SWORD, PALE_OAK_STICK, "copper");
        registerSwordItem(PALE_OAK_IRON_SWORD, PALE_OAK_STICK, "iron");
        registerSwordItem(PALE_OAK_GOLDEN_SWORD, PALE_OAK_STICK, "golden");
        registerSwordItem(PALE_OAK_DIAMOND_SWORD, PALE_OAK_STICK, "diamond");
        registerSwordItem(PALE_OAK_NETHERITE_SWORD, PALE_OAK_STICK, "netherite");
        registerSpearItem(PALE_OAK_WOODEN_SPEAR, PALE_OAK_STICK, "wooden");
        registerSpearItem(PALE_OAK_STONE_SPEAR, PALE_OAK_STICK, "stone");
        registerSpearItem(PALE_OAK_DEEPSLATE_SPEAR, PALE_OAK_STICK, "deepslate");
        registerSpearItem(PALE_OAK_BLACKSTONE_SPEAR, PALE_OAK_STICK, "blackstone");
        registerSpearItem(PALE_OAK_COPPER_SPEAR, PALE_OAK_STICK, "copper");
        registerSpearItem(PALE_OAK_IRON_SPEAR, PALE_OAK_STICK, "iron");
        registerSpearItem(PALE_OAK_GOLDEN_SPEAR, PALE_OAK_STICK, "golden");
        registerSpearItem(PALE_OAK_DIAMOND_SPEAR, PALE_OAK_STICK, "diamond");
        registerSpearItem(PALE_OAK_NETHERITE_SPEAR, PALE_OAK_STICK, "netherite");
        registerBowItem(PALE_OAK_BOW, PALE_OAK_STICK);
        registerCrossbowItem(PALE_OAK_CROSSBOW, PALE_OAK_STICK);
        registerArrowItem(PALE_OAK_ARROW, PALE_OAK_STICK, PALE_OAK_TIPPED_ARROW, 13);


      //Jungle Weapons
       registerSwordItem(JUNGLE_WOODEN_SWORD, JUNGLE_STICK, "wooden");
       registerSwordItem(JUNGLE_STONE_SWORD, JUNGLE_STICK, "stone");
       registerSwordItem(JUNGLE_DEEPSLATE_SWORD, JUNGLE_STICK, "deepslate");
       registerSwordItem(JUNGLE_BLACKSTONE_SWORD, JUNGLE_STICK, "blackstone");
       registerSwordItem(JUNGLE_COPPER_SWORD, JUNGLE_STICK, "copper");
       registerSwordItem(JUNGLE_IRON_SWORD, JUNGLE_STICK, "iron");
       registerSwordItem(JUNGLE_GOLDEN_SWORD, JUNGLE_STICK, "golden");
       registerSwordItem(JUNGLE_DIAMOND_SWORD, JUNGLE_STICK, "diamond");
       registerSwordItem(JUNGLE_NETHERITE_SWORD, JUNGLE_STICK, "netherite");
       registerSpearItem(JUNGLE_WOODEN_SPEAR, JUNGLE_STICK, "wooden");
       registerSpearItem(JUNGLE_STONE_SPEAR, JUNGLE_STICK, "stone");
       registerSpearItem(JUNGLE_DEEPSLATE_SPEAR, JUNGLE_STICK, "deepslate");
       registerSpearItem(JUNGLE_BLACKSTONE_SPEAR, JUNGLE_STICK, "blackstone");
       registerSpearItem(JUNGLE_COPPER_SPEAR, JUNGLE_STICK, "copper");
       registerSpearItem(JUNGLE_IRON_SPEAR, JUNGLE_STICK, "iron");
       registerSpearItem(JUNGLE_GOLDEN_SPEAR, JUNGLE_STICK, "golden");
       registerSpearItem(JUNGLE_DIAMOND_SPEAR, JUNGLE_STICK, "diamond");
       registerSpearItem(JUNGLE_NETHERITE_SPEAR, JUNGLE_STICK, "netherite");
       registerBowItem(JUNGLE_BOW, JUNGLE_STICK);
       registerCrossbowItem(JUNGLE_CROSSBOW, JUNGLE_STICK);
       registerArrowItem(JUNGLE_ARROW, JUNGLE_STICK, JUNGLE_TIPPED_ARROW);


      //Mangrove Weapons
       registerSwordItem(MANGROVE_WOODEN_SWORD, MANGROVE_STICK, "wooden");
       registerSwordItem(MANGROVE_STONE_SWORD, MANGROVE_STICK, "stone");
       registerSwordItem(MANGROVE_DEEPSLATE_SWORD, MANGROVE_STICK, "deepslate");
       registerSwordItem(MANGROVE_BLACKSTONE_SWORD, MANGROVE_STICK, "blackstone");
       registerSwordItem(MANGROVE_COPPER_SWORD, MANGROVE_STICK, "copper");
       registerSwordItem(MANGROVE_IRON_SWORD, MANGROVE_STICK, "iron");
       registerSwordItem(MANGROVE_GOLDEN_SWORD, MANGROVE_STICK, "golden");
       registerSwordItem(MANGROVE_DIAMOND_SWORD, MANGROVE_STICK, "diamond");
       registerSwordItem(MANGROVE_NETHERITE_SWORD, MANGROVE_STICK, "netherite");
       registerSpearItem(MANGROVE_WOODEN_SPEAR, MANGROVE_STICK, "wooden");
       registerSpearItem(MANGROVE_STONE_SPEAR, MANGROVE_STICK, "stone");
       registerSpearItem(MANGROVE_DEEPSLATE_SPEAR, MANGROVE_STICK, "deepslate");
       registerSpearItem(MANGROVE_BLACKSTONE_SPEAR, MANGROVE_STICK, "blackstone");
       registerSpearItem(MANGROVE_COPPER_SPEAR, MANGROVE_STICK, "copper");
       registerSpearItem(MANGROVE_IRON_SPEAR, MANGROVE_STICK, "iron");
       registerSpearItem(MANGROVE_GOLDEN_SPEAR, MANGROVE_STICK, "golden");
       registerSpearItem(MANGROVE_DIAMOND_SPEAR, MANGROVE_STICK, "diamond");
       registerSpearItem(MANGROVE_NETHERITE_SPEAR, MANGROVE_STICK, "netherite");
       registerBowItem(MANGROVE_BOW, MANGROVE_STICK);
       registerCrossbowItem(MANGROVE_CROSSBOW, MANGROVE_STICK);
       registerArrowItem(MANGROVE_ARROW, MANGROVE_STICK, MANGROVE_TIPPED_ARROW);


      //Oak Weapons
        more_weapons.add(Items.WOODEN_SWORD); more_swords.add(Items.WOODEN_SWORD);
        more_weapons.add(Items.STONE_SWORD); more_swords.add(Items.STONE_SWORD);
       registerSwordItem(OAK_DEEPSLATE_SWORD, Items.STICK, "deepslate");
       registerSwordItem(OAK_BLACKSTONE_SWORD, Items.STICK, "blackstone");
        more_weapons.add(Items.COPPER_SWORD); more_swords.add(Items.COPPER_SWORD);
        more_weapons.add(Items.IRON_SWORD); more_swords.add(Items.IRON_SWORD);
        more_weapons.add(Items.GOLDEN_SWORD); more_swords.add(Items.GOLDEN_SWORD);
        more_weapons.add(Items.DIAMOND_SWORD); more_swords.add(Items.DIAMOND_SWORD);
        more_weapons.add(Items.NETHERITE_SWORD); more_swords.add(Items.NETHERITE_SWORD);
        more_weapons.add(Items.WOODEN_SPEAR);
        more_weapons.add(Items.STONE_SPEAR);
       registerSpearItem(OAK_DEEPSLATE_SPEAR, Items.STICK, "deepslate");
       registerSpearItem(OAK_BLACKSTONE_SPEAR, Items.STICK, "blackstone");
        more_weapons.add(Items.COPPER_SPEAR);
        more_weapons.add(Items.IRON_SPEAR);
        more_weapons.add(Items.GOLDEN_SPEAR);
        more_weapons.add(Items.DIAMOND_SPEAR);
        more_weapons.add(Items.NETHERITE_SPEAR);
        more_weapons.add(Items.BOW);
       registerCrossbowItem(OAK_CROSSBOW, Items.STICK);
        more_weapons.add(Items.ARROW);
        more_tippable_arrows.put(Items.ARROW, Items.TIPPED_ARROW);

      //Spruce Weapons
       registerSwordItem(SPRUCE_WOODEN_SWORD, SPRUCE_STICK, "wooden");
       registerSwordItem(SPRUCE_STONE_SWORD, SPRUCE_STICK, "stone");
       registerSwordItem(SPRUCE_DEEPSLATE_SWORD, SPRUCE_STICK, "deepslate");
       registerSwordItem(SPRUCE_BLACKSTONE_SWORD, SPRUCE_STICK, "blackstone");
       registerSwordItem(SPRUCE_COPPER_SWORD, SPRUCE_STICK, "copper");
       registerSwordItem(SPRUCE_IRON_SWORD, SPRUCE_STICK, "iron");
       registerSwordItem(SPRUCE_GOLDEN_SWORD, SPRUCE_STICK, "golden");
       registerSwordItem(SPRUCE_DIAMOND_SWORD, SPRUCE_STICK, "diamond");
       registerSwordItem(SPRUCE_NETHERITE_SWORD, SPRUCE_STICK, "netherite");
       registerSpearItem(SPRUCE_WOODEN_SPEAR, SPRUCE_STICK, "wooden");
       registerSpearItem(SPRUCE_STONE_SPEAR, SPRUCE_STICK, "stone");
       registerSpearItem(SPRUCE_DEEPSLATE_SPEAR, SPRUCE_STICK, "deepslate");
       registerSpearItem(SPRUCE_BLACKSTONE_SPEAR, SPRUCE_STICK, "blackstone");
       registerSpearItem(SPRUCE_COPPER_SPEAR, SPRUCE_STICK, "copper");
       registerSpearItem(SPRUCE_IRON_SPEAR, SPRUCE_STICK, "iron");
       registerSpearItem(SPRUCE_GOLDEN_SPEAR, SPRUCE_STICK, "golden");
       registerSpearItem(SPRUCE_DIAMOND_SPEAR, SPRUCE_STICK, "diamond");
       registerSpearItem(SPRUCE_NETHERITE_SPEAR, SPRUCE_STICK, "netherite");
       registerBowItem(SPRUCE_BOW, SPRUCE_STICK);
       registerCrossbowItem(SPRUCE_CROSSBOW, SPRUCE_STICK);
       registerArrowItem(SPRUCE_ARROW, SPRUCE_STICK, SPRUCE_TIPPED_ARROW);


      //Warped Weapons
       registerSwordItem(WARPED_WOODEN_SWORD, WARPED_STICK, "wooden");
       registerSwordItem(WARPED_STONE_SWORD, WARPED_STICK, "stone");
       registerSwordItem(WARPED_DEEPSLATE_SWORD, WARPED_STICK, "deepslate");
       registerSwordItem(WARPED_BLACKSTONE_SWORD, WARPED_STICK, "blackstone");
       registerSwordItem(WARPED_COPPER_SWORD, WARPED_STICK, "copper");
       registerSwordItem(WARPED_IRON_SWORD, WARPED_STICK, "iron");
       registerSwordItem(WARPED_GOLDEN_SWORD, WARPED_STICK, "golden");
       registerSwordItem(WARPED_DIAMOND_SWORD, WARPED_STICK, "diamond");
       registerSwordItem(WARPED_NETHERITE_SWORD, WARPED_STICK, "netherite");
       registerSpearItem(WARPED_WOODEN_SPEAR, WARPED_STICK, "wooden");
       registerSpearItem(WARPED_STONE_SPEAR, WARPED_STICK, "stone");
       registerSpearItem(WARPED_DEEPSLATE_SPEAR, WARPED_STICK, "deepslate");
       registerSpearItem(WARPED_BLACKSTONE_SPEAR, WARPED_STICK, "blackstone");
       registerSpearItem(WARPED_COPPER_SPEAR, WARPED_STICK, "copper");
       registerSpearItem(WARPED_IRON_SPEAR, WARPED_STICK, "iron");
       registerSpearItem(WARPED_GOLDEN_SPEAR, WARPED_STICK, "golden");
       registerSpearItem(WARPED_DIAMOND_SPEAR, WARPED_STICK, "diamond");
       registerSpearItem(WARPED_NETHERITE_SPEAR, WARPED_STICK, "netherite");
       registerBowItem(WARPED_BOW, WARPED_STICK);
       registerCrossbowItem(WARPED_CROSSBOW, WARPED_STICK);
       registerArrowItem(WARPED_ARROW, WARPED_STICK, WARPED_TIPPED_ARROW);

    }

    private static void registerSwordItem(Item swordItem, Item stickIngredient, String toolMaterialName) {
        more_swords.add(swordItem);
        registerWeaponItem(swordItem, stickIngredient, swordName(stickIngredient, toolMaterialName));
    }
    private static void registerSpearItem(Item spearItem, Item stickIngredient, String toolMaterialName) {
        more_spears.add(spearItem);
        registerWeaponItem(spearItem, stickIngredient, spearName(toolMaterialName, stickIngredient));
    }
    private static void registerBowItem(Item bowItem, Item stickIngredient) {
        more_bows.add(bowItem);
        registerWeaponItem(bowItem, stickIngredient, bowName(stickIngredient));
    }
    private static void registerCrossbowItem(Item crossbowItem, Item stickIngredient) {
        more_crossbows.add(crossbowItem);
        registerWeaponItem(crossbowItem, stickIngredient, crossbowName(stickIngredient));
    }
    private static void registerArrowItem(Item arrowItem, Item stickIngredient, Item tippedArrowItem) {
        more_arrows.add(arrowItem);
        more_arrows.add(tippedArrowItem);
        more_tippable_arrows.put(arrowItem, tippedArrowItem);
        registerWeaponItem(arrowItem, stickIngredient, arrowName(stickIngredient, false));
        registerWeaponItem(tippedArrowItem, stickIngredient, arrowName(stickIngredient, true));
    }
    private static void registerWeaponItem(Item weaponItem, Item stickIngredient, String weaponName) {
        more_weapons.add(weaponItem);
        more_weapon_sticks.put(weaponItem, stickIngredient);
        Registry.register(BuiltInRegistries.ITEM, withModId(weaponName), weaponItem);
    }
}
