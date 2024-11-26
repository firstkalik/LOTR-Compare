/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockBrickBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class LOTRBlockBrick8
extends LOTRBlockBrickBase {
    public LOTRBlockBrick8() {
        this.setBrickNames("reddwarvenGlowing", "mithrilGlowing", "luigonGlowing", "greyGlowing");
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        if (world.getBlockMetadata(i, j, k) == 0) {
            return Blocks.glowstone.getLightValue();
        }
        if (world.getBlockMetadata(i, j, k) == 1) {
            return Blocks.glowstone.getLightValue();
        }
        if (world.getBlockMetadata(i, j, k) == 2) {
            return Blocks.glowstone.getLightValue();
        }
        if (world.getBlockMetadata(i, j, k) == 3) {
            return Blocks.glowstone.getLightValue();
        }
        return 1;
    }
}

