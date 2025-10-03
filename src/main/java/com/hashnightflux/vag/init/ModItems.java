package com.hashnightflux.vag.init;

import com.hashnightflux.vag.Reference;
import com.hashnightflux.vag.common.Attachments;
import com.hashnightflux.vag.item.AmmoItem;
import com.hashnightflux.vag.item.GrenadeItem;
import com.hashnightflux.vag.item.GunItem;
import com.hashnightflux.vag.item.ScopeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<GunItem> HANDCANNON = REGISTER.register("handcannon", () -> new GunItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BOOMSTICK = REGISTER.register("boomstick", () -> new GunItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SIDEHAMMER = REGISTER.register("sidehammer", () -> new GunItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DYNAMITE_LAUNCHER = REGISTER.register("dynamite_launcher", () -> new GunItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<GunItem> BOLTCASTER = REGISTER.register("boltcaster", () -> new GunItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MINIMACH = REGISTER.register("minimach", () -> new GunItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MUSKET = REGISTER.register("musket", () -> new GunItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> POWDERED_PELLET = REGISTER.register("powdered_pellet", () -> new AmmoItem(new Item.Properties()));
    public static final RegistryObject<Item> BOLT = REGISTER.register("bolt", () -> new AmmoItem(new Item.Properties()));
    public static final RegistryObject<Item> SHELL = REGISTER.register("shell", () -> new AmmoItem(new Item.Properties()));
    public static final RegistryObject<Item> DYNAMITE = REGISTER.register("dynamite", () -> new GrenadeItem(new Item.Properties(), 20 * 4));

    /* Scope Attachments */
    public static final RegistryObject<Item> SHORT_SCOPE = REGISTER.register("short_scope", () -> new ScopeItem(Attachments.SHORT_SCOPE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MEDIUM_SCOPE = REGISTER.register("medium_scope", () -> new ScopeItem(Attachments.MEDIUM_SCOPE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LONG_SCOPE = REGISTER.register("long_scope", () -> new ScopeItem(Attachments.LONG_SCOPE, new Item.Properties().stacksTo(1)));
}
