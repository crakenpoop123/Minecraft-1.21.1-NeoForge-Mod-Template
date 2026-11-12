package com.streicheln.holmium.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class HolmiumDimension {
    public static final ResourceLocation HOLMIUM_DIMENSION = ResourceLocation.fromNamespaceAndPath("holmium", "custom_dimension");
    public static final ResourceKey<Level> HOLMIUM_DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, HOLMIUM_DIMENSION);


}
