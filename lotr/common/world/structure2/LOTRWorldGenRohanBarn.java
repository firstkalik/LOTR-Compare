/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityRohanFarmer;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenRohanStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRohanBarn
extends LOTRWorldGenRohanStructure {
    public LOTRWorldGenRohanBarn(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int j1;
        int k18;
        int i12;
        int k12;
        int beam;
        int j12;
        int i13;
        int i14;
        int i2;
        int i22;
        int i152;
        int k2;
        this.setOriginAndRotation(world, i, j, k, rotation, 1);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i13 = -7; i13 <= 7; ++i13) {
                for (k18 = -1; k18 <= 16; ++k18) {
                    j1 = this.getTopBlock(world, i13, k18) - 1;
                    if (!this.isSurface(world, i13, j1, k18)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 6) continue;
                    return false;
                }
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (int k13 = 0; k13 <= 15; ++k13) {
                int i23 = Math.abs(i1);
                int k22 = IntMath.mod((int)k13, (int)3);
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k13) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k13, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k13);
                }
                for (j1 = 1; j1 <= 11; ++j1) {
                    this.setAir(world, i1, j1, k13);
                }
                beam = 0;
                if (i23 == 5 && k22 == 0) {
                    beam = 1;
                }
                if ((k13 == 0 || k13 == 15) && i23 == 2) {
                    beam = 1;
                }
                if (beam != 0) {
                    for (j12 = 1; j12 <= 5; ++j12) {
                        this.setBlockAndMetadata(world, i1, j12, k13, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    if (k13 == 0 || k13 == 15) {
                        for (j12 = 6; j12 <= 7; ++j12) {
                            this.setBlockAndMetadata(world, i1, j12, k13, this.woodBeamBlock, this.woodBeamMeta);
                        }
                    }
                } else if (i23 == 5 || k13 == 0 || k13 == 15) {
                    this.setBlockAndMetadata(world, i1, 1, k13, this.plank2Block, this.plank2Meta);
                    for (j12 = 2; j12 <= 5; ++j12) {
                        this.setBlockAndMetadata(world, i1, j12, k13, this.plankBlock, this.plankMeta);
                    }
                    if (k13 == 0 || k13 == 15) {
                        for (j12 = 6; j12 <= 7; ++j12) {
                            this.setBlockAndMetadata(world, i1, j12, k13, this.plankBlock, this.plankMeta);
                        }
                    }
                    this.setBlockAndMetadata(world, i1, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                    this.setBlockAndMetadata(world, i1, 8, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                }
                if (i23 > 4 || k13 < 1 || k13 > 14) continue;
                if (k13 >= 3 && k13 <= 12) {
                    this.setBlockAndMetadata(world, i1, 0, k13, Blocks.dirt, 1);
                }
                if (random.nextBoolean()) {
                    this.setBlockAndMetadata(world, i1, 1, k13, LOTRMod.thatchFloor, 0);
                }
                if (i23 < 2 && k13 > 3) continue;
                this.setBlockAndMetadata(world, i1, 5, k13, this.plankBlock, this.plankMeta);
                if (!random.nextBoolean()) continue;
                this.setBlockAndMetadata(world, i1, 6, k13, LOTRMod.thatchFloor, 0);
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            int j13;
            i22 = Math.abs(i1);
            if (i22 == 2 || i22 == 5) {
                for (int k14 = -1; k14 <= 16; ++k14) {
                    this.setBlockAndMetadata(world, i1, 5, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
                    this.setBlockAndMetadata(world, i1, 8, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
                    if (k14 != -1 && k14 != 16) continue;
                    this.setBlockAndMetadata(world, i1, 1, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
                    this.setGrassToDirt(world, i1, 0, k14);
                    for (j13 = 2; j13 <= 4; ++j13) {
                        this.setBlockAndMetadata(world, i1, j13, k14, this.fenceBlock, this.fenceMeta);
                    }
                    for (j13 = 6; j13 <= 7; ++j13) {
                        this.setBlockAndMetadata(world, i1, j13, k14, this.fenceBlock, this.fenceMeta);
                    }
                }
                continue;
            }
            for (int k15 : new int[]{0, 15}) {
                this.setBlockAndMetadata(world, i1, 3, k15, this.plank2SlabBlock, this.plank2SlabMeta);
                if (i1 == -4 || i1 == 3) {
                    this.setBlockAndMetadata(world, i1, 4, k15, this.plankStairBlock, 4);
                } else if (i1 == -3 || i1 == 4) {
                    this.setBlockAndMetadata(world, i1, 4, k15, this.plankStairBlock, 5);
                }
                if (i1 == -1) {
                    this.setBlockAndMetadata(world, i1, 4, k15, this.plankStairBlock, 4);
                } else if (i1 == 1) {
                    this.setBlockAndMetadata(world, i1, 4, k15, this.plankStairBlock, 5);
                } else if (i1 == 0) {
                    this.setBlockAndMetadata(world, i1, 4, k15, this.plankSlabBlock, this.plankSlabMeta | 8);
                }
                this.setBlockAndMetadata(world, i1, 7, k15, this.fenceBlock, this.fenceMeta);
            }
            int[] k14 = new int[]{-1, 16};
            j13 = k14.length;
            for (beam = 0; beam < j13; ++beam) {
                int k15;
                k15 = k14[beam];
                if (i22 >= 3 || k15 != -1) {
                    this.setBlockAndMetadata(world, i1, 1, k15, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                }
                this.setBlockAndMetadata(world, i1, 5, k15, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, i1, 8, k15, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            }
        }
        for (int k16 = 0; k16 <= 15; ++k16) {
            k2 = IntMath.mod((int)k16, (int)3);
            if (k2 == 0) {
                for (i13 = -7; i13 <= 7; ++i13) {
                    i2 = Math.abs(i13);
                    if (i2 == 6) {
                        this.setBlockAndMetadata(world, i13, 1, k16, this.woodBeamBlock, this.woodBeamMeta | 4);
                        this.setGrassToDirt(world, i13, 0, k16);
                        for (j1 = 2; j1 <= 4; ++j1) {
                            this.setBlockAndMetadata(world, i13, j1, k16, this.fenceBlock, this.fenceMeta);
                        }
                    }
                    if (i2 < 6) continue;
                    this.setBlockAndMetadata(world, i13, 5, k16, this.woodBeamBlock, this.woodBeamMeta | 4);
                }
                continue;
            }
            for (int i16 : new int[]{-6, 6}) {
                this.setBlockAndMetadata(world, i16, 1, k16, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            }
            this.setBlockAndMetadata(world, -7, 5, k16, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -6, 5, k16, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, 6, 5, k16, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 7, 5, k16, this.plank2StairBlock, 0);
            if (k16 < 3) continue;
            for (int i16 : new int[]{-5, 5}) {
                this.setBlockAndMetadata(world, i16, 3, k16, this.plank2SlabBlock, this.plank2SlabMeta);
                if (k2 == 1) {
                    this.setBlockAndMetadata(world, i16, 4, k16, this.plankStairBlock, 7);
                    continue;
                }
                if (k2 != 2) continue;
                this.setBlockAndMetadata(world, i16, 4, k16, this.plankStairBlock, 6);
            }
        }
        int[] k16 = new int[]{-1, 16};
        k2 = k16.length;
        for (i13 = 0; i13 < k2; ++i13) {
            k18 = k16[i13];
            this.setBlockAndMetadata(world, -7, 5, k18, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -6, 5, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 6, 5, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 7, 5, k18, this.plank2StairBlock, 0);
        }
        for (int i17 = -1; i17 <= 1; ++i17) {
            for (int j14 = 1; j14 <= 4; ++j14) {
                this.setBlockAndMetadata(world, i17, j14, 0, this.gateBlock, 2);
            }
        }
        this.setBlockAndMetadata(world, 0, 3, 0, LOTRMod.gateIronBars, 2);
        for (int k17 = 1; k17 <= 14; ++k17) {
            if (IntMath.mod((int)k17, (int)3) == 0) {
                this.setBlockAndMetadata(world, -6, 6, k17, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, -6, 7, k17, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, -6, 8, k17, this.plank2StairBlock, 1);
                this.setBlockAndMetadata(world, -5, 9, k17, this.plank2StairBlock, 1);
                this.setBlockAndMetadata(world, -4, 9, k17, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, -3, 10, k17, this.plank2SlabBlock, this.plank2SlabMeta);
                for (i12 = -2; i12 <= 2; ++i12) {
                    this.setBlockAndMetadata(world, i12, 10, k17, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                }
                this.setBlockAndMetadata(world, 3, 10, k17, this.plank2SlabBlock, this.plank2SlabMeta);
                this.setBlockAndMetadata(world, 4, 9, k17, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, 5, 9, k17, this.plank2StairBlock, 0);
                this.setBlockAndMetadata(world, 6, 8, k17, this.plank2StairBlock, 0);
                this.setBlockAndMetadata(world, 6, 6, k17, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, 6, 7, k17, this.plank2Block, this.plank2Meta);
                continue;
            }
            this.setBlockAndMetadata(world, -6, 6, k17, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, -6, 7, k17, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, -6, 8, k17, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, -5, 9, k17, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, -4, 9, k17, this.roofSlabBlock, this.roofSlabMeta | 8);
            this.setBlockAndMetadata(world, -3, 10, k17, this.roofSlabBlock, this.roofSlabMeta);
            for (i12 = -2; i12 <= 2; ++i12) {
                this.setBlockAndMetadata(world, i12, 10, k17, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
            this.setBlockAndMetadata(world, 3, 10, k17, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, 4, 9, k17, this.roofSlabBlock, this.roofSlabMeta | 8);
            this.setBlockAndMetadata(world, 5, 9, k17, this.roofStairBlock, 0);
            this.setBlockAndMetadata(world, 6, 8, k17, this.roofStairBlock, 0);
            this.setBlockAndMetadata(world, 6, 6, k17, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 6, 7, k17, this.roofBlock, this.roofMeta);
        }
        for (int k18 : new int[]{0, 15}) {
            this.setBlockAndMetadata(world, -6, 6, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -6, 7, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -6, 8, k18, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -5, 9, k18, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -4, 9, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -3, 9, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -3, 10, k18, this.plank2SlabBlock, this.plank2SlabMeta);
            this.setBlockAndMetadata(world, -2, 9, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -2, 10, k18, this.plank2Block, this.plank2Meta);
            for (i152 = -1; i152 <= 1; ++i152) {
                this.setBlockAndMetadata(world, i152, 10, k18, this.plank2Block, this.plank2Meta);
            }
            this.setBlockAndMetadata(world, 2, 9, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 2, 10, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 3, 9, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 3, 10, k18, this.plank2SlabBlock, this.plank2SlabMeta);
            this.setBlockAndMetadata(world, 4, 9, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 5, 9, k18, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 6, 8, k18, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 6, 6, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 6, 7, k18, this.plank2Block, this.plank2Meta);
        }
        int[] k17 = new int[]{-1, 16};
        i12 = k17.length;
        for (i13 = 0; i13 < i12; ++i13) {
            k18 = k17[i13];
            this.setBlockAndMetadata(world, -6, 8, k18, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -5, 9, k18, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -4, 9, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -3, 9, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -3, 10, k18, this.plank2SlabBlock, this.plank2SlabMeta);
            this.setBlockAndMetadata(world, -2, 10, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -1, 10, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -1, 11, k18, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 0, 11, k18, this.plank2SlabBlock, this.plank2SlabMeta);
            this.setBlockAndMetadata(world, 1, 10, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 1, 11, k18, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, 2, 10, k18, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 3, 9, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 3, 10, k18, this.plank2SlabBlock, this.plank2SlabMeta);
            this.setBlockAndMetadata(world, 4, 9, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 5, 9, k18, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 6, 8, k18, this.plank2StairBlock, 0);
        }
        for (k12 = 0; k12 <= 15; ++k12) {
            this.setBlockAndMetadata(world, 0, 11, k12, this.plank2SlabBlock, this.plank2SlabMeta);
        }
        this.setBlockAndMetadata(world, -4, 1, 1, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, -3, 1, 1, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 3, 1, 1, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 4, 1, 1, Blocks.hay_block, 0);
        for (int j15 = 1; j15 <= 7; ++j15) {
            if (j15 >= 6) {
                this.setBlockAndMetadata(world, -5, j15, 2, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, 5, j15, 2, this.plankBlock, this.plankMeta);
            }
            this.setBlockAndMetadata(world, -4, j15, 2, Blocks.ladder, 4);
            this.setBlockAndMetadata(world, 4, j15, 2, Blocks.ladder, 5);
        }
        for (k12 = 3; k12 <= 12; ++k12) {
            k2 = IntMath.mod((int)k12, (int)3);
            for (i13 = -4; i13 <= 4; ++i13) {
                i2 = Math.abs(i13);
                if (k2 == 0) {
                    if (i2 >= 2) {
                        this.setBlockAndMetadata(world, i13, 1, k12, this.fenceBlock, this.fenceMeta);
                        this.setBlockAndMetadata(world, i13, 2, k12, this.fenceBlock, this.fenceMeta);
                    }
                    if (i2 == 2) {
                        this.setBlockAndMetadata(world, i13, 3, k12, this.fenceBlock, this.fenceMeta);
                        this.setBlockAndMetadata(world, i13, 4, k12, this.fenceBlock, this.fenceMeta);
                    }
                }
                if (k2 == 1) {
                    if (i2 == 2) {
                        this.setBlockAndMetadata(world, i13, 1, k12, this.fenceBlock, this.fenceMeta);
                    }
                    if (i2 == 4) {
                        this.setBlockAndMetadata(world, i13, 1, k12, Blocks.hay_block, 0);
                        this.setBlockAndMetadata(world, i13, 2, k12, this.fenceBlock, this.fenceMeta);
                    }
                }
                if (k2 == 2) {
                    if (i2 == 2) {
                        this.setBlockAndMetadata(world, i13, 1, k12, this.fenceGateBlock, i13 > 0 ? 3 : 1);
                    }
                    if (i2 == 4) {
                        this.setBlockAndMetadata(world, i13, 1, k12, (Block)Blocks.cauldron, 3);
                        this.setBlockAndMetadata(world, i13, 2, k12, this.fenceBlock, this.fenceMeta);
                    }
                    if (i2 == 3) {
                        EntityAnimal animal = LOTRWorldGenRohanBarn.getRandomAnimal(world, random);
                        this.spawnNPCAndSetHome((EntityCreature)animal, world, i13, 1, k12, 0);
                        animal.detachHome();
                    }
                }
                if (i2 != 4) continue;
                this.setBlockAndMetadata(world, i13, 3, k12, this.plank2SlabBlock, this.plank2SlabMeta);
            }
        }
        for (i14 = -1; i14 <= 1; ++i14) {
            int hayHeight = 1 + random.nextInt(2);
            for (int j16 = 1; j16 <= hayHeight; ++j16) {
                this.setBlockAndMetadata(world, i14, j16, 14, Blocks.hay_block, 0);
            }
        }
        this.placeChest(world, random, -4, 1, 13, 4, LOTRChestContents.ROHAN_HOUSE);
        this.placeChest(world, random, -4, 1, 14, 4, LOTRChestContents.ROHAN_HOUSE);
        this.setBlockAndMetadata(world, 4, 1, 13, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 4, 1, 14, this.tableBlock, 0);
        this.setBlockAndMetadata(world, -2, 3, 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 2, 3, 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -2, 3, 14, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 3, 14, Blocks.torch, 4);
        for (k12 = 3; k12 <= 14; ++k12) {
            this.setBlockAndMetadata(world, -2, 6, k12, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 2, 6, k12, this.fenceBlock, this.fenceMeta);
        }
        for (i14 = -1; i14 <= 1; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, 3, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -2, 6, 1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 2, 6, 1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -2, 7, 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 2, 7, 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -2, 7, 14, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 7, 14, Blocks.torch, 4);
        for (int k18 : new int[]{1, 14}) {
            for (i152 = -4; i152 <= 4; ++i152) {
                int i24 = Math.abs(i152);
                if (i24 > 1 && i24 < 3) continue;
                this.setBlockAndMetadata(world, i152, 8, k18, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            }
        }
        for (int k19 = 1; k19 <= 14; ++k19) {
            if (k19 == 1 || IntMath.mod((int)k19, (int)3) == 0) {
                for (int i152 : new int[]{-5, 5}) {
                    this.setBlockAndMetadata(world, i152, 6, k19, this.fenceBlock, this.fenceMeta);
                    this.setBlockAndMetadata(world, i152, 7, k19, this.fenceBlock, this.fenceMeta);
                }
                continue;
            }
            if (k19 == 2) continue;
            for (int i152 : new int[]{-5, 5}) {
                j12 = 6;
                if (!random.nextBoolean()) continue;
                int j2 = j12;
                if (random.nextBoolean()) {
                    ++j2;
                }
                for (int j3 = j12; j3 <= j2; ++j3) {
                    this.setBlockAndMetadata(world, i152, j3, k19, Blocks.hay_block, 0);
                }
                if (j2 < j12 + 1 || !random.nextBoolean()) continue;
                int i25 = (Math.abs(i152) - 1) * Integer.signum(i152);
                j2 = j12;
                if (random.nextBoolean()) {
                    ++j2;
                }
                for (int j3 = j12; j3 <= j2; ++j3) {
                    this.setBlockAndMetadata(world, i25, j3, k19, Blocks.hay_block, 0);
                }
            }
        }
        for (int i18 = -4; i18 <= 4; ++i18) {
            i22 = Math.abs(i18);
            if (i22 == 2 || !random.nextBoolean()) continue;
            this.setBlockAndMetadata(world, i18, 6, 1, Blocks.hay_block, 0);
        }
        LOTREntityRohanFarmer farmer = new LOTREntityRohanFarmer(world);
        this.spawnNPCAndSetHome(farmer, world, 0, 1, 8, 16);
        return true;
    }

    public static EntityAnimal getRandomAnimal(World world, Random random) {
        int animal = random.nextInt(4);
        if (animal == 0) {
            return new EntityCow(world);
        }
        if (animal == 1) {
            return new EntityPig(world);
        }
        if (animal == 2) {
            return new EntitySheep(world);
        }
        if (animal == 3) {
            return new EntityChicken(world);
        }
        return null;
    }
}

