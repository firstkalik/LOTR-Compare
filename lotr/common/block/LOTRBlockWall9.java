/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockWallBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class LOTRBlockWall9
extends LOTRBlockWallBase {
    public LOTRBlockWall9() {
        super(LOTRMod.brick9, 7);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j == 0) {
            return LOTRMod.brick9.getIcon(i, 4);
        }
        if (j == 1) {
            return LOTRMod.brick9.getIcon(i, 12);
        }
        if (j == 2) {
            return LOTRMod.brick9.getIcon(i, 14);
        }
        if (j == 3) {
            return LOTRMod.brick9.getIcon(i, 15);
        }
        if (j == 4) {
            return LOTRMod.brick9.getIcon(i, 13);
        }
        if (j == 5) {
            return LOTRMod.brick10.getIcon(i, 0);
        }
        if (j == 6) {
            return LOTRMod.brick8.getIcon(i, 1);
        }
        return super.getIcon(i, j);
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        if (world.getBlockMetadata(i, j, k) == 6) {
            return Blocks.glowstone.getLightValue();
        }
        return 0;
    }
}

