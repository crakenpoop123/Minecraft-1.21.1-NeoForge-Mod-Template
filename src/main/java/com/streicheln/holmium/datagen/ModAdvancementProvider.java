package com.streicheln.holmium.datagen;

import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output,
                                  CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    private static final class ModAdvancementGenerator implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

            Advancement.Builder enter_holmium_dimension = Advancement.Builder.advancement();

            enter_holmium_dimension.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root"));
            enter_holmium_dimension.display(new ItemStack(Items.GRASS_BLOCK),
                    Component.translatable("advancements.holmium.test_advancement.title"),
                    Component.translatable("advancements.holmium.test_advancement.description"),
            null,
                    AdvancementType.GOAL,
            true,
            true,
            false
            );

            enter_holmium_dimension.rewards(
                    AdvancementRewards.Builder.experience(100)
            );
            enter_holmium_dimension.addCriterion("enter_holmium_dimension", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HOLMIUM_DIRT));
            enter_holmium_dimension.requirements(AdvancementRequirements.allOf(List.of("Pickup_holmium_dirt")));
            enter_holmium_dimension.save(saver, ResourceLocation.fromNamespaceAndPath("holmium", "test"), existingFileHelper);


        }
    }
}
