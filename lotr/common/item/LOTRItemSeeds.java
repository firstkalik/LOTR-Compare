/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSeeds
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class LOTRItemSeeds
extends ItemSeeds {
    public LOTRItemSeeds(Block crop, Block soil) {
        super(crop, soil);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
    }
}

