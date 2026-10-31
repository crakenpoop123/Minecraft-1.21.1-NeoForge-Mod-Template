package com.streicheln.holmium.material;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModFluids{
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Holmium.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Holmium.MODID);

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> SOURCE_HOLMIUM_FLUID =
            FLUIDS.register("source_holmium_fluid", () -> new BaseFlowingFluid.Source(ModFluids.HOLMIUM_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_HOLMIUM_FLUID =
            FLUIDS.register("flowing_holmium_fluid", () -> new BaseFlowingFluid.Flowing(ModFluids.HOLMIUM_FLUID_PROPERTIES));

    public static final DeferredHolder<Fluid, ? extends FlowingFluid> HOLMIUM_FLUID = FLUIDS.register("holmium_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.HOLMIUM_FLUID_PROPERTIES));



    public static final BaseFlowingFluid.Properties HOLMIUM_FLUID_PROPERTIES =
            new BaseFlowingFluid.Properties(
                    ModFluidTypes.HOLMIUM_FLUID_TYPE,
                    SOURCE_HOLMIUM_FLUID,
                    FLOWING_HOLMIUM_FLUID
            )
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1)
                    .block(ModFluids.HOLMIUM_FLUID_BLOCK)
                    .bucket(ModItems.HOLMIUM_FLUID_BUCKET);

    public static final DeferredBlock<LiquidBlock> HOLMIUM_FLUID_BLOCK = BLOCKS.register("holmium_fluid",
            () -> new ModLiquidBlock(HOLMIUM_FLUID, BlockBehaviour.Properties.of()
                    .replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).lightLevel(state -> 15)
                    .ignitedByLava()
            ));

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
