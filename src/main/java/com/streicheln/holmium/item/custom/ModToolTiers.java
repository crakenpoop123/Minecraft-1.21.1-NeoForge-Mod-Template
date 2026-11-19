package com.streicheln.holmium.item.custom;

import com.streicheln.holmium.item.ModItems;
import com.streicheln.holmium.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier CESIUM = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CESIUM_TOOL,
            5, 5f, 100f, 0, () -> Ingredient.of(ModItems.CESIUM_INGOT.get()));
}