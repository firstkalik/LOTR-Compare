/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockFire
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityEasterling;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenEasterlingStructureTown;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenEasterlingLargeTownHouse
extends LOTRWorldGenEasterlingStructureTown {
    public LOTRWorldGenEasterlingLargeTownHouse(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int i1;
        int j1;
        int k2;
        int k12;
        int k132;
        int i12;
        int j12;
        int k14;
        int l;
        int i2;
        int i132;
        int i14;
        this.setOriginAndRotation(world, i, j, k, rotation, 9);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i15 = -7; i15 <= 7; ++i15) {
                for (k132 = -9; k132 <= 9; ++k132) {
                    j12 = this.getTopBlock(world, i15, k132) - 1;
                    if (!this.isSurface(world, i15, j12, k132)) {
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
        for (i1 = -6; i1 <= 6; ++i1) {
            for (k12 = -8; k12 <= 8; ++k12) {
                i2 = Math.abs(i1);
                k2 = Math.abs(k12);
                if (k2 == 8 && i2 % 4 == 2 || i2 == 6 && k2 % 4 == 0) {
                    for (j12 = 4; !(j12 < 0 && this.isOpaque(world, i1, j12, k12) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i1, j12, k12, this.woodBeamBlock, this.woodBeamMeta);
                        this.setGrassToDirt(world, i1, j12 - 1, k12);
                    }
                    continue;
                }
                if (i2 == 6 || k2 == 8) {
                    for (j12 = 3; !(j12 < 0 && this.isOpaque(world, i1, j12, k12) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i1, j12, k12, this.brickBlock, this.brickMeta);
                        this.setGrassToDirt(world, i1, j12 - 1, k12);
                    }
                    if (k2 == 8) {
                        this.setBlockAndMetadata(world, i1, 4, k12, this.woodBeamBlock, this.woodBeamMeta | 4);
                        continue;
                    }
                    if (i2 != 6) continue;
                    this.setBlockAndMetadata(world, i1, 4, k12, this.woodBeamBlock, this.woodBeamMeta | 8);
                    continue;
                }
                for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i1, j12, k12) || this.getY(j12) < 0); --j12) {
                    if (IntMath.mod((int)i1, (int)2) == 1 && IntMath.mod((int)k12, (int)2) == 1) {
                        this.setBlockAndMetadata(world, i1, j12, k12, this.pillarRedBlock, this.pillarRedMeta);
                    } else {
                        this.setBlockAndMetadata(world, i1, j12, k12, this.brickRedBlock, this.brickRedMeta);
                    }
                    this.setGrassToDirt(world, i1, j12 - 1, k12);
                }
                for (j12 = 1; j12 <= 14; ++j12) {
                    this.setAir(world, i1, j12, k12);
                }
            }
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k12 = -3; k12 <= -1; ++k12) {
                this.setBlockAndMetadata(world, i1, 0, k12, this.brickGoldBlock, this.brickGoldMeta);
            }
        }
        for (int k132 : new int[]{-4, 0, 4}) {
            for (i14 = -5; i14 <= 5; ++i14) {
                this.setBlockAndMetadata(world, i14, 0, k132, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        for (int i132 : new int[]{-2, 2}) {
            for (k14 = -7; k14 <= 7; ++k14) {
                this.setBlockAndMetadata(world, i132, 0, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
        }
        for (int i132 : new int[]{-4, 4}) {
            this.setBlockAndMetadata(world, i132 - 1, 2, -8, this.brickStairBlock, 4);
            this.setAir(world, i132, 2, -8);
            this.setBlockAndMetadata(world, i132 + 1, 2, -8, this.brickStairBlock, 5);
            this.setBlockAndMetadata(world, i132, 3, -8, this.brickStairBlock, 6);
            this.setBlockAndMetadata(world, i132 - 1, 2, 8, this.brickStairBlock, 4);
            this.setAir(world, i132, 2, 8);
            this.setBlockAndMetadata(world, i132 + 1, 2, 8, this.brickStairBlock, 5);
            this.setBlockAndMetadata(world, i132, 3, 8, this.brickStairBlock, 7);
        }
        for (int k132 : new int[]{-6, -2, 2, 6}) {
            this.setBlockAndMetadata(world, -6, 2, k132 - 1, this.brickStairBlock, 7);
            this.setAir(world, -6, 2, k132);
            this.setBlockAndMetadata(world, -6, 2, k132 + 1, this.brickStairBlock, 6);
            this.setBlockAndMetadata(world, -6, 3, k132, this.brickStairBlock, 5);
            this.setBlockAndMetadata(world, 6, 2, k132 - 1, this.brickStairBlock, 7);
            this.setAir(world, 6, 2, k132);
            this.setBlockAndMetadata(world, 6, 2, k132 + 1, this.brickStairBlock, 6);
            this.setBlockAndMetadata(world, 6, 3, k132, this.brickStairBlock, 4);
        }
        for (int k132 : new int[]{-9, 9}) {
            this.setBlockAndMetadata(world, -6, 3, k132, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 6, 3, k132, this.fenceBlock, this.fenceMeta);
        }
        for (int i132 : new int[]{-7, 7}) {
            this.setBlockAndMetadata(world, i132, 3, -8, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i132, 3, 8, this.fenceBlock, this.fenceMeta);
        }
        for (int i132 : new int[]{-2, 2}) {
            this.setBlockAndMetadata(world, i132, 3, -9, Blocks.torch, 4);
            this.setBlockAndMetadata(world, i132, 3, 9, Blocks.torch, 3);
        }
        int[] i16 = new int[]{-4, 0, 4};
        k12 = i16.length;
        for (i2 = 0; i2 < k12; ++i2) {
            k132 = i16[i2];
            this.setBlockAndMetadata(world, -7, 3, k132, Blocks.torch, 1);
            this.setBlockAndMetadata(world, 7, 3, k132, Blocks.torch, 2);
        }
        for (int i17 = -5; i17 <= 5; ++i17) {
            for (k12 = -7; k12 <= 7; ++k12) {
                this.setBlockAndMetadata(world, i17, 4, k12, this.plankSlabBlock, this.plankSlabMeta | 8);
            }
        }
        for (int k132 : new int[]{-4, 4}) {
            for (i14 = -5; i14 <= 5; ++i14) {
                this.setBlockAndMetadata(world, i14, 4, k132, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        for (int i132 : new int[]{-2, 2}) {
            for (k14 = -7; k14 <= 7; ++k14) {
                if (k14 > -5 && k14 < 5) continue;
                this.setBlockAndMetadata(world, i132, 4, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
        }
        int[] i17 = new int[]{-2, 2};
        k12 = i17.length;
        for (i2 = 0; i2 < k12; ++i2) {
            i132 = i17[i2];
            for (j12 = 1; j12 <= 3; ++j12) {
                this.setBlockAndMetadata(world, i132, j12, -4, this.woodBeamBlock, this.woodBeamMeta);
            }
            for (j12 = 1; j12 <= 4; ++j12) {
                this.setBlockAndMetadata(world, i132, j12, 0, this.woodBeamBlock, this.woodBeamMeta);
            }
        }
        for (int l2 = 0; l2 <= 4; ++l2) {
            int j13 = 1 + l2;
            int k15 = -1 + l2;
            for (i132 = -1; i132 <= 1; ++i132) {
                this.setAir(world, i132, 4, k15);
                if (l2 <= 3) {
                    this.setBlockAndMetadata(world, i132, j13, k15, this.plankStairBlock, 2);
                }
                for (int j2 = j13 - 1; j2 >= 1; --j2) {
                    this.setBlockAndMetadata(world, i132, j2, k15, this.plankBlock, this.plankMeta);
                }
            }
        }
        for (int k16 = -1; k16 <= 3; ++k16) {
            this.setBlockAndMetadata(world, -2, 5, k16, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 2, 5, k16, this.fenceBlock, this.fenceMeta);
        }
        for (int i18 = -2; i18 <= 2; ++i18) {
            this.setBlockAndMetadata(world, i18, 5, -2, this.fenceBlock, this.fenceMeta);
        }
        for (int i132 : new int[]{-2, 2}) {
            for (int k17 : new int[]{0, 4}) {
                this.setBlockAndMetadata(world, i132, 5, k17, this.woodBeamBlock, this.woodBeamMeta);
                this.setBlockAndMetadata(world, i132, 6, k17, this.plankSlabBlock, this.plankSlabMeta);
            }
        }
        for (int i19 = -5; i19 <= 5; ++i19) {
            for (k12 = -7; k12 <= 7; ++k12) {
                i2 = Math.abs(i19);
                k2 = Math.abs(k12);
                if (i2 == 5 && (k2 == 0 || k2 == 4 || k2 == 7) || k2 == 7 && i2 == 2) {
                    for (j12 = 5; j12 <= 8; ++j12) {
                        this.setBlockAndMetadata(world, i19, j12, k12, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    continue;
                }
                if (i2 != 5 && k2 != 7) continue;
                for (j12 = 5; j12 <= 7; ++j12) {
                    this.setBlockAndMetadata(world, i19, j12, k12, this.brickBlock, this.brickMeta);
                }
                if (k2 == 7) {
                    this.setBlockAndMetadata(world, i19, 8, k12, this.woodBeamBlock, this.woodBeamMeta | 4);
                    continue;
                }
                if (i2 != 5) continue;
                this.setBlockAndMetadata(world, i19, 8, k12, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, 0, 6, -7, LOTRMod.reedBars, 0);
        for (int i132 : new int[]{-5, 5}) {
            this.setBlockAndMetadata(world, i132, 6, -2, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, i132, 6, 2, LOTRMod.reedBars, 0);
        }
        for (int k132 : new int[]{-8, 8}) {
            this.setBlockAndMetadata(world, -5, 7, k132, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, 5, 7, k132, this.fenceBlock, this.fenceMeta);
        }
        for (int i132 : new int[]{-6, 6}) {
            this.setBlockAndMetadata(world, i132, 7, -7, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i132, 7, 7, this.fenceBlock, this.fenceMeta);
        }
        for (int i132 : new int[]{-2, 2}) {
            this.setBlockAndMetadata(world, i132, 7, -8, Blocks.torch, 4);
            this.setBlockAndMetadata(world, i132, 7, 8, Blocks.torch, 3);
        }
        for (int k132 : new int[]{-4, 0, 4}) {
            this.setBlockAndMetadata(world, -6, 7, k132, Blocks.torch, 1);
            this.setBlockAndMetadata(world, 6, 7, k132, Blocks.torch, 2);
        }
        for (int i110 = -6; i110 <= 6; ++i110) {
            this.setBlockAndMetadata(world, i110, 5, -8, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i110, 5, 8, this.roofStairBlock, 3);
        }
        for (int k18 = -7; k18 <= 7; ++k18) {
            this.setBlockAndMetadata(world, -6, 5, k18, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 6, 5, k18, this.roofStairBlock, 0);
        }
        for (int k132 : new int[]{-9, 9}) {
            this.setBlockAndMetadata(world, -7, 5, k132, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, -6, 4, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, -5, 4, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, -4, 5, k132, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, -3, 4, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, -2, 4, k132, this.roofStairBlock, k132 > 0 ? 3 : 2);
            this.setBlockAndMetadata(world, -1, 4, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, 0, 5, k132, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, 1, 4, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, 2, 4, k132, this.roofStairBlock, k132 > 0 ? 3 : 2);
            this.setBlockAndMetadata(world, 3, 4, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, 4, 5, k132, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, 5, 4, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, 6, 4, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, 7, 5, k132, this.roofSlabBlock, this.roofSlabMeta);
        }
        for (int i132 : new int[]{-7, 7}) {
            for (int k17 : new int[]{-4, 0, 4}) {
                this.setBlockAndMetadata(world, i132, 4, k17 - 1, this.roofStairBlock, 6);
                this.setBlockAndMetadata(world, i132, 4, k17, this.roofStairBlock, i132 > 0 ? 0 : 1);
                this.setBlockAndMetadata(world, i132, 4, k17 + 1, this.roofStairBlock, 7);
            }
            for (int k17 : new int[]{-6, -2, 2, 6}) {
                this.setBlockAndMetadata(world, i132, 5, k17, this.roofSlabBlock, this.roofSlabMeta);
            }
            this.setBlockAndMetadata(world, i132, 4, -8, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, i132, 4, -7, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i132, 4, 7, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, i132, 4, 8, this.roofStairBlock, 7);
        }
        for (int k132 : new int[]{-8, 8}) {
            this.setBlockAndMetadata(world, -6, 9, k132, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, -5, 8, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, -4, 8, k132, this.roofStairBlock, k132 > 0 ? 3 : 2);
            this.setBlockAndMetadata(world, -3, 8, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, -2, 8, k132, this.roofStairBlock, k132 > 0 ? 3 : 2);
            this.setBlockAndMetadata(world, -1, 8, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, 0, 9, k132, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, 1, 8, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, 2, 8, k132, this.roofStairBlock, k132 > 0 ? 3 : 2);
            this.setBlockAndMetadata(world, 3, 8, k132, this.roofStairBlock, 5);
            this.setBlockAndMetadata(world, 4, 8, k132, this.roofStairBlock, k132 > 0 ? 3 : 2);
            this.setBlockAndMetadata(world, 5, 8, k132, this.roofStairBlock, 4);
            this.setBlockAndMetadata(world, 6, 9, k132, this.roofSlabBlock, this.roofSlabMeta);
        }
        int[] k18 = new int[]{-6, 6};
        k12 = k18.length;
        for (i2 = 0; i2 < k12; ++i2) {
            i132 = k18[i2];
            this.setBlockAndMetadata(world, i132, 8, -7, this.roofStairBlock, 6);
            for (k14 = -6; k14 <= -4; ++k14) {
                this.setBlockAndMetadata(world, i132, 8, k14, this.roofStairBlock, i132 > 0 ? 0 : 1);
            }
            this.setBlockAndMetadata(world, i132, 8, -3, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i132, 9, -2, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i132, 8, -1, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, i132, 9, 0, this.roofStairBlock, i132 > 0 ? 0 : 1);
            this.setBlockAndMetadata(world, i132, 8, 1, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i132, 9, 2, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i132, 8, 3, this.roofStairBlock, 6);
            for (k14 = 4; k14 <= 6; ++k14) {
                this.setBlockAndMetadata(world, i132, 8, k14, this.roofStairBlock, i132 > 0 ? 0 : 1);
            }
            this.setBlockAndMetadata(world, i132, 8, 7, this.roofStairBlock, 7);
        }
        for (int k19 = -7; k19 <= 7; ++k19) {
            for (l = 0; l <= 4; ++l) {
                j1 = 9 + l;
                this.setBlockAndMetadata(world, -5 + l, j1, k19, this.roofStairBlock, 1);
                this.setBlockAndMetadata(world, 5 - l, j1, k19, this.roofStairBlock, 0);
                if (l <= 0) continue;
                this.setBlockAndMetadata(world, -5 + l, j1 - 1, k19, this.roofStairBlock, 4);
                this.setBlockAndMetadata(world, 5 - l, j1 - 1, k19, this.roofStairBlock, 5);
            }
            this.setBlockAndMetadata(world, 0, 13, k19, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, 0, 14, k19, this.roofSlabBlock, this.roofSlabMeta);
        }
        this.setBlockAndMetadata(world, 0, 13, -8, this.roofStairBlock, 6);
        this.setBlockAndMetadata(world, 0, 14, -8, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 0, 13, 8, this.roofStairBlock, 7);
        this.setBlockAndMetadata(world, 0, 14, 8, this.roofStairBlock, 2);
        for (int k132 : new int[]{-7, 7}) {
            for (int i111 : new int[]{-2, 2}) {
                for (int j14 = 5; j14 <= 11; ++j14) {
                    this.setBlockAndMetadata(world, i111, j14, k132, this.woodBeamBlock, this.woodBeamMeta);
                }
            }
        }
        for (int i112 = -4; i112 <= 4; ++i112) {
            this.setBlockAndMetadata(world, i112, 8, -6, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i112, 8, 6, this.plankStairBlock, 6);
        }
        for (int k132 : new int[]{-6, 6}) {
            for (int l3 = 0; l3 <= 3; ++l3) {
                int j15 = 9 + l3;
                for (int i113 = -4 + l3; i113 <= 4 - l3; ++i113) {
                    this.setBlockAndMetadata(world, i113, j15, k132, this.plankBlock, this.plankMeta);
                }
            }
        }
        for (int k132 : new int[]{-4, 4}) {
            for (i14 = -4; i14 <= 4; ++i14) {
                this.setBlockAndMetadata(world, i14, 8, k132, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        int[] i112 = new int[]{-2, 2};
        l = i112.length;
        for (j1 = 0; j1 < l; ++j1) {
            i132 = i112[j1];
            for (k14 = -6; k14 <= 6; ++k14) {
                this.setBlockAndMetadata(world, i132, 8, k14, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, 0, 0, -8, this.woodBeamBlock, this.woodBeamMeta | 4);
        this.setBlockAndMetadata(world, 0, 1, -8, this.doorBlock, 1);
        this.setBlockAndMetadata(world, 0, 2, -8, this.doorBlock, 8);
        this.setBlockAndMetadata(world, -2, 3, -5, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 3, -5, Blocks.torch, 4);
        this.spawnItemFrame(world, -2, 2, -4, 1, this.getEasterlingFramedItem(random));
        this.spawnItemFrame(world, 2, 2, -4, 3, this.getEasterlingFramedItem(random));
        this.setBlockAndMetadata(world, -3, 1, -6, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 3, 1, -6, this.plankStairBlock, 0);
        for (k1 = -7; k1 <= -5; ++k1) {
            this.setBlockAndMetadata(world, -5, 1, k1, this.plankStairBlock, 4);
            if (random.nextBoolean()) {
                this.placePlate(world, random, -5, 2, k1, this.plateBlock, LOTRFoods.RHUN);
            } else {
                this.placeMug(world, random, -5, 2, k1, 3, LOTRFoods.RHUN_DRINK);
            }
            this.setBlockAndMetadata(world, 5, 1, k1, this.plankStairBlock, 5);
            if (random.nextBoolean()) {
                this.placePlate(world, random, 5, 2, k1, this.plateBlock, LOTRFoods.RHUN);
                continue;
            }
            this.placeMug(world, random, 5, 2, k1, 1, LOTRFoods.RHUN_DRINK);
        }
        this.spawnItemFrame(world, -6, 2, 0, 1, this.getEasterlingFramedItem(random));
        this.spawnItemFrame(world, 6, 2, 0, 3, this.getEasterlingFramedItem(random));
        this.setBlockAndMetadata(world, -2, 1, 1, (Block)Blocks.chest, 5);
        this.setBlockAndMetadata(world, -2, 1, 2, (Block)Blocks.chest, 5);
        this.setBlockAndMetadata(world, 2, 1, 1, (Block)Blocks.chest, 4);
        this.setBlockAndMetadata(world, 2, 1, 2, (Block)Blocks.chest, 4);
        this.setBlockAndMetadata(world, -2, 1, 3, this.plankStairBlock, 5);
        this.placeBarrel(world, random, -2, 2, 3, 5, LOTRFoods.RHUN_DRINK);
        this.setBlockAndMetadata(world, 2, 1, 3, this.plankStairBlock, 4);
        this.placeBarrel(world, random, 2, 2, 3, 4, LOTRFoods.RHUN_DRINK);
        this.setBlockAndMetadata(world, -2, 3, 4, LOTRMod.chandelier, 3);
        this.setBlockAndMetadata(world, 2, 3, 4, LOTRMod.chandelier, 3);
        this.setBlockAndMetadata(world, 0, 1, 3, this.tableBlock, 0);
        this.setAir(world, 0, 2, 3);
        this.setBlockAndMetadata(world, 0, 3, 3, this.plankStairBlock, 7);
        for (i12 = -1; i12 <= 1; ++i12) {
            for (k12 = 5; k12 <= 7; ++k12) {
                this.setBlockAndMetadata(world, i12, 0, k12, this.brickBlock, this.brickMeta);
            }
            for (k12 = 7; k12 <= 8; ++k12) {
                for (j1 = 1; j1 <= 4; ++j1) {
                    this.setBlockAndMetadata(world, i12, j1, k12, this.brickBlock, this.brickMeta);
                }
            }
            k12 = 9;
            for (j1 = 4; !(j1 < 0 && this.isOpaque(world, i12, j1, k12) || this.getY(j1) < 0); --j1) {
                this.setBlockAndMetadata(world, i12, j1, k12, this.brickBlock, this.brickMeta);
                this.setGrassToDirt(world, i12, j1 - 1, k12);
            }
            this.setBlockAndMetadata(world, i12, 5, 9, this.brickStairBlock, 3);
            this.setBlockAndMetadata(world, i12, 4, 8, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, i12, 5, 8, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, i12, 6, 8, this.brickStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -1, 1, 7, this.brickStairBlock, 1);
        this.setBlockAndMetadata(world, -1, 2, 7, this.brickStairBlock, 5);
        this.setBlockAndMetadata(world, -1, 3, 7, this.brickStairBlock, 5);
        this.setBlockAndMetadata(world, 1, 1, 7, this.brickStairBlock, 0);
        this.setBlockAndMetadata(world, 1, 2, 7, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, 1, 3, 7, this.brickStairBlock, 4);
        this.setBlockAndMetadata(world, 0, 0, 7, LOTRMod.hearth, 0);
        this.setBlockAndMetadata(world, 0, 1, 7, this.barsBlock, 0);
        this.setBlockAndMetadata(world, 0, 2, 7, Blocks.furnace, 2);
        this.setBlockAndMetadata(world, 0, 0, 8, LOTRMod.hearth, 0);
        this.setBlockAndMetadata(world, 0, 1, 8, (Block)Blocks.fire, 0);
        this.setAir(world, 0, 2, 8);
        this.setBlockAndMetadata(world, -5, 1, 5, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, -5, 1, 6, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -5, 1, 7, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, -4, 1, 7, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, -3, 1, 7, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, 5, 1, 5, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 5, 1, 6, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 5, 1, 7, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 4, 1, 7, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, 3, 1, 7, this.plankStairBlock, 6);
        for (i12 = 3; i12 <= 5; ++i12) {
            if (random.nextBoolean()) {
                this.placePlate(world, random, i12, 2, 7, this.plateBlock, LOTRFoods.RHUN);
                continue;
            }
            this.placeMug(world, random, i12, 2, 7, 0, LOTRFoods.RHUN_DRINK);
        }
        for (k1 = 5; k1 <= 6; ++k1) {
            if (random.nextBoolean()) {
                this.placePlate(world, random, 5, 2, k1, this.plateBlock, LOTRFoods.RHUN);
                continue;
            }
            this.placeMug(world, random, 5, 2, k1, 1, LOTRFoods.RHUN_DRINK);
        }
        this.placeWallBanner(world, 0, 7, 7, this.bannerType, 2);
        this.setBlockAndMetadata(world, -2, 7, 4, LOTRMod.chandelier, 3);
        this.setBlockAndMetadata(world, 2, 7, 4, LOTRMod.chandelier, 3);
        this.placeArmorStand(world, -4, 5, 6, 0, null);
        this.placeArmorStand(world, 4, 5, 6, 0, null);
        this.setBlockAndMetadata(world, -2, 7, -4, LOTRMod.chandelier, 3);
        this.setBlockAndMetadata(world, 2, 7, -4, LOTRMod.chandelier, 3);
        for (int i132 : new int[]{-4, 0, 4}) {
            this.setBlockAndMetadata(world, i132, 5, -5, this.bedBlock, 2);
            this.setBlockAndMetadata(world, i132, 5, -6, this.bedBlock, 10);
        }
        for (int i132 : new int[]{-2, 2}) {
            this.placeChest(world, random, i132, 5, -6, 3, this.chestContents);
        }
        for (int i132 : new int[]{-3, -1, 1, 3}) {
            this.setBlockAndMetadata(world, i132, 5, -6, this.plankStairBlock, 7);
            if (random.nextBoolean()) {
                this.placePlate(world, random, i132, 6, -6, this.plateBlock, LOTRFoods.RHUN);
                continue;
            }
            this.placeMug(world, random, i132, 6, -6, 2, LOTRFoods.RHUN_DRINK);
        }
        this.placeWeaponRack(world, 0, 7, -6, 4, this.getEasterlingWeaponItem(random));
        if (random.nextBoolean()) {
            this.spawnItemFrame(world, -2, 7, -7, 0, new ItemStack(Items.clock));
        } else {
            this.spawnItemFrame(world, 2, 7, -7, 0, new ItemStack(Items.clock));
        }
        int men = 2;
        for (l = 0; l < men; ++l) {
            LOTREntityEasterling easterling = new LOTREntityEasterling(world);
            this.spawnNPCAndSetHome(easterling, world, 0, 5, 3, 16);
        }
        return true;
    }
}

