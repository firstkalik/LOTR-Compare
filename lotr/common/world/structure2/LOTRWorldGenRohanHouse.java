/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockFire
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityRohanMan;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenRohanStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenRohanHouse
extends LOTRWorldGenRohanStructure {
    private boolean hasStoneBase;
    private boolean setHasBase = false;

    public LOTRWorldGenRohanHouse(boolean flag) {
        super(flag);
    }

    public LOTRWorldGenRohanHouse setHasBase(boolean flag) {
        this.hasStoneBase = flag;
        this.setHasBase = true;
        return this;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int roofEdge;
        int i12;
        int i13;
        int k1;
        int j1;
        int step;
        int k12;
        int k132;
        int j12;
        int j13;
        int k14;
        if (!this.setHasBase) {
            this.hasStoneBase = random.nextBoolean();
        }
        this.setOriginAndRotation(world, i, j, k, rotation, this.hasStoneBase ? 10 : 6);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i14 = -6; i14 <= 5; ++i14) {
                for (k132 = -10; k132 <= 10; ++k132) {
                    int j14 = this.getTopBlock(world, i14, k132) - 1;
                    if (!this.isSurface(world, i14, j14, k132)) {
                        return false;
                    }
                    if (j14 < minHeight) {
                        minHeight = j14;
                    }
                    if (j14 > maxHeight) {
                        maxHeight = j14;
                    }
                    if (maxHeight - minHeight <= 5) continue;
                    return false;
                }
            }
        }
        if (this.hasStoneBase) {
            for (i12 = -6; i12 <= 5; ++i12) {
                for (k14 = -9; k14 <= 8; ++k14) {
                    int j15;
                    boolean stair;
                    boolean corner = (i12 == -6 || i12 == 5) && Math.abs(k14) == 8;
                    boolean stairSide = (i12 == -2 || i12 == 1) && k14 == -9;
                    boolean bl = stair = i12 >= -1 && i12 <= 0 && k14 == -9;
                    if (corner || stairSide) {
                        for (j15 = 1; !(j15 < 1 && this.isOpaque(world, i12, j15, k14) || this.getY(j15) < 0); --j15) {
                            this.setBlockAndMetadata(world, i12, j15, k14, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                            this.setGrassToDirt(world, i12, j15 - 1, k14);
                        }
                        if (!corner) continue;
                        this.setBlockAndMetadata(world, i12, 2, k14, this.rockSlabBlock, this.rockSlabMeta);
                        continue;
                    }
                    if (!stair && k14 < -8) continue;
                    for (j15 = 1; !(j15 < 1 && this.isOpaque(world, i12, j15, k14) || this.getY(j15) < 0); --j15) {
                        this.setBlockAndMetadata(world, i12, j15, k14, this.brickBlock, this.brickMeta);
                        this.setGrassToDirt(world, i12, j15 - 1, k14);
                    }
                    if (!stair) continue;
                    this.setBlockAndMetadata(world, i12, 1, k14, this.brickStairBlock, 2);
                }
            }
            ++this.originY;
        } else {
            for (i12 = -3; i12 <= 2; ++i12) {
                for (k14 = -5; k14 <= 4; ++k14) {
                    if (k14 < -4 && (i12 < -1 || i12 > 0)) continue;
                    for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i12, j12, k14) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i12, j12, k14, this.plank2Block, this.plank2Meta);
                        this.setGrassToDirt(world, i12, j12 - 1, k14);
                    }
                }
            }
        }
        for (i12 = -5; i12 <= 4; ++i12) {
            for (k14 = -7; k14 <= 7; ++k14) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i12, j12, k14);
                }
            }
        }
        for (i12 = -3; i12 <= 2; ++i12) {
            for (k14 = -4; k14 <= 4; ++k14) {
                if (!random.nextBoolean()) continue;
                this.setBlockAndMetadata(world, i12, 1, k14, LOTRMod.thatchFloor, 0);
            }
        }
        for (i12 = -4; i12 <= 3; ++i12) {
            for (k14 = -7; k14 <= 5; ++k14) {
                boolean beam = false;
                if (k14 == -7 && (i12 == -4 || i12 == -2 || i12 == 1 || i12 == 3)) {
                    beam = true;
                } else if (Math.abs(k14) == 5 && (i12 == -4 || i12 == 3)) {
                    beam = true;
                }
                if (beam) {
                    for (j1 = 3; !(j1 < 1 && this.isOpaque(world, i12, j1, k14) || this.getY(j1) < 0); --j1) {
                        this.setBlockAndMetadata(world, i12, j1, k14, this.woodBeamBlock, this.woodBeamMeta);
                        this.setGrassToDirt(world, i12, j1 - 1, k14);
                    }
                    continue;
                }
                if (k14 < -5) continue;
                if (i12 == -4 || i12 == 3) {
                    this.setBlockAndMetadata(world, i12, 1, k14, this.plank2Block, this.plank2Meta);
                    this.setGrassToDirt(world, i12, 0, k14);
                    for (j1 = 2; j1 <= 3; ++j1) {
                        this.setBlockAndMetadata(world, i12, j1, k14, this.plankBlock, this.plankMeta);
                    }
                    continue;
                }
                if (Math.abs(k14) != 5) continue;
                this.setBlockAndMetadata(world, i12, 1, k14, this.plank2Block, this.plank2Meta);
                this.setGrassToDirt(world, i12, 0, k14);
                for (j1 = 2; j1 <= 3; ++j1) {
                    this.setBlockAndMetadata(world, i12, j1, k14, this.plankBlock, this.plankMeta);
                }
                this.setBlockAndMetadata(world, i12, 4, k14, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        for (int k15 = -7; k15 <= 6; ++k15) {
            roofEdge = k15 == -7 || k15 == 6 ? 1 : 0;
            for (step = 0; step <= 4; ++step) {
                j1 = 3 + step;
                int[] stairBlock = this.roofStairBlock;
                if (step == 4 || roofEdge != 0) {
                    stairBlock = this.plank2StairBlock;
                }
                this.setBlockAndMetadata(world, -5 + step, j1, k15, (Block)stairBlock, 1);
                this.setBlockAndMetadata(world, 4 - step, j1, k15, (Block)stairBlock, 0);
                if (roofEdge != 0 && step <= 3) {
                    this.setBlockAndMetadata(world, -4 + step, j1, k15, (Block)stairBlock, 4);
                    this.setBlockAndMetadata(world, 3 - step, j1, k15, (Block)stairBlock, 5);
                }
                if (k15 < -4 || k15 > 4 || step < 1 || step > 3) continue;
                this.setBlockAndMetadata(world, -4 + step, j1, k15, (Block)stairBlock, 4);
                this.setBlockAndMetadata(world, 3 - step, j1, k15, (Block)stairBlock, 5);
            }
        }
        for (int k132 : new int[]{-6, -5, 5}) {
            this.setBlockAndMetadata(world, -2, 5, k132, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -1, 5, k132, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, 0, 5, k132, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, 1, 5, k132, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -1, 6, k132, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 0, 6, k132, this.plankBlock, this.plankMeta);
        }
        int[] k15 = new int[]{-7, 6};
        roofEdge = k15.length;
        for (step = 0; step < roofEdge; ++step) {
            k132 = k15[step];
            this.setBlockAndMetadata(world, -1, 8, k132, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 0, 8, k132, this.plank2StairBlock, 1);
        }
        for (i1 = -4; i1 <= 3; ++i1) {
            if (i1 == -4 || i1 == -2 || i1 == 1 || i1 == 3) {
                this.setBlockAndMetadata(world, i1, 3, -7, this.plank2Block, this.plank2Meta);
            } else {
                this.setBlockAndMetadata(world, i1, 3, -7, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            }
            if (i1 < -3 || i1 > 2) continue;
            this.setBlockAndMetadata(world, i1, 3, 6, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (i1 = -3; i1 <= 2; ++i1) {
            this.setBlockAndMetadata(world, i1, 4, -6, this.plankBlock, this.plankMeta);
        }
        this.setBlockAndMetadata(world, -4, 3, -6, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        this.setBlockAndMetadata(world, 3, 3, -6, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        this.setBlockAndMetadata(world, -1, 4, -6, this.rockSlabBlock, this.rockSlabMeta);
        this.setBlockAndMetadata(world, 0, 4, -6, this.rockSlabBlock, this.rockSlabMeta);
        this.setBlockAndMetadata(world, -2, 4, -7, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 1, 4, -7, this.fenceBlock, this.fenceMeta);
        for (i1 = -1; i1 <= 0; ++i1) {
            for (int j16 = 1; j16 <= 2; ++j16) {
                this.setAir(world, i1, j16, -5);
            }
        }
        this.setBlockAndMetadata(world, -1, 3, -5, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 0, 3, -5, this.plankStairBlock, 5);
        for (int i15 : new int[]{-5, 4}) {
            for (int k16 : new int[]{-7, 6}) {
                for (j13 = 2; !(j13 < 1 && this.isOpaque(world, i15, j13, k16) || this.getY(j13) < 0); --j13) {
                    this.setBlockAndMetadata(world, i15, j13, k16, this.fenceBlock, this.fenceMeta);
                }
            }
        }
        for (int i15 : new int[]{-4, 3}) {
            this.setAir(world, i15, 2, -2);
            this.setBlockAndMetadata(world, i15, 3, -2, this.plankSlabBlock, this.plankSlabMeta | 8);
            this.setBlockAndMetadata(world, i15, 4, -2, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i15, 2, -3, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 2, -1, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 3, -3, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i15, 3, -1, this.plankStairBlock, 6);
        }
        for (int i15 : new int[]{-5, 4}) {
            this.setBlockAndMetadata(world, i15, 1, -3, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i15, 1, -2, this.plankSlabBlock, this.plankSlabMeta | 8);
            this.setBlockAndMetadata(world, i15, 1, -1, this.plankStairBlock, 6);
            for (int k17 = -3; k17 <= -1; ++k17) {
                if (!random.nextBoolean()) continue;
                this.placeFlowerPot(world, i15, 2, k17, this.getRandomFlower(world, random));
            }
            this.setBlockAndMetadata(world, i15, 3, -4, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i15, 3, -3, this.roofSlabBlock, this.roofSlabMeta | 8);
            this.setBlockAndMetadata(world, i15, 4, -3, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i15, 4, -2, this.roofBlock, this.roofMeta);
            this.setAir(world, i15, 3, -2);
            this.setBlockAndMetadata(world, i15, 3, -1, this.roofSlabBlock, this.roofSlabMeta | 8);
            this.setBlockAndMetadata(world, i15, 4, -1, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i15, 3, 0, this.roofBlock, this.roofMeta);
            for (int k16 : new int[]{-4, 0}) {
                for (j13 = 2; !(j13 < 1 && this.isOpaque(world, i15, j13, k16) || this.getY(j13) < 0); --j13) {
                    this.setBlockAndMetadata(world, i15, j13, k16, this.fenceBlock, this.fenceMeta);
                }
            }
        }
        this.setBlockAndMetadata(world, -4, 2, 3, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -2, 2, 5, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 1, 2, 5, this.fenceBlock, this.fenceMeta);
        for (k12 = 1; k12 <= 3; ++k12) {
            for (i13 = 2; i13 <= 3; ++i13) {
                for (j12 = 5; !(j12 < 0 && this.isOpaque(world, i13, j12, k12) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i13, j12, k12, this.brickBlock, this.brickMeta);
                }
            }
        }
        this.setBlockAndMetadata(world, 3, 5, 1, this.brickStairBlock, 2);
        this.setBlockAndMetadata(world, 3, 5, 3, this.brickStairBlock, 3);
        this.setBlockAndMetadata(world, 2, 6, 1, this.brickStairBlock, 2);
        this.setBlockAndMetadata(world, 2, 6, 3, this.brickStairBlock, 3);
        this.setBlockAndMetadata(world, 3, 6, 2, this.brickStairBlock, 0);
        this.setBlockAndMetadata(world, 1, 6, 2, this.brickBlock, this.brickMeta);
        for (int j17 = 6; j17 <= 8; ++j17) {
            this.setBlockAndMetadata(world, 2, j17, 2, this.brickBlock, this.brickMeta);
        }
        this.setBlockAndMetadata(world, 2, 9, 2, this.rockSlabBlock, this.rockSlabMeta);
        for (k12 = 0; k12 <= 4; ++k12) {
            this.setBlockAndMetadata(world, 2, 4, k12, this.brickBlock, this.brickMeta);
            for (int step2 = 0; step2 <= 1; ++step2) {
                this.setBlockAndMetadata(world, 1 - step2, 5 + step2, k12, this.brickStairBlock, 5);
            }
        }
        for (int k132 : new int[]{0, 4}) {
            for (int j18 = 1; j18 <= 3; ++j18) {
                this.setBlockAndMetadata(world, 2, j18, k132, this.rockWallBlock, this.rockWallMeta);
            }
        }
        this.setBlockAndMetadata(world, 2, 0, 2, LOTRMod.hearth, 0);
        this.setBlockAndMetadata(world, 2, 1, 2, (Block)Blocks.fire, 0);
        this.setBlockAndMetadata(world, 2, 2, 2, Blocks.furnace, 5);
        this.setBlockAndMetadata(world, 2, 3, 2, this.brickCarvedBlock, this.brickCarvedMeta);
        this.setBlockAndMetadata(world, 1, 0, 2, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
        this.setBlockAndMetadata(world, 1, 1, 1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 1, 1, 2, this.barsBlock, 0);
        this.setBlockAndMetadata(world, 1, 1, 3, this.brickBlock, this.brickMeta);
        for (k1 = 1; k1 <= 3; ++k1) {
            this.setBlockAndMetadata(world, 1, 2, k1, this.rockSlabBlock, this.rockSlabMeta);
        }
        this.placeWeaponRack(world, 1, 3, 2, 7, this.getRandomRohanWeapon(random));
        this.spawnItemFrame(world, 2, 4, 2, 3, this.getRohanFramedItem(random));
        for (int i16 = -2; i16 <= 1; ++i16) {
            this.setBlockAndMetadata(world, i16, 5, -4, this.plank2SlabBlock, this.plank2SlabMeta);
        }
        this.setBlockAndMetadata(world, -3, 1, -4, this.plankStairBlock, 3);
        this.setBlockAndMetadata(world, -3, 1, -3, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, -3, 1, -2, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -3, 1, -1, this.tableBlock, 0);
        this.placeChest(world, random, -3, 1, 0, 4, LOTRChestContents.ROHAN_HOUSE);
        this.setBlockAndMetadata(world, 2, 1, -4, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, 2, 1, -3, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, 2, 1, -2, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, 2, 1, -1, (Block)Blocks.cauldron, 3);
        this.placeBarrel(world, random, 2, 2, -4, 5, LOTRFoods.ROHAN_DRINK);
        this.placeMug(world, random, 2, 2, -3, 1, LOTRFoods.ROHAN_DRINK);
        if (random.nextBoolean()) {
            this.placePlateWithCertainty(world, random, 2, 2, -2, this.plateBlock, LOTRFoods.ROHAN);
        } else {
            this.setBlockAndMetadata(world, 2, 2, -2, this.plateBlock, 0);
        }
        if (random.nextBoolean()) {
            this.setBlockAndMetadata(world, 3, 2, -2, this.getRandomCakeBlock(random), 0);
        }
        for (k1 = 2; k1 <= 3; ++k1) {
            this.setBlockAndMetadata(world, -2, 1, k1, this.bedBlock, 3);
            this.setBlockAndMetadata(world, -3, 1, k1, this.bedBlock, 11);
            this.setBlockAndMetadata(world, -3, 3, k1, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (int k132 : new int[]{1, 4}) {
            for (int j19 = 1; j19 <= 2; ++j19) {
                this.setBlockAndMetadata(world, -3, j19, k132, this.fenceBlock, this.fenceMeta);
            }
            this.setBlockAndMetadata(world, -3, 3, k132, this.plank2Block, this.plank2Meta);
        }
        this.setBlockAndMetadata(world, -3, 3, -4, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 2, 3, -4, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -2, 4, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 1, 4, 4, Blocks.torch, 4);
        this.spawnItemFrame(world, -2, 3, -5, 0, this.getRohanFramedItem(random));
        this.spawnItemFrame(world, 1, 3, -5, 0, this.getRohanFramedItem(random));
        if (random.nextInt(3) != 0) {
            for (int i17 = -1; i17 <= 0; ++i17) {
                for (k14 = -3; k14 <= -1; ++k14) {
                    this.setBlockAndMetadata(world, i17, 1, k14, this.carpetBlock, this.carpetMeta);
                }
            }
        }
        if (random.nextInt(3) != 0) {
            boolean hayOrWood = random.nextBoolean();
            for (i13 = -1; i13 <= 1; ++i13) {
                for (int k18 = 6; k18 <= 7; ++k18) {
                    if (k18 != 6 && i13 != 0) continue;
                    j1 = 1;
                    while (!this.isOpaque(world, i13, j1 - 1, k18) && this.getY(j1) >= 0) {
                        --j1;
                    }
                    int j2 = j1;
                    if (i13 == 0 && k18 == 6) {
                        ++j2;
                    }
                    for (int j3 = j1; j3 <= j2; ++j3) {
                        if (hayOrWood) {
                            this.setBlockAndMetadata(world, i13, j3, k18, Blocks.hay_block, 0);
                            continue;
                        }
                        this.setBlockAndMetadata(world, i13, j3, k18, this.woodBeamBlock, this.woodBeamMeta | 8);
                    }
                    this.setGrassToDirt(world, i13, j1 - 1, k18);
                }
            }
        }
        if (random.nextBoolean()) {
            int i15;
            int j110 = 2;
            k14 = 6;
            ArrayList<Integer> chestCoords = new ArrayList<Integer>();
            for (i15 = -4; i15 <= 3; ++i15) {
                if (this.isOpaque(world, i15, j110, k14)) continue;
                chestCoords.add(i15);
            }
            if (!chestCoords.isEmpty()) {
                i15 = (Integer)chestCoords.get(random.nextInt(chestCoords.size()));
                while (!this.isOpaque(world, i15, j110 - 1, k14) && this.getY(j110) >= 0) {
                    --j110;
                }
                this.placeChest(world, random, i15, j110, k14, 3, LOTRChestContents.ROHAN_HOUSE);
            }
        }
        int men = 1 + random.nextInt(2);
        for (int l = 0; l < men; ++l) {
            LOTREntityRohanMan rohirrim = new LOTREntityRohanMan(world);
            this.spawnNPCAndSetHome(rohirrim, world, 0, 1, 0, 16);
        }
        return true;
    }
}

