package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.item.ModCreativeModeTabs;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Holmium.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.HOLMIUM_STONE.get(), "Holmium Stone");
        add(ModBlocks.COBBLED_HOLMIUM.get(), "Cobbled Holmium");
        add(ModBlocks.HOLMIUM_LEAVES.get(), "Holmium Leaves");
        add(ModBlocks.HOLMIUM_SAPLING.get(), "Holmium Sapling");
        add(ModBlocks.HOLMIUM_LOG.get(), "Holmium Log");
        add(ModBlocks.HOLMIUM_WOOD.get(), "Holmium Wood");
        add(ModBlocks.STRIPPED_HOLMIUM_LOG.get(), "Stripped Holmium Log");
        add(ModBlocks.STRIPPED_HOLMIUM_WOOD.get(), "Stripped Holmium Wood");
        add(ModBlocks.HOLMIUM_GRASS.get(), "Holmium Grass");

    }
}
