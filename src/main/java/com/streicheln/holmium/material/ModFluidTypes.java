package com.streicheln.holmium.material;

import com.streicheln.holmium.Holmium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

public class ModFluidTypes{
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, "holmium");

    public static final ResourceLocation HOLMIUM_FLUID_ALL_RL = ResourceLocation.parse("holmium:block/sum_bullshit.png");

    public static final DeferredHolder<FluidType, FluidType> HOLMIUM_FLUID_TYPE =
            FLUID_TYPES.register("holmium_fluid_type", () -> new BaseFluidType(HOLMIUM_FLUID_ALL_RL,HOLMIUM_FLUID_ALL_RL,HOLMIUM_FLUID_ALL_RL,
                    0xA1E038D0, new Vector3f(224f/255f, 56f/255f, 208f/255f),
                    FluidType.Properties.create()
                       .descriptionId("fluid.holmium.holmium_fluid")
                       .density(100000)
                       .viscosity(100000)
                       .lightLevel(15)
            ));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
