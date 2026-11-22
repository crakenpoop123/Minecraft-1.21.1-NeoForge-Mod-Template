package com.streicheln.holmium.util;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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

            spawnPortalAt(level, pos);
        }
    }

    @SubscribeEvent
    public static void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getTo() == HolmiumDimension.HOLMIUM_DIMENSION_KEY) {
            Vec3i playerPos = event.getEntity().blockPosition();

            Level level = event.getEntity().level();

            int range = 2;

            for (int x = -range; x < range + 1; x++) {
                for (int y = -range; y < range + 1; y++) {
                    for (int z = -range; z < range + 1; z++) {
                        Vec3i pos = new Vec3i(x, y, z);

                        BlockPos newPos = (BlockPos) playerPos.offset(pos);

                        clearBlockAt(level, newPos);
                        
                    }
                }
            }
        }
    }

    public static void spawnPortalAt(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            BlockState state = ModBlocks.HOLMIUM_PORTAL.get().defaultBlockState();
            level.setBlock(pos, state, 3);
        }
    }

    public static void clearBlockAt(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            BlockState state = Blocks.AIR.defaultBlockState();
            level.setBlock(pos, state, 3);
        }
    }

}
