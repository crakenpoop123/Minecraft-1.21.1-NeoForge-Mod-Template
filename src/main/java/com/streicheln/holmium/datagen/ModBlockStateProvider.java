package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Holmium.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.HOLMIUM_STONE);
        blockWithItem(ModBlocks.COBBLED_HOLMIUM);

        logBlock(((RotatedPillarBlock) ModBlocks.HOLMIUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.HOLMIUM_WOOD.get()), blockTexture(ModBlocks.HOLMIUM_LOG.get()), blockTexture(ModBlocks.HOLMIUM_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_HOLMIUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_HOLMIUM_WOOD.get()), blockTexture(ModBlocks.STRIPPED_HOLMIUM_LOG.get()), blockTexture(ModBlocks.STRIPPED_HOLMIUM_LOG.get()));

        blockItem(ModBlocks.HOLMIUM_LOG);
        blockItem(ModBlocks.HOLMIUM_WOOD);
        blockItem(ModBlocks.STRIPPED_HOLMIUM_LOG);
        blockItem(ModBlocks.STRIPPED_HOLMIUM_WOOD);

        blockWithItem(ModBlocks.HOLMIUM_PLANKS);

        leavesBlock(ModBlocks.HOLMIUM_LEAVES);
        saplingBlock(ModBlocks.HOLMIUM_SAPLING);

    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) { // Ignore this warning
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("holmium:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("holmium:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
