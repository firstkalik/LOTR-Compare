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

public class LOTRBlockSlab121
extends LOTRBlockSlabBase {
    public LOTRBlockSlab121(boolean flag) {
        super(flag, Material.rock, 8);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if ((j &= 7) == 0) {
            return LOTRMod.brick10.getIcon(i, 3);
        }
        if (j == 1) {
            return LOTRMod.brick10.getIcon(i, 2);
        }
        if (j == 2) {
            return LOTRMod.brick10.getIcon(i, 1);
        }
        if (j == 3) {
            return LOTRMod.pillar4.getIcon(i, 6);
        }
        if (j == 4) {
            return LOTRMod.pillar4.getIcon(i, 7);
        }
        if (j == 5) {
            return LOTRMod.brick10.getIcon(i, 4);
        }
        if (j == 6) {
            return LOTRMod.brick10.getIcon(i, 5);
        }
        if (j == 7) {
            return LOTRMod.brick11.getIcon(i, 2);
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}

