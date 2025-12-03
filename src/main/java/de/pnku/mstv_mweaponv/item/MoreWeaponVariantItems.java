package de.pnku.mstv_mweaponv.item;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import de.pnku.mstv_mweaponv.util.IArrow;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.*;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;
import static de.pnku.mstv_mtoolv.item.MoreToolVariantItems.*;
import static de.pnku.mstv_mweaponv.MoreWeaponVariants.*;

import static net.minecraft.world.item.Tiers.*;

public class MoreWeaponVariantItems {
        static int swordAD = 3; static float swordAS = -2.4F;
    
        private static Item.Properties applyFireRes(boolean isFireResWood, Tier tier, Item.Properties properties){
            if (isFireResWood || tier.equals(NETHERITE)) {
                properties.fireResistant();
            }
            return properties;
        }

        private static String getStickName(Item stickIngredient) {
            if (stickIngredient.equals(Items.BAMBOO)) return "bamboo";
            if (stickIngredient.equals(Items.STICK)) return "oak";
            return ((MoreStickVariantItem) stickIngredient).mstvWoodType;
        }

        private static String swordName(SwordItem sword, Item stickIngredient, String tierNameVariant) {
            return swordName(sword.getTier(), stickIngredient, tierNameVariant);
        }

        private static String swordName(Tier tier, Item stickIngredient, String tierNameVariant) {
            String woodType = getStickName(stickIngredient);
            String tierName;
            if (!tierNameVariant.isEmpty()) {
                tierName = tierNameVariant;
            } else if (tier == WOOD) {
                tierName = "wooden";
            } else if (tier == STONE) {
                tierName = "stone";
            } else if (tier == IRON) {
                tierName = "iron";
            } else if (tier == GOLD) {
                tierName = "golden";
            } else if (tier == DIAMOND) {
                tierName = "diamond";
            } else if (tier == NETHERITE) {
                tierName = "netherite";
            } else {
                tierName = "unknown";
            }
            return woodType + "_" + tierName + "_sword";
        }

        private static Item.Properties swordProperties(Tier tier) {
            return swordProperties(tier, false);
        }

        private static Item.Properties swordProperties(Tier tier, boolean isFireResWood) {
            Item.Properties properties = new Item.Properties();
            return applyFireRes(isFireResWood, tier, properties);
        }

        private static String bowName(Item stickIngredient) {
            return getStickName(stickIngredient) + "_bow";
        }

        private static Item.Properties bowProperties() {return bowProperties(false);}

        private static Item.Properties bowProperties(boolean isFireResWood) {
            return applyFireRes(isFireResWood, WOOD, new Item.Properties().durability(384));
        }

        private static String crossbowName(Item stickIngredient) {
            return getStickName(stickIngredient) + "_crossbow";
        }

        private static Item.Properties crossbowProperties() {return crossbowProperties(false);}

        private static Item.Properties crossbowProperties(boolean isFireResWood) {
            return applyFireRes(isFireResWood, WOOD, new Item.Properties()
                    .durability(465));
        }

        private static String arrowName(Item stickIngredient, boolean tipped) {
            return (tipped ? "tipped_" : "") + getStickName(stickIngredient) + ("_arrow");
        }

        private static Item.Properties arrowProperties(boolean isFireResWood) {
            return applyFireRes(isFireResWood, WOOD, new Item.Properties());
        }

            // Swords
            public static final Item ACACIA_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item BAMBOO_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item BIRCH_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item CHERRY_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item CRIMSON_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD, true));
            public static final Item DARK_OAK_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item JUNGLE_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item MANGROVE_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item SPRUCE_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD));
            public static final Item WARPED_WOODEN_SWORD = new SwordItem(Tiers.WOOD, swordAD, swordAS, swordProperties(Tiers.WOOD, true));

                    public static final Item ACACIA_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item BAMBOO_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item BIRCH_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item CHERRY_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item CRIMSON_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE, true));
                    public static final Item DARK_OAK_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item JUNGLE_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item MANGROVE_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item SPRUCE_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item WARPED_STONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE, true));

                    public static final Item ACACIA_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item BAMBOO_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item BIRCH_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item CHERRY_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item CRIMSON_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE, true));
                    public static final Item DARK_OAK_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item JUNGLE_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item MANGROVE_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item OAK_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item SPRUCE_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item WARPED_DEEPSLATE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE, true));

                    public static final Item ACACIA_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item BAMBOO_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item BIRCH_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item CHERRY_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item CRIMSON_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE, true));
                    public static final Item DARK_OAK_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item JUNGLE_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item MANGROVE_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item OAK_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item SPRUCE_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE));
                    public static final Item WARPED_BLACKSTONE_SWORD = new SwordItem(Tiers.STONE, swordAD, swordAS, swordProperties(Tiers.STONE, true));

            public static final Item ACACIA_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item BAMBOO_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item BIRCH_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item CHERRY_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item CRIMSON_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD, true));
            public static final Item DARK_OAK_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item JUNGLE_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item MANGROVE_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item SPRUCE_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD));
            public static final Item WARPED_GOLDEN_SWORD = new SwordItem(Tiers.GOLD, swordAD, swordAS, swordProperties(Tiers.GOLD, true));

            public static final Item ACACIA_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item BAMBOO_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item BIRCH_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item CHERRY_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item CRIMSON_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON, true));
            public static final Item DARK_OAK_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item JUNGLE_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item MANGROVE_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item SPRUCE_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON));
            public static final Item WARPED_IRON_SWORD = new SwordItem(Tiers.IRON, swordAD, swordAS, swordProperties(Tiers.IRON, true));

            public static final Item ACACIA_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item BAMBOO_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item BIRCH_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item CHERRY_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item CRIMSON_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND, true));
            public static final Item DARK_OAK_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item JUNGLE_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item MANGROVE_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item SPRUCE_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND));
            public static final Item WARPED_DIAMOND_SWORD = new SwordItem(Tiers.DIAMOND, swordAD, swordAS, swordProperties(Tiers.DIAMOND, true));

            public static final Item ACACIA_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item BAMBOO_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item BIRCH_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item CHERRY_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item CRIMSON_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE, true));
            public static final Item DARK_OAK_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item JUNGLE_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item MANGROVE_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item SPRUCE_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE));
            public static final Item WARPED_NETHERITE_SWORD = new SwordItem(Tiers.NETHERITE, swordAD, swordAS, swordProperties(Tiers.NETHERITE, true));


            // Bows
            public static final Item ACACIA_BOW = new BowItem(bowProperties());
            public static final Item BAMBOO_BOW = new BowItem(bowProperties());
            public static final Item BIRCH_BOW = new BowItem(bowProperties());
            public static final Item CHERRY_BOW = new BowItem(bowProperties());
            public static final Item CRIMSON_BOW = new BowItem(bowProperties(true));
            public static final Item DARK_OAK_BOW = new BowItem(bowProperties());
            public static final Item JUNGLE_BOW = new BowItem(bowProperties());
            public static final Item MANGROVE_BOW = new BowItem(bowProperties());
            public static final Item SPRUCE_BOW = new BowItem(bowProperties());
            public static final Item WARPED_BOW = new BowItem(bowProperties(true));
            // Crossbows
            public static final Item ACACIA_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item BAMBOO_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item BIRCH_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item CHERRY_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item CRIMSON_CROSSBOW = new CrossbowItem(crossbowProperties(true));
            public static final Item JUNGLE_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item MANGROVE_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item OAK_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item SPRUCE_CROSSBOW = new CrossbowItem(crossbowProperties());
            public static final Item WARPED_CROSSBOW = new CrossbowItem(crossbowProperties(true));
            // Arrows
            public static final Item ACACIA_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item BAMBOO_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item BIRCH_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item CHERRY_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item CRIMSON_ARROW = new ArrowItem(arrowProperties(true));
            public static final Item DARK_OAK_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item JUNGLE_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item MANGROVE_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item SPRUCE_ARROW = new ArrowItem(arrowProperties(false));
            public static final Item WARPED_ARROW = new ArrowItem(arrowProperties(true));
            // Tipped Arrows
            public static final Item ACACIA_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item BAMBOO_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item BIRCH_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item CHERRY_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item CRIMSON_TIPPED_ARROW = new TippedArrowItem(arrowProperties(true));
            public static final Item DARK_OAK_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item JUNGLE_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item MANGROVE_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item SPRUCE_TIPPED_ARROW = new TippedArrowItem(arrowProperties(false));
            public static final Item WARPED_TIPPED_ARROW = new TippedArrowItem(arrowProperties(true));


    public static final List<Item> more_weapons = new ArrayList<>();
    public static final List<Item> more_swords = new ArrayList<>();
    public static final List<Item> more_bows = new ArrayList<>();
    public static final List<Item> more_crossbows = new ArrayList<>();
    public static final List<Item> more_arrows = new ArrayList<>();
    public static final Map<Item, Item> more_weapon_sticks = new HashMap<>();
    public static final Map<Item, Item> more_tippable_arrows = new HashMap<>();

    public static void registerWeaponItems() {

      //Acacia Weapons
       registerSwordItem(ACACIA_WOODEN_SWORD, ACACIA_STICK);
       registerSwordItem(ACACIA_STONE_SWORD, ACACIA_STICK);
       registerSwordItem(ACACIA_DEEPSLATE_SWORD, ACACIA_STICK, "deepslate");
       registerSwordItem(ACACIA_BLACKSTONE_SWORD, ACACIA_STICK, "blackstone");
       registerSwordItem(ACACIA_IRON_SWORD, ACACIA_STICK);
       registerSwordItem(ACACIA_GOLDEN_SWORD, ACACIA_STICK);
       registerSwordItem(ACACIA_DIAMOND_SWORD, ACACIA_STICK);
       registerSwordItem(ACACIA_NETHERITE_SWORD, ACACIA_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(ACACIA_WOODEN_AXE, ACACIA_STONE_AXE, ACACIA_DEEPSLATE_AXE, ACACIA_BLACKSTONE_AXE, ACACIA_IRON_AXE, ACACIA_GOLDEN_AXE, ACACIA_DIAMOND_AXE, ACACIA_NETHERITE_AXE));}
       registerBowItem(ACACIA_BOW, ACACIA_STICK);
       registerCrossbowItem(ACACIA_CROSSBOW, ACACIA_STICK);
       registerArrowItem(ACACIA_ARROW, ACACIA_STICK, ACACIA_TIPPED_ARROW);


        //Bamboo Weapons
       registerSwordItem(BAMBOO_WOODEN_SWORD, Items.BAMBOO);
       registerSwordItem(BAMBOO_STONE_SWORD, Items.BAMBOO);
       registerSwordItem(BAMBOO_DEEPSLATE_SWORD, Items.BAMBOO, "deepslate");
       registerSwordItem(BAMBOO_BLACKSTONE_SWORD, Items.BAMBOO, "blackstone");
       registerSwordItem(BAMBOO_IRON_SWORD, Items.BAMBOO);
       registerSwordItem(BAMBOO_GOLDEN_SWORD, Items.BAMBOO);
       registerSwordItem(BAMBOO_DIAMOND_SWORD, Items.BAMBOO);
       registerSwordItem(BAMBOO_NETHERITE_SWORD, Items.BAMBOO);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(BAMBOO_WOODEN_AXE, BAMBOO_STONE_AXE, BAMBOO_DEEPSLATE_AXE, BAMBOO_BLACKSTONE_AXE, BAMBOO_IRON_AXE, BAMBOO_GOLDEN_AXE, BAMBOO_DIAMOND_AXE, BAMBOO_NETHERITE_AXE));}
       registerBowItem(BAMBOO_BOW, Items.BAMBOO);
       registerCrossbowItem(BAMBOO_CROSSBOW, Items.BAMBOO);
       registerArrowItem(BAMBOO_ARROW, Items.BAMBOO, BAMBOO_TIPPED_ARROW);


      //Birch Weapons
       registerSwordItem(BIRCH_WOODEN_SWORD, BIRCH_STICK);
       registerSwordItem(BIRCH_STONE_SWORD, BIRCH_STICK);
       registerSwordItem(BIRCH_DEEPSLATE_SWORD, BIRCH_STICK, "deepslate");
       registerSwordItem(BIRCH_BLACKSTONE_SWORD, BIRCH_STICK, "blackstone");
       registerSwordItem(BIRCH_IRON_SWORD, BIRCH_STICK);
       registerSwordItem(BIRCH_GOLDEN_SWORD, BIRCH_STICK);
       registerSwordItem(BIRCH_DIAMOND_SWORD, BIRCH_STICK);
       registerSwordItem(BIRCH_NETHERITE_SWORD, BIRCH_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(BIRCH_WOODEN_AXE, BIRCH_STONE_AXE, BIRCH_DEEPSLATE_AXE, BIRCH_BLACKSTONE_AXE, BIRCH_IRON_AXE, BIRCH_GOLDEN_AXE, BIRCH_DIAMOND_AXE, BIRCH_NETHERITE_AXE));}
       registerBowItem(BIRCH_BOW, BIRCH_STICK);
       registerCrossbowItem(BIRCH_CROSSBOW, BIRCH_STICK);
       registerArrowItem(BIRCH_ARROW, BIRCH_STICK, BIRCH_TIPPED_ARROW);


      //Cherry Weapons
       registerSwordItem(CHERRY_WOODEN_SWORD, CHERRY_STICK);
       registerSwordItem(CHERRY_STONE_SWORD, CHERRY_STICK);
       registerSwordItem(CHERRY_DEEPSLATE_SWORD, CHERRY_STICK, "deepslate");
       registerSwordItem(CHERRY_BLACKSTONE_SWORD, CHERRY_STICK, "blackstone");
       registerSwordItem(CHERRY_IRON_SWORD, CHERRY_STICK);
       registerSwordItem(CHERRY_GOLDEN_SWORD, CHERRY_STICK);
       registerSwordItem(CHERRY_DIAMOND_SWORD, CHERRY_STICK);
       registerSwordItem(CHERRY_NETHERITE_SWORD, CHERRY_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(CHERRY_WOODEN_AXE, CHERRY_STONE_AXE, CHERRY_DEEPSLATE_AXE, CHERRY_BLACKSTONE_AXE, CHERRY_IRON_AXE, CHERRY_GOLDEN_AXE, CHERRY_DIAMOND_AXE, CHERRY_NETHERITE_AXE));}
       registerBowItem(CHERRY_BOW, CHERRY_STICK);
       registerCrossbowItem(CHERRY_CROSSBOW, CHERRY_STICK);
       registerArrowItem(CHERRY_ARROW, CHERRY_STICK, CHERRY_TIPPED_ARROW);


      //Crimson Weapons
       registerSwordItem(CRIMSON_WOODEN_SWORD, CRIMSON_STICK);
       registerSwordItem(CRIMSON_STONE_SWORD, CRIMSON_STICK);
       registerSwordItem(CRIMSON_DEEPSLATE_SWORD, CRIMSON_STICK, "deepslate");
       registerSwordItem(CRIMSON_BLACKSTONE_SWORD, CRIMSON_STICK, "blackstone");
       registerSwordItem(CRIMSON_IRON_SWORD, CRIMSON_STICK);
       registerSwordItem(CRIMSON_GOLDEN_SWORD, CRIMSON_STICK);
       registerSwordItem(CRIMSON_DIAMOND_SWORD, CRIMSON_STICK);
       registerSwordItem(CRIMSON_NETHERITE_SWORD, CRIMSON_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(CRIMSON_WOODEN_AXE, CRIMSON_STONE_AXE, CRIMSON_DEEPSLATE_AXE, CRIMSON_BLACKSTONE_AXE, CRIMSON_IRON_AXE, CRIMSON_GOLDEN_AXE, CRIMSON_DIAMOND_AXE, CRIMSON_NETHERITE_AXE));}
       registerBowItem(CRIMSON_BOW, CRIMSON_STICK);
       registerCrossbowItem(CRIMSON_CROSSBOW, CRIMSON_STICK);
       registerArrowItem(CRIMSON_ARROW, CRIMSON_STICK, CRIMSON_TIPPED_ARROW);


      //Dark_oak Weapons
       registerSwordItem(DARK_OAK_WOODEN_SWORD, DARK_OAK_STICK);
       registerSwordItem(DARK_OAK_STONE_SWORD, DARK_OAK_STICK);
       registerSwordItem(DARK_OAK_DEEPSLATE_SWORD, DARK_OAK_STICK, "deepslate");
       registerSwordItem(DARK_OAK_BLACKSTONE_SWORD, DARK_OAK_STICK, "blackstone");
       registerSwordItem(DARK_OAK_IRON_SWORD, DARK_OAK_STICK);
       registerSwordItem(DARK_OAK_GOLDEN_SWORD, DARK_OAK_STICK);
       registerSwordItem(DARK_OAK_DIAMOND_SWORD, DARK_OAK_STICK);
       registerSwordItem(DARK_OAK_NETHERITE_SWORD, DARK_OAK_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(DARK_OAK_WOODEN_AXE, DARK_OAK_STONE_AXE, DARK_OAK_DEEPSLATE_AXE, DARK_OAK_BLACKSTONE_AXE, DARK_OAK_IRON_AXE, DARK_OAK_GOLDEN_AXE, DARK_OAK_DIAMOND_AXE, DARK_OAK_NETHERITE_AXE));}
       registerBowItem(DARK_OAK_BOW, DARK_OAK_STICK);
        more_weapons.add(Items.CROSSBOW);
       registerArrowItem(DARK_OAK_ARROW, DARK_OAK_STICK, DARK_OAK_TIPPED_ARROW);


      //Jungle Weapons
       registerSwordItem(JUNGLE_WOODEN_SWORD, JUNGLE_STICK);
       registerSwordItem(JUNGLE_STONE_SWORD, JUNGLE_STICK);
       registerSwordItem(JUNGLE_DEEPSLATE_SWORD, JUNGLE_STICK, "deepslate");
       registerSwordItem(JUNGLE_BLACKSTONE_SWORD, JUNGLE_STICK, "blackstone");
       registerSwordItem(JUNGLE_IRON_SWORD, JUNGLE_STICK);
       registerSwordItem(JUNGLE_GOLDEN_SWORD, JUNGLE_STICK);
       registerSwordItem(JUNGLE_DIAMOND_SWORD, JUNGLE_STICK);
       registerSwordItem(JUNGLE_NETHERITE_SWORD, JUNGLE_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(JUNGLE_WOODEN_AXE, JUNGLE_STONE_AXE, JUNGLE_DEEPSLATE_AXE, JUNGLE_BLACKSTONE_AXE, JUNGLE_IRON_AXE, JUNGLE_GOLDEN_AXE, JUNGLE_DIAMOND_AXE, JUNGLE_NETHERITE_AXE));}
       registerBowItem(JUNGLE_BOW, JUNGLE_STICK);
       registerCrossbowItem(JUNGLE_CROSSBOW, JUNGLE_STICK);
       registerArrowItem(JUNGLE_ARROW, JUNGLE_STICK, JUNGLE_TIPPED_ARROW);


      //Mangrove Weapons
       registerSwordItem(MANGROVE_WOODEN_SWORD, MANGROVE_STICK);
       registerSwordItem(MANGROVE_STONE_SWORD, MANGROVE_STICK);
       registerSwordItem(MANGROVE_DEEPSLATE_SWORD, MANGROVE_STICK, "deepslate");
       registerSwordItem(MANGROVE_BLACKSTONE_SWORD, MANGROVE_STICK, "blackstone");
       registerSwordItem(MANGROVE_IRON_SWORD, MANGROVE_STICK);
       registerSwordItem(MANGROVE_GOLDEN_SWORD, MANGROVE_STICK);
       registerSwordItem(MANGROVE_DIAMOND_SWORD, MANGROVE_STICK);
       registerSwordItem(MANGROVE_NETHERITE_SWORD, MANGROVE_STICK);
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

      //Spruce Weapons
       registerSwordItem(SPRUCE_WOODEN_SWORD, SPRUCE_STICK);
       registerSwordItem(SPRUCE_STONE_SWORD, SPRUCE_STICK);
       registerSwordItem(SPRUCE_DEEPSLATE_SWORD, SPRUCE_STICK, "deepslate");
       registerSwordItem(SPRUCE_BLACKSTONE_SWORD, SPRUCE_STICK, "blackstone");
       registerSwordItem(SPRUCE_IRON_SWORD, SPRUCE_STICK);
       registerSwordItem(SPRUCE_GOLDEN_SWORD, SPRUCE_STICK);
       registerSwordItem(SPRUCE_DIAMOND_SWORD, SPRUCE_STICK);
       registerSwordItem(SPRUCE_NETHERITE_SWORD, SPRUCE_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(SPRUCE_WOODEN_AXE, SPRUCE_STONE_AXE, SPRUCE_DEEPSLATE_AXE, SPRUCE_BLACKSTONE_AXE, SPRUCE_IRON_AXE, SPRUCE_GOLDEN_AXE, SPRUCE_DIAMOND_AXE, SPRUCE_NETHERITE_AXE));}
       registerBowItem(SPRUCE_BOW, SPRUCE_STICK);
       registerCrossbowItem(SPRUCE_CROSSBOW, SPRUCE_STICK);
       registerArrowItem(SPRUCE_ARROW, SPRUCE_STICK, SPRUCE_TIPPED_ARROW);


      //Warped Weapons
       registerSwordItem(WARPED_WOODEN_SWORD, WARPED_STICK);
       registerSwordItem(WARPED_STONE_SWORD, WARPED_STICK);
       registerSwordItem(WARPED_DEEPSLATE_SWORD, WARPED_STICK, "deepslate");
       registerSwordItem(WARPED_BLACKSTONE_SWORD, WARPED_STICK, "blackstone");
       registerSwordItem(WARPED_IRON_SWORD, WARPED_STICK);
       registerSwordItem(WARPED_GOLDEN_SWORD, WARPED_STICK);
       registerSwordItem(WARPED_DIAMOND_SWORD, WARPED_STICK);
       registerSwordItem(WARPED_NETHERITE_SWORD, WARPED_STICK);
       if (isMtoolvLoaded) {more_weapons.addAll(Set.of(WARPED_WOODEN_AXE, WARPED_STONE_AXE, WARPED_DEEPSLATE_AXE, WARPED_BLACKSTONE_AXE, WARPED_IRON_AXE, WARPED_GOLDEN_AXE, WARPED_DIAMOND_AXE, WARPED_NETHERITE_AXE));}
       registerBowItem(WARPED_BOW, WARPED_STICK);
       registerCrossbowItem(WARPED_CROSSBOW, WARPED_STICK);
       registerArrowItem(WARPED_ARROW, WARPED_STICK, WARPED_TIPPED_ARROW);

    }

    private static void registerSwordItem(Item swordItem, Item stickIngredient) {registerSwordItem(swordItem, stickIngredient, "");}
    
    private static void registerSwordItem(Item swordItem, Item stickIngredient, String tierVariant) {
        more_swords.add(swordItem);
        registerWeaponItem(swordItem, stickIngredient, swordName((SwordItem) swordItem, stickIngredient, tierVariant));
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

    public static void registerDispenseArrowBehavior(Item arrowItem){
        registerDispenseArrowBehavior(arrowItem, false);
    }
    public static void registerDispenseArrowBehavior(Item arrowItem, boolean isTipped){
        DispenserBlock.registerBehavior(arrowItem, new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level level, Position position, ItemStack ammo) {
                Arrow arrow = new Arrow(level, position.x(), position.y(), position.z());
                Item arrowVariantItem = ammo.getItem();
                Item stickItem;
                String arrowVariant;
                if (more_arrows.contains(arrowVariantItem)) {
                    stickItem = more_weapon_sticks.get(arrowVariantItem);
                    if (stickItem.equals(Items.BAMBOO)) {
                        arrowVariant = "bamboo";
                    } else if (stickItem.equals(Items.STICK)) {
                        arrowVariant = "oak";
                    } else {
                        arrowVariant = ((MoreStickVariantItem) stickItem).mstvWoodType;
                    }
                    ((IArrow) arrow).mweaponv$setVariant(arrowVariant);
                }
                if (isTipped) {arrow.setEffectsFromItem(ammo);}
                arrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return arrow;}
        });
    }

}
