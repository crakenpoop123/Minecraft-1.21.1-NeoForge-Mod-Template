package com.streicheln.holmium.item.custom;

import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.item.ModItems;
import com.streicheln.holmium.util.ModLeashData;
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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;

@EventBusSubscriber(modid = Holmium.MODID)
public class ModLeadItem extends LeadItem {
    public ModLeadItem(Properties properties) {
        super(properties);
    }

    public static float pullStrength = 1.0f;

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        // System.out.println("Disabled interactLivingEntity");

        return InteractionResult.FAIL;
    }

    public static void arbitraryEntityInteract(Player player, Level level, PlayerInteractEvent event) {
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
//        if (level.isClientSide) return;

        Vec3 eyePos = player.getEyePosition();
        Vec3 lookVec = player.getLookAngle();
        Vec3 endPos = eyePos.add(lookVec.scale(player.entityInteractionRange())); // Reach distance

        AABB searchBox = player.getBoundingBox().expandTowards(lookVec.scale(player.entityInteractionRange())).inflate(0.5);
        List<Entity> entities = level.getEntities(player, searchBox);

        // System.out.println("Right Clicked in onArbitraryEntityInteract: ");

        for (Entity entity : entities) {
            // System.out.print("Interacting with entity: ");
            // System.out.println(entity);
            if (entity.getBoundingBox().inflate(0.2).clip(eyePos, endPos).isPresent()) {
                if (entity instanceof Leashable leashable) {
                    Leashable.LeashData data = leashable.getLeashData();
                    if ((Object) data instanceof ModLeashData modLeashData) {
                        // System.out.println("Instance of ModLeashData");
                        // System.out.println(modLeashData.getLeadType());
                        // System.out.println(data);
                    } else {
                        // System.out.println("Not an instance of ModLeashData");
                    }

                    if (!leashable.isLeashed() && (stack.is(ModItems.HOLMIUM_LEAD) || stack.is(Items.LEAD))) {
                        leashEntity(player, entity, leashable, stack);
                        cancelEvent(event);
                    }
                    else if (leashable.getLeashHolder() == player) {
                        dropLeashCustom(leashable, player);
                        cancelEvent(event);
                    }
                    break;
                }
            }
        }
    }

    public static void dropLeashCustom(Leashable leashable, Player player) {
        if (!player.level().isClientSide) {
            Leashable.LeashData leashData = leashable.getLeashData();
            if ((Object) leashData instanceof ModLeashData modLeashData && !player.isCreative()) {
                 System.out.println("Dropped a Leash Item");
                if (modLeashData.getLeadType() == ModLeashData.leadTypeStates.LEAD) {
                    ((Entity) leashable).spawnAtLocation(Items.LEAD);
                } else {
                    ((Entity) leashable).spawnAtLocation(ModItems.HOLMIUM_LEAD);
                }
            }

            // System.out.println("Disconnected the Leash");
            leashable.dropLeash(true, false);
            // System.out.println("Leashable new holder is: " +  leashable.getLeashHolder());
        }
    }

    public static InteractionResult leashEntity(Player player, Entity target, Leashable leashable, ItemStack stack){
        if (!player.level().isClientSide()) {
            if (!leashable.isLeashed()) {
                ModLeashData.leadTypeStates leadType = stack.is(Items.LEAD) ? ModLeashData.leadTypeStates.LEAD : ModLeashData.leadTypeStates.HOLMIUM_LEAD;


                if (leadType == ModLeashData.leadTypeStates.HOLMIUM_LEAD || (target instanceof LivingEntity && leashable.canBeLeashed())) {
                    leashable.setLeashedTo(player, true);
                } else {
                    // System.out.println("Can't leash this entity");
                    return InteractionResult.FAIL;
                }
                stack.consume(1, player);

                Leashable.LeashData leashData = leashable.getLeashData();
                if ((Object) leashData instanceof ModLeashData modLeashData) {
                    modLeashData.setLeadType(leadType);
                    // System.out.println(modLeashData.getLeadType());
                    // System.out.println(leashData.leashHolder);
                }



                if (leadType == ModLeashData.leadTypeStates.HOLMIUM_LEAD) {
                    // Get vector between target and player
                    Vec3 playerPos = player.getPosition(1.0f);
                    Vec3 targetPos = target.getPosition(1.0f);
                    Vec3 pullVec = playerPos.subtract(targetPos);
                    pullVec = pullVec.normalize().multiply(new Vec3(pullStrength, pullStrength, pullStrength));
                    // Pull target to player
                    target.setDeltaMovement(pullVec);
                    target.hurtMarked = true;

                }

                return InteractionResult.sidedSuccess(player.level().isClientSide());
            }
        }
        return InteractionResult.sidedSuccess(player.level().isClientSide());
    }

    public static void cancelEvent(PlayerInteractEvent event) {
        if (event instanceof PlayerInteractEvent.EntityInteract entityInteractEvent) {
            entityInteractEvent.setCanceled(true);
            entityInteractEvent.setCancellationResult(InteractionResult.SUCCESS);
            // System.out.println("Canceled entityInteractEvent");
        } else if (event instanceof ICancellableEvent cancellableEvent) {
            cancellableEvent.setCanceled(true);
            // System.out.println("Canceled ICancellableEvent");
        } else {
            // System.out.println("Failed to cancel event");
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut (PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide) {
            AABB searchBox = player.getBoundingBox().inflate(32.0);
            for (Entity entity : player.level().getEntities(player, searchBox)) {
                if (entity instanceof Leashable leashable && leashable.getLeashHolder() == player) {
                    dropLeashCustom(leashable, player);
                }
            }
        }
    }

    // These are used to ensure Entities are counted whenever the player Right Clicks
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            // System.out.println(event);
            Player player = event.getEntity();
            Level level = event.getLevel();
            arbitraryEntityInteract(player, level, event);
//            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            // System.out.println(event);
            Player player = event.getEntity();
            Level level = event.getLevel();
            arbitraryEntityInteract(player, level, event);
//            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onRightClickLivingEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            // System.out.println(event);
            Player player = event.getEntity();
            Level level = event.getLevel();
            arbitraryEntityInteract(player, level, event);
//            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onRightClickLivingEntitySpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getHand() == InteractionHand.MAIN_HAND && event.getTarget() instanceof Leashable) {
            cancelEvent(event);
        }
    }




}
