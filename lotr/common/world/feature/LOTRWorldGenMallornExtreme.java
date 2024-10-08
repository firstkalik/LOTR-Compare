/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
import lotr.common.world.structure.LOTRWorldGenElfLordHouse;
import lotr.common.world.structure2.LOTRWorldGenElfHouse;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenMallornExtreme
extends WorldGenAbstractTree {
    private static int HEIGHT_MIN = 35;
    private static int HEIGHT_MAX = 70;
    private static int BOUGH_ANGLE_INTERVAL_MIN = 10;
    private static int BOUGH_ANGLE_INTERVAL_MAX = 30;
    private static int BOUGH_LENGTH_MIN = 15;
    private static int BOUGH_LENGTH_MAX = 25;
    private static float BOUGH_THICKNESS_FACTOR = 0.03f;
    private static float BOUGH_BASE_HEIGHT_MIN = 0.9f;
    private static float BOUGH_BASE_HEIGHT_MAX = 1.0f;
    private static int BOUGH_HEIGHT_MIN = 7;
    private static int BOUGH_HEIGHT_MAX = 10;
    private static int BRANCH_LENGTH_MIN = 8;
    private static int BRANCH_LENGTH_MAX = 10;
    private static int BRANCH_HEIGHT_MIN = 6;
    private static int BRANCH_HEIGHT_MAX = 8;
    public static float HOUSE_HEIGHT_MIN = 0.4f;
    public static float HOUSE_HEIGHT_MAX = 0.7f;
    private static float HOUSE_CHANCE = 0.7f;
    private static float HOUSE_ELFLORD_CHANCE = 0.15f;
    private boolean notify;
    private boolean saplingGrowth = false;

    public LOTRWorldGenMallornExtreme(boolean flag) {
        super(flag);
        this.notify = flag;
    }

    public LOTRWorldGenMallornExtreme setSaplingGrowth() {
        this.saplingGrowth = true;
        return this;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        return this.generateAndReturnHeight(world, random, i, j, k, false) > 0;
    }

    public int generateAndReturnHeight(World world, Random random, int i, int j, int k, boolean forceGeneration) {
        int height = MathHelper.getRandomIntegerInRange((Random)random, (int)HEIGHT_MIN, (int)HEIGHT_MAX);
        int trunkWidth = 2;
        boolean flag = true;
        if (j >= 1 && j + height + 5 <= 256 || forceGeneration) {
            int i1;
            int k1;
            int j1;
            for (j1 = j; j1 <= j + 1 + height; ++j1) {
                int range = trunkWidth;
                if (j1 == j) {
                    range = 0;
                }
                if (j1 >= j + 1 + height - 2) {
                    range = trunkWidth + 1;
                }
                for (int i2 = i - range; i2 <= i + range && flag; ++i2) {
                    for (int k2 = k - range; k2 <= k + range && flag; ++k2) {
                        if (j1 >= 0 && j1 < 256) {
                            Block block = world.getBlock(i2, j1, k2);
                            if (forceGeneration || this.isReplaceable(world, i2, j1, k2) || block == LOTRMod.quenditeGrass) continue;
                            flag = false;
                            continue;
                        }
                        if (forceGeneration) continue;
                        flag = false;
                    }
                }
            }
            if (!flag && !forceGeneration) {
                return 0;
            }
            if (!forceGeneration) {
                for (i1 = i - trunkWidth; i1 <= i + trunkWidth; ++i1) {
                    for (k1 = k - trunkWidth; k1 <= k + trunkWidth; ++k1) {
                        Block block = world.getBlock(i1, j - 1, k1);
                        boolean correctBlock = false;
                        if (this.saplingGrowth) {
                            if (block == LOTRMod.quenditeGrass) {
                                correctBlock = true;
                            }
                        } else if (block.canSustainPlant((IBlockAccess)world, i1, j - 1, k1, ForgeDirection.UP, (IPlantable)LOTRMod.sapling)) {
                            correctBlock = true;
                        }
                        if (correctBlock) continue;
                        return 0;
                    }
                }
            }
            for (i1 = i - trunkWidth; i1 <= i + trunkWidth; ++i1) {
                for (k1 = k - trunkWidth; k1 <= k + trunkWidth; ++k1) {
                    if (!forceGeneration) {
                        world.getBlock(i1, j - 1, k1).onPlantGrow(world, i1, j - 1, k1, i1, j, k1);
                    }
                    for (j1 = 0; j1 < height; ++j1) {
                        Block block = world.getBlock(i1, j + j1, k1);
                        if (!block.isReplaceable((IBlockAccess)world, i1, j + j1, k1) && !block.isLeaves((IBlockAccess)world, i1, j + j1, k1)) continue;
                        this.setBlockAndNotifyAdequately(world, i1, j + j1, k1, LOTRMod.wood, 1);
                    }
                }
            }
            int angle = 0;
            while (angle < 360) {
                float angleR = (float)Math.toRadians(angle += MathHelper.getRandomIntegerInRange((Random)random, (int)BOUGH_ANGLE_INTERVAL_MIN, (int)BOUGH_ANGLE_INTERVAL_MAX));
                float sin = MathHelper.sin((float)angleR);
                float cos = MathHelper.cos((float)angleR);
                int boughLength = MathHelper.getRandomIntegerInRange((Random)random, (int)BOUGH_LENGTH_MIN, (int)BOUGH_LENGTH_MAX);
                int boughThickness = Math.round((float)boughLength * BOUGH_THICKNESS_FACTOR);
                int boughBaseHeight = j + MathHelper.floor_double((double)((float)height * MathHelper.randomFloatClamp((Random)random, (float)BOUGH_BASE_HEIGHT_MIN, (float)BOUGH_BASE_HEIGHT_MAX)));
                int boughHeight = MathHelper.getRandomIntegerInRange((Random)random, (int)BOUGH_HEIGHT_MIN, (int)BOUGH_HEIGHT_MAX);
                for (int l = 0; l < boughLength; ++l) {
                    int i12 = i + Math.round(sin * (float)l);
                    int k12 = k + Math.round(cos * (float)l);
                    int j12 = boughBaseHeight + Math.round((float)l / (float)boughLength * (float)boughHeight);
                    int range = boughThickness - Math.round((float)l / (float)boughLength * (float)boughThickness * 0.5f);
                    for (int i2 = i12 - range; i2 <= i12 + range; ++i2) {
                        for (int j2 = j12 - range; j2 <= j12 + range; ++j2) {
                            for (int k2 = k12 - range; k2 <= k12 + range; ++k2) {
                                Block block = world.getBlock(i2, j2, k2);
                                if (!block.isReplaceable((IBlockAccess)world, i2, j2, k2) && !block.isLeaves((IBlockAccess)world, i2, j2, k2)) continue;
                                this.setBlockAndNotifyAdequately(world, i2, j2, k2, LOTRMod.wood, 13);
                            }
                        }
                    }
                    if (l != boughLength - 1) continue;
                    int branches = MathHelper.getRandomIntegerInRange((Random)random, (int)8, (int)16);
                    for (int b = 0; b < branches; ++b) {
                        float branch_angle = random.nextFloat() * 2.0f * 3.1415927f;
                        float branch_sin = MathHelper.sin((float)branch_angle);
                        float branch_cos = MathHelper.cos((float)branch_angle);
                        int branchLength = MathHelper.getRandomIntegerInRange((Random)random, (int)BRANCH_LENGTH_MIN, (int)BRANCH_LENGTH_MAX);
                        int branchHeight = MathHelper.getRandomIntegerInRange((Random)random, (int)BRANCH_HEIGHT_MIN, (int)BRANCH_HEIGHT_MAX);
                        for (int b1 = 0; b1 < branchLength; ++b1) {
                            int i2 = i12 + Math.round(branch_sin * (float)b1);
                            int k2 = k12 + Math.round(branch_cos * (float)b1);
                            int j2 = j12 + Math.round((float)b1 / (float)branchLength * (float)branchHeight);
                            Block block = world.getBlock(i2, j2, k2);
                            if (block.isReplaceable((IBlockAccess)world, i2, j2, k2) || block.isLeaves((IBlockAccess)world, i2, j2, k2)) {
                                this.setBlockAndNotifyAdequately(world, i2, j2, k2, LOTRMod.wood, 13);
                            }
                            if (b1 != branchLength - 1) continue;
                            this.spawnLeafCluster(world, random, i2, j2, k2, 3);
                        }
                    }
                }
            }
            if (trunkWidth > 0) {
                for (int j13 = j + (int)((float)height * BOUGH_BASE_HEIGHT_MIN); j13 > j + (int)((float)height * 0.67f); j13 -= 1 + random.nextInt(3)) {
                    int branches = 1 + random.nextInt(5);
                    for (int b = 0; b < branches; ++b) {
                        float branchAngle = random.nextFloat() * 3.1415927f * 2.0f;
                        int i13 = i + (int)(1.5f + MathHelper.cos((float)branchAngle) * 4.0f);
                        int k13 = k + (int)(1.5f + MathHelper.sin((float)branchAngle) * 4.0f);
                        int j2 = j13;
                        int length = MathHelper.getRandomIntegerInRange((Random)random, (int)10, (int)20);
                        for (int l = 0; l < length && this.isReplaceable(world, i13 = i + (int)(1.5f + MathHelper.cos((float)branchAngle) * (float)l), j2 = j13 - 3 + l / 2, k13 = k + (int)(1.5f + MathHelper.sin((float)branchAngle) * (float)l)); ++l) {
                            this.setBlockAndNotifyAdequately(world, i13, j2, k13, LOTRMod.wood, 13);
                        }
                        this.spawnLeafLayer(world, random, i13, j2 + 1, k13, 2);
                        this.spawnLeafLayer(world, random, i13, j2, k13, 3);
                        this.spawnLeafLayer(world, random, i13, j2 - 1, k13, 1);
                    }
                }
            }
            if (trunkWidth > 0) {
                int roots = MathHelper.getRandomIntegerInRange((Random)random, (int)6, (int)10);
                for (int l = 0; l < roots; ++l) {
                    int i14 = i;
                    int j14 = j + 1 + random.nextInt(5);
                    int k14 = k;
                    int xDirection = 0;
                    int zDirection = 0;
                    int rootLength = 1 + random.nextInt(4);
                    if (random.nextBoolean()) {
                        if (random.nextBoolean()) {
                            i14 -= trunkWidth + 1;
                            xDirection = -1;
                        } else {
                            i14 += trunkWidth + 1;
                            xDirection = 1;
                        }
                        k14 -= trunkWidth + 1;
                        k14 += random.nextInt(trunkWidth * 2 + 2);
                    } else {
                        if (random.nextBoolean()) {
                            k14 -= trunkWidth + 1;
                            zDirection = -1;
                        } else {
                            k14 += trunkWidth + 1;
                            zDirection = 1;
                        }
                        i14 -= trunkWidth + 1;
                        i14 += random.nextInt(trunkWidth * 2 + 2);
                    }
                    for (int l1 = 0; l1 < rootLength; ++l1) {
                        int rootBlocks = 0;
                        int j2 = j14;
                        while (!LOTRMod.isOpaque(world, i14, j2, k14)) {
                            this.setBlockAndNotifyAdequately(world, i14, j2, k14, LOTRMod.wood, 13);
                            world.getBlock(i14, j2 - 1, k14).onPlantGrow(world, i14, j2 - 1, k14, i14, j2, k14);
                            if (++rootBlocks > 5) break;
                            --j2;
                        }
                        --j14;
                        if (!random.nextBoolean()) continue;
                        if (xDirection == -1) {
                            --i14;
                            continue;
                        }
                        if (xDirection == 1) {
                            ++i14;
                            continue;
                        }
                        if (zDirection == -1) {
                            --k14;
                            continue;
                        }
                        if (zDirection != 1) continue;
                        ++k14;
                    }
                }
            }
            if (!this.saplingGrowth && !this.notify && !forceGeneration && random.nextFloat() < HOUSE_CHANCE) {
                int houseHeight = MathHelper.floor_double((double)((float)height * MathHelper.randomFloatClamp((Random)random, (float)HOUSE_HEIGHT_MIN, (float)HOUSE_HEIGHT_MAX)));
                boolean isElfLordTree = random.nextFloat() < HOUSE_ELFLORD_CHANCE;
                boolean spawnedElfLord = false;
                if (isElfLordTree) {
                    LOTRWorldGenElfLordHouse house = new LOTRWorldGenElfLordHouse(true);
                    house.restrictions = false;
                    spawnedElfLord = house.generate(world, random, i, j + houseHeight, k);
                }
                if (!isElfLordTree || !spawnedElfLord) {
                    LOTRWorldGenElfHouse house = new LOTRWorldGenElfHouse(true);
                    house.restrictions = false;
                    house.generate(world, random, i, j + houseHeight, k);
                }
            }
            return height;
        }
        return 0;
    }

    private void spawnLeafCluster(World world, Random random, int i, int j, int k, int leafRange) {
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
                    if (dist >= leafRangeSqLess && (dist >= leafRangeSq || random.nextInt(3) != 0) || !(block = world.getBlock(i1, j1, k1)).isReplaceable((IBlockAccess)world, i1, j1, k1) && !block.isLeaves((IBlockAccess)world, i1, j1, k1)) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.leaves, 1);
                }
            }
        }
    }

    private void spawnLeafLayer(World world, Random random, int i, int j, int k, int leafRange) {
        int leafRangeSq = leafRange * leafRange;
        for (int i1 = i - leafRange; i1 <= i + leafRange; ++i1) {
            for (int k1 = k - leafRange; k1 <= k + leafRange; ++k1) {
                Block block;
                int i2 = i1 - i;
                int k2 = k1 - k;
                int dist = i2 * i2 + k2 * k2;
                if (dist > leafRangeSq || !(block = world.getBlock(i1, j, k1)).isReplaceable((IBlockAccess)world, i1, j, k1) && !block.isLeaves((IBlockAccess)world, i1, j, k1)) continue;
                this.setBlockAndNotifyAdequately(world, i1, j, k1, LOTRMod.leaves, 1);
            }
        }
    }
}

