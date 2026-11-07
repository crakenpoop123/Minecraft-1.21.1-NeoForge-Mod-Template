package com.streicheln.holmium.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LeadItem;

public class ModLeadItem extends LeadItem {
    public ModLeadItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof Leashable leashable && leashable.canBeLeashed()) {
            if (!player.level().isClientSide()) {
                if (!leashable.isLeashed()) {
                    leashable.setLeashedTo(player, true);
                    stack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide());
        }
        return super.interactLivingEntity(stack, player, target, hand);
    }
}
