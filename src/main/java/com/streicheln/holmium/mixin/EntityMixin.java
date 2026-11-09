package com.streicheln.holmium.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

@Mixin(Entity.class)
public abstract class EntityMixin implements Leashable {
    @Unique
    @Nullable
    private Leashable.LeashData leashData;

    @Override
    @Nullable
    public Leashable.LeashData getLeashData() {
        return this.leashData;
    }

    @Override
    public void setLeashData(@Nullable Leashable.LeashData data) {
        this.leashData = data;
    }

}
