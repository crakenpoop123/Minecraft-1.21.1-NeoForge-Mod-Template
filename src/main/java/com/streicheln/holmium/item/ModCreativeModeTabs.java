package com.streicheln.holmium.item;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Holmium.MODID);

    public static final Supplier<CreativeModeTab> HOLMIUM_ITEMS_TAB = CREATIVE_MODE_TAB.register("holmium_items_tab",

            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.HOLMIUM_STONE.get()))
                    .title(Component.translatable("creativetab.holmium."))

                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
