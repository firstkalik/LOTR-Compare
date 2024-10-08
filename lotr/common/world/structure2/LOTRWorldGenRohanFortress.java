/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityRohanBlacksmith;
import lotr.common.entity.npc.LOTREntityRohirrimArcher;
import lotr.common.entity.npc.LOTREntityRohirrimMarshal;
import lotr.common.entity.npc.LOTREntityRohirrimWarrior;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenRohanStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRohanFortress
extends LOTRWorldGenRohanStructure {
    public LOTRWorldGenRohanFortress(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.gateBlock = LOTRMod.gateRohan;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int k13;
        int j12;
        int j13;
        int i2;
        int i12;
        int k2;
        int k1;
        int k12;
        int i14;
        int i13;
        int j1;
        int i15;
        this.setOriginAndRotation(world, i, j, k, rotation, 13);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i15 = -12; i15 <= 12; ++i15) {
                for (k12 = -12; k12 <= 12; ++k12) {
                    j12 = this.getTopBlock(world, i15, k12) - 1;
                    if (this.isSurface(world, i15, j12, k12)) continue;
                    return false;
                }
            }
        }
        for (i15 = -12; i15 <= 12; ++i15) {
            for (k12 = -12; k12 <= 12; ++k12) {
                int j14;
                i2 = Math.abs(i15);
                k2 = Math.abs(k12);
                for (j14 = 1; j14 <= 10; ++j14) {
                    this.setAir(world, i15, j14, k12);
                }
                for (j14 = 0; !(j14 < 0 && this.isOpaque(world, i15, j14, k12) || this.getY(j14) < 0); --j14) {
                    if (i2 == 12 && (k2 == 12 || k2 == 9 || k2 == 2) || k2 == 12 && (i2 == 12 || i2 == 9 || i2 == 2)) {
                        this.setBlockAndMetadata(world, i15, j14, k12, this.woodBeam2Block, this.woodBeam2Meta);
                    } else if (i2 > 9 || k2 > 9) {
                        this.setBlockAndMetadata(world, i15, j14, k12, this.plankBlock, this.plankMeta);
                    } else if (j14 == 0) {
                        int randomGround = random.nextInt(3);
                        if (randomGround == 0) {
                            this.setBlockAndMetadata(world, i15, j14, k12, (Block)Blocks.grass, 0);
                        } else if (randomGround == 1) {
                            this.setBlockAndMetadata(world, i15, j14, k12, Blocks.dirt, 1);
                        } else if (randomGround == 2) {
                            this.setBlockAndMetadata(world, i15, j14, k12, LOTRMod.dirtPath, 0);
                        }
                        if (random.nextInt(3) == 0) {
                            this.setBlockAndMetadata(world, i15, j14 + 1, k12, LOTRMod.thatchFloor, 0);
                        }
                    } else {
                        this.setBlockAndMetadata(world, i15, j14, k12, Blocks.dirt, 0);
                    }
                    this.setGrassToDirt(world, i15, j14 - 1, k12);
                }
            }
        }
        for (i15 = -12; i15 <= 12; ++i15) {
            for (k12 = -12; k12 <= 12; ++k12) {
                int j15;
                i2 = Math.abs(i15);
                k2 = Math.abs(k12);
                int yBoost = 0;
                if (k12 < 8 && i2 < 7) {
                    yBoost = 1;
                }
                if (!(i2 != 9 && i2 != 12 || k2 != 9 && k2 != 12)) {
                    for (j15 = 1; j15 <= 8; ++j15) {
                        this.setBlockAndMetadata(world, i15, j15, k12, this.woodBeam2Block, this.woodBeam2Meta);
                    }
                    continue;
                }
                if (!(i2 != 12 && k2 != 12 || k2 != 2 && i2 != 2)) {
                    for (j15 = 1; j15 <= 6 + yBoost; ++j15) {
                        this.setBlockAndMetadata(world, i15, j15, k12, this.woodBeam2Block, this.woodBeam2Meta);
                    }
                    continue;
                }
                if (i2 == 12 || k2 == 12) {
                    for (j15 = 1; j15 <= 5 + yBoost; ++j15) {
                        this.setBlockAndMetadata(world, i15, j15, k12, this.plankBlock, this.plankMeta);
                    }
                    if (i2 == 12 && k2 >= 10 && k2 <= 11 || k2 == 12 && i2 >= 10 && i2 <= 11) {
                        this.setBlockAndMetadata(world, i15, 5 + yBoost, k12, this.fenceBlock, this.fenceMeta);
                        continue;
                    }
                    if (IntMath.mod((int)(i2 + k2), (int)2) == 0) {
                        this.setBlockAndMetadata(world, i15, 6 + yBoost, k12, this.plankBlock, this.plankMeta);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i15, 6 + yBoost, k12, this.plankSlabBlock, this.plankSlabMeta);
                    continue;
                }
                if (i2 > 9 || k2 > 9) {
                    for (j15 = 1; j15 <= 4 + yBoost; ++j15) {
                        this.setBlockAndMetadata(world, i15, j15, k12, this.plankBlock, this.plankMeta);
                    }
                    continue;
                }
                if (i2 != 9 && k2 != 9) continue;
                this.setBlockAndMetadata(world, i15, 5 + yBoost, k12, this.fenceBlock, this.fenceMeta);
                if (i2 == 9 && IntMath.mod((int)k12, (int)3) == 0 || k2 == 9 && IntMath.mod((int)i15, (int)3) == 0) {
                    this.setBlockAndMetadata(world, i15, 6 + yBoost, k12, Blocks.torch, 5);
                }
                if (k12 == -9) {
                    this.setBlockAndMetadata(world, i15, 4 + yBoost, k12, this.plank2StairBlock, 7);
                    continue;
                }
                if (k12 == 9) {
                    this.setBlockAndMetadata(world, i15, 4 + yBoost, k12, this.plank2StairBlock, 6);
                    continue;
                }
                if (i15 == -9) {
                    this.setBlockAndMetadata(world, i15, 4 + yBoost, k12, this.plank2StairBlock, 4);
                    continue;
                }
                if (i15 != 9) continue;
                this.setBlockAndMetadata(world, i15, 4 + yBoost, k12, this.plank2StairBlock, 5);
            }
        }
        int[] i16 = new int[]{-12, 9};
        k12 = i16.length;
        for (i2 = 0; i2 < k12; ++i2) {
            i13 = i16[i2];
            for (int k14 : new int[]{-12, 9}) {
                int i22;
                this.setBlockAndMetadata(world, i13 + 1, 8, k14, this.plank2StairBlock, 4);
                this.setBlockAndMetadata(world, i13 + 2, 8, k14, this.plank2StairBlock, 5);
                this.setBlockAndMetadata(world, i13 + 1, 8, k14 + 3, this.plank2StairBlock, 4);
                this.setBlockAndMetadata(world, i13 + 2, 8, k14 + 3, this.plank2StairBlock, 5);
                this.setBlockAndMetadata(world, i13, 8, k14 + 1, this.plank2StairBlock, 7);
                this.setBlockAndMetadata(world, i13, 8, k14 + 2, this.plank2StairBlock, 6);
                this.setBlockAndMetadata(world, i13 + 3, 8, k14 + 1, this.plank2StairBlock, 7);
                this.setBlockAndMetadata(world, i13 + 3, 8, k14 + 2, this.plank2StairBlock, 6);
                for (i22 = i13; i22 <= i13 + 3; ++i22) {
                    this.setBlockAndMetadata(world, i22, 9, k14 - 1, this.roofSlabBlock, this.roofSlabMeta);
                    this.setBlockAndMetadata(world, i22, 9, k14 + 4, this.roofSlabBlock, this.roofSlabMeta);
                }
                for (int k22 = k14; k22 <= k14 + 3; ++k22) {
                    this.setBlockAndMetadata(world, i13 - 1, 9, k22, this.roofSlabBlock, this.roofSlabMeta);
                    this.setBlockAndMetadata(world, i13 + 4, 9, k22, this.roofSlabBlock, this.roofSlabMeta);
                }
                for (i22 = i13; i22 <= i13 + 3; ++i22) {
                    for (int k23 = k14; k23 <= k14 + 3; ++k23) {
                        if (i22 >= i13 + 1 && i22 <= i13 + 2 && k23 >= k14 + 1 && k23 <= k14 + 2) {
                            this.setBlockAndMetadata(world, i22, 9, k23, this.roofSlabBlock, this.roofSlabMeta | 8);
                            this.setBlockAndMetadata(world, i22, 10, k23, this.roofSlabBlock, this.roofSlabMeta);
                            continue;
                        }
                        this.setBlockAndMetadata(world, i22, 9, k23, this.roofBlock, this.roofMeta);
                    }
                }
            }
        }
        for (k1 = -12; k1 <= 12; ++k1) {
            int k24 = Math.abs(k1);
            if (k24 >= 10 && k24 <= 11 || k24 >= 3 && k24 <= 8) {
                this.setBlockAndMetadata(world, -12, 1, k1, this.plank2StairBlock, 1);
                for (j12 = 2; j12 <= 3; ++j12) {
                    this.setAir(world, -12, j12, k1);
                }
                this.setBlockAndMetadata(world, -12, 4, k1, this.plank2StairBlock, 5);
                this.setBlockAndMetadata(world, 12, 1, k1, this.plank2StairBlock, 0);
                for (j12 = 2; j12 <= 3; ++j12) {
                    this.setAir(world, 12, j12, k1);
                }
                this.setBlockAndMetadata(world, 12, 4, k1, this.plank2StairBlock, 4);
            }
            if (k24 != 12 || k1 <= 0) continue;
            for (i12 = -1; i12 <= 1; ++i12) {
                this.setBlockAndMetadata(world, i12, 1, k1, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i12, 4, k1, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i12, 5, k1, this.woodBeam2Block, this.woodBeam2Meta | 4);
                this.setBlockAndMetadata(world, i12, 6, k1, this.fenceBlock, this.fenceMeta);
                this.setBlockAndMetadata(world, i12, 5, k1 - 1 * Integer.signum(k1), this.plankSlabBlock, this.plankSlabMeta);
            }
            this.setBlockAndMetadata(world, -2, 7, k1, this.plankSlabBlock, this.plankSlabMeta);
            this.setBlockAndMetadata(world, 2, 7, k1, this.plankSlabBlock, this.plankSlabMeta);
            this.setBlockAndMetadata(world, -1, 2, k1, this.plank2StairBlock, 0);
            this.setAir(world, 0, 2, k1);
            this.setBlockAndMetadata(world, 1, 2, k1, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -1, 3, k1, this.plank2StairBlock, 4);
            this.setAir(world, 0, 3, k1);
            this.setBlockAndMetadata(world, 1, 3, k1, this.plank2StairBlock, 5);
        }
        for (i14 = -12; i14 <= 12; ++i14) {
            int i23 = Math.abs(i14);
            if (i23 >= 10 && i23 <= 11 || i23 >= 3 && i23 <= 8) {
                this.setBlockAndMetadata(world, i14, 1, -12, this.plank2StairBlock, 2);
                for (j12 = 2; j12 <= 3; ++j12) {
                    this.setAir(world, i14, j12, -12);
                }
                this.setBlockAndMetadata(world, i14, 4, -12, this.plank2StairBlock, 6);
                this.setBlockAndMetadata(world, i14, 1, 12, this.plank2StairBlock, 3);
                for (j12 = 2; j12 <= 3; ++j12) {
                    this.setAir(world, i14, j12, 12);
                }
                this.setBlockAndMetadata(world, i14, 4, 12, this.plank2StairBlock, 7);
            }
            if (i23 != 12) continue;
            for (k13 = -1; k13 <= 1; ++k13) {
                this.setBlockAndMetadata(world, i14, 1, k13, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i14, 4, k13, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i14, 5, k13, this.woodBeam2Block, this.woodBeam2Meta | 8);
                this.setBlockAndMetadata(world, i14, 6, k13, this.fenceBlock, this.fenceMeta);
                this.setBlockAndMetadata(world, i14 - 1 * Integer.signum(i14), 5, k13, this.plankSlabBlock, this.plankSlabMeta);
            }
            this.setBlockAndMetadata(world, i14, 7, -2, this.plankSlabBlock, this.plankSlabMeta);
            this.setBlockAndMetadata(world, i14, 7, 2, this.plankSlabBlock, this.plankSlabMeta);
            this.setBlockAndMetadata(world, i14, 2, -1, this.plank2StairBlock, 3);
            this.setAir(world, i14, 2, 0);
            this.setBlockAndMetadata(world, i14, 2, 1, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i14, 3, -1, this.plank2StairBlock, 7);
            this.setAir(world, i14, 3, 0);
            this.setBlockAndMetadata(world, i14, 3, 1, this.plank2StairBlock, 6);
        }
        for (k1 = -11; k1 <= -10; ++k1) {
            this.setAir(world, -6, 5, k1);
            this.setBlockAndMetadata(world, -5, 5, k1, this.plankStairBlock, 1);
            this.setBlockAndMetadata(world, 5, 5, k1, this.plankStairBlock, 0);
            this.setAir(world, 6, 5, k1);
        }
        for (j1 = 4; j1 <= 7; ++j1) {
            this.setBlockAndMetadata(world, -6, j1, -9, this.woodBeam2Block, this.woodBeam2Meta);
            this.setBlockAndMetadata(world, 6, j1, -9, this.woodBeam2Block, this.woodBeam2Meta);
        }
        this.setBlockAndMetadata(world, -6, 8, -9, Blocks.torch, 5);
        this.setBlockAndMetadata(world, 6, 8, -9, Blocks.torch, 5);
        for (k1 = -12; k1 <= -10; ++k1) {
            for (j13 = 1; j13 <= 4; ++j13) {
                for (i12 = -1; i12 <= 1; ++i12) {
                    this.setAir(world, i12, j13, k1);
                }
                this.setBlockAndMetadata(world, -2, j13, k1, this.woodBeam2Block, this.woodBeam2Meta);
                this.setBlockAndMetadata(world, 2, j13, k1, this.woodBeam2Block, this.woodBeam2Meta);
            }
            for (i1 = -1; i1 <= 1; ++i1) {
                this.setBlockAndMetadata(world, i1, 4, k1, this.woodBeam2Block, this.woodBeam2Meta | 4);
            }
        }
        for (j1 = 1; j1 <= 3; ++j1) {
            for (i1 = -1; i1 <= 1; ++i1) {
                this.setBlockAndMetadata(world, i1, j1, -11, this.gateBlock, 3);
            }
        }
        for (i14 = -1; i14 <= 1; ++i14) {
            this.setBlockAndMetadata(world, i14, 4, -12, this.plank2StairBlock, 6);
            this.setBlockAndMetadata(world, i14, 5, -12, this.woodBeam2Block, this.woodBeam2Meta | 4);
            this.setBlockAndMetadata(world, i14, 6, -12, this.woodBeam2Block, this.woodBeam2Meta | 4);
            this.setBlockAndMetadata(world, i14, 6, -11, this.plankSlabBlock, this.plankSlabMeta);
        }
        for (j1 = 6; j1 <= 8; ++j1) {
            this.setBlockAndMetadata(world, 0, j1, -12, this.woodBeam2Block, this.woodBeam2Meta);
        }
        this.setBlockAndMetadata(world, -2, 8, -12, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 0, 9, -12, this.plankSlabBlock, this.plankSlabMeta);
        this.setBlockAndMetadata(world, 2, 8, -12, this.plankStairBlock, 0);
        this.setBlockAndMetadata(world, -1, 7, -12, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 1, 7, -12, this.fenceBlock, this.fenceMeta);
        this.placeWallBanner(world, -2, 6, -12, this.bannerType, 2);
        this.placeWallBanner(world, 0, 7, -12, this.bannerType, 2);
        this.placeWallBanner(world, 2, 6, -12, this.bannerType, 2);
        this.setBlockAndMetadata(world, -2, 3, -13, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 3, -13, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -2, 3, -9, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 2, 3, -9, Blocks.torch, 3);
        for (k1 = -13; k1 <= 9; ++k1) {
            for (i1 = -1; i1 <= 1; ++i1) {
                for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i1, j12, k1) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i1, j12, k1, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i1, j12 - 1, k1);
                }
            }
            if (k1 <= -10) continue;
            this.setBlockAndMetadata(world, -2, 0, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
            this.setBlockAndMetadata(world, 2, 0, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
            if (IntMath.mod((int)k1, (int)4) != 2) continue;
            this.setBlockAndMetadata(world, -2, 1, k1, this.brickWallBlock, this.brickWallMeta);
            this.setBlockAndMetadata(world, -2, 2, k1, Blocks.torch, 5);
            this.setBlockAndMetadata(world, 2, 1, k1, this.brickWallBlock, this.brickWallMeta);
            this.setBlockAndMetadata(world, 2, 2, k1, Blocks.torch, 5);
        }
        for (j1 = 1; j1 <= 3; ++j1) {
            this.setBlockAndMetadata(world, -2, j1, 10, this.woodBeam2Block, this.woodBeam2Meta);
            this.setBlockAndMetadata(world, 2, j1, 10, this.woodBeam2Block, this.woodBeam2Meta);
        }
        this.setBlockAndMetadata(world, -2, 3, 9, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 3, 9, Blocks.torch, 4);
        int[] j16 = new int[]{-7, 7};
        i1 = j16.length;
        for (j12 = 0; j12 < i1; ++j12) {
            i13 = j16[j12];
            this.setBlockAndMetadata(world, i13, 1, 0, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i13, 2, 0, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i13, 3, 0, Blocks.torch, 5);
            for (int l = 0; l < 2; ++l) {
                LOTREntityHorse horse = new LOTREntityHorse(world);
                this.spawnNPCAndSetHome((EntityCreature)horse, world, i13 - Integer.signum(i13) * 3, 1, 0, 0);
                horse.setHorseType(0);
                horse.saddleMountForWorldGen();
                horse.detachHome();
                this.leashEntityTo((EntityCreature)horse, world, i13, 2, 0);
            }
        }
        for (int k15 = -9; k15 <= -5; ++k15) {
            for (i1 = -9; i1 <= -5; ++i1) {
                this.setBlockAndMetadata(world, i1, 3, k15, this.plank2SlabBlock, this.plank2SlabMeta);
            }
        }
        this.setBlockAndMetadata(world, -9, 3, -9, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -6, 3, -9, this.plank2Block, this.plank2Meta);
        for (int j17 = 1; j17 <= 2; ++j17) {
            if (j17 == 1) {
                this.setBlockAndMetadata(world, -7, j17, -9, Blocks.furnace, 3);
                this.setBlockAndMetadata(world, -9, j17, -7, Blocks.furnace, 4);
            } else {
                this.setBlockAndMetadata(world, -7, j17, -9, LOTRMod.alloyForge, 3);
                this.setBlockAndMetadata(world, -9, j17, -7, LOTRMod.alloyForge, 4);
            }
            this.setBlockAndMetadata(world, -8, j17, -9, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -9, j17, -9, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -9, j17, -8, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -5, j17, -5, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -5, 1, -9, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -5, 2, -9, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -6, 1, -9, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -6, 2, -9, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -9, 1, -6, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -9, 2, -6, Blocks.torch, 2);
        this.setBlockAndMetadata(world, -9, 1, -5, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -9, 2, -5, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -6, 1, -5, Blocks.anvil, 1);
        this.setBlockAndMetadata(world, -5, 1, -6, (Block)Blocks.cauldron, 3);
        LOTREntityRohanBlacksmith blacksmith = new LOTREntityRohanBlacksmith(world);
        this.spawnNPCAndSetHome(blacksmith, world, -4, 1, -4, 8);
        for (k12 = 5; k12 <= 9; ++k12) {
            for (i12 = -9; i12 <= -5; ++i12) {
                this.setBlockAndMetadata(world, i12, 3, k12, this.plank2SlabBlock, this.plank2SlabMeta);
            }
        }
        this.setBlockAndMetadata(world, -9, 3, 9, this.plank2Block, this.plank2Meta);
        for (j13 = 1; j13 <= 2; ++j13) {
            this.setBlockAndMetadata(world, -9, j13, 9, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -5, j13, 5, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -5, 1, 9, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -5, 2, 9, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -6, 1, 9, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -6, 2, 9, Blocks.torch, 4);
        this.placeChest(world, random, -7, 1, 9, 2, LOTRChestContents.ROHAN_WATCHTOWER);
        this.placeChest(world, random, -8, 1, 9, 2, LOTRChestContents.ROHAN_WATCHTOWER);
        this.setBlockAndMetadata(world, -9, 1, 8, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -9, 1, 7, this.tableBlock, 0);
        this.setBlockAndMetadata(world, -9, 1, 6, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -9, 2, 6, Blocks.torch, 2);
        this.setBlockAndMetadata(world, -9, 1, 5, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -9, 2, 5, this.fenceBlock, this.fenceMeta);
        for (k12 = 5; k12 <= 10; ++k12) {
            for (i12 = 5; i12 <= 10; ++i12) {
                this.setBlockAndMetadata(world, i12, 0, k12, this.plank2Block, this.plank2Meta);
                this.setAir(world, i12, 1, k12);
                this.setAir(world, i12, 2, k12);
                this.setBlockAndMetadata(world, i12, 3, k12, this.plank2Block, this.plank2Meta);
            }
        }
        for (k12 = 4; k12 <= 9; ++k12) {
            this.setBlockAndMetadata(world, 4, 3, k12, this.plank2StairBlock, 1);
        }
        for (i1 = 5; i1 <= 9; ++i1) {
            this.setBlockAndMetadata(world, i1, 3, 4, this.plank2StairBlock, 2);
        }
        for (k12 = 5; k12 <= 10; ++k12) {
            this.setBlockAndMetadata(world, 5, 1, k12, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 5, 2, k12, this.plankBlock, this.plankMeta);
        }
        for (i1 = 6; i1 <= 10; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, 5, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i1, 2, 5, this.plankBlock, this.plankMeta);
        }
        this.setBlockAndMetadata(world, 5, 0, 8, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, 5, 1, 8, this.doorBlock, 2);
        this.setBlockAndMetadata(world, 5, 2, 8, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 5, 1, 5, this.woodBeam2Block, this.woodBeam2Meta);
        this.setBlockAndMetadata(world, 5, 2, 5, this.woodBeam2Block, this.woodBeam2Meta);
        for (i1 = 6; i1 <= 10; ++i1) {
            if (IntMath.mod((int)i1, (int)2) == 0) {
                this.setBlockAndMetadata(world, i1, 2, 6, Blocks.torch, 3);
                this.setBlockAndMetadata(world, i1, 2, 10, Blocks.torch, 4);
            }
            for (k13 = 6; k13 <= 10; ++k13) {
                if (!random.nextBoolean()) continue;
                this.setBlockAndMetadata(world, i1, 1, k13, LOTRMod.thatchFloor, 0);
            }
        }
        for (int k16 : new int[]{6, 10}) {
            this.setBlockAndMetadata(world, 7, 1, k16, this.bedBlock, 3);
            this.setBlockAndMetadata(world, 6, 1, k16, this.bedBlock, 11);
            this.setBlockAndMetadata(world, 9, 1, k16, this.bedBlock, 1);
            this.setBlockAndMetadata(world, 10, 1, k16, this.bedBlock, 9);
        }
        this.placeChest(world, random, 8, 1, 6, 3, this.chestContents);
        this.placeChest(world, random, 8, 1, 10, 2, this.chestContents);
        this.setBlockAndMetadata(world, 7, 1, 8, this.carpetBlock, this.carpetMeta);
        this.setBlockAndMetadata(world, 8, 1, 8, this.carpetBlock, this.carpetMeta);
        this.setBlockAndMetadata(world, 10, 1, 8, this.plankBlock, this.plankMeta);
        this.placeBarrel(world, random, 10, 2, 8, 5, LOTRFoods.ROHAN_DRINK);
        for (int j18 = 1; j18 <= 4; ++j18) {
            this.setBlockAndMetadata(world, 6, j18, -9, this.woodBeam2Block, this.woodBeam2Meta);
            this.setBlockAndMetadata(world, 7, j18, -9, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 8, j18, -9, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 9, j18, -9, this.woodBeam2Block, this.woodBeam2Meta);
        }
        for (k1 = -8; k1 <= -7; ++k1) {
            for (i12 = 6; i12 <= 9; ++i12) {
                int stairHeight = i12 - 5;
                for (int j19 = 0; j19 < stairHeight; ++j19) {
                    this.setBlockAndMetadata(world, i12, j19, k1, this.plankBlock, this.plankMeta);
                }
                this.setBlockAndMetadata(world, i12, stairHeight, k1, this.plankStairBlock, 1);
            }
            this.setAir(world, 9, 5, k1);
        }
        this.placeWallBanner(world, -10, 3, 0, this.bannerType, 1);
        this.placeWallBanner(world, 10, 3, 0, this.bannerType, 3);
        for (i1 = -1; i1 <= 1; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, 10, this.brickBlock, this.brickMeta);
            for (j12 = 1; j12 <= 3; ++j12) {
                this.setAir(world, i1, j12, 10);
            }
        }
        this.setBlockAndMetadata(world, 0, 1, 9, LOTRMod.commandTable, 0);
        this.placeWallBanner(world, 0, 3, 11, this.bannerType, 2);
        LOTREntityRohirrimMarshal marshal = new LOTREntityRohirrimMarshal(world);
        marshal.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(marshal, world, 0, 1, 0, 8);
        for (int l = 0; l < 8; ++l) {
            LOTREntityRohirrimWarrior rohirrim = world.rand.nextInt(3) == 0 ? new LOTREntityRohirrimArcher(world) : new LOTREntityRohirrimWarrior(world);
            rohirrim.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(rohirrim, world, 0, 1, 0, 20);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityRohirrimWarrior.class, LOTREntityRohirrimArcher.class);
        respawner.setCheckRanges(16, -8, 10, 12);
        respawner.setSpawnRanges(11, 1, 6, 20);
        this.placeNPCRespawner(respawner, world, 0, 0, 0);
        return true;
    }
}

