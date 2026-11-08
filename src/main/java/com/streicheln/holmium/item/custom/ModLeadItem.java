package com.streicheln.holmium.item.custom;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Holmium.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModLeadItem extends LeadItem {
    public ModLeadItem(Properties properties) {
        super(properties);
    }

    public float pullStrength = 1.0f;



    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
//        System.out.println("Player interacts with Living Entity");
//        System.out.println("Leashable");
        System.out.println(target instanceof Leashable leashable);
        if (target instanceof Leashable leashable) {
            if (!player.level().isClientSide()) {
                if (!leashable.isLeashed()) {
                    leashable.setLeashedTo(player, true);
                    stack.shrink(1);

                    // Get vector between target and player
                    Vec3 playerPos = player.getPosition(1.0f);
                    Vec3 targetPos = target.getPosition(1.0f);
                    Vec3 pullVec = playerPos.subtract(targetPos);
                    pullVec = pullVec.normalize().multiply(new Vec3(pullStrength, pullStrength, pullStrength));
                    // Pull target to player
                    target.setDeltaMovement(pullVec);
                    target.hurtMarked = true;


                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide());
        }
        return super.interactLivingEntity(stack, player, target, hand);
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        System.out.print("entity is leashable: ");
        System.out.println(event.getTarget() instanceof Leashable);
        System.out.print("entity is item: ");
        System.out.println(event.getTarget() instanceof ItemEntity);
        if (event.getTarget() instanceof Leashable leashable) {
            if (leashable.getLeashHolder() == event.getEntity()) {
                event.setCanceled(true);
                dropLeashCustom(leashable, event.getEntity());
            }
        }
    }

    public static void dropLeashCustom(Leashable leashable, Player player) {
        if (!player.level().isClientSide) {
            ((Entity) leashable).spawnAtLocation(ModItems.HOLMIUM_LEAD.get());
        }

        leashable.dropLeash(true, false);
    }


}
