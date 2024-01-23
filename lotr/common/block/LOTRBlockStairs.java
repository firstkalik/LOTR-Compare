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

public class LOTRBlockStairs
extends BlockStairs {
    private Block baseBlock;
    private int baseMeta;

    public LOTRBlockStairs(Block block, int meta) {
        super(block, meta);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.useNeighborBrightness = true;
        this.baseBlock = block;
        this.baseMeta = meta;
    }
}

