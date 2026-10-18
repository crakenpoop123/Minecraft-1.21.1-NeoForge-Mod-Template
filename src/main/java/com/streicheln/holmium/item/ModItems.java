package com.streicheln.holmium.item;

import com.streicheln.holmium.Holmium;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Holmium.MODID);

    public static final DeferredItem<Item> ITEM_NAME = ITEMS.register("item_name",
            () -> new Item(new Item.Properties())); // example item with no purpose


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
