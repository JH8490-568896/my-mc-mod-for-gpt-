package com.enderveil.endupdate.world.feature;

import com.enderveil.endupdate.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class VoidSpireFeature extends Feature<DefaultFeatureConfig> {
    public VoidSpireFeature(com.mojang.serialization.Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (!world.getBlockState(origin.down()).isOf(Blocks.END_STONE)) {
            return false;
        }

        int height = 7 + random.nextInt(10);
        for (int y = 0; y < height; y++) {
            world.setBlockState(origin.up(y), (y % 3 == 0 ? ModBlocks.ENDER_LATTICE : ModBlocks.VOID_GLASS).getDefaultState(), 3);
        }
        world.setBlockState(origin.up(height), ModBlocks.ANCIENT_ALTAR.getDefaultState(), 3);
        return true;
    }
}
