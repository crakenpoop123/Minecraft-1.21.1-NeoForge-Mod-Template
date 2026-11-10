package com.streicheln.holmium.mixin;

import com.streicheln.holmium.util.ModLeashData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Leashable.class)
public interface LeashableMixin {

    @Inject(method = "writeLeashData", at = @At("TAIL"))
    private static void writeCustomLeashData(CompoundTag tag, Leashable.LeashData leashData, CallbackInfo ci) {
        if ((Object) leashData instanceof ModLeashData modLeashData) {
            tag.putString("HolmiumLeadType", modLeashData.getLeadType().name());
        }
    }

    @Inject(method = "readLeashData", at = @At("TAIL"))
    private static void readCustomLeashData(CompoundTag tag, CallbackInfoReturnable<Leashable.LeashData> cir) {
        Leashable.LeashData leashData = cir.getReturnValue();
        if (leashData != null && tag.contains("HolmiumLeadType", 8)) { // 8 is String for NBTs
            if ((Object) leashData instanceof ModLeashData modLeashData) {
                try {
                    String savedType = tag.getString("HolmiumLeadType");
                    ModLeashData.leadTypeStates state = ModLeashData.leadTypeStates.valueOf(savedType);
                    modLeashData.setLeadType(state);
                }
                catch (Exception e) {
                    modLeashData.setLeadType(ModLeashData.leadTypeStates.LEAD);
                }
            }
        }
    }
}
