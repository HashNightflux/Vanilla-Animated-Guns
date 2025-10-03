package com.hashnightflux.vag.client;

import com.hashnightflux.vag.Reference;
import com.hashnightflux.vag.client.render.entity.GrenadeRenderer;
import com.hashnightflux.vag.client.render.entity.ProjectileRenderer;
import com.hashnightflux.vag.client.render.entity.ThrowableGrenadeRenderer;
import com.hashnightflux.vag.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GunEntityRenderers
{
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.PROJECTILE.get(), ProjectileRenderer::new);
        event.registerEntityRenderer(ModEntities.DYNAMITE.get(), GrenadeRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWABLE_DYNAMITE.get(), ThrowableGrenadeRenderer::new);
    }
}
