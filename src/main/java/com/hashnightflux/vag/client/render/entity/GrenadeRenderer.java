package com.hashnightflux.vag.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.hashnightflux.vag.entity.GrenadeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;

/**
 * Author: MrCrayfish
 */
public class GrenadeRenderer extends EntityRenderer<GrenadeEntity>
{
    public GrenadeRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(GrenadeEntity entity)
    {
        return null;
    }

    @Override
    public void render(GrenadeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light)
    {
        if(!entity.getProjectile().isVisible() || entity.tickCount <= 1)
        {
            return;
        }

    poseStack.pushPose();
    poseStack.translate(0.0f, 0.25f, 0.0f);
    float scale = 0.5F;
    poseStack.scale(scale, scale, scale);
    poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
    Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItem(),ItemDisplayContext.NONE,light,OverlayTexture.NO_OVERLAY,poseStack,renderTypeBuffer,entity.level,0);
    poseStack.popPose();
    }
}
