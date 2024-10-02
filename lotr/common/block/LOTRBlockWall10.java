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

public class LOTRBlockWall10
extends LOTRBlockWallBase {
    public LOTRBlockWall10() {
        super(LOTRMod.brick10, 3);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j == 0) {
            return LOTRMod.brick10.getIcon(i, 1);
        }
        if (j == 1) {
            return LOTRMod.brick10.getIcon(i, 2);
        }
        if (j == 2) {
            return LOTRMod.brick10.getIcon(i, 3);
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

