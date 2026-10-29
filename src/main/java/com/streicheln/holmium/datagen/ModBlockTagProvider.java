package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import javax.annotation.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider{
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Holmium.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.HOLMIUM_STONE.getKey())
                .add(ModBlocks.COBBLED_HOLMIUM.getKey())
                .add(ModBlocks.URANIUM_ORE.getKey());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.HOLMIUM_LOG.getKey())
                .add(ModBlocks.HOLMIUM_PLANKS.getKey())
                .add(ModBlocks.HOLMIUM_WOOD.getKey())
                .add(ModBlocks.STRIPPED_HOLMIUM_LOG.getKey())
                .add(ModBlocks.STRIPPED_HOLMIUM_WOOD.getKey());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.HOLMIUM_LOG.get())
                .add(ModBlocks.STRIPPED_HOLMIUM_LOG.get())
                .add(ModBlocks.HOLMIUM_WOOD.get())
                .add(ModBlocks.STRIPPED_HOLMIUM_WOOD.get());
    }
}

