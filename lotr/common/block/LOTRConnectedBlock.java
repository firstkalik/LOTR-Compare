/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.IBlockAccess
 */
package lotr.common.block;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface LOTRConnectedBlock {
    public String getConnectedName(int var1);

    public boolean areBlocksConnected(IBlockAccess var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public static class Checks {
        public static boolean matchBlockAndMeta(Block block, IBlockAccess world, int i, int j, int k, int i1, int j1, int k1) {
            int meta = world.getBlockMetadata(i, j, k);
            Block otherBlock = world.getBlock(i1, j1, k1);
            int otherMeta = world.getBlockMetadata(i1, j1, k1);
            return otherBlock == block && meta == otherMeta;
        }
    }

}

