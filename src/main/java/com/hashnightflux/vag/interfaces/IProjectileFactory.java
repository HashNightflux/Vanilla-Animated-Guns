package com.hashnightflux.vag.interfaces;

import com.hashnightflux.vag.common.Gun;
import com.hashnightflux.vag.common.ProjectileManager;
import com.hashnightflux.vag.entity.ProjectileEntity;
import com.hashnightflux.vag.item.GunItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * This class allows weapons to fire custom projectiles instead of the default implementation. The
 * dynamite launcher uses this to spawn a dynamite entity with custom physics. Use {@link ProjectileManager}
 * to register a factory.
 *
 * Author: MrCrayfish
 */
public interface IProjectileFactory
{
    /**
     * Creates a new projectile entity.
     *
     * @param worldIn     the world the projectile is going to be spawned into
     * @param entity      the entity who fired the weapon
     * @param weapon      the item stack of the weapon
     * @param item        the gun item
     * @param modifiedGun the properties of the gun
     * @return a projectile entity
     */
    ProjectileEntity create(Level worldIn, LivingEntity entity, ItemStack weapon, GunItem item, Gun modifiedGun);
}
