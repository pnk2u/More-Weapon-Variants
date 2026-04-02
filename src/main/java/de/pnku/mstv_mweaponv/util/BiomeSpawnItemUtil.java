package de.pnku.mstv_mweaponv.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

import static de.pnku.mstv_base.item.MoreStickVariantItems.*;

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

    // Global "biome-group -> wood key + probabilities" table, shared by all mixins.
    private static final Map<String[], WoodVariant> WOOD_VARIANTS = Map.ofEntries(
            Map.entry(ACACIA_FULL,                  new WoodVariant(ACACIA_STICK, 0.95, null, 0.0)),
            Map.entry(BIRCH_HEAVY,                  new WoodVariant(BIRCH_STICK, 0.90, null, 0.0)),
            Map.entry(BIRCH_LIGHT,                  new WoodVariant(BIRCH_STICK, 0.20, null, 0.0)),
            Map.entry(BAMBOO_HEAVY_JUNGLE_LIGHT,    new WoodVariant(Items.BAMBOO, 0.60, JUNGLE_STICK, 0.30)),
            Map.entry(JUNGLE_HEAVY_BAMBOO_LIGHT,    new WoodVariant(JUNGLE_STICK, 0.80, Items.BAMBOO, 0.10)),
            Map.entry(JUNGLE_HEAVY,                 new WoodVariant(JUNGLE_STICK, 0.80, null, 0.0)),
            Map.entry(CHERRY_FULL,                  new WoodVariant(CHERRY_STICK, 0.925, null, 0.0)),
            Map.entry(CRIMSON_FULL,                 new WoodVariant(CRIMSON_STICK, 1.0, null, 0.0)),
            Map.entry(CRIMSON_WARPED_LIGHT,         new WoodVariant(CRIMSON_STICK, 0.25, WARPED_STICK, 0.05)),
            Map.entry(DARK_OAK_HEAVY_BIRCH_LIGHT,   new WoodVariant(DARK_OAK_STICK, 0.80, BIRCH_STICK, 0.10)),
            Map.entry(MANGROVE_FULL,                new WoodVariant(MANGROVE_STICK, 0.975, null, 0.0)),
            Map.entry(SPRUCE_HEAVY,                 new WoodVariant(SPRUCE_STICK, 0.85, null, 0.0)),
            Map.entry(SPRUCE_LIGHT,                 new WoodVariant(SPRUCE_STICK, 0.30, null, 0.0)),
            Map.entry(WARPED_FULL,                  new WoodVariant(WARPED_STICK, 1.0, null, 0.0)),
            Map.entry(WARPED_LIGHT,                 new WoodVariant(WARPED_STICK, 0.125, null, 0.0))
    );

    private BiomeSpawnItemUtil() {}

    public static Item chooseStickForSpawnBiome(LivingEntity entity) {
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
