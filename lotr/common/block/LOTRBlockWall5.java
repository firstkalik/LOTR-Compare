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

public class LOTRBlockWall5
extends LOTRBlockWallBase {
    public LOTRBlockWall5() {
        super(LOTRMod.brick, 3);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j == 0) {
            return LOTRMod.brick6.getIcon(i, 6);
        }
        if (j == 1) {
            return LOTRMod.brick6.getIcon(i, 7);
        }
        if (j == 2) {
            return LOTRMod.brick6.getIcon(i, 10);
        }
        return super.getIcon(i, j);
    }
}

