package com.enderveil.endupdate.registry;

import com.enderveil.endupdate.EndUpdateMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public final class ModBlocks {
    public static final Block CHORUS_STONE = register("chorus_stone", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE).strength(1.5F, 6.0F)));
    public static final Block CHORUS_BRICKS = register("chorus_bricks", new Block(AbstractBlock.Settings.copy(CHORUS_STONE)));
    public static final Block CHORUS_TILES = register("chorus_tiles", new Block(AbstractBlock.Settings.copy(CHORUS_STONE)));
    public static final Block POLISHED_END_STONE = register("polished_end_stone", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE).strength(1.6F, 5.0F)));
    public static final Block RUNED_END_STONE = register("runed_end_stone", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE).strength(1.8F, 6.0F)));
    public static final Block VOID_GLASS = register("void_glass", new Block(AbstractBlock.Settings.copy(Blocks.GLASS).strength(0.3F).nonOpaque()));
    public static final Block ENDER_LATTICE = register("ender_lattice", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).strength(2.5F, 6.0F)));
    public static final Block ANCIENT_ALTAR = register("ancient_altar", new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_POLISHED_BLACKSTONE).strength(4.0F, 12.0F)));
    public static final Block RELIC_ORE = register("relic_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS).requiresTool().strength(3.0F, 6.0F), UniformIntProvider.create(2, 5)));
    public static final Block END_BLOSSOM = register("end_blossom", new Block(AbstractBlock.Settings.copy(Blocks.AZALEA).strength(0.1F).nonOpaque().breakInstantly()));
    public static final Block VOIDROOT_BLOCK = register("voidroot_block", new Block(AbstractBlock.Settings.copy(Blocks.WARPED_ROOTS).strength(0.2F).nonOpaque().breakInstantly()));

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(EndUpdateMod.MOD_ID, name), block);
    }

    public static void register() {
    }
}
