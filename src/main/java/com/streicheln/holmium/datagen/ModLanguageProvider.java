package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.item.ModCreativeModeTabs;
import com.streicheln.holmium.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Holmium.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlockTranslation();
        addItemTranslation();
    }

    private void addItemTranslation() {
        add(ModItems.RAW_URANIUM.toStack(), "Raw Uranium");
        add(ModItems.URANIUM_BAR.toStack(), "Uranium Ingot");
        add(ModItems.CESIUM_INGOT.toStack(), "Cesium Ingot");
        add(ModItems.CESIUM_SWORD.toStack(), "Cesium Sword");
        add(ModItems.ITEM_NAME.toStack(), "Femboy Juice");
        add(ModItems.HOLMIUM_FLUID_BUCKET.toStack(), "Bucket of Holmium Fluid");
        add(ModItems.URANIUM_HELMET.toStack(), "Uranium Helmet");
        add(ModItems.URANIUM_CHESTPLATE.toStack(), "Uranium Chestplate");
        add(ModItems.URANIUM_LEGGINGS.toStack(), "Uranium Leggings");
        add(ModItems.URANIUM_BOOTS.toStack(), "Uranium Boots");
        add(ModItems.HOLMIUM_LEAD.toStack(), "Holmium Lead");
    }

    private void addBlockTranslation() {
        add(ModBlocks.HOLMIUM_STONE.get(), "Holmium Stone");
        add(ModBlocks.COBBLED_HOLMIUM.get(), "Cobbled Holmium");
        add(ModBlocks.HOLMIUM_LEAVES.get(), "Holmium Leaves");
        add(ModBlocks.HOLMIUM_SAPLING.get(), "Holmium Sapling");
        add(ModBlocks.HOLMIUM_LOG.get(), "Holmium Log");
        add(ModBlocks.HOLMIUM_WOOD.get(), "Holmium Wood");
        add(ModBlocks.HOLMIUM_PLANKS.get(), "Holmium Planks");
        add(ModBlocks.STRIPPED_HOLMIUM_LOG.get(), "Stripped Holmium Log");
        add(ModBlocks.STRIPPED_HOLMIUM_WOOD.get(), "Stripped Holmium Wood");
        add(ModBlocks.HOLMIUM_GRASS.get(), "Holmium Grass");
        add(ModBlocks.HOLMIUM_DIRT.get(), "Holmium Dirt");
        add(ModBlocks.URANIUM_ORE.get(), "Uranium Ore");
        add(ModBlocks.HOLMIUM_BROWN_MUSHROOM.get(), "Holmium Brown Mushroom");
        add(ModBlocks.HOLMIUM_RED_MUSHROOM.get(), "Holmium Red Mushroom");
        add(ModBlocks.HOLMIUM_PORTAL.get(), "Holmium Portal");
    }
}
