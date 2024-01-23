/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenBaobab
extends WorldGenAbstractTree {
    private Block woodBlock = LOTRMod.wood4;
    private int woodMeta = 1;
    private Block leafBlock = LOTRMod.leaves4;
    private int leafMeta = 1;

    public LOTRWorldGenBaobab(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int k1;
        int i1;
        int k2;
        int j1;
        int i2;
        int j12;
        int trunkCircleWidth = 4;
        int height = 16 + random.nextInt(10);
        int xSlope = 5 + random.nextInt(10);
        if (random.nextBoolean()) {
            xSlope *= -1;
        }
        int zSlope = 5 + random.nextInt(10);
        if (random.nextBoolean()) {
            zSlope *= -1;
        }
        boolean flag = true;
        if (j >= 1 && j + height + 5 <= 256) {
            for (i1 = i - trunkCircleWidth - 1; i1 <= i + trunkCircleWidth + 1 && flag; ++i1) {
                for (k1 = k - trunkCircleWidth - 1; k1 <= k + trunkCircleWidth + 1 && flag; ++k1) {
                    i2 = Math.abs(i1 - i);
                    if (i2 * i2 + (k2 = Math.abs(k1 - k)) * k2 > trunkCircleWidth * trunkCircleWidth) continue;
                    for (j12 = j; j12 <= j + 1 + height; ++j12) {
                        if (j12 >= 0 && j12 < 256) {
                            Block block = world.getBlock(i1, j12, k1);
                            if (this.isReplaceable(world, i1, j12, k1) || block.isReplaceable((IBlockAccess)world, i1, j12, k1)) continue;
                            flag = false;
                            continue;
                        }
                        flag = false;
                    }
                    Block below = world.getBlock(i1, j - 1, k1);
                    boolean isSoil = below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling);
                    if (isSoil) continue;
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        if (!flag) {
            return false;
        }
        for (j1 = 0; j1 < height; ++j1) {
            for (int i12 = i - trunkCircleWidth - 1; i12 <= i + trunkCircleWidth + 1; ++i12) {
                for (int k12 = k - trunkCircleWidth - 1; k12 <= k + trunkCircleWidth + 1; ++k12) {
                    int k22;
                    int i22 = Math.abs(i12 - i);
                    if (i22 * i22 + (k22 = Math.abs(k12 - k)) * k22 > trunkCircleWidth * trunkCircleWidth) continue;
                    if (j1 == 0) {
                        world.getBlock(i12, j - 1, k12).onPlantGrow(world, i12, j - 1, k12, i12, j, k12);
                    }
                    this.setBlockAndNotifyAdequately(world, i12, j + j1, k12, this.woodBlock, this.woodMeta);
                }
            }
            if (j1 % xSlope == 0) {
                if (xSlope > 0) {
                    ++i;
                } else if (xSlope < 0) {
                    --i;
                }
            }
            if (j1 % zSlope != 0) continue;
            if (zSlope > 0) {
                ++k;
                continue;
            }
            if (zSlope >= 0) continue;
            --k;
        }
        for (j1 = j + height - 1; j1 > j + (int)((float)height * 0.75f); --j1) {
            int branches = 2 + random.nextInt(3);
            for (int l = 0; l < branches; ++l) {
                float angle = random.nextFloat() * 3.1415927f * 2.0f;
                int i13 = i;
                int k13 = k;
                int j2 = j1;
                int length = MathHelper.getRandomIntegerInRange((Random)random, (int)4, (int)6);
                for (int l1 = trunkCircleWidth; l1 < trunkCircleWidth + length && this.isReplaceable(world, i13 = i + (int)(1.5f + MathHelper.cos((float)angle) * (float)l1), j2 = j1 - 3 + l1 / 2, k13 = k + (int)(1.5f + MathHelper.sin((float)angle) * (float)l1)); ++l1) {
                    this.setBlockAndNotifyAdequately(world, i13, j2, k13, this.woodBlock, this.woodMeta);
                }
                int leafMin = 1 + random.nextInt(2);
                for (int j3 = j2 - leafMin; j3 <= j2; ++j3) {
                    int leafRange = 1 - (j3 - j2);
                    this.spawnLeaves(world, i13, j3, k13, leafRange);
                }
            }
        }
        for (i1 = i - trunkCircleWidth - 1; i1 <= i + trunkCircleWidth + 1; ++i1) {
            for (k1 = k - trunkCircleWidth - 1; k1 <= k + trunkCircleWidth + 1; ++k1) {
                i2 = Math.abs(i1 - i);
                if (i2 * i2 + (k2 = Math.abs(k1 - k)) * k2 > trunkCircleWidth * trunkCircleWidth || random.nextInt(5) != 0) continue;
                j12 = j + height;
                int topHeight = 2 + random.nextInt(3);
                for (int l = 0; l < topHeight; ++l) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, this.woodBlock, this.woodMeta);
                    ++j12;
                }
                int leafMin = 2;
                for (int j2 = j12 - leafMin; j2 <= j12; ++j2) {
                    int leafRange = 1 - (j2 - j12);
                    this.spawnLeaves(world, i1, j2, k1, leafRange);
                }
            }
        }
        return true;
    }

    private void spawnLeaves(World world, int i, int j, int k, int leafRange) {
        int leafRangeSq = leafRange * leafRange;
        for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
            for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                Block block;
                int i2 = i1 - i;
                int k2 = k1 - k;
                if (i2 * i2 + k2 * k2 > leafRangeSq || !(block = world.getBlock(i1, j, k1)).isReplaceable((IBlockAccess)world, i1, j, k1) && !block.isLeaves((IBlockAccess)world, i1, j, k1)) continue;
                this.setBlockAndNotifyAdequately(world, i1, j, k1, this.leafBlock, this.leafMeta);
            }
        }
    }
}

