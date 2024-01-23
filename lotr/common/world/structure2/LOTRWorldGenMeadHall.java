/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityRohanMan;
import lotr.common.entity.npc.LOTREntityRohanMeadhost;
import lotr.common.entity.npc.LOTREntityRohanShieldmaiden;
import lotr.common.entity.npc.LOTREntityRohirrimWarrior;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.item.LOTRItemBanner;
import lotr.common.item.LOTRItemMug;
import lotr.common.world.structure2.LOTRWorldGenRohanStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenMeadHall
extends LOTRWorldGenRohanStructure {
    private String[] meadHallName;
    private String[] meadNameSign;
    private String meadNameNPC;

    public LOTRWorldGenMeadHall(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.meadHallName = LOTRNames.getRohanMeadHallName(random);
        this.meadNameSign = new String[]{"", this.meadHallName[0], this.meadHallName[1], ""};
        this.meadNameNPC = this.meadHallName[0] + " " + this.meadHallName[1];
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int i12;
        int i13;
        int j1;
        int k1;
        int k12;
        int k13;
        int j12;
        int j13;
        int j14;
        int step;
        int i2;
        this.setOriginAndRotation(world, i, j, k, rotation, 1);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i12 = -8; i12 <= 8; ++i12) {
                for (int k14 = 0; k14 <= 28; ++k14) {
                    j12 = this.getTopBlock(world, i12, k14) - 1;
                    if (!this.isSurface(world, i12, j12, k14)) {
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
        for (i13 = -8; i13 <= 8; ++i13) {
            for (k13 = 0; k13 <= 28; ++k13) {
                boolean stairSide;
                for (j14 = 1; j14 <= 11; ++j14) {
                    this.setAir(world, i13, j14, k13);
                }
                boolean corner = Math.abs(i13) == 8 && (k13 == 0 || k13 == 28);
                boolean bl = stairSide = Math.abs(i13) == 3 && k13 == 0;
                if (corner || stairSide) {
                    for (j12 = 1; !(j12 < 1 && this.isOpaque(world, i13, j12, k13) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i13, j12, k13, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                        this.setGrassToDirt(world, i13, j12 - 1, k13);
                    }
                    if (!corner) continue;
                    this.setBlockAndMetadata(world, i13, 2, k13, this.rockSlabBlock, this.rockSlabMeta);
                    continue;
                }
                for (j12 = 1; !(j12 < 1 && this.isOpaque(world, i13, j12, k13) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i13, j12, k13, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i13, j12 - 1, k13);
                }
                if (Math.abs(i13) > 4 || k13 < 4 || k13 > 21 || random.nextInt(4) != 0) continue;
                this.setBlockAndMetadata(world, i13, 2, k13, LOTRMod.thatchFloor, 0);
            }
        }
        for (i13 = -7; i13 <= 7; ++i13) {
            i2 = Math.abs(i13);
            if (i2 <= 2) {
                this.setBlockAndMetadata(world, i13, 1, 0, this.brickStairBlock, 2);
            }
            if (i2 == 3) {
                this.setBlockAndMetadata(world, i13, 2, 0, this.rockWallBlock, this.rockWallMeta);
            }
            if (i2 < 4 || i2 > 7) continue;
            this.setBlockAndMetadata(world, i13, 2, 0, this.fenceBlock, this.fenceMeta);
        }
        for (i13 = -7; i13 <= 7; ++i13) {
            for (k13 = 2; k13 <= 26; ++k13) {
                int i22 = Math.abs(i13);
                if (i22 == 5 && (k13 == 2 || k13 == 26)) {
                    for (j13 = 2; j13 <= 4; ++j13) {
                        this.setBlockAndMetadata(world, i13, j13, k13, this.woodBeamRohanBlock, this.woodBeamRohanMeta);
                    }
                } else {
                    if (i22 == 5 && k13 >= 3 && k13 <= 25) {
                        this.setBlockAndMetadata(world, i13, 2, k13, this.brickBlock, this.brickMeta);
                        this.setBlockAndMetadata(world, i13, 3, k13, this.plank2Block, this.plank2Meta);
                        this.setBlockAndMetadata(world, i13, 4, k13, this.plankBlock, this.plankMeta);
                    }
                    if (i22 <= 4 && k13 == 3) {
                        for (j13 = 2; j13 <= 4; ++j13) {
                            this.setBlockAndMetadata(world, i13, j13, k13, this.plankBlock, this.plankMeta);
                        }
                        this.setBlockAndMetadata(world, i13, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                    }
                    if (i22 <= 4 && k13 == 25) {
                        this.setBlockAndMetadata(world, i13, 2, k13, this.brickBlock, this.brickMeta);
                        this.setBlockAndMetadata(world, i13, 3, k13, this.plank2Block, this.plank2Meta);
                        this.setBlockAndMetadata(world, i13, 4, k13, this.plankBlock, this.plankMeta);
                        this.setBlockAndMetadata(world, i13, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                    }
                }
                if (k13 < 3 || k13 > 25) continue;
                if (i22 == 6) {
                    if (k13 % 6 == 2) {
                        for (j13 = 2; j13 <= 4; ++j13) {
                            this.setBlockAndMetadata(world, i13, j13, k13, this.woodBeamBlock, this.woodBeamMeta);
                        }
                    } else {
                        if (k13 == 3 || k13 == 25) {
                            this.setBlockAndMetadata(world, i13, 2, k13, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                        } else {
                            this.setBlockAndMetadata(world, i13, 2, k13, this.rockSlabBlock, this.rockSlabMeta | 8);
                        }
                        if (k13 % 6 == 3 || k13 % 6 == 1) {
                            this.setBlockAndMetadata(world, i13, 4, k13, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                        }
                        if (random.nextInt(5) == 0) {
                            this.placeFlowerPot(world, i13, 3, k13, this.getRandomFlower(world, random));
                        }
                    }
                }
                if (i22 != 5 || k13 % 6 != 5) continue;
                this.setBlockAndMetadata(world, i13, 3, k13, this.plank2StairBlock, i13 > 0 ? 0 : 1);
                this.setBlockAndMetadata(world, i13, 4, k13, this.fenceBlock, this.fenceMeta);
            }
        }
        for (k1 = 3; k1 <= 25; ++k1) {
            for (step = 0; step <= 5; ++step) {
                i12 = 1 + step;
                j13 = 7 - step / 2;
                int[] block = this.roofSlabBlock;
                int meta = this.roofSlabMeta;
                if (k1 == 3 || k1 == 25) {
                    block = this.plankSlabBlock;
                    meta = this.plankSlabMeta;
                }
                if (step % 2 == 0) {
                    meta |= 8;
                }
                this.setBlockAndMetadata(world, -i12, j13, k1, (Block)block, meta);
                this.setBlockAndMetadata(world, i12, j13, k1, (Block)block, meta);
            }
        }
        for (k1 = 2; k1 <= 26; ++k1) {
            this.setBlockAndMetadata(world, 0, 7, k1, this.logBlock, this.logMeta | 8);
            this.setBlockAndMetadata(world, 0, 8, k1, this.plank2SlabBlock, this.plank2SlabMeta);
            if (k1 % 12 != 2) continue;
            for (step = 0; step <= 6; ++step) {
                i12 = 1 + step;
                j13 = 8 - (step + 1) / 2;
                for (int i23 : new int[]{-i12, i12}) {
                    if (step % 2 == 0) {
                        this.setBlockAndMetadata(world, i23, j13, k1, this.plank2SlabBlock, this.plank2SlabMeta);
                        this.setBlockAndMetadata(world, i23, j13 - 1, k1, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i23, j13, k1, this.plank2Block, this.plank2Meta);
                }
            }
            this.setBlockAndMetadata(world, 0, 8, k1, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 0, 9, k1, this.plank2SlabBlock, this.plank2SlabMeta);
            this.setBlockAndMetadata(world, -1, 9, k1, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 1, 9, k1, this.plank2StairBlock, 4);
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setBlockAndMetadata(world, -7, j1, k1, this.fence2Block, this.fence2Meta);
                this.setBlockAndMetadata(world, 7, j1, k1, this.fence2Block, this.fence2Meta);
            }
            if (k1 != 2 && k1 != 26) continue;
            this.setBlockAndMetadata(world, -6, 4, k1, this.fence2Block, this.fence2Meta);
            this.setBlockAndMetadata(world, 6, 4, k1, this.fence2Block, this.fence2Meta);
        }
        for (i13 = -5; i13 <= 5; ++i13) {
            i2 = Math.abs(i13);
            if (i2 == 5) {
                this.setBlockAndMetadata(world, i13, 5, 3, this.plankBlock, this.plankMeta);
            }
            if (i2 >= 2 && i2 <= 3) {
                this.setBlockAndMetadata(world, i13, 6, 3, this.plankBlock, this.plankMeta);
            }
            if (i2 == 1) {
                this.setBlockAndMetadata(world, i13, 6, 3, this.plankStairBlock, i13 > 0 ? 5 : 4);
            }
            if (i2 != 1) continue;
            this.setBlockAndMetadata(world, i13, 7, 3, this.plankBlock, this.plankMeta);
        }
        for (i13 = -5; i13 <= 5; ++i13) {
            i2 = Math.abs(i13);
            if (i2 == 5) {
                this.setBlockAndMetadata(world, i13, 5, 25, this.plankBlock, this.plankMeta);
            }
            if (i2 == 3) {
                this.setBlockAndMetadata(world, i13, 6, 25, this.plankBlock, this.plankMeta);
            }
            if (i2 == 2) {
                for (j14 = 2; j14 <= 6; ++j14) {
                    this.setBlockAndMetadata(world, i13, j14, 25, this.woodBeamRohanBlock, this.woodBeamRohanMeta);
                }
            }
            if (i2 <= 1) {
                for (j14 = 2; j14 <= 8; ++j14) {
                    if (j14 == 5) continue;
                    this.setBlockAndMetadata(world, i13, j14, 25, this.brickBlock, this.brickMeta);
                }
            }
            if (i2 == 0) {
                for (j14 = 9; j14 <= 11; ++j14) {
                    this.setBlockAndMetadata(world, i13, j14, 25, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i13, 12, 25, this.rockSlabBlock, this.rockSlabMeta);
            }
            if (i2 == 1) {
                this.setBlockAndMetadata(world, i13, 9, 25, this.brickStairBlock, i13 > 0 ? 0 : 1);
            }
            if (i2 <= 2) {
                for (int k15 = 23; k15 <= 24; ++k15) {
                    for (j13 = 2; j13 <= 6; ++j13) {
                        this.setBlockAndMetadata(world, i13, j13, k15, this.brickBlock, this.brickMeta);
                    }
                }
            }
            if (i2 <= 1) {
                this.setBlockAndMetadata(world, i13, 7, 24, this.brickBlock, this.brickMeta);
            }
            if (i2 == 1) {
                this.setBlockAndMetadata(world, i13, 8, 24, this.brickStairBlock, i13 > 0 ? 0 : 1);
            }
            if (i2 == 0) {
                this.setBlockAndMetadata(world, i13, 8, 24, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i13, 9, 24, this.brickStairBlock, 2);
            }
            if (i2 >= 3 && i2 <= 4) {
                for (j14 = 2; j14 <= 5; ++j14) {
                    this.setBlockAndMetadata(world, i13, j14, 24, this.brickBlock, this.brickMeta);
                }
            }
            if (i2 <= 1) {
                this.setBlockAndMetadata(world, i13, 2, 26, this.brickBlock, this.brickMeta);
            }
            if (i2 == 1) {
                this.setBlockAndMetadata(world, i13, 3, 26, this.brickStairBlock, i13 > 0 ? 0 : 1);
            }
            if (i2 != 0) continue;
            this.setBlockAndMetadata(world, i13, 3, 26, this.brickBlock, this.brickMeta);
        }
        for (int k14 : new int[]{2, 26}) {
            for (int i14 = -5; i14 <= 5; ++i14) {
                int i24 = Math.abs(i14);
                if (i24 == 2 || i24 == 5) {
                    this.setBlockAndMetadata(world, i14, 5, k14, this.woodBeamRohanBlock, this.woodBeamRohanMeta | 8);
                }
                if (i24 > 1) continue;
                this.setBlockAndMetadata(world, i14, 5, k14, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            }
        }
        for (int i15 : new int[]{-4, 3}) {
            this.setBlockAndMetadata(world, i15, 2, 2, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, i15 + 1, 2, 2, this.plank2StairBlock, 5);
            for (int i25 = i15; i25 <= i15 + 1; ++i25) {
                if (!random.nextBoolean()) continue;
                this.placeFlowerPot(world, i25, 3, 2, this.getRandomFlower(world, random));
            }
        }
        this.setBlockAndMetadata(world, 0, 2, 3, this.doorBlock, 1);
        this.setBlockAndMetadata(world, 0, 3, 3, this.doorBlock, 8);
        this.setBlockAndMetadata(world, -1, 3, 2, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 1, 3, 2, Blocks.torch, 4);
        this.placeSign(world, 0, 4, 2, Blocks.wall_sign, 2, this.meadNameSign);
        for (int i15 : new int[]{-2, 2}) {
            for (j12 = 2; j12 <= 4; ++j12) {
                this.setBlockAndMetadata(world, i15, j12, 3, this.woodBeamRohanGoldBlock, this.woodBeamRohanGoldMeta);
            }
        }
        for (int i15 : new int[]{-3, 3}) {
            this.setBlockAndMetadata(world, i15, 3, 3, this.plankStairBlock, 2);
            this.setBlockAndMetadata(world, i15, 4, 3, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, 0, 4, 4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -2, 5, 4, this.woodBeamBlock, this.woodBeamMeta | 8);
        this.setBlockAndMetadata(world, 2, 5, 4, this.woodBeamBlock, this.woodBeamMeta | 8);
        for (k12 = 4; k12 <= 24; ++k12) {
            this.setBlockAndMetadata(world, -5, 5, k12, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 5, 5, k12, this.roofBlock, this.roofMeta);
            if (k12 > 23) continue;
            if (k12 % 6 == 2) {
                for (j1 = 2; j1 <= 5; ++j1) {
                    this.setBlockAndMetadata(world, -4, j1, k12, this.woodBeamRohanBlock, this.woodBeamRohanMeta);
                    this.setBlockAndMetadata(world, 4, j1, k12, this.woodBeamRohanBlock, this.woodBeamRohanMeta);
                }
                this.setBlockAndMetadata(world, -3, 5, k12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, 3, 5, k12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, -2, 6, k12, this.plank2SlabBlock, this.plank2SlabMeta);
                this.setBlockAndMetadata(world, 2, 6, k12, this.plank2SlabBlock, this.plank2SlabMeta);
                this.setBlockAndMetadata(world, -1, 6, k12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, 1, 6, k12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                this.setBlockAndMetadata(world, 0, 6, k12, this.fenceBlock, this.fenceMeta);
                this.setBlockAndMetadata(world, 0, 5, k12, LOTRMod.chandelier, 1);
            } else {
                this.setBlockAndMetadata(world, -4, 5, k12, this.plankSlabBlock, this.plankSlabMeta);
            }
            if (k12 % 6 == 4 || k12 % 6 == 0) {
                this.setBlockAndMetadata(world, -4, 4, k12, Blocks.torch, 2);
                this.setBlockAndMetadata(world, 4, 4, k12, Blocks.torch, 1);
            }
            if (k12 % 6 != 1 && k12 % 6 != 3) continue;
            this.spawnItemFrame(world, -5, 3, k12, 1, this.getRohanFramedItem(random));
            this.spawnItemFrame(world, 5, 3, k12, 3, this.getRohanFramedItem(random));
        }
        for (k12 = 9; k12 <= 19; ++k12) {
            for (int i16 = -1; i16 <= 1; ++i16) {
                if (k12 % 5 == 4 && Math.abs(i16) == 1) {
                    this.setBlockAndMetadata(world, i16, 2, k12, this.plank2Block, this.plank2Meta);
                } else {
                    this.setBlockAndMetadata(world, i16, 2, k12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
                }
                if (i16 == 0 && random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        this.placeBarrel(world, random, i16, 3, k12, random.nextBoolean() ? 4 : 5, LOTRFoods.ROHAN_DRINK);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i16, 3, k12, this.getRandomCakeBlock(random), 0);
                    continue;
                }
                if (random.nextInt(3) == 0) {
                    this.placeMug(world, random, i16, 3, k12, random.nextInt(4), LOTRFoods.ROHAN_DRINK);
                    continue;
                }
                this.placePlate(world, random, i16, 3, k12, this.plateBlock, LOTRFoods.ROHAN);
            }
        }
        for (k12 = 8; k12 <= 20; ++k12) {
            if (k12 % 2 != 0) continue;
            this.setBlockAndMetadata(world, -3, 2, k12, this.plankStairBlock, 0);
            this.setBlockAndMetadata(world, 3, 2, k12, this.plankStairBlock, 1);
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k13 = 6; k13 <= 7; ++k13) {
                this.setBlockAndMetadata(world, i1, 2, k13, this.carpetBlock, this.carpetMeta);
            }
        }
        this.setBlockAndMetadata(world, -2, 2, 4, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -3, 2, 4, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, -4, 2, 4, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -4, 2, 5, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, -4, 2, 6, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, -2, 3, 4, 2, LOTRFoods.ROHAN_DRINK);
        this.placeBarrel(world, random, -3, 3, 4, 3, LOTRFoods.ROHAN_DRINK);
        this.placeBarrel(world, random, -4, 3, 5, 4, LOTRFoods.ROHAN_DRINK);
        this.placeMug(world, random, -4, 3, 6, 3, LOTRFoods.ROHAN_DRINK);
        this.setBlockAndMetadata(world, 2, 2, 4, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, 3, 2, 4, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, 4, 2, 4, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, 4, 2, 5, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, 4, 2, 6, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 2, 3, 4, 2, LOTRFoods.ROHAN_DRINK);
        this.placeBarrel(world, random, 3, 3, 4, 3, LOTRFoods.ROHAN_DRINK);
        this.placeBarrel(world, random, 4, 3, 5, 5, LOTRFoods.ROHAN_DRINK);
        this.placeMug(world, random, 4, 3, 6, 1, LOTRFoods.ROHAN_DRINK);
        for (i1 = -1; i1 <= 1; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, 24, LOTRMod.hearth, 0);
            this.setBlockAndMetadata(world, i1, 2, 24, (Block)Blocks.fire, 0);
            for (j1 = 3; j1 <= 4; ++j1) {
                this.setAir(world, i1, j1, 24);
            }
            for (j1 = 2; j1 <= 3; ++j1) {
                this.setBlockAndMetadata(world, i1, j1, 23, this.barsBlock, 0);
            }
        }
        this.setBlockAndMetadata(world, -3, 6, 24, this.roofBlock, this.roofMeta);
        this.setBlockAndMetadata(world, 3, 6, 24, this.roofBlock, this.roofMeta);
        for (i1 = -3; i1 <= 3; ++i1) {
            for (k13 = 22; k13 <= 23; ++k13) {
                this.setBlockAndMetadata(world, i1, 1, k13, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
            }
        }
        this.setBlockAndMetadata(world, -3, 4, 23, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 3, 4, 23, Blocks.torch, 4);
        this.placeWallBanner(world, -2, 5, 23, this.bannerType, 2);
        this.placeWallBanner(world, 2, 5, 23, this.bannerType, 2);
        this.setBlockAndMetadata(world, -1, 5, 23, this.brickCarvedBlock, this.brickCarvedMeta);
        this.setBlockAndMetadata(world, 1, 5, 23, this.brickCarvedBlock, this.brickCarvedMeta);
        this.placeWeaponRack(world, 0, 5, 22, 6, this.getRandomRohanWeapon(random));
        LOTREntityRohanMeadhost meadhost = new LOTREntityRohanMeadhost(world);
        this.spawnNPCAndSetHome(meadhost, world, 0, 2, 21, 8);
        int men = 5 + random.nextInt(5);
        for (int l = 0; l < men; ++l) {
            int i15;
            i15 = random.nextBoolean() ? -2 : 2;
            j12 = 2;
            int k16 = MathHelper.getRandomIntegerInRange((Random)random, (int)8, (int)20);
            LOTREntityRohanMan rohirrim = random.nextBoolean() ? new LOTREntityRohanMan(world) : new LOTREntityRohirrimWarrior(world);
            this.spawnNPCAndSetHome(rohirrim, world, i15, j12, k16, 16);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClass(LOTREntityRohanShieldmaiden.class);
        respawner.setCheckRanges(32, -12, 12, 2);
        respawner.setSpawnRanges(4, -2, 4, 16);
        respawner.setSpawnIntervalMinutes(5);
        respawner.setNoPlayerRange(8);
        this.placeNPCRespawner(respawner, world, 0, 0, 0);
        return true;
    }

    @Override
    protected ItemStack getRohanFramedItem(Random random) {
        if (random.nextBoolean()) {
            return LOTRFoods.ROHAN_DRINK.getRandomVessel(random).getEmptyVessel();
        }
        return super.getRohanFramedItem(random);
    }
}

