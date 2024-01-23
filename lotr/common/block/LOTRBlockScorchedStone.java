/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockScorchedStone
extends Block {
    public LOTRBlockScorchedStone() {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setResistance(10.0f);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }
}

