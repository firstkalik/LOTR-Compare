/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockGravel
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockGravel
extends BlockGravel {
    public LOTRBlockGravel() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(0.6f);
        this.setStepSound(Block.soundTypeGravel);
    }
}

