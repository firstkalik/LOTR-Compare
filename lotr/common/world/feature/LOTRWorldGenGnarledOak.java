/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Direction
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
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenGnarledOak
extends WorldGenAbstractTree {
    private Block woodBlock = Blocks.log;
    private int woodMeta = 0;
    private Block leafBlock = Blocks.leaves;
    private int leafMeta = 0;
    private int minHeight = 4;
    private int maxHeight = 9;

    public LOTRWorldGenGnarledOak(boolean flag) {
        super(flag);
    }

    public LOTRWorldGenGnarledOak setBlocks(Block b1, int m1, Block b2, int m2) {
        this.woodBlock = b1;
        this.woodMeta = m1;
        this.leafBlock = b2;
        this.leafMeta = m2;
        return this;
    }

    public LOTRWorldGenGnarledOak setMinMaxHeight(int min, int max) {
        this.minHeight = min;
        this.maxHeight = max;
        return this;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int length;
        int height = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minHeight, (int)this.maxHeight);
        boolean flag = true;
        if (j >= 1 && height + 1 <= 256) {
            for (int j1 = j; j1 <= j + height + 1; ++j1) {
                int range = 1;
                if (j1 == j) {
                    range = 0;
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
        } else {
            flag = false;
        }
        if (!flag) {
            return false;
        }
        boolean canGrow = true;
        Block below = world.getBlock(i, j - 1, k);
        if (!below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling)) {
            canGrow = false;
        }
        if (!canGrow) {
            return false;
        }
        below = world.getBlock(i, j - 1, k);
        below.onPlantGrow(world, i, j - 1, k, i, j, k);
        for (int j1 = j; j1 < j + height; ++j1) {
            this.setBlockAndNotifyAdequately(world, i, j1, k, this.woodBlock, this.woodMeta);
        }
        this.generateLeaves(world, random, i, j + height, k);
        int branches = 2 + random.nextInt(3);
        for (int b = 0; b < branches; ++b) {
            float angle = random.nextFloat() * 3.1415927f * 2.0f;
            float cos = MathHelper.cos((float)angle);
            float sin = MathHelper.sin((float)angle);
            float angleY = random.nextFloat() * (float)Math.toRadians(40.0);
            MathHelper.cos((float)angleY);
            float sinY = MathHelper.sin((float)angleY);
            length = 2 + random.nextInt(3);
            int i1 = i;
            int k1 = k;
            int j1 = j + height - 1 - random.nextInt(3);
            if (j1 < j + 2) {
                j1 = j + 2;
            }
            for (int l = 0; l < length; ++l) {
                if (Math.floor(cos * (float)l) != Math.floor(cos * (float)(l - 1))) {
                    i1 = (int)((float)i1 + Math.signum(cos));
                }
                if (Math.floor(sin * (float)l) != Math.floor(sin * (float)(l - 1))) {
                    k1 = (int)((float)k1 + Math.signum(sin));
                }
                if (Math.floor(sinY * (float)l) != Math.floor(sinY * (float)(l - 1))) {
                    j1 = (int)((float)j1 + Math.signum(sinY));
                }
                if (!this.isReplaceable(world, i1, j1, k1)) break;
                this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.woodBlock, this.woodMeta | 0xC);
            }
            this.generateLeaves(world, random, i1, j1, k1);
        }
        int lastDir = -1;
        for (int j1 = j + 2; j1 < j + height; ++j1) {
            int i1;
            int dir;
            int k1;
            Block block;
            if (random.nextInt(3) != 0 || (dir = random.nextInt(4)) == lastDir) continue;
            lastDir = dir;
            length = 1;
            for (int l = 1; l <= length && ((block = world.getBlock(i1 = i + Direction.offsetX[dir] * l, j1, k1 = k + Direction.offsetZ[dir] * l)).isReplaceable((IBlockAccess)world, i1, j1, k1) || block.isLeaves((IBlockAccess)world, i1, j1, k1)); ++l) {
                if (dir == 0 || dir == 2) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.woodBlock, this.woodMeta | 8);
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.woodBlock, this.woodMeta | 4);
            }
        }
        for (int i1 = i - 1; i1 <= i + 1; ++i1) {
            for (int k1 = k - 1; k1 <= k + 1; ++k1) {
                if (i1 == i && k1 == k || random.nextInt(4) > 0) continue;
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

    private void generateLeaves(World world, Random random, int i, int j, int k) {
        int leafRange = 3;
        int leafRangeSq = leafRange * leafRange;
        int leafRangeSqLess = (int)(((double)leafRange - 0.5) * ((double)leafRange - 0.5));
        for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
            for (int j1 = j - leafRange + 1; j1 <= j + leafRange; ++j1) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                    Block block;
                    int i2 = i1 - i;
                    int j2 = j1 - j;
                    int k2 = k1 - k;
                    int dist = i2 * i2 + j2 * j2 + k2 * k2;
                    if (dist >= leafRangeSqLess && (dist >= leafRangeSq || random.nextInt(3) != 0) || !(block = world.getBlock(i1, j1, k1)).isReplaceable((IBlockAccess)world, i1, j1, k1) && !block.isLeaves((IBlockAccess)world, i1, j1, k1)) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.leafBlock, this.leafMeta);
                }
            }
        }
    }
}

