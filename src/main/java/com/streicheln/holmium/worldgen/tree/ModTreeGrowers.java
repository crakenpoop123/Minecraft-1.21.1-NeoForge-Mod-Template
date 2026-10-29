package com.streicheln.holmium.worldgen.tree;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower HOLMIUM = new TreeGrower(Holmium.MODID + ":holmium",
            Optional.empty(), Optional.of(ModConfiguredFeatures.HOLMIUM_TREE_KEY), Optional.empty());
}
