package com.streicheln.holmium.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Entity.class)
public abstract class EntityMixin implements Leashable {
    @Unique
    @Nullable
    private Leashable.LeashData holmium$leashData;

    @Override
    @Nullable
    public Leashable.LeashData getLeashData() {
        return this.holmium$leashData;
    }

    @Override
    public void setLeashData(@Nullable Leashable.LeashData data) {
        this.holmium$leashData = data;
    }

    @Override
    public boolean canBeLeashed() {
        return true;
    }

}
