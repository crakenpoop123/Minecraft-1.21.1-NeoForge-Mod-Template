package com.streicheln.holmium.worldgen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_URANIUM_ORE_KEY = registerKey("uranium_ore");


    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLMIUM_TREE_KEY = registerKey("holmium_tree");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES); // Replace certain tags
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK); // Replace individual blocks
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState>  overworldUraniumOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.URANIUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_URANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldUraniumOre, 9)); // Ctrl left click ore to see it all

        register(context, HOLMIUM_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.HOLMIUM_LOG.get()), // What block the trunk is
                new StraightTrunkPlacer(4, 2, 0), // How tall it is

                BlockStateProvider.simple(ModBlocks.HOLMIUM_LEAVES.get()), // What block the leaves are
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), // What size it is

                new TwoLayersFeatureSize(1, 0, 1)) // How much space it needs to grow
                .ignoreVines() // no clue
                .dirt(BlockStateProvider.simple(ModBlocks.HOLMIUM_DIRT.get()))
                .build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Holmium.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
