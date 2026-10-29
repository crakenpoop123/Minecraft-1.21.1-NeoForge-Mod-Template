package com.streicheln.holmium.block;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.custom.ModFlammableRotatedPillarBlock;
import com.streicheln.holmium.item.ModItems;
import com.streicheln.holmium.material.ModFluids;
import com.streicheln.holmium.material.ModLiquidBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Holmium.MODID);


    public static final DeferredBlock<Block> HOLMIUM_STONE = registerBlock("holmium_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> COBBLED_HOLMIUM = registerBlock("cobbled_holmium",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));

    public static final DeferredBlock<Block> HOLMIUM_LOG = registerBlock("holmium_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> HOLMIUM_WOOD = registerBlock("holmium_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_HOLMIUM_LOG = registerBlock("stripped_holmium_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_HOLMIUM_WOOD = registerBlock("stripped_holmium_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> URANIUM_ORE = registerBlock("uranium_ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 7), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 7.0F)));
    public static final DeferredBlock<Block> HOLMIUM_BROWN_MUSHROOM = registerBlock("holmium_brown_mushroom",
            () -> new MushroomBlock(TreeFeatures.HUGE_BROWN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
    public static final DeferredBlock<Block> HOLMIUM_RED_MUSHROOM = registerBlock("holmium_red_mushroom",
            () -> new MushroomBlock(TreeFeatures.HUGE_RED_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM)));
    public static final DeferredBlock<Block> HOLMIUM_GRASS = registerBlock("holmium_grass",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
    public static final DeferredBlock<Block> HOLMIUM_DIRT = registerBlock("holmium_dirt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));




    public static final DeferredBlock<Block> HOLMIUM_PLANKS = registerBlock("holmium_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final DeferredBlock<Block> HOLMIUM_LEAVES = registerBlock("holmium_leaves",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final DeferredBlock<Block> HOLMIUM_SAPLING = registerBlock("holmium_sapling",
            () -> new SaplingBlock(TreeGrower.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // :3 >.< TwT uwu >///<

}

