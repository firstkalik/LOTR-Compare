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

public class LOTRBlockKebab
extends Block {
    public LOTRBlockKebab() {
        super(Material.sand);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
        this.setHardness(0.5f);
        this.setStepSound(Block.soundTypeWood);
    }
}

