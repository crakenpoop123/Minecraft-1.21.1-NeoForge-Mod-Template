package com.streicheln.holmium.block.custom;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.streicheln.holmium.block.entity.HolmiumPortalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.jetbrains.annotations.Nullable;

public class HolmiumPortalBlock extends BaseEntityBlock implements Portal {
    public static final VoxelShape SHAPE = Block.box(2, 0, 8, 16, 16, 8);
    public static final MapCodec<HolmiumPortalBlock> CODEC = simpleCodec(HolmiumPortalBlock::new);

    public HolmiumPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    // Block entity stuff:

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new HolmiumPortalBlockEntity(blockPos, blockState);
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false) && Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getShape(level, pos), BooleanOp.AND)) {
            entity.setAsInsidePortal(this, pos);
        }

    }

    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos blockPos) {
        ResourceKey<Level> resourcekey = level.dimension() == Level.END ? Level.OVERWORLD : Level.END;
        ServerLevel serverlevel = level.getServer().getLevel(resourcekey);
        if (serverlevel == null) {
            return null;
        } else {
            boolean flag = resourcekey == Level.END;
            BlockPos blockpos = flag ? ServerLevel.END_SPAWN_POINT : serverlevel.getSharedSpawnPos();
            Vec3 vec3 = blockpos.getBottomCenter();
            float f = entity.getYRot();
            if (flag) {
                EndPlatformFeature.createEndPlatform(serverlevel, BlockPos.containing(vec3).below(), true);
                f = Direction.WEST.toYRot();
                if (entity instanceof ServerPlayer) {
                    vec3 = vec3.subtract((double)0.0F, (double)1.0F, (double)0.0F);
                }
            } else {
                if (entity instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)entity;
                    return serverplayer.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING);
                }

                vec3 = entity.adjustSpawnLocation(serverlevel, blockpos).getBottomCenter();
            }

            return new DimensionTransition(serverlevel, vec3, entity.getDeltaMovement(), f, entity.getXRot(), DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET));
        }
    }
}
