/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenGondorCottage
extends LOTRWorldGenGondorStructure {
    public LOTRWorldGenGondorCottage(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.wallBlock = LOTRMod.daub;
        this.wallMeta = 0;
        if (random.nextInt(3) == 0) {
            this.roofBlock = this.brick2Block;
            this.roofMeta = this.brick2Meta;
            this.roofSlabBlock = this.brick2SlabBlock;
            this.roofSlabMeta = this.brick2SlabMeta;
            this.roofStairBlock = this.brick2StairBlock;
            this.bedBlock = Blocks.bed;
        }
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int k1;
        int j1;
        int k122;
        int k13;
        int j12;
        int i12;
        int i13;
        int l;
        int j13;
        this.setOriginAndRotation(world, i, j, k, rotation, 6);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i14 = -6; i14 <= 6; ++i14) {
                for (k122 = -7; k122 <= 10; ++k122) {
                    j1 = this.getTopBlock(world, i14, k122) - 1;
                    if (!this.isSurface(world, i14, j1, k122)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 5) continue;
                    return false;
                }
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (k1 = -5; k1 <= 5; ++k1) {
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                if (i2 == 5 && k2 == 5) {
                    for (j1 = 3; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.woodBeamBlock, this.woodBeamMeta);
                        this.setGrassToDirt(world, i1, j1 - 1, k1);
                    }
                    continue;
                }
                if (i2 == 5 || k2 == 5) {
                    for (j1 = 1; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.brickBlock, this.brickMeta);
                        this.setGrassToDirt(world, i1, j1 - 1, k1);
                    }
                    this.setBlockAndMetadata(world, i1, 2, k1, this.wallBlock, this.wallMeta);
                    this.setBlockAndMetadata(world, i1, 3, k1, this.wallBlock, this.wallMeta);
                    continue;
                }
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.rockBlock, this.rockMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 1; j1 <= 7; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                if (random.nextInt(3) != 0) {
                    this.setBlockAndMetadata(world, i1, 1, k1, LOTRMod.thatchFloor, 0);
                }
                if (i2 != 4 || k2 != 4) continue;
                for (j1 = 1; j1 <= 4; ++j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.woodBeamBlock, this.woodBeamMeta);
                }
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (k1 = -7; k1 <= -6; ++k1) {
                for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i1, j12, k1) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i1, j12, k1, LOTRMod.dirtPath, 0);
                    this.setGrassToDirt(world, i1, j12 - 1, k1);
                }
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            for (k1 = 6; k1 <= 10; ++k1) {
                if (k1 == 10 && Math.abs(i1) >= 3) continue;
                for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i1, j12, k1) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i1, j12, k1, LOTRMod.dirtPath, 0);
                    this.setGrassToDirt(world, i1, j12 - 1, k1);
                }
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (int i15 : new int[]{-5, 5}) {
            this.setBlockAndMetadata(world, i15, 2, -3, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 2, -2, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 2, 0, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 2, 2, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i15, 2, 3, this.fenceBlock, this.fenceMeta);
        }
        for (int k122 : new int[]{-5, 5}) {
            int i16;
            for (i16 = -1; i16 <= 1; ++i16) {
                for (int j14 = 2; j14 <= 3; ++j14) {
                    this.setBlockAndMetadata(world, i16, j14, k122, this.brickBlock, this.brickMeta);
                }
            }
            for (i16 = -4; i16 <= 4; ++i16) {
                this.setBlockAndMetadata(world, i16, 4, k122, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        for (int i17 = -3; i17 <= 3; ++i17) {
            if (Math.abs(i17) <= 1) continue;
            this.setBlockAndMetadata(world, i17, 2, -5, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i17, 3, -5, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 2, 5, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i17, 3, 5, this.wallBlock, this.wallMeta);
        }
        this.setBlockAndMetadata(world, 0, 0, -5, this.rockBlock, this.rockMeta);
        this.setBlockAndMetadata(world, 0, 1, -5, this.doorBlock, 1);
        this.setBlockAndMetadata(world, 0, 2, -5, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 0, 3, -6, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 0, 0, 5, this.rockBlock, this.rockMeta);
        this.setBlockAndMetadata(world, 0, 1, 5, this.doorBlock, 3);
        this.setBlockAndMetadata(world, 0, 2, 5, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 0, 3, 6, Blocks.torch, 3);
        int[] i17 = new int[]{-5, 5};
        k1 = i17.length;
        for (j12 = 0; j12 < k1; ++j12) {
            k122 = i17[j12];
            for (int l2 = 0; l2 <= 2; ++l2) {
                for (int i18 = -3 + l2; i18 <= 3 - l2; ++i18) {
                    this.setBlockAndMetadata(world, i18, 5 + l2, k122, this.wallBlock, this.wallMeta);
                }
            }
        }
        this.setBlockAndMetadata(world, 0, 5, -5, this.wallBlock, this.wallMeta);
        this.setBlockAndMetadata(world, 0, 6, -5, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 7, -5, this.wallBlock, this.wallMeta);
        this.setBlockAndMetadata(world, 0, 5, 5, this.wallBlock, this.wallMeta);
        this.setBlockAndMetadata(world, 0, 6, 5, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 7, 5, this.wallBlock, this.wallMeta);
        for (k13 = -6; k13 <= 6; ++k13) {
            for (l = 0; l <= 5; ++l) {
                this.setBlockAndMetadata(world, -6 + l, 3 + l, k13, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 6 - l, 3 + l, k13, this.roofStairBlock, 0);
            }
            this.setBlockAndMetadata(world, 0, 8, k13, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 0, 9, k13, this.roofSlabBlock, this.roofSlabMeta);
            if (Math.abs(k13) != 6) continue;
            for (l = 0; l <= 4; ++l) {
                this.setBlockAndMetadata(world, -5 + l, 3 + l, k13, this.roofStairBlock, 4);
                this.setBlockAndMetadata(world, 5 - l, 3 + l, k13, this.roofStairBlock, 5);
            }
        }
        for (i12 = -4; i12 <= 4; ++i12) {
            this.setBlockAndMetadata(world, i12, 4, 0, this.woodBeamBlock, this.woodBeamMeta | 4);
        }
        for (k13 = -4; k13 <= 4; ++k13) {
            this.setBlockAndMetadata(world, 0, 4, k13, this.woodBeamBlock, this.woodBeamMeta | 8);
        }
        for (j13 = 1; j13 <= 7; ++j13) {
            this.setBlockAndMetadata(world, 0, j13, 0, this.woodBeamBlock, this.woodBeamMeta);
        }
        this.setBlockAndMetadata(world, -1, 3, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 1, 3, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 3, -1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 3, 1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -4, 3, 0, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 4, 3, 0, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 0, 3, -4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 0, 3, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 0, 5, -4, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 6, -4, Blocks.torch, 5);
        this.setBlockAndMetadata(world, 0, 5, 4, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 6, 4, Blocks.torch, 5);
        this.setBlockAndMetadata(world, -2, 1, -4, this.bedBlock, 3);
        this.setBlockAndMetadata(world, -3, 1, -4, this.bedBlock, 11);
        this.setBlockAndMetadata(world, 2, 1, -4, this.bedBlock, 1);
        this.setBlockAndMetadata(world, 3, 1, -4, this.bedBlock, 9);
        this.setBlockAndMetadata(world, -4, 1, -2, this.bedBlock, 2);
        this.setBlockAndMetadata(world, -4, 1, -3, this.bedBlock, 10);
        this.setBlockAndMetadata(world, 4, 1, -2, this.bedBlock, 2);
        this.setBlockAndMetadata(world, 4, 1, -3, this.bedBlock, 10);
        this.setBlockAndMetadata(world, -4, 1, 0, Blocks.furnace, 4);
        this.setBlockAndMetadata(world, -4, 1, 1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
        this.placePlateWithCertainty(world, random, -4, 2, 1, this.plateBlock, LOTRFoods.GONDOR);
        this.setBlockAndMetadata(world, -4, 1, 2, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, -4, 1, 3, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
        this.placeMug(world, random, -4, 2, 3, 3, LOTRFoods.GONDOR_DRINK);
        this.setBlockAndMetadata(world, -3, 1, 4, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
        this.placeFlowerPot(world, -3, 2, 4, this.getRandomFlower(world, random));
        this.setBlockAndMetadata(world, -2, 1, 4, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 4, 1, 0, this.tableBlock, 0);
        this.placeChest(world, random, 4, 1, 1, 5, this.chestContents);
        this.placeChest(world, random, 4, 1, 2, 5, this.chestContents);
        this.setBlockAndMetadata(world, 4, 1, 3, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 3, 1, 4, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
        this.placeFlowerPot(world, 3, 2, 4, this.getRandomFlower(world, random));
        this.setBlockAndMetadata(world, 2, 1, 4, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, -5, 1, -6, LOTRMod.reedBars, 0);
        for (i12 = -5; i12 <= -3; ++i12) {
            this.setBlockAndMetadata(world, i12, 1, -7, LOTRMod.reedBars, 0);
        }
        this.placeFlowerPot(world, -4, 1, -6, this.getRandomFlower(world, random));
        this.placeFlowerPot(world, -3, 1, -6, this.getRandomFlower(world, random));
        this.placeFlowerPot(world, 2, 1, -6, this.getRandomFlower(world, random));
        this.setBlockAndMetadata(world, 3, 1, -6, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 4, 1, -6, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 5, 1, -6, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 4, 2, -6, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 4, 1, -7, Blocks.hay_block, 0);
        for (j13 = 1; j13 <= 2; ++j13) {
            for (k1 = 6; k1 <= 9; ++k1) {
                this.setBlockAndMetadata(world, -4, j13, k1, LOTRMod.reedBars, 0);
                this.setBlockAndMetadata(world, 4, j13, k1, LOTRMod.reedBars, 0);
            }
            this.setBlockAndMetadata(world, -3, j13, 9, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, -2, j13, 9, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, 2, j13, 9, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, 3, j13, 9, LOTRMod.reedBars, 0);
            for (i13 = -2; i13 <= 2; ++i13) {
                this.setBlockAndMetadata(world, i13, j13, 10, LOTRMod.reedBars, 0);
            }
        }
        int[] j15 = new int[]{-2, 1};
        i13 = j15.length;
        for (j12 = 0; j12 < i13; ++j12) {
            int i15;
            for (int i2 = i15 = j15[j12]; i2 <= i15 + 1; ++i2) {
                for (int k14 = 7; k14 <= 8; ++k14) {
                    this.setBlockAndMetadata(world, i2, 0, k14, Blocks.farmland, 7);
                    this.setBlockAndMetadata(world, i2, 1, k14, this.cropBlock, this.cropMeta);
                }
            }
        }
        this.setBlockAndMetadata(world, 0, -1, 9, Blocks.dirt, 0);
        this.setGrassToDirt(world, 0, -2, 9);
        this.setBlockAndMetadata(world, 0, 0, 9, Blocks.water, 0);
        this.setBlockAndMetadata(world, 0, 1, 9, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 2, 9, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 3, 9, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 0, 4, 9, Blocks.pumpkin, 0);
        int men = 1 + random.nextInt(2);
        for (l = 0; l < men; ++l) {
            LOTREntityGondorMan gondorMan = new LOTREntityGondorMan(world);
            this.spawnNPCAndSetHome(gondorMan, world, 0, 1, -1, 16);
        }
        return true;
    }
}

