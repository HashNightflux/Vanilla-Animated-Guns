package com.hashnightflux.vag.init;

import com.mojang.serialization.Codec;
import com.hashnightflux.vag.Reference;
import com.hashnightflux.vag.particles.BulletHoleData;
import com.hashnightflux.vag.particles.TrailData;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Author: MrCrayfish
 */
public class ModParticleTypes
{
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Reference.MOD_ID);

    public static final RegistryObject<ParticleType<BulletHoleData>> BULLET_HOLE = REGISTER.register("bullet_hole",() -> new ParticleType<>(false, BulletHoleData.DESERIALIZER)
    {
        @Override
        public Codec<BulletHoleData> codec()
        {
            return BulletHoleData.CODEC;
        }
    });
    public static final RegistryObject<ParticleType<TrailData>> TRAIL = REGISTER.register("trail", () -> new ParticleType<>(false, TrailData.DESERIALIZER)
    {
        @Override
        public Codec<TrailData> codec()
        {
            return TrailData.CODEC;
        }
    });
}
