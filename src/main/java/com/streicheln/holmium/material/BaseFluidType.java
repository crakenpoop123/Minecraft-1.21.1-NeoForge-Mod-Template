package com.streicheln.holmium.material;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.function.Consumer;


public class BaseFluidType extends FluidType {
    // Texture stuff
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final ResourceLocation overlayTexture;
    private final int tintColour;
    private final Vector3f fogColour;

    public BaseFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture, final ResourceLocation overlayTexture, final int tintColour, final Vector3f fogColour, final Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.tintColour = tintColour;
        this.fogColour = fogColour;
    }


    public ResourceLocation getStillTexture() {
        return stillTexture;
    }

    public ResourceLocation getFlowingTexture() {
        return flowingTexture;
    }

    public ResourceLocation getOverlayTexture() {
        return overlayTexture;
    }

    public int getTintColour() {
        return tintColour;
    }

    public Vector3f getFogColour() {
        return fogColour;
    }

    @Override
    @SuppressWarnings("deprecated")
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) { // IGNORE THE ERROR, IT IS FINEEEEE
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return overlayTexture;
            }

            @Override
            public int getTintColor() {
                return tintColour;
            }

//            @Override
            public @NotNull Vector3f modifyFogColour(Camera camera, float partialTick, ClientLevel level,
                                                     int renderDistance, float darkenWorldAmount, Vector3f fluidFogColour) {
                return fogColour;
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(-8f);
                RenderSystem.setShaderFogEnd(96f);
            }
        });
    }
}
