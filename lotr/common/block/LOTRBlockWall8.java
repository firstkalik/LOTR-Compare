/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockWallBase;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class LOTRBlockWall8
extends LOTRBlockWallBase {
    public LOTRBlockWall8() {
        super(LOTRMod.brick8, 8);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j == 0) {
            return LOTRMod.brick9.getIcon(i, 0);
        }
        if (j == 1) {
            return LOTRMod.brick9.getIcon(i, 1);
        }
        if (j == 2) {
            return LOTRMod.brick9.getIcon(i, 2);
        }
        if (j == 3) {
            return LOTRMod.brick9.getIcon(i, 3);
        }
        if (j == 4) {
            return LOTRMod.brick9.getIcon(i, 5);
        }
        if (j == 5) {
            return LOTRMod.brick9.getIcon(i, 6);
        }
        if (j == 6) {
            return LOTRMod.brick9.getIcon(i, 9);
        }
        if (j == 7) {
            return LOTRMod.brick9.getIcon(i, 10);
        }
        return super.getIcon(i, j);
    }
}

