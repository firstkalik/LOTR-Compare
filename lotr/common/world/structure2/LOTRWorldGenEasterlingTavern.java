/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockFire
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityEasterling;
import lotr.common.entity.npc.LOTREntityEasterlingBartender;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.item.LOTRItemMug;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenEasterlingStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenEasterlingTavern
extends LOTRWorldGenEasterlingStructure {
    private String[] tavernName;
    private String[] tavernNameSign;
    private String tavernNameNPC;

    public LOTRWorldGenEasterlingTavern(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.bedBlock = LOTRMod.strawBed;
        this.tavernName = LOTRNames.getRhunTavernName(random);
        this.tavernNameSign = new String[]{"", this.tavernName[0], this.tavernName[1], ""};
        this.tavernNameNPC = this.tavernName[0] + " " + this.tavernName[1];
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int i12;
        int i13;
        int j1;
        int i2;
        int k1;
        int i142;
        int k12;
        int k13;
        int i15;
        int i22;
        int k14;
        int j12;
        int k15;
        int k2;
        int k22;
        int step;
        int j13;
        int j14;
        this.setOriginAndRotation(world, i, j, k, rotation, 11);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i13 = -9; i13 <= 9; ++i13) {
                for (k15 = -12; k15 <= 11; ++k15) {
                    j12 = this.getTopBlock(world, i13, k15) - 1;
                    if (!this.isSurface(world, i13, j12, k15)) {
                        return false;
                    }
                    if (j12 < minHeight) {
                        minHeight = j12;
                    }
                    if (j12 > maxHeight) {
                        maxHeight = j12;
                    }
                    if (maxHeight - minHeight <= 8) continue;
                    return false;
                }
            }
        }
        for (i12 = -8; i12 <= 8; ++i12) {
            for (k14 = -10; k14 <= 10; ++k14) {
                i2 = Math.abs(i12);
                k2 = Math.abs(k14);
                for (j12 = 1; j12 <= 12; ++j12) {
                    this.setAir(world, i12, j12, k14);
                }
                if (i2 == 8 && k2 % 4 == 2 || k2 == 10 && i2 % 4 == 0) {
                    for (j12 = 4; !(j12 < 0 && this.isOpaque(world, i12, j12, k14) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i12, j12, k14, this.woodBeamBlock, this.woodBeamMeta);
                        this.setGrassToDirt(world, i12, j12 - 1, k14);
                    }
                    continue;
                }
                if (i2 == 8 || k2 == 10) {
                    for (j12 = 3; !(j12 < 0 && this.isOpaque(world, i12, j12, k14) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i12, j12, k14, this.brickBlock, this.brickMeta);
                        this.setGrassToDirt(world, i12, j12 - 1, k14);
                    }
                    if (k2 == 10) {
                        this.setBlockAndMetadata(world, i12, 4, k14, this.woodBeamBlock, this.woodBeamMeta | 4);
                        continue;
                    }
                    if (i2 != 8) continue;
                    this.setBlockAndMetadata(world, i12, 4, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
                    continue;
                }
                for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i12, j12, k14) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i12, j12, k14, this.plankBlock, this.plankMeta);
                    this.setGrassToDirt(world, i12, j12 - 1, k14);
                }
                if (i2 % 4 != 2 || k2 % 4 != 0) continue;
                this.setBlockAndMetadata(world, i12, 0, k14, this.logBlock, this.logMeta);
            }
        }
        for (i12 = -7; i12 <= 7; ++i12) {
            for (k14 = -9; k14 <= 9; ++k14) {
                i2 = Math.abs(i12);
                k2 = Math.abs(k14);
                if (i2 <= 4 && k2 <= 9) {
                    this.setBlockAndMetadata(world, i12, 4, k14, this.plankSlabBlock, this.plankSlabMeta | 8);
                }
                if (i2 % 4 == 0 && k2 <= 9) {
                    this.setBlockAndMetadata(world, i12, 0, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
                    this.setBlockAndMetadata(world, i12, 4, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
                }
                if (k2 % 4 == 2 && i2 <= 7) {
                    this.setBlockAndMetadata(world, i12, 0, k14, this.woodBeamBlock, this.woodBeamMeta | 4);
                    if (k2 == 2) {
                        this.setBlockAndMetadata(world, i12, 4, k14, this.woodBeamBlock, this.woodBeamMeta | 4);
                    }
                }
                if (k2 != 2 || i2 % 4 != 0) continue;
                for (j12 = 1; j12 <= 3; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, k14, this.woodBeamBlock, this.woodBeamMeta);
                }
            }
        }
        for (i12 = -8; i12 <= 8; ++i12) {
            i22 = Math.abs(i12);
            if (i22 == 2) {
                this.setBlockAndMetadata(world, i12, 2, -10, LOTRMod.reedBars, 0);
                this.setBlockAndMetadata(world, i12, 3, -10, this.brickStairBlock, 6);
            }
            if (i22 == 6) {
                this.setBlockAndMetadata(world, i12 - 1, 2, -10, this.brickStairBlock, 4);
                this.setAir(world, i12, 2, -10);
                this.setBlockAndMetadata(world, i12 + 1, 2, -10, this.brickStairBlock, 5);
                this.setBlockAndMetadata(world, i12, 3, -10, this.brickStairBlock, 6);
                this.setBlockAndMetadata(world, i12 - 1, 2, 10, this.brickStairBlock, 4);
                this.setAir(world, i12, 2, 10);
                this.setBlockAndMetadata(world, i12 + 1, 2, 10, this.brickStairBlock, 5);
                this.setBlockAndMetadata(world, i12, 3, 10, this.brickStairBlock, 7);
            }
            if (i22 == 4) {
                this.setBlockAndMetadata(world, i12, 3, -11, Blocks.torch, 4);
                this.setBlockAndMetadata(world, i12, 3, 11, Blocks.torch, 3);
            }
            if (i22 == 0) {
                this.setBlockAndMetadata(world, i12, 3, 11, Blocks.torch, 3);
            }
            if (i22 != 8) continue;
            this.setBlockAndMetadata(world, i12, 3, -11, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i12, 3, 11, this.fenceBlock, this.fenceMeta);
        }
        for (int k16 = -10; k16 <= 10; ++k16) {
            k22 = Math.abs(k16);
            if (k22 % 4 == 0) {
                this.setBlockAndMetadata(world, -8, 2, k16 - 1, this.brickStairBlock, 7);
                this.setAir(world, -8, 2, k16);
                this.setBlockAndMetadata(world, -8, 2, k16 + 1, this.brickStairBlock, 6);
                this.setBlockAndMetadata(world, -8, 3, k16, this.brickStairBlock, 5);
                this.setBlockAndMetadata(world, 8, 2, k16 - 1, this.brickStairBlock, 7);
                this.setAir(world, 8, 2, k16);
                this.setBlockAndMetadata(world, 8, 2, k16 + 1, this.brickStairBlock, 6);
                this.setBlockAndMetadata(world, 8, 3, k16, this.brickStairBlock, 4);
            }
            if (k22 % 4 == 2) {
                this.setBlockAndMetadata(world, -9, 3, k16, Blocks.torch, 1);
                this.setBlockAndMetadata(world, 9, 3, k16, Blocks.torch, 2);
            }
            if (k22 != 10) continue;
            this.setBlockAndMetadata(world, -9, 3, k16, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 9, 3, k16, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, 0, 0, -10, this.woodBeamBlock, this.woodBeamMeta | 8);
        this.setBlockAndMetadata(world, 0, 1, -10, this.doorBlock, 1);
        this.setBlockAndMetadata(world, 0, 2, -10, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 0, 3, -10, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 4, -11, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 4, -12, this.plankBlock, this.plankMeta);
        this.placeSign(world, -1, 4, -12, Blocks.wall_sign, 5, this.tavernNameSign);
        this.placeSign(world, 1, 4, -12, Blocks.wall_sign, 4, this.tavernNameSign);
        this.placeSign(world, 0, 4, -13, Blocks.wall_sign, 2, this.tavernNameSign);
        for (i12 = -4; i12 <= 4; ++i12) {
            for (k14 = -9; k14 <= 9; ++k14) {
                i2 = Math.abs(i12);
                k2 = Math.abs(k14);
                if (i2 == 4 && k2 == 2 || k2 == 9 && i2 % 4 == 0) {
                    for (j12 = 5; j12 <= 8; ++j12) {
                        this.setBlockAndMetadata(world, i12, j12, k14, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    if (i2 != 0) continue;
                    for (j12 = 9; j12 <= 11; ++j12) {
                        this.setBlockAndMetadata(world, i12, j12, k14, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    continue;
                }
                if (i2 != 4 && k2 != 9) continue;
                for (j12 = 5; j12 <= 7; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, k14, this.brickBlock, this.brickMeta);
                }
                if (k2 == 9) {
                    this.setBlockAndMetadata(world, i12, 8, k14, this.woodBeamBlock, this.woodBeamMeta | 4);
                    continue;
                }
                if (i2 != 4) continue;
                this.setBlockAndMetadata(world, i12, 8, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
        }
        for (int i142 : new int[]{-2, 2}) {
            this.setBlockAndMetadata(world, i142, 6, -9, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, i142, 7, -9, this.brickStairBlock, 6);
            if (i142 < 0) continue;
            this.setBlockAndMetadata(world, i142, 6, 9, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, i142, 7, 9, this.brickStairBlock, 7);
        }
        for (int i142 : new int[]{-4, 4}) {
            this.setBlockAndMetadata(world, i142, 8, -10, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i142, 8, 10, this.fenceBlock, this.fenceMeta);
        }
        int[] i16 = new int[]{-9, 9};
        k14 = i16.length;
        for (i2 = 0; i2 < k14; ++i2) {
            k15 = i16[i2];
            this.setBlockAndMetadata(world, -5, 8, k15, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 5, 8, k15, this.fenceBlock, this.fenceMeta);
        }
        for (int step2 = 0; step2 <= 1; ++step2) {
            j1 = 5 + step2;
            for (k13 = -10 + step2; k13 <= 10 - step2; ++k13) {
                this.setBlockAndMetadata(world, -8 + step2, j1, k13, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, -7 + step2, j1, k13, this.roofStairBlock, 4);
            }
            for (i13 = -7 + step2; i13 <= -5; ++i13) {
                this.setBlockAndMetadata(world, i13, j1, -10 + step2, this.roofStairBlock, 2);
                this.setBlockAndMetadata(world, i13, j1, -9 + step2, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, i13, j1, 10 - step2, this.roofStairBlock, 3);
                this.setBlockAndMetadata(world, i13, j1, 9 - step2, this.roofStairBlock, 6);
            }
            for (k13 = -10 + step2; k13 <= 10 - step2; ++k13) {
                this.setBlockAndMetadata(world, 8 - step2, j1, k13, this.roofStairBlock, 0);
                this.setBlockAndMetadata(world, 7 - step2, j1, k13, this.roofStairBlock, 5);
            }
            for (i13 = 5; i13 <= 7 - step2; ++i13) {
                this.setBlockAndMetadata(world, i13, j1, -10 + step2, this.roofStairBlock, 2);
                this.setBlockAndMetadata(world, i13, j1, -9 + step2, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, i13, j1, 10 - step2, this.roofStairBlock, 3);
                this.setBlockAndMetadata(world, i13, j1, 9 - step2, this.roofStairBlock, 6);
            }
            if (step2 != 1) continue;
            for (k13 = -9 + step2; k13 <= 9 - step2; ++k13) {
                for (i142 = -7 + step2; i142 <= -5; ++i142) {
                    this.setBlockAndMetadata(world, i142, j1 + 1, k13, this.roofSlabBlock, this.roofSlabMeta);
                }
                for (i142 = 5; i142 <= 7 - step2; ++i142) {
                    this.setBlockAndMetadata(world, i142, j1 + 1, k13, this.roofSlabBlock, this.roofSlabMeta);
                }
            }
        }
        this.setBlockAndMetadata(world, -4, 5, -10, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -3, 5, -10, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -2, 5, -10, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -1, 5, -10, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 0, 5, -10, this.roofBlock, this.roofMeta);
        this.setBlockAndMetadata(world, 1, 5, -10, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 2, 5, -10, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 3, 5, -10, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 4, 5, -10, this.roofStairBlock, 1);
        for (i1 = -4; i1 <= 4; ++i1) {
            this.setBlockAndMetadata(world, i1, 5, 10, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, 0, 5, 10, this.roofBlock, this.roofMeta);
        for (i1 = -9; i1 <= 9; ++i1) {
            i22 = Math.abs(i1);
            if (i22 == 9 || i22 == 6 || i22 == 2) {
                this.setBlockAndMetadata(world, i1, 5, -11, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (i1 == -8 || i1 == 7) {
                this.setBlockAndMetadata(world, i1, 4, -11, this.roofStairBlock, 5);
                this.setBlockAndMetadata(world, i1 + 1, 4, -11, this.roofStairBlock, 4);
            }
            if (i22 == 4) {
                this.setBlockAndMetadata(world, i1 - 1, 4, -11, this.roofStairBlock, 5);
                this.setBlockAndMetadata(world, i1, 4, -11, this.roofStairBlock, 2);
                this.setBlockAndMetadata(world, i1 + 1, 4, -11, this.roofStairBlock, 4);
            }
            if (i22 <= 1) {
                this.setBlockAndMetadata(world, i1, 5, -11, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
            if (i22 == 9 || i22 == 6 || i22 == 2) {
                this.setBlockAndMetadata(world, i1, 5, 11, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (i1 == -8 || i1 == 7) {
                this.setBlockAndMetadata(world, i1, 4, 11, this.roofStairBlock, 5);
                this.setBlockAndMetadata(world, i1 + 1, 4, 11, this.roofStairBlock, 4);
            }
            if (i22 != 4 && i22 != 0) continue;
            this.setBlockAndMetadata(world, i1 - 1, 4, 11, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, i1, 4, 11, this.roofStairBlock, 3);
            this.setBlockAndMetadata(world, i1 + 1, 4, 11, this.roofStairBlock, 4);
        }
        for (k12 = -10; k12 <= 10; ++k12) {
            k22 = Math.abs(k12);
            if (k22 % 4 == 0) {
                this.setBlockAndMetadata(world, -9, 5, k12, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, 9, 5, k12, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (k12 == -10 || k12 == 9) {
                this.setBlockAndMetadata(world, -9, 4, k12, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, -9, 4, k12 + 1, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, 9, 4, k12, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, 9, 4, k12 + 1, this.roofStairBlock, 7);
            }
            if (k22 > 6 || k22 % 4 != 2) continue;
            this.setBlockAndMetadata(world, -9, 4, k12 - 1, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, -9, 4, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, -9, 4, k12 + 1, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, 9, 4, k12 - 1, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, 9, 4, k12, this.roofStairBlock, 0);
            this.setBlockAndMetadata(world, 9, 4, k12 + 1, this.roofStairBlock, 7);
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            i22 = Math.abs(i1);
            int[] k17 = new int[]{-10, 10};
            i142 = k17.length;
            for (j12 = 0; j12 < i142; ++j12) {
                int k18 = k17[j12];
                if (i22 == 2 || i22 == 5) {
                    this.setBlockAndMetadata(world, i1, 9, k18, this.roofSlabBlock, this.roofSlabMeta);
                }
                if (i22 == 0) {
                    this.setBlockAndMetadata(world, i1 - 1, 8, k18, this.roofStairBlock, 5);
                    this.setBlockAndMetadata(world, i1, 8, k18, this.roofBlock, this.roofMeta);
                    this.setBlockAndMetadata(world, i1 + 1, 8, k18, this.roofStairBlock, 4);
                    this.setBlockAndMetadata(world, i1, 6, k18, this.roofWallBlock, this.roofWallMeta);
                    this.setBlockAndMetadata(world, i1, 7, k18, this.roofWallBlock, this.roofWallMeta);
                }
                if (i1 != -4 && i1 != 3) continue;
                this.setBlockAndMetadata(world, i1, 8, k18, this.roofStairBlock, 5);
                this.setBlockAndMetadata(world, i1 + 1, 8, k18, this.roofStairBlock, 4);
            }
        }
        for (k12 = -9; k12 <= 9; ++k12) {
            k22 = Math.abs(k12);
            if (k22 == 0 || k22 == 4 || k22 == 7) {
                this.setBlockAndMetadata(world, -5, 9, k12, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, 5, 9, k12, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (k22 == 2) {
                this.setBlockAndMetadata(world, -5, 8, k12 - 1, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, -5, 8, k12, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, -5, 8, k12 + 1, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, 5, 8, k12 - 1, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, 5, 8, k12, this.roofStairBlock, 0);
                this.setBlockAndMetadata(world, 5, 8, k12 + 1, this.roofStairBlock, 7);
            }
            if (k12 != -9 && k12 != -6 && k12 != 5 && k12 != 8) continue;
            this.setBlockAndMetadata(world, -5, 8, k12, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, -5, 8, k12 + 1, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, 5, 8, k12, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, 5, 8, k12 + 1, this.roofStairBlock, 7);
        }
        for (k12 = -9; k12 <= 9; ++k12) {
            for (step = 0; step <= 3; ++step) {
                j14 = 9 + step;
                this.setBlockAndMetadata(world, -4 + step, j14, k12, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 4 - step, j14, k12, this.roofStairBlock, 0);
                if (step <= 0) continue;
                this.setBlockAndMetadata(world, -4 + step, j14 - 1, k12, this.roofStairBlock, 4);
                this.setBlockAndMetadata(world, 4 - step, j14 - 1, k12, this.roofStairBlock, 5);
            }
            this.setBlockAndMetadata(world, 0, 12, k12, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 0, 13, k12, this.roofSlabBlock, this.roofSlabMeta);
        }
        this.setBlockAndMetadata(world, 0, 12, -10, this.roofStairBlock, 6);
        this.setBlockAndMetadata(world, 0, 13, -10, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 0, 12, 10, this.roofStairBlock, 7);
        this.setBlockAndMetadata(world, 0, 13, 10, this.roofStairBlock, 2);
        int[] k19 = new int[]{-8, 8};
        step = k19.length;
        for (j14 = 0; j14 < step; ++j14) {
            k15 = k19[j14];
            for (int step3 = 0; step3 <= 2; ++step3) {
                int j15 = 9 + step3;
                for (int i17 = -3 + step3; i17 <= 3 - step3; ++i17) {
                    this.setBlockAndMetadata(world, i17, j15, k15, this.plankBlock, this.plankMeta);
                }
            }
        }
        for (i15 = -3; i15 <= 3; ++i15) {
            this.setBlockAndMetadata(world, i15, 8, -8, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i15, 8, 8, this.plankStairBlock, 6);
        }
        this.setBlockAndMetadata(world, -4, 3, -6, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, 0, 3, -6, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, 4, 3, -6, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, -6, 3, -2, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, 6, 3, -2, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, -6, 3, 2, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, 6, 3, 2, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, -4, 3, 6, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, 4, 3, 6, LOTRMod.chandelier, 0);
        this.placeTable(world, random, -5, -4, 1, -7, -6);
        for (i15 = -7; i15 <= -4; ++i15) {
            this.setBlockAndMetadata(world, i15, 1, -9, this.plankStairBlock, 3);
        }
        for (k1 = -8; k1 <= -6; ++k1) {
            this.setBlockAndMetadata(world, -7, 1, k1, this.plankStairBlock, 0);
        }
        this.placeTable(world, random, 4, 5, 1, -7, -6);
        for (i15 = 4; i15 <= 7; ++i15) {
            this.setBlockAndMetadata(world, i15, 1, -9, this.plankStairBlock, 3);
        }
        for (k1 = -8; k1 <= -6; ++k1) {
            this.setBlockAndMetadata(world, 7, 1, k1, this.plankStairBlock, 1);
        }
        this.placeTable(world, random, -7, -6, 1, 0, 0);
        for (i15 = -7; i15 <= -6; ++i15) {
            this.setBlockAndMetadata(world, i15, 1, -2, this.plankStairBlock, 3);
            this.setBlockAndMetadata(world, i15, 1, 2, this.plankStairBlock, 2);
        }
        this.placeTable(world, random, 4, 5, 1, -1, 1);
        for (k1 = -1; k1 <= 1; ++k1) {
            this.setBlockAndMetadata(world, 7, 1, k1, this.plankStairBlock, 1);
        }
        this.placeTable(world, random, -7, -6, 1, 8, 9);
        for (i15 = -7; i15 <= -6; ++i15) {
            this.setBlockAndMetadata(world, i15, 1, 6, this.plankStairBlock, 3);
        }
        for (k1 = 8; k1 <= 9; ++k1) {
            this.setBlockAndMetadata(world, -4, 1, k1, this.plankStairBlock, 1);
        }
        this.placeTable(world, random, 6, 7, 1, 8, 9);
        for (i15 = 6; i15 <= 7; ++i15) {
            this.setBlockAndMetadata(world, i15, 1, 6, this.plankStairBlock, 3);
        }
        for (k1 = 8; k1 <= 9; ++k1) {
            this.setBlockAndMetadata(world, 4, 1, k1, this.plankStairBlock, 0);
        }
        for (i15 = -3; i15 <= -1; ++i15) {
            this.setBlockAndMetadata(world, i15, 1, -2, this.brickStairBlock, 6);
            this.setBlockAndMetadata(world, i15, 3, -2, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 3, 2, this.fenceBlock, this.fenceMeta);
        }
        for (k1 = -1; k1 <= 1; ++k1) {
            this.setBlockAndMetadata(world, -4, 1, k1, this.brickStairBlock, 5);
            this.setBlockAndMetadata(world, -4, 3, k1, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 0, 1, k1, this.brickStairBlock, 4);
            this.setBlockAndMetadata(world, 0, 3, k1, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -3, 1, 2, Blocks.furnace, 2);
        this.setBlockAndMetadata(world, -2, 1, 2, this.fenceGateBlock, 0);
        this.setBlockAndMetadata(world, -1, 1, 2, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -4, 1, 1, (Block)Blocks.cauldron, 3);
        this.placeChest(world, random, -1, 0, -1, 3, LOTRChestContents.EASTERLING_HOUSE);
        this.placeBarrel(world, random, -3, 2, -2, 2, LOTRFoods.RHUN_DRINK);
        this.placeBarrel(world, random, 0, 2, -1, 4, LOTRFoods.RHUN_DRINK);
        for (i15 = -4; i15 <= 0; ++i15) {
            for (k14 = -2; k14 <= 2; ++k14) {
                if (!(i15 == -4 && k14 >= -1 && k14 <= 0 || k14 == -2 && i15 >= -2 && i15 <= -1) && (i15 != 0 || k14 < 0 || k14 > 1)) continue;
                if (random.nextBoolean()) {
                    this.placeMug(world, random, i15, 2, k14, random.nextInt(4), LOTRFoods.RHUN_DRINK);
                    continue;
                }
                this.placePlate(world, random, i15, 2, k14, this.plateBlock, LOTRFoods.RHUN);
            }
        }
        for (i15 = -3; i15 <= -1; ++i15) {
            for (k14 = 8; k14 <= 10; ++k14) {
                for (j14 = 0; j14 <= 4; ++j14) {
                    this.setBlockAndMetadata(world, i15, j14, k14, this.brickBlock, this.brickMeta);
                }
            }
            for (k14 = 8; k14 <= 9; ++k14) {
                for (j14 = 5; j14 <= 8; ++j14) {
                    this.setBlockAndMetadata(world, i15, j14, k14, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (j13 = 1; j13 <= 7; ++j13) {
            this.setAir(world, -2, j13, 9);
        }
        this.setBlockAndMetadata(world, -2, 0, 9, LOTRMod.hearth, 0);
        this.setBlockAndMetadata(world, -2, 1, 9, (Block)Blocks.fire, 0);
        this.setBlockAndMetadata(world, -2, 1, 8, this.barsBlock, 0);
        this.setBlockAndMetadata(world, -2, 2, 8, Blocks.furnace, 2);
        this.spawnItemFrame(world, -2, 3, 8, 2, this.getEasterlingFramedItem(random));
        this.setBlockAndMetadata(world, -2, 6, 8, this.barsBlock, 0);
        this.setBlockAndMetadata(world, -2, 7, 8, this.barsBlock, 0);
        this.setBlockAndMetadata(world, -3, 8, 8, this.brickStairBlock, 1);
        this.setBlockAndMetadata(world, -1, 8, 8, this.brickStairBlock, 0);
        for (j13 = 5; j13 <= 7; ++j13) {
            this.setBlockAndMetadata(world, -2, j13, 10, this.brickBlock, this.brickMeta);
        }
        this.setBlockAndMetadata(world, -2, 8, 10, this.brickStairBlock, 3);
        for (j13 = 9; j13 <= 13; ++j13) {
            this.setBlockAndMetadata(world, -2, j13, 9, this.brickBlock, this.brickMeta);
        }
        this.setBlockAndMetadata(world, -2, 14, 9, Blocks.flower_pot, 0);
        for (int step4 = 0; step4 <= 3; ++step4) {
            j1 = 1 + step4;
            k13 = 4 + step4;
            for (i142 = 2; i142 <= 3; ++i142) {
                this.setAir(world, i142, 4, k13);
                this.setBlockAndMetadata(world, i142, j1, k13, this.plankStairBlock, 2);
                this.setBlockAndMetadata(world, i142, j1, k13 + 1, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i142, j1, k13 + 2, this.plankStairBlock, 7);
            }
        }
        for (i15 = 1; i15 <= 3; ++i15) {
            this.setBlockAndMetadata(world, i15, 5, 3, this.fenceBlock, this.fenceMeta);
        }
        for (k1 = 4; k1 <= 6; ++k1) {
            this.setBlockAndMetadata(world, 1, 5, k1, this.fenceBlock, this.fenceMeta);
        }
        for (i15 = -3; i15 <= 3; ++i15) {
            for (j1 = 5; j1 <= 7; ++j1) {
                this.setBlockAndMetadata(world, i15, j1, -2, this.plankBlock, this.plankMeta);
            }
            this.setBlockAndMetadata(world, i15, 8, -2, this.woodBeamBlock, this.woodBeamMeta | 4);
        }
        for (k1 = -8; k1 <= 8; ++k1) {
            if (k1 <= -2) {
                for (j1 = 5; j1 <= 7; ++j1) {
                    this.setBlockAndMetadata(world, 0, j1, k1, this.plankBlock, this.plankMeta);
                }
            }
            this.setBlockAndMetadata(world, 0, 8, k1, this.woodBeamBlock, this.woodBeamMeta | 8);
        }
        for (j13 = 5; j13 <= 7; ++j13) {
            this.setBlockAndMetadata(world, 0, j13, -2, this.woodBeamBlock, this.woodBeamMeta);
        }
        this.placeTable(world, random, -3, -2, 5, 4, 5);
        for (i15 = -3; i15 <= -2; ++i15) {
            this.setBlockAndMetadata(world, i15, 5, 2, this.plankStairBlock, 3);
            this.setBlockAndMetadata(world, i15, 5, 7, this.plankStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -3, 7, 2, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 3, 7, 2, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 0, 7, 8, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -2, 5, -2, this.doorBlock, 3);
        this.setBlockAndMetadata(world, -2, 6, -2, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 2, 5, -2, this.doorBlock, 3);
        this.setBlockAndMetadata(world, 2, 6, -2, this.doorBlock, 8);
        this.setBlockAndMetadata(world, -3, 5, -3, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, -3, 5, -5, this.plankBlock, this.plankMeta);
        this.placePlateWithCertainty(world, random, -3, 6, -5, this.plateBlock, LOTRFoods.RHUN);
        this.setBlockAndMetadata(world, -3, 5, -6, (Block)Blocks.chest, 4);
        for (int i142 : new int[]{-3, -1}) {
            this.setBlockAndMetadata(world, i142, 5, -7, this.bedBlock, 2);
            this.setBlockAndMetadata(world, i142, 5, -8, this.bedBlock, 10);
        }
        this.spawnItemFrame(world, 0, 6, -5, 3, LOTRFoods.RHUN_DRINK.getRandomVessel(random).getEmptyVessel());
        this.setBlockAndMetadata(world, -3, 6, -3, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -1, 6, -3, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -3, 6, -8, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -1, 6, -8, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 3, 5, -3, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, 3, 5, -5, this.plankBlock, this.plankMeta);
        this.placePlateWithCertainty(world, random, 3, 6, -5, this.plateBlock, LOTRFoods.RHUN);
        this.setBlockAndMetadata(world, 3, 5, -6, (Block)Blocks.chest, 5);
        for (int i142 : new int[]{1, 3}) {
            this.setBlockAndMetadata(world, i142, 5, -7, this.bedBlock, 2);
            this.setBlockAndMetadata(world, i142, 5, -8, this.bedBlock, 10);
        }
        this.spawnItemFrame(world, 0, 6, -5, 1, LOTRFoods.RHUN_DRINK.getRandomVessel(random).getEmptyVessel());
        this.setBlockAndMetadata(world, 3, 6, -3, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 1, 6, -3, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 3, 6, -8, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 1, 6, -8, Blocks.torch, 3);
        LOTREntityEasterlingBartender bartender = new LOTREntityEasterlingBartender(world);
        bartender.setSpecificLocationName(this.tavernNameNPC);
        this.spawnNPCAndSetHome(bartender, world, -2, 1, 0, 2);
        int men = 6 + random.nextInt(5);
        for (int l = 0; l < men; ++l) {
            LOTREntityEasterling easterling = new LOTREntityEasterling(world);
            this.spawnNPCAndSetHome(easterling, world, 2, 1, 0, 16);
        }
        return true;
    }

    private void placeTable(World world, Random random, int i1, int i2, int j, int k1, int k2) {
        for (int i = i1; i <= i2; ++i) {
            for (int k = k1; k <= k2; ++k) {
                this.setBlockAndMetadata(world, i, j, k, this.plankBlock, this.plankMeta);
                if (random.nextInt(3) == 0) continue;
                if (random.nextBoolean()) {
                    this.placeMug(world, random, i, j + 1, k, random.nextInt(4), LOTRFoods.RHUN_DRINK);
                    continue;
                }
                this.placePlate(world, random, i, j + 1, k, this.plateBlock, LOTRFoods.RHUN);
            }
        }
    }
}

