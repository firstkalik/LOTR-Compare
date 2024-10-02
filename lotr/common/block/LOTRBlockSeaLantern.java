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

public class LOTRBlockSeaLantern
extends Block {
    public LOTRBlockSeaLantern() {
        super(Material.glass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(0.3f);
        this.setLightLevel(1.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeGlass);
    }
}

