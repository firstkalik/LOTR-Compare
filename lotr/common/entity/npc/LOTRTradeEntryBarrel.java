/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTRTradeEntry;
import lotr.common.item.LOTRItemBarrel;
import lotr.common.recipe.LOTRBrewingRecipes;
import lotr.common.tileentity.LOTRTileEntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRTradeEntryBarrel
extends LOTRTradeEntry {
    public LOTRTradeEntryBarrel(ItemStack itemstack, int cost) {
        super(itemstack, cost);
    }

    @Override
    public ItemStack createTradeItem() {
        ItemStack drinkItem = super.createTradeItem();
        ItemStack barrelItem = new ItemStack(LOTRMod.barrel);
        LOTRTileEntityBarrel barrel = new LOTRTileEntityBarrel();
        barrel.setInventorySlotContents(9, new ItemStack(drinkItem.getItem(), LOTRBrewingRecipes.BARREL_CAPACITY, drinkItem.getItemDamage()));
        barrel.barrelMode = 2;
        LOTRItemBarrel.setBarrelDataFromTE(barrelItem, barrel);
        return barrelItem;
    }
}

