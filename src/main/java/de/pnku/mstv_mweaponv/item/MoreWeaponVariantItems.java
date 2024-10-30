package de.pnku.mstv_mweaponv.item;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ChargedProjectiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;
import static de.pnku.mstv_mtoolv.item.MoreToolVariantItems.*;
import static de.pnku.mstv_mweaponv.MoreWeaponVariants.*;

import static net.minecraft.world.item.Tiers.*;

public class MoreWeaponVariantItems {


    public static SwordItem createSwordVariantItem (Tier tier, String woodType){
        int AD = 3; float AS = -2.4F;
        Item.Properties swordVariantProperties = (new Item.Properties()).attributes(SwordItem.createAttributes(tier, AD, AS));
        if (woodType.matches("crimson|warped") || tier.equals(NETHERITE)) {
            swordVariantProperties.fireResistant();
        }
        SwordItem swordItem = new SwordItem(tier, swordVariantProperties);
        more_sword_tiers.put(swordItem, tier);
        return swordItem;
    }

    public static BowItem createBowVariantItem (String woodType){
        Item.Properties bowVariantProperties = (new Item.Properties()).durability(384);
        if (woodType.matches("crimson|warped")) {
            bowVariantProperties.fireResistant();
        }
        return new BowItem(bowVariantProperties);
    }

    public static CrossbowItem createCrossbowVariantItem (String woodType){
        Item.Properties crossbowVariantProperties = (new Item.Properties()).durability(465).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
        if (woodType.matches("crimson|warped")) {
            crossbowVariantProperties.fireResistant();
        }
        return new CrossbowItem(crossbowVariantProperties);
    }

    public static ArrowItem createArrowVariantItem(String woodType){
        return createArrowVariantItem(woodType, false);
    }

    public static ArrowItem createArrowVariantItem (String woodType, Boolean isTipped){
        Item.Properties arrowVariantProperties = (new Item.Properties());
        if (isTipped) {arrowVariantProperties.component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);}
        if (woodType.matches("crimson|warped")) {
            arrowVariantProperties.fireResistant();
        }
        if (isTipped) {
            return new TippedArrowItem(arrowVariantProperties);
        } else {
            return new ArrowItem(arrowVariantProperties);
        }
    }

    public static final Map<Item, Tier> more_sword_tiers = new HashMap<>();

            // Swords
            public static final Item ACACIA_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "acacia");
            public static final Item BAMBOO_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "bamboo");
            public static final Item BIRCH_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "birch");
            public static final Item CHERRY_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "cherry");
            public static final Item CRIMSON_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "crimson");
            public static final Item DARK_OAK_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "dark_oak");
            public static final Item JUNGLE_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "jungle");
            public static final Item MANGROVE_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "mangrove");
            public static final Item SPRUCE_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "spruce");
            public static final Item WARPED_WOODEN_SWORD = createSwordVariantItem(Tiers.WOOD, "warped");

                    public static final Item ACACIA_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "acacia");
                    public static final Item BAMBOO_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "bamboo");
                    public static final Item BIRCH_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "birch");
                    public static final Item CHERRY_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "cherry");
                    public static final Item CRIMSON_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "crimson");
                    public static final Item DARK_OAK_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "dark_oak");
                    public static final Item JUNGLE_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "jungle");
                    public static final Item MANGROVE_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "mangrove");
                    public static final Item SPRUCE_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "spruce");
                    public static final Item WARPED_STONE_SWORD = createSwordVariantItem(Tiers.STONE, "warped");

                    public static final Item ACACIA_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "acacia");
                    public static final Item BAMBOO_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "bamboo");
                    public static final Item BIRCH_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "birch");
                    public static final Item CHERRY_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "cherry");
                    public static final Item CRIMSON_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "crimson");
                    public static final Item DARK_OAK_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "dark_oak");
                    public static final Item JUNGLE_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "jungle");
                    public static final Item MANGROVE_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "mangrove");
                    public static final Item OAK_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "oak");
                    public static final Item SPRUCE_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "spruce");
                    public static final Item WARPED_DEEPSLATE_SWORD = createSwordVariantItem(Tiers.STONE, "warped");

                    public static final Item ACACIA_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "acacia");
                    public static final Item BAMBOO_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "bamboo");
                    public static final Item BIRCH_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "birch");
                    public static final Item CHERRY_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "cherry");
                    public static final Item CRIMSON_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "crimson");
                    public static final Item DARK_OAK_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "dark_oak");
                    public static final Item JUNGLE_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "jungle");
                    public static final Item MANGROVE_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "mangrove");
                    public static final Item OAK_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "oak");
                    public static final Item SPRUCE_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "spruce");
                    public static final Item WARPED_BLACKSTONE_SWORD = createSwordVariantItem(Tiers.STONE, "warped");

            public static final Item ACACIA_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "acacia");
            public static final Item BAMBOO_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "bamboo");
            public static final Item BIRCH_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "birch");
            public static final Item CHERRY_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "cherry");
            public static final Item CRIMSON_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "crimson");
            public static final Item DARK_OAK_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "dark_oak");
            public static final Item JUNGLE_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "jungle");
            public static final Item MANGROVE_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "mangrove");
            public static final Item SPRUCE_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "spruce");
            public static final Item WARPED_GOLDEN_SWORD = createSwordVariantItem(Tiers.GOLD, "warped");

            public static final Item ACACIA_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "acacia");
            public static final Item BAMBOO_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "bamboo");
            public static final Item BIRCH_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "birch");
            public static final Item CHERRY_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "cherry");
            public static final Item CRIMSON_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "crimson");
            public static final Item DARK_OAK_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "dark_oak");
            public static final Item JUNGLE_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "jungle");
            public static final Item MANGROVE_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "mangrove");
            public static final Item SPRUCE_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "spruce");
            public static final Item WARPED_IRON_SWORD = createSwordVariantItem(Tiers.IRON, "warped");

            public static final Item ACACIA_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "acacia");
            public static final Item BAMBOO_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "bamboo");
            public static final Item BIRCH_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "birch");
            public static final Item CHERRY_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "cherry");
            public static final Item CRIMSON_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "crimson");
            public static final Item DARK_OAK_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "dark_oak");
            public static final Item JUNGLE_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "jungle");
            public static final Item MANGROVE_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "mangrove");
            public static final Item SPRUCE_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "spruce");
            public static final Item WARPED_DIAMOND_SWORD = createSwordVariantItem(Tiers.DIAMOND, "warped");

            public static final Item ACACIA_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "acacia");
            public static final Item BAMBOO_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "bamboo");
            public static final Item BIRCH_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "birch");
            public static final Item CHERRY_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "cherry");
            public static final Item CRIMSON_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "crimson");
            public static final Item DARK_OAK_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "dark_oak");
            public static final Item JUNGLE_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "jungle");
            public static final Item MANGROVE_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "mangrove");
            public static final Item SPRUCE_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "spruce");
            public static final Item WARPED_NETHERITE_SWORD = createSwordVariantItem(Tiers.NETHERITE, "warped");


            // Bows
            public static final Item ACACIA_BOW = createBowVariantItem("acacia");
            public static final Item BAMBOO_BOW = createBowVariantItem("bamboo");
            public static final Item BIRCH_BOW = createBowVariantItem("birch");
            public static final Item CHERRY_BOW = createBowVariantItem("cherry");
            public static final Item CRIMSON_BOW = createBowVariantItem("crimson");
            public static final Item DARK_OAK_BOW = createBowVariantItem("dark_oak");
            public static final Item JUNGLE_BOW = createBowVariantItem("jungle");
            public static final Item MANGROVE_BOW = createBowVariantItem("mangrove");
            public static final Item SPRUCE_BOW = createBowVariantItem("spruce");
            public static final Item WARPED_BOW = createBowVariantItem("warped");
            // Crossbows
            public static final Item ACACIA_CROSSBOW = createCrossbowVariantItem("acacia");
            public static final Item BAMBOO_CROSSBOW = createCrossbowVariantItem("bamboo");
            public static final Item BIRCH_CROSSBOW = createCrossbowVariantItem("birch");
            public static final Item CHERRY_CROSSBOW = createCrossbowVariantItem("cherry");
            public static final Item CRIMSON_CROSSBOW = createCrossbowVariantItem("crimson");
            public static final Item JUNGLE_CROSSBOW = createCrossbowVariantItem("jungle");
            public static final Item MANGROVE_CROSSBOW = createCrossbowVariantItem("mangrove");
            public static final Item OAK_CROSSBOW = createCrossbowVariantItem("oak");
            public static final Item SPRUCE_CROSSBOW = createCrossbowVariantItem("spruce");
            public static final Item WARPED_CROSSBOW = createCrossbowVariantItem("warped");
            // Arrows
            public static final Item ACACIA_ARROW = createArrowVariantItem("acacia");
            public static final Item BAMBOO_ARROW = createArrowVariantItem("bamboo");
            public static final Item BIRCH_ARROW = createArrowVariantItem("birch");
            public static final Item CHERRY_ARROW = createArrowVariantItem("cherry");
            public static final Item CRIMSON_ARROW = createArrowVariantItem("crimson");
            public static final Item DARK_OAK_ARROW = createArrowVariantItem("dark_oak");
            public static final Item JUNGLE_ARROW = createArrowVariantItem("jungle");
            public static final Item MANGROVE_ARROW = createArrowVariantItem("mangrove");
            public static final Item SPRUCE_ARROW = createArrowVariantItem("spruce");
            public static final Item WARPED_ARROW = createArrowVariantItem("warped");
            // Tipped Arrows
            public static final Item ACACIA_TIPPED_ARROW = createArrowVariantItem("acacia", true);
            public static final Item BAMBOO_TIPPED_ARROW = createArrowVariantItem("bamboo", true);
            public static final Item BIRCH_TIPPED_ARROW = createArrowVariantItem("birch", true);
            public static final Item CHERRY_TIPPED_ARROW = createArrowVariantItem("cherry", true);
            public static final Item CRIMSON_TIPPED_ARROW = createArrowVariantItem("crimson", true);
            public static final Item DARK_OAK_TIPPED_ARROW = createArrowVariantItem("dark_oak", true);
            public static final Item JUNGLE_TIPPED_ARROW = createArrowVariantItem("jungle", true);
            public static final Item MANGROVE_TIPPED_ARROW = createArrowVariantItem("mangrove", true);
            public static final Item SPRUCE_TIPPED_ARROW = createArrowVariantItem("spruce", true);
            public static final Item WARPED_TIPPED_ARROW = createArrowVariantItem("warped", true);


    public static final List<Item> more_weapons = new ArrayList<>();
    public static final List<Item> more_swords = new ArrayList<>();
    public static final List<Item> more_bows = new ArrayList<>();
    public static final List<Item> more_crossbows = new ArrayList<>();
    public static final List<Item> more_arrows = new ArrayList<>();
    public static final Map<Item, Item> more_weapon_sticks = new HashMap<>();
    public static final Map<Item, Item> more_blade_ingredients = new HashMap<>();
    public static final Map<Item, Item> more_tippable_arrows = new HashMap<>();

    public static void registerWeaponItems() {

      //Acacia Weapons
       registerSwordItem(ACACIA_WOODEN_SWORD, ACACIA_STICK, Items.ACACIA_PLANKS, "wooden");
       registerSwordItem(ACACIA_STONE_SWORD, ACACIA_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(ACACIA_DEEPSLATE_SWORD, ACACIA_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(ACACIA_BLACKSTONE_SWORD, ACACIA_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(ACACIA_IRON_SWORD, ACACIA_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(ACACIA_GOLDEN_SWORD, ACACIA_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(ACACIA_DIAMOND_SWORD, ACACIA_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(ACACIA_NETHERITE_SWORD, ACACIA_STICK, ACACIA_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(ACACIA_WOODEN_AXE); more_weapons.add(ACACIA_STONE_AXE); more_weapons.add(ACACIA_DEEPSLATE_AXE); more_weapons.add(ACACIA_BLACKSTONE_AXE); more_weapons.add(ACACIA_IRON_AXE); more_weapons.add(ACACIA_GOLDEN_AXE); more_weapons.add(ACACIA_DIAMOND_AXE); more_weapons.add(ACACIA_NETHERITE_AXE);}
        registerBowItem(ACACIA_BOW, ACACIA_STICK);
        registerCrossbowItem(ACACIA_CROSSBOW, ACACIA_STICK);
        registerArrowItem(ACACIA_ARROW, ACACIA_STICK, ACACIA_TIPPED_ARROW);


        //Bamboo Weapons
       registerSwordItem(BAMBOO_WOODEN_SWORD, Items.BAMBOO, Items.BAMBOO_PLANKS, "wooden");
       registerSwordItem(BAMBOO_STONE_SWORD, Items.BAMBOO, Items.COBBLESTONE, "stone");
       registerSwordItem(BAMBOO_DEEPSLATE_SWORD, Items.BAMBOO, Items.DEEPSLATE, "deepslate");
       registerSwordItem(BAMBOO_BLACKSTONE_SWORD, Items.BAMBOO, Items.BLACKSTONE, "blackstone");
       registerSwordItem(BAMBOO_IRON_SWORD, Items.BAMBOO, Items.IRON_INGOT, "iron");
       registerSwordItem(BAMBOO_GOLDEN_SWORD, Items.BAMBOO, Items.GOLD_INGOT, "golden");
       registerSwordItem(BAMBOO_DIAMOND_SWORD, Items.BAMBOO, Items.DIAMOND, "diamond");
       registerSwordItem(BAMBOO_NETHERITE_SWORD, Items.BAMBOO, BAMBOO_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(BAMBOO_WOODEN_AXE); more_weapons.add(BAMBOO_STONE_AXE); more_weapons.add(BAMBOO_DEEPSLATE_AXE); more_weapons.add(BAMBOO_BLACKSTONE_AXE); more_weapons.add(BAMBOO_IRON_AXE); more_weapons.add(BAMBOO_GOLDEN_AXE); more_weapons.add(BAMBOO_DIAMOND_AXE); more_weapons.add(BAMBOO_NETHERITE_AXE);}
        registerBowItem(BAMBOO_BOW, Items.BAMBOO);
        registerCrossbowItem(BAMBOO_CROSSBOW, Items.BAMBOO);
        registerArrowItem(BAMBOO_ARROW, Items.BAMBOO, BAMBOO_TIPPED_ARROW);


      //Birch Weapons
       registerSwordItem(BIRCH_WOODEN_SWORD, BIRCH_STICK, Items.BIRCH_PLANKS, "wooden");
       registerSwordItem(BIRCH_STONE_SWORD, BIRCH_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(BIRCH_DEEPSLATE_SWORD, BIRCH_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(BIRCH_BLACKSTONE_SWORD, BIRCH_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(BIRCH_IRON_SWORD, BIRCH_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(BIRCH_GOLDEN_SWORD, BIRCH_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(BIRCH_DIAMOND_SWORD, BIRCH_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(BIRCH_NETHERITE_SWORD, BIRCH_STICK, BIRCH_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(BIRCH_WOODEN_AXE); more_weapons.add(BIRCH_STONE_AXE); more_weapons.add(BIRCH_DEEPSLATE_AXE); more_weapons.add(BIRCH_BLACKSTONE_AXE); more_weapons.add(BIRCH_IRON_AXE); more_weapons.add(BIRCH_GOLDEN_AXE); more_weapons.add(BIRCH_DIAMOND_AXE); more_weapons.add(BIRCH_NETHERITE_AXE);}
        registerBowItem(BIRCH_BOW, BIRCH_STICK);
        registerCrossbowItem(BIRCH_CROSSBOW, BIRCH_STICK);
        registerArrowItem(BIRCH_ARROW, BIRCH_STICK, BIRCH_TIPPED_ARROW);


      //Cherry Weapons
       registerSwordItem(CHERRY_WOODEN_SWORD, CHERRY_STICK, Items.CHERRY_PLANKS, "wooden");
       registerSwordItem(CHERRY_STONE_SWORD, CHERRY_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(CHERRY_DEEPSLATE_SWORD, CHERRY_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(CHERRY_BLACKSTONE_SWORD, CHERRY_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(CHERRY_IRON_SWORD, CHERRY_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(CHERRY_GOLDEN_SWORD, CHERRY_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(CHERRY_DIAMOND_SWORD, CHERRY_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(CHERRY_NETHERITE_SWORD, CHERRY_STICK, CHERRY_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(CHERRY_WOODEN_AXE); more_weapons.add(CHERRY_STONE_AXE); more_weapons.add(CHERRY_DEEPSLATE_AXE); more_weapons.add(CHERRY_BLACKSTONE_AXE); more_weapons.add(CHERRY_IRON_AXE); more_weapons.add(CHERRY_GOLDEN_AXE); more_weapons.add(CHERRY_DIAMOND_AXE); more_weapons.add(CHERRY_NETHERITE_AXE);}
        registerBowItem(CHERRY_BOW, CHERRY_STICK);
        registerCrossbowItem(CHERRY_CROSSBOW, CHERRY_STICK);
        registerArrowItem(CHERRY_ARROW, CHERRY_STICK, CHERRY_TIPPED_ARROW);


      //Crimson Weapons
       registerSwordItem(CRIMSON_WOODEN_SWORD, CRIMSON_STICK, Items.CRIMSON_PLANKS, "wooden");
       registerSwordItem(CRIMSON_STONE_SWORD, CRIMSON_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(CRIMSON_DEEPSLATE_SWORD, CRIMSON_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(CRIMSON_BLACKSTONE_SWORD, CRIMSON_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(CRIMSON_IRON_SWORD, CRIMSON_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(CRIMSON_GOLDEN_SWORD, CRIMSON_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(CRIMSON_DIAMOND_SWORD, CRIMSON_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(CRIMSON_NETHERITE_SWORD, CRIMSON_STICK, CRIMSON_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(CRIMSON_WOODEN_AXE); more_weapons.add(CRIMSON_STONE_AXE); more_weapons.add(CRIMSON_DEEPSLATE_AXE); more_weapons.add(CRIMSON_BLACKSTONE_AXE); more_weapons.add(CRIMSON_IRON_AXE); more_weapons.add(CRIMSON_GOLDEN_AXE); more_weapons.add(CRIMSON_DIAMOND_AXE); more_weapons.add(CRIMSON_NETHERITE_AXE);}
        registerBowItem(CRIMSON_BOW, CRIMSON_STICK);
        registerCrossbowItem(CRIMSON_CROSSBOW, CRIMSON_STICK);
        registerArrowItem(CRIMSON_ARROW, CRIMSON_STICK, CRIMSON_TIPPED_ARROW);


      //Dark_oak Weapons
       registerSwordItem(DARK_OAK_WOODEN_SWORD, DARK_OAK_STICK, Items.DARK_OAK_PLANKS, "wooden");
       registerSwordItem(DARK_OAK_STONE_SWORD, DARK_OAK_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(DARK_OAK_DEEPSLATE_SWORD, DARK_OAK_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(DARK_OAK_BLACKSTONE_SWORD, DARK_OAK_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(DARK_OAK_IRON_SWORD, DARK_OAK_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(DARK_OAK_GOLDEN_SWORD, DARK_OAK_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(DARK_OAK_DIAMOND_SWORD, DARK_OAK_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(DARK_OAK_NETHERITE_SWORD, DARK_OAK_STICK, DARK_OAK_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(DARK_OAK_WOODEN_AXE); more_weapons.add(DARK_OAK_STONE_AXE); more_weapons.add(DARK_OAK_DEEPSLATE_AXE); more_weapons.add(DARK_OAK_BLACKSTONE_AXE); more_weapons.add(DARK_OAK_IRON_AXE); more_weapons.add(DARK_OAK_GOLDEN_AXE); more_weapons.add(DARK_OAK_DIAMOND_AXE); more_weapons.add(DARK_OAK_NETHERITE_AXE);}
        registerBowItem(DARK_OAK_BOW, DARK_OAK_STICK);
        more_weapons.add(Items.CROSSBOW);
        registerArrowItem(DARK_OAK_ARROW, DARK_OAK_STICK, DARK_OAK_TIPPED_ARROW);


      //Jungle Weapons
       registerSwordItem(JUNGLE_WOODEN_SWORD, JUNGLE_STICK, Items.JUNGLE_PLANKS, "wooden");
       registerSwordItem(JUNGLE_STONE_SWORD, JUNGLE_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(JUNGLE_DEEPSLATE_SWORD, JUNGLE_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(JUNGLE_BLACKSTONE_SWORD, JUNGLE_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(JUNGLE_IRON_SWORD, JUNGLE_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(JUNGLE_GOLDEN_SWORD, JUNGLE_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(JUNGLE_DIAMOND_SWORD, JUNGLE_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(JUNGLE_NETHERITE_SWORD, JUNGLE_STICK, JUNGLE_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(JUNGLE_WOODEN_AXE); more_weapons.add(JUNGLE_STONE_AXE); more_weapons.add(JUNGLE_DEEPSLATE_AXE); more_weapons.add(JUNGLE_BLACKSTONE_AXE); more_weapons.add(JUNGLE_IRON_AXE); more_weapons.add(JUNGLE_GOLDEN_AXE); more_weapons.add(JUNGLE_DIAMOND_AXE); more_weapons.add(JUNGLE_NETHERITE_AXE);}
        registerBowItem(JUNGLE_BOW, JUNGLE_STICK);
        registerCrossbowItem(JUNGLE_CROSSBOW, JUNGLE_STICK);
        registerArrowItem(JUNGLE_ARROW, JUNGLE_STICK, JUNGLE_TIPPED_ARROW);


      //Mangrove Weapons
       registerSwordItem(MANGROVE_WOODEN_SWORD, MANGROVE_STICK, Items.MANGROVE_PLANKS, "wooden");
       registerSwordItem(MANGROVE_STONE_SWORD, MANGROVE_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(MANGROVE_DEEPSLATE_SWORD, MANGROVE_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(MANGROVE_BLACKSTONE_SWORD, MANGROVE_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(MANGROVE_IRON_SWORD, MANGROVE_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(MANGROVE_GOLDEN_SWORD, MANGROVE_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(MANGROVE_DIAMOND_SWORD, MANGROVE_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(MANGROVE_NETHERITE_SWORD, MANGROVE_STICK, MANGROVE_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(MANGROVE_WOODEN_AXE); more_weapons.add(MANGROVE_STONE_AXE); more_weapons.add(MANGROVE_DEEPSLATE_AXE); more_weapons.add(MANGROVE_BLACKSTONE_AXE); more_weapons.add(MANGROVE_IRON_AXE); more_weapons.add(MANGROVE_GOLDEN_AXE); more_weapons.add(MANGROVE_DIAMOND_AXE); more_weapons.add(MANGROVE_NETHERITE_AXE);}
        registerBowItem(MANGROVE_BOW, MANGROVE_STICK);
        registerCrossbowItem(MANGROVE_CROSSBOW, MANGROVE_STICK);
        registerArrowItem(MANGROVE_ARROW, MANGROVE_STICK, MANGROVE_TIPPED_ARROW);


      //Oak Weapons
        more_weapons.add(Items.WOODEN_SWORD);
        more_weapons.add(Items.STONE_SWORD);
       registerSwordItem(OAK_DEEPSLATE_SWORD, Items.STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(OAK_BLACKSTONE_SWORD, Items.STICK, Items.BLACKSTONE, "blackstone");
        more_weapons.add(Items.IRON_SWORD);
        more_weapons.add(Items.GOLDEN_SWORD);
        more_weapons.add(Items.DIAMOND_SWORD);
        more_weapons.add(Items.NETHERITE_SWORD);
        if(isMtoolvLoaded){more_weapons.add(Items.WOODEN_AXE); more_weapons.add(Items.STONE_AXE); more_weapons.add(OAK_DEEPSLATE_AXE); more_weapons.add(OAK_BLACKSTONE_AXE); more_weapons.add(Items.IRON_AXE); more_weapons.add(Items.GOLDEN_AXE); more_weapons.add(Items.DIAMOND_AXE); more_weapons.add(Items.NETHERITE_AXE);}
        more_weapons.add(Items.BOW);
        registerCrossbowItem(OAK_CROSSBOW, Items.STICK);
        more_weapons.add(Items.ARROW);

      //Spruce Weapons
       registerSwordItem(SPRUCE_WOODEN_SWORD, SPRUCE_STICK, Items.SPRUCE_PLANKS, "wooden");
       registerSwordItem(SPRUCE_STONE_SWORD, SPRUCE_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(SPRUCE_DEEPSLATE_SWORD, SPRUCE_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(SPRUCE_BLACKSTONE_SWORD, SPRUCE_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(SPRUCE_IRON_SWORD, SPRUCE_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(SPRUCE_GOLDEN_SWORD, SPRUCE_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(SPRUCE_DIAMOND_SWORD, SPRUCE_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(SPRUCE_NETHERITE_SWORD, SPRUCE_STICK, SPRUCE_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(SPRUCE_WOODEN_AXE); more_weapons.add(SPRUCE_STONE_AXE); more_weapons.add(SPRUCE_DEEPSLATE_AXE); more_weapons.add(SPRUCE_BLACKSTONE_AXE); more_weapons.add(SPRUCE_IRON_AXE); more_weapons.add(SPRUCE_GOLDEN_AXE); more_weapons.add(SPRUCE_DIAMOND_AXE); more_weapons.add(SPRUCE_NETHERITE_AXE);}
        registerBowItem(SPRUCE_BOW, SPRUCE_STICK);
        registerCrossbowItem(SPRUCE_CROSSBOW, SPRUCE_STICK);
        registerArrowItem(SPRUCE_ARROW, SPRUCE_STICK, SPRUCE_TIPPED_ARROW);


      //Warped Weapons
       registerSwordItem(WARPED_WOODEN_SWORD, WARPED_STICK, Items.WARPED_PLANKS, "wooden");
       registerSwordItem(WARPED_STONE_SWORD, WARPED_STICK, Items.COBBLESTONE, "stone");
       registerSwordItem(WARPED_DEEPSLATE_SWORD, WARPED_STICK, Items.DEEPSLATE, "deepslate");
       registerSwordItem(WARPED_BLACKSTONE_SWORD, WARPED_STICK, Items.BLACKSTONE, "blackstone");
       registerSwordItem(WARPED_IRON_SWORD, WARPED_STICK, Items.IRON_INGOT, "iron");
       registerSwordItem(WARPED_GOLDEN_SWORD, WARPED_STICK, Items.GOLD_INGOT, "golden");
       registerSwordItem(WARPED_DIAMOND_SWORD, WARPED_STICK, Items.DIAMOND, "diamond");
       registerSwordItem(WARPED_NETHERITE_SWORD, WARPED_STICK, WARPED_DIAMOND_SWORD, "netherite");
       if(isMtoolvLoaded){more_weapons.add(WARPED_WOODEN_AXE); more_weapons.add(WARPED_STONE_AXE); more_weapons.add(WARPED_DEEPSLATE_AXE); more_weapons.add(WARPED_BLACKSTONE_AXE); more_weapons.add(WARPED_IRON_AXE); more_weapons.add(WARPED_GOLDEN_AXE); more_weapons.add(WARPED_DIAMOND_AXE); more_weapons.add(WARPED_NETHERITE_AXE);}
        registerBowItem(WARPED_BOW, WARPED_STICK);
        registerCrossbowItem(WARPED_CROSSBOW, WARPED_STICK);
        registerArrowItem(WARPED_ARROW, WARPED_STICK, WARPED_TIPPED_ARROW);

    }

    private static void registerSwordItem(Item swordItem, Item stickIngredient, Item bladeIngredient, String weaponType) {
        String stickWood;
        if (stickIngredient.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickIngredient.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickIngredient).mstvWoodType;}
        String swordName = stickWood + "_" + weaponType + "_sword";
        more_swords.add(swordItem);
        registerWeaponItem(swordItem, stickIngredient, bladeIngredient, swordName);
    }
    private static void registerBowItem(Item bowItem, Item stickIngredient) {
        String stickWood;
        if (stickIngredient.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickIngredient.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickIngredient).mstvWoodType;}
        String bowName = stickWood + "_bow";
        more_bows.add(bowItem);
        registerWeaponItem(bowItem, stickIngredient, null, bowName);
    }
    private static void registerCrossbowItem(Item crossbowItem, Item stickIngredient) {
        String stickWood;
        if (stickIngredient.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickIngredient.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickIngredient).mstvWoodType;}
        String crossbowName = stickWood + "_crossbow";
        more_crossbows.add(crossbowItem);
        registerWeaponItem(crossbowItem, stickIngredient, null, crossbowName);
    }
    private static void registerArrowItem(Item arrowItem, Item stickIngredient, Item tippedArrowItem) {
        String stickWood;
        if (stickIngredient.equals(Items.BAMBOO)) {stickWood = "bamboo";} else if (stickIngredient.equals(Items.STICK)) {stickWood = "oak";} else { stickWood = ((MoreStickVariantItem) stickIngredient).mstvWoodType;}
        String arrowName = stickWood + "_arrow";
        String tippedArrowName = "tipped_" + arrowName;
        more_arrows.add(arrowItem);
        more_arrows.add(tippedArrowItem);
        more_tippable_arrows.put(arrowItem, tippedArrowItem);
        registerWeaponItem(arrowItem, stickIngredient, null, arrowName);
        registerWeaponItem(tippedArrowItem, stickIngredient, null, tippedArrowName);
    }
    private static void registerWeaponItem(Item weaponItem, Item stickIngredient, Item bladeIngredient, String weaponName) {
        more_weapons.add(weaponItem);
        more_weapon_sticks.put(weaponItem, stickIngredient);
        if (bladeIngredient != null) {more_blade_ingredients.put(weaponItem, bladeIngredient);}
        Registry.register(BuiltInRegistries.ITEM, MoreWeaponVariants.asId(weaponName), weaponItem);
    }



}
