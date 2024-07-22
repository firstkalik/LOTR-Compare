/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockTripWireHook
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenHobbitStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenHobbitHouse
extends LOTRWorldGenHobbitStructure {
    public LOTRWorldGenHobbitHouse(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean makeWealthy(Random random) {
        return false;
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.bedBlock = random.nextBoolean() ? LOTRMod.strawBed : Blocks.bed;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i16;
        int i9;
        int i19;
        int i20;
        int i12;
        int i10;
        int m;
        int i202;
        int i8;
        int i7;
        int i6;
        int i203;
        int i1;
        int i13;
        this.setOriginAndRotation(world, i, j, k, rotation, 11, 1);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i202 = -9; i202 <= 8; ++i202) {
                for (int i21 = -10; i21 <= 6; ++i21) {
                    int i22 = this.getTopBlock(world, i202, i21) - 1;
                    if (!this.isSurface(world, i202, i22, i21)) {
                        return false;
                    }
                    if (i22 < minHeight) {
                        minHeight = i22;
                    }
                    if (i22 <= maxHeight) continue;
                    maxHeight = i22;
                }
            }
            if (maxHeight - minHeight > 6) {
                return false;
            }
        }
        for (i19 = -4; i19 <= 3; ++i19) {
            for (i203 = -10; i203 <= -6; ++i203) {
                int i21;
                for (i21 = 0; !(i21 < 0 && this.isOpaque(world, i19, i21, i203) || this.getY(i21) < 0); --i21) {
                    if (i21 == 0) {
                        this.setBlockAndMetadata(world, i19, i21, i203, (Block)Blocks.grass, 0);
                    } else {
                        this.setBlockAndMetadata(world, i19, i21, i203, Blocks.dirt, 0);
                    }
                    this.setGrassToDirt(world, i19, i21 - 1, i203);
                }
                for (i21 = 1; i21 <= 5; ++i21) {
                    this.setAir(world, i19, i21, i203);
                }
                if (i19 != -4 && i19 != 3 && i203 != -10) continue;
                this.setBlockAndMetadata(world, i19, 1, i203, this.outFenceBlock, this.outFenceMeta);
            }
        }
        for (i19 = -9; i19 <= 8; ++i19) {
            for (i203 = -6; i203 <= 6; ++i203) {
                int i21;
                int k2 = Math.abs(i203);
                boolean beam = false;
                boolean wall = false;
                int indoors = 0;
                if ((i19 == -7 || i19 == 6) && k2 == 4) {
                    beam = true;
                } else if ((i19 == -9 || i19 == 8) && k2 == 0) {
                    beam = true;
                } else if ((i19 == -3 || i19 == 2) && k2 == 6) {
                    beam = true;
                } else if (i19 >= -6 && i19 <= 5 && k2 <= 5 || k2 <= 3 && i19 >= -8 && i19 <= 7) {
                    if (i19 == -8 || i19 == 7 || k2 == 5) {
                        wall = true;
                    } else {
                        indoors = 1;
                    }
                }
                if (!beam && !wall && indoors == 0) continue;
                for (i21 = 1; i21 <= 6; ++i21) {
                    this.setAir(world, i19, i21, i203);
                }
                if (beam) {
                    for (i21 = 2; !(i21 < 0 && this.isOpaque(world, i19, i21, i203) || this.getY(i21) < 0); --i21) {
                        this.setBlockAndMetadata(world, i19, i21, i203, this.beamBlock, this.beamMeta);
                        this.setGrassToDirt(world, i19, i21 - 1, i203);
                    }
                    continue;
                }
                if (wall) {
                    for (i21 = 0; !(i21 < 0 && this.isOpaque(world, i19, i21, i203) || this.getY(i21) < 0); --i21) {
                        this.setBlockAndMetadata(world, i19, i21, i203, this.plankBlock, this.plankMeta);
                        this.setGrassToDirt(world, i19, i21 - 1, i203);
                    }
                    for (i21 = 1; i21 <= 2; ++i21) {
                        this.setBlockAndMetadata(world, i19, i21, i203, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                if (indoors == 0) continue;
                for (i21 = 0; !(i21 < 0 && this.isOpaque(world, i19, i21, i203) || this.getY(i21) < 0); --i21) {
                    this.setBlockAndMetadata(world, i19, i21, i203, this.floorBlock, this.floorMeta);
                    this.setGrassToDirt(world, i19, i21 - 1, i203);
                }
                this.setBlockAndMetadata(world, i19, 3, i203, this.plankSlabBlock, this.plankSlabMeta | 8);
            }
        }
        for (i19 = -2; i19 <= 1; ++i19) {
            for (i203 = 1; i203 <= 3; ++i203) {
                this.setBlockAndMetadata(world, i19, i203, -5, Blocks.brick_block, 0);
            }
        }
        for (i19 = -1; i19 <= 0; ++i19) {
            this.setBlockAndMetadata(world, i19, 0, -5, this.floorBlock, this.floorMeta);
            for (i203 = 1; i203 <= 2; ++i203) {
                this.setBlockAndMetadata(world, i19, i203, -5, this.gateBlock, 2);
            }
        }
        this.setBlockAndMetadata(world, 1, 2, -4, (Block)Blocks.tripwire_hook, 0);
        for (int i18 = 1; i18 <= 3; ++i18) {
            this.setBlockAndMetadata(world, -3, i18, -4, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -7, i18, 0, this.beamBlock, this.beamMeta);
            for (i202 = -6; i202 <= -4; ++i202) {
                this.setBlockAndMetadata(world, i202, i18, 0, this.plankBlock, this.plankMeta);
            }
            this.setBlockAndMetadata(world, -3, i18, 0, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -3, i18, 4, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 2, i18, 4, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 6, i18, 0, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 2, i18, -4, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, -2, 3, -4, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -3, 3, -3, this.plankBlock, this.plankMeta);
        for (int i17 = -6; i17 <= -4; ++i17) {
            this.setBlockAndMetadata(world, i17, 3, -4, this.plankBlock, this.plankMeta);
        }
        for (i16 = -3; i16 <= -1; ++i16) {
            this.setBlockAndMetadata(world, -7, 3, i16, this.plankBlock, this.plankMeta);
        }
        for (i16 = 1; i16 <= 3; ++i16) {
            this.setBlockAndMetadata(world, -7, 3, i16, this.plankBlock, this.plankMeta);
        }
        for (int i15 = -6; i15 <= -4; ++i15) {
            this.setBlockAndMetadata(world, i15, 3, 4, this.plankBlock, this.plankMeta);
        }
        for (int i14 = 1; i14 <= 3; ++i14) {
            this.setBlockAndMetadata(world, -3, 3, i14, this.plankBlock, this.plankMeta);
        }
        for (i13 = -2; i13 <= 1; ++i13) {
            this.setBlockAndMetadata(world, i13, 3, 4, this.plankBlock, this.plankMeta);
        }
        this.setBlockAndMetadata(world, 2, 3, 3, this.plankBlock, this.plankMeta);
        for (i13 = 3; i13 <= 5; ++i13) {
            this.setBlockAndMetadata(world, i13, 3, 4, (Block)Blocks.double_stone_slab, 0);
        }
        for (i12 = 1; i12 <= 3; ++i12) {
            this.setBlockAndMetadata(world, 6, 3, i12, (Block)Blocks.double_stone_slab, 0);
        }
        this.setBlockAndMetadata(world, 5, 3, 0, this.plankBlock, this.plankMeta);
        for (i12 = -3; i12 <= -1; ++i12) {
            this.setBlockAndMetadata(world, 6, 3, i12, this.plankBlock, this.plankMeta);
        }
        for (int i11 = 3; i11 <= 5; ++i11) {
            this.setBlockAndMetadata(world, i11, 3, -4, this.plankBlock, this.plankMeta);
        }
        this.setBlockAndMetadata(world, 2, 3, -3, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, 1, 3, -4, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -4, 1, -4, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -4, 2, -4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -5, 2, -5, LOTRMod.glassPane, 0);
        this.placeChest(world, random, -6, 1, -4, 3, LOTRChestContents.HOBBIT_HOLE_STUDY);
        for (int j1 = 1; j1 <= 2; ++j1) {
            this.setBlockAndMetadata(world, -7, j1, -3, Blocks.bookshelf, 0);
            this.setBlockAndMetadata(world, -7, j1, -1, Blocks.bookshelf, 0);
        }
        this.setBlockAndMetadata(world, -7, 1, -2, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        this.setBlockAndMetadata(world, -5, 1, -2, Blocks.oak_stairs, 1);
        this.spawnItemFrame(world, -5, 2, 0, 2, new ItemStack(Items.clock));
        this.setBlockAndMetadata(world, -3, 1, 1, this.plankStairBlock, 3);
        this.setBlockAndMetadata(world, -3, 2, 1, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, -3, 1, 3, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, -3, 2, 3, this.plankStairBlock, 6);
        for (int i204 : new int[]{-6, -5}) {
            this.setBlockAndMetadata(world, i204, 1, 3, this.bedBlock, 0);
            this.setBlockAndMetadata(world, i204, 1, 4, this.bedBlock, 8);
        }
        this.setBlockAndMetadata(world, -4, 1, 4, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -4, 2, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -7, 1, 1, this.beamBlock, this.beamMeta);
        this.placeBarrel(world, random, -7, 2, 1, 4, LOTRFoods.HOBBIT_DRINK);
        this.setBlockAndMetadata(world, -1, 2, 5, LOTRMod.glassPane, 0);
        this.setBlockAndMetadata(world, 0, 2, 5, LOTRMod.glassPane, 0);
        this.setBlockAndMetadata(world, 2, 2, 3, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 3, 1, 4, this.tableBlock, 0);
        for (i10 = 4; i10 <= 5; ++i10) {
            this.placeChest(world, random, i10, 1, 4, 2, LOTRChestContents.HOBBIT_HOLE_LARDER);
        }
        this.setBlockAndMetadata(world, 6, 1, 3, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 6, 1, 2, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, 6, 1, 1, LOTRMod.hobbitOven, 5);
        this.setBlockAndMetadata(world, 6, 2, -1, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 4, 2, -5, LOTRMod.glassPane, 0);
        this.setBlockAndMetadata(world, 3, 2, -4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 6, 1, -1, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, 6, 1, -2, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 6, 1, -3, this.plankStairBlock, 3);
        this.setBlockAndMetadata(world, 5, 1, -4, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 4, 1, -4, this.plankStairBlock, 3);
        this.setBlockAndMetadata(world, 3, 1, -4, this.plankStairBlock, 0);
        for (i10 = 3; i10 <= 4; ++i10) {
            for (i20 = -2; i20 <= -1; ++i20) {
                this.setBlockAndMetadata(world, i10, 1, i20, Blocks.planks, 1);
                if (random.nextBoolean()) {
                    this.placePlateWithCertainty(world, random, i10, 2, i20, this.plateBlock, LOTRFoods.HOBBIT);
                    continue;
                }
                this.placeMug(world, random, i10, 2, i20, random.nextInt(4), LOTRFoods.HOBBIT_DRINK);
            }
        }
        for (i10 = -1; i10 <= 0; ++i10) {
            for (i20 = -2; i20 <= 1; ++i20) {
                this.setBlockAndMetadata(world, i10, 1, i20, this.carpetBlock, this.carpetMeta);
            }
        }
        for (i10 = -2; i10 <= 1; ++i10) {
            this.setBlockAndMetadata(world, i10, 0, -6, this.pathBlock, this.pathMeta);
        }
        this.setBlockAndMetadata(world, -1, 0, -7, this.pathBlock, this.pathMeta);
        this.setBlockAndMetadata(world, 0, 0, -7, this.pathBlock, this.pathMeta);
        this.setBlockAndMetadata(world, 0, 0, -8, this.pathBlock, this.pathMeta);
        this.setBlockAndMetadata(world, 1, 0, -8, this.pathBlock, this.pathMeta);
        this.setBlockAndMetadata(world, 1, 0, -9, this.pathBlock, this.pathMeta);
        this.setBlockAndMetadata(world, 1, 0, -10, this.pathBlock, this.pathMeta);
        this.setAir(world, 1, 1, -10);
        for (i10 = -3; i10 <= 2; ++i10) {
            for (i20 = -9; i20 <= -7; ++i20) {
                if (this.getBlock(world, i10, 0, i20) != Blocks.grass || random.nextInt(4) != 0) continue;
                this.plantFlower(world, random, i10, 1, i20);
            }
        }
        this.placeHedge(world, -7, 1, -5);
        this.placeHedge(world, -8, 1, -4);
        for (i9 = -3; i9 <= -1; ++i9) {
            this.placeHedge(world, -9, 1, i9);
        }
        for (i9 = 1; i9 <= 3; ++i9) {
            this.placeHedge(world, -9, 1, i9);
        }
        this.placeHedge(world, -8, 1, 4);
        this.placeHedge(world, -7, 1, 5);
        for (i8 = -6; i8 <= -4; ++i8) {
            this.placeHedge(world, i8, 1, 6);
        }
        for (i8 = 3; i8 <= 5; ++i8) {
            this.placeHedge(world, i8, 1, 6);
        }
        this.placeHedge(world, 6, 1, 5);
        this.placeHedge(world, 7, 1, 4);
        for (i7 = 1; i7 <= 3; ++i7) {
            this.placeHedge(world, 8, 1, i7);
        }
        for (i7 = -3; i7 <= -1; ++i7) {
            this.placeHedge(world, 8, 1, i7);
        }
        this.placeHedge(world, 7, 1, -4);
        this.placeHedge(world, 6, 1, -5);
        for (i6 = -2; i6 <= 1; ++i6) {
            this.setBlockAndMetadata(world, i6, 1, 6, Blocks.brick_block, 0);
            this.setGrassToDirt(world, i6, 0, 6);
            this.placeFlowerPot(world, i6, 2, 6, this.getRandomFlower(world, random));
        }
        for (i6 = -6; i6 <= 5; ++i6) {
            this.setBlockAndMetadata(world, i6, 3, -6, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i6, 3, 6, this.roofSlabBlock, this.roofSlabMeta);
        }
        for (int i5 = -3; i5 <= 3; ++i5) {
            this.setBlockAndMetadata(world, -9, 3, i5, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, 8, 3, i5, this.roofSlabBlock, this.roofSlabMeta);
        }
        this.setBlockAndMetadata(world, -7, 3, -5, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -8, 3, -4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -8, 3, 4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -7, 3, 5, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 6, 3, 5, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 7, 3, 4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 7, 3, -4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 6, 3, -5, this.roofSlabBlock, this.roofSlabMeta);
        for (int i4 = -6; i4 <= 5; ++i4) {
            this.setBlockAndMetadata(world, i4, 3, -5, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i4, 3, 5, this.roofBlock, this.roofMeta);
        }
        for (int i3 = -3; i3 <= 3; ++i3) {
            this.setBlockAndMetadata(world, -8, 3, i3, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 7, 3, i3, this.roofBlock, this.roofMeta);
        }
        this.setBlockAndMetadata(world, -7, 3, -4, this.roofBlock, this.roofMeta);
        this.setBlockAndMetadata(world, 6, 3, -4, this.roofBlock, this.roofMeta);
        this.setBlockAndMetadata(world, -7, 3, 4, this.roofBlock, this.roofMeta);
        this.setBlockAndMetadata(world, 6, 3, 4, this.roofBlock, this.roofMeta);
        for (int i2 = -5; i2 <= 4; ++i2) {
            this.setBlockAndMetadata(world, i2, 4, -4, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i2, 4, 4, this.roofStairBlock, 3);
        }
        for (int n = -2; n <= 2; ++n) {
            this.setBlockAndMetadata(world, -7, 4, n, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 6, 4, n, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -6, 4, -4, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -6, 4, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -7, 4, -3, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 5, 4, -4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 5, 4, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 6, 4, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -6, 4, 4, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -6, 4, 3, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -7, 4, 3, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 5, 4, 4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 5, 4, 3, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 6, 4, 3, this.roofStairBlock, 0);
        for (m = -6; m <= 5; ++m) {
            for (int i205 = -3; i205 <= 3; ++i205) {
                if ((m < -5 || m > 4) && (i205 < -2 || i205 > 2)) continue;
                this.setBlockAndMetadata(world, m, 4, i205, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, m, 5, i205, this.roofSlabBlock, this.roofSlabMeta);
            }
        }
        for (m = -5; m <= 4; ++m) {
            this.setBlockAndMetadata(world, m, 5, -2, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, m, 5, 2, this.roofStairBlock, 3);
        }
        for (int k1 = -1; k1 <= 1; ++k1) {
            this.setBlockAndMetadata(world, -5, 5, k1, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 4, 5, k1, this.roofStairBlock, 0);
        }
        for (i1 = -4; i1 <= 3; ++i1) {
            for (int i206 = -1; i206 <= 1; ++i206) {
                this.setBlockAndMetadata(world, i1, 5, i206, this.roofBlock, this.roofMeta);
            }
        }
        this.setBlockAndMetadata(world, 3, 5, 0, Blocks.brick_block, 0);
        this.setBlockAndMetadata(world, 3, 6, 0, Blocks.brick_block, 0);
        this.setBlockAndMetadata(world, 3, 7, 0, Blocks.flower_pot, 0);
        for (i1 = -2; i1 <= 1; ++i1) {
            this.setBlockAndMetadata(world, i1, 3, -5, Blocks.brick_block, 0);
            this.setBlockAndMetadata(world, i1, 4, -5, this.roofSlabBlock, this.roofSlabMeta);
        }
        this.setBlockAndMetadata(world, -3, 3, -6, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, -2, 3, -6, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, -1, 4, -6, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, 0, 4, -6, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, 1, 3, -6, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, 2, 3, -6, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, -3, 2, -7, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, -2, 3, -7, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, -1, 3, -7, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, 0, 3, -7, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, 1, 3, -7, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, 2, 2, -7, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.spawnHobbitCouple(world, 0, 1, 0, 16);
        return true;
    }

    private void placeHedge(World world, int i, int j, int k) {
        int j1;
        for (j1 = j; !this.isOpaque(world, i, j1 - 1, k) && j1 >= j - 6; --j1) {
        }
        this.setBlockAndMetadata(world, i, j1, k, this.hedgeBlock, this.hedgeMeta);
    }
}

