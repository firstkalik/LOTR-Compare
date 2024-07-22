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
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenPartyTrees
extends WorldGenAbstractTree {
    private Block woodBlock;
    private int woodMeta;
    private Block leafBlock;
    private int leafMeta;
    private boolean restrictions = true;

    public LOTRWorldGenPartyTrees(Block block, int i, Block block1, int j) {
        super(false);
        this.woodBlock = block;
        this.woodMeta = i;
        this.leafBlock = block1;
        this.leafMeta = j;
    }

    public LOTRWorldGenPartyTrees disableRestrictions() {
        this.restrictions = false;
        return this;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int k1;
        int i1;
        int trunkWidth = 1;
        int height = random.nextInt(12) + 12;
        boolean flag = true;
        if (this.restrictions) {
            if (j >= 1 && j + height + 1 <= 256) {
                for (j1 = j; j1 <= j + 1 + height; ++j1) {
                    int range = trunkWidth + 1;
                    if (j1 == j) {
                        range = trunkWidth;
                    }
                    for (int i12 = i - range; i12 <= i + range && flag; ++i12) {
                        for (int k12 = k - range; k12 <= k + range && flag; ++k12) {
                            if (j1 >= 0 && j1 < 256) {
                                if (this.isReplaceable(world, i12, j1, k12)) continue;
                                flag = false;
                                continue;
                            }
                            flag = false;
                        }
                    }
                }
                for (i1 = i - trunkWidth; i1 <= i + trunkWidth && flag; ++i1) {
                    for (k1 = k - trunkWidth; k1 <= k + trunkWidth && flag; ++k1) {
                        Block block = world.getBlock(i1, j - 1, k1);
                        if (block.canSustainPlant((IBlockAccess)world, i1, j - 1, k1, ForgeDirection.UP, (IPlantable)Blocks.sapling)) continue;
                        flag = false;
                    }
                }
                if (!flag) {
                    return false;
                }
            } else {
                return false;
            }
        }
        for (i1 = i - trunkWidth; i1 <= i + trunkWidth; ++i1) {
            for (k1 = k - trunkWidth; k1 <= k + trunkWidth; ++k1) {
                world.getBlock(i1, j - 1, k1).onPlantGrow(world, i1, j - 1, k1, i1, j, k1);
            }
        }
        for (j1 = 0; j1 < height; ++j1) {
            for (int i13 = i - trunkWidth; i13 <= i + trunkWidth; ++i13) {
                for (int k13 = k - trunkWidth; k13 <= k + trunkWidth; ++k13) {
                    this.setBlockAndNotifyAdequately(world, i13, j + j1, k13, this.woodBlock, this.woodMeta);
                }
            }
        }
        int angle = 0;
        while (angle < 360) {
            float angleR = (float)(angle += 20 + random.nextInt(25)) / 180.0f * 3.1415927f;
            float sin = MathHelper.sin((float)angleR);
            float cos = MathHelper.cos((float)angleR);
            int boughLength = 6 + random.nextInt(6);
            int boughThickness = Math.round((float)boughLength / 20.0f * 1.5f);
            int boughBaseHeight = j + MathHelper.floor_double((double)((float)height * (0.75f + random.nextFloat() * 0.25f)));
            int boughHeight = 3 + random.nextInt(4);
            for (int l = 0; l < boughLength; ++l) {
                int i14 = i + Math.round(sin * (float)l);
                int k14 = k + Math.round(cos * (float)l);
                int j12 = boughBaseHeight + Math.round((float)l / (float)boughLength * (float)boughHeight);
                int range = boughThickness - Math.round((float)l / (float)boughLength * (float)boughThickness * 0.5f);
                for (int i2 = i14 - range; i2 <= i14 + range; ++i2) {
                    for (int j2 = j12 - range; j2 <= j12 + range; ++j2) {
                        for (int k2 = k14 - range; k2 <= k14 + range; ++k2) {
                            Block block = world.getBlock(i2, j2, k2);
                            if (!block.isReplaceable((IBlockAccess)world, i2, j2, k2) && !block.isLeaves((IBlockAccess)world, i2, j2, k2)) continue;
                            this.setBlockAndNotifyAdequately(world, i2, j2, k2, this.woodBlock, this.woodMeta | 0xC);
                        }
                    }
                }
                int branch_angle = angle + random.nextInt(360);
                float branch_angleR = (float)branch_angle / 180.0f * 3.1415927f;
                float branch_sin = MathHelper.sin((float)branch_angleR);
                float branch_cos = MathHelper.cos((float)branch_angleR);
                int branchLength = 4 + random.nextInt(4);
                int branchHeight = random.nextInt(5);
                int leafRange = 3;
                for (int l1 = 0; l1 < branchLength; ++l1) {
                    int j2;
                    int i2 = i14 + Math.round(branch_sin * (float)l1);
                    int k2 = k14 + Math.round(branch_cos * (float)l1);
                    for (int j3 = j2 = j12 + Math.round((float)l1 / (float)branchLength * (float)branchHeight); j3 >= j2 - 1; --j3) {
                        Block block = world.getBlock(i2, j3, k2);
                        if (!block.isReplaceable((IBlockAccess)world, i2, j3, k2) && !block.isLeaves((IBlockAccess)world, i2, j3, k2)) continue;
                        this.setBlockAndNotifyAdequately(world, i2, j3, k2, this.woodBlock, this.woodMeta | 0xC);
                    }
                    if (l1 != branchLength - 1) continue;
                    for (int i3 = i2 - leafRange; i3 <= i2 + leafRange; ++i3) {
                        for (int j3 = j2 - leafRange; j3 <= j2 + leafRange; ++j3) {
                            for (int k3 = k2 - leafRange; k3 <= k2 + leafRange; ++k3) {
                                Block block2;
                                int i4 = i3 - i2;
                                int j4 = j3 - j2;
                                int k4 = k3 - k2;
                                int dist = i4 * i4 + j4 * j4 + k4 * k4;
                                if (dist >= (leafRange - 1) * (leafRange - 1) && (dist >= leafRange * leafRange || random.nextInt(3) == 0) || !(block2 = world.getBlock(i3, j3, k3)).isReplaceable((IBlockAccess)world, i3, j3, k3) && !block2.isLeaves((IBlockAccess)world, i3, j3, k3)) continue;
                                this.setBlockAndNotifyAdequately(world, i3, j3, k3, this.leafBlock, this.leafMeta);
                            }
                        }
                    }
                }
            }
        }
        int roots = 5 + random.nextInt(5);
        for (int l = 0; l < roots; ++l) {
            int i15 = i;
            int j13 = j + 1 + random.nextInt(5);
            int k15 = k;
            int xDirection = 0;
            int zDirection = 0;
            int rootLength = 2 + random.nextInt(4);
            if (random.nextBoolean()) {
                if (random.nextBoolean()) {
                    i15 -= trunkWidth + 1;
                    xDirection = -1;
                } else {
                    i15 += trunkWidth + 1;
                    xDirection = 1;
                }
                k15 -= trunkWidth + 1;
                k15 += random.nextInt(trunkWidth * 2 + 2);
            } else {
                if (random.nextBoolean()) {
                    k15 -= trunkWidth + 1;
                    zDirection = -1;
                } else {
                    k15 += trunkWidth + 1;
                    zDirection = 1;
                }
                i15 -= trunkWidth + 1;
                i15 += random.nextInt(trunkWidth * 2 + 2);
            }
            for (int l1 = 0; l1 < rootLength; ++l1) {
                Block block;
                int rootBlocks = 0;
                int j2 = j13;
                while (!world.getBlock(i15, j2, k15).isOpaqueCube() && ((block = world.getBlock(i15, j2, k15)).isReplaceable((IBlockAccess)world, i15, j2, k15) || block.isLeaves((IBlockAccess)world, i15, j2, k15))) {
                    this.setBlockAndNotifyAdequately(world, i15, j2, k15, this.woodBlock, this.woodMeta | 0xC);
                    world.getBlock(i15, j2 - 1, k15).onPlantGrow(world, i15, j2 - 1, k15, i15, j2, k15);
                    if (++rootBlocks > 5) break;
                    --j2;
                }
                --j13;
                if (!random.nextBoolean()) continue;
                if (xDirection == -1) {
                    --i15;
                    continue;
                }
                if (xDirection == 1) {
                    ++i15;
                    continue;
                }
                if (zDirection == -1) {
                    --k15;
                    continue;
                }
                if (zDirection != 1) continue;
                ++k15;
            }
        }
        return true;
    }
}

