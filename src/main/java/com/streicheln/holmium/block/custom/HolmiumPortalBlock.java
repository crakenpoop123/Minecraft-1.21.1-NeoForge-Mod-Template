package com.streicheln.holmium.block.custom;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
//import com.streicheln.holmium.block.entity.HolmiumPortalBlockEntity;
import com.streicheln.holmium.util.HolmiumDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class HolmiumPortalBlock extends Block implements Portal {
    public static final MapCodec<HolmiumPortalBlock> CODEC = simpleCodec(HolmiumPortalBlock::new);

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final VoxelShape SHAPE_NS = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape SHAPE_EW = Block.box(7, 0, 0, 9, 16, 16);

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static ResourceKey<Level> cachedOriginDimension;

    public HolmiumPortalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST, WEST -> SHAPE_EW;
            default -> SHAPE_NS;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

//    @Override
//    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
//        return new HolmiumPortalBlockEntity(blockPos, blockState);
//    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false) && Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getShape(level, pos), BooleanOp.AND)) {
            entity.setAsInsidePortal(this, pos);
        }

        System.out.println("Entered Portal");

    }

    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos blockPos) {
        if (cachedOriginDimension == null) cachedOriginDimension = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(Level.OVERWORLD.location().toString()));
        ResourceKey<Level> dimensionDestination = level.dimension().location().equals(HolmiumDimension.HOLMIUM_DIMENSION) ? Level.OVERWORLD : HolmiumDimension.HOLMIUM_DIMENSION_KEY;
        ServerLevel serverlevel = level.getServer().getLevel(dimensionDestination);
        if (serverlevel == null) {
            return null;
        } else {
            Vec3 vec3 = entity.position();
            return new DimensionTransition(serverlevel, vec3, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET));
        }
    }
}
