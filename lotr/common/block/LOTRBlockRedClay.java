/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRBlockRedClay
extends Block {
    public LOTRBlockRedClay() {
        super(Material.clay);
        this.setHardness(0.6f);
        this.setStepSound(Block.soundTypeGravel);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return LOTRMod.redClayBall;
    }

    public int quantityDropped(Random random) {
        return 4;
    }
}

