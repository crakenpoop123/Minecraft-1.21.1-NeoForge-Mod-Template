package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.worldgen.ModBiomeModifiers;
import com.streicheln.holmium.worldgen.ModConfiguredFeatures;
import com.streicheln.holmium.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            ;
    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
       // super(output, registries, BUILDER, Set.of(Holmium.MODID));
    }
}
