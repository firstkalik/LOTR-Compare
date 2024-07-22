/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockStairs2
extends BlockStairs {
    public LOTRBlockStairs2(Block block, int metadata) {
        super(block, metadata);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.useNeighborBrightness = true;
    }
}

