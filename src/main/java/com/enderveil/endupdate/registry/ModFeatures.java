package com.enderveil.endupdate.registry;

import com.enderveil.endupdate.EndUpdateMod;
import com.enderveil.endupdate.world.feature.EndRuinFeature;
import com.enderveil.endupdate.world.feature.VoidSpireFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public final class ModFeatures {
    public static final Feature<DefaultFeatureConfig> ANCIENT_RUIN = register("ancient_ruin", new EndRuinFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> VOID_SPIRE = register("void_spire", new VoidSpireFeature(DefaultFeatureConfig.CODEC));

    private static <C extends net.minecraft.world.gen.feature.FeatureConfig> Feature<C> register(String name, Feature<C> feature) {
        return Registry.register(Registries.FEATURE, Identifier.of(EndUpdateMod.MOD_ID, name), feature);
    }

    public static void register() {
    }
}
