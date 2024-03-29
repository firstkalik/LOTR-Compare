/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockUtumnoSlabBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class LOTRBlockUtumnoSlab
extends LOTRBlockUtumnoSlabBase {
    public LOTRBlockUtumnoSlab(boolean flag) {
        super(flag, 6);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if ((j &= 7) == 0) {
            return LOTRMod.utumnoBrick.getIcon(i, 0);
        }
        if (j == 1) {
            return LOTRMod.utumnoBrick.getIcon(i, 2);
        }
        if (j == 2) {
            return LOTRMod.utumnoBrick.getIcon(i, 4);
        }
        if (j == 3) {
            return LOTRMod.utumnoPillar.getIcon(i, 0);
        }
        if (j == 4) {
            return LOTRMod.utumnoPillar.getIcon(i, 1);
        }
        if (j == 5) {
            return LOTRMod.utumnoPillar.getIcon(i, 2);
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}

