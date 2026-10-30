package com.streicheln.holmium.worldgen;

import com.streicheln.holmium.Holmium;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;

public class ModBiomes {
    public static final ResourceKey<Biome> HOLMIUM_BIOME = ResourceKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Holmium.MODID, "holmium_biome"));

}
