/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRuinedDunedainTower
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenRuinedDunedainTower(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j2;
        int k1;
        int j1;
        int i1;
        if (this.restrictions && world.getBlock(i, j - 1, k) != Blocks.grass) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
        int radius = 4 + random.nextInt(2);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
            switch (rotation) {
                case 0: {
                    k += radius;
                    break;
                }
                case 1: {
                    i -= radius;
                    break;
                }
                case 2: {
                    k -= radius;
                    break;
                }
                case 3: {
                    i += radius;
                }
            }
        }
        int sections = 4 + random.nextInt(3);
        int sectionHeight = 4 + random.nextInt(4);
        int maxHeight = (sections - 1) * sectionHeight;
        int wallThresholdMin = radius;
        wallThresholdMin *= wallThresholdMin;
        int wallThresholdMax = radius + 1;
        wallThresholdMax *= wallThresholdMax;
        for (i1 = i - radius; i1 <= i + radius; ++i1) {
            for (int k12 = k - radius; k12 <= k + radius; ++k12) {
                int i2 = i1 - i;
                int k2 = k12 - k;
                int distSq = i2 * i2 + k2 * k2;
                if (distSq >= wallThresholdMax) continue;
                if (distSq >= wallThresholdMin) {
                    for (j1 = j - 1; j1 >= 0; --j1) {
                        Block block = world.getBlock(i1, j1, k12);
                        this.placeRandomBrick(world, random, i1, j1, k12);
                        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.stone || !this.restrictions && block.isOpaqueCube()) break;
                    }
                    j2 = j + maxHeight;
                    for (int j12 = j; j12 <= j2; ++j12) {
                        if (random.nextInt(20) == 0) continue;
                        this.placeRandomBrick(world, random, i1, j12, k12);
                    }
                    int j3 = j2 + 1 + random.nextInt(3);
                    for (int j13 = j2; j13 <= j3; ++j13) {
                        this.placeRandomBrick(world, random, i1, j13, k12);
                    }
                    continue;
                }
                for (j1 = j + sectionHeight; j1 <= j + maxHeight; j1 += sectionHeight) {
                    if (random.nextInt(6) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, (Block)Blocks.stone_slab, 8);
                }
            }
        }
        for (int j12 = j + sectionHeight; j12 < j + maxHeight; j12 += sectionHeight) {
            for (int j22 = j12 + 2; j22 <= j12 + 3; ++j22) {
                for (int i12 = i - 1; i12 <= i + 1; ++i12) {
                    this.placeIronBars(world, random, i12, j22, k - radius);
                    this.placeIronBars(world, random, i12, j22, k + radius);
                }
                for (k1 = k - 1; k1 <= k + 1; ++k1) {
                    this.placeIronBars(world, random, i - radius, j22, k1);
                    this.placeIronBars(world, random, i + radius, j22, k1);
                }
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + maxHeight, k, (Block)Blocks.stone_slab, 8);
        this.setBlockAndNotifyAdequately(world, i, j + maxHeight + 1, k, LOTRMod.chestStone, rotation + 2);
        LOTRChestContents.fillChest(world, random, i, j + maxHeight + 1, k, LOTRChestContents.DUNEDAIN_TOWER);
        switch (rotation) {
            case 0: {
                int height;
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    height = j + 1 + random.nextInt(3);
                    for (j1 = j; j1 <= height; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k - radius, Blocks.air, 0);
                    }
                }
                break;
            }
            case 1: {
                int k13;
                int j13;
                int height;
                for (k13 = k - 1; k13 <= k + 1; ++k13) {
                    height = j + 1 + random.nextInt(3);
                    for (j13 = j; j13 <= height; ++j13) {
                        this.setBlockAndNotifyAdequately(world, i + radius, j13, k13, Blocks.air, 0);
                    }
                }
                break;
            }
            case 2: {
                int height;
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    height = j + 1 + random.nextInt(3);
                    for (j1 = j; j1 <= height; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k + radius, Blocks.air, 0);
                    }
                }
                break;
            }
            case 3: {
                int k13;
                int j13;
                int height;
                for (k13 = k - 1; k13 <= k + 1; ++k13) {
                    height = j + 1 + random.nextInt(3);
                    for (j13 = j; j13 <= height; ++j13) {
                        this.setBlockAndNotifyAdequately(world, i - radius, j13, k13, Blocks.air, 0);
                    }
                }
                break;
            }
        }
        for (int l = 0; l < 16; ++l) {
            int i13 = i - random.nextInt(radius * 2) + random.nextInt(radius * 2);
            if (world.getBlock(i13, (j1 = world.getHeightValue(i13, k1 = k - random.nextInt(radius * 2) + random.nextInt(radius * 2))) - 1, k1) != Blocks.grass) continue;
            int randomFeature = random.nextInt(4);
            boolean flag = true;
            if (randomFeature == 0) {
                if (!LOTRMod.isOpaque(world, i13, j1, k1)) {
                    this.setBlockAndNotifyAdequately(world, i13, j1, k1, (Block)Blocks.stone_slab, random.nextBoolean() ? 0 : 5);
                }
            } else {
                for (j2 = j1; j2 < j1 + randomFeature && flag; ++j2) {
                    flag = !LOTRMod.isOpaque(world, i13, j2, k1);
                }
                if (flag) {
                    for (j2 = j1; j2 < j1 + randomFeature; ++j2) {
                        this.placeRandomBrick(world, random, i13, j2, k1);
                    }
                }
            }
            if (!flag) continue;
            world.getBlock(i13, j1 - 1, k1).onPlantGrow(world, i13, j1 - 1, k1, i13, j1, k1);
        }
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(5) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick2, 4 + random.nextInt(2));
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick2, 3);
        }
    }

    private void placeIronBars(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, Blocks.air, 0);
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, Blocks.iron_bars, 0);
        }
    }
}

