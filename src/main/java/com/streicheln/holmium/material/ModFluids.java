package com.streicheln.holmium.material;

import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModFluids{
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, "holmium");

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> SOURCE_HOLMIUM_FLUID =
            FLUIDS.register("holmium_fluid", () -> new BaseFlowingFluid.Source(ModFluids.HOLMIUM_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_HOLMIUM_FLUID =
            FLUIDS.register("flowing_holmium_fluid", () -> new BaseFlowingFluid.Flowing(ModFluids.HOLMIUM_FLUID_PROPERTIES));

    public static final FlowingFluid HOLMIUM_FLUID = (FlowingFluid) register("holmium_fluid",
            new WaterFluid.Source());

            public static final BaseFlowingFluid.Properties HOLMIUM_FLUID_PROPERTIES =
            new BaseFlowingFluid.Properties(
                    ModFluidTypes.HOLMIUM_FLUID_TYPE,
                    SOURCE_HOLMIUM_FLUID,
                    FLOWING_HOLMIUM_FLUID
            )
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1)
                    .block(ModBlocks.HOLMIUM_FLUID_BLOCK);

    private static <T extends Fluid> T register(String key, T fluid) {
        return (T)(Registry.register(BuiltInRegistries.FLUID, key, fluid));
    }
}
