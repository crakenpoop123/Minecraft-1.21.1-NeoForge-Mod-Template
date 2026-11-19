package com.streicheln.holmium.datagen;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.item.ModItems;
import com.streicheln.holmium.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Holmium.MODID);
    }

    @Override
    protected void start() {
        this.add("village_pirate_token",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(), LootItemRandomChanceCondition.randomChance(0.001f).build()
                }, ModItems.URANIUM_BAR.get()));

    }
}
