/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityDwarfCommander;
import lotr.common.entity.npc.LOTREntityDwarfWarrior;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemBanner;
import lotr.common.tileentity.LOTRTileEntityAlloyForge;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRWorldGenDwarvenTower
extends LOTRWorldGenStructureBase2 {
    protected Block brickBlock = LOTRMod.brick;
    protected int brickMeta = 6;
    protected Block brickSlabBlock = LOTRMod.slabSingle;
    protected int brickSlabMeta = 7;
    protected Block brickStairBlock = LOTRMod.stairsDwarvenBrick;
    protected Block brickWallBlock = LOTRMod.wall;
    protected int brickWallMeta = 7;
    protected Block pillarBlock = LOTRMod.pillar;
    protected int pillarMeta = 0;
    protected Block plankBlock;
    protected int plankMeta;
    protected Block plankSlabBlock;
    protected int plankSlabMeta;
    protected Block barsBlock = LOTRMod.dwarfBars;
    protected Block gateBlock = LOTRMod.gateDwarven;
    protected Block tableBlock = LOTRMod.dwarvenTable;
    protected Block forgeBlock = LOTRMod.dwarvenForge;
    protected Block glowBrickBlock = LOTRMod.brick3;
    protected int glowBrickMeta = 12;
    protected Block plateBlock;
    protected LOTRItemBanner.BannerType bannerType = LOTRItemBanner.BannerType.DWARF;
    protected LOTRChestContents chestContents = LOTRChestContents.DWARVEN_TOWER;
    protected boolean ruined = false;

    public LOTRWorldGenDwarvenTower(boolean flag) {
        super(flag);
    }

    protected LOTREntityNPC getCommanderNPC(World world) {
        return new LOTREntityDwarfCommander(world);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        int randomWood = random.nextInt(4);
        if (randomWood == 0) {
            this.plankBlock = Blocks.planks;
            this.plankMeta = 1;
            this.plankSlabBlock = Blocks.wooden_slab;
            this.plankSlabMeta = 1;
        } else if (randomWood == 1) {
            this.plankBlock = LOTRMod.planks;
            this.plankMeta = 13;
            this.plankSlabBlock = LOTRMod.woodSlabSingle2;
            this.plankSlabMeta = 5;
        } else if (randomWood == 2) {
            this.plankBlock = LOTRMod.planks2;
            this.plankMeta = 4;
            this.plankSlabBlock = LOTRMod.woodSlabSingle3;
            this.plankSlabMeta = 4;
        } else if (randomWood == 3) {
            this.plankBlock = LOTRMod.planks2;
            this.plankMeta = 3;
            this.plankSlabBlock = LOTRMod.woodSlabSingle3;
            this.plankSlabMeta = 3;
        }
        this.plateBlock = random.nextBoolean() ? LOTRMod.ceramicPlateBlock : LOTRMod.woodPlateBlock;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int k12;
        int i1;
        int j1;
        int j12;
        LOTREntityNPC commander;
        this.setOriginAndRotation(world, i, j, k, rotation, 6);
        this.setupRandomBlocks(random);
        int sections = 5 + random.nextInt(3);
        if (this.restrictions) {
            for (i1 = -6; i1 <= 6; ++i1) {
                for (k1 = -6; k1 <= 6; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1);
                    Block block = this.getBlock(world, i1, j1 - 1, k1);
                    if (block == Blocks.grass || block == Blocks.stone || block == Blocks.snow) continue;
                    return false;
                }
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (k1 = -5; k1 <= 5; ++k1) {
                for (j1 = 0; !(j1 != 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.placeBrick(world, random, i1, j1, k1);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            for (k1 = -4; k1 <= 4; ++k1) {
                boolean flag = true;
                if (this.ruined) {
                    boolean bl = flag = random.nextInt(12) != 0;
                }
                if (!flag) continue;
                this.setBlockAndMetadata(world, i1, 0, k1, this.plankBlock, this.plankMeta);
            }
        }
        for (int l = 0; l <= sections; ++l) {
            int i12;
            int i13;
            int k13;
            int j13;
            int k2;
            Object k14;
            int j14;
            int sectionBase = l * 5;
            for (i12 = -4; i12 <= 4; ++i12) {
                for (j14 = sectionBase + 1; j14 <= sectionBase + 5; ++j14) {
                    for (k14 = -4; k14 <= 4; ++k14) {
                        this.setAir(world, i12, j14, (int)k14);
                        this.setAir(world, i12, j14, (int)k14);
                    }
                }
            }
            for (j1 = sectionBase + 1; j1 <= sectionBase + 5; ++j1) {
                boolean flag;
                int n;
                int n2;
                for (int i14 = -5; i14 <= 5; ++i14) {
                    k14 = new int[]{-5, 5};
                    n2 = ((int)k14).length;
                    for (n = 0; n < n2; ++n) {
                        void k15 = k14[n];
                        flag = true;
                        if (this.ruined) {
                            boolean bl = flag = random.nextInt(20) != 0;
                        }
                        if (!flag) continue;
                        this.placeBrick(world, random, i14, j1, (int)k15);
                    }
                }
                for (k13 = -4; k13 <= 4; ++k13) {
                    k14 = new int[]{-5, 5};
                    n2 = ((int)k14).length;
                    for (n = 0; n < n2; ++n) {
                        void i15 = k14[n];
                        flag = true;
                        if (this.ruined) {
                            boolean bl = flag = random.nextInt(20) != 0;
                        }
                        if (!flag) continue;
                        this.placeBrick(world, random, (int)i15, j1, k13);
                    }
                }
            }
            this.placePillar(world, random, -4, sectionBase + 1, -4);
            this.placePillar(world, random, -4, sectionBase + 2, -4);
            this.setBlockAndMetadata(world, -4, sectionBase + 3, -4, this.glowBrickBlock, this.glowBrickMeta);
            this.placePillar(world, random, -4, sectionBase + 4, -4);
            this.placePillar(world, random, -4, sectionBase + 1, 4);
            this.placePillar(world, random, -4, sectionBase + 2, 4);
            this.setBlockAndMetadata(world, -4, sectionBase + 3, 4, this.glowBrickBlock, this.glowBrickMeta);
            this.placePillar(world, random, -4, sectionBase + 4, 4);
            this.placePillar(world, random, 4, sectionBase + 1, -4);
            this.placePillar(world, random, 4, sectionBase + 2, -4);
            this.setBlockAndMetadata(world, 4, sectionBase + 3, -4, this.glowBrickBlock, this.glowBrickMeta);
            this.placePillar(world, random, 4, sectionBase + 4, -4);
            this.placePillar(world, random, 4, sectionBase + 1, 4);
            this.placePillar(world, random, 4, sectionBase + 2, 4);
            this.setBlockAndMetadata(world, 4, sectionBase + 3, 4, this.glowBrickBlock, this.glowBrickMeta);
            this.placePillar(world, random, 4, sectionBase + 4, 4);
            for (i12 = -4; i12 <= 4; ++i12) {
                for (k13 = -4; k13 <= 4; ++k13) {
                    boolean flag = true;
                    if (this.ruined) {
                        boolean bl = flag = random.nextInt(12) != 0;
                    }
                    if (!flag) continue;
                    this.setBlockAndMetadata(world, i12, sectionBase + 5, k13, this.plankBlock, this.plankMeta);
                }
            }
            for (k12 = -2; k12 <= 2; ++k12) {
                for (j14 = sectionBase + 1; j14 <= sectionBase + 4; ++j14) {
                    if (Math.abs(k12) < 2 && (j14 == sectionBase + 2 || j14 == sectionBase + 3)) {
                        this.setBlockAndMetadata(world, -5, j14, k12, this.barsBlock, 0);
                        this.setBlockAndMetadata(world, 5, j14, k12, this.barsBlock, 0);
                        continue;
                    }
                    this.placePillar(world, random, -5, j14, k12);
                    this.placePillar(world, random, 5, j14, k12);
                }
            }
            int randomFeature = random.nextInt(5);
            if (l % 2 == 0) {
                for (k13 = -1; k13 <= 4; ++k13) {
                    for (i13 = 1; i13 <= 2; ++i13) {
                        this.setAir(world, i13, sectionBase + 5, k13);
                        k2 = k13 - -1;
                        for (j13 = sectionBase + 1; j13 <= sectionBase + k2; ++j13) {
                            this.placeBrick(world, random, i13, j13, k13);
                        }
                        if (k2 >= 5) continue;
                        this.placeBrickStair(world, random, i13, sectionBase + k2 + 1, k13, 2);
                    }
                }
                this.placeRandomFeature(world, random, -2, sectionBase + 1, 4, randomFeature, false);
                this.placeRandomFeature(world, random, -1, sectionBase + 1, 4, randomFeature, false);
                this.setBlockAndMetadata(world, 0, sectionBase + 1, 4, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, -3, sectionBase + 1, 4, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, 0, sectionBase + 2, 4, this.plankSlabBlock, this.plankSlabMeta);
                this.setBlockAndMetadata(world, -3, sectionBase + 2, 4, this.plankSlabBlock, this.plankSlabMeta);
            } else {
                for (k13 = -4; k13 <= 1; ++k13) {
                    for (i13 = -2; i13 <= -1; ++i13) {
                        this.setAir(world, i13, sectionBase + 5, k13);
                        k2 = 5 - (k13 - -4);
                        for (j13 = sectionBase + 1; j13 <= sectionBase + k2; ++j13) {
                            this.placeBrick(world, random, i13, j13, k13);
                        }
                        if (k2 >= 5) continue;
                        this.placeBrickStair(world, random, i13, sectionBase + k2 + 1, k13, 3);
                    }
                }
                this.placeRandomFeature(world, random, 2, sectionBase + 1, -4, randomFeature, true);
                this.placeRandomFeature(world, random, 1, sectionBase + 1, -4, randomFeature, true);
                this.setBlockAndMetadata(world, 0, sectionBase + 1, -4, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, 3, sectionBase + 1, -4, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, 0, sectionBase + 2, -4, this.plankSlabBlock, this.plankSlabMeta);
                this.setBlockAndMetadata(world, 3, sectionBase + 2, -4, this.plankSlabBlock, this.plankSlabMeta);
            }
            if (this.ruined) continue;
            LOTREntityDwarfWarrior dwarf = random.nextInt(3) == 0 ? new LOTREntityDwarfAxeThrower(world) : new LOTREntityDwarfWarrior(world);
            this.spawnNPCAndSetHome(dwarf, world, 0, sectionBase + 1, 0, 12);
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            for (j12 = 1; j12 <= 2; ++j12) {
                for (k12 = -4; k12 <= 4; ++k12) {
                    this.setAir(world, i1, (sections + 1) * 5 + j12, k12);
                }
            }
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            this.placeBrickWall(world, random, i1, (sections + 1) * 5 + 1, -5);
            this.placeBrickWall(world, random, i1, (sections + 1) * 5 + 1, 5);
        }
        for (int k16 = -4; k16 <= 4; ++k16) {
            this.placeBrickWall(world, random, -5, (sections + 1) * 5 + 1, k16);
            this.placeBrickWall(world, random, 5, (sections + 1) * 5 + 1, k16);
        }
        this.generateCornerPillars(world, random, -5, (sections + 1) * 5 + 5, -5);
        this.generateCornerPillars(world, random, -5, (sections + 1) * 5 + 5, 6);
        this.generateCornerPillars(world, random, 6, (sections + 1) * 5 + 5, -5);
        this.generateCornerPillars(world, random, 6, (sections + 1) * 5 + 5, 6);
        for (i1 = -1; i1 <= 1; ++i1) {
            this.placePillar(world, random, i1, 0, -5);
            for (j12 = 1; j12 <= 4; ++j12) {
                this.setBlockAndMetadata(world, i1, j12, -5, this.gateBlock, 2);
            }
        }
        for (int i14 : new int[]{-2, 2}) {
            int j15 = 4;
            while (!this.isOpaque(world, i14, j15, -6) && this.getY(j15) >= 0) {
                if (j15 == 3) {
                    this.setBlockAndMetadata(world, i14, j15, -6, this.glowBrickBlock, this.glowBrickMeta);
                } else {
                    this.placePillar(world, random, i14, j15, -6);
                }
                this.setGrassToDirt(world, i14, j15 - 1, -6);
                --j15;
            }
        }
        for (int i16 = -2; i16 <= 2; ++i16) {
            this.placeBrickSlab(world, random, i16, 5, -6, false);
        }
        if (this.bannerType != null) {
            this.placeWallBanner(world, -2, 7, -5, this.bannerType, 2);
            this.placeWallBanner(world, 0, 8, -5, this.bannerType, 2);
            this.placeWallBanner(world, 2, 7, -5, this.bannerType, 2);
        }
        if ((commander = this.getCommanderNPC(world)) != null) {
            this.spawnNPCAndSetHome(commander, world, 0, (sections + 1) * 5 + 1, 0, 16);
            if (sections % 2 == 0) {
                this.setBlockAndMetadata(world, -3, (sections + 1) * 5 + 1, -3, LOTRMod.commandTable, 0);
            } else {
                this.setBlockAndMetadata(world, 3, (sections + 1) * 5 + 1, 3, LOTRMod.commandTable, 0);
            }
        }
        this.placePillar(world, random, -4, (sections + 1) * 5 + 1, 0);
        this.placePillar(world, random, -4, (sections + 1) * 5 + 2, 0);
        this.placePillar(world, random, 4, (sections + 1) * 5 + 1, 0);
        this.placePillar(world, random, 4, (sections + 1) * 5 + 2, 0);
        if (this.bannerType != null) {
            this.placeBrick(world, random, -4, (sections + 1) * 5 + 1, 0);
            this.placeBanner(world, -4, (sections + 1) * 5 + 1, 0, this.bannerType, 1);
            this.placeBrick(world, random, 4, (sections + 1) * 5 + 1, 0);
            this.placeBanner(world, 4, (sections + 1) * 5 + 1, 0, this.bannerType, 3);
        }
        if (!this.ruined) {
            LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
            respawner.setSpawnClasses(LOTREntityDwarfWarrior.class, LOTREntityDwarfAxeThrower.class);
            respawner.setCheckRanges(12, -8, 42, 16);
            respawner.setSpawnRanges(4, 1, 41, 12);
            this.placeNPCRespawner(respawner, world, 0, 0, 0);
        }
        return true;
    }

    protected void placeBrick(World world, Random random, int i, int j, int k) {
        this.setBlockAndMetadata(world, i, j, k, this.brickBlock, this.brickMeta);
    }

    protected void placeBrickSlab(World world, Random random, int i, int j, int k, boolean flip) {
        this.setBlockAndMetadata(world, i, j, k, this.brickSlabBlock, this.brickSlabMeta | (flip ? 8 : 0));
    }

    protected void placeBrickStair(World world, Random random, int i, int j, int k, int meta) {
        this.setBlockAndMetadata(world, i, j, k, this.brickStairBlock, meta);
    }

    protected void placeBrickWall(World world, Random random, int i, int j, int k) {
        this.setBlockAndMetadata(world, i, j, k, this.brickWallBlock, this.brickWallMeta);
    }

    protected void placePillar(World world, Random random, int i, int j, int k) {
        this.setBlockAndMetadata(world, i, j, k, this.pillarBlock, this.pillarMeta);
    }

    private void generateCornerPillars(World world, Random random, int i, int j, int k) {
        for (int i1 = i - 1; i1 <= i; ++i1) {
            for (int k1 = k - 1; k1 <= k; ++k1) {
                for (int j1 = j; !(j1 != 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    if (j1 == j - 2) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.glowBrickBlock, this.glowBrickMeta);
                        continue;
                    }
                    this.placePillar(world, random, i1, j1, k1);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
            }
        }
    }

    private void placeRandomFeature(World world, Random random, int i, int j, int k, int randomFeature, boolean flip) {
        if (randomFeature == 0) {
            this.setBlockAndMetadata(world, i, j, k, this.tableBlock, 0);
        } else if (randomFeature == 1) {
            this.setBlockAndMetadata(world, i, j, k, this.forgeBlock, flip ? 3 : 2);
            TileEntity tileentity = this.getTileEntity(world, i, j, k);
            if (tileentity instanceof LOTRTileEntityAlloyForge) {
                ((LOTRTileEntityAlloyForge)tileentity).setInventorySlotContents(12, new ItemStack(Items.coal, 1 + random.nextInt(4)));
            }
        } else if (randomFeature == 2) {
            this.setBlockAndMetadata(world, i, j, k, this.plankSlabBlock, this.plankSlabMeta | 8);
            this.placeChest(world, random, i, j + 1, k, flip ? 3 : 2, this.chestContents);
        } else if (randomFeature == 3) {
            this.setBlockAndMetadata(world, i, j, k, this.plankSlabBlock, this.plankSlabMeta | 8);
            if (!this.ruined) {
                this.placePlateWithCertainty(world, random, i, j + 1, k, this.plateBlock, LOTRFoods.DWARF);
            }
        } else if (randomFeature == 4) {
            this.setBlockAndMetadata(world, i, j, k, this.plankSlabBlock, this.plankSlabMeta | 8);
            if (!this.ruined) {
                this.placeBarrel(world, random, i, j + 1, k, flip ? 3 : 2, LOTRFoods.DWARF_DRINK);
            }
        }
    }
}

