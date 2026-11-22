package com.streicheln.holmium.util;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Holmium.MODID)
public class HolmiumPortalSpawner {

    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            BlockPos pos = player.blockPosition();
            Level level = event.getEntity().level();

            spawnPortalAt(level, pos);
        }
    }

    public static void spawnPortalAt(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            BlockState state = ModBlocks.HOLMIUM_PORTAL.get().defaultBlockState();
            level.setBlock(pos, state, 3);
        }
    }
}
