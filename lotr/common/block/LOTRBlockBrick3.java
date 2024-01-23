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

public class LOTRBlockBrick3
extends LOTRBlockBrickBase {
    public LOTRBlockBrick3() {
        this.setBrickNames("blueCarved", "redCarved", "highElven", "highElvenMossy", "highElvenCracked", "woodElven", "woodElvenMossy", "woodElvenCracked", "nearHaradCarved", "dolAmroth", "moredain", "nearHaradCracked", "dwarvenGlowing", "nearHaradRed", "nearHaradRedCracked", "nearHaradRedCarved");
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        if (world.getBlockMetadata(i, j, k) == 12) {
            return Blocks.glowstone.getLightValue();
        }
        return 0;
    }
}

