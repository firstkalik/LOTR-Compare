/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityRohanBarrowWraith;
import lotr.common.world.biome.LOTRBiomeGenRohan;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRWorldGenRohanBarrow
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenRohanBarrow(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int k1;
        int j1;
        int k12;
        int i2;
        int i12;
        int i1;
        if (this.restrictions && (world.getBlock(i, j - 1, k) != Blocks.grass || !(world.getBiomeGenForCoords(i, k) instanceof LOTRBiomeGenRohan))) {
            return false;
        }
        --j;
        int radius = 7;
        int height = 4;
        if (!this.restrictions && this.usingPlayer != null) {
            int playerRotation = this.usingPlayerRotation();
            switch (playerRotation) {
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
        if (this.restrictions) {
            int minHeight = j;
            int maxHeight = j;
            for (int i13 = i - radius; i13 <= i + radius; ++i13) {
                for (int k13 = k - radius; k13 <= k + radius; ++k13) {
                    int j12 = world.getTopSolidOrLiquidBlock(i13, k13) - 1;
                    if (world.getBlock(i13, j12, k13) != Blocks.grass) {
                        return false;
                    }
                    if (j12 < minHeight) {
                        minHeight = j12;
                    }
                    if (j12 <= maxHeight) continue;
                    maxHeight = j12;
                }
            }
            if (maxHeight - minHeight > 3) {
                return false;
            }
        }
        for (i1 = i - radius; i1 <= i + radius; ++i1) {
            for (int j13 = j + height; j13 >= j; --j13) {
                for (k12 = k - radius; k12 <= k + radius; ++k12) {
                    i2 = i1 - i;
                    int j2 = j13 - j;
                    int k2 = k12 - k;
                    if (i2 * i2 + j2 * j2 + k2 * k2 > radius * radius) continue;
                    boolean grass = !LOTRMod.isOpaque(world, i1, j13 + 1, k12);
                    this.setBlockAndNotifyAdequately(world, i1, j13, k12, (Block)(grass ? Blocks.grass : Blocks.dirt), 0);
                    this.setGrassToDirt(world, i1, j13 - 1, k12);
                }
            }
        }
        for (i1 = i - radius; i1 <= i + radius; ++i1) {
            for (k1 = k - radius; k1 <= k + radius; ++k1) {
                for (j1 = j - 1; !LOTRMod.isOpaque(world, i1, j1, k1) && j1 >= 0; --j1) {
                    i2 = i1 - i;
                    int k2 = k1 - k;
                    if (i2 * i2 + k2 * k2 > radius * radius) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
        }
        for (int l = 0; l < 12; ++l) {
            int j14;
            i12 = i - random.nextInt(radius) + random.nextInt(radius);
            if (world.getBlock(i12, (j14 = world.getHeightValue(i12, k12 = k - random.nextInt(radius) + random.nextInt(radius))) - 1, k12) != Blocks.grass) continue;
            this.setBlockAndNotifyAdequately(world, i12, j14, k12, LOTRMod.simbelmyne, 0);
        }
        j += height;
        for (i1 = i - 1; i1 < i + 1; ++i1) {
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                this.setBlockAndNotifyAdequately(world, i1, j - 1, k1, Blocks.air, 0);
            }
        }
        for (i1 = i - 2; i1 <= i + 2; ++i1) {
            for (k1 = k - 2; k1 <= k + 2; ++k1) {
                for (j1 = j - 2; j1 >= j - 4; --j1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, Blocks.air, 0);
                }
                this.setBlockAndNotifyAdequately(world, i1, j - 5, k1, LOTRMod.slabDouble2, 1);
            }
        }
        for (int j15 = j - 3; j15 >= j - 4; --j15) {
            for (i12 = i - 4; i12 <= i + 4; ++i12) {
                for (k12 = k - 1; k12 <= k + 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i12, j15, k12, Blocks.air, 0);
                    if (Math.abs(i12 - i) <= 2) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j - 5, k12, LOTRMod.brick, 4);
                }
            }
            for (i12 = i - 1; i12 <= i + 1; ++i12) {
                for (k12 = k - 4; k12 <= k + 4; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i12, j15, k12, Blocks.air, 0);
                    if (Math.abs(k12 - k) <= 2) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j - 5, k12, LOTRMod.brick, 4);
                }
            }
            this.setBlockAndNotifyAdequately(world, i - 4, j15, k - 1, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j15, k, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i - 4, j15, k + 1, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, j15, k - 1, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j15, k, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, j15, k + 1, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i - 1, j15, k - 4, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i, j15, k - 5, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i + 1, j15, k - 4, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i - 1, j15, k + 4, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i, j15, k + 5, LOTRMod.rock, 2);
            this.setBlockAndNotifyAdequately(world, i + 1, j15, k + 4, LOTRMod.rock, 2);
        }
        this.setBlockAndNotifyAdequately(world, i - 4, j - 3, k, Blocks.torch, 1);
        this.setBlockAndNotifyAdequately(world, i - 4, j - 4, k, LOTRMod.slabSingle2, 9);
        this.setBlockAndNotifyAdequately(world, i + 4, j - 3, k, Blocks.torch, 2);
        this.setBlockAndNotifyAdequately(world, i + 4, j - 4, k, LOTRMod.slabSingle2, 9);
        this.setBlockAndNotifyAdequately(world, i, j - 3, k - 4, Blocks.torch, 3);
        this.setBlockAndNotifyAdequately(world, i, j - 4, k - 4, LOTRMod.slabSingle2, 9);
        this.setBlockAndNotifyAdequately(world, i, j - 3, k + 4, Blocks.torch, 4);
        this.setBlockAndNotifyAdequately(world, i, j - 4, k + 4, LOTRMod.slabSingle2, 9);
        for (i1 = i - 1; i1 <= i + 1; ++i1) {
            this.setBlockAndNotifyAdequately(world, i1, j - 4, k - 1, LOTRMod.stairsRohanBrick, 2);
            this.setBlockAndNotifyAdequately(world, i1, j - 4, k + 1, LOTRMod.stairsRohanBrick, 3);
        }
        this.setBlockAndNotifyAdequately(world, i - 1, j - 4, k, LOTRMod.stairsRohanBrick, 0);
        this.setBlockAndNotifyAdequately(world, i + 1, j - 4, k, LOTRMod.stairsRohanBrick, 1);
        this.placeSpawnerChest(world, i, j - 5, k, LOTRMod.spawnerChestStone, 4, LOTREntityRohanBarrowWraith.class);
        LOTRChestContents.fillChest(world, random, i, j - 5, k, LOTRChestContents.ROHAN_BARROWS);
        this.setBlockAndNotifyAdequately(world, i, j - 3, k, LOTRMod.slabSingle2, 1);
        return true;
    }
}

