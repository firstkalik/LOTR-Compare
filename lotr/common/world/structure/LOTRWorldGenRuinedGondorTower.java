/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRWorldGenRuinedGondorTower
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenRuinedGondorTower(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j12;
        int i1;
        int k12;
        int i12;
        int j1;
        Block id;
        int k1;
        if (this.restrictions && world.getBlock(i, j - 1, k) != Blocks.grass) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                k += 4;
                break;
            }
            case 1: {
                i -= 4;
                break;
            }
            case 2: {
                k -= 4;
                break;
            }
            case 3: {
                i += 4;
            }
        }
        if (this.restrictions) {
            for (i12 = i - 3; i12 <= i + 3; ++i12) {
                for (k12 = k + 3; k12 <= k + 3; ++k12) {
                    j12 = world.getHeightValue(i12, k12);
                    Block block = world.getBlock(i12, j12 - 1, k12);
                    if (block == Blocks.grass || block.isWood((IBlockAccess)world, i12, j12 - 1, k12) || block.isLeaves((IBlockAccess)world, i12, j12 - 1, k12)) continue;
                    return false;
                }
            }
        }
        for (i12 = i - 3; i12 <= i + 3; ++i12) {
            for (k12 = k - 3; k12 <= k + 3; ++k12) {
                if (Math.abs(i12 - i) == 3 || Math.abs(k12 - k) == 3) {
                    for (j12 = j + 8; !(j12 < j && LOTRMod.isOpaque(world, i12, j12, k12) || j12 < 0); --j12) {
                        this.placeRandomBrick(world, random, i12, j12, k12);
                        this.setGrassToDirt(world, i12, j12 - 1, k12);
                    }
                } else {
                    for (j12 = j; !LOTRMod.isOpaque(world, i12, j12, k12) && j12 >= 0; --j12) {
                        this.placeRandomBrick(world, random, i12, j12, k12);
                        this.setGrassToDirt(world, i12, j12 - 1, k12);
                    }
                    for (j12 = j + 1; j12 <= j + 8; ++j12) {
                        this.setBlockAndNotifyAdequately(world, i12, j12, k12, Blocks.air, 0);
                    }
                }
                if (Math.abs(i12 - i) >= 3 || Math.abs(k12 - k) >= 3 || random.nextInt(20) == 0) continue;
                this.setBlockAndNotifyAdequately(world, i12, j + 5, k12, Blocks.planks, 0);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 2, j + 1, k - 2, LOTRMod.chestLebethron, 0);
        if (random.nextInt(3) == 0) {
            LOTRChestContents.fillChest(world, random, i - 2, j + 1, k - 2, LOTRChestContents.GONDOR_FORTRESS_SUPPLIES);
        }
        this.setBlockAndNotifyAdequately(world, i + 2, j + 1, k - 2, LOTRMod.gondorianTable, 0);
        if (random.nextInt(3) != 0) {
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k - 2, LOTRMod.strawBed, 10);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k - 1, LOTRMod.strawBed, 2);
        }
        if (random.nextBoolean()) {
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k + 2, Blocks.anvil, 8);
        }
        for (k1 = k; k1 <= k + 2; ++k1) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 6, k1, LOTRMod.slabSingle, 10);
        }
        if (random.nextBoolean()) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 7, k, LOTRMod.mugBlock, 1);
        }
        if (random.nextBoolean()) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 7, k + 1, LOTRMod.plateBlock, 0);
        }
        if (random.nextBoolean()) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 7, k + 2, LOTRMod.barrel, 5);
        }
        for (i12 = i - 4; i12 <= i + 4; ++i12) {
            for (k12 = k - 4; k12 <= k + 4; ++k12) {
                this.placeRandomBrick(world, random, i12, j + 9, k12);
                if ((Math.abs(i12 - i) != 4 || Math.abs(k12 - k) % 2 != 0) && (Math.abs(k12 - k) != 4 || Math.abs(i12 - i) % 2 != 0) || !LOTRMod.isOpaque(world, i12, j + 9, k12)) continue;
                this.placeRandomBrick(world, random, i12, j + 10, k12);
            }
        }
        for (j1 = j + 1; j1 <= j + 9; ++j1) {
            if (rotation == 2) {
                if (!LOTRMod.isOpaque(world, i + 3, j1, k)) continue;
                this.setBlockAndNotifyAdequately(world, i + 2, j1, k, Blocks.ladder, 4);
                continue;
            }
            if (!LOTRMod.isOpaque(world, i, j1, k + 3)) continue;
            this.setBlockAndNotifyAdequately(world, i, j1, k + 2, Blocks.ladder, 2);
        }
        if (rotation == 0) {
            for (j1 = j + 1; j1 <= j + 2; ++j1) {
                this.setBlockAndNotifyAdequately(world, i - 1, j1, k - 3, LOTRMod.brick, 5);
                this.setBlockAndNotifyAdequately(world, i, j1, k - 3, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 1, j1, k - 3, LOTRMod.brick, 5);
            }
            for (k1 = k - 4; k1 >= k - 7; --k1) {
                for (i1 = i - 2; i1 <= i + 2; i1 += 4) {
                    j12 = world.getHeightValue(i1, k1);
                    id = world.getBlock(i1, j12 - 1, k1);
                    if (id != Blocks.grass || random.nextInt(4) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.wall, 3 + random.nextInt(3));
                }
            }
        }
        if (rotation == 1) {
            for (j1 = j + 1; j1 <= j + 2; ++j1) {
                this.setBlockAndNotifyAdequately(world, i + 3, j1, k - 1, LOTRMod.brick, 5);
                this.setBlockAndNotifyAdequately(world, i + 3, j1, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 3, j1, k + 1, LOTRMod.brick, 5);
            }
            for (i12 = i + 4; i12 <= i + 7; ++i12) {
                for (k12 = k - 2; k12 <= k + 2; k12 += 4) {
                    j12 = world.getHeightValue(i12, k12);
                    id = world.getBlock(i12, j12 - 1, k12);
                    if (id != Blocks.grass || random.nextInt(4) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j12, k12, LOTRMod.wall, 3 + random.nextInt(3));
                }
            }
        }
        if (rotation == 2) {
            for (j1 = j + 1; j1 <= j + 2; ++j1) {
                this.setBlockAndNotifyAdequately(world, i - 1, j1, k + 3, LOTRMod.brick, 5);
                this.setBlockAndNotifyAdequately(world, i, j1, k + 3, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 1, j1, k + 3, LOTRMod.brick, 5);
            }
            for (k1 = k + 4; k1 <= k + 7; ++k1) {
                for (i1 = i - 2; i1 <= i + 2; i1 += 4) {
                    j12 = world.getHeightValue(i1, k1);
                    id = world.getBlock(i1, j12 - 1, k1);
                    if (id != Blocks.grass || random.nextInt(4) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.wall, 3 + random.nextInt(3));
                }
            }
        }
        if (rotation == 3) {
            for (j1 = j + 1; j1 <= j + 2; ++j1) {
                this.setBlockAndNotifyAdequately(world, i - 3, j1, k - 1, LOTRMod.brick, 5);
                this.setBlockAndNotifyAdequately(world, i - 3, j1, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i - 3, j1, k + 1, LOTRMod.brick, 5);
            }
            for (i12 = i - 4; i12 >= i - 7; --i12) {
                for (k12 = k - 2; k12 <= k + 2; k12 += 4) {
                    j12 = world.getHeightValue(i12, k12);
                    id = world.getBlock(i12, j12 - 1, k12);
                    if (id != Blocks.grass || random.nextInt(4) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j12, k12, LOTRMod.wall, 3 + random.nextInt(3));
                }
            }
        }
        int radius = 8;
        int ruinParts = 2 + random.nextInt(6);
        for (int l = 0; l < ruinParts; ++l) {
            int j2;
            int j13;
            int k13;
            int i13 = i - random.nextInt(radius * 2) + random.nextInt(radius * 2);
            Block id2 = world.getBlock(i13, (j13 = world.getHeightValue(i13, k13 = k - random.nextInt(radius * 2) + random.nextInt(radius * 2))) - 1, k13);
            if (id2 != Blocks.grass) continue;
            int height = 1 + random.nextInt(3);
            boolean flag = true;
            for (j2 = j13; j2 < j13 + height && flag; ++j2) {
                flag = !LOTRMod.isOpaque(world, i13, j2, k13);
            }
            if (!flag) continue;
            for (j2 = j13; j2 < j13 + height; ++j2) {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick, 2 + random.nextInt(2));
            }
            this.setGrassToDirt(world, i13, j13 - 1, k13);
        }
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(16) == 0) {
            return;
        }
        if (random.nextInt(4) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick, 2 + random.nextInt(2));
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick, 1);
        }
    }
}

