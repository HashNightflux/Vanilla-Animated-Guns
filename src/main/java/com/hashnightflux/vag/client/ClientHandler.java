package com.hashnightflux.vag.client;

import com.hashnightflux.vag.VanillaAnimatedGuns;
import com.hashnightflux.vag.Reference;
import com.hashnightflux.vag.client.handler.*;
import com.hashnightflux.vag.client.render.gun.ModelOverrides;
import com.hashnightflux.vag.client.render.gun.model.SimpleModel;
import com.hashnightflux.vag.client.util.PropertyHelper;
import com.hashnightflux.vag.debug.IEditorMenu;
import com.hashnightflux.vag.debug.client.screen.EditorScreen;
import com.hashnightflux.vag.enchantment.EnchantmentTypes;
import com.hashnightflux.vag.init.ModItems;
import com.hashnightflux.vag.item.GunItem;
import com.hashnightflux.vag.item.attachment.IAttachment;
import com.hashnightflux.vag.network.PacketHandler;
import com.hashnightflux.vag.network.message.C2SMessageAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.MouseSettingsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class ClientHandler
{
    private static Field mouseOptionsField;

    public static void setup()
    {
        MinecraftForge.EVENT_BUS.register(AimingHandler.get());
        MinecraftForge.EVENT_BUS.register(BulletTrailRenderingHandler.get());
        MinecraftForge.EVENT_BUS.register(CrosshairHandler.get());
        MinecraftForge.EVENT_BUS.register(GunRenderingHandler.get());
        MinecraftForge.EVENT_BUS.register(RecoilHandler.get());
        MinecraftForge.EVENT_BUS.register(ReloadHandler.get());
        MinecraftForge.EVENT_BUS.register(ShootingHandler.get());
        MinecraftForge.EVENT_BUS.register(new PlayerModelHandler());

        /* Only register controller events if Controllable is loaded otherwise it will crash */
        if(VanillaAnimatedGuns.controllableLoaded)
        {
            MinecraftForge.EVENT_BUS.register(new ControllerHandler());
            GunButtonBindings.register();
        }

        setupRenderLayers();
        registerModelOverrides();
        registerScreenFactories();
    }

    private static void setupRenderLayers() {
    }

    private static void registerModelOverrides()
    {
        /* Weapons */
        ModelOverrides.register(ModItems.BOLTCASTER.get(), new SimpleModel(SpecialModels.BOLTCASTER::getModel));
        ModelOverrides.register(ModItems.DYNAMITE_LAUNCHER.get(), new SimpleModel(SpecialModels.DYNAMITE_LAUNCHER::getModel));
        ModelOverrides.register(ModItems.MUSKET.get(), new SimpleModel(SpecialModels.MUSKET::getModel));
        ModelOverrides.register(ModItems.MINIMACH.get(), new SimpleModel(SpecialModels.MINIMACH::getModel));
        ModelOverrides.register(ModItems.HANDCANNON.get(), new SimpleModel(SpecialModels.HANDCANNON::getModel));
        ModelOverrides.register(ModItems.SIDEHAMMER.get(), new SimpleModel(SpecialModels.SIDEHAMMER::getModel));
        ModelOverrides.register(ModItems.BOOMSTICK.get(), new SimpleModel(SpecialModels.BOOMSTICK::getModel));
    }

    private static void registerScreenFactories()
    {
    }

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event)
    {
        if(event.getScreen() instanceof MouseSettingsScreen screen)
        {
            if(mouseOptionsField == null)
            {
                mouseOptionsField = ObfuscationReflectionHelper.findField(MouseSettingsScreen.class, "f_96218_");
                mouseOptionsField.setAccessible(true);
            }
            try
            {
                OptionsList list = (OptionsList) mouseOptionsField.get(screen);
                //list.addBig(OptionInstance.createBoolean("t", true));
                //list.addSmall(GunOptions.ADS_SENSITIVITY, GunOptions.CROSSHAIR);
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.Key event)
    {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player != null && mc.screen == null && event.getAction() == GLFW.GLFW_PRESS)
        {
            {
                PacketHandler.getPlayChannel().sendToServer(new C2SMessageAttachments());
            }
            /*else if(event.getKey() == GLFW.GLFW_KEY_KP_9)
            {
                mc.setScreen(new EditorScreen(null, new Debug.Menu()));
            }*/
        }
    }

    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event)
    {
        event.registerReloadListener((ResourceManagerReloadListener) manager -> {
            PropertyHelper.resetCache();
        });
    }

    public static void registerAdditional(ModelEvent.RegisterAdditional event)
    {
        event.register(new ResourceLocation(Reference.MOD_ID, "special/test"));
    }

    public static void onRegisterCreativeTab(CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "creative_tab"), builder ->
        {
            builder.title(Component.translatable("itemGroup." + Reference.MOD_ID));
            builder.icon(() -> {
                ItemStack stack = new ItemStack(ModItems.HANDCANNON.get());
                stack.getOrCreateTag().putBoolean("IgnoreAmmo", true);
                return stack;
            });
            builder.displayItems((flags, output) ->
            {
                ModItems.REGISTER.getEntries().forEach(registryObject ->
                {
                    if (registryObject.get() instanceof GunItem item)
{
    ItemStack stack = new ItemStack(item);
    CompoundTag tag = stack.getOrCreateTag();

    // Default ammo
    tag.putInt("AmmoCount", item.getGun().getGeneral().getMaxAmmo());

    // Attachments tag
    CompoundTag attachments = new CompoundTag();

    String id = ForgeRegistries.ITEMS.getKey(item).getPath();

    tag.put("Attachments", attachments);
    output.accept(stack);
    return;
}

        ResourceLocation id = ForgeRegistries.ITEMS.getKey(registryObject.get());
        if (id != null)
        {
            String path = id.getPath();
            if (path.contains("barrel") || path.contains("scope") || path.contains("stock"))
                return; // Do NOT accept these
        }

        output.accept(registryObject.get());
                });
                CustomGunManager.fill(output);
                for(Enchantment enchantment : ForgeRegistries.ENCHANTMENTS)
                {
                    if(enchantment.category == EnchantmentTypes.GUN || enchantment.category == EnchantmentTypes.SEMI_AUTO_GUN)
                    {
                        output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantment.getMaxLevel())), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                    }
                }
            });
        });
    }

    public static Screen createEditorScreen(IEditorMenu menu)
    {
        return new EditorScreen(Minecraft.getInstance().screen, menu);
    }

    /* Uncomment for debugging headshot hit boxes */

    /*@SubscribeEvent
    @SuppressWarnings("unchecked")
    public static void onRenderLiving(RenderLivingEvent.Post event)
    {
        LivingEntity entity = event.getEntity();
        IHeadshotBox<LivingEntity> headshotBox = (IHeadshotBox<LivingEntity>) BoundingBoxManager.getHeadshotBoxes(entity.getType());
        if(headshotBox != null)
        {
            AxisAlignedBB box = headshotBox.getHeadshotBox(entity);
            if(box != null)
            {
                WorldRenderer.drawBoundingBox(event.getMatrixStack(), event.getBuffers().getBuffer(RenderType.getLines()), box, 1.0F, 1.0F, 0.0F, 1.0F);

                AxisAlignedBB boundingBox = entity.getBoundingBox().offset(entity.getPositionVec().inverse());
                boundingBox = boundingBox.grow(Config.COMMON.gameplay.growBoundingBoxAmount.get(), 0, Config.COMMON.gameplay.growBoundingBoxAmount.get());
                WorldRenderer.drawBoundingBox(event.getMatrixStack(), event.getBuffers().getBuffer(RenderType.getLines()), boundingBox, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }*/
}
