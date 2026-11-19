package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Holmium.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ITEM_NAME.get());
        basicItem(ModBlocks.HOLMIUM_SAPLING.asItem());
        basicItem(ModItems.RAW_URANIUM.get());
//        basicItem(ModItems.CESIUM_INGOT.get());
//        basicItem(ModItems.CESIUM_SWORD.get());
        basicItem(ModItems.URANIUM_BAR.get());
        basicItem(ModItems.HOLMIUM_FLUID_BUCKET.get());
        basicItem(ModItems.URANIUM_HELMET.get());
        basicItem(ModItems.URANIUM_CHESTPLATE.get());
        basicItem(ModItems.URANIUM_LEGGINGS.get());
        basicItem(ModItems.URANIUM_BOOTS.get());
        basicItem(ModItems.HOLMIUM_LEAD.get());
//        basicItem(ModBlocks.VOLCANIC_DIRT.asItem());
//        basicItem(ModBlocks.HOLMIUM_PORTAL.asItem());



        // Give all items a model
//        for (DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS.getEntries()) {
//            basicItem(item.get());
//        }
    }
}
