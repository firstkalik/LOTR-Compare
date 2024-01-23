/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockSlabBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class LOTRBlockSlabV
extends LOTRBlockSlabBase {
    public LOTRBlockSlabV(boolean flag) {
        super(flag, Material.rock, 6);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if ((j &= 7) == 0) {
            return Blocks.stonebrick.getIcon(i, 1);
        }
        if (j == 1) {
            return Blocks.stonebrick.getIcon(i, 2);
        }
        if (j == 2) {
            return LOTRMod.redBrick.getIcon(i, 0);
        }
        if (j == 3) {
            return LOTRMod.redBrick.getIcon(i, 1);
        }
        if (j == 4) {
            return Blocks.mossy_cobblestone.getIcon(i, 0);
        }
        if (j == 5) {
            return Blocks.stone.getIcon(i, 0);
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}

