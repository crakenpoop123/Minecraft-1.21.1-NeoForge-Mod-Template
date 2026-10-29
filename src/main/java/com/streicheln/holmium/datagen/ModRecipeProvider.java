package com.streicheln.holmium.datagen;

import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> HOLMIUM_SMELTABLES = List.of( // THIS IS CONFUSING ASK ME WHEN YOU NEED TO ADD SOMETHING SMELTED
                ModItems.ITEM_NAME
                );

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HOLMIUM_STONE.get()) // SHAPED RECIPE TEMPLATE
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.ITEM_NAME.get())
                .unlockedBy("has_item", has(ModItems.ITEM_NAME)).save(recipeOutput, "holmium:holmium_stone_from_example_item"); // give it a unique name

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ITEM_NAME.get(), 9) // SHAPELESS RECIPE TEMPLATE
                .requires(ModBlocks.HOLMIUM_STONE)
                .unlockedBy("has_block", has(ModBlocks.HOLMIUM_STONE)).save(recipeOutput, "holmium:example_item_from_holmium_stone");

        oreSmelting(recipeOutput, HOLMIUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.HOLMIUM_STONE.get(), 10f, 200, "holmium"); // SMELTING EXAMPLES
        oreBlasting(recipeOutput, HOLMIUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.HOLMIUM_STONE.get(), 20f, 100, "holmium");
    }
}
