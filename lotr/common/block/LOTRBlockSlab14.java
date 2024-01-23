/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
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
import net.minecraft.util.IIcon;

public class LOTRBlockSlab14
extends LOTRBlockSlabBase {
    public LOTRBlockSlab14(boolean flag) {
        super(flag, Material.rock, 5);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if ((j &= 7) == 0) {
            return LOTRMod.pillar2.getIcon(i, 14);
        }
        if (j == 1) {
            return LOTRMod.brick6.getIcon(i, 11);
        }
        if (j == 2) {
            return LOTRMod.brick6.getIcon(i, 13);
        }
        if (j == 3) {
            return LOTRMod.pillar2.getIcon(i, 15);
        }
        if (j == 4) {
            return LOTRMod.pillar3.getIcon(i, 0);
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}

