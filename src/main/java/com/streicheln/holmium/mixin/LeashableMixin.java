package com.streicheln.holmium.mixin;

import com.streicheln.holmium.item.ModItems;
import com.streicheln.holmium.util.ModLeashData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Leashable.class)
public interface LeashableMixin {

    @Inject(method = "dropLeash(Lnet/minecraft/world/entity/Entity;ZZ)V", at = @At("HEAD"))
    private static <E extends Entity & Leashable> void saveLeadTypeOnDrop(E entity, boolean broadcastPacket, boolean dropItem, CallbackInfo ci) {
        Leashable.LeashData data = entity.getLeashData();
        if ((Object) data instanceof ModLeashData modLeashData) {
            ModLeashData.DROP_TYPE.set(modLeashData.getLeadType());
        } else {
            ModLeashData.DROP_TYPE.remove();
        }
    }

    @Redirect(
            method = "dropLeash(Lnet/minecraft/world/entity/Entity;ZZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"
            )
    )
    private static ItemEntity spawnCustomLead(Entity entity, ItemLike item) {
        ModLeashData.leadTypeStates state = ModLeashData.DROP_TYPE.get();
        ModLeashData.DROP_TYPE.remove();

        if (state == ModLeashData.leadTypeStates.HOLMIUM_LEAD) {
            return entity.spawnAtLocation(ModItems.HOLMIUM_LEAD.get());
        }
        return entity.spawnAtLocation(item);
    }

}
