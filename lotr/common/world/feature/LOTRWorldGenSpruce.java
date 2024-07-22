/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRWorldGenSpruce
extends WorldGenAbstractTree {
    public LOTRWorldGenSpruce(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int i;
        int j;
        int k;
        Block g = world.getBlock(x, y - 1, z);
        if (g != Blocks.grass && g != Blocks.dirt) {
            return false;
        }
        int start = 1;
        int small = 2;
        int large = 0;
        int treeSize = rand.nextInt(2) + 1;
        if (treeSize == 1) {
            small = 2;
            large = 2;
        } else if (treeSize == 2) {
            start = 1 + rand.nextInt(2);
            small = 3;
            large = 3;
        }
        for (i = 0; i < start; ++i) {
            this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.log, 1);
            ++y;
        }
        for (i = 0; i < large; ++i) {
            for (j = -2; j <= 2; ++j) {
                for (k = -2; k <= 2; ++k) {
                    if (Math.abs(j) + Math.abs(k) == 4 || (j <= -2 || k <= -2 || j >= 2 || k >= 2) && rand.nextInt(4) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, x + j, y, z + k, (Block)Blocks.leaves, 1);
                }
            }
            this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.log, 1);
            ++y;
        }
        for (i = 0; i < small; ++i) {
            for (j = -1; j <= 1; ++j) {
                for (k = -1; k <= 1; ++k) {
                    if (Math.abs(j) + Math.abs(k) >= 2 && rand.nextInt(4) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, x + j, y, z + k, (Block)Blocks.leaves, 1);
                }
            }
            if (i == 0) {
                this.setBlockAndNotifyAdequately(world, x + 1, y, z, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x - 1, y, z, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x, y, z + 1, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x, y, z - 1, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x + 2, y, z, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x - 2, y, z, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x, y, z + 2, (Block)Blocks.leaves, 1);
                this.setBlockAndNotifyAdequately(world, x, y, z - 2, (Block)Blocks.leaves, 1);
            }
            this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.log, 1);
            ++y;
        }
        this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.log, 1);
        this.setBlockAndNotifyAdequately(world, x + 1, y, z, (Block)Blocks.leaves, 1);
        this.setBlockAndNotifyAdequately(world, x - 1, y, z, (Block)Blocks.leaves, 1);
        this.setBlockAndNotifyAdequately(world, x, y, z + 1, (Block)Blocks.leaves, 1);
        this.setBlockAndNotifyAdequately(world, x, y, z - 1, (Block)Blocks.leaves, 1);
        this.setBlockAndNotifyAdequately(world, x, y + 1, z, (Block)Blocks.leaves, 1);
        this.setBlockAndNotifyAdequately(world, x, y + 2, z, (Block)Blocks.leaves, 1);
        return true;
    }
}

