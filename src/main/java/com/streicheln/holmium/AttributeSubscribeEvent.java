package com.streicheln.holmium;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@SuppressWarnings("deprecated")
@EventBusSubscriber(modid = Holmium.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AttributeSubscribeEvent {

    @SubscribeEvent
    public static void loadEntityAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, NeoForgeMod.CREATIVE_FLIGHT, 1.0);
        event.add(EntityType.PLAYER, Attributes.LUCK, 1024.0);
        event.add(EntityType.PLAYER, Attributes.MAX_HEALTH, 1024.0);
        event.add(EntityType.PLAYER, Attributes.MAX_ABSORPTION, 2048.0);
//        event.add(EntityType.PLAYER, Attributes.SCALE, 16.0);
    }
}
