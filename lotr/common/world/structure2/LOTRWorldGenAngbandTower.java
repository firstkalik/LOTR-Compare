/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityAngbandCap;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRWorldGenAngbandTower
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenAngbandTower(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int k1;
        int k12;
        int i1;
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
        int equipmentSection = 1 + random.nextInt(sections);
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
            for (j1 = j; !LOTRMod.isOpaque(world, i - 6, j1, k1) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i - 6, j1, k1, LOTRMod.utumnoBrick, 2);
            }
            for (j1 = j; !LOTRMod.isOpaque(world, i + 6, j1, k1) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i + 6, j1, k1, LOTRMod.utumnoBrick, 2);
            }
        }
        for (k1 = k - 4; k1 <= k + 4; ++k1) {
            for (j1 = j; !LOTRMod.isOpaque(world, i - 5, j1, k1) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i - 5, j1, k1, LOTRMod.utumnoBrick, 2);
            }
            for (j1 = j; !LOTRMod.isOpaque(world, i + 5, j1, k1) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i + 5, j1, k1, LOTRMod.utumnoBrick, 2);
            }
        }
        for (k1 = k - 5; k1 <= k + 5; ++k1) {
            for (i1 = i - 4; i1 <= i - 3; ++i1) {
                for (j12 = j; !LOTRMod.isOpaque(world, i1, j12, k1) && j12 >= 0; --j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.utumnoBrick, 2);
                }
            }
            for (i1 = i + 3; i1 <= i + 4; ++i1) {
                for (j12 = j; !LOTRMod.isOpaque(world, i1, j12, k1) && j12 >= 0; --j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.utumnoBrick, 2);
                }
            }
        }
        for (k1 = k - 6; k1 <= k + 6; ++k1) {
            for (i1 = i - 2; i1 <= i + 2; ++i1) {
                for (j12 = j; !LOTRMod.isOpaque(world, i1, j12, k1) && j12 >= 0; --j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.utumnoBrick, 2);
                }
            }
        }
        for (int l = 0; l <= sections; ++l) {
            this.generateTowerSection(world, random, i, j, k, l, false, l == equipmentSection);
        }
        this.generateTowerSection(world, random, i, j, k, sections + 1, true, false);
        LOTREntityAngbandCap trader = new LOTREntityAngbandCap(world);
        trader.setLocationAndAngles((double)i + 0.5, (double)(j + (sections + 1) * 8 + 1), (double)(k - 4) + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
        trader.onSpawnWithEgg(null);
        trader.setHomeArea(i, j + (sections + 1) * 8, k, 24);
        world.spawnEntityInWorld((Entity)trader);
        switch (rotation) {
            case 0: {
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j, k - 6, LOTRMod.slabUtumnoDouble, 1);
                    for (j12 = j + 1; j12 <= j + 4; ++j12) {
                        this.setBlockAndNotifyAdequately(world, i1, j12, k - 6, LOTRMod.gateOrc, 3);
                    }
                }
                this.placeWallBanner(world, i, j + 7, k - 6, 2, LOTRItemBanner.BannerType.DORNADAERACHAS);
                break;
            }
            case 1: {
                for (k12 = k - 1; k12 <= k + 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i + 6, j, k12, LOTRMod.slabUtumnoDouble, 1);
                    for (j12 = j + 1; j12 <= j + 4; ++j12) {
                        this.setBlockAndNotifyAdequately(world, i + 6, j12, k12, LOTRMod.gateOrc, 4);
                    }
                }
                this.placeWallBanner(world, i + 6, j + 7, k, 3, LOTRItemBanner.BannerType.DORNADAERACHAS);
                break;
            }
            case 2: {
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j, k + 6, LOTRMod.slabUtumnoDouble, 1);
                    for (j12 = j + 1; j12 <= j + 4; ++j12) {
                        this.setBlockAndNotifyAdequately(world, i1, j12, k + 6, LOTRMod.gateOrc, 2);
                    }
                }
                this.placeWallBanner(world, i, j + 7, k + 6, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
                break;
            }
            case 3: {
                for (k12 = k - 1; k12 <= k + 1; ++k12) {
                    this.setBlockAndNotifyAdequately(world, i - 6, j, k12, LOTRMod.slabUtumnoDouble, 1);
                    for (j12 = j + 1; j12 <= j + 4; ++j12) {
                        this.setBlockAndNotifyAdequately(world, i - 6, j12, k12, LOTRMod.gateOrc, 5);
                    }
                }
                this.placeWallBanner(world, i - 6, j + 7, k, 1, LOTRItemBanner.BannerType.DORNADAERACHAS);
            }
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClass(LOTREntityAngbandOrc.class);
        respawner.setCheckRanges(12, -8, 50, 20);
        respawner.setSpawnRanges(5, 1, 40, 16);
        this.placeNPCRespawner(respawner, world, i, j, k);
        return true;
    }

    private void generateTowerSection(World world, Random random, int i, int j, int k, int section, boolean isTop, boolean isEquipmentSection) {
        int j1;
        int k1;
        int i1;
        for (j1 = section == 0 ? j : (j += section * 8) + 1; j1 <= (isTop ? j + 10 : j + 8); ++j1) {
            int i12;
            Block fillBlock = Blocks.air;
            int fillMeta = 0;
            if (j1 == j) {
                fillBlock = LOTRMod.slabUtumnoDouble;
                fillMeta = 1;
            } else if (j1 == j + 8 && !isTop) {
                fillBlock = LOTRMod.slabUtumnoDouble;
                fillMeta = 1;
            } else {
                fillBlock = Blocks.air;
                fillMeta = 0;
            }
            for (k1 = k - 2; k1 <= k + 2; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 5, j1, k1, fillBlock, fillMeta);
                this.setBlockAndNotifyAdequately(world, i + 5, j1, k1, fillBlock, fillMeta);
            }
            for (k1 = k - 4; k1 <= k + 4; ++k1) {
                for (i12 = i - 4; i12 <= i - 3; ++i12) {
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, fillBlock, fillMeta);
                }
                for (i12 = i + 3; i12 <= i + 4; ++i12) {
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, fillBlock, fillMeta);
                }
            }
            for (k1 = k - 5; k1 <= k + 5; ++k1) {
                for (i12 = i - 2; i12 <= i + 2; ++i12) {
                    this.setBlockAndNotifyAdequately(world, i12, j1, k1, fillBlock, fillMeta);
                }
            }
        }
        for (j1 = j + 1; j1 <= (isTop ? j + 1 : j + 8); ++j1) {
            for (k1 = k - 2; k1 <= k + 2; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 6, j1, k1, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i + 6, j1, k1, LOTRMod.utumnoBrick, 2);
            }
            for (int i13 = i - 2; i13 <= i + 2; ++i13) {
                this.setBlockAndNotifyAdequately(world, i13, j1, k - 6, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i13, j1, k + 6, LOTRMod.utumnoBrick, 2);
            }
            this.setBlockAndNotifyAdequately(world, i - 5, j1, k - 4, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j1, k - 3, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j1, k + 3, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j1, k + 4, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 4, j1, k - 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 4, j1, k + 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 3, j1, k - 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 3, j1, k + 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 3, j1, k - 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 3, j1, k + 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, j1, k - 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, j1, k + 5, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j1, k - 4, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j1, k - 3, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j1, k + 3, LOTRMod.utumnoBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j1, k + 4, LOTRMod.utumnoBrick, 2);
        }
        this.placeOrcTorch(world, i - 5, j + 1, k - 2);
        this.placeOrcTorch(world, i - 5, j + 1, k + 2);
        this.placeOrcTorch(world, i + 5, j + 1, k - 2);
        this.placeOrcTorch(world, i + 5, j + 1, k + 2);
        this.placeOrcTorch(world, i - 2, j + 1, k - 5);
        this.placeOrcTorch(world, i + 2, j + 1, k - 5);
        this.placeOrcTorch(world, i - 2, j + 1, k + 5);
        this.placeOrcTorch(world, i + 2, j + 1, k + 5);
        if (!isTop) {
            for (j1 = j + 2; j1 <= j + 4; ++j1) {
                for (k1 = k - 1; k1 <= k + 1; ++k1) {
                    this.setBlockAndNotifyAdequately(world, i - 6, j1, k1, LOTRMod.orcSteelBars, 0);
                    this.setBlockAndNotifyAdequately(world, i + 6, j1, k1, LOTRMod.orcSteelBars, 0);
                }
                for (int i14 = i - 1; i14 <= i + 1; ++i14) {
                    this.setBlockAndNotifyAdequately(world, i14, j1, k - 6, LOTRMod.orcSteelBars, 0);
                    this.setBlockAndNotifyAdequately(world, i14, j1, k + 6, LOTRMod.orcSteelBars, 0);
                }
            }
            for (i1 = i - 2; i1 <= i + 2; ++i1) {
                for (k1 = k - 2; k1 <= k + 2; ++k1) {
                    this.setBlockAndNotifyAdequately(world, i1, j + 8, k1, Blocks.air, 0);
                }
            }
            this.setBlockAndNotifyAdequately(world, i - 2, j + 1, k + 1, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 1, k + 2, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 2, k + 2, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i, j + 2, k + 2, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k + 2, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 3, k + 2, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 4, k + 1, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 4, k, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k - 1, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i + 2, j + 5, k - 2, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 6, k - 2, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i, j + 6, k - 2, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i - 1, j + 7, k - 2, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 7, k - 2, LOTRMod.slabUtumnoDouble, 4);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 8, k - 1, LOTRMod.slabUtumnoSingle, 4);
            this.setBlockAndNotifyAdequately(world, i - 2, j + 8, k, LOTRMod.slabUtumnoDouble, 4);
        }
        for (i1 = i - 1; i1 <= i + 1; ++i1) {
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                for (int j12 = j + 1; j12 <= (isTop ? j + 3 : j + 8); ++j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.utumnoBrick, 2);
                }
            }
        }
        if (isEquipmentSection) {
            int l = random.nextInt(4);
            switch (l) {
                case 0: {
                    for (int i15 = i - 1; i15 <= i + 1; ++i15) {
                        this.setBlockAndNotifyAdequately(world, i15, j + 1, k - 5, LOTRMod.orcBomb, 0);
                        this.setBlockAndNotifyAdequately(world, i15, j + 1, k + 5, LOTRMod.slabUtumnoDouble, 4);
                        this.placeBarrel(world, random, i15, j + 2, k + 5, 2, LOTRFoods.ORC_DRINK);
                    }
                    break;
                }
                case 1: {
                    int k12;
                    for (k12 = k - 1; k12 <= k + 1; ++k12) {
                        this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k12, LOTRMod.orcBomb, 0);
                        this.setBlockAndNotifyAdequately(world, i - 5, j + 1, k12, LOTRMod.slabUtumnoDouble, 4);
                        this.placeBarrel(world, random, i - 5, j + 2, k12, 5, LOTRFoods.ORC_DRINK);
                    }
                    break;
                }
                case 2: {
                    for (int i16 = i - 1; i16 <= i + 1; ++i16) {
                        this.setBlockAndNotifyAdequately(world, i16, j + 1, k + 5, LOTRMod.orcBomb, 0);
                        this.setBlockAndNotifyAdequately(world, i16, j + 1, k - 5, LOTRMod.slabUtumnoDouble, 4);
                        this.placeBarrel(world, random, i16, j + 2, k - 5, 3, LOTRFoods.ORC_DRINK);
                    }
                    break;
                }
                case 3: {
                    int k12;
                    for (k12 = k - 1; k12 <= k + 1; ++k12) {
                        this.setBlockAndNotifyAdequately(world, i - 5, j + 1, k12, LOTRMod.orcBomb, 0);
                        this.setBlockAndNotifyAdequately(world, i + 5, j + 1, k12, LOTRMod.slabUtumnoDouble, 4);
                        this.placeBarrel(world, random, i + 5, j + 2, k12, 4, LOTRFoods.ORC_DRINK);
                    }
                    break;
                }
            }
        }
        if (isTop) {
            for (j1 = j + 1; j1 <= j + 8; ++j1) {
                for (k1 = k - 1; k1 <= k + 1; ++k1) {
                    this.setBlockAndNotifyAdequately(world, i - 7, j1, k1, LOTRMod.utumnoBrick, 2);
                    this.setBlockAndNotifyAdequately(world, i + 7, j1, k1, LOTRMod.utumnoBrick, 2);
                }
                for (int i17 = i - 1; i17 <= i + 1; ++i17) {
                    this.setBlockAndNotifyAdequately(world, i17, j1, k - 7, LOTRMod.utumnoBrick, 2);
                    this.setBlockAndNotifyAdequately(world, i17, j1, k + 7, LOTRMod.utumnoBrick, 2);
                }
            }
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 7, j, k1, LOTRMod.stairsUtumnoBrickIce, 4);
                this.setBlockAndNotifyAdequately(world, i - 6, j + 2, k1, LOTRMod.stairsUtumnoBrickIce, 1);
                this.setBlockAndNotifyAdequately(world, i - 7, j + 9, k1, LOTRMod.stairsUtumnoBrickIce, 0);
                this.setBlockAndNotifyAdequately(world, i - 6, j + 9, k1, LOTRMod.stairsUtumnoBrickIce, 5);
                this.setBlockAndNotifyAdequately(world, i - 6, j + 10, k1, LOTRMod.stairsUtumnoBrickIce, 0);
                this.setBlockAndNotifyAdequately(world, i + 7, j, k1, LOTRMod.stairsUtumnoBrickIce, 5);
                this.setBlockAndNotifyAdequately(world, i + 6, j + 2, k1, LOTRMod.stairsUtumnoBrickIce, 0);
                this.setBlockAndNotifyAdequately(world, i + 7, j + 9, k1, LOTRMod.stairsUtumnoBrickIce, 1);
                this.setBlockAndNotifyAdequately(world, i + 6, j + 9, k1, LOTRMod.stairsUtumnoBrickIce, 4);
                this.setBlockAndNotifyAdequately(world, i + 6, j + 10, k1, LOTRMod.stairsUtumnoBrickIce, 1);
            }
            for (i1 = i - 1; i1 <= i + 1; ++i1) {
                this.setBlockAndNotifyAdequately(world, i1, j, k - 7, LOTRMod.stairsUtumnoBrickIce, 6);
                this.setBlockAndNotifyAdequately(world, i1, j + 2, k - 6, LOTRMod.stairsUtumnoBrickIce, 3);
                this.setBlockAndNotifyAdequately(world, i1, j + 9, k - 7, LOTRMod.stairsUtumnoBrickIce, 2);
                this.setBlockAndNotifyAdequately(world, i1, j + 9, k - 6, LOTRMod.stairsUtumnoBrickIce, 7);
                this.setBlockAndNotifyAdequately(world, i1, j + 10, k - 6, LOTRMod.stairsUtumnoBrickIce, 2);
                this.setBlockAndNotifyAdequately(world, i1, j, k + 7, LOTRMod.stairsUtumnoBrickIce, 7);
                this.setBlockAndNotifyAdequately(world, i1, j + 2, k + 6, LOTRMod.stairsUtumnoBrickIce, 2);
                this.setBlockAndNotifyAdequately(world, i1, j + 9, k + 7, LOTRMod.stairsUtumnoBrickIce, 3);
                this.setBlockAndNotifyAdequately(world, i1, j + 9, k + 6, LOTRMod.stairsUtumnoBrickIce, 6);
                this.setBlockAndNotifyAdequately(world, i1, j + 10, k + 6, LOTRMod.stairsUtumnoBrickIce, 3);
            }
            for (j1 = j; j1 <= j + 4; ++j1) {
                this.setBlockAndNotifyAdequately(world, i - 5, j1, k - 5, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i - 5, j1, k + 5, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i + 5, j1, k - 5, LOTRMod.utumnoBrick, 2);
                this.setBlockAndNotifyAdequately(world, i + 5, j1, k + 5, LOTRMod.utumnoBrick, 2);
            }
            this.placeBanner(world, i - 5, j + 5, k - 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeBanner(world, i - 5, j + 5, k + 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeBanner(world, i + 5, j + 5, k - 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.placeBanner(world, i + 5, j + 5, k + 5, 0, LOTRItemBanner.BannerType.DORNADAERACHAS);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 2, k - 4, LOTRMod.stairsUtumnoBrickIce, 3);
            this.setBlockAndNotifyAdequately(world, i - 4, j + 2, k - 5, LOTRMod.stairsUtumnoBrickIce, 1);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 2, k + 4, LOTRMod.stairsUtumnoBrickIce, 2);
            this.setBlockAndNotifyAdequately(world, i - 4, j + 2, k + 5, LOTRMod.stairsUtumnoBrickIce, 1);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 2, k - 4, LOTRMod.stairsUtumnoBrickIce, 3);
            this.setBlockAndNotifyAdequately(world, i + 4, j + 2, k - 5, LOTRMod.stairsUtumnoBrickIce, 0);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 2, k + 4, LOTRMod.stairsUtumnoBrickIce, 2);
            this.setBlockAndNotifyAdequately(world, i + 4, j + 2, k + 5, LOTRMod.stairsUtumnoBrickIce, 0);
        }
    }
}

