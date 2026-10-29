package com.streicheln.holmium.material;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.common.Mod;

import java.util.function.Supplier;

public class ModLiquidBlock extends LiquidBlock {
    private final Supplier<? extends FlowingFluid> fluidSupplier;



    public ModLiquidBlock(Supplier<? extends FlowingFluid> fluidSupplier, Properties properties) {
        super(fluidSupplier.get(), properties);
        this.fluidSupplier = fluidSupplier;
    }

    public Fluid getFluid() {
        return this.fluidSupplier.get();
    }
}
