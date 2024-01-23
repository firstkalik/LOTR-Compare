/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockFence;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

public class LOTRBlockFenceVanilla
extends LOTRBlockFence {
    public LOTRBlockFenceVanilla() {
        super(Blocks.planks);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
}

