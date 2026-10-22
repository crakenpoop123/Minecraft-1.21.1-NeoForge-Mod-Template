package com.streicheln.holmium.datagen;

import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
//        dropSelf(ModBlocks.HOLMIUM_STONE.get());
        dropSelf(ModBlocks.COBBLED_HOLMIUM.get());


        // Drop cobbled
        add(ModBlocks.HOLMIUM_STONE.get(),
                block -> createSilkTouchDispatchTable(ModBlocks.HOLMIUM_STONE.get(),
                        applyExplosionDecay(ModBlocks.HOLMIUM_STONE.get(),
                                LootItem.lootTableItem(ModBlocks.COBBLED_HOLMIUM.get()))));
    }

    // :3

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}
