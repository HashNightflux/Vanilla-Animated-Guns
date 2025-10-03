package com.hashnightflux.vag.item;

import com.hashnightflux.vag.item.attachment.IScope;
import com.hashnightflux.vag.item.attachment.impl.Scope;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * A basic scope attachment item implementation
 *
 * Author: MrCrayfish
 */
public class ScopeItem extends AttachmentItem implements IScope
{
    private final Scope scope;

    public ScopeItem(Scope scope, Item.Properties properties)
    {
        super(properties);
        this.scope = scope;
    }

    public ScopeItem(Scope scope, Item.Properties properties, boolean colored)
    {
        super(properties);
        this.scope = scope;
    }

    @Override
    public Scope getProperties()
    {
        return this.scope;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
    {
        return enchantment == Enchantments.BINDING_CURSE || super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
