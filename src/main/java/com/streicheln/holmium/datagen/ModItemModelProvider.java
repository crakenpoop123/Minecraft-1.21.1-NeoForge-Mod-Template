package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Holmium.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
