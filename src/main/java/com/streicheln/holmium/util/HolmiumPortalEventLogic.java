package com.streicheln.holmium.util;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Holmium.MODID)
public class HolmiumPortalEventLogic {

    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            BlockPos pos = player.blockPosition();
            Level level = event.getEntity().level();

            setArbitraryBlock(level, pos, ModBlocks.HOLMIUM_PORTAL.get());
        }
    }

    @SubscribeEvent
    public static void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getTo() == HolmiumDimension.HOLMIUM_DIMENSION_KEY || event.getFrom() == HolmiumDimension.HOLMIUM_DIMENSION_KEY) {
            BlockPos playerPos = event.getEntity().blockPosition();

            Level level = event.getEntity().level();

            createPortalArea(level, playerPos);
        }
    }

    public static void createPortalArea(Level level, BlockPos playerPos) {
        int range = 2;

        // Iterate through a 2*range + 1 side length cube around (0, 0, 0)
        for (int x = -range; x < range + 1; x++) {
            for (int y = -1; y < range + 1; y++) {
                for (int z = -range; z < range + 1; z++) {
                    Vec3i pos = new Vec3i(x, y, z);

                    // Get the global pos of this block by adding the relative pos to the player pos
                    BlockPos newPos = (BlockPos) playerPos.offset(pos);

                    // Centre block
                    if (x == 0 && y == 0 && z == 0) {
                        setArbitraryBlock(level, newPos, ModBlocks.HOLMIUM_PORTAL.get());
                    }
                    // Bottom of the cube
                    else if (y == -1) {
                        setArbitraryBlock(level, newPos, ModBlocks.HOLMIUM_STONE.get());
                    }
                    else {
                        setArbitraryBlock(level, newPos, Blocks.AIR);
                    }
                }
            }
        }
    }


    public static void setArbitraryBlock(Level level, BlockPos pos, Block block) {
        if (!level.isClientSide) {
            BlockState state = block.defaultBlockState();
            level.setBlock(pos, state, 3);
        }
    }



}
