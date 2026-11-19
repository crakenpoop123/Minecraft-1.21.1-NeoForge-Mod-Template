package com.streicheln.holmium.mixin;

import com.streicheln.holmium.item.custom.ModLeadItem;
import com.streicheln.holmium.util.ModLeashData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Entity.class)
public abstract class EntityMixin implements Leashable {
    @Unique
    @Nullable
    private Leashable.LeashData holmium$leashData;
    private ModLeashData.leadTypeStates holmium$leadState;


    @Override
    public Leashable.LeashData getLeashData() {return this.holmium$leashData;}

    @Inject(method = "interact", at = @At("TAIL"))
    public InteractionResult interactLeash(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> ci) {
        System.out.println("interacted with entity leash");
        return ci.getReturnValue();
    }

//    @Inject(method = "setLeashData", at = @At("TAIL"))
    public void setLeashData(@Nullable Leashable.LeashData newLeashData) {
        if ((Object) newLeashData instanceof ModLeashData newModData) {
            Leashable.LeashData oldLeashData = this.getLeashData();

            if ((Object) oldLeashData instanceof ModLeashData oldModData) {
                newModData.setLeadType(oldModData.getLeadType());
            } else if (this.holmium$leashData != null) {
                newModData.setLeadType(this.holmium$leadState);
                this.holmium$leashData = null;
            }
        }
        this.holmium$leashData = newLeashData;
    }

    @Inject(method = "saveWithoutId", at = @At("TAIL"))
    private void saveLeadType(CompoundTag compoundTag, CallbackInfoReturnable<CompoundTag> cir) {
        Entity self = (Entity) (Object) this;
        if (!(self instanceof Mob) && !(self instanceof Boat)) {
            this.writeLeashData(compoundTag, this.getLeashData());
        }

        Leashable.LeashData data = this.getLeashData();
        if (data != null && !(data.leashHolder instanceof Player)) {
            if ((Object) data instanceof ModLeashData modLeashData) {
                compoundTag.putString("leadType", modLeashData.getLeadType().name());
            }
        } else if (this.holmium$leadState != null) {
            compoundTag.putString("leadType", this.holmium$leadState.name());
        }
    }

    @Inject(method = "load", at = @At("TAIL"))
    private void loadLeadType(CompoundTag compoundTag, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (!(self instanceof Mob) && !(self instanceof Boat)) {
            this.setLeashData(this.readLeashData(compoundTag));
        }

        if (compoundTag.contains("leadType")) {
            try {
                ModLeashData.leadTypeStates loadedType =
                        ModLeashData.leadTypeStates.valueOf(compoundTag.getString("leadType"));
                Leashable.LeashData data = this.getLeashData();

                if ((Object) data instanceof ModLeashData modLeashData) {
                    modLeashData.setLeadType(loadedType);
                } else {
                    this.holmium$leadState = loadedType;
                }
            } catch (IllegalArgumentException ignored) {}
        }
    }

    @Override
    public boolean canBeLeashed() {
        return true;
    }

}
