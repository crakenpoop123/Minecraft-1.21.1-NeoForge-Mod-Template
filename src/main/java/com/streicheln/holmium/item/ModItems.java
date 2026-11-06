package com.streicheln.holmium.item;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.item.custom.ModArmourItem;
import com.streicheln.holmium.material.ModFluids;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Holmium.MODID);

    public static final DeferredItem<Item> ITEM_NAME = ITEMS.register("item_name",
            () -> new Item(new Item.Properties())); // example item with no purpose
    public static final DeferredItem<Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> URANIUM_BAR = ITEMS.register("uranium_bar",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HOLMIUM_FLUID_BUCKET = ITEMS.register("holmium_fluid_bucket",
            () -> new BucketItem(ModFluids.SOURCE_HOLMIUM_FLUID.get(), new Item.Properties()
                    .stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredItem<ArmorItem> URANIUM_HELMET = ITEMS.register("uranium_helmet",
            () -> new ModArmourItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> URANIUM_CHESTPLATE = ITEMS.register("uranium_chestplate",
            () -> new ModArmourItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));
    public static final DeferredItem<ArmorItem> URANIUM_LEGGINGS = ITEMS.register("uranium_leggings",
            () -> new ModArmourItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));
    public static final DeferredItem<ArmorItem> URANIUM_BOOTS = ITEMS.register("uranium_boots",
            () -> new ModArmourItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
