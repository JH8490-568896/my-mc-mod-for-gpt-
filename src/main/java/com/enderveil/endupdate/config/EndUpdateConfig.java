package com.enderveil.endupdate.config;

import com.enderveil.endupdate.EndUpdateMod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class EndUpdateConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "endupdate.json";

    public boolean enableWorldgen = true;
    public boolean enableBoss = true;
    public int shardlingWeight = 28;
    public int watcherWeight = 10;
    public int glowmothWeight = 8;
    public double relicDropMultiplier = 1.0D;
    public double bossHealthMultiplier = 1.0D;
    public int ruinFrequency = 1;

    public static EndUpdateConfig load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        if (Files.exists(path)) {
            try {
                return GSON.fromJson(Files.readString(path), EndUpdateConfig.class);
            } catch (Exception e) {
                EndUpdateMod.LOGGER.warn("Failed to load End Update config; using defaults", e);
            }
        }
        EndUpdateConfig config = new EndUpdateConfig();
        config.save();
        return config;
    }

    public void save() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(this));
        } catch (IOException e) {
            EndUpdateMod.LOGGER.warn("Failed to save End Update config", e);
        }
    }
}
