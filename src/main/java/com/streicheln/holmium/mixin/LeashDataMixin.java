package com.streicheln.holmium.mixin;

import com.streicheln.holmium.util.ModLeashData;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Leashable.LeashData.class)
public abstract class LeashDataMixin implements ModLeashData{

    @Unique
    ModLeashData.leadTypeStates leadType = ModLeashData.leadTypeStates.LEAD;

    @Override
    public leadTypeStates getLeadType() {
        return this.leadType;
    }

    @Override
    public void setLeadType(leadTypeStates state) {
        this.leadType = state;
    }

}
