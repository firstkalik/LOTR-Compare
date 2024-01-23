/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPane
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockPane
extends BlockPane {
    public LOTRBlockPane(String s, String s1, Material material, boolean flag) {
        super(s, s1, material, flag);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }
}

