package de.pnku.mstv_mweaponv.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;
import static de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems.*;

public final class BiomeSpawnItemUtil {

    public record WoodVariant(Item main, double mainProb, Item alt, double altProb) {}

    // Predefined Biome String Arrays based on intended spawn chances
    // SUFFIX: total variant spawn chance in percentage
    // FULL  : >90%
    // HEAVY : >=60% <=90%
    // MEDIUM: >30% <60%
    // LIGHT : <=30%
    public static final String[] ACACIA_FULL = {
            "minecraft:savanna",
            "minecraft:savanna_plateau",
            "minecraft:windswept_savanna"
    };
    public static final String[] BIRCH_HEAVY = {
            "minecraft:birch_forest",
            "minecraft:old_growth_birch_forest"
    };
    public static final String[] BIRCH_LIGHT = {
            "minecraft:forest",
            "minecraft:meadow"
    };
    public static final String[] BAMBOO_HEAVY_JUNGLE_LIGHT = {
            "minecraft:bamboo_jungle"
    };
    public static final String[] JUNGLE_HEAVY_BAMBOO_LIGHT = {
            "minecraft:jungle"
    };
    public static final String[] JUNGLE_HEAVY = {
            "minecraft:sparse_jungle",
            "minecraft:desert"
    };
    public static final String[] CHERRY_FULL = {
            "minecraft:cherry_grove"
    };
    public static final String[] CRIMSON_FULL = {
            "minecraft:crimson_forest"
    };
    public static final String[] CRIMSON_WARPED_LIGHT = {
            "minecraft:nether_wastes"
    };
    public static final String[] DARK_OAK_HEAVY_BIRCH_LIGHT = {
            "minecraft:dark_forest",
    };
    public static final String[] MANGROVE_FULL = {
            "minecraft:mangrove_swamp"
    };
    public static final String[] PALE_OAK_FULL = {
            "minecraft:pale_garden"
    };
    public static final String[] SPRUCE_HEAVY = {
            "minecraft:taiga",
            "minecraft:old_growth_pine_taiga",
            "minecraft:old_growth_spruce_taiga",
            "minecraft:snowy_taiga"
    };
    public static final String[] SPRUCE_LIGHT = {
            "minecraft:windswept_forest",
            "minecraft:snowy_plains"
    };
    public static final String[] WARPED_FULL = {
            "minecraft:warped_forest"
    };
    public  static final String[] WARPED_LIGHT = {
            "minecraft:soul_sand_valley"
    };

    private static final Map<String[], WoodVariant> WOOD_VARIANTS = new HashMap<>();

    public static final Map<Item, Item> BOW_BY_STICK = new HashMap<>();

    public static final Map<Item, Item[]> SWORD_AND_SPEAR_BY_STICK = new HashMap<>();

    private static boolean initialized = false;

    private BiomeSpawnItemUtil() {}

    private static void initMaps() {
        addWoodVariant(ACACIA_FULL, ACACIA_STICK, 0.95, null, 0.0);
        addWoodVariant(BIRCH_HEAVY, BIRCH_STICK, 0.90, null, 0.0);
        addWoodVariant(BIRCH_LIGHT, BIRCH_STICK, 0.20, null, 0.0);
        addWoodVariant(BAMBOO_HEAVY_JUNGLE_LIGHT, Items.BAMBOO, 0.60, JUNGLE_STICK, 0.30);
        addWoodVariant(JUNGLE_HEAVY_BAMBOO_LIGHT, JUNGLE_STICK, 0.80, Items.BAMBOO, 0.10);
        addWoodVariant(JUNGLE_HEAVY, JUNGLE_STICK, 0.80, null, 0.0);
        addWoodVariant(CHERRY_FULL, CHERRY_STICK, 0.925, null, 0.0);
        addWoodVariant(CRIMSON_FULL, CRIMSON_STICK, 1.0, null, 0.0);
        addWoodVariant(CRIMSON_WARPED_LIGHT, CRIMSON_STICK, 0.25, WARPED_STICK, 0.05);
        addWoodVariant(DARK_OAK_HEAVY_BIRCH_LIGHT, DARK_OAK_STICK, 0.80, BIRCH_STICK, 0.10);
        addWoodVariant(PALE_OAK_FULL, PALE_OAK_STICK, 1.00, null, 0.0);
        addWoodVariant(MANGROVE_FULL, MANGROVE_STICK, 0.975, null, 0.0);
        addWoodVariant(SPRUCE_HEAVY, SPRUCE_STICK, 0.85, null, 0.0);
        addWoodVariant(SPRUCE_LIGHT, SPRUCE_STICK, 0.30, null, 0.0);
        addWoodVariant(WARPED_FULL, WARPED_STICK, 1.00, null, 0.00);
        addWoodVariant(WARPED_LIGHT, WARPED_STICK, 0.125, null, 0.00);

        addBowVariant(ACACIA_BOW, ACACIA_STICK);
        addBowVariant(BIRCH_BOW, BIRCH_STICK);
        addBowVariant(BAMBOO_BOW, Items.BAMBOO);
        addBowVariant(JUNGLE_BOW, JUNGLE_STICK);
        addBowVariant(CHERRY_BOW, CHERRY_STICK);
        addBowVariant(CRIMSON_BOW, CRIMSON_STICK);
        addBowVariant(DARK_OAK_BOW, DARK_OAK_STICK);
        addBowVariant(PALE_OAK_BOW, PALE_OAK_STICK);
        addBowVariant(MANGROVE_BOW, MANGROVE_STICK);
        addBowVariant(SPRUCE_BOW, SPRUCE_STICK);
        addBowVariant(WARPED_BOW, WARPED_STICK);

        addSwordAndSpearVariant(ACACIA_IRON_SWORD, ACACIA_IRON_SPEAR, ACACIA_STICK);
        addSwordAndSpearVariant(BIRCH_IRON_SWORD, BIRCH_IRON_SPEAR, BIRCH_STICK);
        addSwordAndSpearVariant(BAMBOO_IRON_SWORD, BAMBOO_IRON_SPEAR, Items.BAMBOO);
        addSwordAndSpearVariant(JUNGLE_IRON_SWORD, JUNGLE_IRON_SPEAR, JUNGLE_STICK);
        addSwordAndSpearVariant(CHERRY_IRON_SWORD, CHERRY_IRON_SPEAR, CHERRY_STICK);
        addSwordAndSpearVariant(CRIMSON_IRON_SWORD, CRIMSON_IRON_SPEAR, CRIMSON_STICK);
        addSwordAndSpearVariant(DARK_OAK_IRON_SWORD, DARK_OAK_IRON_SPEAR, DARK_OAK_STICK);
        addSwordAndSpearVariant(PALE_OAK_IRON_SWORD, PALE_OAK_IRON_SPEAR, PALE_OAK_STICK);
        addSwordAndSpearVariant(MANGROVE_IRON_SWORD, MANGROVE_IRON_SPEAR, MANGROVE_STICK);
        addSwordAndSpearVariant(SPRUCE_IRON_SWORD, SPRUCE_IRON_SPEAR, SPRUCE_STICK);
        addSwordAndSpearVariant(WARPED_IRON_SWORD, WARPED_IRON_SPEAR, WARPED_STICK);

        initialized = true;
    }

    public static void addWoodVariant(String[] biomeNames, Item mainStickItem, double mainProb, Item altStickItem, double altProb) {
        WOOD_VARIANTS.putIfAbsent(biomeNames, new WoodVariant(mainStickItem, mainProb, altStickItem, altProb));
    }

    public static void addBowVariant(Item bowItem, Item stickItem) {
        BOW_BY_STICK.putIfAbsent(stickItem, bowItem);
    }

    public static void addSwordAndSpearVariant(Item swordItem, Item spearItem, Item stickItem) {
        SWORD_AND_SPEAR_BY_STICK.putIfAbsent(stickItem, new Item[]{swordItem, spearItem});
    }

    public static Item chooseStickForSpawnBiome(LivingEntity entity) {
        if (!initialized) initMaps();
        if (entity == null) return null;
        BlockPos pos = entity.blockPosition();
        String biomeName = entity.level().getBiome(pos).getRegisteredName();

        WoodVariant variant = findVariantForBiome(biomeName);
        if (variant == null || variant.main == null || variant.mainProb <= 0.0) {
            return null;
        }

        double r = Math.random();
        if (variant.alt == null || variant.altProb <= 0.0) {
            return r < variant.mainProb ? variant.main : null;
        } else {
            if (r < variant.mainProb) return variant.main;
            if (r < variant.mainProb + variant.altProb) return variant.alt;
            return null;
        }
    }

    private static WoodVariant findVariantForBiome(String biomeName) {
        for (Map.Entry<String[], WoodVariant> e : WOOD_VARIANTS.entrySet()) {
            for (String b : e.getKey()) {
                if (b.equals(biomeName)) return e.getValue();
            }
        }
        return null;
    }
}
