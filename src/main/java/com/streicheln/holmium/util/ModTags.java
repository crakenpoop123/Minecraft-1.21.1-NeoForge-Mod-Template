package com.streicheln.holmium.util;

import com.streicheln.holmium.Holmium;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.awt.*;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Holmium.MODID, name));
        }
    }
    public static class Items {

        public static final TagKey<Item> EXAMPLE_ITEMS = createTag("example_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Holmium.MODID, name));
        }

    }
    public static class Biomes {

        public static final TagKey<Biome> IS_HOLMIUM_DIMENSION = create("holmium_dimension");

        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.withDefaultNamespace(name));
        }
    }
}
