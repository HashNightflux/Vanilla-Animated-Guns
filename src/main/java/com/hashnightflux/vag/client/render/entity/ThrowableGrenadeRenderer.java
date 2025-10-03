package com.hashnightflux.vag.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.hashnightflux.vag.entity.ThrowableGrenadeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemDisplayContext;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class ThrowableGrenadeRenderer extends EntityRenderer<ThrowableGrenadeEntity>
{
    public ThrowableGrenadeRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(ThrowableGrenadeEntity entity)
    {
        return null;
    }

    @Override
    public void render(ThrowableGrenadeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light)
    {
    poseStack.pushPose();
    poseStack.translate(0.0f, 0.25f, 0.0f);
    float scale = 0.5F;
    poseStack.scale(scale, scale, scale);
    poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
    Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItem(),ItemDisplayContext.NONE,light,OverlayTexture.NO_OVERLAY,poseStack,renderTypeBuffer,entity.level,0);
    poseStack.popPose();
    }
}