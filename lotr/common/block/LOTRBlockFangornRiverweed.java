/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLilyPad
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.world.IBlockAccess
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class LOTRBlockFangornRiverweed
extends BlockLilyPad {
    public LOTRBlockFangornRiverweed() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    @SideOnly(value=Side.CLIENT)
    public int getBlockColor() {
        return 16777215;
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int meta) {
        return 16777215;
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        return 16777215;
    }
}

