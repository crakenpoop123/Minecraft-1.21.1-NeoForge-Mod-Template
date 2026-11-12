package com.streicheln.holmium.block.entity;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Holmium.MODID);

    public static final Supplier<BlockEntityType<HolmiumPortalBlockEntity>> HOLMIUM_PORTAL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("holmium_portal_block_entity", () -> BlockEntityType.Builder.of(
               HolmiumPortalBlockEntity::new, ModBlocks.HOLMIUM_PORTAL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
