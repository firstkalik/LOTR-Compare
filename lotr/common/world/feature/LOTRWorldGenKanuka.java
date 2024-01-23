/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
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
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenKanuka
extends WorldGenAbstractTree {
    private int minHeight;
    private int maxHeight;
    private int trunkWidth;
    private Block woodBlock = LOTRMod.wood9;
    private int woodMeta = 1;
    private Block leafBlock = LOTRMod.leaves9;
    private int leafMeta = 1;

    public LOTRWorldGenKanuka(boolean flag) {
        this(flag, 5, 12, 0);
    }

    public LOTRWorldGenKanuka(boolean flag, int i, int j, int k) {
        super(flag);
        this.minHeight = i;
        this.maxHeight = j;
        this.trunkWidth = k;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int height = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minHeight, (int)this.maxHeight);
        float trunkAngleY = (float)Math.toRadians(90.0f - MathHelper.randomFloatClamp((Random)random, (float)0.0f, (float)35.0f));
        float trunkAngle = random.nextFloat() * 3.1415927f * 2.0f;
        float trunkYCos = MathHelper.cos((float)trunkAngleY);
        float trunkYSin = MathHelper.sin((float)trunkAngleY);
        float trunkCos = MathHelper.cos((float)trunkAngle) * trunkYCos;
        float trunkSin = MathHelper.sin((float)trunkAngle) * trunkYCos;
        boolean flag = true;
        if (j >= 1 && j + height + 3 <= 256) {
            for (int j1 = j; j1 <= j + height + 3; ++j1) {
                int range = this.trunkWidth + 1;
                if (j1 == j) {
                    range = this.trunkWidth;
                }
                if (j1 >= j + height + 2) {
                    range = this.trunkWidth + 2;
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
            for (int i1 = i - this.trunkWidth; i1 <= i + this.trunkWidth && flag; ++i1) {
                for (int k1 = k - this.trunkWidth; k1 <= k + this.trunkWidth && flag; ++k1) {
                    Block block = world.getBlock(i1, j - 1, k1);
                    boolean isSoil = block.canSustainPlant((IBlockAccess)world, i1, j - 1, k1, ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.sapling));
                    if (isSoil) continue;
                    flag = false;
                }
            }
            if (!flag) {
                return false;
            }
            for (int pass = 0; pass <= 1; ++pass) {
                if (pass == 1) {
                    for (int i1 = i - this.trunkWidth; i1 <= i + this.trunkWidth; ++i1) {
                        for (int k1 = k - this.trunkWidth; k1 <= k + this.trunkWidth; ++k1) {
                            world.getBlock(i1, j - 1, k1).onPlantGrow(world, i1, j - 1, k1, i1, j, k1);
                        }
                    }
                }
                int trunkX = i;
                int trunkZ = k;
                int trunkY = j;
                ArrayList<int[]> trunkCoords = new ArrayList<int[]>();
                int trunkHeight = height;
                for (int l = 0; l < trunkHeight; ++l) {
                    if (l > 0) {
                        if (Math.floor(trunkCos * (float)l) != Math.floor(trunkCos * (float)(l + 1))) {
                            trunkX = (int)((float)trunkX + Math.signum(trunkCos));
                        }
                        if (Math.floor(trunkSin * (float)l) != Math.floor(trunkSin * (float)(l + 1))) {
                            trunkZ = (int)((float)trunkZ + Math.signum(trunkSin));
                        }
                        if (Math.floor(trunkYSin * (float)l) != Math.floor(trunkYSin * (float)(l + 1))) {
                            trunkY = (int)((float)trunkY + Math.signum(trunkYSin));
                        }
                    }
                    int j1 = trunkY;
                    for (int i1 = trunkX - this.trunkWidth; i1 <= trunkX + this.trunkWidth; ++i1) {
                        for (int k1 = trunkZ - this.trunkWidth; k1 <= trunkZ + this.trunkWidth; ++k1) {
                            if (pass == 0 && !this.isReplaceable(world, i1, j1, k1)) {
                                return false;
                            }
                            if (pass != 1) continue;
                            this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.woodBlock, this.woodMeta | 0xC);
                            trunkCoords.add(new int[]{i1, j1, k1});
                        }
                    }
                }
                if (pass != true) continue;
                int branchHeight = (int)((double)height * 0.67);
                int deg = 0;
                while (deg < 360) {
                    int degIncr = MathHelper.getRandomIntegerInRange((Random)random, (int)70, (int)150);
                    degIncr -= this.trunkWidth * 10;
                    degIncr = Math.max(degIncr, 20);
                    float angle = (float)Math.toRadians(deg += degIncr);
                    float cos = MathHelper.cos((float)angle);
                    float sin = MathHelper.sin((float)angle);
                    float angleY = (float)Math.toRadians(70.0f - random.nextFloat() * 20.0f);
                    float cosY = MathHelper.cos((float)angleY);
                    float sinY = MathHelper.sin((float)angleY);
                    cos *= cosY;
                    sin *= cosY;
                    int length = branchHeight + MathHelper.getRandomIntegerInRange((Random)random, (int)-3, (int)3);
                    length = Math.max(length, 3);
                    int trunkIndex = MathHelper.getRandomIntegerInRange((Random)random, (int)((int)((double)(trunkCoords.size() - 1) * 0.5)), (int)(trunkCoords.size() - 1));
                    int[] oneTrunkCoord = (int[])trunkCoords.get(trunkIndex);
                    int i1 = oneTrunkCoord[0];
                    int j1 = oneTrunkCoord[1];
                    int k1 = oneTrunkCoord[2];
                    for (int l = 0; l < (length += this.trunkWidth); ++l) {
                        Block block;
                        if (Math.floor(cos * (float)l) != Math.floor(cos * (float)(l + 1))) {
                            i1 = (int)((float)i1 + Math.signum(cos));
                        }
                        if (Math.floor(sin * (float)l) != Math.floor(sin * (float)(l + 1))) {
                            k1 = (int)((float)k1 + Math.signum(sin));
                        }
                        if (Math.floor(sinY * (float)l) != Math.floor(sinY * (float)(l + 1))) {
                            j1 = (int)((float)j1 + Math.signum(sinY));
                        }
                        if (!(block = world.getBlock(i1, j1, k1)).isReplaceable((IBlockAccess)world, i1, j1, k1) && !block.isWood((IBlockAccess)world, i1, j1, k1) && !block.isLeaves((IBlockAccess)world, i1, j1, k1)) break;
                        this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.woodBlock, this.woodMeta | 0xC);
                    }
                    this.growLeafCanopy(world, random, i1, j1, k1);
                }
            }
            return true;
        }
        return false;
    }

    private void growLeafCanopy(World world, Random random, int i, int j, int k) {
        int leafHeight = 2;
        int maxRange = 1 + random.nextInt(3);
        for (int j1 = 0; j1 < leafHeight; ++j1) {
            int j2 = j + j1;
            int leafRange = maxRange - j1;
            for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                    Block block;
                    int k2;
                    int i2 = Math.abs(i1 - i);
                    int dist = i2 + (k2 = Math.abs(k1 - k));
                    if (dist > leafRange || !(block = world.getBlock(i1, j2, k1)).isReplaceable((IBlockAccess)world, i1, j2, k1) && !block.isLeaves((IBlockAccess)world, i1, j2, k1)) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j2, k1, this.leafBlock, this.leafMeta);
                }
            }
        }
        Block block = world.getBlock(i, j, k);
        if (block.isReplaceable((IBlockAccess)world, i, j, k) || block.isLeaves((IBlockAccess)world, i, j, k)) {
            this.setBlockAndNotifyAdequately(world, i, j, k, this.woodBlock, this.woodMeta | 0xC);
        }
    }
}

