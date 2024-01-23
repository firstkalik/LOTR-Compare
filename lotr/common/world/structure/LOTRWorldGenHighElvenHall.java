/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityHighElf;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenHighElvenHall
extends LOTRWorldGenStructureBase {
    private Block plankBlock;
    private int plankMeta;
    private Block slabBlock;
    private int slabMeta;
    private Block stairBlock;
    private Block roofBlock;
    private int roofMeta;
    private Block roofStairBlock;
    protected Block tableBlock = LOTRMod.highElvenTable;
    protected Block plateBlock = LOTRMod.plateBlock;
    protected LOTRItemBanner.BannerType bannerType = LOTRItemBanner.BannerType.HIGH_ELF;
    protected LOTRChestContents chestContents = LOTRChestContents.HIGH_ELVEN_HALL;

    public LOTRWorldGenHighElvenHall(boolean flag) {
        super(flag);
    }

    protected LOTREntityElf createElf(World world) {
        return new LOTREntityHighElf(world);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int k1;
        int k12;
        int l;
        int i1;
        int k13;
        int k14;
        int i12;
        int i13;
        int i14;
        int j12;
        if (this.restrictions && world.getBlock(i, j - 1, k) != Blocks.grass) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                i -= 8;
                ++k;
                break;
            }
            case 1: {
                i -= 16;
                k -= 8;
                break;
            }
            case 2: {
                i -= 7;
                k -= 16;
                break;
            }
            case 3: {
                ++i;
                k -= 7;
            }
        }
        if (this.restrictions) {
            int minHeight = j + 1;
            int maxHeight = j + 1;
            for (i13 = i - 1; i13 <= i + 16; ++i13) {
                for (k13 = k - 1; k13 <= k + 16; ++k13) {
                    j12 = world.getTopSolidOrLiquidBlock(i13, k13);
                    Block block = world.getBlock(i13, j12 - 1, k13);
                    if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.stone) {
                        return false;
                    }
                    if (j12 > maxHeight) {
                        maxHeight = j12;
                    }
                    if (j12 >= minHeight) continue;
                    minHeight = j12;
                }
            }
            if (Math.abs(maxHeight - minHeight) > 5) {
                return false;
            }
            int height = j + 1;
            for (i1 = i - 1; i1 <= i + 16; ++i1) {
                for (k1 = k - 1; k1 <= k + 16; ++k1) {
                    int j13;
                    if (i1 != i - 1 && i1 != i + 16 || k1 != k - 1 && k1 != k + 16 || (j13 = world.getTopSolidOrLiquidBlock(i1, k1)) <= height) continue;
                    height = j13;
                }
            }
            j = height - 1;
        }
        int randomWood = random.nextInt(4);
        switch (randomWood) {
            case 0: {
                this.plankBlock = Blocks.planks;
                this.plankMeta = 0;
                this.slabBlock = Blocks.wooden_slab;
                this.slabMeta = 0;
                this.stairBlock = Blocks.oak_stairs;
                break;
            }
            case 1: {
                this.plankBlock = Blocks.planks;
                this.plankMeta = 2;
                this.slabBlock = Blocks.wooden_slab;
                this.slabMeta = 2;
                this.stairBlock = Blocks.birch_stairs;
                break;
            }
            case 2: {
                this.plankBlock = LOTRMod.planks;
                this.plankMeta = 9;
                this.slabBlock = LOTRMod.woodSlabSingle2;
                this.slabMeta = 1;
                this.stairBlock = LOTRMod.stairsBeech;
                break;
            }
            case 3: {
                this.plankBlock = LOTRMod.planks;
                this.plankMeta = 4;
                this.slabBlock = LOTRMod.woodSlabSingle;
                this.slabMeta = 4;
                this.stairBlock = LOTRMod.stairsApple;
            }
        }
        int randomRoof = random.nextInt(5);
        if (randomRoof == 0) {
            this.roofBlock = LOTRMod.clayTileDyed;
            this.roofMeta = 11;
            this.roofStairBlock = LOTRMod.stairsClayTileDyedBlue;
        } else if (randomRoof == 1) {
            this.roofBlock = LOTRMod.clayTileDyed;
            this.roofMeta = 3;
            this.roofStairBlock = LOTRMod.stairsClayTileDyedLightBlue;
        } else if (randomRoof == 2) {
            this.roofBlock = LOTRMod.clayTileDyed;
            this.roofMeta = 9;
            this.roofStairBlock = LOTRMod.stairsClayTileDyedCyan;
        } else if (randomRoof == 3) {
            this.roofBlock = LOTRMod.clayTileDyed;
            this.roofMeta = 8;
            this.roofStairBlock = LOTRMod.stairsClayTileDyedLightGray;
        } else if (randomRoof == 4) {
            this.roofBlock = LOTRMod.clayTileDyed;
            this.roofMeta = 7;
            this.roofStairBlock = LOTRMod.stairsClayTileDyedGray;
        }
        for (i13 = i; i13 <= i + 15; ++i13) {
            for (k13 = k; k13 <= k + 15; ++k13) {
                for (j12 = j; !(j12 != j && LOTRMod.isOpaque(world, i13, j12, k13) || j12 < 0); --j12) {
                    this.setBlockAndNotifyAdequately(world, i13, j12, k13, LOTRMod.brick3, 2);
                    this.setGrassToDirt(world, i13, j12 - 1, k13);
                }
                for (j12 = j + 1; j12 <= j + 4; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i13, j12, k13, Blocks.air, 0);
                }
                if (i13 < i + 2 || i13 > i + 13 || k13 < k + 2 || k13 > k + 13) {
                    this.setBlockAndNotifyAdequately(world, i13, j + 5, k13, LOTRMod.brick3, 2);
                } else {
                    this.setBlockAndNotifyAdequately(world, i13, j + 5, k13, this.plankBlock, this.plankMeta);
                }
                for (j12 = j + 6; j12 <= j + 9; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i13, j12, k13, Blocks.air, 0);
                }
            }
        }
        for (i13 = i + 1; i13 <= i + 14; ++i13) {
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k, LOTRMod.wall2, 11);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k + 15, LOTRMod.wall2, 11);
        }
        for (int k15 = k + 1; k15 <= k + 14; ++k15) {
            this.setBlockAndNotifyAdequately(world, i, j + 6, k15, LOTRMod.brick3, 2);
            this.setBlockAndNotifyAdequately(world, i, j + 7, k15, LOTRMod.wall2, 11);
            this.setBlockAndNotifyAdequately(world, i + 15, j + 6, k15, LOTRMod.wall2, 11);
        }
        for (int j14 = j; j14 <= j + 5; j14 += 5) {
            int j2;
            for (k13 = k; k13 <= k + 15; k13 += 15) {
                int i15;
                for (i15 = i; i15 <= i + 15; i15 += 3) {
                    for (j2 = j14 + 1; j2 <= j14 + 4; ++j2) {
                        this.setBlockAndNotifyAdequately(world, i15, j2, k13, LOTRMod.pillar, 10);
                    }
                    this.setBlockAndNotifyAdequately(world, i15, j14 + 5, k13, LOTRMod.brick3, 2);
                }
                for (i15 = i + 1; i15 <= i + 13; i15 += 3) {
                    this.setBlockAndNotifyAdequately(world, i15, j14 + 5, k13, LOTRMod.stairsHighElvenBrick, 5);
                    this.setBlockAndNotifyAdequately(world, i15 + 1, j14 + 5, k13, LOTRMod.stairsHighElvenBrick, 4);
                }
            }
            for (i1 = i; i1 <= i + 15; i1 += 15) {
                for (k1 = k + 3; k1 <= k + 12; k1 += 3) {
                    for (j2 = j14 + 1; j2 <= j14 + 4; ++j2) {
                        this.setBlockAndNotifyAdequately(world, i1, j2, k1, LOTRMod.pillar, 10);
                    }
                    this.setBlockAndNotifyAdequately(world, i1, j14 + 5, k1, LOTRMod.brick3, 2);
                }
                for (k1 = k + 1; k1 <= k + 13; k1 += 3) {
                    this.setBlockAndNotifyAdequately(world, i1, j14 + 5, k1, LOTRMod.stairsHighElvenBrick, 7);
                    this.setBlockAndNotifyAdequately(world, i1, j14 + 5, k1 + 1, LOTRMod.stairsHighElvenBrick, 6);
                }
            }
            for (i1 = i; i1 <= i + 15; i1 += 3) {
                this.setBlockAndNotifyAdequately(world, i1, j14 + 4, k + 1, LOTRMod.highElvenTorch, 3);
                this.setBlockAndNotifyAdequately(world, i1, j14 + 4, k + 14, LOTRMod.highElvenTorch, 4);
            }
            for (k13 = k; k13 <= k + 15; k13 += 3) {
                this.setBlockAndNotifyAdequately(world, i + 1, j14 + 4, k13, LOTRMod.highElvenTorch, 1);
                this.setBlockAndNotifyAdequately(world, i + 14, j14 + 4, k13, LOTRMod.highElvenTorch, 2);
            }
        }
        int roofWidth = 18;
        int roofX = i - 1;
        int roofY = j + 11;
        int roofZ = k - 1;
        while (roofWidth > 2) {
            for (i12 = roofX; i12 < roofX + roofWidth; ++i12) {
                this.setBlockAndNotifyAdequately(world, i12, roofY, roofZ, this.roofStairBlock, 2);
                this.setBlockAndNotifyAdequately(world, i12, roofY, roofZ + roofWidth - 1, this.roofStairBlock, 3);
            }
            for (k12 = roofZ; k12 < roofZ + roofWidth; ++k12) {
                this.setBlockAndNotifyAdequately(world, roofX, roofY, k12, this.roofStairBlock, 0);
                this.setBlockAndNotifyAdequately(world, roofX + roofWidth - 1, roofY, k12, this.roofStairBlock, 1);
            }
            for (i12 = roofX + 1; i12 < roofX + roofWidth - 2; ++i12) {
                for (k14 = roofZ + 1; k14 < roofZ + roofWidth - 2; ++k14) {
                    this.setBlockAndNotifyAdequately(world, i12, roofY, k14, Blocks.air, 0);
                }
            }
            for (i12 = roofX + 1; i12 < roofX + roofWidth - 1; ++i12) {
                if (roofWidth > 16) {
                    this.setBlockAndNotifyAdequately(world, i12, roofY, roofZ + 1, this.roofBlock, this.roofMeta);
                    this.setBlockAndNotifyAdequately(world, i12, roofY, roofZ + roofWidth - 2, this.roofBlock, this.roofMeta);
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, i12, roofY, roofZ + 1, this.roofStairBlock, 7);
                this.setBlockAndNotifyAdequately(world, i12, roofY, roofZ + roofWidth - 2, this.roofStairBlock, 6);
            }
            for (k12 = roofZ + 1; k12 < roofZ + roofWidth - 1; ++k12) {
                if (roofWidth > 16) {
                    this.setBlockAndNotifyAdequately(world, roofX + 1, roofY, k12, this.roofBlock, this.roofMeta);
                    this.setBlockAndNotifyAdequately(world, roofX + roofWidth - 2, roofY, k12, this.roofBlock, this.roofMeta);
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, roofX + 1, roofY, k12, this.roofStairBlock, 5);
                this.setBlockAndNotifyAdequately(world, roofX + roofWidth - 2, roofY, k12, this.roofStairBlock, 4);
            }
            roofWidth -= 2;
            ++roofX;
            ++roofY;
            ++roofZ;
        }
        for (i12 = roofX; i12 < roofX + roofWidth; ++i12) {
            for (k14 = roofZ; k14 < roofZ + roofWidth; ++k14) {
                this.setBlockAndNotifyAdequately(world, i12, roofY - 1, k14, LOTRMod.glass, 0);
            }
        }
        this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k + 9, LOTRMod.highElvenBed, 1);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 9, LOTRMod.highElvenBed, 9);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 10, this.plankBlock, this.plankMeta);
        this.placeFlowerPot(world, i + 1, j + 7, k + 10, this.getRandomPlant(random));
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 8, this.plankBlock, this.plankMeta);
        this.placeFlowerPot(world, i + 1, j + 7, k + 8, this.getRandomPlant(random));
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 6, Blocks.bookshelf, 0);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 5, this.plankBlock, this.plankMeta);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 4, this.plankBlock, this.plankMeta);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k + 3, Blocks.bookshelf, 0);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 6, k + 4, this.stairBlock, 0);
        this.placeMug(world, random, i + 1, j + 7, k + 4, 1, LOTRFoods.ELF_DRINK);
        this.setBlockAndNotifyAdequately(world, i + 11, j + 6, k + 10, this.plankBlock, this.plankMeta);
        this.setBlockAndNotifyAdequately(world, i + 11, j + 6, k + 11, this.plankBlock, this.plankMeta);
        for (k12 = k + 10; k12 <= k + 12; ++k12) {
            this.setBlockAndNotifyAdequately(world, i + 13, j + 6, k12, this.stairBlock, 0);
        }
        for (i12 = i + 11; i12 <= i + 13; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j + 6, k + 13, this.stairBlock, 2);
        }
        for (k12 = k + 5; k12 <= k + 9; ++k12) {
            for (i14 = i + 7; i14 <= i + 10; ++i14) {
                this.setBlockAndNotifyAdequately(world, i14, j + 5, k12, Blocks.air, 0);
            }
        }
        for (k12 = k + 5; k12 <= k + 6; ++k12) {
            for (j1 = j + 1; j1 <= j + 4; ++j1) {
                this.setBlockAndNotifyAdequately(world, i + 7, j1, k12, LOTRMod.brick3, 2);
            }
            this.setBlockAndNotifyAdequately(world, i + 7, j + 5, k12, LOTRMod.stairsHighElvenBrick, 1);
            for (i14 = i + 8; i14 <= i + 10; ++i14) {
                for (int j15 = j + 1; j15 <= j + 3; ++j15) {
                    this.setBlockAndNotifyAdequately(world, i14, j15, k12, LOTRMod.brick3, 2);
                }
            }
            this.setBlockAndNotifyAdequately(world, i + 8, j + 4, k12, LOTRMod.stairsHighElvenBrick, 1);
        }
        for (i12 = i + 9; i12 <= i + 10; ++i12) {
            for (j1 = j + 1; j1 <= j + 2; ++j1) {
                this.setBlockAndNotifyAdequately(world, i12, j1, k + 7, LOTRMod.brick3, 2);
            }
            this.setBlockAndNotifyAdequately(world, i12, j + 3, k + 7, LOTRMod.stairsHighElvenBrick, 3);
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k + 8, LOTRMod.brick3, 2);
            this.setBlockAndNotifyAdequately(world, i12, j + 2, k + 8, LOTRMod.stairsHighElvenBrick, 3);
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k + 9, LOTRMod.stairsHighElvenBrick, 3);
        }
        this.setBlockAndNotifyAdequately(world, i + 11, j + 1, k + 5, LOTRMod.pillar, 10);
        this.setBlockAndNotifyAdequately(world, i + 11, j + 1, k + 6, this.tableBlock, 0);
        this.setBlockAndNotifyAdequately(world, i + 11, j + 1, k + 7, LOTRMod.pillar, 10);
        this.placeFlowerPot(world, i + 11, j + 2, k + 5, this.getRandomPlant(random));
        this.placeFlowerPot(world, i + 11, j + 2, k + 7, this.getRandomPlant(random));
        this.setBlockAndNotifyAdequately(world, i + 11, j + 3, k + 6, LOTRMod.highElvenTorch, 1);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 3, k + 6, LOTRMod.highElvenTorch, 2);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 3, k + 7, LOTRMod.highElvenTorch, 3);
        this.setBlockAndNotifyAdequately(world, i + 10, j + 1, k + 4, LOTRMod.pillar, 10);
        this.placeBarrel(world, random, i + 10, j + 2, k + 4, 2, LOTRFoods.ELF_DRINK);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 1, k + 4, LOTRMod.pillar, 10);
        this.placeBarrel(world, random, i + 7, j + 2, k + 4, 2, LOTRFoods.ELF_DRINK);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 1, k + 4, (Block)Blocks.chest, 0);
        this.setBlockAndNotifyAdequately(world, i + 9, j + 1, k + 4, (Block)Blocks.chest, 0);
        LOTRChestContents.fillChest(world, random, i + 8, j + 1, k + 4, this.chestContents);
        LOTRChestContents.fillChest(world, random, i + 9, j + 1, k + 4, this.chestContents);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 2, k + 5, Blocks.furnace, 2);
        this.setBlockAndNotifyAdequately(world, i + 9, j + 2, k + 5, Blocks.furnace, 2);
        this.setBlockMetadata(world, i + 8, j + 2, k + 5, 2);
        this.setBlockMetadata(world, i + 9, j + 2, k + 5, 2);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 1, k + 7, this.plankBlock, this.plankMeta);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 1, k + 7, this.plankBlock, this.plankMeta);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 1, k + 8, this.plankBlock, this.plankMeta);
        this.placePlateWithCertainty(world, random, i + 7, j + 2, k + 7, this.plateBlock, LOTRFoods.ELF);
        this.placePlateWithCertainty(world, random, i + 8, j + 2, k + 7, this.plateBlock, LOTRFoods.ELF);
        this.placePlateWithCertainty(world, random, i + 8, j + 2, k + 8, this.plateBlock, LOTRFoods.ELF);
        for (k12 = k + 6; k12 <= k + 12; ++k12) {
            for (i14 = i + 2; i14 <= i + 4; ++i14) {
                this.setBlockAndNotifyAdequately(world, i14, j + 1, k12, this.slabBlock, this.slabMeta | 8);
            }
        }
        for (k12 = k + 6; k12 <= k + 12; k12 += 3) {
            this.setBlockAndNotifyAdequately(world, i + 2, j + 1, k12, this.plankBlock, this.plankMeta);
            this.setBlockAndNotifyAdequately(world, i + 4, j + 1, k12, this.plankBlock, this.plankMeta);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k12, this.stairBlock, 1);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k12, this.stairBlock, 0);
        }
        this.setBlockAndNotifyAdequately(world, i + 3, j + 1, k + 13, this.stairBlock, 2);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 1, k + 5, this.stairBlock, 3);
        for (k12 = k + 6; k12 <= k + 12; k12 += 2) {
            this.placePlateWithCertainty(world, random, i + 2, j + 2, k12, this.plateBlock, LOTRFoods.ELF);
            this.placePlateWithCertainty(world, random, i + 4, j + 2, k12, this.plateBlock, LOTRFoods.ELF);
        }
        for (k12 = k + 7; k12 <= k + 11; k12 += 2) {
            l = random.nextInt(3);
            if (l == 0) {
                this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k12, LOTRMod.appleCrumble, 0);
            } else if (l == 1) {
                this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k12, LOTRMod.cherryPie, 0);
            } else if (l == 2) {
                this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k12, LOTRMod.berryPie, 0);
            }
            this.placeMug(world, random, i + 2, j + 2, k12, 3, LOTRFoods.ELF_DRINK);
            this.placeMug(world, random, i + 4, j + 2, k12, 1, LOTRFoods.ELF_DRINK);
        }
        this.placeMug(world, random, i + 3, j + 2, k + 6, 0, LOTRFoods.ELF_DRINK);
        this.placeMug(world, random, i + 3, j + 2, k + 12, 2, LOTRFoods.ELF_DRINK);
        this.placeFlowerPot(world, i + 3, j + 2, k + 8, this.getRandomPlant(random));
        this.placeFlowerPot(world, i + 3, j + 2, k + 10, this.getRandomPlant(random));
        for (int j16 = j + 3; j16 <= j + 8; j16 += 5) {
            for (i14 = i + 3; i14 <= i + 12; i14 += 3) {
                this.placeWallBanner(world, i14, j16, k, 0, this.bannerType);
                this.placeWallBanner(world, i14, j16, k + 15, 2, this.bannerType);
            }
            for (k14 = k + 3; k14 <= k + 12; k14 += 3) {
                this.placeWallBanner(world, i, j16, k14, 3, this.bannerType);
                this.placeWallBanner(world, i + 15, j16, k14, 1, this.bannerType);
            }
        }
        int elves = 2 + random.nextInt(4);
        for (l = 0; l < elves; ++l) {
            LOTREntityElf elf = this.createElf(world);
            elf.setLocationAndAngles((double)(i + 6), (double)(j + 6), (double)(k + 6), 0.0f, 0.0f);
            elf.spawnRidingHorse = false;
            elf.onSpawnWithEgg(null);
            elf.isNPCPersistent = true;
            world.spawnEntityInWorld((Entity)elf);
            elf.setHomeArea(i + 7, j + 3, k + 7, 24);
        }
        return true;
    }

    private ItemStack getRandomPlant(Random random) {
        int l = random.nextInt(5);
        switch (l) {
            case 0: {
                return new ItemStack(Blocks.sapling, 1, 0);
            }
            case 1: {
                return new ItemStack(Blocks.sapling, 1, 2);
            }
            case 2: {
                return new ItemStack(Blocks.sapling, 1, 2);
            }
            case 3: {
                return new ItemStack((Block)Blocks.red_flower);
            }
            case 4: {
                return new ItemStack((Block)Blocks.yellow_flower);
            }
        }
        return new ItemStack(Blocks.sapling, 1, 0);
    }
}

