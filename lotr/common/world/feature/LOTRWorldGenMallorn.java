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

public class LOTRWorldGenMallorn
extends WorldGenAbstractTree {
    private int minHeight = 10;
    private int maxHeight = 14;
    private Block woodBlock = LOTRMod.wood;
    private int woodMeta = 1;
    private Block leafBlock = LOTRMod.leaves;
    private int leafMeta = 1;

    public LOTRWorldGenMallorn(boolean flag) {
        super(flag);
    }

    public LOTRWorldGenMallorn setMinMaxHeight(int min, int max) {
        this.minHeight = min;
        this.maxHeight = max;
        return this;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int height = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minHeight, (int)this.maxHeight);
        int leafMin = j + (int)((float)height * 0.6f);
        boolean flag = true;
        if (j >= 1 && j + height + 1 <= 256) {
            for (int j1 = j; j1 <= j + height + 1; ++j1) {
                int range = 1;
                if (j1 == j) {
                    range = 0;
                }
                if (j1 >= leafMin) {
                    range = 2;
                }
                for (int i1 = i - range; i1 <= i + range && flag; ++i1) {
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
            if (!flag) {
                return false;
            }
            boolean canGrow = true;
            Block below = world.getBlock(i, j - 1, k);
            if (!below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling)) {
                canGrow = false;
            }
            if (canGrow) {
                int j1;
                below = world.getBlock(i, j - 1, k);
                below.onPlantGrow(world, i, j - 1, k, i, j, k);
                int deg = 0;
                for (j1 = j + height; j1 >= leafMin; --j1) {
                    int branches = 1 + random.nextInt(2);
                    for (int b = 0; b < branches; ++b) {
                        float angle = (float)Math.toRadians(deg += 50 + random.nextInt(70));
                        float cos = MathHelper.cos((float)angle);
                        float sin = MathHelper.sin((float)angle);
                        float angleY = random.nextFloat() * (float)Math.toRadians(50.0);
                        MathHelper.cos((float)angleY);
                        float sinY = MathHelper.sin((float)angleY);
                        int length = 4 + random.nextInt(6);
                        int i1 = i;
                        int k1 = k;
                        int j2 = j1;
                        for (int l = 0; l < length; ++l) {
                            Block block;
                            if (Math.floor(cos * (float)l) != Math.floor(cos * (float)(l - 1))) {
                                i1 = (int)((float)i1 + Math.signum(cos));
                            }
                            if (Math.floor(sin * (float)l) != Math.floor(sin * (float)(l - 1))) {
                                k1 = (int)((float)k1 + Math.signum(sin));
                            }
                            if (Math.floor(sinY * (float)l) != Math.floor(sinY * (float)(l - 1))) {
                                j2 = (int)((float)j2 + Math.signum(sinY));
                            }
                            if (!(block = world.getBlock(i1, j2, k1)).isReplaceable((IBlockAccess)world, i1, j2, k1) && !block.isWood((IBlockAccess)world, i1, j2, k1) && !block.isLeaves((IBlockAccess)world, i1, j2, k1)) break;
                            this.setBlockAndNotifyAdequately(world, i1, j2, k1, this.woodBlock, this.woodMeta | 0xC);
                        }
                        this.growLeafCanopy(world, random, i1, j2, k1);
                    }
                }
                for (j1 = j; j1 < j + height; ++j1) {
                    this.setBlockAndNotifyAdequately(world, i, j1, k, this.woodBlock, this.woodMeta);
                }
                for (int i1 = i - 1; i1 <= i + 1; ++i1) {
                    for (int k1 = k - 1; k1 <= k + 1; ++k1) {
                        int i2 = i1 - i;
                        int k2 = k1 - k;
                        if (Math.abs(i2) == Math.abs(k2)) continue;
                        int rootX = i1;
                        int rootY = j + random.nextInt(2);
                        int rootZ = k1;
                        int roots = 0;
                        while (world.getBlock(rootX, rootY, k1).isReplaceable((IBlockAccess)world, rootX, rootY, rootZ)) {
                            this.setBlockAndNotifyAdequately(world, rootX, rootY, rootZ, this.woodBlock, this.woodMeta | 0xC);
                            world.getBlock(rootX, rootY - 1, rootZ).onPlantGrow(world, rootX, rootY - 1, rootZ, rootX, rootY, rootZ);
                            --rootY;
                            if (++roots > 4 + random.nextInt(3)) continue;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void growLeafCanopy(World world, Random random, int i, int j, int k) {
        int leafStart = j - 1;
        int leafTop = j + 2;
        int maxRange = 3 + random.nextInt(2);
        int[] ranges = new int[]{-2, 0, -1, -2};
        for (int j1 = leafStart; j1 <= leafTop; ++j1) {
            int leafRange = maxRange + ranges[j1 - leafStart];
            int leafRangeSq = leafRange * leafRange;
            for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                    boolean grow;
                    Block block;
                    int i2 = Math.abs(i1 - i);
                    int k2 = Math.abs(k1 - k);
                    int j2 = Math.abs(j1 - j);
                    int dSq = i2 * i2 + k2 * k2;
                    int dCh = i2 + j2 + k2;
                    boolean bl = grow = dSq < leafRangeSq && dCh <= 4;
                    if (i2 == leafRange - 1 || k2 == leafRange - 1) {
                        grow &= random.nextInt(4) != 0;
                    }
                    if (!grow || !(block = world.getBlock(i1, j1, k1)).isReplaceable((IBlockAccess)world, i1, j1, k1) && !block.isLeaves((IBlockAccess)world, i1, j1, k1)) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.leafBlock, this.leafMeta);
                }
            }
        }
    }
}

