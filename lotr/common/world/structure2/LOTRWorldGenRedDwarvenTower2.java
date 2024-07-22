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
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityBlacklockAxe;
import lotr.common.entity.npc.LOTREntityBlacklockCap;
import lotr.common.entity.npc.LOTREntityBlacklockWarrior;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRedDwarvenTower2
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenRedDwarvenTower2(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int i12;
        Block block;
        if (this.restrictions && (block = world.getBlock(i, j - 1, k)) != Blocks.grass && block != Blocks.stone && block != Blocks.dirt && block != LOTRMod.rock && block != Blocks.snow) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                k += 8;
                break;
            }
            case 1: {
                i -= 8;
                break;
            }
            case 2: {
                k -= 8;
                break;
            }
            case 3: {
                i += 8;
            }
        }
        if (this.restrictions) {
            int minHeight = j;
            int maxHeight = j;
            for (int i13 = i - 6; i13 <= i + 6; ++i13) {
                for (int k12 = k - 6; k12 <= k + 6; ++k12) {
                    int j1 = world.getTopSolidOrLiquidBlock(i13, k12) - 1;
                    Block block2 = world.getBlock(i13, j1, k12);
                    if (block2 != Blocks.grass && block2 != Blocks.stone && block2 != Blocks.dirt && block2 != LOTRMod.rock && block2 != Blocks.snow) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 <= maxHeight) continue;
                    maxHeight = j1;
                }
            }
            if (maxHeight - minHeight > 10) {
                return false;
            }
        }
        for (int k1 = k - 6; k1 <= k + 6; ++k1) {
            for (i12 = i - 6; i12 <= i + 6; ++i12) {
                boolean flag = Math.abs(k1 - k) == 6 && Math.abs(i12 - i) == 6;
                for (int j1 = j + 7; !(j1 < j && LOTRMod.isOpaque(world, i12, j1, k1) || j1 < 0); --j1) {
                    if (flag) {
                        this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.pillar, 4);
                    } else {
                        if (Math.abs(i12 - i) < 6 && Math.abs(k1 - k) < 6) {
                            if (j1 >= j + 1 && j1 <= j + 3) {
                                this.setAir(world, i12, j1, k1);
                                continue;
                            }
                            if (j1 >= j + 4 && j1 <= j + 7) {
                                this.setAir(world, i12, j1, k1);
                                continue;
                            }
                            if (j1 == j) {
                                this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.planks, 9);
                                continue;
                            }
                        }
                        this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.brick2, 2);
                    }
                    if (j1 > j) continue;
                    this.setGrassToDirt(world, i12, j1 - 1, k1);
                }
            }
        }
        for (int i1 = i - 6; i1 <= i + 6; ++i1) {
            this.setBlockAndNotifyAdequately(world, i1, j + 8, k - 6, LOTRMod.stairsSarnkaranBrickGlow, 2);
            this.setBlockAndNotifyAdequately(world, i1, j + 8, k + 6, LOTRMod.stairsSarnkaranBrickGlow, 3);
        }
        for (int var21 = k - 6; var21 <= k + 6; ++var21) {
            this.setBlockAndNotifyAdequately(world, i - 6, j + 8, var21, LOTRMod.stairsSarnkaranBrickGlow, 0);
            this.setBlockAndNotifyAdequately(world, i + 6, j + 8, var21, LOTRMod.stairsSarnkaranBrickGlow, 1);
        }
        for (int var22 = k - 5; var22 <= k + 5; ++var22) {
            for (i12 = i - 5; i12 <= i + 5; ++i12) {
                this.setBlockAndNotifyAdequately(world, i12, j + 4, var22, LOTRMod.slabDouble3, 5);
                this.setBlockAndNotifyAdequately(world, i12, j + 8, var22, LOTRMod.slabDouble3, 5);
                int i2 = Math.abs(i12 - i);
                int k2 = Math.abs(var22 - k);
                int m = -1;
                if (i2 == 5) {
                    m = k2 % 2;
                } else if (k2 == 5) {
                    m = i2 % 2;
                }
                if (m <= -1) continue;
                if (m == 1) {
                    for (int j1 = j + 9; j1 <= j + 11; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i12, j1, var22, LOTRMod.pillar, 4);
                    }
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, i12, j + 9, var22, LOTRMod.wall2, 3);
            }
        }
        for (int var18 = i - 5; var18 <= i + 5; ++var18) {
            this.setBlockAndNotifyAdequately(world, var18, j + 12, k - 5, LOTRMod.stairsSarnkaranBrickGlow, 2);
            this.setBlockAndNotifyAdequately(world, var18, j + 12, k + 5, LOTRMod.stairsSarnkaranBrickGlow, 3);
        }
        for (int var23 = k - 5; var23 <= k + 5; ++var23) {
            this.setBlockAndNotifyAdequately(world, i - 5, j + 12, var23, LOTRMod.stairsSarnkaranBrickGlow, 0);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 12, var23, LOTRMod.stairsSarnkaranBrickGlow, 1);
        }
        for (int var24 = k - 4; var24 <= k + 4; ++var24) {
            for (i12 = i - 4; i12 <= i + 4; ++i12) {
                this.setBlockAndNotifyAdequately(world, i12, j + 12, var24, LOTRMod.slabSingle3, 5);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 7, k, LOTRMod.chandelier, 2);
        this.setBlockAndNotifyAdequately(world, i, j + 11, k, LOTRMod.chandelier, 2);
        this.setBlockAndNotifyAdequately(world, i, j + 12, k, LOTRMod.brick2, 2);
        switch (rotation) {
            case 0: {
                this.generateFacingSouth(world, random, i, j, k);
                break;
            }
            case 1: {
                this.generateFacingWest(world, random, i, j, k);
                break;
            }
            case 2: {
                this.generateFacingNorth(world, random, i, j, k);
                break;
            }
            case 3: {
                this.generateFacingEast(world, random, i, j, k);
            }
        }
        this.spawnDwarfCommander(world, i, j + 9, k);
        for (int l = 0; l < 4; ++l) {
            this.spawnDwarf(world, i, j + 5, k);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityBlacklockWarrior.class, LOTREntityBlacklockAxe.class);
        respawner.setCheckRanges(8, -8, 16, 8);
        respawner.setSpawnRanges(8, 1, 10, 16);
        this.placeNPCRespawner(respawner, world, i, j, k);
        return true;
    }

    private void generateFacingSouth(World world, Random random, int i, int j, int k) {
        int m;
        int i1;
        int j2;
        for (int i12 = i - 6; i12 <= i + 6; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k - 7, LOTRMod.slabSingle3, 5);
            this.setGrassToDirt(world, i12, j, k - 7);
            for (m = j; !LOTRMod.isOpaque(world, i12, m, k - 7) && m >= 0; --m) {
                this.setBlockAndNotifyAdequately(world, i12, m, k - 7, LOTRMod.pillar, 4);
                this.setGrassToDirt(world, i12, m - 1, k - 7);
            }
        }
        for (int j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i, j1, k - 6);
            this.setBlockAndNotifyAdequately(world, i - 1, j1, k - 7, LOTRMod.pillar, 4);
            this.setBlockAndNotifyAdequately(world, i + 1, j1, k - 7, LOTRMod.pillar, 4);
        }
        this.setBlockAndNotifyAdequately(world, i, j, k - 7, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i, j, k - 6, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k - 7, LOTRMod.doorBeech, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k - 7, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 3, k - 7, LOTRMod.stairsSarnkaranBrickGlow, 0);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k - 7, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k - 7, LOTRMod.stairsSarnkaranBrickGlow, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 4, k - 7, LOTRMod.slabSingle3, 5);
        this.placeWallBanner(world, i, j + 6, k - 6, 2, LOTRItemBanner.BannerType.BLACKLOCKS);
        for (int var15 = j + 1; var15 <= j + 3; ++var15) {
            for (m = k - 4; m <= k - 1; ++m) {
                for (i1 = i - 5; i1 <= i - 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, var15, m, LOTRMod.brick2, 2);
                }
                for (i1 = i + 1; i1 <= i + 5; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, var15, m, LOTRMod.brick2, 2);
                }
            }
            for (m = k - 2; m <= k + 5; ++m) {
                this.setBlockAndNotifyAdequately(world, i - 1, var15, m, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + 1, var15, m, LOTRMod.brick2, 2);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 1, j + 1, k + 3, LOTRMod.doorBeech, 0);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 2, k + 3, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k + 3, LOTRMod.doorBeech, 2);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 2, k + 3, LOTRMod.doorBeech, 8);
        for (int var27 = i - 5; var27 <= i + 2; var27 += 7) {
            this.setBlockAndNotifyAdequately(world, var27, j + 1, k + 1, LOTRMod.dwarvenBed, 2);
            this.setBlockAndNotifyAdequately(world, var27, j + 1, k, LOTRMod.dwarvenBed, 10);
            this.setBlockAndNotifyAdequately(world, var27 + 3, j + 1, k + 1, LOTRMod.dwarvenBed, 2);
            this.setBlockAndNotifyAdequately(world, var27 + 3, j + 1, k, LOTRMod.dwarvenBed, 10);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 1, k, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 2, j + 1, k, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 2, k, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 2, j + 2, k, (Block)Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, var27 + 1, j + 1, k, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, var27 + 2, j + 1, k, LOTRChestContents.LOTRChestContents2.BLACKLOCKS);
            LOTRChestContents.fillChest(world, random, var27 + 1, j + 2, k, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, var27 + 2, j + 2, k, LOTRChestContents.LOTRChestContents2.BLACKLOCKS);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 3, k + 3, LOTRMod.chandelier, 2);
            this.setBlockAndNotifyAdequately(world, var27, j + 1, k + 5, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, var27 + 3, j + 1, k + 5, LOTRMod.planks, 9);
            this.placeBarrel(world, random, var27, j + 2, k + 5, 2, LOTRFoods.REDDWARF_DRINK);
            this.placeBarrel(world, random, var27 + 3, j + 2, k + 5, 2, LOTRFoods.REDDWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 1, k + 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, var27 + 1, j + 1, k + 5, 2);
            this.setBlockAndNotifyAdequately(world, var27 + 2, j + 1, k + 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, var27 + 2, j + 1, k + 5, 2);
        }
        this.setAir(world, i, j + 4, k - 5);
        int stairX = 1;
        for (int j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i - stairX, j + 4, k - 5);
            this.setAir(world, i + stairX, j + 4, k - 5);
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k - 5, LOTRMod.stairsSarnkaranBrickGlow, 1);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k - 5, LOTRMod.stairsSarnkaranBrickGlow, 0);
            for (j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k - 5, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k - 5, LOTRMod.brick2, 2);
            }
            ++stairX;
        }
        for (int var17 = j + 1; var17 <= j + 3; ++var17) {
            this.setBlockAndNotifyAdequately(world, i - stairX, var17, k - 5, LOTRMod.brick2, 2);
            this.setBlockAndNotifyAdequately(world, i + stairX, var17, k - 5, LOTRMod.brick2, 2);
        }
        for (int i13 = i - 5; i13 <= i + 5; i13 += 10) {
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 2, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k - 2, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 2, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k + 2, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k, LOTRMod.reddwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 6, k + 6, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 6, k + 6, LOTRMod.brick8, 0);
        stairX = 4;
        for (int var18 = j + 5; var18 <= j + 8; ++var18) {
            this.setAir(world, i - stairX, j + 8, k - 4);
            this.setAir(world, i + stairX, j + 8, k - 4);
            this.setBlockAndNotifyAdequately(world, i - stairX, var18, k - 4, LOTRMod.stairsSarnkaranBrickGlow, 0);
            this.setBlockAndNotifyAdequately(world, i + stairX, var18, k - 4, LOTRMod.stairsSarnkaranBrickGlow, 1);
            for (j2 = var18 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k - 4, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k - 4, LOTRMod.brick2, 2);
            }
            --stairX;
        }
        for (int var19 = j + 5; var19 <= j + 7; ++var19) {
            this.setBlockAndNotifyAdequately(world, i, var19, k - 4, LOTRMod.brick2, 2);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 6, k - 4, LOTRMod.brick8, 0);
        for (int k1 = k + 7; k1 <= k + 8; ++k1) {
            for (i1 = i - 4; i1 <= i + 4; ++i1) {
                this.placeBalconySection(world, i1, j, k1, false, false);
            }
            this.placeBalconySection(world, i - 5, j, k1, true, false);
            this.placeBalconySection(world, i + 5, j, k1, true, false);
        }
        for (int var28 = i - 2; var28 <= i + 2; ++var28) {
            this.placeBalconySection(world, var28, j, k + 9, false, false);
        }
        for (int var29 = i - 5; var29 <= i + 5; ++var29) {
            if (Math.abs(var29 - i) < 3) continue;
            this.placeBalconySection(world, var29, j, k + 9, true, false);
        }
        for (int var30 = i - 1; var30 <= i + 1; ++var30) {
            this.placeBalconySection(world, var30, j, k + 10, false, false);
        }
        for (int var31 = i - 3; var31 <= i + 3; ++var31) {
            if (Math.abs(var31 - i) < 2) continue;
            this.placeBalconySection(world, var31, j, k + 10, true, false);
        }
        for (int var32 = i - 2; var32 <= i + 2; ++var32) {
            if (Math.abs(var32 - i) == 0) {
                this.placeBalconySection(world, var32, j, k + 11, true, true);
                continue;
            }
            this.placeBalconySection(world, var32, j, k + 11, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 4, k + 6, LOTRMod.slabDouble3, 5);
        this.setAir(world, i, j + 5, k + 6);
        this.setAir(world, i, j + 6, k + 6);
        this.setBlockAndNotifyAdequately(world, i, j, k + 6, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k + 6, LOTRMod.doorBeech, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k + 6, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k + 8, LOTRMod.chandelier, 2);
        for (int var20 = j + 1; var20 <= j + 2; ++var20) {
            for (int k12 = k + 7; k12 <= k + 8; ++k12) {
                this.placeRandomOre(world, random, i - 4, var20, k12);
                this.placeRandomOre(world, random, i - 3, var20, k12);
                this.placeRandomOre(world, random, i + 3, var20, k12);
                this.placeRandomOre(world, random, i + 4, var20, k12);
            }
            this.placeRandomOre(world, random, i - 2, var20, k + 9);
            this.placeRandomOre(world, random, i + 2, var20, k + 9);
            for (i1 = i - 1; i1 <= i + 1; ++i1) {
                this.placeRandomOre(world, random, i1, var20, k + 10);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 9, k + 3, LOTRMod.commandTable, 0);
    }

    private void generateFacingWest(World world, Random random, int i, int j, int k) {
        int m;
        int j2;
        int k12;
        for (int k13 = k - 6; k13 <= k + 6; ++k13) {
            this.setBlockAndNotifyAdequately(world, i + 7, j + 1, k13, LOTRMod.slabSingle3, 5);
            this.setGrassToDirt(world, i + 7, j, k13);
            for (m = j; !LOTRMod.isOpaque(world, i + 7, m, k13) && m >= 0; --m) {
                this.setBlockAndNotifyAdequately(world, i + 7, m, k13, LOTRMod.pillar, 4);
                this.setGrassToDirt(world, i + 7, m - 1, k13);
            }
        }
        for (int j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i + 6, j1, k);
            this.setBlockAndNotifyAdequately(world, i + 7, j1, k - 1, LOTRMod.pillar, 4);
            this.setBlockAndNotifyAdequately(world, i + 7, j1, k + 1, LOTRMod.pillar, 4);
        }
        this.setBlockAndNotifyAdequately(world, i + 7, j, k, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i + 6, j, k, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 1, k, LOTRMod.doorBeech, 2);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 2, k, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 3, k - 1, LOTRMod.stairsSarnkaranBrickGlow, 2);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 3, k, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 3, k + 1, LOTRMod.stairsSarnkaranBrickGlow, 3);
        this.setBlockAndNotifyAdequately(world, i + 7, j + 4, k, LOTRMod.slabSingle3, 5);
        this.placeWallBanner(world, i + 6, j + 6, k, 3, LOTRItemBanner.BannerType.BLACKLOCKS);
        for (int var15 = j + 1; var15 <= j + 3; ++var15) {
            for (m = i + 4; m >= i + 1; --m) {
                for (k12 = k - 5; k12 <= k - 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, m, var15, k12, LOTRMod.brick2, 2);
                }
                for (k12 = k + 1; k12 <= k + 5; ++k12) {
                    this.setBlockAndNotifyAdequately(world, m, var15, k12, LOTRMod.brick2, 2);
                }
            }
            for (m = i + 2; m >= i - 5; --m) {
                this.setBlockAndNotifyAdequately(world, m, var15, k - 1, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, m, var15, k + 1, LOTRMod.brick2, 2);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 1, k - 1, LOTRMod.doorBeech, 1);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 2, k - 1, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 1, k + 1, LOTRMod.doorBeech, 3);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 2, k + 1, LOTRMod.doorBeech, 8);
        for (int var30 = k - 5; var30 <= k + 2; var30 += 7) {
            this.setBlockAndNotifyAdequately(world, i - 1, j + 1, var30, LOTRMod.dwarvenBed, 3);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30, LOTRMod.dwarvenBed, 11);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 1, var30 + 3, LOTRMod.dwarvenBed, 3);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30 + 3, LOTRMod.dwarvenBed, 11);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30 + 1, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30 + 2, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 2, var30 + 1, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 2, var30 + 2, (Block)Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i, j + 1, var30 + 1, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i, j + 1, var30 + 2, LOTRChestContents.LOTRChestContents2.REDDWARFTOWER);
            LOTRChestContents.fillChest(world, random, i, j + 2, var30 + 1, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i, j + 2, var30 + 2, LOTRChestContents.LOTRChestContents2.REDDWARFTOWER);
            this.setBlockAndNotifyAdequately(world, i - 3, j + 3, var30 + 1, LOTRMod.chandelier, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, var30, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, var30 + 3, LOTRMod.planks, 9);
            this.placeBarrel(world, random, i - 5, j + 2, var30, 5, LOTRFoods.REDDWARF_DRINK);
            this.placeBarrel(world, random, i - 5, j + 2, var30 + 3, 5, LOTRFoods.REDDWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, var30 + 1, Blocks.furnace, 0);
            this.setBlockMetadata(world, i - 5, j + 1, var30 + 1, 5);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 1, var30 + 2, Blocks.furnace, 0);
            this.setBlockMetadata(world, i - 5, j + 1, var30 + 2, 5);
        }
        this.setAir(world, i + 5, j + 4, k);
        int stairX = 1;
        for (int j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i + 5, j + 4, k - stairX);
            this.setAir(world, i + 5, j + 4, k + stairX);
            this.setBlockAndNotifyAdequately(world, i + 5, j12, k - stairX, LOTRMod.stairsSarnkaranBrickGlow, 3);
            this.setBlockAndNotifyAdequately(world, i + 5, j12, k + stairX, LOTRMod.stairsSarnkaranBrickGlow, 2);
            for (j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i + 5, j2, k - stairX, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + 5, j2, k + stairX, LOTRMod.brick2, 2);
            }
            ++stairX;
        }
        for (int var17 = j + 1; var17 <= j + 3; ++var17) {
            this.setBlockAndNotifyAdequately(world, i + 5, var17, k - stairX, LOTRMod.brick2, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, var17, k + stairX, LOTRMod.brick2, 2);
        }
        for (int k1 = k - 5; k1 <= k + 5; k1 += 10) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 5, k1, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 6, k1, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k1, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k1, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 5, k1, LOTRMod.reddwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i - 6, j + 6, k - 3, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i - 6, j + 6, k + 3, LOTRMod.brick8, 0);
        stairX = 4;
        for (int var18 = j + 5; var18 <= j + 8; ++var18) {
            this.setAir(world, i + 4, j + 8, k - stairX);
            this.setAir(world, i + 4, j + 8, k + stairX);
            this.setBlockAndNotifyAdequately(world, i + 4, var18, k - stairX, LOTRMod.stairsSarnkaranBrickGlow, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, var18, k + stairX, LOTRMod.stairsSarnkaranBrickGlow, 3);
            for (j2 = var18 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i + 4, j2, k - stairX, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + 4, j2, k + stairX, LOTRMod.brick2, 2);
            }
            --stairX;
        }
        for (int var19 = j + 5; var19 <= j + 7; ++var19) {
            this.setBlockAndNotifyAdequately(world, i + 4, var19, k, LOTRMod.brick2, 2);
        }
        this.setBlockAndNotifyAdequately(world, i + 4, j + 6, k, LOTRMod.brick8, 0);
        for (int i1 = i - 7; i1 >= i - 8; --i1) {
            for (k12 = k - 4; k12 <= k + 4; ++k12) {
                this.placeBalconySection(world, i1, j, k12, false, false);
            }
            this.placeBalconySection(world, i1, j, k - 5, true, false);
            this.placeBalconySection(world, i1, j, k + 5, true, false);
        }
        for (int var22 = k - 2; var22 <= k + 2; ++var22) {
            this.placeBalconySection(world, i - 9, j, var22, false, false);
        }
        for (int var23 = k - 5; var23 <= k + 5; ++var23) {
            if (Math.abs(var23 - k) < 3) continue;
            this.placeBalconySection(world, i - 9, j, var23, true, false);
        }
        for (int var24 = k - 1; var24 <= k + 1; ++var24) {
            this.placeBalconySection(world, i - 10, j, var24, false, false);
        }
        for (int var25 = k - 3; var25 <= k + 3; ++var25) {
            if (Math.abs(var25 - k) < 2) continue;
            this.placeBalconySection(world, i - 10, j, var25, true, false);
        }
        for (int var26 = k - 2; var26 <= k + 2; ++var26) {
            if (Math.abs(var26 - k) == 0) {
                this.placeBalconySection(world, i - 11, j, var26, true, true);
                continue;
            }
            this.placeBalconySection(world, i - 11, j, var26, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i - 6, j + 4, k, LOTRMod.slabDouble3, 5);
        this.setAir(world, i - 6, j + 5, k);
        this.setAir(world, i - 6, j + 6, k);
        this.setBlockAndNotifyAdequately(world, i - 6, j, k, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i - 6, j + 1, k, LOTRMod.doorBeech, 0);
        this.setBlockAndNotifyAdequately(world, i - 6, j + 2, k, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i - 8, j + 3, k, LOTRMod.chandelier, 2);
        for (int var20 = j + 1; var20 <= j + 2; ++var20) {
            for (int i12 = i - 7; i12 >= i - 8; --i12) {
                this.placeRandomOre(world, random, i12, var20, k - 4);
                this.placeRandomOre(world, random, i12, var20, k - 3);
                this.placeRandomOre(world, random, i12, var20, k + 3);
                this.placeRandomOre(world, random, i12, var20, k + 4);
            }
            this.placeRandomOre(world, random, i - 9, var20, k - 2);
            this.placeRandomOre(world, random, i - 9, var20, k + 2);
            for (k12 = k - 1; k12 <= k + 1; ++k12) {
                this.placeRandomOre(world, random, i - 10, var20, k12);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 9, k, LOTRMod.commandTable, 0);
    }

    private void generateFacingNorth(World world, Random random, int i, int j, int k) {
        int m;
        int i1;
        int j2;
        for (int i12 = i - 6; i12 <= i + 6; ++i12) {
            this.setBlockAndNotifyAdequately(world, i12, j + 1, k + 7, LOTRMod.slabSingle3, 5);
            this.setGrassToDirt(world, i12, j, k + 7);
            for (m = j; !LOTRMod.isOpaque(world, i12, m, k + 7) && m >= 0; --m) {
                this.setBlockAndNotifyAdequately(world, i12, m, k + 7, LOTRMod.pillar, 4);
                this.setGrassToDirt(world, i12, m - 1, k + 7);
            }
        }
        for (int j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i, j1, k + 6);
            this.setBlockAndNotifyAdequately(world, i - 1, j1, k + 7, LOTRMod.pillar, 4);
            this.setBlockAndNotifyAdequately(world, i + 1, j1, k + 7, LOTRMod.pillar, 4);
        }
        this.setBlockAndNotifyAdequately(world, i, j, k + 7, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i, j, k + 6, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k + 7, LOTRMod.doorBeech, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k + 7, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 3, k + 7, LOTRMod.stairsSarnkaranBrickGlow, 0);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k + 7, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k + 7, LOTRMod.stairsSarnkaranBrickGlow, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 4, k + 7, LOTRMod.slabSingle3, 5);
        this.placeWallBanner(world, i, j + 6, k + 6, 0, LOTRItemBanner.BannerType.BLACKLOCKS);
        for (int var15 = j + 1; var15 <= j + 3; ++var15) {
            for (m = k + 4; m >= k + 1; --m) {
                for (i1 = i - 5; i1 <= i - 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, var15, m, LOTRMod.brick2, 2);
                }
                for (i1 = i + 1; i1 <= i + 5; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, var15, m, LOTRMod.brick2, 2);
                }
            }
            for (m = k + 2; m >= k - 5; --m) {
                this.setBlockAndNotifyAdequately(world, i - 1, var15, m, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + 1, var15, m, LOTRMod.brick2, 2);
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 1, j + 1, k - 3, LOTRMod.doorBeech, 0);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 2, k - 3, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 1, k - 3, LOTRMod.doorBeech, 2);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 2, k - 3, LOTRMod.doorBeech, 8);
        for (int var27 = i - 5; var27 <= i + 2; var27 += 7) {
            this.setBlockAndNotifyAdequately(world, var27, j + 1, k - 1, LOTRMod.dwarvenBed, 0);
            this.setBlockAndNotifyAdequately(world, var27, j + 1, k, LOTRMod.dwarvenBed, 8);
            this.setBlockAndNotifyAdequately(world, var27 + 3, j + 1, k - 1, LOTRMod.dwarvenBed, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 3, j + 1, k, LOTRMod.dwarvenBed, 8);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 1, k, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 2, j + 1, k, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 2, k, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, var27 + 2, j + 2, k, (Block)Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, var27 + 1, j + 1, k, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, var27 + 2, j + 1, k, LOTRChestContents.LOTRChestContents2.REDDWARFTOWER);
            LOTRChestContents.fillChest(world, random, var27 + 1, j + 2, k, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, var27 + 2, j + 2, k, LOTRChestContents.LOTRChestContents2.REDDWARFTOWER);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 3, k - 3, LOTRMod.chandelier, 2);
            this.setBlockAndNotifyAdequately(world, var27, j + 1, k - 5, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, var27 + 3, j + 1, k - 5, LOTRMod.planks, 9);
            this.placeBarrel(world, random, var27, j + 2, k - 5, 3, LOTRFoods.REDDWARF_DRINK);
            this.placeBarrel(world, random, var27 + 3, j + 2, k - 5, 3, LOTRFoods.REDDWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, var27 + 1, j + 1, k - 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, var27 + 1, j + 1, k - 5, 3);
            this.setBlockAndNotifyAdequately(world, var27 + 2, j + 1, k - 5, Blocks.furnace, 0);
            this.setBlockMetadata(world, var27 + 2, j + 1, k - 5, 3);
        }
        this.setAir(world, i, j + 4, k + 5);
        int stairX = 1;
        for (int j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i - stairX, j + 4, k + 5);
            this.setAir(world, i + stairX, j + 4, k + 5);
            this.setBlockAndNotifyAdequately(world, i - stairX, j12, k + 5, LOTRMod.stairsSarnkaranBrickGlow, 1);
            this.setBlockAndNotifyAdequately(world, i + stairX, j12, k + 5, LOTRMod.stairsSarnkaranBrickGlow, 0);
            for (j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k + 5, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k + 5, LOTRMod.brick2, 2);
            }
            ++stairX;
        }
        for (int var17 = j + 1; var17 <= j + 3; ++var17) {
            this.setBlockAndNotifyAdequately(world, i - stairX, var17, k + 5, LOTRMod.brick2, 2);
            this.setBlockAndNotifyAdequately(world, i + stairX, var17, k + 5, LOTRMod.brick2, 2);
        }
        for (int i13 = i - 5; i13 <= i + 5; i13 += 10) {
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 2, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k + 2, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 2, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i13, j + 6, k - 2, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k, LOTRMod.reddwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k + 1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i13, j + 5, k - 1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 6, k - 6, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 6, k - 6, LOTRMod.brick8, 0);
        stairX = 4;
        for (int var18 = j + 5; var18 <= j + 8; ++var18) {
            this.setAir(world, i - stairX, j + 8, k + 4);
            this.setAir(world, i + stairX, j + 8, k + 4);
            this.setBlockAndNotifyAdequately(world, i - stairX, var18, k + 4, LOTRMod.stairsSarnkaranBrickGlow, 0);
            this.setBlockAndNotifyAdequately(world, i + stairX, var18, k + 4, LOTRMod.stairsSarnkaranBrickGlow, 1);
            for (j2 = var18 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i - stairX, j2, k + 4, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i + stairX, j2, k + 4, LOTRMod.brick2, 2);
            }
            --stairX;
        }
        for (int var19 = j + 5; var19 <= j + 7; ++var19) {
            this.setBlockAndNotifyAdequately(world, i, var19, k + 4, LOTRMod.brick2, 2);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 6, k + 4, LOTRMod.brick8, 0);
        for (int k1 = k - 7; k1 >= k - 8; --k1) {
            for (i1 = i - 4; i1 <= i + 4; ++i1) {
                this.placeBalconySection(world, i1, j, k1, false, false);
            }
            this.placeBalconySection(world, i - 5, j, k1, true, false);
            this.placeBalconySection(world, i + 5, j, k1, true, false);
        }
        for (int var28 = i - 2; var28 <= i + 2; ++var28) {
            this.placeBalconySection(world, var28, j, k - 9, false, false);
        }
        for (int var29 = i - 5; var29 <= i + 5; ++var29) {
            if (Math.abs(var29 - i) < 3) continue;
            this.placeBalconySection(world, var29, j, k - 9, true, false);
        }
        for (int var30 = i - 1; var30 <= i + 1; ++var30) {
            this.placeBalconySection(world, var30, j, k - 10, false, false);
        }
        for (int var31 = i - 3; var31 <= i + 3; ++var31) {
            if (Math.abs(var31 - i) < 2) continue;
            this.placeBalconySection(world, var31, j, k - 10, true, false);
        }
        for (int var32 = i - 2; var32 <= i + 2; ++var32) {
            if (Math.abs(var32 - i) == 0) {
                this.placeBalconySection(world, var32, j, k - 11, true, true);
                continue;
            }
            this.placeBalconySection(world, var32, j, k - 11, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 4, k - 6, LOTRMod.slabDouble3, 5);
        this.setAir(world, i, j + 5, k - 6);
        this.setAir(world, i, j + 6, k - 6);
        this.setBlockAndNotifyAdequately(world, i, j, k - 6, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i, j + 1, k - 6, LOTRMod.doorBeech, 1);
        this.setBlockAndNotifyAdequately(world, i, j + 2, k - 6, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k - 8, LOTRMod.chandelier, 2);
        for (int var20 = j + 1; var20 <= j + 2; ++var20) {
            for (int k12 = k - 7; k12 >= k - 8; --k12) {
                this.placeRandomOre(world, random, i - 4, var20, k12);
                this.placeRandomOre(world, random, i - 3, var20, k12);
                this.placeRandomOre(world, random, i + 3, var20, k12);
                this.placeRandomOre(world, random, i + 4, var20, k12);
            }
            this.placeRandomOre(world, random, i - 2, var20, k - 9);
            this.placeRandomOre(world, random, i + 2, var20, k - 9);
            for (i1 = i - 1; i1 <= i + 1; ++i1) {
                this.placeRandomOre(world, random, i1, var20, k - 10);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 9, k - 3, LOTRMod.commandTable, 0);
    }

    private void generateFacingEast(World world, Random random, int i, int j, int k) {
        int m;
        int j2;
        int k12;
        for (int k13 = k - 6; k13 <= k + 6; ++k13) {
            this.setBlockAndNotifyAdequately(world, i - 7, j + 1, k13, LOTRMod.slabSingle3, 5);
            this.setGrassToDirt(world, i - 7, j, k13);
            for (m = j; !LOTRMod.isOpaque(world, i - 7, m, k13) && m >= 0; --m) {
                this.setBlockAndNotifyAdequately(world, i - 7, m, k13, LOTRMod.pillar, 4);
                this.setGrassToDirt(world, i - 7, m - 1, k13);
            }
        }
        for (int j1 = j + 1; j1 <= j + 2; ++j1) {
            this.setAir(world, i - 6, j1, k);
            this.setBlockAndNotifyAdequately(world, i - 7, j1, k - 1, LOTRMod.pillar, 4);
            this.setBlockAndNotifyAdequately(world, i - 7, j1, k + 1, LOTRMod.pillar, 4);
        }
        this.setBlockAndNotifyAdequately(world, i - 7, j, k, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i - 6, j, k, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 1, k, LOTRMod.doorBeech, 0);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 2, k, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 3, k - 1, LOTRMod.stairsSarnkaranBrickGlow, 2);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 3, k, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 3, k + 1, LOTRMod.stairsSarnkaranBrickGlow, 3);
        this.setBlockAndNotifyAdequately(world, i - 7, j + 4, k, LOTRMod.slabSingle3, 5);
        this.placeWallBanner(world, i - 6, j + 6, k, 1, LOTRItemBanner.BannerType.BLACKLOCKS);
        for (int var15 = j + 1; var15 <= j + 3; ++var15) {
            for (m = i - 4; m <= i - 1; ++m) {
                for (k12 = k - 5; k12 <= k - 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, m, var15, k12, LOTRMod.brick2, 2);
                }
                for (k12 = k + 1; k12 <= k + 5; ++k12) {
                    this.setBlockAndNotifyAdequately(world, m, var15, k12, LOTRMod.brick2, 2);
                }
            }
            for (m = i - 2; m <= i + 5; ++m) {
                this.setBlockAndNotifyAdequately(world, m, var15, k - 1, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, m, var15, k + 1, LOTRMod.brick2, 2);
            }
        }
        this.setBlockAndNotifyAdequately(world, i + 3, j + 1, k - 1, LOTRMod.doorBeech, 1);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k - 1, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 1, k + 1, LOTRMod.doorBeech, 3);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 2, k + 1, LOTRMod.doorBeech, 8);
        for (int var30 = k - 5; var30 <= k + 2; var30 += 7) {
            this.setBlockAndNotifyAdequately(world, i + 1, j + 1, var30, LOTRMod.dwarvenBed, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30, LOTRMod.dwarvenBed, 9);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 1, var30 + 3, LOTRMod.dwarvenBed, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30 + 3, LOTRMod.dwarvenBed, 9);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30 + 1, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 1, var30 + 2, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 2, var30 + 1, (Block)Blocks.chest, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 2, var30 + 2, (Block)Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i, j + 1, var30 + 1, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i, j + 1, var30 + 2, LOTRChestContents.LOTRChestContents2.REDDWARFTOWER);
            LOTRChestContents.fillChest(world, random, i, j + 2, var30 + 1, LOTRChestContents.DWARF_HOUSE_LARDER);
            LOTRChestContents.fillChest(world, random, i, j + 2, var30 + 2, LOTRChestContents.LOTRChestContents2.REDDWARFTOWER);
            this.setBlockAndNotifyAdequately(world, i + 3, j + 3, var30 + 1, LOTRMod.chandelier, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, var30, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, var30 + 3, LOTRMod.planks, 9);
            this.placeBarrel(world, random, i + 5, j + 2, var30, 4, LOTRFoods.REDDWARF_DRINK);
            this.placeBarrel(world, random, i + 5, j + 2, var30 + 3, 4, LOTRFoods.REDDWARF_DRINK);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, var30 + 1, Blocks.furnace, 0);
            this.setBlockMetadata(world, i + 5, j + 1, var30 + 1, 4);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 1, var30 + 2, Blocks.furnace, 0);
            this.setBlockMetadata(world, i + 5, j + 1, var30 + 2, 4);
        }
        this.setAir(world, i - 5, j + 4, k);
        int stairX = 1;
        for (int j12 = j + 1; j12 <= j + 4; ++j12) {
            this.setAir(world, i - 5, j + 4, k - stairX);
            this.setAir(world, i - 5, j + 4, k + stairX);
            this.setBlockAndNotifyAdequately(world, i - 5, j12, k - stairX, LOTRMod.stairsSarnkaranBrickGlow, 3);
            this.setBlockAndNotifyAdequately(world, i - 5, j12, k + stairX, LOTRMod.stairsSarnkaranBrickGlow, 2);
            for (j2 = j12 - 1; j2 > j; --j2) {
                this.setBlockAndNotifyAdequately(world, i - 5, j2, k - stairX, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i - 5, j2, k + stairX, LOTRMod.brick2, 2);
            }
            ++stairX;
        }
        for (int var17 = j + 1; var17 <= j + 3; ++var17) {
            this.setBlockAndNotifyAdequately(world, i - 5, var17, k - stairX, LOTRMod.brick2, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, var17, k + stairX, LOTRMod.brick2, 2);
        }
        for (int k1 = k - 5; k1 <= k + 5; k1 += 10) {
            this.setBlockAndNotifyAdequately(world, i - 2, j + 5, k1, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 6, k1, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k1, LOTRMod.planks, 9);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 6, k1, LOTRMod.woodSlabSingle2, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 5, k1, LOTRMod.reddwarvenTable, 0);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 5, k1, LOTRMod.dwarvenForge, 0);
        }
        this.setBlockAndNotifyAdequately(world, i + 6, j + 6, k - 3, LOTRMod.brick8, 0);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 6, k + 3, LOTRMod.brick8, 0);
        stairX = 4;
        for (int var18 = j + 5; var18 <= j + 8; ++var18) {
            this.setAir(world, i - 4, j + 8, k - stairX);
            this.setAir(world, i - 4, j + 8, k + stairX);
            this.setBlockAndNotifyAdequately(world, i - 4, var18, k - stairX, LOTRMod.stairsSarnkaranBrickGlow, 2);
            this.setBlockAndNotifyAdequately(world, i - 4, var18, k + stairX, LOTRMod.stairsSarnkaranBrickGlow, 3);
            for (j2 = var18 - 1; j2 > j + 4; --j2) {
                this.setBlockAndNotifyAdequately(world, i - 4, j2, k - stairX, LOTRMod.brick2, 2);
                this.setBlockAndNotifyAdequately(world, i - 4, j2, k + stairX, LOTRMod.brick2, 2);
            }
            --stairX;
        }
        for (int var19 = j + 5; var19 <= j + 7; ++var19) {
            this.setBlockAndNotifyAdequately(world, i - 4, var19, k, LOTRMod.brick2, 2);
        }
        this.setBlockAndNotifyAdequately(world, i - 4, j + 6, k, LOTRMod.brick8, 0);
        for (int i1 = i + 7; i1 <= i + 8; ++i1) {
            for (k12 = k - 4; k12 <= k + 4; ++k12) {
                this.placeBalconySection(world, i1, j, k12, false, false);
            }
            this.placeBalconySection(world, i1, j, k - 5, true, false);
            this.placeBalconySection(world, i1, j, k + 5, true, false);
        }
        for (int var22 = k - 2; var22 <= k + 2; ++var22) {
            this.placeBalconySection(world, i + 9, j, var22, false, false);
        }
        for (int var23 = k - 5; var23 <= k + 5; ++var23) {
            if (Math.abs(var23 - k) < 3) continue;
            this.placeBalconySection(world, i + 9, j, var23, true, false);
        }
        for (int var24 = k - 1; var24 <= k + 1; ++var24) {
            this.placeBalconySection(world, i + 10, j, var24, false, false);
        }
        for (int var25 = k - 3; var25 <= k + 3; ++var25) {
            if (Math.abs(var25 - k) < 2) continue;
            this.placeBalconySection(world, i + 10, j, var25, true, false);
        }
        for (int var26 = k - 2; var26 <= k + 2; ++var26) {
            if (Math.abs(var26 - k) == 0) {
                this.placeBalconySection(world, i + 11, j, var26, true, true);
                continue;
            }
            this.placeBalconySection(world, i + 11, j, var26, true, false);
        }
        this.setBlockAndNotifyAdequately(world, i + 6, j + 4, k, LOTRMod.slabDouble3, 5);
        this.setAir(world, i + 6, j + 5, k);
        this.setAir(world, i + 6, j + 6, k);
        this.setBlockAndNotifyAdequately(world, i + 6, j, k, LOTRMod.planks, 9);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 1, k, LOTRMod.doorBeech, 2);
        this.setBlockAndNotifyAdequately(world, i + 6, j + 2, k, LOTRMod.doorBeech, 8);
        this.setBlockAndNotifyAdequately(world, i + 8, j + 3, k, LOTRMod.chandelier, 2);
        for (int var20 = j + 1; var20 <= j + 2; ++var20) {
            for (int i12 = i + 7; i12 <= i + 8; ++i12) {
                this.placeRandomOre(world, random, i12, var20, k - 4);
                this.placeRandomOre(world, random, i12, var20, k - 3);
                this.placeRandomOre(world, random, i12, var20, k + 3);
                this.placeRandomOre(world, random, i12, var20, k + 4);
            }
            this.placeRandomOre(world, random, i + 9, var20, k - 2);
            this.placeRandomOre(world, random, i + 9, var20, k + 2);
            for (k12 = k - 1; k12 <= k + 1; ++k12) {
                this.placeRandomOre(world, random, i + 10, var20, k12);
            }
        }
        this.setBlockAndNotifyAdequately(world, i + 3, j + 9, k, LOTRMod.commandTable, 0);
    }

    private void spawnDwarfCommander(World world, int i, int j, int k) {
        LOTREntityBlacklockCap dwarf = new LOTREntityBlacklockCap(world);
        dwarf.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, 0.0f, 0.0f);
        dwarf.onSpawnWithEgg(null);
        dwarf.setHomeArea(i, j, k, 16);
        world.spawnEntityInWorld((Entity)dwarf);
    }

    private void spawnDwarf(World world, int i, int j, int k) {
        LOTREntityBlacklockWarrior dwarf = world.rand.nextInt(3) == 0 ? new LOTREntityBlacklockAxe(world) : new LOTREntityBlacklockWarrior(world);
        dwarf.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, 0.0f, 0.0f);
        dwarf.onSpawnWithEgg(null);
        dwarf.isNPCPersistent = true;
        dwarf.setHomeArea(i, j, k, 16);
        world.spawnEntityInWorld((Entity)dwarf);
    }

    private void placeBalconySection(World world, int i, int j, int k, boolean isEdge, boolean isPillar) {
        if (isEdge) {
            for (int j1 = j + 4; !(j1 < j && LOTRMod.isOpaque(world, i, j1, k) || j1 < 0); --j1) {
                if (isPillar) {
                    this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.pillar, 4);
                } else {
                    this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.brick2, 2);
                }
                this.setGrassToDirt(world, i, j1 - 1, k);
            }
            if (isPillar) {
                this.setBlockAndNotifyAdequately(world, i, j + 4, k, LOTRMod.brick2, 2);
            }
            this.setBlockAndNotifyAdequately(world, i, j + 5, k, LOTRMod.brick2, 2);
            this.setBlockAndNotifyAdequately(world, i, j + 6, k, LOTRMod.wall2, 3);
        } else {
            for (int j1 = j - 1; !LOTRMod.isOpaque(world, i, j1, k) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.brick2, 2);
                this.setGrassToDirt(world, i, j1 - 1, k);
            }
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.planks, 9);
            for (int var9 = j + 1; var9 <= j + 6; ++var9) {
                this.setAir(world, i, var9, k);
            }
            this.setBlockAndNotifyAdequately(world, i, j + 4, k, LOTRMod.slabDouble3, 5);
        }
    }

    private void placeRandomOre(World world, Random random, int i, int j, int k) {
        if (LOTRMod.isOpaque(world, i, j - 1, k) && random.nextBoolean()) {
            int l = random.nextInt(5);
            Block block = null;
            switch (l) {
                case 0: {
                    block = Blocks.iron_ore;
                    break;
                }
                case 1: {
                    block = Blocks.gold_ore;
                    break;
                }
                case 2: {
                    block = LOTRMod.oreCopper;
                    break;
                }
                case 3: {
                    block = LOTRMod.oreTin;
                    break;
                }
                case 4: {
                    block = LOTRMod.oreSilver;
                }
            }
            this.setBlockAndNotifyAdequately(world, i, j, k, block, 0);
        }
    }
}

