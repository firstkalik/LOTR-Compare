/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityAvariElf;
import lotr.common.entity.npc.LOTREntityAvariElfCaptain;
import lotr.common.entity.npc.LOTREntityAvariElfScout;
import lotr.common.entity.npc.LOTREntityAvariElfWarrior;
import lotr.common.entity.npc.LOTREntityDurmethOrc;
import lotr.common.entity.npc.LOTREntityEasterling;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.feature.LOTRWorldGenMirkOak2;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenAvariElfTower
extends LOTRWorldGenStructureBase {
    private WorldGenerator treeGen = new LOTRWorldGenMirkOak2(true, 6, 6, 0, false).setGreenOak1().disableRestrictions().disableRoots();
    protected Block plateBlock = LOTRMod.woodPlateBlock;

    public LOTRWorldGenAvariElfTower(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int k2;
        int i1;
        int distSq;
        int k12;
        int k13;
        int i12;
        int k1;
        if (this.restrictions && world.getBlock(i, j - 1, k) != Blocks.grass) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
        int radius = 6;
        int radiusPlusOne = radius + 1;
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                k += radiusPlusOne;
                break;
            }
            case 1: {
                i -= radiusPlusOne;
                break;
            }
            case 2: {
                k -= radiusPlusOne;
                break;
            }
            case 3: {
                i += radiusPlusOne;
            }
        }
        if (this.restrictions) {
            int minHeight = j;
            int maxHeight = j;
            for (int i13 = i - radiusPlusOne; i13 <= i + radiusPlusOne; ++i13) {
                for (int k14 = k - radiusPlusOne; k14 <= k + radiusPlusOne; ++k14) {
                    int i2 = i13 - i;
                    int k22 = k14 - k;
                    if (i2 * i2 + k22 * k22 > radiusPlusOne * radiusPlusOne) continue;
                    int j12 = world.getTopSolidOrLiquidBlock(i13, k14) - 1;
                    Block block = world.getBlock(i13, j12, k14);
                    if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.stone && !block.isWood((IBlockAccess)world, i13, j12, k14) && !block.isLeaves((IBlockAccess)world, i13, j12, k14)) {
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
        int sections = 3 + random.nextInt(3);
        int sectionHeight = 8;
        int topHeight = j + sections * sectionHeight;
        int wallThresholdMin = radius * radius;
        int wallThresholdMax = radiusPlusOne * radiusPlusOne;
        for (int i14 = i - radius; i14 <= i + radius; ++i14) {
            for (int k15 = k - radius; k15 <= k + radius; ++k15) {
                int start;
                int i2 = i14 - i;
                k2 = k15 - k;
                distSq = i2 * i2 + k2 * k2;
                if (distSq >= wallThresholdMax) continue;
                for (int j13 = start = j - sectionHeight; !(j13 != start && LOTRMod.isOpaque(world, i14, j13, k15) || j13 < 0); --j13) {
                    if (j13 != start || distSq >= wallThresholdMin) {
                        this.setBlockAndNotifyAdequately(world, i14, j13, k15, LOTRMod.brick10, 1);
                    } else {
                        this.setBlockAndNotifyAdequately(world, i14, j13, k15, LOTRMod.planks, 13);
                    }
                    this.setGrassToDirt(world, i14, j13 - 1, k15);
                }
            }
        }
        for (int l = -1; l < sections; ++l) {
            int j14;
            int sectionBase = j + l * sectionHeight;
            for (j14 = sectionBase + 1; j14 <= sectionBase + sectionHeight; ++j14) {
                for (i12 = i - radius; i12 <= i + radius; ++i12) {
                    for (k13 = k - radius; k13 <= k + radius; ++k13) {
                        int i2 = i12 - i;
                        k2 = k13 - k;
                        distSq = i2 * i2 + k2 * k2;
                        if (distSq >= wallThresholdMax) continue;
                        if (distSq >= wallThresholdMin) {
                            this.setBlockAndNotifyAdequately(world, i12, j14, k13, LOTRMod.brick10, 1);
                            if (l == sections - 1 && j14 == sectionBase + sectionHeight) {
                                this.setBlockAndNotifyAdequately(world, i12, j14 + 1, k13, LOTRMod.brick10, 1);
                                this.setBlockAndNotifyAdequately(world, i12, j14 + 2, k13, LOTRMod.slabSingle121, 2);
                            }
                        } else if (j14 == sectionBase + sectionHeight && (Math.abs(i2) > 2 || Math.abs(k2) > 2)) {
                            this.setBlockAndNotifyAdequately(world, i12, j14, k13, LOTRMod.planks, 13);
                        } else {
                            this.setBlockAndNotifyAdequately(world, i12, j14, k13, Blocks.air, 0);
                        }
                        this.setGrassToDirt(world, i12, j14 - 1, k13);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i, j14, k, LOTRMod.wood3, 1);
            }
            for (int l1 = 0; l1 < 2; ++l1) {
                int stairBase = sectionBase + l1 * 4;
                this.setBlockAndNotifyAdequately(world, i - 4, sectionBase + 2, k - 4, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i, stairBase + 1, k + 1, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i, stairBase + 1, k + 2, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i + 1, stairBase + 2, k, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i + 2, stairBase + 2, k, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i, stairBase + 3, k - 1, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i, stairBase + 3, k - 2, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i - 1, stairBase + 4, k, LOTRMod.slabSingle121, 2);
                this.setBlockAndNotifyAdequately(world, i - 2, stairBase + 4, k, LOTRMod.slabSingle121, 2);
                for (i1 = 0; i1 <= 1; ++i1) {
                    for (k1 = 0; k1 <= 1; ++k1) {
                        this.setBlockAndNotifyAdequately(world, i + 1 + i1, stairBase + 1, k + 1 + k1, LOTRMod.slabSingle121, 10);
                        this.setBlockAndNotifyAdequately(world, i + 1 + i1, stairBase + 2, k - 2 + k1, LOTRMod.slabSingle121, 10);
                        this.setBlockAndNotifyAdequately(world, i - 2 + i1, stairBase + 3, k - 2 + k1, LOTRMod.slabSingle121, 10);
                        this.setBlockAndNotifyAdequately(world, i - 2 + i1, stairBase + 4, k + 1 + k1, LOTRMod.slabSingle121, 10);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i - 1, stairBase + 2, k, LOTRMod.avariTorch, 2);
                this.setBlockAndNotifyAdequately(world, i + 1, stairBase + 4, k, LOTRMod.avariTorch, 1);
            }
            this.setBlockAndNotifyAdequately(world, i - 4, sectionBase + 2, k - 4, LOTRMod.fence, 13);
            this.setBlockAndNotifyAdequately(world, i - 4, sectionBase + 3, k - 4, LOTRMod.avariTorch, 5);
            this.setBlockAndNotifyAdequately(world, i - 4, sectionBase + 2, k + 4, LOTRMod.fence, 13);
            this.setBlockAndNotifyAdequately(world, i - 4, sectionBase + 3, k + 4, LOTRMod.avariTorch, 5);
            this.setBlockAndNotifyAdequately(world, i + 4, sectionBase + 2, k - 4, LOTRMod.fence, 13);
            this.setBlockAndNotifyAdequately(world, i + 4, sectionBase + 3, k - 4, LOTRMod.avariTorch, 5);
            this.setBlockAndNotifyAdequately(world, i + 4, sectionBase + 2, k + 4, LOTRMod.fence, 13);
            this.setBlockAndNotifyAdequately(world, i + 4, sectionBase + 3, k + 4, LOTRMod.avariTorch, 5);
            if (l > 0) {
                int k16;
                int i15;
                for (j14 = sectionBase + 1; j14 <= sectionBase + 4; ++j14) {
                    for (i12 = i - 1; i12 <= i + 1; ++i12) {
                        this.setBlockAndNotifyAdequately(world, i12, j14, k - 6, Blocks.air, 0);
                        this.setBlockAndNotifyAdequately(world, i12, j14, k + 6, Blocks.air, 0);
                    }
                    for (k12 = k - 1; k12 <= k + 1; ++k12) {
                        this.setBlockAndNotifyAdequately(world, i - 6, j14, k12, Blocks.air, 0);
                        this.setBlockAndNotifyAdequately(world, i + 6, j14, k12, Blocks.air, 0);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i - 1, sectionBase + 4, k - 6, LOTRMod.stairsAvariBrick, 5);
                this.setBlockAndNotifyAdequately(world, i + 1, sectionBase + 4, k - 6, LOTRMod.stairsAvariBrick, 4);
                this.setBlockAndNotifyAdequately(world, i - 1, sectionBase + 4, k + 6, LOTRMod.stairsAvariBrick, 5);
                this.setBlockAndNotifyAdequately(world, i + 1, sectionBase + 4, k + 6, LOTRMod.stairsAvariBrick, 4);
                this.setBlockAndNotifyAdequately(world, i - 6, sectionBase + 4, k - 1, LOTRMod.stairsAvariBrick, 7);
                this.setBlockAndNotifyAdequately(world, i - 6, sectionBase + 4, k + 1, LOTRMod.stairsAvariBrick, 6);
                this.setBlockAndNotifyAdequately(world, i + 6, sectionBase + 4, k - 1, LOTRMod.stairsAvariBrick, 7);
                this.setBlockAndNotifyAdequately(world, i + 6, sectionBase + 4, k + 1, LOTRMod.stairsAvariBrick, 6);
                for (i15 = i - 2; i15 <= i + 2; ++i15) {
                    this.setBlockAndNotifyAdequately(world, i15, sectionBase, k - 8, LOTRMod.stairsLarch, 6);
                    this.setBlockAndNotifyAdequately(world, i15, sectionBase + 1, k - 8, LOTRMod.fence, 13);
                    this.setBlockAndNotifyAdequately(world, i15, sectionBase, k + 8, LOTRMod.stairsLarch, 7);
                    this.setBlockAndNotifyAdequately(world, i15, sectionBase + 1, k + 8, LOTRMod.fence, 13);
                }
                for (k16 = k - 2; k16 <= k + 2; ++k16) {
                    this.setBlockAndNotifyAdequately(world, i - 8, sectionBase, k16, LOTRMod.stairsLarch, 4);
                    this.setBlockAndNotifyAdequately(world, i - 8, sectionBase + 1, k16, LOTRMod.fence, 13);
                    this.setBlockAndNotifyAdequately(world, i + 8, sectionBase, k16, LOTRMod.stairsLarch, 5);
                    this.setBlockAndNotifyAdequately(world, i + 8, sectionBase + 1, k16, LOTRMod.fence, 13);
                }
                for (i15 = i - 1; i15 <= i + 1; ++i15) {
                    this.setBlockAndNotifyAdequately(world, i15, sectionBase, k - 7, LOTRMod.planks, 13);
                    this.setBlockAndNotifyAdequately(world, i15, sectionBase, k + 7, LOTRMod.planks, 13);
                }
                for (k16 = k - 1; k16 <= k + 1; ++k16) {
                    this.setBlockAndNotifyAdequately(world, i - 7, sectionBase, k16, LOTRMod.planks, 13);
                    this.setBlockAndNotifyAdequately(world, i + 7, sectionBase, k16, LOTRMod.planks, 13);
                }
                this.setBlockAndNotifyAdequately(world, i - 7, sectionBase, k - 2, LOTRMod.stairsLarch, 6);
                this.setBlockAndNotifyAdequately(world, i - 7, sectionBase + 1, k - 2, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i - 8, sectionBase + 2, k - 2, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i - 7, sectionBase, k + 2, LOTRMod.stairsLarch, 7);
                this.setBlockAndNotifyAdequately(world, i - 7, sectionBase + 1, k + 2, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i - 8, sectionBase + 2, k + 2, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i + 7, sectionBase, k - 2, LOTRMod.stairsLarch, 6);
                this.setBlockAndNotifyAdequately(world, i + 7, sectionBase + 1, k - 2, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i + 8, sectionBase + 2, k - 2, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i + 7, sectionBase, k + 2, LOTRMod.stairsLarch, 7);
                this.setBlockAndNotifyAdequately(world, i + 7, sectionBase + 1, k + 2, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i + 8, sectionBase + 2, k + 2, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i - 2, sectionBase, k - 7, LOTRMod.stairsLarch, 4);
                this.setBlockAndNotifyAdequately(world, i - 2, sectionBase + 1, k - 7, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i - 2, sectionBase + 2, k - 8, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i + 2, sectionBase, k - 7, LOTRMod.stairsLarch, 5);
                this.setBlockAndNotifyAdequately(world, i + 2, sectionBase + 1, k - 7, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i + 2, sectionBase + 2, k - 8, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i - 2, sectionBase, k + 7, LOTRMod.stairsLarch, 4);
                this.setBlockAndNotifyAdequately(world, i - 2, sectionBase + 1, k + 7, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i - 2, sectionBase + 2, k + 8, LOTRMod.avariTorch, 5);
                this.setBlockAndNotifyAdequately(world, i + 2, sectionBase, k + 7, LOTRMod.stairsLarch, 5);
                this.setBlockAndNotifyAdequately(world, i + 2, sectionBase + 1, k + 7, LOTRMod.fence, 13);
                this.setBlockAndNotifyAdequately(world, i + 2, sectionBase + 2, k + 8, LOTRMod.avariTorch, 5);
            }
            LOTREntityAvariElf woodElf = random.nextInt(3) == 0 ? new LOTREntityAvariElfScout(world) : new LOTREntityAvariElfWarrior(world);
            woodElf.setLocationAndAngles((double)(i - 3) + 0.5, (double)(sectionBase + 1), (double)(k - 3) + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
            woodElf.spawnRidingHorse = false;
            woodElf.onSpawnWithEgg(null);
            woodElf.setHomeArea(i, sectionBase + 1, k, 12);
            woodElf.isNPCPersistent = true;
            world.spawnEntityInWorld((Entity)woodElf);
        }
        this.treeGen.generate(world, random, i, topHeight, k);
        for (int j15 = topHeight + 2; j15 <= topHeight + 3; ++j15) {
            this.setBlockAndNotifyAdequately(world, i + 6, j15, k - 3, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i + 6, j15, k, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i + 6, j15, k + 3, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i - 3, j15, k + 6, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i, j15, k + 6, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i + 3, j15, k + 6, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i - 6, j15, k - 3, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i - 6, j15, k, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i - 6, j15, k + 3, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i - 3, j15, k - 6, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i, j15, k - 6, LOTRMod.brick10, 1);
            this.setBlockAndNotifyAdequately(world, i + 3, j15, k - 6, LOTRMod.brick10, 1);
        }
        this.setBlockAndNotifyAdequately(world, i + 6, topHeight + 2, k - 2, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i + 6, topHeight + 2, k + 2, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i - 2, topHeight + 2, k + 6, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i + 2, topHeight + 2, k + 6, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i - 6, topHeight + 2, k - 2, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i - 6, topHeight + 2, k + 2, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i - 2, topHeight + 2, k - 6, LOTRMod.brick10, 1);
        this.setBlockAndNotifyAdequately(world, i + 2, topHeight + 2, k - 6, LOTRMod.brick10, 1);
        ItemStack bow1 = new ItemStack(LOTRMod.bowAvariElf);
        ItemStack bow2 = new ItemStack(LOTRMod.bowAvariElf);
        ItemStack[] armor = new ItemStack[]{new ItemStack(LOTRMod.helmetAvariElfScout), new ItemStack(LOTRMod.bodyAvariElfScout), new ItemStack(LOTRMod.legsAvariElfScout), new ItemStack(LOTRMod.bootsAvariElfScout)};
        switch (rotation) {
            case 0: {
                this.placeArmorStand(world, i, topHeight + 1, k + 5, 0, armor);
                this.spawnItemFrame(world, i + 6, topHeight + 2, k, 1, bow1);
                this.spawnItemFrame(world, i - 6, topHeight + 2, k, 3, bow2);
                this.setBlockAndNotifyAdequately(world, i, topHeight + 1, k - 4, LOTRMod.commandTable, 0);
                break;
            }
            case 1: {
                this.spawnItemFrame(world, i, topHeight + 2, k + 6, 2, bow1);
                this.spawnItemFrame(world, i, topHeight + 2, k - 6, 0, bow2);
                this.placeArmorStand(world, i - 5, topHeight + 1, k, 1, armor);
                this.setBlockAndNotifyAdequately(world, i + 4, topHeight + 1, k, LOTRMod.commandTable, 0);
                break;
            }
            case 2: {
                this.spawnItemFrame(world, i + 6, topHeight + 2, k, 1, bow1);
                this.placeArmorStand(world, i, topHeight + 1, k - 5, 2, armor);
                this.spawnItemFrame(world, i - 6, topHeight + 2, k, 3, bow2);
                this.setBlockAndNotifyAdequately(world, i, topHeight + 1, k + 4, LOTRMod.commandTable, 0);
                break;
            }
            case 3: {
                this.spawnItemFrame(world, i, topHeight + 2, k + 6, 2, bow1);
                this.placeArmorStand(world, i + 5, topHeight + 1, k, 3, armor);
                this.spawnItemFrame(world, i, topHeight + 2, k - 6, 0, bow2);
                this.setBlockAndNotifyAdequately(world, i - 4, topHeight + 1, k, LOTRMod.commandTable, 0);
            }
        }
        this.placeWallBanner(world, i, topHeight + 1, k + 6, 0, LOTRItemBanner.BannerType.AVARI);
        this.placeWallBanner(world, i - 6, topHeight + 1, k, 1, LOTRItemBanner.BannerType.AVARI);
        this.placeWallBanner(world, i, topHeight + 1, k - 6, 2, LOTRItemBanner.BannerType.AVARI);
        this.placeWallBanner(world, i + 6, topHeight + 1, k, 3, LOTRItemBanner.BannerType.AVARI);
        for (i12 = i - 3; i12 <= i + 3; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k - 5, LOTRMod.woodSlabSingle5, 8);
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k + 5, LOTRMod.woodSlabSingle5, 8);
            if (random.nextBoolean()) {
                this.placeMug(world, random, i12, j - sectionHeight + 2, k + 5, 0, LOTRFoods.WOOD_ELF_DRINK);
            }
            if (Math.abs(i12 - i) <= 1) continue;
            this.placeBarrel(world, random, i12, j - sectionHeight + 2, k - 5, 3, LOTRFoods.WOOD_ELF_DRINK);
        }
        this.setBlockAndNotifyAdequately(world, i - 1, j - sectionHeight + 1, k - 5, LOTRMod.avariTable, 0);
        this.setBlockAndNotifyAdequately(world, i + 1, j - sectionHeight + 1, k - 5, LOTRMod.avariTable, 0);
        this.setBlockAndNotifyAdequately(world, i, j - sectionHeight + 1, k - 5, (Block)Blocks.chest, 0);
        LOTRChestContents.fillChest(world, random, i, j - sectionHeight + 1, k - 5, LOTRChestContents.LOTRChestContents2.AVARI_ELF_HOUSE);
        for (i12 = i + 4; i12 <= i + 5; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k - 3, LOTRMod.stairsPlum, 3);
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k - 1, LOTRMod.planks3, 0);
            this.placeMug(world, random, i12, j - sectionHeight + 2, k - 1, 0, LOTRFoods.WOOD_ELF_DRINK);
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k, LOTRMod.woodSlabSingle5, 8);
            this.placePlateWithCertainty(world, random, i12, j - sectionHeight + 2, k, this.plateBlock, LOTRFoods.ELF);
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k + 1, LOTRMod.planks3, 0);
            this.placeMug(world, random, i12, j - sectionHeight + 2, k + 1, 2, LOTRFoods.WOOD_ELF_DRINK);
            this.setBlockAndNotifyAdequately(world, i12, j - sectionHeight + 1, k + 3, LOTRMod.stairsPlum, 2);
        }
        this.setBlockAndNotifyAdequately(world, i + 4, j - sectionHeight + 1, k - 4, LOTRMod.planks3, 0);
        this.setBlockAndNotifyAdequately(world, i + 4, j - sectionHeight + 1, k + 4, LOTRMod.planks3, 0);
        for (j1 = j - sectionHeight - 6; j1 <= j - sectionHeight - 1; ++j1) {
            this.placeDungeonBlock(world, random, i - 6, j1, k);
            this.placeDungeonBlock(world, random, i - 5, j1, k - 2);
            this.placeDungeonBlock(world, random, i - 5, j1, k - 1);
            this.placeDungeonBlock(world, random, i - 5, j1, k + 1);
            this.placeDungeonBlock(world, random, i - 5, j1, k + 2);
            this.placeDungeonBlock(world, random, i - 4, j1, k - 3);
            this.placeDungeonBlock(world, random, i - 4, j1, k + 3);
            this.placeDungeonBlock(world, random, i - 3, j1, k - 5);
            this.placeDungeonBlock(world, random, i - 3, j1, k - 4);
            this.placeDungeonBlock(world, random, i - 3, j1, k + 4);
            this.placeDungeonBlock(world, random, i - 3, j1, k + 5);
            this.placeDungeonBlock(world, random, i - 2, j1, k - 6);
            this.placeDungeonBlock(world, random, i - 2, j1, k + 6);
            this.placeDungeonBlock(world, random, i - 1, j1, k - 6);
            this.placeDungeonBlock(world, random, i - 1, j1, k + 6);
            this.placeDungeonBlock(world, random, i, j1, k - 6);
            this.placeDungeonBlock(world, random, i, j1, k + 6);
            this.placeDungeonBlock(world, random, i + 1, j1, k - 5);
            this.placeDungeonBlock(world, random, i + 1, j1, k - 4);
            this.placeDungeonBlock(world, random, i + 1, j1, k + 4);
            this.placeDungeonBlock(world, random, i + 1, j1, k + 5);
            this.placeDungeonBlock(world, random, i + 2, j1, k - 3);
            this.placeDungeonBlock(world, random, i + 2, j1, k + 3);
            this.placeDungeonBlock(world, random, i + 3, j1, k - 2);
            this.placeDungeonBlock(world, random, i + 3, j1, k + 2);
            this.placeDungeonBlock(world, random, i + 4, j1, k - 2);
            this.placeDungeonBlock(world, random, i + 4, j1, k + 2);
            this.placeDungeonBlock(world, random, i + 5, j1, k - 1);
            this.placeDungeonBlock(world, random, i + 5, j1, k);
            this.placeDungeonBlock(world, random, i + 5, j1, k + 1);
            if (j1 == j - sectionHeight - 6 || j1 == j - sectionHeight - 1) {
                this.placeDungeonBlock(world, random, i - 5, j1, k);
                for (k13 = k - 2; k13 <= k + 2; ++k13) {
                    this.placeDungeonBlock(world, random, i - 4, j1, k13);
                }
                for (k13 = k - 3; k13 <= k + 3; ++k13) {
                    this.placeDungeonBlock(world, random, i - 3, j1, k13);
                }
                for (k13 = k - 5; k13 <= k + 5; ++k13) {
                    this.placeDungeonBlock(world, random, i - 2, j1, k13);
                    this.placeDungeonBlock(world, random, i - 1, j1, k13);
                    this.placeDungeonBlock(world, random, i, j1, k13);
                }
                for (k13 = k - 3; k13 <= k + 3; ++k13) {
                    this.placeDungeonBlock(world, random, i + 1, j1, k13);
                }
                for (k13 = k - 2; k13 <= k + 2; ++k13) {
                    this.placeDungeonBlock(world, random, i + 2, j1, k13);
                }
                for (k13 = k - 1; k13 <= k + 1; ++k13) {
                    this.placeDungeonBlock(world, random, i + 3, j1, k13);
                    this.placeDungeonBlock(world, random, i + 4, j1, k13);
                }
                continue;
            }
            this.setBlockAndNotifyAdequately(world, i - 5, j1, k, Blocks.air, 0);
            for (k13 = k - 2; k13 <= k + 2; ++k13) {
                this.setBlockAndNotifyAdequately(world, i - 4, j1, k13, Blocks.air, 0);
            }
            for (k13 = k - 3; k13 <= k + 3; ++k13) {
                this.setBlockAndNotifyAdequately(world, i - 3, j1, k13, Blocks.air, 0);
            }
            for (k13 = k - 5; k13 <= k + 5; ++k13) {
                this.setBlockAndNotifyAdequately(world, i - 2, j1, k13, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i - 1, j1, k13, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i, j1, k13, Blocks.air, 0);
            }
            for (k13 = k - 3; k13 <= k + 3; ++k13) {
                this.setBlockAndNotifyAdequately(world, i + 1, j1, k13, Blocks.air, 0);
            }
            for (k13 = k - 2; k13 <= k + 2; ++k13) {
                this.setBlockAndNotifyAdequately(world, i + 2, j1, k13, Blocks.air, 0);
            }
            for (k13 = k - 1; k13 <= k + 1; ++k13) {
                this.setBlockAndNotifyAdequately(world, i + 3, j1, k13, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 4, j1, k13, Blocks.air, 0);
            }
        }
        for (i12 = i - 2; i12 <= i; ++i12) {
            this.placeDungeonBlock(world, random, i12, j - sectionHeight - 2, k - 5);
            this.placeDungeonBlock(world, random, i12, j - sectionHeight - 2, k - 4);
            this.placeDungeonBlock(world, random, i12, j - sectionHeight - 2, k + 4);
            this.placeDungeonBlock(world, random, i12, j - sectionHeight - 2, k + 5);
        }
        for (k12 = k - 1; k12 <= k + 1; ++k12) {
            this.placeDungeonBlock(world, random, i + 3, j - sectionHeight - 2, k12);
            this.placeDungeonBlock(world, random, i + 4, j - sectionHeight - 2, k12);
        }
        for (j1 = j - sectionHeight - 5; j1 <= j - sectionHeight - 3; ++j1) {
            for (i1 = i - 2; i1 <= i; ++i1) {
                this.setBlockAndNotifyAdequately(world, i1, j1, k - 4, LOTRMod.avariBars, 0);
                this.setBlockAndNotifyAdequately(world, i1, j1, k + 4, LOTRMod.avariBars, 0);
            }
            for (k13 = k - 1; k13 <= k + 1; ++k13) {
                this.setBlockAndNotifyAdequately(world, i + 3, j1, k13, LOTRMod.avariBars, 0);
            }
        }
        this.placePrisoner(world, random, i - 2, j - sectionHeight - 5, k - 5, 3, 1);
        this.placePrisoner(world, random, i - 2, j - sectionHeight - 5, k + 5, 3, 1);
        this.placePrisoner(world, random, i + 4, j - sectionHeight - 5, k - 1, 1, 3);
        this.setBlockAndNotifyAdequately(world, i - 4, j - sectionHeight - 3, k - 1, LOTRMod.avariTorch, 1);
        this.setBlockAndNotifyAdequately(world, i - 4, j - sectionHeight - 3, k + 1, LOTRMod.avariTorch, 1);
        for (j1 = j - sectionHeight - 5; j1 <= j - sectionHeight; ++j1) {
            this.setBlockAndNotifyAdequately(world, i - 5, j1, k, Blocks.ladder, 5);
        }
        this.setBlockAndNotifyAdequately(world, i - 5, j - sectionHeight + 1, k, LOTRMod.trapdoorLarch, 3);
        switch (rotation) {
            case 0: {
                k1 = k - radius;
                for (i12 = i - 1; i12 <= i + 1; ++i12) {
                    for (int j16 = j + 1; j16 <= j + 3; ++j16) {
                        this.setBlockAndNotifyAdequately(world, i12, j16, k1, LOTRMod.gateAvari, 2);
                    }
                }
                this.placeWallBanner(world, i, j + 6, k1, 2, LOTRItemBanner.BannerType.AVARI);
                break;
            }
            case 1: {
                i12 = i + radius;
                for (k1 = k - 1; k1 <= k + 1; ++k1) {
                    for (int j16 = j + 1; j16 <= j + 3; ++j16) {
                        this.setBlockAndNotifyAdequately(world, i12, j16, k1, LOTRMod.gateAvari, 5);
                    }
                }
                this.placeWallBanner(world, i12, j + 6, k, 3, LOTRItemBanner.BannerType.AVARI);
                break;
            }
            case 2: {
                k1 = k + radius;
                for (i12 = i - 1; i12 <= i + 1; ++i12) {
                    for (int j16 = j + 1; j16 <= j + 3; ++j16) {
                        this.setBlockAndNotifyAdequately(world, i12, j16, k1, LOTRMod.gateAvari, 3);
                    }
                }
                this.placeWallBanner(world, i, j + 6, k1, 0, LOTRItemBanner.BannerType.AVARI);
                break;
            }
            case 3: {
                i12 = i - radius;
                for (k1 = k - 1; k1 <= k + 1; ++k1) {
                    for (int j16 = j + 1; j16 <= j + 3; ++j16) {
                        this.setBlockAndNotifyAdequately(world, i12, j16, k1, LOTRMod.gateAvari, 4);
                    }
                }
                this.placeWallBanner(world, i12, j + 6, k, 1, LOTRItemBanner.BannerType.AVARI);
            }
        }
        LOTREntityAvariElfCaptain woodElfCaptain = new LOTREntityAvariElfCaptain(world);
        woodElfCaptain.setLocationAndAngles((double)(i - 3) + 0.5, (double)(topHeight + 1), (double)(k - 3) + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
        woodElfCaptain.spawnRidingHorse = false;
        woodElfCaptain.onSpawnWithEgg(null);
        woodElfCaptain.setHomeArea(i, topHeight, k, 16);
        world.spawnEntityInWorld((Entity)woodElfCaptain);
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityAvariElfWarrior.class, LOTREntityAvariElfScout.class);
        respawner.setCheckRanges(12, -16, 40, 12);
        respawner.setSpawnRanges(5, 1, 40, 12);
        this.placeNPCRespawner(respawner, world, i, j, k);
        return true;
    }

    private void placeDungeonBlock(World world, Random random, int i, int j, int k) {
        int l = random.nextInt(3);
        switch (l) {
            case 0: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick10, 1);
                break;
            }
            case 1: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick10, 2);
                break;
            }
            case 2: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick10, 3);
            }
        }
    }

    private void placePrisoner(World world, Random random, int i, int j, int k, int xRange, int zRange) {
        i += random.nextInt(xRange);
        k += random.nextInt(zRange);
        if (random.nextInt(3) == 0) {
            LOTREntityNPC npc = random.nextInt(10) == 0 ? new LOTREntityEasterling(world) : (random.nextBoolean() ? new LOTREntityMordorOrc(world) : new LOTREntityDurmethOrc(world));
            npc.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, 0.0f, 0.0f);
            npc.spawnRidingHorse = false;
            npc.onSpawnWithEgg(null);
            for (int l = 0; l < 5; ++l) {
                npc.setCurrentItemOrArmor(l, null);
            }
            npc.npcItemsInv.setMeleeWeapon(null);
            npc.npcItemsInv.setMeleeWeaponMounted(null);
            npc.npcItemsInv.setRangedWeapon(null);
            npc.npcItemsInv.setSpearBackup(null);
            npc.npcItemsInv.setIdleItem(null);
            npc.npcItemsInv.setIdleItemMounted(null);
            npc.isNPCPersistent = true;
            world.spawnEntityInWorld((Entity)npc);
        } else {
            this.placeSkull(world, random, i, j, k);
        }
    }
}

