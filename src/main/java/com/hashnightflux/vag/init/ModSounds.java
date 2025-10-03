package com.hashnightflux.vag.init;

import com.hashnightflux.vag.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds 
{
	public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

	public static final RegistryObject<SoundEvent> ITEM_HANDCANNON_FIRE = register("item.handcannon.fire");
	public static final RegistryObject<SoundEvent> ITEM_HANDCANNON_SILENCED_FIRE = register("item.handcannon.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_HANDCANNON_ENCHANTED_FIRE = register("item.handcannon.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HANDCANNON_RELOAD = register("item.handcannon.reload");
	public static final RegistryObject<SoundEvent> ITEM_HANDCANNON_COCK = register("item.handcannon.cock");
	public static final RegistryObject<SoundEvent> ITEM_BOOMSTICK_FIRE = register("item.boomstick.fire");
	public static final RegistryObject<SoundEvent> ITEM_BOOMSTICK_SILENCED_FIRE = register("item.boomstick.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_BOOMSTICK_ENCHANTED_FIRE = register("item.boomstick.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_BOOMSTICK_COCK = register("item.boomstick.cock");
	public static final RegistryObject<SoundEvent> ITEM_SIDEHAMMER_FIRE = register("item.sidehammer.fire");
	public static final RegistryObject<SoundEvent> ITEM_SIDEHAMMER_SILENCED_FIRE = register("item.sidehammer.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_SIDEHAMMER_ENCHANTED_FIRE = register("item.sidehammer.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_SIDEHAMMER_COCK = register("item.sidehammer.cock");
	public static final RegistryObject<SoundEvent> ITEM_BOLTCASTER_FIRE = register("item.boltcaster.fire");
	public static final RegistryObject<SoundEvent> ITEM_BOLTCASTER_SILENCED_FIRE = register("item.boltcaster.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_BOLTCASTER_ENCHANTED_FIRE = register("item.boltcaster.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_BOLTCASTER_COCK = register("item.boltcaster.cock");
	public static final RegistryObject<SoundEvent> ITEM_DYNAMITE_LAUNCHER_FIRE = register("item.dynamite_launcher.fire");
	public static final RegistryObject<SoundEvent> ITEM_MINIMACH_FIRE = register("item.minimach.fire");
	public static final RegistryObject<SoundEvent> ITEM_MINIMACH_SILENCED_FIRE = register("item.minimach.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_MINIMACH_ENCHANTED_FIRE = register("item.minimach.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_MUSKET_FIRE = register("item.musket.fire");
	public static final RegistryObject<SoundEvent> ITEM_MUSKET_SILENCED_FIRE = register("item.musket.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_MUSKET_ENCHANTED_FIRE = register("item.musket.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_MUSKET_COCK = register("item.musket.cock");
	public static final RegistryObject<SoundEvent> ITEM_DYNAMITE_PIN = register("item.dynamite.pin");
	public static final RegistryObject<SoundEvent> UI_WEAPON_ATTACH = register("ui.weapon.attach");

	private static RegistryObject<SoundEvent> register(String key)
	{
		return REGISTER.register(key, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, key)));
	}
}
