package com.hashnightflux.vag.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.hashnightflux.vag.client.GunModel;
import com.hashnightflux.vag.client.render.gun.IOverrideModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Deprecated. This scope utilises the new scope rendering and doesn't need custom rendering.
 * To upgrade, create a .vagmeta file for your scope and customise the properties.
 */
@Deprecated(since = "1.3.0", forRemoval = true)
public class ShortScopeModel implements IOverrideModel
{

    @Override
    public void render(float partialTicks, ItemDisplayContext display, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light, int overlay)
    {
        BakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getItemModel(stack);
        Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.NONE, false, poseStack, renderTypeBuffer, light, overlay, GunModel.wrap(bakedModel));
    }
}