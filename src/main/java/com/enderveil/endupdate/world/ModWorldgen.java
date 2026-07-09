package com.enderveil.endupdate.world;

import com.enderveil.endupdate.registry.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;

public final class ModWorldgen {
    public static void register() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.MONSTER, ModEntities.SHARDLING, 28, 1, 3);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.MONSTER, ModEntities.WATCHER, 10, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), SpawnGroup.AMBIENT, ModEntities.GLOWMOTH, 8, 2, 4);
    }
}
