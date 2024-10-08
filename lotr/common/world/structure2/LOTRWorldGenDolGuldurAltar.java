/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenDolGuldurAltar
extends LOTRWorldGenStructureBase2 {
    public LOTRWorldGenDolGuldurAltar(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        int i1;
        int k12;
        int k1;
        this.setOriginAndRotation(world, i, j, k, rotation, 6);
        if (this.restrictions) {
            for (i1 = -5; i1 <= 5; ++i1) {
                for (k12 = -5; k12 <= 5; ++k12) {
                    j1 = this.getTopBlock(world, i1, k12);
                    Block block = this.getBlock(world, i1, j1 - 1, k12);
                    if (block == Blocks.stone || block == Blocks.dirt || block == Blocks.grass) continue;
                    return false;
                }
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (k12 = -5; k12 <= 5; ++k12) {
                j1 = 0;
                while (!this.isOpaque(world, i1, j1, k12) && this.getY(j1) >= 0) {
                    this.placeRandomBrick(world, random, i1, j1, k12);
                    this.setGrassToDirt(world, i1, j1 - 1, k12);
                    --j1;
                }
                for (j1 = 1; j1 <= 8; ++j1) {
                    this.setAir(world, i1, j1, k12);
                }
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k12);
                if (i2 <= 4 && k2 <= 4) {
                    this.placeRandomBrick(world, random, i1, 1, k12);
                }
                if (i2 > 3 || k2 > 3) continue;
                if (random.nextInt(10) == 0) {
                    this.setBlockAndMetadata(world, i1, 2, k12, LOTRMod.guldurilBrick, 4);
                    continue;
                }
                this.placeRandomBrick(world, random, i1, 2, k12);
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            this.placeRandomStairs(world, random, i1, 1, -5, 2);
            this.placeRandomStairs(world, random, i1, 1, 5, 3);
        }
        for (k1 = -5; k1 <= 5; ++k1) {
            this.placeRandomStairs(world, random, -5, 1, k1, 1);
            this.placeRandomStairs(world, random, 5, 1, k1, 0);
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            this.placeRandomStairs(world, random, i1, 2, -4, 2);
            this.placeRandomStairs(world, random, i1, 2, 4, 3);
        }
        for (k1 = -4; k1 <= 4; ++k1) {
            this.placeRandomStairs(world, random, -4, 2, k1, 1);
            this.placeRandomStairs(world, random, 4, 2, k1, 0);
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            this.placeRandomStairs(world, random, i1, 3, -1, 2);
            this.placeRandomStairs(world, random, i1, 3, 1, 3);
        }
        for (k1 = -1; k1 <= 1; ++k1) {
            this.placeRandomStairs(world, random, -1, 3, k1, 1);
            this.placeRandomStairs(world, random, 1, 3, k1, 0);
        }
        this.placeRandomBrick(world, random, 0, 3, 0);
        this.setBlockAndMetadata(world, 0, 4, 0, LOTRMod.dolGuldurTable, 0);
        for (int x = -4; x <= 3; x += 7) {
            for (int z = -4; z <= 3; z += 7) {
                for (int i12 = x; i12 <= x + 1; ++i12) {
                    for (int k13 = z; k13 <= z + 1; ++k13) {
                        for (int j12 = 2; j12 <= 5; ++j12) {
                            this.placeRandomBrick(world, random, i12, j12, k13);
                        }
                    }
                }
            }
        }
        this.setBlockAndMetadata(world, -4, 6, -4, LOTRMod.wall2, 8);
        this.setBlockAndMetadata(world, -4, 7, -4, LOTRMod.morgulTorch, 5);
        this.setBlockAndMetadata(world, 4, 6, -4, LOTRMod.wall2, 8);
        this.setBlockAndMetadata(world, 4, 7, -4, LOTRMod.morgulTorch, 5);
        this.setBlockAndMetadata(world, -4, 6, 4, LOTRMod.wall2, 8);
        this.setBlockAndMetadata(world, -4, 7, 4, LOTRMod.morgulTorch, 5);
        this.setBlockAndMetadata(world, 4, 6, 4, LOTRMod.wall2, 8);
        this.setBlockAndMetadata(world, 4, 7, 4, LOTRMod.morgulTorch, 5);
        for (i1 = -4; i1 <= 4; i1 += 8) {
            this.placeRandomStairs(world, random, i1, 6, -3, 2);
            this.placeRandomStairs(world, random, i1, 6, -2, 7);
            this.setBlockAndMetadata(world, i1, 7, -2, LOTRMod.guldurilBrick, 4);
            this.placeRandomStairs(world, random, i1, 8, -2, 2);
            this.placeRandomStairs(world, random, i1, 8, -1, 7);
            this.placeRandomStairs(world, random, i1, 6, 3, 3);
            this.placeRandomStairs(world, random, i1, 6, 2, 6);
            this.setBlockAndMetadata(world, i1, 7, 2, LOTRMod.guldurilBrick, 4);
            this.placeRandomStairs(world, random, i1, 8, 2, 3);
            this.placeRandomStairs(world, random, i1, 8, 1, 6);
        }
        for (k1 = -4; k1 <= 4; k1 += 8) {
            this.placeRandomStairs(world, random, -3, 6, k1, 1);
            this.placeRandomStairs(world, random, -2, 6, k1, 4);
            this.setBlockAndMetadata(world, -2, 7, k1, LOTRMod.guldurilBrick, 4);
            this.placeRandomStairs(world, random, -2, 8, k1, 1);
            this.placeRandomStairs(world, random, -1, 8, k1, 4);
            this.placeRandomStairs(world, random, 3, 6, k1, 0);
            this.placeRandomStairs(world, random, 2, 6, k1, 5);
            this.setBlockAndMetadata(world, 2, 7, k1, LOTRMod.guldurilBrick, 4);
            this.placeRandomStairs(world, random, 2, 8, k1, 0);
            this.placeRandomStairs(world, random, 1, 8, k1, 5);
        }
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 9);
        } else {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 8);
        }
    }

    private void placeRandomStairs(World world, Random random, int i, int j, int k, int meta) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsDolGuldurBrickCracked, meta);
        } else {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsDolGuldurBrick, meta);
        }
    }
}

