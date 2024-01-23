/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Direction
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDaleMan;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDaleStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenDaleHouse
extends LOTRWorldGenDaleStructure {
    public LOTRWorldGenDaleHouse(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int k13;
        int j1;
        int fillBlock22;
        int k1;
        int i12;
        int i13;
        int k12;
        int k14;
        this.setOriginAndRotation(world, i, j, k, rotation, 1);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i16 = -8; i16 <= 3; ++i16) {
                for (int k142 = -1; k142 <= 9; ++k142) {
                    int j12 = this.getTopBlock(world, i16, k142) - 1;
                    Block block = this.getBlock(world, i16, j12, k142);
                    if (block != Blocks.grass) {
                        return false;
                    }
                    if (j12 < minHeight) {
                        minHeight = j12;
                    }
                    if (j12 > maxHeight) {
                        maxHeight = j12;
                    }
                    if (maxHeight - minHeight <= 5) continue;
                    return false;
                }
            }
        }
        for (int i17 = -7; i17 <= 2; ++i17) {
            for (k13 = 0; k13 <= 8; ++k13) {
                int j12;
                if (i17 < -2 && k13 > 4) continue;
                for (int j13 = 1; j13 <= 10; ++j13) {
                    this.setAir(world, i17, j13, k13);
                }
                Block fillBlock22 = null;
                int fillMeta = 0;
                if (!(i17 != -2 && i17 != 2 || k13 != 0 && k13 != 4 && k13 != 8)) {
                    fillBlock22 = this.brickBlock;
                    fillMeta = this.brickMeta;
                    for (j12 = 1; j12 <= 7; ++j12) {
                        this.setBlockAndMetadata(world, i17, j12, k13, this.brickBlock, this.brickMeta);
                    }
                } else if ((k13 == 0 || k13 == 4) && i17 >= -6 && i17 <= -4) {
                    fillBlock22 = this.brickBlock;
                    fillMeta = this.brickMeta;
                    for (j12 = 1; j12 <= 4; ++j12) {
                        this.setBlockAndMetadata(world, i17, j12, k13, this.brickBlock, this.brickMeta);
                    }
                    for (j12 = 5; j12 <= 6; ++j12) {
                        this.setBlockAndMetadata(world, i17, j12, k13, this.plankBlock, this.plankMeta);
                    }
                    this.setBlockAndMetadata(world, i17, 7, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                } else if (i17 == -7 && k13 >= 1 && k13 <= 3) {
                    fillBlock22 = this.brickBlock;
                    fillMeta = this.brickMeta;
                    for (j12 = 1; j12 <= 4; ++j12) {
                        this.setBlockAndMetadata(world, i17, j12, k13, this.brickBlock, this.brickMeta);
                    }
                    for (j12 = 5; j12 <= 6; ++j12) {
                        this.setBlockAndMetadata(world, i17, j12, k13, this.plankBlock, this.plankMeta);
                    }
                    this.setBlockAndMetadata(world, i17, 7, k13, this.woodBeamBlock, this.woodBeamMeta | 8);
                } else if (!(k13 != 0 && k13 != 4 || i17 != -7 && i17 != -3)) {
                    fillBlock22 = this.woodBlock;
                    fillMeta = this.woodMeta;
                    for (j12 = 1; j12 <= 7; ++j12) {
                        this.setBlockAndMetadata(world, i17, j12, k13, this.woodBlock, this.woodMeta);
                    }
                } else {
                    fillBlock22 = this.floorBlock;
                    fillMeta = this.floorMeta;
                }
                for (j12 = 0; !(j12 != 0 && this.isOpaque(world, i17, j12, k13) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i17, j12, k13, fillBlock22, fillMeta);
                    this.setGrassToDirt(world, i17, j12 - 1, k13);
                }
            }
        }
        for (int[] pos : new int[][]{{-3, -1}, {-7, -1}, {-8, 0}, {-8, 4}, {-7, 5}}) {
            int i132 = pos[0];
            int k15 = pos[1];
            for (int j14 = 7; !(j14 < 4 && this.isOpaque(world, i132, j14, k15) || this.getY(j14) < 0); --j14) {
                this.setBlockAndMetadata(world, i132, j14, k15, this.fenceBlock, this.fenceMeta);
            }
        }
        for (int[] k16 : new int[]{0, 4, 8}) {
            this.setBlockAndMetadata(world, -1, 3, (int)k16, this.brickStairBlock, 4);
            this.setBlockAndMetadata(world, 1, 3, (int)k16, this.brickStairBlock, 5);
        }
        int[][] i17 = new int[]{-2, 2};
        k13 = i17.length;
        for (fillBlock22 = 0; fillBlock22 < k13; ++fillBlock22) {
            int[] i122 = i17[fillBlock22];
            this.setBlockAndMetadata(world, (int)i122, 3, 1, this.brickStairBlock, 7);
            this.setBlockAndMetadata(world, (int)i122, 3, 3, this.brickStairBlock, 6);
            this.setBlockAndMetadata(world, (int)i122, 3, 5, this.brickStairBlock, 7);
            this.setBlockAndMetadata(world, (int)i122, 3, 7, this.brickStairBlock, 6);
        }
        for (int j15 = 1; j15 <= 2; ++j15) {
            this.setBlockAndMetadata(world, -2, j15, 1, this.brickWallBlock, this.brickWallMeta);
            this.setBlockAndMetadata(world, -2, j15, 3, this.brickWallBlock, this.brickWallMeta);
        }
        for (k1 = 1; k1 <= 3; ++k1) {
            for (j1 = 1; j1 <= 3; ++j1) {
                this.setBlockAndMetadata(world, -3, j1, k1, this.brickBlock, this.brickMeta);
            }
        }
        this.setBlockAndMetadata(world, -3, 1, 2, this.doorBlock, 0);
        this.setBlockAndMetadata(world, -3, 2, 2, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 0, 1, 7, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 1, 1, 7, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 1, 2, 7, Blocks.hay_block, 0);
        this.setBlockAndMetadata(world, 1, 1, 6, Blocks.hay_block, 0);
        for (i1 = -2; i1 <= 2; ++i1) {
            this.setBlockAndMetadata(world, i1, 4, -1, this.brickStairBlock, 6);
            if (IntMath.mod((int)i1, (int)2) != 1) continue;
            this.setBlockAndMetadata(world, i1, 5, -1, this.brickWallBlock, this.brickWallMeta);
        }
        for (k1 = -1; k1 <= 9; ++k1) {
            this.setBlockAndMetadata(world, 3, 4, k1, this.brickStairBlock, 4);
            if (IntMath.mod((int)k1, (int)2) != 1) continue;
            this.setBlockAndMetadata(world, 3, 5, k1, this.brickWallBlock, this.brickWallMeta);
        }
        for (i1 = 2; i1 >= -2; --i1) {
            this.setBlockAndMetadata(world, i1, 4, 9, this.brickStairBlock, 7);
            if (IntMath.mod((int)i1, (int)2) != 1) continue;
            this.setBlockAndMetadata(world, i1, 5, 9, this.brickWallBlock, this.brickWallMeta);
        }
        for (k1 = 9; k1 >= 5; --k1) {
            this.setBlockAndMetadata(world, -3, 4, k1, this.brickStairBlock, 5);
            if (IntMath.mod((int)k1, (int)2) != 1) continue;
            this.setBlockAndMetadata(world, -3, 5, k1, this.brickWallBlock, this.brickWallMeta);
        }
        for (i1 = -4; i1 >= -6; --i1) {
            this.setBlockAndMetadata(world, i1, 4, 5, this.brickStairBlock, 7);
        }
        this.setBlockAndMetadata(world, -7, 4, 5, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -8, 4, 5, this.brickSlabBlock, this.brickSlabMeta | 8);
        this.setBlockAndMetadata(world, -8, 4, 4, this.brickBlock, this.brickMeta);
        for (k1 = 3; k1 >= 1; --k1) {
            this.setBlockAndMetadata(world, -8, 4, k1, this.brickStairBlock, 5);
        }
        this.setBlockAndMetadata(world, -8, 4, 0, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -8, 4, -1, this.brickSlabBlock, this.brickSlabMeta | 8);
        this.setBlockAndMetadata(world, -7, 4, -1, this.brickBlock, this.brickMeta);
        for (int i18 = -6; i18 <= -4; ++i18) {
            this.setBlockAndMetadata(world, i18, 4, -1, this.brickStairBlock, 6);
        }
        this.setBlockAndMetadata(world, -3, 4, -1, this.brickBlock, this.brickMeta);
        for (int k15 : new int[]{0, 4, 8}) {
            for (int i14 = -1; i14 <= 1; ++i14) {
                this.setBlockAndMetadata(world, i14, 4, k15, this.brickBlock, this.brickMeta);
                if (k15 != 0 && k15 != 8) continue;
                this.setBlockAndMetadata(world, i14, 5, k15, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i14, 6, k15, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i14, 7, k15, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        int[] i18 = new int[]{-2, 2};
        j1 = i18.length;
        for (fillBlock22 = 0; fillBlock22 < j1; ++fillBlock22) {
            int i15 = i18[fillBlock22];
            for (int k122 = 1; k122 <= 3; ++k122) {
                this.setBlockAndMetadata(world, i15, 4, k122, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i15, 5, k122, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i15, 6, k122, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i15, 7, k122, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
            for (k12 = 5; k12 <= 7; ++k12) {
                this.setBlockAndMetadata(world, i15, 4, k12, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i15, 5, k12, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i15, 6, k12, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i15, 7, k12, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
        }
        for (int i15 = -1; i15 <= 1; ++i15) {
            for (k14 = 1; k14 <= 3; ++k14) {
                this.setBlockAndMetadata(world, i15, 4, k14, this.plankBlock, this.plankMeta);
            }
            for (k14 = 5; k14 <= 7; ++k14) {
                this.setBlockAndMetadata(world, i15, 4, k14, this.plankBlock, this.plankMeta);
            }
        }
        this.setBlockAndMetadata(world, -5, 2, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 6, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 2, 6, 2, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 2, 6, 6, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 6, 8, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -2, 6, 6, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -5, 6, 4, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -7, 6, 2, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -5, 6, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -2, 7, -1, this.brickStairBlock, 6);
        this.setBlockAndMetadata(world, 2, 7, -1, this.brickStairBlock, 6);
        this.setBlockAndMetadata(world, 3, 7, 0, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, 3, 7, 4, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, 3, 7, 8, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, 2, 7, 9, this.brickStairBlock, 7);
        this.setBlockAndMetadata(world, -2, 7, 9, this.brickStairBlock, 7);
        this.setBlockAndMetadata(world, -3, 7, 8, this.brickStairBlock, 5);
        for (i13 = -8; i13 <= 3; ++i13) {
            for (k14 = -1; k14 <= 9; ++k14) {
                if (i13 < -3 && k14 > 5) continue;
                this.setBlockAndMetadata(world, i13, 8, k14, this.brickBlock, this.brickMeta);
            }
        }
        this.setBlockAndMetadata(world, -8, 8, -1, this.brickSlabBlock, this.brickSlabMeta | 8);
        this.setBlockAndMetadata(world, -8, 8, 5, this.brickSlabBlock, this.brickSlabMeta | 8);
        for (i13 = -8; i13 <= 3; ++i13) {
            this.setBlockAndMetadata(world, i13, 9, -1, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i13, 10, 0, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i13, 11, 1, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i13, 11, 2, this.roofBlock, this.roofMeta);
            if (i13 <= -1 || i13 >= 1) {
                this.setBlockAndMetadata(world, i13, 11, 3, this.roofStairBlock, 3);
            }
            if (i13 <= -2 || i13 >= 2) {
                this.setBlockAndMetadata(world, i13, 10, 4, this.roofStairBlock, 3);
            }
            if (i13 > -3 && i13 < 3) continue;
            this.setBlockAndMetadata(world, i13, 9, 5, this.roofStairBlock, 3);
        }
        for (int k17 = 3; k17 <= 9; ++k17) {
            if (k17 >= 6) {
                this.setBlockAndMetadata(world, -3, 9, k17, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 3, 9, k17, this.roofStairBlock, 0);
            }
            if (k17 >= 5) {
                this.setBlockAndMetadata(world, -2, 10, k17, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 2, 10, k17, this.roofStairBlock, 0);
            }
            if (k17 >= 4) {
                this.setBlockAndMetadata(world, -1, 11, k17, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 1, 11, k17, this.roofStairBlock, 0);
            }
            this.setBlockAndMetadata(world, 0, 11, k17, this.roofBlock, this.roofMeta);
        }
        for (int i15 : new int[]{-7, 2}) {
            for (k12 = 0; k12 <= 4; ++k12) {
                this.setBlockAndMetadata(world, i15, 9, k12, this.brickBlock, this.brickMeta);
            }
            for (k12 = 1; k12 <= 3; ++k12) {
                this.setBlockAndMetadata(world, i15, 10, k12, this.brickBlock, this.brickMeta);
            }
        }
        for (int i15 : new int[]{-8, 3}) {
            this.setBlockAndMetadata(world, i15, 9, 0, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i15, 10, 1, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i15, 10, 3, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, i15, 9, 4, this.roofStairBlock, 6);
        }
        for (int i14 = -2; i14 <= 2; ++i14) {
            this.setBlockAndMetadata(world, i14, 9, 8, this.brickBlock, this.brickMeta);
        }
        for (i12 = -1; i12 <= 1; ++i12) {
            this.setBlockAndMetadata(world, i12, 10, 8, this.brickBlock, this.brickMeta);
        }
        this.setBlockAndMetadata(world, -2, 9, 9, this.roofStairBlock, 4);
        this.setBlockAndMetadata(world, -1, 10, 9, this.roofStairBlock, 4);
        this.setBlockAndMetadata(world, 1, 10, 9, this.roofStairBlock, 5);
        this.setBlockAndMetadata(world, 2, 9, 9, this.roofStairBlock, 5);
        this.setBlockAndMetadata(world, 0, 12, 2, LOTRMod.hearth, 0);
        this.setBlockAndMetadata(world, 0, 13, 2, (Block)Blocks.fire, 0);
        this.setBlockAndMetadata(world, 0, 11, 1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 12, 1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -1, 12, 2, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 1, 12, 2, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 12, 3, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 13, 1, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, -1, 13, 2, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 1, 13, 2, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 0, 13, 3, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 0, 14, 1, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -1, 14, 2, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 1, 14, 2, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 0, 14, 3, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 0, 14, 2, this.roofBlock, this.roofMeta);
        for (int j16 = 1; j16 <= 7; ++j16) {
            this.setBlockAndMetadata(world, -5, j16, 2, this.woodBeamBlock, this.woodBeamMeta);
        }
        this.setBlockAndMetadata(world, -4, 3, 1, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -6, 7, 2, Blocks.torch, 2);
        this.setBlockAndMetadata(world, -3, 7, 2, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -5, 1, 1, this.brickStairBlock, 0);
        this.setBlockAndMetadata(world, -6, 1, 1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -6, 1, 2, this.brickStairBlock, 7);
        this.setBlockAndMetadata(world, -6, 2, 2, this.brickStairBlock, 2);
        this.setBlockAndMetadata(world, -6, 2, 3, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -5, 2, 3, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, -5, 3, 3, this.brickStairBlock, 1);
        this.setBlockAndMetadata(world, -4, 3, 3, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -4, 3, 2, this.brickStairBlock, 6);
        this.setBlockAndMetadata(world, -4, 4, 2, this.brickStairBlock, 3);
        this.setBlockAndMetadata(world, -4, 4, 1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -5, 4, 1, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, -5, 5, 1, this.fenceBlock, this.fenceMeta);
        for (int k18 = 1; k18 <= 3; ++k18) {
            this.setBlockAndMetadata(world, -3, 4, k18, this.woodBeamBlock, this.woodBeamMeta | 8);
            this.setBlockAndMetadata(world, -2, 5, k18, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -2, 6, k18, this.brickBlock, this.brickMeta);
        }
        this.setBlockAndMetadata(world, -2, 5, 2, this.doorBlock, 2);
        this.setBlockAndMetadata(world, -2, 6, 2, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 0, 7, 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -1, 7, 3, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 1, 7, 3, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -1, 7, 5, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 0, 5, 1, LOTRMod.strawBed, 1);
        this.setBlockAndMetadata(world, 1, 5, 1, LOTRMod.strawBed, 9);
        this.setBlockAndMetadata(world, 1, 5, 2, this.woodBeamBlock, this.woodBeamMeta);
        this.placeMug(world, random, 1, 6, 2, 1, LOTRFoods.DALE_DRINK);
        this.placeChest(world, random, 1, 5, 3, 5, LOTRChestContents.DALE_HOUSE);
        this.spawnItemFrame(world, 2, 7, 1, 3, new ItemStack(Items.clock));
        this.setBlockAndMetadata(world, 1, 5, 4, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 1, 6, 4, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 1, 7, 4, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 7, 4, this.brickStairBlock, 5);
        this.setBlockAndMetadata(world, -1, 7, 4, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, 1, 5, 5, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 1, 5, 6, Blocks.furnace, 5);
        this.setBlockAndMetadata(world, 1, 5, 7, this.brickBlock, this.brickMeta);
        this.placePlateWithCertainty(world, random, 1, 6, 7, this.plateBlock, LOTRFoods.DALE);
        this.setBlockAndMetadata(world, 0, 5, 7, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, -1, 5, 7, LOTRMod.daleTable, 0);
        this.setBlockAndMetadata(world, -1, 7, 7, this.brickStairBlock, 2);
        this.setBlockAndMetadata(world, 0, 7, 7, this.brickStairBlock, 2);
        this.setBlockAndMetadata(world, 1, 7, 7, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 1, 7, 6, this.brickStairBlock, 1);
        this.setBlockAndMetadata(world, 1, 7, 5, this.brickStairBlock, 1);
        this.setBlockAndMetadata(world, -3, 10, 2, LOTRMod.chandelier, 0);
        this.setBlockAndMetadata(world, 0, 10, 5, LOTRMod.chandelier, 0);
        if (random.nextInt(3) == 0) {
            i12 = MathHelper.getRandomIntegerInRange((Random)random, (int)-6, (int)1);
            k14 = MathHelper.getRandomIntegerInRange((Random)random, (int)0, (int)4);
            int chestDir = Direction.directionToFacing[random.nextInt(4)];
            this.placeChest(world, random, i12, 9, k14, chestDir, LOTRChestContents.DALE_HOUSE_TREASURE);
        }
        LOTREntityDaleMan daleMan = new LOTREntityDaleMan(world);
        this.spawnNPCAndSetHome(daleMan, world, -1, 5, 2, 16);
        return true;
    }
}

