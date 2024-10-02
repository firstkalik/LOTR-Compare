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

public class LOTRBlockObsidian
extends Block {
    public LOTRBlockObsidian() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(50.0f);
        this.setResistance(2000.0f);
        this.setStepSound(soundTypePiston);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(0.7f);
    }
}

