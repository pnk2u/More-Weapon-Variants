package de.pnku.mstv_mweaponv.mixin.mtoolv;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static de.pnku.mstv_mweaponv.MoreWeaponVariants.isMtoolvLoaded;

public class MtoolvMixinPlugin implements IMixinConfigPlugin {
    public static final Logger LOGGER = LoggerFactory.getLogger("mweaponvmixinplugin");

    @Override
    public void onLoad(String mixinPackage) {
        isMtoolvLoaded = FabricLoader.getInstance().isModLoaded("mstv-mtoolv");
        if (isMtoolvLoaded) {LOGGER.info("mToolV loaded");}
    }

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return isMtoolvLoaded;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
