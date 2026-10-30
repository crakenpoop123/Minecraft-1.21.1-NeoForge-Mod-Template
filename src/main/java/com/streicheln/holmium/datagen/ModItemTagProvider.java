package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Holmium.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.HOLMIUM_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_HOLMIUM_LOG.get().asItem())
                .add(ModBlocks.HOLMIUM_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_HOLMIUM_WOOD.get().asItem());
         tag(ItemTags.PLANKS)
                 .add(ModBlocks.HOLMIUM_PLANKS.asItem());
         tag(ModTags.Items.HOLMIUM_LOGS)
                 .add(ModBlocks.HOLMIUM_LOG.get().asItem())
                 .add(ModBlocks.STRIPPED_HOLMIUM_LOG.get().asItem())
                 .add(ModBlocks.HOLMIUM_WOOD.get().asItem())
                 .add(ModBlocks.STRIPPED_HOLMIUM_WOOD.get().asItem());
    }
}
