/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemReed
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockPlaceableFood;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;

public class LOTRItemPlaceableFood
extends ItemReed {
    public LOTRItemPlaceableFood(Block block) {
        super(block);
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
        ((LOTRBlockPlaceableFood)block).foodItem = this;
    }
}

