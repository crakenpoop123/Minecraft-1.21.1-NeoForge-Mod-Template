package com.streicheln.holmium.worldgen.custom;

import com.mojang.serialization.Codec;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ModIslandFeature extends Feature<NoneFeatureConfiguration> {
    public ModIslandFeature(Codec<NoneFeatureConfiguration> p_65701_) {
        super(p_65701_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159717_) {
        WorldGenLevel worldgenlevel = p_159717_.level();
        RandomSource randomsource = p_159717_.random();
        BlockPos blockpos = p_159717_.origin();
        float f = (float)randomsource.nextInt(3) + 4.0F;

        for(int i = 0; f > 0.5F; --i) {
            for(int j = Mth.floor(-f); j <= Mth.ceil(f); ++j) {
                for(int k = Mth.floor(-f); k <= Mth.ceil(f); ++k) {
                    if ((float)(j * j + k * k) <= (f + 1.0F) * (f + 1.0F)) {
                        this.setBlock(worldgenlevel, blockpos.offset(j, i, k), ModBlocks.HOLMIUM_STONE.get().defaultBlockState());
                    }
                }
            }

            f -= (float)randomsource.nextInt(2) + 0.5F;
        }

        return true;
    }
}