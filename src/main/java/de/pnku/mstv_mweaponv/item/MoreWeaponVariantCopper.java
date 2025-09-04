package de.pnku.mstv_mweaponv.item;

import de.pnku.mstv_mweaponv.MoreWeaponVariants;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;

import java.lang.reflect.Field;
import java.util.function.Supplier;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.LOGGER;
import static de.pnku.mstv_mweaponv.MoreWeaponVariants.isNemosCopperLoaded;

public class MoreWeaponVariantCopper {
    
    public static final ToolMaterial copperMaterial = getCopperMaterial();
    public static final Item copperSword = getCopperSword();

    protected static ToolMaterial getCopperMaterial() {
        if (isNemosCopperLoaded) {
            try {
                Class<?> modClass = Class.forName("com.nemonotfound.nemos.copper.item.ModToolMaterials");
                Field copperField = modClass.getField("COPPER");
                Object copperValue = copperField.get(null);
                if (copperValue instanceof ToolMaterial material) {
                    return material;
                }
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignored) {
                try {
                    Class<?> modClass = Class.forName("com.devnemo.nemos.copper.item.ModToolMaterials");
                    Field copperField = modClass.getField("COPPER");
                    Object copperValue = copperField.get(null);
                    if (copperValue instanceof ToolMaterial material) {
                        return material;
                    }
                } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignored2) {
                    LOGGER.warn("Failed to load Copper ToolMaterial from Nemo's Copper mod.");
                    MoreWeaponVariants.isNemosCopperLoaded = false;
                    LOGGER.info("Stopped registering Copper weapons.");
                }
            }
        }
        // Final fallback to a safe ToolMaterial to avoid returning null
        return ToolMaterial.STONE;
    }

    protected static Item getCopperSword() {
        if (isNemosCopperLoaded) {
            try {
                Class<?> modClass = Class.forName("com.nemonotfound.nemos.copper.item.ModItems");
                Field copperSwordField = modClass.getField("COPPER_SWORD");
                Object copperSwordItemSupplier = copperSwordField.get(null);
                if (copperSwordItemSupplier instanceof Supplier) {
                    Supplier<?> supplier = (Supplier<?>) copperSwordItemSupplier;
                    Object supplied = supplier.get();
                    if (supplied instanceof Item item) {
                        return item;
                    }
                }
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignored) {
                try {
                    Class<?> modClass = Class.forName("com.devnemo.nemos.copper.item.ModItems");
                    Field copperSwordField = modClass.getField("COPPER_SWORD");
                    Object copperSwordItemSupplier = copperSwordField.get(null);
                    if (copperSwordItemSupplier instanceof Supplier) {
                        Supplier<?> supplier = (Supplier<?>) copperSwordItemSupplier;
                        Object supplied = supplier.get();
                        if (supplied instanceof Item item) {
                            return item;
                        }
                    }
                } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignored2) {
                    LOGGER.warn("Failed to load (Oak) Copper Sword from Nemo's Copper mod.");
                }
            }
        }
        // Final fallback to a safe Item to avoid returning null
        return Items.COPPER_INGOT;
    }
}
