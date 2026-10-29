package com.streicheln.holmium.datagen;

import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        // Drop cobbled
        add(ModBlocks.HOLMIUM_STONE.get(),
                block -> createSilkTouchDispatchTable(ModBlocks.HOLMIUM_STONE.get(),
                        applyExplosionDecay(ModBlocks.HOLMIUM_STONE.get(),
                                LootItem.lootTableItem(ModBlocks.COBBLED_HOLMIUM.get()))));

        add(ModBlocks.HOLMIUM_GRASS.get(),
                block -> createSilkTouchDispatchTable(ModBlocks.HOLMIUM_GRASS.get(),
                        applyExplosionDecay(ModBlocks.HOLMIUM_GRASS.get(),
                                LootItem.lootTableItem(ModBlocks.HOLMIUM_DIRT.get()))));


        ModBlocks.BLOCKS.getEntries().stream()
                .map(DeferredHolder::get)
                .filter(block -> !this.map.containsKey(block.getLootTable())) // Skip blocks predefined
                .forEach(this::dropSelf); // Default blocks to dropSelf()

    }

    // :3

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
