/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityDaleArcher;
import lotr.common.entity.npc.LOTREntityDaleCaptain;
import lotr.common.entity.npc.LOTREntityDaleSoldier;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDaleStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenDaleFortress
extends LOTRWorldGenDaleStructure {
    public LOTRWorldGenDaleFortress(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int i2;
        int i12;
        int i132;
        int k1;
        int i14;
        Block block;
        int k12;
        int j1;
        int k2;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, 12);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            int maxEdgeHeight = 0;
            for (i132 = -12; i132 <= 12; ++i132) {
                for (int k13 = -12; k13 <= 12; ++k13) {
                    j1 = this.getTopBlock(world, i132, k13) - 1;
                    block = this.getBlock(world, i132, j1, k13);
                    if (block != Blocks.grass) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight > 8) {
                        return false;
                    }
                    if (Math.abs(i132) != 12 && Math.abs(k13) != 12 || j1 <= maxEdgeHeight) continue;
                    maxEdgeHeight = j1;
                }
            }
            this.originY = this.getY(maxEdgeHeight);
        }
        for (int i15 = -11; i15 <= 11; ++i15) {
            for (k12 = -11; k12 <= 11; ++k12) {
                int j13;
                i2 = Math.abs(i15);
                k2 = Math.abs(k12);
                this.layFoundation(world, i15, k12);
                if (i2 > 9 || k2 > 9) continue;
                for (j13 = 1; j13 <= 8; ++j13) {
                    this.setAir(world, i15, j13, k12);
                }
                if (i2 <= 8 && k2 <= 8 && (i2 == 8 && k2 >= 4 || k2 == 8 && i2 >= 4)) {
                    for (j13 = 1; j13 <= 3; ++j13) {
                        this.setBlockAndMetadata(world, i15, j13, k12, this.brickBlock, this.brickMeta);
                    }
                }
                if (i2 == 9 && k2 == 2 || k2 == 9 && i2 == 2) {
                    for (j13 = 1; j13 <= 5; ++j13) {
                        this.setBlockAndMetadata(world, i15, j13, k12, this.pillarBlock, this.pillarMeta);
                    }
                } else if (i2 == 9 && (k2 == 3 || k2 == 5 || k2 == 9) || k2 == 9 && (i2 == 3 || i2 == 5 || i2 == 9)) {
                    for (j13 = 1; j13 <= 5; ++j13) {
                        this.setBlockAndMetadata(world, i15, j13, k12, this.brickBlock, this.brickMeta);
                    }
                } else if (i2 == 9 || k2 == 9) {
                    for (j13 = 4; j13 <= 5; ++j13) {
                        this.setBlockAndMetadata(world, i15, j13, k12, this.brickBlock, this.brickMeta);
                    }
                }
                if (i2 == 9) {
                    this.setBlockAndMetadata(world, i15, 3, -8, this.brickStairBlock, 7);
                    this.setBlockAndMetadata(world, i15, 3, -6, this.brickStairBlock, 6);
                    this.setBlockAndMetadata(world, i15, 3, 6, this.brickStairBlock, 7);
                    this.setBlockAndMetadata(world, i15, 3, 8, this.brickStairBlock, 6);
                }
                if (k2 == 9) {
                    this.setBlockAndMetadata(world, -8, 3, k12, this.brickStairBlock, 4);
                    this.setBlockAndMetadata(world, -6, 3, k12, this.brickStairBlock, 5);
                    this.setBlockAndMetadata(world, 6, 3, k12, this.brickStairBlock, 4);
                    this.setBlockAndMetadata(world, 8, 3, k12, this.brickStairBlock, 5);
                }
                if (i2 == 9 && k2 <= 5 || k2 == 9 && i2 <= 5) {
                    this.setBlockAndMetadata(world, i15, 6, k12, this.brickWallBlock, this.brickWallMeta);
                }
                if (i2 == 6 && k2 <= 5 || k2 == 6 && i2 <= 5) {
                    this.setBlockAndMetadata(world, i15, 6, k12, this.brickWallBlock, this.brickWallMeta);
                    if (i2 == 6 && k2 <= 3 || k2 == 6 && i2 <= 3) {
                        this.setBlockAndMetadata(world, i15, 5, k12, this.brickSlabBlock, this.brickSlabMeta | 8);
                    } else {
                        this.setBlockAndMetadata(world, i15, 5, k12, this.brickBlock, this.brickMeta);
                    }
                    if (i15 == -5) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.brickStairBlock, 4);
                    } else if (i15 == 5) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.brickStairBlock, 5);
                    } else if (k12 == -5) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.brickStairBlock, 7);
                    } else if (k12 == 5) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.brickStairBlock, 6);
                    }
                }
                if (i2 <= 8 && k2 <= 8 && (i2 >= 7 || k2 >= 7)) {
                    if (i2 <= 2 || k2 <= 2) {
                        this.setBlockAndMetadata(world, i15, 5, k12, this.plankBlock, this.plankMeta);
                    } else if (i15 == -3) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.plankStairBlock, 4);
                        this.setBlockAndMetadata(world, i15, 5, k12, this.plankStairBlock, 1);
                    } else if (i15 == 3) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.plankStairBlock, 5);
                        this.setBlockAndMetadata(world, i15, 5, k12, this.plankStairBlock, 0);
                    } else if (k12 == -3) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.plankStairBlock, 7);
                        this.setBlockAndMetadata(world, i15, 5, k12, this.plankStairBlock, 2);
                    } else if (k12 == 3) {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.plankStairBlock, 6);
                        this.setBlockAndMetadata(world, i15, 5, k12, this.plankStairBlock, 3);
                    } else {
                        this.setBlockAndMetadata(world, i15, 4, k12, this.plankBlock, this.plankMeta);
                    }
                }
                if (i2 == 6 && k2 == 6) {
                    for (j13 = 1; j13 <= 5; ++j13) {
                        this.setBlockAndMetadata(world, i15, j13, k12, this.brickBlock, this.brickMeta);
                    }
                }
                if (!(i2 != 6 && i2 != 9 || k2 != 6 && k2 != 9)) {
                    for (j13 = 6; j13 <= 7; ++j13) {
                        this.setBlockAndMetadata(world, i15, j13, k12, this.brickBlock, this.brickMeta);
                    }
                }
                if ((i2 != 9 || k2 != 7 && k2 != 8) && (k2 != 9 || i2 != 7 && i2 != 8)) continue;
                for (j13 = 6; j13 <= 7; ++j13) {
                    this.setBlockAndMetadata(world, i15, j13, k12, this.barsBlock, 0);
                }
            }
        }
        for (int j14 = 1; j14 <= 3; ++j14) {
            for (i12 = -1; i12 <= 1; ++i12) {
                this.setBlockAndMetadata(world, i12, j14, -9, LOTRMod.gateWooden, 2);
                this.setBlockAndMetadata(world, i12, j14, 9, this.brickBlock, this.brickMeta);
            }
            for (k12 = -1; k12 <= 1; ++k12) {
                this.setBlockAndMetadata(world, -9, j14, k12, LOTRMod.gateWooden, 5);
                this.setBlockAndMetadata(world, 9, j14, k12, LOTRMod.gateWooden, 4);
            }
        }
        for (int i132 : new int[]{-9, 6}) {
            int[] j15 = new int[]{-9, 6};
            j1 = j15.length;
            for (block = (Block)false; block < j1; ++block) {
                int k22;
                int i22;
                int k14 = j15[block];
                for (i22 = i132; i22 <= i132 + 3; ++i22) {
                    this.setBlockAndMetadata(world, i22, 8, k14, this.brickBlock, this.brickMeta);
                    this.setBlockAndMetadata(world, i22, 8, k14 + 3, this.brickBlock, this.brickMeta);
                }
                for (k22 = k14 + 1; k22 <= k14 + 2; ++k22) {
                    this.setBlockAndMetadata(world, i132, 8, k22, this.brickBlock, this.brickMeta);
                    this.setBlockAndMetadata(world, i132 + 3, 8, k22, this.brickBlock, this.brickMeta);
                }
                for (i22 = i132; i22 <= i132 + 3; ++i22) {
                    for (int k23 = k14; k23 <= k14 + 3; ++k23) {
                        this.setBlockAndMetadata(world, i22, 9, k23, this.roofBlock, this.roofMeta);
                        this.setBlockAndMetadata(world, i22, 10, k23, this.roofSlabBlock, this.roofSlabMeta);
                    }
                }
                for (i22 = i132 - 1; i22 <= i132 + 4; ++i22) {
                    this.setBlockAndMetadata(world, i22, 9, k14 - 1, this.roofStairBlock, 2);
                    this.setBlockAndMetadata(world, i22, 9, k14 + 4, this.roofStairBlock, 3);
                }
                for (k22 = k14; k22 <= k14 + 3; ++k22) {
                    this.setBlockAndMetadata(world, i132 - 1, 9, k22, this.roofStairBlock, 1);
                    this.setBlockAndMetadata(world, i132 + 4, 9, k22, this.roofStairBlock, 0);
                }
                this.setBlockAndMetadata(world, i132, 8, k14 - 1, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, i132 + 3, 8, k14 - 1, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, i132 + 4, 8, k14, this.roofStairBlock, 4);
                this.setBlockAndMetadata(world, i132 + 4, 8, k14 + 3, this.roofStairBlock, 4);
                this.setBlockAndMetadata(world, i132 + 3, 8, k14 + 4, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, i132, 8, k14 + 4, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, i132 - 1, 8, k14 + 3, this.roofStairBlock, 5);
                this.setBlockAndMetadata(world, i132 - 1, 8, k14, this.roofStairBlock, 5);
            }
        }
        this.setBlockAndMetadata(world, -6, 8, -8, this.roofStairBlock, 7);
        this.setBlockAndMetadata(world, -6, 8, -7, this.roofStairBlock, 6);
        this.setBlockAndMetadata(world, -7, 8, -6, this.roofStairBlock, 5);
        this.setBlockAndMetadata(world, -8, 8, -6, this.roofStairBlock, 4);
        this.setBlockAndMetadata(world, 6, 8, -8, this.roofStairBlock, 7);
        this.setBlockAndMetadata(world, 6, 8, -7, this.roofStairBlock, 6);
        this.setBlockAndMetadata(world, 7, 8, -6, this.roofStairBlock, 4);
        this.setBlockAndMetadata(world, 8, 8, -6, this.roofStairBlock, 5);
        this.setBlockAndMetadata(world, -6, 8, 8, this.roofStairBlock, 6);
        this.setBlockAndMetadata(world, -6, 8, 7, this.roofStairBlock, 7);
        this.setBlockAndMetadata(world, -7, 8, 6, this.roofStairBlock, 5);
        this.setBlockAndMetadata(world, -8, 8, 6, this.roofStairBlock, 4);
        this.setBlockAndMetadata(world, 6, 8, 8, this.roofStairBlock, 6);
        this.setBlockAndMetadata(world, 6, 8, 7, this.roofStairBlock, 7);
        this.setBlockAndMetadata(world, 7, 8, 6, this.roofStairBlock, 4);
        this.setBlockAndMetadata(world, 8, 8, 6, this.roofStairBlock, 5);
        this.setBlockAndMetadata(world, -8, 8, -8, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 8, 8, -8, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -8, 8, 8, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 8, 8, 8, Blocks.torch, 1);
        int[] j14 = new int[]{-2, 2};
        k12 = j14.length;
        for (i2 = 0; i2 < k12; ++i2) {
            i132 = j14[i2];
            for (int j16 = 6; j16 <= 8; ++j16) {
                this.setBlockAndMetadata(world, i132, j16, -9, this.pillarBlock, this.pillarMeta);
            }
            this.setBlockAndMetadata(world, i132, 9, -9, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i132, 5, -10, Blocks.torch, 4);
            this.placeWallBanner(world, i132, 8, -9, LOTRItemBanner.BannerType.DALE, 2);
        }
        for (j12 = 1; j12 <= 4; ++j12) {
            this.setBlockAndMetadata(world, -7, j12, -7, Blocks.ladder, 4);
            this.setBlockAndMetadata(world, 7, j12, -7, Blocks.ladder, 5);
        }
        this.setBlockAndMetadata(world, 0, 4, -8, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 0, 4, 8, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -8, 4, 0, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 8, 4, 0, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -6, 3, -5, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -5, 3, -6, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 6, 3, -5, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 5, 3, -6, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -6, 3, 5, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -5, 3, 6, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 6, 3, 5, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 5, 3, 6, Blocks.torch, 1);
        for (k1 = -8; k1 <= -4; ++k1) {
            this.setBlockAndMetadata(world, -3, 0, k1, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, -2, 0, k1, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, 2, 0, k1, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, 3, 0, k1, (Block)Blocks.grass, 0);
        }
        for (k1 = 4; k1 <= 8; ++k1) {
            this.setBlockAndMetadata(world, -3, 0, k1, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, -2, 0, k1, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, 2, 0, k1, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, 3, 0, k1, (Block)Blocks.grass, 0);
        }
        for (i1 = -8; i1 <= -4; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, -3, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, i1, 0, -2, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, i1, 0, 2, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, i1, 0, 3, (Block)Blocks.grass, 0);
        }
        for (i1 = 4; i1 <= 8; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, -3, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, i1, 0, -2, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, i1, 0, 2, (Block)Blocks.grass, 0);
            this.setBlockAndMetadata(world, i1, 0, 3, (Block)Blocks.grass, 0);
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k12 = -1; k12 <= 1; ++k12) {
                i2 = Math.abs(i1);
                k2 = Math.abs(k12);
                this.setBlockAndMetadata(world, i1, 1, k12, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i1, 2, k12, this.brickBlock, this.brickMeta);
                if (i2 == 1 && k2 == 1) {
                    this.setBlockAndMetadata(world, i1, 3, k12, this.brickWallBlock, this.brickWallMeta);
                } else if (i2 == 1 || k2 == 1) {
                    this.setBlockAndMetadata(world, i1, 3, k12, this.brickBlock, this.brickMeta);
                    this.setBlockAndMetadata(world, i1, 4, k12, this.brickBlock, this.brickMeta);
                    this.setBlockAndMetadata(world, i1, 5, k12, this.brickWallBlock, this.brickWallMeta);
                }
                if (i1 != 0 || k12 != 0) continue;
                this.setBlockAndMetadata(world, i1, 3, k12, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i1, 4, k12, LOTRMod.hearth, 0);
                this.setBlockAndMetadata(world, i1, 5, k12, (Block)Blocks.fire, 0);
            }
        }
        this.setBlockAndMetadata(world, 0, 6, 0, this.roofBlock, this.roofMeta);
        this.setBlockAndMetadata(world, -1, 6, 0, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 1, 6, 0, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 0, 6, -1, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 0, 6, 1, this.roofStairBlock, 3);
        for (k1 = -7; k1 <= -4; ++k1) {
            for (i12 = -7; i12 <= -4; ++i12) {
                this.setBlockAndMetadata(world, i12, 0, k1, LOTRMod.dirtPath, 0);
            }
            for (i12 = 4; i12 <= 7; ++i12) {
                this.setBlockAndMetadata(world, i12, 0, k1, LOTRMod.dirtPath, 0);
            }
        }
        this.setBlockAndMetadata(world, -4, 1, -5, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, -5, 1, -4, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 5, 1, -4, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 4, 1, -5, this.brickWallBlock, this.brickWallMeta);
        for (k1 = 4; k1 <= 7; ++k1) {
            for (i12 = -7; i12 <= -4; ++i12) {
                this.setBlockAndMetadata(world, i12, 0, k1, this.plankBlock, this.plankMeta);
            }
            for (i12 = 4; i12 <= 7; ++i12) {
                this.setBlockAndMetadata(world, i12, 0, k1, this.plankBlock, this.plankMeta);
            }
        }
        for (k1 = 4; k1 <= 6; ++k1) {
            for (i12 = -6; i12 <= -4; ++i12) {
                this.setBlockAndMetadata(world, i12, 4, k1, Blocks.wool, 11);
            }
            for (i12 = 4; i12 <= 6; ++i12) {
                this.setBlockAndMetadata(world, i12, 4, k1, Blocks.wool, 11);
            }
        }
        for (j12 = 1; j12 <= 3; ++j12) {
            this.setBlockAndMetadata(world, -4, j12, 4, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 4, j12, 4, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -4, 0, -4, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, -3, 0, -3, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, 4, 0, -4, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, 3, 0, -3, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, -4, 0, 4, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, -3, 0, 3, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, 4, 0, 4, (Block)Blocks.grass, 0);
        this.setBlockAndMetadata(world, 3, 0, 3, (Block)Blocks.grass, 0);
        for (j12 = 1; j12 <= 3; ++j12) {
            this.setBlockAndMetadata(world, -7, j12, 7, this.plankBlock, this.plankMeta);
        }
        this.setBlockAndMetadata(world, -7, 3, 6, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, -6, 3, 7, this.plankStairBlock, 4);
        for (int j17 : new int[]{1, 2}) {
            this.setBlockAndMetadata(world, -7, j17, 5, LOTRMod.strawBed, 0);
            this.setBlockAndMetadata(world, -7, j17, 6, LOTRMod.strawBed, 8);
            this.setBlockAndMetadata(world, -5, j17, 7, LOTRMod.strawBed, 3);
            this.setBlockAndMetadata(world, -6, j17, 7, LOTRMod.strawBed, 11);
        }
        for (int j18 = 1; j18 <= 3; ++j18) {
            this.setBlockAndMetadata(world, 7, j18, 7, this.plankBlock, this.plankMeta);
        }
        this.setBlockAndMetadata(world, 7, 3, 6, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, 6, 3, 7, this.plankStairBlock, 5);
        for (i14 = 4; i14 <= 6; ++i14) {
            this.setBlockAndMetadata(world, i14, 1, 7, this.plankBlock, this.plankMeta);
            if (i14 > 5) continue;
            this.placeBarrel(world, random, i14, 2, 7, 2, LOTRFoods.DALE_DRINK);
        }
        for (int k15 = 4; k15 <= 6; ++k15) {
            this.setBlockAndMetadata(world, 7, 1, k15, this.plankBlock, this.plankMeta);
            if (k15 > 5) continue;
            this.placeBarrel(world, random, 7, 2, k15, 5, LOTRFoods.DALE_DRINK);
        }
        for (i14 = -3; i14 <= 3; ++i14) {
            this.setBlockAndMetadata(world, i14, 0, 8, this.floorBlock, this.floorMeta);
        }
        this.setBlockAndMetadata(world, -3, 1, 8, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -2, 1, 8, this.plankBlock, this.plankMeta);
        this.placeChest(world, random, -3, 2, 8, 2, LOTRChestContents.DALE_WATCHTOWER);
        this.placeChest(world, random, -2, 2, 8, 2, LOTRChestContents.DALE_WATCHTOWER);
        this.setBlockAndMetadata(world, 2, 1, 8, LOTRMod.daleTable, 0);
        this.setBlockAndMetadata(world, 3, 1, 8, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 0, 1, 7, LOTRMod.commandTable, 0);
        this.setBlockAndMetadata(world, 6, 1, 6, Blocks.furnace, 2);
        LOTREntityDaleCaptain captain = new LOTREntityDaleCaptain(world);
        captain.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(captain, world, 0, 1, 3, 16);
        int soldiers = 3 + random.nextInt(4);
        for (int l = 0; l < soldiers; ++l) {
            LOTREntityDaleSoldier soldier = random.nextInt(3) == 0 ? new LOTREntityDaleArcher(world) : new LOTREntityDaleSoldier(world);
            soldier.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(soldier, world, 0, 1, 3, 16);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityDaleSoldier.class, LOTREntityDaleArcher.class);
        respawner.setCheckRanges(16, -6, 10, 12);
        respawner.setSpawnRanges(10, -2, 2, 16);
        this.placeNPCRespawner(respawner, world, 0, 0, 0);
        return true;
    }

    private void layFoundation(World world, int i, int k) {
        for (int j = 0; j == 0 || !this.isOpaque(world, i, j, k) && this.getY(j) >= 0; --j) {
            this.setBlockAndMetadata(world, i, j, k, this.floorBlock, this.floorMeta);
            this.setGrassToDirt(world, i, j - 1, k);
        }
    }
}

