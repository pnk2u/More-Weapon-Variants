package de.pnku.mstv_mweaponv.item;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.MoreWeaponVariants;
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
import static de.pnku.mstv_mtoolv.item.MoreToolVariantItems.*;
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
       registerSwordItem(ACACIA_IRON_SWORD, ACACIA_STICK, "iron");
       registerSwordItem(ACACIA_GOLDEN_SWORD, ACACIA_STICK, "golden");
       registerSwordItem(ACACIA_DIAMOND_SWORD, ACACIA_STICK, "diamond");
       registerSwordItem(ACACIA_NETHERITE_SWORD, ACACIA_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(ACACIA_WOODEN_AXE, ACACIA_STONE_AXE, ACACIA_DEEPSLATE_AXE, ACACIA_BLACKSTONE_AXE, ACACIA_IRON_AXE, ACACIA_GOLDEN_AXE, ACACIA_DIAMOND_AXE, ACACIA_NETHERITE_AXE));}
       registerBowItem(ACACIA_BOW, ACACIA_STICK);
       registerCrossbowItem(ACACIA_CROSSBOW, ACACIA_STICK);
       registerArrowItem(ACACIA_ARROW, ACACIA_STICK, ACACIA_TIPPED_ARROW);


        //Bamboo Weapons
       registerSwordItem(BAMBOO_WOODEN_SWORD, Items.BAMBOO, "wooden");
       registerSwordItem(BAMBOO_STONE_SWORD, Items.BAMBOO, "stone");
       registerSwordItem(BAMBOO_DEEPSLATE_SWORD, Items.BAMBOO, "deepslate");
       registerSwordItem(BAMBOO_BLACKSTONE_SWORD, Items.BAMBOO, "blackstone");
       registerSwordItem(BAMBOO_IRON_SWORD, Items.BAMBOO, "iron");
       registerSwordItem(BAMBOO_GOLDEN_SWORD, Items.BAMBOO, "golden");
       registerSwordItem(BAMBOO_DIAMOND_SWORD, Items.BAMBOO, "diamond");
       registerSwordItem(BAMBOO_NETHERITE_SWORD, Items.BAMBOO, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(BAMBOO_WOODEN_AXE, BAMBOO_STONE_AXE, BAMBOO_DEEPSLATE_AXE, BAMBOO_BLACKSTONE_AXE, BAMBOO_IRON_AXE, BAMBOO_GOLDEN_AXE, BAMBOO_DIAMOND_AXE, BAMBOO_NETHERITE_AXE));}
       registerBowItem(BAMBOO_BOW, Items.BAMBOO);
       registerCrossbowItem(BAMBOO_CROSSBOW, Items.BAMBOO);
       registerArrowItem(BAMBOO_ARROW, Items.BAMBOO, BAMBOO_TIPPED_ARROW);


      //Birch Weapons
       registerSwordItem(BIRCH_WOODEN_SWORD, BIRCH_STICK, "wooden");
       registerSwordItem(BIRCH_STONE_SWORD, BIRCH_STICK, "stone");
       registerSwordItem(BIRCH_DEEPSLATE_SWORD, BIRCH_STICK, "deepslate");
       registerSwordItem(BIRCH_BLACKSTONE_SWORD, BIRCH_STICK, "blackstone");
       registerSwordItem(BIRCH_IRON_SWORD, BIRCH_STICK, "iron");
       registerSwordItem(BIRCH_GOLDEN_SWORD, BIRCH_STICK, "golden");
       registerSwordItem(BIRCH_DIAMOND_SWORD, BIRCH_STICK, "diamond");
       registerSwordItem(BIRCH_NETHERITE_SWORD, BIRCH_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(BIRCH_WOODEN_AXE, BIRCH_STONE_AXE, BIRCH_DEEPSLATE_AXE, BIRCH_BLACKSTONE_AXE, BIRCH_IRON_AXE, BIRCH_GOLDEN_AXE, BIRCH_DIAMOND_AXE, BIRCH_NETHERITE_AXE));}
       registerBowItem(BIRCH_BOW, BIRCH_STICK);
       registerCrossbowItem(BIRCH_CROSSBOW, BIRCH_STICK);
       registerArrowItem(BIRCH_ARROW, BIRCH_STICK, BIRCH_TIPPED_ARROW);


      //Cherry Weapons
       registerSwordItem(CHERRY_WOODEN_SWORD, CHERRY_STICK, "wooden");
       registerSwordItem(CHERRY_STONE_SWORD, CHERRY_STICK, "stone");
       registerSwordItem(CHERRY_DEEPSLATE_SWORD, CHERRY_STICK, "deepslate");
       registerSwordItem(CHERRY_BLACKSTONE_SWORD, CHERRY_STICK, "blackstone");
       registerSwordItem(CHERRY_IRON_SWORD, CHERRY_STICK, "iron");
       registerSwordItem(CHERRY_GOLDEN_SWORD, CHERRY_STICK, "golden");
       registerSwordItem(CHERRY_DIAMOND_SWORD, CHERRY_STICK, "diamond");
       registerSwordItem(CHERRY_NETHERITE_SWORD, CHERRY_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(CHERRY_WOODEN_AXE, CHERRY_STONE_AXE, CHERRY_DEEPSLATE_AXE, CHERRY_BLACKSTONE_AXE, CHERRY_IRON_AXE, CHERRY_GOLDEN_AXE, CHERRY_DIAMOND_AXE, CHERRY_NETHERITE_AXE));}
       registerBowItem(CHERRY_BOW, CHERRY_STICK);
       registerCrossbowItem(CHERRY_CROSSBOW, CHERRY_STICK);
       registerArrowItem(CHERRY_ARROW, CHERRY_STICK, CHERRY_TIPPED_ARROW);


      //Crimson Weapons
       registerSwordItem(CRIMSON_WOODEN_SWORD, CRIMSON_STICK, "wooden");
       registerSwordItem(CRIMSON_STONE_SWORD, CRIMSON_STICK, "stone");
       registerSwordItem(CRIMSON_DEEPSLATE_SWORD, CRIMSON_STICK, "deepslate");
       registerSwordItem(CRIMSON_BLACKSTONE_SWORD, CRIMSON_STICK, "blackstone");
       registerSwordItem(CRIMSON_IRON_SWORD, CRIMSON_STICK, "iron");
       registerSwordItem(CRIMSON_GOLDEN_SWORD, CRIMSON_STICK, "golden");
       registerSwordItem(CRIMSON_DIAMOND_SWORD, CRIMSON_STICK, "diamond");
       registerSwordItem(CRIMSON_NETHERITE_SWORD, CRIMSON_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(CRIMSON_WOODEN_AXE, CRIMSON_STONE_AXE, CRIMSON_DEEPSLATE_AXE, CRIMSON_BLACKSTONE_AXE, CRIMSON_IRON_AXE, CRIMSON_GOLDEN_AXE, CRIMSON_DIAMOND_AXE, CRIMSON_NETHERITE_AXE));}
       registerBowItem(CRIMSON_BOW, CRIMSON_STICK);
       registerCrossbowItem(CRIMSON_CROSSBOW, CRIMSON_STICK);
       registerArrowItem(CRIMSON_ARROW, CRIMSON_STICK, CRIMSON_TIPPED_ARROW);


      //Dark_oak Weapons
       registerSwordItem(DARK_OAK_WOODEN_SWORD, DARK_OAK_STICK, "wooden");
       registerSwordItem(DARK_OAK_STONE_SWORD, DARK_OAK_STICK, "stone");
       registerSwordItem(DARK_OAK_DEEPSLATE_SWORD, DARK_OAK_STICK, "deepslate");
       registerSwordItem(DARK_OAK_BLACKSTONE_SWORD, DARK_OAK_STICK, "blackstone");
       registerSwordItem(DARK_OAK_IRON_SWORD, DARK_OAK_STICK, "iron");
       registerSwordItem(DARK_OAK_GOLDEN_SWORD, DARK_OAK_STICK, "golden");
       registerSwordItem(DARK_OAK_DIAMOND_SWORD, DARK_OAK_STICK, "diamond");
       registerSwordItem(DARK_OAK_NETHERITE_SWORD, DARK_OAK_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(DARK_OAK_WOODEN_AXE, DARK_OAK_STONE_AXE, DARK_OAK_DEEPSLATE_AXE, DARK_OAK_BLACKSTONE_AXE, DARK_OAK_IRON_AXE, DARK_OAK_GOLDEN_AXE, DARK_OAK_DIAMOND_AXE, DARK_OAK_NETHERITE_AXE));}
       registerBowItem(DARK_OAK_BOW, DARK_OAK_STICK);
        more_weapons.add(Items.CROSSBOW);
       registerArrowItem(DARK_OAK_ARROW, DARK_OAK_STICK, DARK_OAK_TIPPED_ARROW);


        //Pale_oak Weapons
        registerSwordItem(PALE_OAK_WOODEN_SWORD, PALE_OAK_STICK, "wooden");
        registerSwordItem(PALE_OAK_STONE_SWORD, PALE_OAK_STICK, "stone");
        registerSwordItem(PALE_OAK_DEEPSLATE_SWORD, PALE_OAK_STICK, "deepslate");
        registerSwordItem(PALE_OAK_BLACKSTONE_SWORD, PALE_OAK_STICK, "blackstone");
        registerSwordItem(PALE_OAK_IRON_SWORD, PALE_OAK_STICK, "iron");
        registerSwordItem(PALE_OAK_GOLDEN_SWORD, PALE_OAK_STICK, "golden");
        registerSwordItem(PALE_OAK_DIAMOND_SWORD, PALE_OAK_STICK, "diamond");
        registerSwordItem(PALE_OAK_NETHERITE_SWORD, PALE_OAK_STICK, "netherite");
        if(isMtoolvLoaded){more_weapons.add(PALE_OAK_WOODEN_AXE); more_weapons.add(PALE_OAK_STONE_AXE); more_weapons.add(PALE_OAK_DEEPSLATE_AXE); more_weapons.add(PALE_OAK_BLACKSTONE_AXE); more_weapons.add(PALE_OAK_IRON_AXE); more_weapons.add(PALE_OAK_GOLDEN_AXE); more_weapons.add(PALE_OAK_DIAMOND_AXE); more_weapons.add(PALE_OAK_NETHERITE_AXE);}
        registerBowItem(PALE_OAK_BOW, PALE_OAK_STICK);
        registerCrossbowItem(PALE_OAK_CROSSBOW, PALE_OAK_STICK);
        registerArrowItem(PALE_OAK_ARROW, PALE_OAK_STICK, PALE_OAK_TIPPED_ARROW);


      //Jungle Weapons
       registerSwordItem(JUNGLE_WOODEN_SWORD, JUNGLE_STICK, "wooden");
       registerSwordItem(JUNGLE_STONE_SWORD, JUNGLE_STICK, "stone");
       registerSwordItem(JUNGLE_DEEPSLATE_SWORD, JUNGLE_STICK, "deepslate");
       registerSwordItem(JUNGLE_BLACKSTONE_SWORD, JUNGLE_STICK, "blackstone");
       registerSwordItem(JUNGLE_IRON_SWORD, JUNGLE_STICK, "iron");
       registerSwordItem(JUNGLE_GOLDEN_SWORD, JUNGLE_STICK, "golden");
       registerSwordItem(JUNGLE_DIAMOND_SWORD, JUNGLE_STICK, "diamond");
       registerSwordItem(JUNGLE_NETHERITE_SWORD, JUNGLE_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(JUNGLE_WOODEN_AXE, JUNGLE_STONE_AXE, JUNGLE_DEEPSLATE_AXE, JUNGLE_BLACKSTONE_AXE, JUNGLE_IRON_AXE, JUNGLE_GOLDEN_AXE, JUNGLE_DIAMOND_AXE, JUNGLE_NETHERITE_AXE));}
       registerBowItem(JUNGLE_BOW, JUNGLE_STICK);
       registerCrossbowItem(JUNGLE_CROSSBOW, JUNGLE_STICK);
       registerArrowItem(JUNGLE_ARROW, JUNGLE_STICK, JUNGLE_TIPPED_ARROW);


      //Mangrove Weapons
       registerSwordItem(MANGROVE_WOODEN_SWORD, MANGROVE_STICK, "wooden");
       registerSwordItem(MANGROVE_STONE_SWORD, MANGROVE_STICK, "stone");
       registerSwordItem(MANGROVE_DEEPSLATE_SWORD, MANGROVE_STICK, "deepslate");
       registerSwordItem(MANGROVE_BLACKSTONE_SWORD, MANGROVE_STICK, "blackstone");
       registerSwordItem(MANGROVE_IRON_SWORD, MANGROVE_STICK, "iron");
       registerSwordItem(MANGROVE_GOLDEN_SWORD, MANGROVE_STICK, "golden");
       registerSwordItem(MANGROVE_DIAMOND_SWORD, MANGROVE_STICK, "diamond");
       registerSwordItem(MANGROVE_NETHERITE_SWORD, MANGROVE_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(MANGROVE_WOODEN_AXE, MANGROVE_STONE_AXE, MANGROVE_DEEPSLATE_AXE, MANGROVE_BLACKSTONE_AXE, MANGROVE_IRON_AXE, MANGROVE_GOLDEN_AXE, MANGROVE_DIAMOND_AXE, MANGROVE_NETHERITE_AXE));}
       registerBowItem(MANGROVE_BOW, MANGROVE_STICK);
       registerCrossbowItem(MANGROVE_CROSSBOW, MANGROVE_STICK);
       registerArrowItem(MANGROVE_ARROW, MANGROVE_STICK, MANGROVE_TIPPED_ARROW);


      //Oak Weapons
        more_weapons.add(Items.WOODEN_SWORD);
        more_weapons.add(Items.STONE_SWORD);
       registerSwordItem(OAK_DEEPSLATE_SWORD, Items.STICK, "deepslate");
       registerSwordItem(OAK_BLACKSTONE_SWORD, Items.STICK, "blackstone");
        more_weapons.add(Items.IRON_SWORD);
        more_weapons.add(Items.GOLDEN_SWORD);
        more_weapons.add(Items.DIAMOND_SWORD);
        more_weapons.add(Items.NETHERITE_SWORD);
        if (isMtoolvLoaded) {more_weapons.addAll(Set.of(Items.WOODEN_AXE, Items.STONE_AXE, OAK_DEEPSLATE_AXE, OAK_BLACKSTONE_AXE, Items.IRON_AXE, Items.GOLDEN_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE));}
        more_weapons.add(Items.BOW);
       registerCrossbowItem(OAK_CROSSBOW, Items.STICK);
        more_weapons.add(Items.ARROW);
        more_tippable_arrows.put(Items.ARROW, Items.TIPPED_ARROW);

      //Spruce Weapons
       registerSwordItem(SPRUCE_WOODEN_SWORD, SPRUCE_STICK, "wooden");
       registerSwordItem(SPRUCE_STONE_SWORD, SPRUCE_STICK, "stone");
       registerSwordItem(SPRUCE_DEEPSLATE_SWORD, SPRUCE_STICK, "deepslate");
       registerSwordItem(SPRUCE_BLACKSTONE_SWORD, SPRUCE_STICK, "blackstone");
       registerSwordItem(SPRUCE_IRON_SWORD, SPRUCE_STICK, "iron");
       registerSwordItem(SPRUCE_GOLDEN_SWORD, SPRUCE_STICK, "golden");
       registerSwordItem(SPRUCE_DIAMOND_SWORD, SPRUCE_STICK, "diamond");
       registerSwordItem(SPRUCE_NETHERITE_SWORD, SPRUCE_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(SPRUCE_WOODEN_AXE, SPRUCE_STONE_AXE, SPRUCE_DEEPSLATE_AXE, SPRUCE_BLACKSTONE_AXE, SPRUCE_IRON_AXE, SPRUCE_GOLDEN_AXE, SPRUCE_DIAMOND_AXE, SPRUCE_NETHERITE_AXE));}
       registerBowItem(SPRUCE_BOW, SPRUCE_STICK);
       registerCrossbowItem(SPRUCE_CROSSBOW, SPRUCE_STICK);
       registerArrowItem(SPRUCE_ARROW, SPRUCE_STICK, SPRUCE_TIPPED_ARROW);


      //Warped Weapons
       registerSwordItem(WARPED_WOODEN_SWORD, WARPED_STICK, "wooden");
       registerSwordItem(WARPED_STONE_SWORD, WARPED_STICK, "stone");
       registerSwordItem(WARPED_DEEPSLATE_SWORD, WARPED_STICK, "deepslate");
       registerSwordItem(WARPED_BLACKSTONE_SWORD, WARPED_STICK, "blackstone");
       registerSwordItem(WARPED_IRON_SWORD, WARPED_STICK, "iron");
       registerSwordItem(WARPED_GOLDEN_SWORD, WARPED_STICK, "golden");
       registerSwordItem(WARPED_DIAMOND_SWORD, WARPED_STICK, "diamond");
       registerSwordItem(WARPED_NETHERITE_SWORD, WARPED_STICK, "netherite");
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(WARPED_WOODEN_AXE, WARPED_STONE_AXE, WARPED_DEEPSLATE_AXE, WARPED_BLACKSTONE_AXE, WARPED_IRON_AXE, WARPED_GOLDEN_AXE, WARPED_DIAMOND_AXE, WARPED_NETHERITE_AXE));}
       registerBowItem(WARPED_BOW, WARPED_STICK);
       registerCrossbowItem(WARPED_CROSSBOW, WARPED_STICK);
       registerArrowItem(WARPED_ARROW, WARPED_STICK, WARPED_TIPPED_ARROW);

    }

    private static void registerSwordItem(Item swordItem, Item stickIngredient, String toolMaterialName) {
        more_swords.add(swordItem);
        registerWeaponItem(swordItem, stickIngredient, swordName(stickIngredient, toolMaterialName));
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
        Registry.register(BuiltInRegistries.ITEM, MoreWeaponVariants.withModId(weaponName), weaponItem);
    }
}
