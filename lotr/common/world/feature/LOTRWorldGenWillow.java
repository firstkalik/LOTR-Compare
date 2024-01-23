/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenWillow
extends WorldGenAbstractTree {
    private Block woodBlock = LOTRMod.wood6;
    private int woodMeta = 1;
    private Block leafBlock = LOTRMod.leaves6;
    private int leafMeta = 1;
    private int minHeight = 8;
    private int maxHeight = 13;
    private boolean needsWater = false;

    public LOTRWorldGenWillow(boolean flag) {
        super(flag);
    }

    public LOTRWorldGenWillow setNeedsWater() {
        this.needsWater = true;
        return this;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        boolean isSoil;
        Block below;
        int height = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minHeight, (int)this.maxHeight);
        boolean flag = true;
        if (j >= 1 && height + 1 <= 256) {
            for (int j1 = j; j1 <= j + height + 1; ++j1) {
                int range = 1;
                if (j1 == j) {
                    range = 0;
                }
                if (j1 >= j + height - 1) {
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
        } else {
            flag = false;
        }
        if (!(isSoil = (below = world.getBlock(i, j - 1, k)).canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling))) {
            flag = false;
        }
        if (this.needsWater) {
            boolean water = false;
            int attempts = 4;
            for (int l = 0; l < attempts; ++l) {
                int k1;
                int j1;
                int i1 = i + MathHelper.getRandomIntegerInRange((Random)random, (int)-12, (int)12);
                if (world.getBlock(i1, j1 = j + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)4), k1 = k + MathHelper.getRandomIntegerInRange((Random)random, (int)-12, (int)12)).getMaterial() != Material.water) continue;
                water = true;
                break;
            }
            if (!water) {
                return false;
            }
        }
        if (!flag) {
            return false;
        }
        below.onPlantGrow(world, i, j - 1, k, i, j, k);
        ArrayList<ChunkCoordinates> vineGrows = new ArrayList<ChunkCoordinates>();
        int angle = 0;
        while (angle < 360) {
            float angleR = (float)Math.toRadians(angle += 30 + random.nextInt(30));
            float sin = MathHelper.sin((float)angleR);
            float cos = MathHelper.cos((float)angleR);
            int base = j + height - 3 - random.nextInt(3);
            int length = 2 + random.nextInt(4);
            int i1 = i;
            int j1 = base;
            int k1 = k;
            for (int l = 0; l < length; ++l) {
                if (l > 0 && (l % 4 == 0 || random.nextInt(3) == 0)) {
                    ++j1;
                }
                if (random.nextFloat() < Math.abs(cos)) {
                    i1 = (int)((float)i1 + Math.signum(cos));
                }
                if (random.nextFloat() < Math.abs(sin)) {
                    k1 = (int)((float)k1 + Math.signum(sin));
                }
                this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.woodBlock, this.woodMeta);
            }
            this.spawnLeafCluster(world, random, i1, j1, k1);
            vineGrows.add(new ChunkCoordinates(i1, j1, k1));
        }
        for (int j1 = 0; j1 < height; ++j1) {
            this.setBlockAndNotifyAdequately(world, i, j + j1, k, this.woodBlock, this.woodMeta);
            if (j1 != height - 1) continue;
            this.spawnLeafCluster(world, random, i, j + j1, k);
            vineGrows.add(new ChunkCoordinates(i, j + j1, k));
        }
        for (int i1 = i - 1; i1 <= i + 1; ++i1) {
            for (int k1 = k - 1; k1 <= k + 1; ++k1) {
                int i2 = i1 - i;
                int k2 = k1 - k;
                if (Math.abs(i2) == Math.abs(k2)) continue;
                int rootX = i1;
                int rootY = j + 1 + random.nextInt(2);
                int rootZ = k1;
                int roots = 0;
                while (world.getBlock(rootX, rootY, k1).isReplaceable((IBlockAccess)world, rootX, rootY, rootZ)) {
                    this.setBlockAndNotifyAdequately(world, rootX, rootY, rootZ, this.woodBlock, this.woodMeta | 0xC);
                    world.getBlock(rootX, rootY - 1, rootZ).onPlantGrow(world, rootX, rootY - 1, rootZ, rootX, rootY, rootZ);
                    --rootY;
                    if (++roots <= 4 + random.nextInt(3)) continue;
                }
            }
        }
        for (ChunkCoordinates coords : vineGrows) {
            this.spawnVineCluster(world, random, coords.posX, coords.posY, coords.posZ);
        }
        return true;
    }

    private void spawnLeafCluster(World world, Random random, int i, int j, int k) {
        int leafRange = 3;
        int leafRangeSq = leafRange * leafRange;
        int leafRangeSqLess = (int)(((double)leafRange - 0.5) * ((double)leafRange - 0.5));
        for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
            for (int j1 = j - leafRange; j1 <= j + leafRange; ++j1) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                    Block block;
                    int i2 = i1 - i;
                    int j2 = j1 - j;
                    int k2 = k1 - k;
                    int dist = i2 * i2 + j2 * j2 + k2 * k2;
                    int taxicab = Math.abs(i2) + Math.abs(j2) + Math.abs(k2);
                    if (dist >= leafRangeSqLess && (dist >= leafRangeSq || random.nextInt(3) != 0) || taxicab > 4 || !(block = world.getBlock(i1, j1, k1)).isReplaceable((IBlockAccess)world, i1, j1, k1) && !block.isLeaves((IBlockAccess)world, i1, j1, k1)) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.leafBlock, this.leafMeta);
                }
            }
        }
    }

    private void spawnVineCluster(World world, Random random, int i, int j, int k) {
        int leafRange = 3;
        int leafRangeSq = leafRange * leafRange;
        for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
            for (int j1 = j - leafRange; j1 <= j + leafRange; ++j1) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                    int i2 = i1 - i;
                    int j2 = j1 - j;
                    int k2 = k1 - k;
                    int dist = i2 * i2 + j2 * j2 + k2 * k2;
                    if (dist >= leafRangeSq) continue;
                    Block block = world.getBlock(i1, j1, k1);
                    int meta = world.getBlockMetadata(i1, j1, k1);
                    if (block != this.leafBlock || meta != this.leafMeta) continue;
                    int vineChance = 2;
                    if (random.nextInt(vineChance) == 0 && world.getBlock(i1 - 1, j1, k1).isAir((IBlockAccess)world, i1 - 1, j1, k1)) {
                        this.growVines(world, random, i1 - 1, j1, k1, 8);
                    }
                    if (random.nextInt(vineChance) == 0 && world.getBlock(i1 + 1, j1, k1).isAir((IBlockAccess)world, i1 + 1, j1, k1)) {
                        this.growVines(world, random, i1 + 1, j1, k1, 2);
                    }
                    if (random.nextInt(vineChance) == 0 && world.getBlock(i1, j1, k1 - 1).isAir((IBlockAccess)world, i1, j1, k1 - 1)) {
                        this.growVines(world, random, i1, j1, k1 - 1, 1);
                    }
                    if (random.nextInt(vineChance) != 0 || !world.getBlock(i1, j1, k1 + 1).isAir((IBlockAccess)world, i1, j1, k1 + 1)) continue;
                    this.growVines(world, random, i1, j1, k1 + 1, 4);
                }
            }
        }
    }

    private void growVines(World world, Random random, int i, int j, int k, int meta) {
        this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.willowVines, meta);
        int vines = 0;
        while (world.getBlock(i, --j, k).isAir((IBlockAccess)world, i, j, k) && vines < 2 + random.nextInt(4)) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.willowVines, meta);
            ++vines;
        }
        return;
    }
}

