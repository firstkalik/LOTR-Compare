/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityGaladhrimElf;
import lotr.common.world.feature.LOTRWorldGenMallornExtreme;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenElfHouse
extends LOTRWorldGenStructureBase2 {
    public LOTRWorldGenElfHouse(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k12;
        int k13;
        int i2;
        int k1;
        int k14;
        int j1;
        int i1;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, this.usingPlayer != null ? 2 : 0);
        if (this.usingPlayer != null) {
            LOTRWorldGenMallornExtreme treeGen = new LOTRWorldGenMallornExtreme(true);
            int i12 = 0;
            j1 = 0;
            k12 = 0;
            int height = treeGen.generateAndReturnHeight(world, random, this.getX(i12, k12), this.getY(j1), this.getZ(i12, k12), true);
            this.originY += MathHelper.floor_double((double)((float)height * MathHelper.randomFloatClamp((Random)random, (float)LOTRWorldGenMallornExtreme.HOUSE_HEIGHT_MIN, (float)LOTRWorldGenMallornExtreme.HOUSE_HEIGHT_MAX)));
        }
        if (this.restrictions) {
            for (int i13 = -8; i13 <= 8; ++i13) {
                for (j12 = -3; j12 <= 6; ++j12) {
                    for (k14 = -8; k14 <= 8; ++k14) {
                        if (Math.abs(i13) <= 2 && Math.abs(k14) <= 2 || this.isAir(world, i13, j12, k14)) continue;
                        return false;
                    }
                }
            }
        } else if (this.usingPlayer != null) {
            for (int i14 = -2; i14 <= 2; ++i14) {
                for (k13 = -2; k13 <= 2; ++k13) {
                    j1 = 0;
                    while (!this.isOpaque(world, i14, j1, k13) && this.getY(j1) >= 0) {
                        this.setBlockAndMetadata(world, i14, j1, k13, LOTRMod.wood, 1);
                        --j1;
                    }
                }
            }
        }
        for (i1 = -7; i1 <= 7; ++i1) {
            for (j12 = 1; j12 <= 4; ++j12) {
                for (k14 = -7; k14 <= 7; ++k14) {
                    this.setBlockAndMetadata(world, i1, j12, k14, Blocks.air, 0);
                }
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (j12 = -1; j12 <= 5; ++j12) {
                for (k14 = -2; k14 <= 2; ++k14) {
                    this.setBlockAndMetadata(world, i1, j12, k14, LOTRMod.wood, 1);
                    if (j12 < 1 || j12 > 2 || Math.abs(i1) != 2 || Math.abs(k14) != 2) continue;
                    this.setBlockAndMetadata(world, i1, j12, k14, LOTRMod.fence, 1);
                }
            }
        }
        for (i1 = -6; i1 <= 6; ++i1) {
            for (k13 = -6; k13 <= 6; ++k13) {
                if (Math.abs(i1) <= 2 && Math.abs(k13) <= 2 || Math.abs(i1) == 6 || Math.abs(k13) == 6) continue;
                this.setBlockAndMetadata(world, i1, 0, k13, LOTRMod.planks, 1);
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, -6, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, i1, 0, 6, LOTRMod.planks, 1);
        }
        for (k1 = -5; k1 <= 5; ++k1) {
            this.setBlockAndMetadata(world, -6, 0, k1, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, 6, 0, k1, LOTRMod.planks, 1);
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, -7, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, i1, 0, 7, LOTRMod.planks, 1);
        }
        for (k1 = -3; k1 <= 3; ++k1) {
            this.setBlockAndMetadata(world, -7, 0, k1, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, 7, 0, k1, LOTRMod.planks, 1);
        }
        for (int j13 = 1; j13 <= 4; ++j13) {
            this.setBlockAndMetadata(world, -5, j13, -5, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, 5, j13, -5, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, -5, j13, 5, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, 5, j13, 5, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, -6, j13, -3, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, -6, j13, 3, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, 6, j13, -3, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, 6, j13, 3, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, -3, j13, -6, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, -3, j13, 6, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, 3, j13, -6, LOTRMod.wood, 1);
            this.setBlockAndMetadata(world, 3, j13, 6, LOTRMod.wood, 1);
        }
        this.setBlockAndMetadata(world, -4, 2, -5, LOTRWorldGenElfHouse.getRandomTorch(random), 2);
        this.setBlockAndMetadata(world, -5, 2, -4, LOTRWorldGenElfHouse.getRandomTorch(random), 3);
        this.setBlockAndMetadata(world, 4, 2, -5, LOTRWorldGenElfHouse.getRandomTorch(random), 1);
        this.setBlockAndMetadata(world, 5, 2, -4, LOTRWorldGenElfHouse.getRandomTorch(random), 3);
        this.setBlockAndMetadata(world, -4, 2, 5, LOTRWorldGenElfHouse.getRandomTorch(random), 2);
        this.setBlockAndMetadata(world, -5, 2, 4, LOTRWorldGenElfHouse.getRandomTorch(random), 4);
        this.setBlockAndMetadata(world, 4, 2, 5, LOTRWorldGenElfHouse.getRandomTorch(random), 1);
        this.setBlockAndMetadata(world, 5, 2, 4, LOTRWorldGenElfHouse.getRandomTorch(random), 4);
        for (i1 = -3; i1 <= 3; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, -7, LOTRMod.fence, 1);
            this.setBlockAndMetadata(world, i1, 1, 7, LOTRMod.fence, 1);
        }
        for (k1 = -3; k1 <= 3; ++k1) {
            this.setBlockAndMetadata(world, -7, 1, k1, LOTRMod.fence, 1);
            this.setBlockAndMetadata(world, 7, 1, k1, LOTRMod.fence, 1);
        }
        this.setBlockAndMetadata(world, -4, 1, -6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -5, 1, -6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -4, 1, 6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -5, 1, 6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 4, 1, -6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 5, 1, -6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 4, 1, 6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 5, 1, 6, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -6, 1, -4, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -6, 1, -5, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 6, 1, -4, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 6, 1, -5, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -6, 1, 4, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -6, 1, 5, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 6, 1, 4, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, 6, 1, 5, LOTRMod.fence, 1);
        this.setBlockAndMetadata(world, -6, 4, -2, LOTRMod.stairsMallorn, 7);
        this.setBlockAndMetadata(world, -6, 4, -1, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, -6, 4, 0, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, -6, 4, 1, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, -6, 4, 2, LOTRMod.stairsMallorn, 6);
        this.setBlockAndMetadata(world, 6, 4, -2, LOTRMod.stairsMallorn, 7);
        this.setBlockAndMetadata(world, 6, 4, -1, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 6, 4, 0, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 6, 4, 1, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 6, 4, 2, LOTRMod.stairsMallorn, 6);
        this.setBlockAndMetadata(world, -2, 4, -6, LOTRMod.stairsMallorn, 4);
        this.setBlockAndMetadata(world, -1, 4, -6, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 0, 4, -6, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 1, 4, -6, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 2, 4, -6, LOTRMod.stairsMallorn, 5);
        this.setBlockAndMetadata(world, -2, 4, 6, LOTRMod.stairsMallorn, 4);
        this.setBlockAndMetadata(world, -1, 4, 6, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 0, 4, 6, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 1, 4, 6, LOTRMod.woodSlabSingle, 9);
        this.setBlockAndMetadata(world, 2, 4, 6, LOTRMod.stairsMallorn, 5);
        for (i1 = -6; i1 <= -4; ++i1) {
            this.setBlockAndMetadata(world, i1, 4, -6, LOTRMod.stairsMallorn, 6);
            this.setBlockAndMetadata(world, i1, 4, 6, LOTRMod.stairsMallorn, 7);
        }
        for (i1 = 4; i1 <= 6; ++i1) {
            this.setBlockAndMetadata(world, i1, 4, -6, LOTRMod.stairsMallorn, 6);
            this.setBlockAndMetadata(world, i1, 4, 6, LOTRMod.stairsMallorn, 7);
        }
        for (k1 = -6; k1 <= -4; ++k1) {
            this.setBlockAndMetadata(world, -6, 4, k1, LOTRMod.stairsMallorn, 5);
            this.setBlockAndMetadata(world, 6, 4, k1, LOTRMod.stairsMallorn, 4);
        }
        for (k1 = 4; k1 <= 6; ++k1) {
            this.setBlockAndMetadata(world, -6, 4, k1, LOTRMod.stairsMallorn, 5);
            this.setBlockAndMetadata(world, 6, 4, k1, LOTRMod.stairsMallorn, 4);
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            if (Math.abs(i1) <= 1) continue;
            this.setBlockAndMetadata(world, i1, 4, -5, LOTRMod.stairsMallorn, 7);
            this.setBlockAndMetadata(world, i1, 4, 5, LOTRMod.stairsMallorn, 6);
        }
        for (k1 = -4; k1 <= 4; ++k1) {
            if (Math.abs(k1) <= 1) continue;
            this.setBlockAndMetadata(world, -5, 4, k1, LOTRMod.stairsMallorn, 4);
            this.setBlockAndMetadata(world, 5, 4, k1, LOTRMod.stairsMallorn, 5);
        }
        for (i1 = -6; i1 <= 6; ++i1) {
            for (k13 = -6; k13 <= 6; ++k13) {
                if (this.restrictions && i1 >= -2 && i1 <= 2 && k13 >= -2 && k13 <= 2 || (i1 == -6 || i1 == 6) && (k13 == -6 || k13 == 6)) continue;
                this.setBlockAndMetadata(world, i1, 5, k13, LOTRMod.planks, 1);
            }
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            this.setBlockAndMetadata(world, i1, 5, -7, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, i1, 5, 7, LOTRMod.planks, 1);
        }
        for (k1 = -3; k1 <= 3; ++k1) {
            this.setBlockAndMetadata(world, -7, 5, k1, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, 7, 5, k1, LOTRMod.planks, 1);
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (k13 = -5; k13 <= 5; ++k13) {
                if (this.restrictions && i1 >= -2 && i1 <= 2 && k13 >= -2 && k13 <= 2 || (i1 == -5 || i1 == 5) && (k13 == -5 || k13 == 5)) continue;
                this.setBlockAndMetadata(world, i1, 6, k13, LOTRMod.planks, 1);
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            this.setBlockAndMetadata(world, i1, 6, -6, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, i1, 6, 6, LOTRMod.planks, 1);
        }
        for (k1 = -2; k1 <= 2; ++k1) {
            this.setBlockAndMetadata(world, -6, 6, k1, LOTRMod.planks, 1);
            this.setBlockAndMetadata(world, 6, 6, k1, LOTRMod.planks, 1);
        }
        for (i1 = -8; i1 <= 8; ++i1) {
            int stairZ = 0;
            int stairX = i1;
            i2 = Math.abs(i1);
            stairZ = i2 <= 3 ? 8 : (i2 <= 5 ? 7 : (i2 <= 7 ? 6 : 4));
            this.setBlockAndMetadata(world, stairX, 5, -stairZ, LOTRMod.stairsMallorn, 2);
            this.setBlockAndMetadata(world, stairX, 5, stairZ, LOTRMod.stairsMallorn, 3);
            stairX = Integer.signum(stairX) * (Math.abs(stairX) - 1);
            this.setBlockAndMetadata(world, stairX, 6, -(--stairZ), LOTRMod.stairsMallorn, 2);
            this.setBlockAndMetadata(world, stairX, 6, stairZ, LOTRMod.stairsMallorn, 3);
        }
        for (k1 = -8; k1 <= 8; ++k1) {
            int stairX = 0;
            int stairZ = k1;
            int k2 = Math.abs(k1);
            stairX = k2 <= 3 ? 8 : (k2 <= 5 ? 7 : (k2 <= 7 ? 6 : 4));
            this.setBlockAndMetadata(world, -stairX, 5, stairZ, LOTRMod.stairsMallorn, 1);
            this.setBlockAndMetadata(world, stairX, 5, stairZ, LOTRMod.stairsMallorn, 0);
            stairZ = Integer.signum(stairZ) * (Math.abs(stairZ) - 1);
            this.setBlockAndMetadata(world, -(--stairX), 6, stairZ, LOTRMod.stairsMallorn, 1);
            this.setBlockAndMetadata(world, stairX, 6, stairZ, LOTRMod.stairsMallorn, 0);
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            this.setBlockAndMetadata(world, i1, 4, -3, LOTRMod.stairsMallorn, 6);
            this.setBlockAndMetadata(world, i1, 4, 3, LOTRMod.stairsMallorn, 7);
        }
        for (k1 = -2; k1 <= 2; ++k1) {
            this.setBlockAndMetadata(world, -3, 4, k1, LOTRMod.stairsMallorn, 5);
            this.setBlockAndMetadata(world, 3, 4, k1, LOTRMod.stairsMallorn, 4);
        }
        for (int bough = 0; bough <= 2; ++bough) {
            j12 = -3 + bough;
            int i15 = 0 + bough;
            k12 = 3 + bough;
            for (i2 = -i15; i2 <= i15; ++i2) {
                for (int k2 = -k12; k2 <= k12; ++k2) {
                    this.setBlockAndMetadata(world, i2, j12, k2, LOTRMod.wood, 13);
                    this.setBlockAndMetadata(world, k2, j12, i2, LOTRMod.wood, 13);
                }
            }
        }
        Block ladder = random.nextBoolean() ? LOTRMod.hithlainLadder : LOTRMod.mallornLadder;
        for (j12 = 3; j12 >= -3 || !this.isOpaque(world, 0, j12, -3) && this.getY(j12) >= 0; --j12) {
            this.setBlockAndMetadata(world, 0, j12, -3, ladder, 2);
        }
        this.setBlockAndMetadata(world, -2, 1, 0, LOTRMod.elvenTable, 0);
        this.setBlockAndMetadata(world, -2, 2, 0, Blocks.air, 0);
        this.setBlockAndMetadata(world, -2, 3, 0, Blocks.air, 0);
        this.setBlockAndMetadata(world, -2, 4, 0, LOTRMod.wood, 5);
        this.setBlockAndMetadata(world, 2, 1, 0, LOTRMod.elvenTable, 0);
        this.setBlockAndMetadata(world, 2, 2, 0, Blocks.air, 0);
        this.setBlockAndMetadata(world, 2, 3, 0, Blocks.air, 0);
        this.setBlockAndMetadata(world, 2, 4, 0, LOTRMod.wood, 5);
        this.placeChest(world, random, 0, 1, 2, LOTRMod.chestMallorn, 0, LOTRChestContents.ELF_HOUSE);
        this.setBlockAndMetadata(world, 0, 2, 2, Blocks.air, 0);
        this.setBlockAndMetadata(world, 0, 3, 2, Blocks.air, 0);
        this.setBlockAndMetadata(world, 0, 4, 2, LOTRMod.wood, 9);
        this.tryPlaceLight(world, -7, -1, -3, random);
        this.tryPlaceLight(world, -7, -1, 3, random);
        this.tryPlaceLight(world, 7, -1, -3, random);
        this.tryPlaceLight(world, 7, -1, 3, random);
        this.tryPlaceLight(world, -3, -1, -7, random);
        this.tryPlaceLight(world, 3, -1, -7, random);
        this.tryPlaceLight(world, -3, -1, 7, random);
        this.tryPlaceLight(world, 3, -1, 7, random);
        this.placeFlowerPot(world, -4, 1, -5, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, -5, 1, -4, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, -5, 1, 4, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, -4, 1, 5, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, 4, 1, -5, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, 5, 1, -4, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, 5, 1, 4, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.placeFlowerPot(world, 4, 1, 5, LOTRWorldGenElfHouse.getRandomPlant(random));
        this.setBlockAndMetadata(world, -2, 1, 5, LOTRMod.elvenBed, 3);
        this.setBlockAndMetadata(world, -3, 1, 5, LOTRMod.elvenBed, 11);
        LOTREntityGaladhrimElf elf = new LOTREntityGaladhrimElf(world);
        elf.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(elf, world, 0, 1, 4, 8);
        return true;
    }

    private void tryPlaceLight(World world, int i, int j, int k, Random random) {
        int j1;
        int height = 2 + random.nextInt(6);
        for (j1 = j; j1 >= -height; --j1) {
            if (!this.restrictions) continue;
            if (!this.isAir(world, i, j1, k)) {
                return;
            }
            if (j1 != -height || this.isAir(world, i, j1, k - 1) && this.isAir(world, i, j1, k + 1) && this.isAir(world, i - 1, j1, k) && this.isAir(world, i + 1, j1, k)) continue;
            return;
        }
        for (j1 = j; j1 >= j - height; --j1) {
            if (j1 == j - height) {
                this.setBlockAndMetadata(world, i, j1, k, LOTRMod.planks, 1);
                this.setBlockAndMetadata(world, i, j1, k - 1, LOTRWorldGenElfHouse.getRandomTorch(random), 4);
                this.setBlockAndMetadata(world, i, j1, k + 1, LOTRWorldGenElfHouse.getRandomTorch(random), 3);
                this.setBlockAndMetadata(world, i - 1, j1, k, LOTRWorldGenElfHouse.getRandomTorch(random), 1);
                this.setBlockAndMetadata(world, i + 1, j1, k, LOTRWorldGenElfHouse.getRandomTorch(random), 2);
                continue;
            }
            this.setBlockAndMetadata(world, i, j1, k, LOTRMod.fence, 1);
        }
    }

    public static ItemStack getRandomPlant(Random random) {
        return random.nextBoolean() ? new ItemStack(LOTRMod.elanor) : new ItemStack(LOTRMod.niphredil);
    }

    public static Block getRandomTorch(Random random) {
        if (random.nextBoolean()) {
            int i = random.nextInt(3);
            if (i == 0) {
                return LOTRMod.mallornTorchBlue;
            }
            if (i == 1) {
                return LOTRMod.mallornTorchGold;
            }
            if (i == 2) {
                return LOTRMod.mallornTorchGreen;
            }
        }
        return LOTRMod.mallornTorchSilver;
    }

    public static ItemStack getRandomChandelier(Random random) {
        if (random.nextBoolean()) {
            int i = random.nextInt(3);
            if (i == 0) {
                return new ItemStack(LOTRMod.chandelier, 1, 13);
            }
            if (i == 1) {
                return new ItemStack(LOTRMod.chandelier, 1, 14);
            }
            if (i == 2) {
                return new ItemStack(LOTRMod.chandelier, 1, 15);
            }
        }
        return new ItemStack(LOTRMod.chandelier, 1, 5);
    }
}

