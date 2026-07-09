package com.enderveil.endupdate.registry;

import com.enderveil.endupdate.EndUpdateMod;
import com.enderveil.endupdate.entity.EnderHeartEntity;
import com.enderveil.endupdate.entity.GlowmothEntity;
import com.enderveil.endupdate.entity.ShardlingEntity;
import com.enderveil.endupdate.entity.WatcherEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEntities {
    public static final EntityType<ShardlingEntity> SHARDLING = Registry.register(Registries.ENTITY_TYPE, Identifier.of(EndUpdateMod.MOD_ID, "shardling"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ShardlingEntity::new).dimensions(EntityDimensions.fixed(0.6F, 0.95F)).trackRangeBlocks(8).build());

    public static final EntityType<WatcherEntity> WATCHER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(EndUpdateMod.MOD_ID, "watcher"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WatcherEntity::new).dimensions(EntityDimensions.fixed(0.8F, 2.0F)).trackRangeBlocks(8).build());

    public static final EntityType<GlowmothEntity> GLOWMOTH = Registry.register(Registries.ENTITY_TYPE, Identifier.of(EndUpdateMod.MOD_ID, "glowmoth"),
        FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, GlowmothEntity::new).dimensions(EntityDimensions.fixed(0.9F, 0.6F)).trackRangeBlocks(8).build());

    public static final EntityType<EnderHeartEntity> ENDER_HEART = Registry.register(Registries.ENTITY_TYPE, Identifier.of(EndUpdateMod.MOD_ID, "ender_heart"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EnderHeartEntity::new).dimensions(EntityDimensions.fixed(3.6F, 4.2F)).trackRangeBlocks(16).build());

    public static void register() {
        FabricDefaultAttributeRegistry.register(SHARDLING, ShardlingEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(WATCHER, WatcherEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(GLOWMOTH, GlowmothEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ENDER_HEART, EnderHeartEntity.createAttributes());
    }
}
