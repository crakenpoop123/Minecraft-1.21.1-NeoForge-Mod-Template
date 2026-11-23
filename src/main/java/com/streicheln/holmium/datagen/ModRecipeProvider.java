package com.streicheln.holmium.datagen;

import com.streicheln.holmium.block.ModBlocks;
import com.streicheln.holmium.item.ModItems;
import com.streicheln.holmium.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
//        List<ItemLike> HOLMIUM_SMELTABLES = List.of( // THIS IS CONFUSING ASK ME WHEN YOU NEED TO ADD SOMETHING SMELTED
//                ModItems.ITEM_NAME
//                );
//
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.URANIUM_CHESTPLATE.get()) // SHAPED RECIPE TEMPLATE
                .pattern("U U")
                .pattern("UUU")
                .pattern("UUU")
                .define('U', ModItems.URANIUM_BAR)
                .unlockedBy("has_item", has(ModItems.URANIUM_BAR)).save(recipeOutput, "holmium:uranium_chest");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.URANIUM_HELMET.get()) // SHAPED RECIPE TEMPLATE
                .pattern("UUU")
                .pattern("U U")
                .define('U', ModItems.URANIUM_BAR)
                .unlockedBy("has_item", has(ModItems.URANIUM_BAR)).save(recipeOutput, "holmium:uranium_helm");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.URANIUM_LEGGINGS.get()) // SHAPED RECIPE TEMPLATE
                .pattern("UUU")
                .pattern("U U")
                .pattern("U U")
                .define('U', ModItems.URANIUM_BAR)
                .unlockedBy("has_item", has(ModItems.URANIUM_BAR)).save(recipeOutput, "holmium:uranium_leg");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.URANIUM_BOOTS.get()) // SHAPED RECIPE TEMPLATE
                .pattern("U U")
                .pattern("U U")
                .define('U', ModItems.URANIUM_BAR)
                .unlockedBy("has_item", has(ModItems.URANIUM_BAR)).save(recipeOutput, "holmium:uranium_boot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CESIUM_SWORD.get()) // SHAPED RECIPE TEMPLATE
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" s ")
                .define('C', ModItems.CESIUM_INGOT)
                .define('s', Items.STICK)
                .unlockedBy("has_item", has(ModItems.URANIUM_BAR)).save(recipeOutput, "holmium:cesium_sword_why_is_this_broken");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HOLMIUM_PORTAL.get()) // SHAPED RECIPE TEMPLATE
                .pattern("UUU")
                .pattern("UUU")
                .pattern("UUU")
                .define('U', ModItems.URANIUM_BAR)
                .unlockedBy("has_item", has(ModItems.URANIUM_BAR)).save(recipeOutput, "holmium:portal");


//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ITEM_NAME.get(), 9) // SHAPELESS RECIPE TEMPLATE
//                .requires(ModBlocks.HOLMIUM_STONE)
//                .unlockedBy("has_block", has(ModBlocks.HOLMIUM_STONE)).save(recipeOutput, "holmium:example_item_from_holmium_stone");
//
//        oreSmelting(recipeOutput, HOLMIUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.HOLMIUM_STONE.get(), 10f, 200, "holmium"); // SMELTING EXAMPLES
//        oreBlasting(recipeOutput, HOLMIUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.HOLMIUM_STONE.get(), 20f, 100, "holmium");

        List<ItemLike> HOLMIUM_SMELTABLES = List.of( // THIS IS CONFUSING ASK ME WHEN YOU NEED TO ADD SOMETHING SMELTED
                ModItems.RAW_URANIUM
                );

        oreSmelting(recipeOutput, HOLMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.URANIUM_BAR.get(), 10f, 200, "uranium"); // SMELTING EXAMPLES
        oreBlasting(recipeOutput, HOLMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.URANIUM_BAR.get(), 20f, 100, "uranium");


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.HOLMIUM_PLANKS.get(), 4)
                .requires(Ingredient.of(ModTags.Items.HOLMIUM_LOGS))
                .unlockedBy("has_block", has(ModBlocks.HOLMIUM_LOG)).save(recipeOutput, "holmium:holmium_planks_from_holmium_log");



    }
}
