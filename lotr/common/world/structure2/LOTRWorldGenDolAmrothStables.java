/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityDolAmrothCaptain;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenDolAmrothStables
extends LOTRWorldGenStructureBase2 {
    private Block brickBlock = LOTRMod.brick3;
    private int brickMeta = 9;
    private Block slabBlock = LOTRMod.slabSingle6;
    private int slabMeta = 7;
    private Block stairBlock = LOTRMod.stairsDolAmrothBrick;
    private Block wallBlock = LOTRMod.wall2;
    private int wallMeta = 14;
    private Block rockSlabBlock = LOTRMod.slabSingle;
    private Block doubleRockSlabBlock = LOTRMod.slabDouble;
    private int rockSlabMeta = 2;
    private Block pillarBlock = LOTRMod.pillar;
    private int pillarMeta = 6;
    private Block logBlock = Blocks.log;
    private int logMeta = 0;
    private Block plankBlock = Blocks.planks;
    private int plankMeta = 0;
    private Block plankSlabBlock = Blocks.wooden_slab;
    private int plankSlabMeta = 0;
    private Block fenceBlock = Blocks.fence;
    private int fenceMeta = 0;
    private Block gateBlock;
    private Block woodBeamBlock = LOTRMod.woodBeamV1;
    private int woodBeamMeta = 0;
    private Block roofBlock;
    private int roofMeta = 11;
    private Block roofSlabBlock;
    private int roofSlabMeta = 3;
    private Block roofStairBlock;
    private Block leafBlock;
    private int leafMeta = 6;

    public LOTRWorldGenDolAmrothStables(boolean flag) {
        super(flag);
        this.gateBlock = Blocks.fence_gate;
        this.roofBlock = LOTRMod.clayTileDyed;
        this.roofSlabBlock = LOTRMod.slabClayTileDyedSingle2;
        this.roofStairBlock = LOTRMod.stairsClayTileDyedBlue;
        this.leafBlock = LOTRMod.leaves4;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int j1;
        int k12;
        int i1;
        int i12;
        int k13;
        int i22;
        int i132;
        int i14;
        int i15;
        int j12;
        int i16;
        int k14;
        int j13;
        int k15;
        int k162;
        int i17;
        int j14;
        int j15;
        int i18;
        int k172;
        this.setOriginAndRotation(world, i, j, k, rotation, 2);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i18 = -9; i18 <= 9; ++i18) {
                for (k162 = -1; k162 <= 19; ++k162) {
                    j13 = this.getTopBlock(world, i18, k162);
                    Block block = this.getBlock(world, i18, j13 - 1, k162);
                    if (block != Blocks.grass) {
                        return false;
                    }
                    if (j13 < minHeight) {
                        minHeight = j13;
                    }
                    if (j13 > maxHeight) {
                        maxHeight = j13;
                    }
                    if (maxHeight - minHeight <= 7) continue;
                    return false;
                }
            }
        }
        for (int segment = 0; segment <= 2; ++segment) {
            int i192;
            int i110;
            int k18;
            int kz = segment * 4;
            for (i18 = -8; i18 <= 8; ++i18) {
                for (k162 = kz; k162 <= kz + 3; ++k162) {
                    for (j13 = 0; !(j13 != 0 && this.isOpaque(world, i18, j13, k162) || this.getY(j13) < 0); --j13) {
                        this.setBlockAndMetadata(world, i18, j13, k162, this.brickBlock, this.brickMeta);
                        this.setGrassToDirt(world, i18, j13 - 1, k162);
                    }
                    for (j13 = 1; j13 <= 7; ++j13) {
                        this.setAir(world, i18, j13, k162);
                    }
                }
            }
            this.placeWoodPillar(world, -8, kz);
            this.placeWoodPillar(world, 8, kz);
            int[] i111 = new int[]{-3, 3};
            k162 = i111.length;
            for (j13 = 0; j13 < k162; ++j13) {
                i192 = i111[j13];
                if (segment == 0) {
                    this.placeWoodPillar(world, i192, kz);
                    continue;
                }
                for (j15 = 1; j15 <= 3; ++j15) {
                    this.setBlockAndMetadata(world, i192, j15, kz, this.logBlock, this.logMeta);
                }
            }
            for (i110 = -7; i110 <= -4; ++i110) {
                this.setBlockAndMetadata(world, i110, 1, kz, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i110, 2, kz, this.wallBlock, this.wallMeta);
                this.setBlockAndMetadata(world, i110, 4, kz, this.brickBlock, this.brickMeta);
            }
            for (i110 = 4; i110 <= 7; ++i110) {
                this.setBlockAndMetadata(world, i110, 1, kz, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i110, 2, kz, this.wallBlock, this.wallMeta);
                this.setBlockAndMetadata(world, i110, 4, kz, this.brickBlock, this.brickMeta);
            }
            this.setBlockAndMetadata(world, -4, 2, kz, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -4, 3, kz, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, 4, 2, kz, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 4, 3, kz, this.wallBlock, this.wallMeta);
            for (k18 = kz + 1; k18 <= kz + 3; ++k18) {
                this.setBlockAndMetadata(world, -8, 1, k18, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, -8, 2, k18, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, -8, 3, k18, this.rockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, -8, 4, k18, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 8, 1, k18, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, 8, 2, k18, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 8, 3, k18, this.rockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, 8, 4, k18, this.brickBlock, this.brickMeta);
            }
            for (int j16 = 1; j16 <= 3; ++j16) {
                this.setBlockAndMetadata(world, -2, j16, kz, this.fenceBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 2, j16, kz, this.fenceBlock, this.brickMeta);
            }
            for (k18 = kz + 1; k18 <= kz + 3; ++k18) {
                for (i132 = -7; i132 <= -3; ++i132) {
                    this.setBlockAndMetadata(world, i132, 0, k18, this.doubleRockSlabBlock, this.rockSlabMeta);
                    if (random.nextInt(3) == 0) continue;
                    this.setBlockAndMetadata(world, i132, 1, k18, LOTRMod.thatchFloor, 0);
                }
                for (i132 = 3; i132 <= 7; ++i132) {
                    this.setBlockAndMetadata(world, i132, 0, k18, this.doubleRockSlabBlock, this.rockSlabMeta);
                    if (random.nextInt(3) == 0) continue;
                    this.setBlockAndMetadata(world, i132, 1, k18, LOTRMod.thatchFloor, 0);
                }
            }
            for (int i192 : new int[]{-7, 7}) {
                this.setBlockAndMetadata(world, i192, 1, kz + 1, Blocks.hay_block, 0);
                this.setBlockAndMetadata(world, i192, 1, kz + 2, Blocks.hay_block, 0);
                this.setBlockAndMetadata(world, i192, 1, kz + 3, this.fenceBlock, this.fenceMeta);
            }
            for (int i192 : new int[]{-3, 3}) {
                this.setBlockAndMetadata(world, i192, 1, kz + 1, this.gateBlock, 1);
                this.setBlockAndMetadata(world, i192, 1, kz + 2, this.gateBlock, 1);
                this.setBlockAndMetadata(world, i192, 1, kz + 3, this.fenceBlock, this.fenceMeta);
                this.setBlockAndMetadata(world, i192, 2, kz + 3, Blocks.torch, 5);
            }
            int[] k19 = new int[]{-1, 1};
            i132 = k19.length;
            for (j13 = 0; j13 < i132; ++j13) {
                int f = k19[j13];
                LOTREntityHorse horse = new LOTREntityHorse(world);
                this.spawnNPCAndSetHome((EntityCreature)horse, world, 5 * f, 1, kz + 2, 0);
                horse.setHorseType(0);
                horse.saddleMountForWorldGen();
                horse.detachHome();
            }
            for (int i112 = -7; i112 <= 7; ++i112) {
                for (k162 = kz + 1; k162 <= kz + 3; ++k162) {
                    this.setBlockAndMetadata(world, i112, 4, k162, this.rockSlabBlock, this.rockSlabMeta | 8);
                }
                if (segment <= 0) continue;
                if (Math.abs(i112) <= 1) {
                    this.setBlockAndMetadata(world, i112, 4, kz, this.rockSlabBlock, this.rockSlabMeta | 8);
                    continue;
                }
                this.setBlockAndMetadata(world, i112, 4, kz, this.brickBlock, this.brickMeta);
            }
            for (int i192 : new int[]{-3, 3}) {
                this.setBlockAndMetadata(world, i192, 4, kz + 1, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, i192, 4, kz + 3, this.doubleRockSlabBlock, this.rockSlabMeta);
            }
            for (int i113 = -8; i113 <= 8; ++i113) {
                this.setBlockAndMetadata(world, i113, 5, kz, this.brickBlock, this.brickMeta);
            }
            for (int i192 : new int[]{-7, 4}) {
                this.setBlockAndMetadata(world, i192, 6, kz, this.stairBlock, 1);
                this.setBlockAndMetadata(world, i192 + 1, 6, kz, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i192 + 1, 7, kz, this.stairBlock, 1);
                this.setBlockAndMetadata(world, i192 + 2, 6, kz, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i192 + 2, 7, kz, this.stairBlock, 0);
                this.setBlockAndMetadata(world, i192 + 3, 6, kz, this.stairBlock, 0);
                for (k12 = kz + 1; k12 <= kz + 3; ++k12) {
                    this.setBlockAndMetadata(world, i192, 5, k12, this.roofStairBlock, 1);
                    this.setBlockAndMetadata(world, i192 + 1, 5, k12, this.brickBlock, this.brickMeta);
                    this.setBlockAndMetadata(world, i192 + 1, 6, k12, this.roofStairBlock, 1);
                    this.setBlockAndMetadata(world, i192 + 2, 5, k12, this.brickBlock, this.brickMeta);
                    this.setBlockAndMetadata(world, i192 + 2, 6, k12, this.roofStairBlock, 0);
                    this.setBlockAndMetadata(world, i192 + 3, 5, k12, this.roofStairBlock, 0);
                }
            }
            this.setBlockAndMetadata(world, -2, 6, kz, this.stairBlock, 1);
            this.setBlockAndMetadata(world, -1, 6, kz, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -1, 7, kz, this.stairBlock, 1);
            this.setBlockAndMetadata(world, 0, 6, kz, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 0, 7, kz, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 0, 8, kz, this.slabBlock, this.slabMeta);
            this.setBlockAndMetadata(world, 1, 6, kz, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 1, 7, kz, this.stairBlock, 0);
            this.setBlockAndMetadata(world, 2, 6, kz, this.stairBlock, 0);
            for (int k110 = kz + 1; k110 <= kz + 3; ++k110) {
                this.setBlockAndMetadata(world, -2, 5, k110, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, -1, 5, k110, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, -1, 6, k110, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 0, 5, k110, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 0, 6, k110, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, 0, 7, k110, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, 1, 5, k110, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 1, 6, k110, this.roofStairBlock, 0);
                this.setBlockAndMetadata(world, 2, 5, k110, this.roofStairBlock, 0);
            }
            int[] k110 = new int[]{-8, -3, 3, 8};
            k162 = k110.length;
            for (j13 = 0; j13 < k162; ++j13) {
                i192 = k110[j13];
                for (k12 = kz + 1; k12 <= kz + 3; ++k12) {
                    this.setBlockAndMetadata(world, i192, 5, k12, this.wallBlock, this.wallMeta);
                }
            }
        }
        for (i15 = -9; i15 <= 9; ++i15) {
            i22 = Math.abs(i15);
            if (i22 <= 2) {
                this.setBlockAndMetadata(world, i15, 0, -1, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, i15, 0, -2, this.doubleRockSlabBlock, this.rockSlabMeta);
                continue;
            }
            this.placeGrassFoundation(world, i15, -1);
            if (i22 == 3 || i22 == 8) {
                for (j12 = 1; j12 <= 4; ++j12) {
                    this.setBlockAndMetadata(world, i15, j12, -1, this.wallBlock, this.wallMeta);
                }
                continue;
            }
            this.setBlockAndMetadata(world, i15, 1, -1, this.leafBlock, this.leafMeta);
        }
        for (int k111 = 0; k111 <= 11; ++k111) {
            int[] i22 = new int[]{-9, 9};
            j12 = i22.length;
            for (k162 = 0; k162 < j12; ++k162) {
                i14 = i22[k162];
                this.placeGrassFoundation(world, i14, k111);
                if (k111 % 4 == 0) {
                    for (j14 = 1; j14 <= 4; ++j14) {
                        this.setBlockAndMetadata(world, i14, j14, k111, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                this.setBlockAndMetadata(world, i14, 1, k111, this.leafBlock, this.leafMeta);
            }
            if (k111 % 4 != 0) continue;
            this.setBlockAndMetadata(world, -9, 5, k111, this.stairBlock, 1);
            this.setBlockAndMetadata(world, 9, 5, k111, this.stairBlock, 0);
        }
        this.setBlockAndMetadata(world, -1, 5, 0, this.stairBlock, 4);
        this.setBlockAndMetadata(world, 0, 5, 0, this.stairBlock, 6);
        this.setBlockAndMetadata(world, 1, 5, 0, this.stairBlock, 5);
        this.setBlockAndMetadata(world, 0, 6, 0, LOTRMod.brick, 5);
        for (i15 = -1; i15 <= 1; ++i15) {
            this.setBlockAndMetadata(world, i15, 0, 0, this.doubleRockSlabBlock, this.rockSlabMeta);
            for (j1 = 1; j1 <= 4; ++j1) {
                this.setBlockAndMetadata(world, i15, j1, 0, LOTRMod.gateDolAmroth, 2);
            }
        }
        for (int i132 : new int[]{-2, 2}) {
            for (j13 = 1; j13 <= 4; ++j13) {
                this.setBlockAndMetadata(world, i132, j13, 0, this.brickBlock, this.brickMeta);
            }
            this.placeWallBanner(world, i132, 4, 0, LOTRItemBanner.BannerType.DOL_AMROTH, 2);
        }
        this.setBlockAndMetadata(world, -8, 5, -1, this.stairBlock, 1);
        this.setBlockAndMetadata(world, 8, 5, -1, this.stairBlock, 0);
        int[] i114 = new int[]{-7, 4};
        j1 = i114.length;
        for (j12 = 0; j12 < j1; ++j12) {
            i132 = i114[j12];
            this.setBlockAndMetadata(world, i132 + 0, 5, -1, this.stairBlock, 4);
            this.setBlockAndMetadata(world, i132 + 0, 6, -1, this.stairBlock, 1);
            this.setBlockAndMetadata(world, i132 + 1, 6, -1, this.stairBlock, 4);
            this.setBlockAndMetadata(world, i132 + 1, 7, -1, this.stairBlock, 1);
            this.setBlockAndMetadata(world, i132 + 2, 6, -1, this.stairBlock, 5);
            this.setBlockAndMetadata(world, i132 + 2, 7, -1, this.stairBlock, 0);
            this.setBlockAndMetadata(world, i132 + 3, 5, -1, this.stairBlock, 5);
            this.setBlockAndMetadata(world, i132 + 3, 6, -1, this.stairBlock, 0);
            this.setBlockAndMetadata(world, i132 + 1, 5, 0, this.stairBlock, 4);
            this.setBlockAndMetadata(world, i132 + 2, 5, 0, this.stairBlock, 5);
        }
        this.setBlockAndMetadata(world, -3, 5, -1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -2, 5, -1, this.stairBlock, 4);
        this.setBlockAndMetadata(world, -2, 6, -1, this.stairBlock, 1);
        this.setBlockAndMetadata(world, -1, 6, -1, this.stairBlock, 4);
        this.setBlockAndMetadata(world, -1, 7, -1, this.stairBlock, 1);
        this.setBlockAndMetadata(world, 0, 7, -1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 8, -1, this.slabBlock, this.slabMeta);
        this.setBlockAndMetadata(world, 1, 6, -1, this.stairBlock, 5);
        this.setBlockAndMetadata(world, 1, 7, -1, this.stairBlock, 0);
        this.setBlockAndMetadata(world, 2, 5, -1, this.stairBlock, 5);
        this.setBlockAndMetadata(world, 2, 6, -1, this.stairBlock, 0);
        this.setBlockAndMetadata(world, 3, 5, -1, this.brickBlock, this.brickMeta);
        for (k15 = 1; k15 <= 3; ++k15) {
            for (i16 = -1; i16 <= 1; ++i16) {
                if (k15 == 3 && Math.abs(i16) >= 1) continue;
                this.setAir(world, i16, 4, k15);
            }
        }
        for (k15 = 1; k15 <= 11; ++k15) {
            this.setBlockAndMetadata(world, 0, 0, k15, this.doubleRockSlabBlock, this.rockSlabMeta);
        }
        for (int i115 = -9; i115 <= 9; ++i115) {
            for (k172 = 12; k172 <= 18; ++k172) {
                for (j12 = 0; !(j12 != 0 && this.isOpaque(world, i115, j12, k172) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i115, j12, k172, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i115, j12 - 1, k172);
                }
                for (j12 = 1; j12 <= 9; ++j12) {
                    this.setAir(world, i115, j12, k172);
                }
            }
        }
        int[] i115 = new int[]{12, 18};
        k172 = i115.length;
        for (j12 = 0; j12 < k172; ++j12) {
            k162 = i115[j12];
            for (i14 = -9; i14 <= 9; ++i14) {
                this.setBlockAndMetadata(world, i14, 1, k162, this.doubleRockSlabBlock, this.rockSlabMeta);
                for (j14 = 2; j14 <= 6; ++j14) {
                    this.setBlockAndMetadata(world, i14, j14, k162, this.brickBlock, this.brickMeta);
                }
            }
            int[] i116 = new int[]{-9, 9};
            j14 = i116.length;
            for (k12 = 0; k12 < j14; ++k12) {
                int i117 = i116[k12];
                this.placeWoodPillar(world, i117, k162);
                this.setBlockAndMetadata(world, i117, 4, k162, this.doubleRockSlabBlock, this.rockSlabMeta);
                for (int j17 = 5; j17 <= 7; ++j17) {
                    this.setBlockAndMetadata(world, i117, j17, k162, this.pillarBlock, this.pillarMeta);
                }
                this.setBlockAndMetadata(world, i117, 8, k162, this.rockSlabBlock, this.rockSlabMeta);
            }
            for (i14 = -8; i14 <= 8; ++i14) {
                int i23 = Math.abs(i14);
                if (i23 >= 5) {
                    if (i23 % 2 == 0) {
                        this.setBlockAndMetadata(world, i14, 7, k162, this.slabBlock, this.slabMeta);
                    } else {
                        this.setBlockAndMetadata(world, i14, 7, k162, this.brickBlock, this.brickMeta);
                    }
                }
                if (i23 == 4) {
                    for (j15 = 5; j15 <= 10; ++j15) {
                        this.setBlockAndMetadata(world, i14, j15, k162, this.pillarBlock, this.pillarMeta);
                    }
                    this.setBlockAndMetadata(world, i14, 11, k162, this.slabBlock, this.slabMeta);
                }
                if (i23 > 3) continue;
                for (j15 = 7; j15 <= 8; ++j15) {
                    this.setBlockAndMetadata(world, i14, j15, k162, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i14, 9, k162, this.doubleRockSlabBlock, this.rockSlabMeta);
                if (i23 >= 1) {
                    this.setBlockAndMetadata(world, i14, 10, k162, this.brickBlock, this.brickMeta);
                    if (i23 % 2 == 0) {
                        this.setBlockAndMetadata(world, i14, 11, k162, this.slabBlock, this.slabMeta);
                    }
                }
                if (i23 != 0) continue;
                this.setBlockAndMetadata(world, i14, 10, k162, this.slabBlock, this.slabMeta);
            }
        }
        for (k1 = 13; k1 <= 17; ++k1) {
            int[] k172 = new int[]{-4, 4};
            j12 = k172.length;
            for (k162 = 0; k162 < j12; ++k162) {
                i14 = k172[k162];
                this.setBlockAndMetadata(world, i14, 9, k1, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, i14, 10, k1, this.brickBlock, this.brickMeta);
                if (k1 % 2 != 0) continue;
                this.setBlockAndMetadata(world, i14, 11, k1, this.slabBlock, this.slabMeta);
            }
        }
        for (int i118 = -3; i118 <= 3; ++i118) {
            i22 = Math.abs(i118);
            for (k13 = 13; k13 <= 17; ++k13) {
                this.setBlockAndMetadata(world, i118, 9, k13, this.doubleRockSlabBlock, this.rockSlabMeta);
            }
            for (k13 = 14; k13 <= 16; ++k13) {
                this.setBlockAndMetadata(world, i118, 10, k13, this.doubleRockSlabBlock, this.rockSlabMeta);
                if (i22 <= 2) {
                    this.setBlockAndMetadata(world, i118, 11, k13, this.brickBlock, this.brickMeta);
                }
                if (i22 > 1) continue;
                this.setBlockAndMetadata(world, i118, 12, k13, this.brickBlock, this.brickMeta);
            }
        }
        for (k1 = 13; k1 <= 17; ++k1) {
            this.setBlockAndMetadata(world, -3, 11, k1, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, -2, 12, k1, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, -1, 13, k1, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 0, 13, k1, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 0, 14, k1, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, 1, 13, k1, this.roofStairBlock, 0);
            this.setBlockAndMetadata(world, 2, 12, k1, this.roofStairBlock, 0);
            this.setBlockAndMetadata(world, 3, 11, k1, this.roofStairBlock, 0);
        }
        int[] k112 = new int[]{13, 17};
        i22 = k112.length;
        for (k13 = 0; k13 < i22; ++k13) {
            k162 = k112[k13];
            this.setBlockAndMetadata(world, -3, 10, k162, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, -2, 11, k162, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, -1, 12, k162, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, 1, 12, k162, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, 2, 11, k162, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, 3, 10, k162, this.roofStairBlock, 5);
        }
        this.placeWallBanner(world, 0, 12, 14, LOTRItemBanner.BannerType.DOL_AMROTH, 2);
        this.placeWallBanner(world, 0, 12, 16, LOTRItemBanner.BannerType.DOL_AMROTH, 0);
        this.placeWallBanner(world, -4, 9, 12, LOTRItemBanner.BannerType.DOL_AMROTH, 2);
        this.setBlockAndMetadata(world, -3, 9, 12, this.doubleRockSlabBlock, this.rockSlabMeta);
        this.setBlockAndMetadata(world, -2, 9, 12, this.stairBlock, 6);
        this.setBlockAndMetadata(world, -2, 8, 12, LOTRMod.stainedGlassPane, 11);
        this.setBlockAndMetadata(world, -1, 9, 12, this.doubleRockSlabBlock, this.rockSlabMeta);
        this.setBlockAndMetadata(world, 0, 9, 12, this.stairBlock, 6);
        this.setBlockAndMetadata(world, 0, 8, 12, LOTRMod.stainedGlassPane, 0);
        this.setBlockAndMetadata(world, 1, 9, 12, this.doubleRockSlabBlock, this.rockSlabMeta);
        this.setBlockAndMetadata(world, 2, 9, 12, this.stairBlock, 6);
        this.setBlockAndMetadata(world, 2, 8, 12, LOTRMod.stainedGlassPane, 11);
        this.setBlockAndMetadata(world, 3, 9, 12, this.doubleRockSlabBlock, this.rockSlabMeta);
        this.placeWallBanner(world, 4, 9, 12, LOTRItemBanner.BannerType.DOL_AMROTH, 2);
        this.placeWoodPillar(world, -3, 12);
        this.placeWoodPillar(world, 3, 12);
        this.placeWoodPillar(world, -6, 18);
        this.placeWoodPillar(world, -2, 18);
        this.placeWoodPillar(world, 2, 18);
        this.placeWoodPillar(world, 6, 18);
        for (k14 = 13; k14 <= 17; ++k14) {
            int[] i24 = new int[]{-9, 9};
            k13 = i24.length;
            for (k162 = 0; k162 < k13; ++k162) {
                i14 = i24[k162];
                this.setBlockAndMetadata(world, i14, 1, k14, this.doubleRockSlabBlock, this.rockSlabMeta);
                for (j14 = 2; j14 <= 3; ++j14) {
                    this.setBlockAndMetadata(world, i14, j14, k14, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i14, 4, k14, this.doubleRockSlabBlock, this.rockSlabMeta);
                for (j14 = 5; j14 <= 6; ++j14) {
                    this.setBlockAndMetadata(world, i14, j14, k14, this.brickBlock, this.brickMeta);
                }
                if (k14 % 2 == 1) {
                    this.setBlockAndMetadata(world, i14, 7, k14, this.slabBlock, this.slabMeta);
                    continue;
                }
                this.setBlockAndMetadata(world, i14, 7, k14, this.brickBlock, this.brickMeta);
            }
        }
        for (k14 = 13; k14 <= 17; ++k14) {
            for (int step = 0; step <= 4; ++step) {
                this.setBlockAndMetadata(world, -8 + step, 4 + step, k14, this.stairBlock, 4);
                this.setBlockAndMetadata(world, 8 - step, 4 + step, k14, this.stairBlock, 5);
            }
            this.setBlockAndMetadata(world, -8, 5, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -8, 6, k14, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, -7, 6, k14, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, 8, 5, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 8, 6, k14, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, 7, 6, k14, (Block)Blocks.grass, 0);
        }
        for (i12 = -8; i12 <= 8; ++i12) {
            i22 = Math.abs(i12);
            if (i22 == 8) {
                for (k13 = 13; k13 <= 17; ++k13) {
                    this.setBlockAndMetadata(world, i12, 7, k13, this.leafBlock, this.leafMeta);
                }
                for (k13 = 14; k13 <= 16; ++k13) {
                    this.setBlockAndMetadata(world, i12, 8, k13, this.leafBlock, this.leafMeta);
                }
                continue;
            }
            if (i22 == 7) {
                for (k13 = 13; k13 <= 17; ++k13) {
                    this.setBlockAndMetadata(world, i12, 7, k13, this.leafBlock, this.leafMeta);
                }
                this.setBlockAndMetadata(world, i12, 8, 13, this.leafBlock, this.leafMeta);
                this.setBlockAndMetadata(world, i12, 8, 17, this.leafBlock, this.leafMeta);
                this.setBlockAndMetadata(world, i12, 7, 15, this.rockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, i12, 6, 15, this.brickBlock, this.brickMeta);
                continue;
            }
            if (i22 == 6) {
                this.setBlockAndMetadata(world, i12, 7, 13, this.leafBlock, this.leafMeta);
                this.setBlockAndMetadata(world, i12, 7, 17, this.leafBlock, this.leafMeta);
                this.setBlockAndMetadata(world, i12, 8, 13, this.leafBlock, this.leafMeta);
                this.setBlockAndMetadata(world, i12, 8, 17, this.leafBlock, this.leafMeta);
                this.setBlockAndMetadata(world, i12, 7, 14, this.rockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, i12, 7, 15, this.rockSlabBlock, this.rockSlabMeta);
                this.setBlockAndMetadata(world, i12, 7, 16, this.rockSlabBlock, this.rockSlabMeta);
                continue;
            }
            if (i22 != 5) continue;
            this.setBlockAndMetadata(world, i12, 8, 13, this.leafBlock, this.leafMeta);
            this.setBlockAndMetadata(world, i12, 8, 14, this.leafBlock, this.leafMeta);
            this.setBlockAndMetadata(world, i12, 8, 16, this.leafBlock, this.leafMeta);
            this.setBlockAndMetadata(world, i12, 8, 17, this.leafBlock, this.leafMeta);
        }
        for (i12 = -8; i12 <= 8; ++i12) {
            for (k172 = 13; k172 <= 17; ++k172) {
                this.setBlockAndMetadata(world, i12, 0, k172, this.doubleRockSlabBlock, this.rockSlabMeta);
            }
        }
        for (i12 = -2; i12 <= 2; ++i12) {
            this.setBlockAndMetadata(world, i12, 0, 12, this.doubleRockSlabBlock, this.rockSlabMeta);
            for (j1 = 1; j1 <= 3; ++j1) {
                this.setAir(world, i12, j1, 12);
            }
        }
        for (int j18 = 1; j18 <= 3; ++j18) {
            int[] j19 = new int[]{-2, 2};
            k13 = j19.length;
            for (k162 = 0; k162 < k13; ++k162) {
                i14 = j19[k162];
                this.setBlockAndMetadata(world, i14, j18, 12, this.brickBlock, this.brickMeta);
            }
            for (int i119 = -1; i119 <= 1; ++i119) {
                this.setBlockAndMetadata(world, i119, j18, 12, LOTRMod.gateDolAmroth, 2);
            }
        }
        for (int f : new int[]{-1, 1}) {
            this.setBlockAndMetadata(world, 4 * f, 4, 13, this.slabBlock, this.slabMeta | 8);
            this.setBlockAndMetadata(world, 3 * f, 5, 13, this.slabBlock, this.slabMeta);
            this.setBlockAndMetadata(world, 2 * f, 5, 13, this.slabBlock, this.slabMeta | 8);
            this.setBlockAndMetadata(world, 1 * f, 6, 13, this.slabBlock, this.slabMeta);
            this.setBlockAndMetadata(world, 0 * f, 6, 13, this.slabBlock, this.slabMeta);
            this.placeWallBanner(world, 3 * f, 4, 12, LOTRItemBanner.BannerType.DOL_AMROTH, 0);
            this.setBlockAndMetadata(world, 6 * f, 3, 13, Blocks.torch, 3);
            for (j13 = 5; j13 <= 6; ++j13) {
                this.setBlockAndMetadata(world, 6 * f, j13, 18, this.woodBeamBlock, this.woodBeamMeta);
            }
            for (j13 = 5; j13 <= 7; ++j13) {
                this.setBlockAndMetadata(world, 2 * f, j13, 18, this.woodBeamBlock, this.woodBeamMeta);
            }
            this.placeWallBanner(world, 6 * f, 5, 18, LOTRItemBanner.BannerType.DOL_AMROTH, 0);
            this.placeWallBanner(world, 6 * f, 5, 18, LOTRItemBanner.BannerType.DOL_AMROTH, 2);
            this.placeWallBanner(world, 2 * f, 6, 18, LOTRItemBanner.BannerType.DOL_AMROTH, 0);
            this.placeWallBanner(world, 2 * f, 6, 18, LOTRItemBanner.BannerType.DOL_AMROTH, 2);
            this.setBlockAndMetadata(world, 6 * f, 3, 17, Blocks.torch, 4);
            for (int k113 : new int[]{13, 17}) {
                this.setBlockAndMetadata(world, 4 * f, 1, k113, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, 5 * f, 1, k113, this.plankSlabBlock, this.plankSlabMeta | 8);
                this.setBlockAndMetadata(world, 7 * f, 1, k113, this.plankSlabBlock, this.plankSlabMeta | 8);
                this.placeChest(world, random, 6 * f, 1, k113, 0, LOTRChestContents.DOL_AMROTH_STABLES);
            }
        }
        this.setBlockAndMetadata(world, -8, 1, 13, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -8, 1, 17, Blocks.furnace, 2);
        this.setBlockAndMetadata(world, 8, 1, 13, LOTRMod.gondorianTable, 0);
        this.setBlockAndMetadata(world, 8, 1, 17, LOTRMod.dolAmrothTable, 0);
        for (int i132 : new int[]{-9, 9}) {
            for (int k114 = 14; k114 <= 16; ++k114) {
                this.setBlockAndMetadata(world, i132, 1, k114, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setAir(world, i132, 2, k114);
            }
            this.setBlockAndMetadata(world, i132, 3, 14, this.stairBlock, 7);
            this.setBlockAndMetadata(world, i132, 3, 15, this.slabBlock, this.slabMeta | 8);
            this.setBlockAndMetadata(world, i132, 3, 16, this.stairBlock, 6);
        }
        int[] j18 = new int[]{-8, 7};
        i16 = j18.length;
        for (k13 = 0; k13 < i16; ++k13) {
            for (int i25 = i132 = j18[k13]; i25 <= i132 + 1; ++i25) {
                this.setBlockAndMetadata(world, i25, 1, 18, this.doubleRockSlabBlock, this.rockSlabMeta);
                this.setAir(world, i25, 2, 18);
            }
            this.setBlockAndMetadata(world, i132, 3, 18, this.stairBlock, 4);
            this.setBlockAndMetadata(world, i132 + 1, 3, 18, this.stairBlock, 5);
        }
        for (i17 = -8; i17 <= 8; ++i17) {
            this.setBlockAndMetadata(world, i17, 1, 15, Blocks.carpet, 11);
        }
        for (i17 = -2; i17 <= 2; ++i17) {
            this.setBlockAndMetadata(world, i17, 1, 14, Blocks.carpet, 11);
            this.setBlockAndMetadata(world, i17, 1, 16, Blocks.carpet, 11);
        }
        this.generateWindow(world, -4, 3, 18);
        this.generateWindow(world, 4, 3, 18);
        for (int k162 : new int[]{14, 16}) {
            this.setBlockAndMetadata(world, -1, 9, k162, Blocks.stained_hardened_clay, 3);
            this.setBlockAndMetadata(world, 0, 9, k162, Blocks.stained_hardened_clay, 11);
            this.setBlockAndMetadata(world, 1, 9, k162, Blocks.stained_hardened_clay, 3);
        }
        this.setBlockAndMetadata(world, -2, 9, 15, Blocks.stained_hardened_clay, 3);
        this.setBlockAndMetadata(world, -1, 9, 15, Blocks.stained_hardened_clay, 11);
        this.setBlockAndMetadata(world, 0, 9, 15, Blocks.stained_hardened_clay, 11);
        this.setBlockAndMetadata(world, 1, 9, 15, Blocks.stained_hardened_clay, 11);
        this.setBlockAndMetadata(world, 2, 9, 15, Blocks.stained_hardened_clay, 3);
        this.setBlockAndMetadata(world, 0, 8, 15, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 7, 15, LOTRMod.chandelier, 2);
        for (i1 = -1; i1 <= 1; ++i1) {
            this.setBlockAndMetadata(world, i1, 3, 18, this.doubleRockSlabBlock, this.rockSlabMeta);
            this.setBlockAndMetadata(world, i1, 7, 18, this.doubleRockSlabBlock, this.rockSlabMeta);
            for (j1 = 4; j1 <= 6; ++j1) {
                if (IntMath.mod((int)(i1 + j1), (int)2) == 0) {
                    this.setBlockAndMetadata(world, i1, j1, 18, LOTRMod.stainedGlassPane, 0);
                    continue;
                }
                this.setBlockAndMetadata(world, i1, j1, 18, LOTRMod.stainedGlassPane, 11);
            }
        }
        this.setBlockAndMetadata(world, 0, 8, 18, this.doubleRockSlabBlock, this.rockSlabMeta);
        for (i1 = -6; i1 <= 6; ++i1) {
            i22 = Math.abs(i1);
            this.placeGrassFoundation(world, i1, 19);
            if (i22 % 4 == 2) {
                this.setBlockAndMetadata(world, i1, 1, 19, this.stairBlock, 3);
                this.setGrassToDirt(world, i1, 0, 19);
            } else {
                this.setBlockAndMetadata(world, i1, 1, 19, this.leafBlock, this.leafMeta);
            }
            if (i22 >= 6) {
                this.setBlockAndMetadata(world, i1, 6, 19, this.slabBlock, this.slabMeta | 8);
                continue;
            }
            if (i22 >= 3) {
                this.setBlockAndMetadata(world, i1, 7, 19, this.slabBlock, this.slabMeta);
                continue;
            }
            if (i22 >= 2) {
                this.setBlockAndMetadata(world, i1, 7, 19, this.slabBlock, this.slabMeta | 8);
                continue;
            }
            this.setBlockAndMetadata(world, i1, 8, 19, this.slabBlock, this.slabMeta);
        }
        LOTREntityDolAmrothCaptain captain = new LOTREntityDolAmrothCaptain(world);
        captain.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(captain, world, 0, 1, 15, 8);
        return true;
    }

    private void placeWoodPillar(World world, int i, int k) {
        int j = 0;
        while ((!this.isOpaque(world, i, j, k) || this.getBlock(world, i, j, k) == this.brickBlock && this.getMeta(world, i, j, k) == this.brickMeta) && this.getY(j) >= 0) {
            this.setBlockAndMetadata(world, i, j, k, this.woodBeamBlock, this.woodBeamMeta);
            this.setGrassToDirt(world, i, j - 1, k);
            --j;
        }
        for (j = 1; j <= 4; ++j) {
            this.setBlockAndMetadata(world, i, j, k, this.woodBeamBlock, this.woodBeamMeta);
        }
    }

    private void generateWindow(World world, int i, int j, int k) {
        this.setBlockAndMetadata(world, i - 1, j, k, this.stairBlock, 0);
        this.setBlockAndMetadata(world, i, j, k, this.slabBlock, this.slabMeta);
        this.setBlockAndMetadata(world, i + 1, j, k, this.stairBlock, 1);
        for (int i1 = i - 1; i1 <= i + 1; ++i1) {
            this.setAir(world, i1, j + 1, k);
            this.setAir(world, i1, j + 2, k);
        }
        this.setBlockAndMetadata(world, i - 1, j + 3, k, this.stairBlock, 4);
        this.setBlockAndMetadata(world, i, j + 3, k, this.slabBlock, this.slabMeta | 8);
        this.setBlockAndMetadata(world, i + 1, j + 3, k, this.stairBlock, 5);
    }

    private void placeGrassFoundation(World world, int i, int k) {
        for (int j1 = 6; !(j1 < 0 && this.isOpaque(world, i, j1, k) || this.getY(j1) < 0); --j1) {
            if (j1 > 0) {
                this.setAir(world, i, j1, k);
                continue;
            }
            if (j1 == 0) {
                this.setBlockAndMetadata(world, i, j1, k, (Block)Blocks.grass, 0);
                this.setGrassToDirt(world, i, j1 - 1, k);
                continue;
            }
            this.setBlockAndMetadata(world, i, j1, k, Blocks.dirt, 0);
            this.setGrassToDirt(world, i, j1 - 1, k);
        }
    }
}

