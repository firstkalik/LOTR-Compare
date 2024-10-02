/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockMallornTorch4
extends BlockTorch {
    private int torchColor;

    public LOTRBlockMallornTorch4(int color) {
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeWood);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setLightLevel(0.875f);
        this.torchColor = color;
    }
}

