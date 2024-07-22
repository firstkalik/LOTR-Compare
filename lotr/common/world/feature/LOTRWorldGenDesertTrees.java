/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenDesertTrees
extends WorldGenAbstractTree {
    private boolean isNatural;
    private Block woodBlock;
    private int woodMeta;
    private Block leafBlock;
    private int leafMeta;

    public LOTRWorldGenDesertTrees(boolean flag, Block b1, int m1, Block b2, int m2) {
        super(flag);
        this.isNatural = !flag;
        this.woodBlock = b1;
        this.woodMeta = m1;
        this.leafBlock = b2;
        this.leafMeta = m2;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int i1;
        Block below;
        boolean isSoil;
        int j1;
        int height = 3 + random.nextInt(3);
        boolean flag = true;
        if (!this.isNatural) {
            if (j >= 1 && height + 1 <= 256) {
                for (j1 = j; j1 <= j + height + 1; ++j1) {
                    int range = 1;
                    if (j1 == j) {
                        range = 0;
                    }
                    if (j1 >= j + height - 1) {
                        range = 2;
                    }
                    for (i1 = i - range; i1 <= i + range && flag; ++i1) {
                        for (int k1 = k - range; k1 <= k + range && flag; ++k1) {
                            if (j1 >= 0 && j1 < 256) {
                                if (this.isReplaceable(world, i1, j1, k1)) continue;
                                flag = false;
                                continue;
                            }
                            flag = false;
                        }
                    }
                }
            } else {
                flag = false;
            }
        }
        boolean bl = isSoil = (below = world.getBlock(i, j - 1, k)).canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling) || this.isNatural && (below == Blocks.sand || below == Blocks.stone);
        if (!isSoil) {
            flag = false;
        }
        if (!flag) {
            return false;
        }
        below.onPlantGrow(world, i, j - 1, k, i, j, k);
        for (int branch = 0; branch < 4; ++branch) {
            int branchLength = 1 + random.nextInt(3);
            i1 = i;
            int j12 = j + height - 1 - random.nextInt(2);
            int k1 = k;
            for (int l = 0; l < branchLength; ++l) {
                if (random.nextInt(3) != 0) {
                    ++j12;
                }
                if (random.nextInt(3) != 0) {
                    switch (branch) {
                        case 0: {
                            --i1;
                            break;
                        }
                        case 1: {
                            ++k1;
                            break;
                        }
                        case 2: {
                            ++i1;
                            break;
                        }
                        case 3: {
                            --k1;
                        }
                    }
                }
                if (!this.isReplaceable(world, i1, j12, k1)) break;
                this.setBlockAndNotifyAdequately(world, i1, j12, k1, this.woodBlock, this.woodMeta);
            }
            int leafStart = 1;
            int leafRangeMin = 0;
            for (int j2 = j12 - leafStart; j2 <= j12 + 1; ++j2) {
                int j3 = j2 - j12;
                int leafRange = leafRangeMin + 1 - j3 / 2;
                for (int i2 = i1 - leafRange; i2 <= i1 + leafRange; ++i2) {
                    int i3 = i2 - i1;
                    for (int k2 = k1 - leafRange; k2 <= k1 + leafRange; ++k2) {
                        Block block;
                        int k3 = k2 - k1;
                        if (Math.abs(i3) == leafRange && Math.abs(k3) == leafRange && (random.nextInt(2) == 0 || j3 == 0) || !(block = world.getBlock(i2, j2, k2)).isReplaceable((IBlockAccess)world, i2, j2, k2) && !block.isLeaves((IBlockAccess)world, i2, j2, k2)) continue;
                        this.setBlockAndNotifyAdequately(world, i2, j2, k2, this.leafBlock, this.leafMeta);
                    }
                }
            }
        }
        for (j1 = j; j1 < j + height; ++j1) {
            this.setBlockAndNotifyAdequately(world, i, j1, k, this.woodBlock, this.woodMeta);
        }
        return true;
    }
}

