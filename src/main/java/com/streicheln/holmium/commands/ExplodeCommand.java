package com.streicheln.holmium.commands;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import java.util.Collection;

public class ExplodeCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("explode")
                        .requires(p_137812_ -> p_137812_.hasPermission(2))
                        .executes(p_137817_ -> explode(p_137817_.getSource(), ImmutableList.of(p_137817_.getSource().getEntityOrException())))
                        .then(
                                Commands.argument("targets", EntityArgument.entities())
                                        .executes(p_137810_ -> explode(p_137810_.getSource(), EntityArgument.getEntities(p_137810_, "targets")))
                        );
    }

    private static int explode(CommandSourceStack source, Collection<? extends Entity> targets) {
        for (Entity entity : targets) {
            entity.kill();
        }

        if (targets.size() == 1) {
            source.sendSuccess(() -> Component.translatable("commands.kill.success.single", targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendSuccess(() -> Component.translatable("commands.kill.success.multiple", targets.size()), true);
        }

        return targets.size();
    }
}
