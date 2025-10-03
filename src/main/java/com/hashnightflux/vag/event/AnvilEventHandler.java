package com.hashnightflux.vag.event;

import com.hashnightflux.vag.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AnvilEventHandler
{
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event)
    {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if(right.getItem() == Items.SPYGLASS)
        {
            ItemStack output = ItemStack.EMPTY;
            ItemStack scopeStack = ItemStack.EMPTY;

            if(left.getItem() == ModItems.BOLTCASTER.get())
            {
                output = left.copy();
                scopeStack = new ItemStack(ModItems.MEDIUM_SCOPE.get());
            }
            else if(left.getItem() == ModItems.MINIMACH.get())
            {
                output = left.copy();
                scopeStack = new ItemStack(ModItems.SHORT_SCOPE.get());
            }
            else if(left.getItem() == ModItems.SIDEHAMMER.get())
            {
                output = left.copy();
                scopeStack = new ItemStack(ModItems.LONG_SCOPE.get());
            }

            if(!output.isEmpty() && !scopeStack.isEmpty())
            {
                // Add scope attachment as ItemStack NBT
                CompoundTag tag = output.getOrCreateTag();
                CompoundTag attachments = tag.getCompound("Attachments");
                attachments.put("Scope", scopeStack.save(new CompoundTag()));
                tag.put("Attachments", attachments);
                output.setTag(tag);

                // Handle renaming
                if(event.getName() != null && !event.getName().isEmpty())
                {
                    output.setHoverName(net.minecraft.network.chat.Component.literal(event.getName()));
                }

                event.setOutput(output);
                event.setCost(2); // Set XP cost
        }
    }
}}