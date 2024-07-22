/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityGondorTowerGuard;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenBeaconTower
extends LOTRWorldGenGondorStructure {
    public boolean generateRoom = true;

    public LOTRWorldGenBeaconTower(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        return this.generateWithSetRotationAndHeight(world, random, i, j, k, rotation, random.nextInt(4));
    }

    public boolean generateWithSetRotationAndHeight(World world, Random random, int i, int j, int k, int rotation, int height) {
        int j12;
        int j1;
        int i12;
        int i1;
        int k2;
        int k1;
        int i2;
        int doorBase = j - 1;
        this.setOriginAndRotation(world, i, j += height, k, rotation, 3);
        doorBase -= this.getY(0);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -3; i1 <= 3; ++i1) {
                for (k1 = -3; k1 <= 3; ++k1) {
                    j12 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j12, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (k1 = -2; k1 <= 2; ++k1) {
                for (j12 = 9; j12 <= 13; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (k1 = -2; k1 <= 2; ++k1) {
                i2 = Math.abs(i1);
                k2 = Math.abs(k1);
                for (j1 = 8; j1 >= doorBase || !this.isOpaque(world, i1, j1, k1) && this.getY(j1) >= 0; --j1) {
                    if (i2 == 2 && k2 == 2) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.pillarBlock, this.pillarMeta);
                    } else {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.brickBlock, this.brickMeta);
                    }
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                if (i2 == 2 && k2 == 2) {
                    for (j1 = 9; j1 <= 12; ++j1) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.pillarBlock, this.pillarMeta);
                    }
                    continue;
                }
                if (i2 != 2 && k2 != 2) continue;
                this.setBlockAndMetadata(world, i1, 9, k1, this.fenceBlock, this.fenceMeta);
            }
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            for (k1 = -3; k1 <= 3; ++k1) {
                i2 = Math.abs(i1);
                k2 = Math.abs(k1);
                if (!(i2 == 3 && k2 == 1 || k2 == 3 && i2 == 1)) continue;
                for (j1 = 4; j1 >= 1 || !this.isOpaque(world, i1, j1, k1) && this.getY(j1) >= 0; --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
        }
        for (int i13 : new int[]{-1, 1}) {
            this.setBlockAndMetadata(world, i13, 5, -3, this.brickStairBlock, 2);
            this.setBlockAndMetadata(world, i13, 5, 3, this.brickStairBlock, 3);
        }
        int[] i14 = new int[]{-1, 1};
        k1 = i14.length;
        for (i2 = 0; i2 < k1; ++i2) {
            int k12 = i14[i2];
            this.setBlockAndMetadata(world, -3, 5, k12, this.brickStairBlock, 1);
            this.setBlockAndMetadata(world, 3, 5, k12, this.brickStairBlock, 0);
        }
        for (i12 = -1; i12 <= 1; ++i12) {
            for (k1 = -1; k1 <= 1; ++k1) {
                this.setBlockAndMetadata(world, i12, 8, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
            }
        }
        this.setBlockAndMetadata(world, 0, 9, 0, this.rockBlock, this.rockMeta);
        this.setBlockAndMetadata(world, 0, 10, 0, LOTRMod.beacon, 0);
        this.setBlockAndMetadata(world, -2, 9, 0, this.fenceGateBlock, 3);
        int j13 = 8;
        while (!this.isOpaque(world, -3, j13, 0) && this.getY(j13) >= 0) {
            this.setBlockAndMetadata(world, -3, j13, 0, Blocks.ladder, 5);
            --j13;
        }
        this.setBlockAndMetadata(world, -2, 12, -1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 2, 12, -1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -2, 12, 1, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 12, 1, Blocks.torch, 4);
        for (i12 = -3; i12 <= 3; ++i12) {
            for (k1 = -3; k1 <= 3; ++k1) {
                i2 = Math.abs(i12);
                k2 = Math.abs(k1);
                if (i2 == 3 || k2 == 3) {
                    this.setBlockAndMetadata(world, i12, 13, k1, this.brickSlabBlock, this.brickSlabMeta | 8);
                    continue;
                }
                if (i2 == 2 || k2 == 2) {
                    if (i2 == 2 && k2 == 2) {
                        this.setBlockAndMetadata(world, i12, 13, k1, this.brickBlock, this.brickMeta);
                    } else {
                        this.setBlockAndMetadata(world, i12, 13, k1, this.brickSlabBlock, this.brickSlabMeta | 8);
                    }
                    this.setBlockAndMetadata(world, i12, 14, k1, this.brickSlabBlock, this.brickSlabMeta);
                    continue;
                }
                if (i2 != 1 && k2 != 1) continue;
                this.setBlockAndMetadata(world, i12, 14, k1, this.brickBlock, this.brickMeta);
            }
        }
        int[] i15 = new int[]{-2, 2};
        k1 = i15.length;
        for (i2 = 0; i2 < k1; ++i2) {
            int i13;
            i13 = i15[i2];
            int[] arrn = new int[]{-2, 2};
            int n = arrn.length;
            for (int i3 = 0; i3 < n; ++i3) {
                int k13 = arrn[i3];
                this.setBlockAndMetadata(world, i13, 13, k13 - 1, this.brickStairBlock, 6);
                this.setBlockAndMetadata(world, i13, 13, k13 + 1, this.brickStairBlock, 7);
                this.setBlockAndMetadata(world, i13 - 1, 13, k13, this.brickStairBlock, 5);
                this.setBlockAndMetadata(world, i13 + 1, 13, k13, this.brickStairBlock, 4);
            }
        }
        if (this.generateRoom) {
            this.setBlockAndMetadata(world, 0, doorBase, -2, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 0, doorBase + 1, -2, this.doorBlock, 1);
            this.setBlockAndMetadata(world, 0, doorBase + 2, -2, this.doorBlock, 9);
            for (int i16 = -1; i16 <= 1; ++i16) {
                for (k1 = -1; k1 <= 1; ++k1) {
                    this.setBlockAndMetadata(world, i16, doorBase, k1, this.brickBlock, this.brickMeta);
                    for (j12 = doorBase + 1; j12 <= doorBase + 4; ++j12) {
                        this.setAir(world, i16, j12, k1);
                    }
                }
            }
            this.setBlockAndMetadata(world, 0, doorBase + 3, -1, Blocks.torch, 3);
            this.setBlockAndMetadata(world, 1, doorBase + 1, -1, this.tableBlock, 0);
            this.placeWallBanner(world, 2, doorBase + 4, -1, this.bannerType, 3);
            this.placeChest(world, random, -1, doorBase + 1, -1, LOTRMod.chestLebethron, 3, LOTRChestContents.GONDOR_FORTRESS_SUPPLIES);
            for (j1 = doorBase + 1; j1 <= doorBase + 4; ++j1) {
                this.setBlockAndMetadata(world, 1, j1, 1, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 1, j1, 0, Blocks.ladder, 2);
            }
            this.setBlockAndMetadata(world, -1, doorBase + 2, 1, this.brickSlabBlock, this.brickSlabMeta | 8);
            this.setBlockAndMetadata(world, 0, doorBase + 2, 1, this.brickSlabBlock, this.brickSlabMeta | 8);
            for (int j14 : new int[]{doorBase + 1, doorBase + 3}) {
                this.setBlockAndMetadata(world, -1, j14, 1, this.bedBlock, 1);
                this.setBlockAndMetadata(world, 0, j14, 1, this.bedBlock, 9);
            }
        }
        int soldiers = 1 + random.nextInt(2);
        for (int l = 0; l < soldiers; ++l) {
            LOTREntityGondorTowerGuard soldier = new LOTREntityGondorTowerGuard(world);
            soldier.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(soldier, world, -1, 9, 0, 16);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClass(LOTREntityGondorTowerGuard.class);
        respawner.setCheckRanges(16, -12, 12, 4);
        respawner.setSpawnRanges(2, -2, 2, 16);
        this.placeNPCRespawner(respawner, world, 0, 9, 0);
        return true;
    }
}

