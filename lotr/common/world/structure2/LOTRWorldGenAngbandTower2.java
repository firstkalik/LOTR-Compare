/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngbandCap2;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRWorldGenAngbandTower2
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenAngbandTower2(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int k1;
        int i1;
        int k12;
        int j12;
        --j;
        int rotation = random.nextInt(4);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                k += 7;
                break;
            }
            case 1: {
                i -= 7;
                break;
            }
            case 2: {
                k -= 7;
                break;
            }
            case 3: {
                i += 7;
            }
        }
        int sections = 2 + random.nextInt(3);
        if (this.restrictions) {
            for (int i12 = i - 7; i12 <= i + 7; ++i12) {
                for (k12 = k - 7; k12 <= k + 7; ++k12) {
                    j1 = world.getHeightValue(i12, k12) - 1;
                    Block block = world.getBlock(i12, j1, k12);
                    if (block == Blocks.snow || block == Blocks.snow || block == Blocks.snow || block.isWood((IBlockAccess)world, i12, j1, k12) || block.isLeaves((IBlockAccess)world, i12, j1, k12)) continue;
                    return false;
                }
            }
        }
        for (k1 = k - 2; k1 <= k + 2; ++k1) {
            for (j12 = j; !LOTRMod.isOpaque(world, i - 6, j12, k1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i - 6, j12, k1, LOTRMod.utumnoBrick, 2);
                this.setGrassToDirt(world, i - 6, j12 - 1, k1);
            }
            for (j12 = j; !LOTRMod.isOpaque(world, i + 6, j12, k1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i + 6, j12, k1, LOTRMod.utumnoBrick, 2);
                this.setGrassToDirt(world, i + 6, j12 - 1, k1);
            }
        }
        for (k1 = k - 4; k1 <= k + 4; ++k1) {
            for (j12 = j; !LOTRMod.isOpaque(world, i - 5, j12, k1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i - 5, j12, k1, LOTRMod.utumnoBrick, 2);
                this.setGrassToDirt(world, i - 5, j12 - 1, k1);
            }
            for (j12 = j; !LOTRMod.isOpaque(world, i + 5, j12, k1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i + 5, j12, k1, LOTRMod.utumnoBrick, 2);
                this.setGrassToDirt(world, i + 5, j12 - 1, k1);
            }
        }
        for (k1 = k - 5; k1 <= k + 5; ++k1) {
            for (i1 = i - 4; i1 <= i - 3; ++i1) {
                for (j1 = j; !LOTRMod.isOpaque(world, i1, j1, k1) && j1 >= 0; --j1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.utumnoBrick, 2);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
            for (i1 = i + 3; i1 <= i + 4; ++i1) {
                for (j1 = j; !LOTRMod.isOpaque(world, i1, j1, k1) && j1 >= 0; --j1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.utumnoBrick, 2);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
        }
        for (k1 = k - 6; k1 <= k + 6; ++k1) {
            for (i1 = i - 2; i1 <= i + 2; ++i1) {
                for (j1 = j; !LOTRMod.isOpaque(world, i1, j1, k1) && j1 >= 0; --j1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k1, LOTRMod.utumnoBrick, 2);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
        }
        for (int l = 0; l <= sections; ++l) {
            this.generateTowerSection(world, random, i, j, k, l, false);
        }
        this.generateTowerSection(world, random, i, j, k, sections + 1, true);
        LOTREntityAngbandCap2 trader = new LOTREntityAngbandCap2(world);
        trader.setLocationAndAngles((double)(i - 2) + 0.5, (double)(j + (sections + 1) * 8 + 1), (double)k + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
        trader.onSpawnWithEgg(null);
        trader.setHomeArea(i, j + (sections + 1) * 8, k, 24);
        world.spawnEntityInWorld((Entity)trader);
        switch (rotation) {
            case 0: {
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j, k - 6, LOTRMod.utumnoBrick, 8);
                    for (j1 = j + 1; j1 <= j + 4; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k - 6, Blocks.air, 0);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i, j + 7, k - 6, LOTRMod.utumnoBrick, 0);
                this.placeWallBanner(world, i, j + 7, k - 6, 2, LOTRItemBanner.BannerType.DORNADAERACHAS);
                break;
            }
            case 1: {
                for (k12 = k - 1; k12 <= k + 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i + 6, j, k12, LOTRMod.utumnoBrick, 8);
                    for (j1 = j + 1; j1 <= j + 4; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i + 6, j1, k12, Blocks.air, 0);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i + 6, j + 7, k, LOTRMod.utumnoBrick, 2);
                this.placeWallBanner(world, i + 6, j + 7, k, 3, LOTRItemBanner.BannerType.DORNADAERACHAS);
                break;
            }
            case 2: {
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j, k + 6, LOTRMod.utumnoBrick, 8);
                    for (j1 = j + 1; j1 <= j + 4; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k + 6, Blocks.air, 0);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i, j + 7, k + 6, LOTRMod.utumnoBrick, 2);
                this.placeWallBanner(world, i, j + 7, k + 6, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
                break;
            }
            case 3: {
                for (k12 = k - 1; k12 <= k + 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i - 6, j, k12, LOTRMod.utumnoBrick, 8);
                    for (j1 = j + 1; j1 <= j + 4; ++j1) {
                        this.setBlockAndNotifyAdequately(world, i - 6, j1, k12, Blocks.air, 0);
                    }
                }
                this.setBlockAndNotifyAdequately(world, i - 6, j + 7, k, LOTRMod.utumnoBrick, 2);
                this.placeWallBanner(world, i - 6, j + 7, k, 1, LOTRItemBanner.BannerType.DORNADAERACHAS);
            }
        }
        int radius = 6;
        for (int l = 0; l < 16; ++l) {
            int k13;
            int j13;
            int i13 = i - random.nextInt(radius * 2) + random.nextInt(radius * 2);
            Block id = world.getBlock(i13, (j13 = world.getHeightValue(i13, k13 = k - random.nextInt(radius * 2) + random.nextInt(radius * 2))) - 1, k13);
            if (id != Blocks.grass && id != Blocks.dirt && id != Blocks.stone) continue;
            int randomFeature = random.nextInt(4);
            boolean flag = true;
            if (randomFeature == 0) {
                if (!LOTRMod.isOpaque(world, i13, j13, k13)) {
                    if (random.nextInt(3) == 0) {
                        this.setBlockAndNotifyAdequately(world, i13, j13, k13, LOTRMod.slabUtumnoSingle, 4);
                    } else {
                        this.setBlockAndNotifyAdequately(world, i13, j13, k13, LOTRMod.slabUtumnoSingle, 3);
                    }
                }
            } else {
                int j2;
                for (j2 = j13; j2 < j13 + randomFeature && flag; ++j2) {
                    flag = !LOTRMod.isOpaque(world, i13, j2, k13);
                }
                if (flag) {
                    for (j2 = j13; j2 < j13 + randomFeature; ++j2) {
                        if (random.nextBoolean()) {
                            this.setBlockAndNotifyAdequately(world, i13, j2, k13, LOTRMod.utumnoBrick, 2);
                            continue;
                        }
                        this.setBlockAndNotifyAdequately(world, i13, j2, k13, LOTRMod.utumnoBrick, 2);
                    }
                }
            }
            if (world.getBlock(i13, j13 - 1, k13) != Blocks.dirt) continue;
            this.setBlockAndNotifyAdequately(world, i13, j13 - 1, k13, Blocks.dirt, 0);
        }
        return true;
    }

    private void generateTowerSection(World world, Random random, int i, int j, int k, int section, boolean isTop) {
        int i1;
        int j1;
        int k1;
        for (j1 = section == 0 ? j : (j += section * 8) + 1; j1 <= (isTop ? j + 10 : j + 8); ++j1) {
            int i12;
            Block fillBlock = Blocks.air;
            int fillMeta = 0;
            if (j1 == j) {
                fillBlock = LOTRMod.utumnoBrick;
                fillMeta = 8;
            } else {
                fillBlock = Blocks.air;
                fillMeta = 0;
            }
            boolean hasCeiling = j1 == j + 8 && !isTop;
            for (k1 = k - 2; k1 <= k + 2; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 5, j1, k1, fillBlock, fillMeta);
                this.setBlockAndNotifyAdequately(world, i + 5, j1, k1, fillBlock, fillMeta);
                if (hasCeiling && random.nextInt(20) != 0) {
                    this.setBlockAndNotifyAdequately(world, i - 5, j1, k1, LOTRMod.slabUtumnoSingle2, 2);
                }
                if (!hasCeiling || random.nextInt(20) == 0) continue;
                this.setBlockAndNotifyAdequately(world, i + 5, j1, k1, LOTRMod.slabUtumnoSingle2, 2);
            }
            for (k1 = k - 4; k1 <= k + 4; ++k1) {
                for (i12 = i - 4; i12 <= i - 3; ++i12) {
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, fillBlock, fillMeta);
                    if (!hasCeiling || random.nextInt(20) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.slabUtumnoSingle2, 2);
                }
                for (i12 = i + 3; i12 <= i + 4; ++i12) {
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, fillBlock, fillMeta);
                    if (!hasCeiling || random.nextInt(20) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.slabUtumnoSingle2, 2);
                }
            }
            for (k1 = k - 5; k1 <= k + 5; ++k1) {
                for (i12 = i - 2; i12 <= i + 2; ++i12) {
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, fillBlock, fillMeta);
                    if (!hasCeiling || random.nextInt(20) == 0) continue;
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, LOTRMod.slabUtumnoSingle2, 2);
                }
            }
        }
        for (j1 = j + 1; j1 <= (isTop ? j + 1 : j + 8); ++j1) {
            for (k1 = k - 2; k1 <= k + 2; ++k1) {
                this.placeRandomBrick(world, random, i - 6, j1, k1);
                this.placeRandomBrick(world, random, i + 6, j1, k1);
            }
            for (int i13 = i - 2; i13 <= i + 2; ++i13) {
                this.placeRandomBrick(world, random, i13, j1, k - 6);
                this.placeRandomBrick(world, random, i13, j1, k + 6);
            }
            this.placeRandomBrick(world, random, i - 5, j1, k - 4);
            this.placeRandomBrick(world, random, i - 5, j1, k - 3);
            this.placeRandomBrick(world, random, i - 5, j1, k + 3);
            this.placeRandomBrick(world, random, i - 5, j1, k + 4);
            this.placeRandomBrick(world, random, i - 4, j1, k - 5);
            this.placeRandomBrick(world, random, i - 4, j1, k + 5);
            this.placeRandomBrick(world, random, i - 3, j1, k - 5);
            this.placeRandomBrick(world, random, i - 3, j1, k + 5);
            this.placeRandomBrick(world, random, i + 3, j1, k - 5);
            this.placeRandomBrick(world, random, i + 3, j1, k + 5);
            this.placeRandomBrick(world, random, i + 4, j1, k - 5);
            this.placeRandomBrick(world, random, i + 4, j1, k + 5);
            this.placeRandomBrick(world, random, i + 5, j1, k - 4);
            this.placeRandomBrick(world, random, i + 5, j1, k - 3);
            this.placeRandomBrick(world, random, i + 5, j1, k + 3);
            this.placeRandomBrick(world, random, i + 5, j1, k + 4);
        }
        if (!isTop) {
            for (j1 = j + 2; j1 <= j + 4; ++j1) {
                for (k1 = k - 1; k1 <= k + 1; ++k1) {
                    if (random.nextInt(3) != 0) {
                        this.setBlockAndNotifyAdequately(world, i - 6, j1, k1, LOTRMod.orcSteelBars, 0);
                    } else {
                        this.setBlockAndNotifyAdequately(world, i - 6, j1, k1, Blocks.air, 0);
                    }
                    if (random.nextInt(3) != 0) {
                        this.setBlockAndNotifyAdequately(world, i + 6, j1, k1, LOTRMod.orcSteelBars, 0);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i + 6, j1, k1, Blocks.air, 0);
                }
                for (int i14 = i - 1; i14 <= i + 1; ++i14) {
                    if (random.nextInt(3) != 0) {
                        this.setBlockAndNotifyAdequately(world, i14, j1, k - 6, LOTRMod.orcSteelBars, 0);
                    } else {
                        this.setBlockAndNotifyAdequately(world, i14, j1, k - 6, Blocks.air, 0);
                    }
                    if (random.nextInt(3) != 0) {
                        this.setBlockAndNotifyAdequately(world, i14, j1, k + 6, LOTRMod.orcSteelBars, 0);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i14, j1, k + 6, Blocks.air, 0);
                }
            }
            for (i1 = i - 2; i1 <= i + 2; ++i1) {
                for (k1 = k - 2; k1 <= k + 2; ++k1) {
                    this.setBlockAndNotifyAdequately(world, i1, j + 8, k1, Blocks.air, 0);
                }
            }
            this.setBlockAndNotifyAdequately(world, i - 2, j + 1, k + 1, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 1, k + 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 2, k + 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 2, k + 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k + 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 3, k + 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 4, k + 1, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 4, k, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k - 1, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k - 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k - 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i, j + 6, k - 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 7, k - 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 7, k - 2, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 8, k - 1, LOTRMod.slabUtumnoSingle, 1);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 8, k, LOTRMod.slabUtumnoSingle, 1);
        }
        for (i1 = i - 1; i1 <= i + 1; ++i1) {
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                for (int j12 = j + 1; j12 <= (isTop ? j + 3 : j + 8); ++j12) {
                    this.placeRandomBrick(world, random, i1, j12, k1);
                }
            }
        }
        if (isTop) {
            int j13;
            int top = 4 + random.nextInt(5);
            for (j13 = j + 1; j13 <= j + top; ++j13) {
                for (int k12 = k - 1; k12 <= k + 1; ++k12) {
                    this.placeRandomBrick(world, random, i - 7, j13, k12);
                    this.placeRandomBrick(world, random, i + 7, j13, k12);
                }
                for (int i15 = i - 1; i15 <= i + 1; ++i15) {
                    this.placeRandomBrick(world, random, i15, j13, k - 7);
                    this.placeRandomBrick(world, random, i15, j13, k + 7);
                }
            }
            for (int k13 = k - 1; k13 <= k + 1; ++k13) {
                this.placeRandomStairs(world, random, i - 7, j, k13, 4);
                this.placeRandomStairs(world, random, i - 6, j + 2, k13, 1);
                this.placeRandomStairs(world, random, i + 7, j, k13, 5);
                this.placeRandomStairs(world, random, i + 6, j + 2, k13, 0);
            }
            for (int i16 = i - 1; i16 <= i + 1; ++i16) {
                this.placeRandomStairs(world, random, i16, j, k - 7, 6);
                this.placeRandomStairs(world, random, i16, j + 2, k - 6, 3);
                this.placeRandomStairs(world, random, i16, j, k + 7, 7);
                this.placeRandomStairs(world, random, i16, j + 2, k + 6, 2);
            }
            for (j13 = j; j13 <= j + 4; ++j13) {
                this.setBlockAndNotifyAdequately(world, i - 5, j13, k - 5, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i - 5, j13, k + 5, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i + 5, j13, k - 5, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i + 5, j13, k + 5, LOTRMod.utumnoBrick, 2);
            }
            this.placeBanner(world, i - 5, j + 5, k - 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeBanner(world, i - 5, j + 5, k + 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeBanner(world, i + 5, j + 5, k - 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeBanner(world, i + 5, j + 5, k + 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeRandomStairs(world, random, i - 5, j + 2, k - 4, 3);
            this.placeRandomStairs(world, random, i - 4, j + 2, k - 5, 1);
            this.placeRandomStairs(world, random, i - 5, j + 2, k + 4, 2);
            this.placeRandomStairs(world, random, i - 4, j + 2, k + 5, 1);
            this.placeRandomStairs(world, random, i + 5, j + 2, k - 4, 3);
            this.placeRandomStairs(world, random, i + 4, j + 2, k - 5, 0);
            this.placeRandomStairs(world, random, i + 5, j + 2, k + 4, 2);
            this.placeRandomStairs(world, random, i + 4, j + 2, k + 5, 0);
        }
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(20) == 0) {
            return;
        }
        if (random.nextInt(3) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.utumnoBrick, 3);
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.utumnoBrick, 2);
        }
    }

    private void placeRandomStairs(World world, Random random, int i, int j, int k, int meta) {
        if (random.nextInt(10) == 0) {
            return;
        }
        if (random.nextInt(3) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.stairsUtumnoBrickIce, meta);
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.stairsUtumnoBrickIce, meta);
        }
    }
}

