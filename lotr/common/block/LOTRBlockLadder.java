/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLadder
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockLadder
extends BlockLadder {
    public LOTRBlockLadder() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }
}

