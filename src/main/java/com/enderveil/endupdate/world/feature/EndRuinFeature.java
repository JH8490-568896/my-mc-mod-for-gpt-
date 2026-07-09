package com.enderveil.endupdate.world.feature;

import com.enderveil.endupdate.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class EndRuinFeature extends Feature<DefaultFeatureConfig> {
    public EndRuinFeature(com.mojang.serialization.Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (!world.getBlockState(origin.down()).isOf(Blocks.END_STONE) && !world.getBlockState(origin.down()).isOf(Blocks.OBSIDIAN)) {
            return false;
        }

        int radius = 3 + random.nextInt(3);
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                int dist = Math.abs(dx) + Math.abs(dz);
                if (dist > radius + 1) continue;
                BlockPos base = origin.add(dx, 0, dz);
                int height = 1 + random.nextInt(2);
                for (int dy = 0; dy < height; dy++) {
                    place(world, base.up(dy), random.nextBoolean() ? ModBlocks.CHORUS_STONE.getDefaultState() : ModBlocks.POLISHED_END_STONE.getDefaultState());
                }
                if (dist == radius || random.nextFloat() < 0.18F) {
                    place(world, base.up(height), ModBlocks.RUNED_END_STONE.getDefaultState());
                }
            }
        }
        place(world, origin, ModBlocks.ANCIENT_ALTAR.getDefaultState());
        place(world, origin.up(), ModBlocks.END_BLOSSOM.getDefaultState());
        return true;
    }

    private void place(StructureWorldAccess world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).isAir() || world.getBlockState(pos).isReplaceable()) {
            world.setBlockState(pos, state, 3);
        }
    }
}
