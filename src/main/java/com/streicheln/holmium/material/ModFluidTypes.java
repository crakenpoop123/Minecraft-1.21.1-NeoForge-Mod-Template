//package com.streicheln.holmium.material;
//
//import net.neoforged.neoforge.fluids.FluidType;
//import net.neoforged.neoforge.registries.DeferredHolder;
//import net.neoforged.neoforge.registries.DeferredRegister;
//import net.neoforged.neoforge.registries.NeoForgeRegistries;
//
//public class ModFluidTypes {
//    public static final DeferredRegister<FluidType> FLUID_TYPES =
//            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, "holmium");
//
//    public static final DeferredHolder<FluidType, FluidType> HOLMIUM_FLUID_TYPE =
//            FLUID_TYPES.register("holmium_fluid_type", () -> new FluidType(
//               FluidType.Properties.create()
//                       .descriptionId("fluid.holmium.holmium_fluid")
//                       .density(1000)
//                       .viscosity(1000)
//                       .lightLevel(15)
//            ));
//}
