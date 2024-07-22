/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockGateDwarvenIthildin;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenDwarfHouse6
extends LOTRWorldGenStructureBase2 {
    protected Block stoneBlock;
    protected int stoneMeta;
    protected Block fillerBlock;
    protected int fillerMeta;
    protected Block topBlock;
    protected int topMeta;
    protected Block brickBlock;
    protected int brickMeta;
    protected Block brickStairBlock;
    protected Block brick2Block;
    protected int brick2Meta;
    protected Block pillarBlock;
    protected int pillarMeta;
    protected Block chandelierBlock;
    protected int chandelierMeta;
    protected Block tableBlock;
    protected Block barsBlock;
    protected Block plankBlock;
    protected int plankMeta;
    protected Block plankSlabBlock;
    protected int plankSlabMeta;
    protected Block plankStairBlock;
    protected Block carpetBlock;
    protected int carpetMeta;
    protected Block plateBlock;
    protected LOTRChestContents larderContents;
    protected LOTRChestContents personalContents;
    protected LOTRFoods plateFoods;
    protected LOTRFoods drinkFoods;
    protected LOTRItemBanner.BannerType bannerType = LOTRItemBanner.BannerType.WIND;

    public LOTRWorldGenDwarfHouse6(boolean flag) {
        super(flag);
    }

    protected LOTREntityDwarf createDwarf(World world) {
        return new LOTREntityDwarf(world);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        this.stoneBlock = Blocks.stone;
        this.stoneMeta = 0;
        this.fillerBlock = Blocks.dirt;
        this.fillerMeta = 0;
        this.topBlock = Blocks.grass;
        this.topMeta = 0;
        this.brickBlock = LOTRMod.brick9;
        this.brickMeta = 4;
        this.brickStairBlock = LOTRMod.stairsWindDwarf;
        this.brick2Block = Blocks.stonebrick;
        this.brick2Meta = 0;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 0;
        this.chandelierBlock = LOTRMod.chandelier2;
        this.chandelierMeta = 0;
        this.tableBlock = LOTRMod.dwarvenTable;
        this.barsBlock = LOTRMod.dwarfBars;
        int randomWood = random.nextInt(4);
        if (randomWood == 0) {
            this.plankBlock = Blocks.planks;
            this.plankMeta = 1;
            this.plankSlabBlock = Blocks.wooden_slab;
            this.plankSlabMeta = 1;
            this.plankStairBlock = Blocks.spruce_stairs;
        } else if (randomWood == 1) {
            this.plankBlock = LOTRMod.planks;
            this.plankMeta = 13;
            this.plankSlabBlock = LOTRMod.woodSlabSingle2;
            this.plankSlabMeta = 5;
            this.plankStairBlock = LOTRMod.stairsLarch;
        } else if (randomWood == 2) {
            this.plankBlock = LOTRMod.planks2;
            this.plankMeta = 4;
            this.plankSlabBlock = LOTRMod.woodSlabSingle3;
            this.plankSlabMeta = 4;
            this.plankStairBlock = LOTRMod.stairsPine;
        } else if (randomWood == 3) {
            this.plankBlock = LOTRMod.planks2;
            this.plankMeta = 3;
            this.plankSlabBlock = LOTRMod.woodSlabSingle3;
            this.plankSlabMeta = 3;
            this.plankStairBlock = LOTRMod.stairsFir;
        }
        this.carpetBlock = Blocks.carpet;
        int randomCarpet = random.nextInt(3);
        if (randomCarpet == 0) {
            this.carpetMeta = 7;
        } else if (randomCarpet == 1) {
            this.carpetMeta = 12;
        } else if (randomCarpet == 2) {
            this.carpetMeta = 15;
        }
        this.plateBlock = random.nextBoolean() ? LOTRMod.ceramicPlateBlock : LOTRMod.woodPlateBlock;
        this.larderContents = LOTRChestContents.DWARF_HOUSE_LARDER;
        this.personalContents = LOTRChestContents.DWARVEN_TOWER;
        this.plateFoods = LOTRFoods.DWARF;
        this.drinkFoods = LOTRFoods.DWARF_DRINK;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j13;
        int j1;
        int i1;
        int j12;
        int i12;
        int k2;
        int i2;
        int i13;
        int k1;
        int k12;
        if (this.restrictions && this.usingPlayer == null) {
            this.setOriginAndRotation(world, i, j, k, rotation, 0);
            int xzRange = 5;
            int yRange = 4;
            for (i12 = -xzRange; i12 <= xzRange; ++i12) {
                for (int j14 = -yRange; j14 <= yRange; ++j14) {
                    for (int k13 = -xzRange; k13 <= xzRange; ++k13) {
                        if (!this.isAir(world, i12, j14, k13)) continue;
                        return false;
                    }
                }
            }
        } else {
            this.setOriginAndRotation(world, i, j, k, rotation, 8);
        }
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i13 = -1; i13 <= 1; ++i13) {
                for (j13 = 1; j13 <= 2; ++j13) {
                    boolean foundAir = false;
                    for (int k14 = -8; k14 >= -14; --k14) {
                        if (!this.isAir(world, i13, j13, k14)) continue;
                        foundAir = true;
                        break;
                    }
                    if (foundAir) continue;
                    return false;
                }
            }
            for (i13 = -1; i13 <= 1; ++i13) {
                for (j13 = 1; j13 <= 2; ++j13) {
                    for (int k15 = -8; k15 >= -14 && !this.isAir(world, i13, j13, k15); --k15) {
                        this.setAir(world, i13, j13, k15);
                        if (j13 != 1) continue;
                        this.setBlockAndMetadata(world, i13, j13 - 1, k15, this.stoneBlock, this.stoneMeta);
                    }
                }
            }
        }
        for (i13 = -7; i13 <= 7; ++i13) {
            for (k12 = -7; k12 <= 7; ++k12) {
                i2 = Math.abs(i13);
                k2 = Math.abs(k12);
                int dist = (int)Math.round(Math.sqrt(i2 * i2 + k2 * k2));
                int top = 13 - dist;
                for (int j15 = top = Math.min(top, 7); !(j15 < -5 && this.isOpaque(world, i13, j15, k12) || this.getY(j15) < 0); --j15) {
                    if (this.isOpaque(world, i13, j15, k12)) continue;
                    Block block = null;
                    int meta = -1;
                    if (j15 >= top - 4) {
                        if (this.isOpaque(world, i13, j15 + 1, k12)) {
                            block = this.fillerBlock;
                            meta = this.fillerMeta;
                        } else {
                            block = this.topBlock;
                            meta = this.topMeta;
                        }
                    } else {
                        block = this.stoneBlock;
                        meta = this.stoneMeta;
                    }
                    if (block == null) continue;
                    this.setBlockAndMetadata(world, i13, j15, k12, block, meta);
                    this.setGrassToDirt(world, i13, j15 - 1, k12);
                }
            }
        }
        for (j1 = 1; j1 <= 3; ++j1) {
            int i22 = 5 - j1;
            if (j1 >= 3) {
                --i22;
            }
            for (i12 = -i22; i12 <= i22; ++i12) {
                this.setBlockAndMetadata(world, i12, j1, -7, this.stoneBlock, this.stoneMeta);
            }
        }
        for (i13 = -11; i13 <= 11; ++i13) {
            for (k12 = -11; k12 <= 11; ++k12) {
                int top;
                i2 = Math.abs(i13);
                k2 = Math.abs(k12);
                if (i2 <= 7 && k2 <= 7) continue;
                int i3 = Math.min(i2, k2);
                int k3 = Math.max(i2, k2);
                int diff = k3 - 8;
                for (int limit : new int[]{4, 7, 9}) {
                    if (i3 < limit) continue;
                    diff += i3 - limit;
                }
                int j16 = top = 0 - (i3 + diff) / 2;
                while (!this.isOpaque(world, i13, j16, k12) && this.getY(j16) >= 0) {
                    Block block = null;
                    int meta = -1;
                    if (j16 >= top - 4) {
                        if (this.isOpaque(world, i13, j16 + 1, k12)) {
                            block = this.fillerBlock;
                            meta = this.fillerMeta;
                        } else {
                            block = this.topBlock;
                            meta = this.topMeta;
                        }
                    } else {
                        block = this.stoneBlock;
                        meta = this.stoneMeta;
                    }
                    if (block != null) {
                        this.setBlockAndMetadata(world, i13, j16, k12, block, meta);
                        this.setGrassToDirt(world, i13, j16 - 1, k12);
                    }
                    --j16;
                }
            }
        }
        for (i13 = -6; i13 <= 6; ++i13) {
            for (k12 = -6; k12 <= 6; ++k12) {
                for (j12 = -4; j12 <= 4; ++j12) {
                    if (Math.abs(i13) == 6 || Math.abs(k12) == 6) {
                        if (j12 == 2) {
                            this.setBlockAndMetadata(world, i13, j12, k12, this.plankBlock, this.plankMeta);
                            continue;
                        }
                        this.setBlockAndMetadata(world, i13, j12, k12, this.brick2Block, this.brick2Meta);
                        continue;
                    }
                    if (j12 == 0 || Math.abs(j12) == 4) {
                        this.setBlockAndMetadata(world, i13, j12, k12, this.brick2Block, this.brick2Meta);
                        continue;
                    }
                    this.setAir(world, i13, j12, k12);
                }
            }
        }
        for (j1 = -3; j1 <= 3; ++j1) {
            if (j1 == 0) continue;
            this.setBlockAndMetadata(world, -5, j1, -5, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, -5, j1, 5, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, 5, j1, -5, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, 5, j1, 5, this.pillarBlock, this.pillarMeta);
        }
        this.setBlockAndMetadata(world, -4, 2, -5, Blocks.torch, 2);
        this.setBlockAndMetadata(world, -5, 2, -4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -4, 2, 5, Blocks.torch, 2);
        this.setBlockAndMetadata(world, -5, 2, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 4, 2, -5, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 5, 2, -4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 4, 2, 5, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 5, 2, 4, Blocks.torch, 4);
        for (i13 = -4; i13 <= 4; ++i13) {
            this.setBlockAndMetadata(world, i13, 3, -5, this.brickStairBlock, 7);
            this.setBlockAndMetadata(world, i13, 3, 5, this.brickStairBlock, 6);
        }
        for (k1 = -4; k1 <= 4; ++k1) {
            this.setBlockAndMetadata(world, -5, 3, k1, this.brickStairBlock, 4);
            this.setBlockAndMetadata(world, 5, 3, k1, this.brickStairBlock, 5);
        }
        for (j1 = 1; j1 <= 2; ++j1) {
            this.setBlockAndMetadata(world, -1, j1, -6, this.pillarBlock, this.pillarMeta);
            this.setAir(world, 0, j1, -6);
            this.setBlockAndMetadata(world, 1, j1, -6, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, -1, j1, -7, this.stoneBlock, this.stoneMeta);
            this.setAir(world, 0, j1, -7);
            this.setBlockAndMetadata(world, 1, j1, -7, this.stoneBlock, this.stoneMeta);
        }
        this.placeIthildinDoor(world, 0, 1, -7, LOTRMod.dwarvenDoorIthildin, 3, LOTRBlockGateDwarvenIthildin.DoorSize._1x2);
        for (k1 = -4; k1 <= -3; ++k1) {
            for (i1 = -3; i1 <= 3; ++i1) {
                this.setBlockAndMetadata(world, i1, 1, k1, this.carpetBlock, this.carpetMeta);
            }
        }
        for (k1 = -1; k1 <= 3; ++k1) {
            for (i1 = -1; i1 <= 1; ++i1) {
                if (Math.abs(i1) == 1 && (k1 == -1 || k1 == 3)) {
                    this.setBlockAndMetadata(world, i1, 1, k1, this.plankBlock, this.plankMeta);
                } else {
                    this.setBlockAndMetadata(world, i1, 1, k1, this.plankSlabBlock, this.plankSlabMeta | 8);
                }
                if (random.nextInt(3) == 0) {
                    this.placeMug(world, random, i1, 2, k1, random.nextInt(4), this.drinkFoods);
                    continue;
                }
                this.placePlate(world, random, i1, 2, k1, this.plateBlock, this.plateFoods);
            }
        }
        this.setBlockAndMetadata(world, 0, 3, 0, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, 0, 3, 2, this.chandelierBlock, this.chandelierMeta);
        for (k1 = 0; k1 <= 2; ++k1) {
            this.setBlockAndMetadata(world, -3, 1, k1, this.plankStairBlock, 0);
            this.setBlockAndMetadata(world, 3, 1, k1, this.plankStairBlock, 1);
        }
        for (k1 = 4; k1 <= 6; ++k1) {
            for (j13 = 1; j13 <= 4; ++j13) {
                for (i12 = -2; i12 <= 2; ++i12) {
                    this.setBlockAndMetadata(world, i12, j13, k1, this.brickBlock, this.brickMeta);
                }
            }
        }
        this.placeWallBanner(world, -2, 3, 4, this.bannerType, 2);
        this.placeWallBanner(world, 2, 3, 4, this.bannerType, 2);
        for (j1 = 1; j1 <= 3; ++j1) {
            this.setBlockAndMetadata(world, -2, j1, 4, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, 2, j1, 4, this.pillarBlock, this.pillarMeta);
        }
        for (i13 = -1; i13 <= 1; ++i13) {
            this.setBlockAndMetadata(world, i13, 2, 4, this.barsBlock, 0);
            this.setBlockAndMetadata(world, i13, 3, 4, this.barsBlock, 0);
            this.setBlockAndMetadata(world, i13, 1, 5, LOTRMod.hearth, 0);
            this.setBlockAndMetadata(world, i13, 2, 5, (Block)Blocks.fire, 0);
            this.setAir(world, i13, 3, 5);
        }
        for (k1 = -2; k1 <= 1; ++k1) {
            this.setAir(world, -5, 0, k1);
            this.setAir(world, 5, 0, k1);
            int height = 1 - k1;
            for (j12 = -3; j12 < -3 + height; ++j12) {
                this.setBlockAndMetadata(world, -5, j12, k1, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 5, j12, k1, this.brickBlock, this.brickMeta);
            }
            this.setBlockAndMetadata(world, -5, -3 + height, k1, this.brickStairBlock, 3);
            this.setBlockAndMetadata(world, 5, -3 + height, k1, this.brickStairBlock, 3);
        }
        for (k1 = -5; k1 <= 5; ++k1) {
            for (j13 = -3; j13 <= -1; ++j13) {
                for (i12 = -1; i12 <= 1; ++i12) {
                    this.setBlockAndMetadata(world, i12, j13, k1, this.plankBlock, this.plankMeta);
                }
            }
        }
        for (j1 = -3; j1 <= -1; ++j1) {
            this.setBlockAndMetadata(world, -2, j1, -5, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, -2, j1, 5, this.pillarBlock, this.pillarMeta);
        }
        this.setBlockAndMetadata(world, -5, -2, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -2, -2, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -2, -2, -4, Blocks.torch, 3);
        for (k1 = -4; k1 <= 4; ++k1) {
            if (IntMath.mod((int)k1, (int)2) == 1) {
                this.setBlockAndMetadata(world, -2, -3, k1, this.plankSlabBlock, this.plankSlabMeta | 8);
                if (random.nextBoolean()) {
                    this.placePlateWithCertainty(world, random, -2, -2, k1, this.plateBlock, this.plateFoods);
                } else {
                    this.placeMug(world, random, -2, -2, k1, 1, this.drinkFoods);
                }
            } else {
                this.setBlockAndMetadata(world, -2, -3, k1, this.plankBlock, this.plankMeta);
            }
            this.setBlockAndMetadata(world, -2, -1, k1, this.brickStairBlock, 5);
        }
        for (i13 = -4; i13 <= -3; ++i13) {
            this.setBlockAndMetadata(world, i13, -3, -5, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i13, -2, -6, this.plankBlock, this.plankMeta);
            this.placeBarrel(world, random, i13, -2, -5, 3, this.drinkFoods);
            this.setBlockAndMetadata(world, i13, -1, -5, this.brickStairBlock, 7);
            this.setBlockAndMetadata(world, i13, -3, 5, Blocks.furnace, 2);
            this.setBlockAndMetadata(world, i13, -2, 6, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i13, -1, 5, this.brickStairBlock, 6);
        }
        for (k1 = -4; k1 <= -3; ++k1) {
            this.setBlockAndMetadata(world, -5, -3, k1, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -6, -2, k1, this.plankBlock, this.plankMeta);
            this.placeChest(world, random, -5, -2, k1, 4, this.larderContents);
            this.setBlockAndMetadata(world, -5, -1, k1, this.brickStairBlock, 4);
        }
        this.setBlockAndMetadata(world, -2, -3, 2, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, -2, -3, 0, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -2, -3, -2, this.tableBlock, 0);
        for (j1 = -3; j1 <= -1; ++j1) {
            this.setBlockAndMetadata(world, 2, j1, -5, this.pillarBlock, this.pillarMeta);
            this.setBlockAndMetadata(world, 2, j1, 5, this.pillarBlock, this.pillarMeta);
        }
        this.setBlockAndMetadata(world, 5, -2, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, -2, 4, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 5, -2, -4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 2, -2, -4, Blocks.torch, 3);
        for (k1 = -4; k1 <= 4; ++k1) {
            this.setBlockAndMetadata(world, 2, -3, k1, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 2, -1, k1, this.brickStairBlock, 4);
        }
        for (i13 = 3; i13 <= 4; ++i13) {
            this.setBlockAndMetadata(world, i13, -3, -5, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i13, -2, -6, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i13, -1, -5, this.brickStairBlock, 7);
            this.setBlockAndMetadata(world, i13, -3, 5, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i13, -2, 6, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i13, -1, 5, this.brickStairBlock, 6);
            for (k12 = -2; k12 <= 0; ++k12) {
                this.setBlockAndMetadata(world, i13, -3, k12, this.carpetBlock, this.carpetMeta);
            }
            this.setBlockAndMetadata(world, i13, -3, -3, LOTRMod.dwarvenBed, 2);
            this.setBlockAndMetadata(world, i13, -3, -4, LOTRMod.dwarvenBed, 10);
            this.placeChest(world, random, i13, -2, -5, 3, this.personalContents, MathHelper.getRandomIntegerInRange((Random)random, (int)2, (int)4));
        }
        for (k1 = -4; k1 <= -3; ++k1) {
            this.setBlockAndMetadata(world, 5, -3, k1, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 6, -2, k1, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 5, -1, k1, this.brickStairBlock, 5);
        }
        for (k1 = -2; k1 <= 2; ++k1) {
            if (k1 == 0) {
                ItemStack item = this.getRandomWeaponItem(random);
                this.placeWeaponRack(world, 2, -2, k1, 5, item);
                continue;
            }
            if (IntMath.mod((int)k1, (int)2) != 0) continue;
            ItemStack item = random.nextBoolean() ? this.getRandomWeaponItem(random) : this.getRandomOtherItem(random);
            this.spawnItemFrame(world, 1, -2, k1, 1, item);
        }
        LOTREntityDwarf dwarfMale = this.createDwarf(world);
        dwarfMale.familyInfo.setMale(true);
        dwarfMale.familyInfo.setName(LOTRNames.getDwarfName(random, dwarfMale.familyInfo.isMale()));
        this.spawnNPCAndSetHome(dwarfMale, world, 0, 2, 0, 8);
        LOTREntityDwarf dwarfFemale = this.createDwarf(world);
        dwarfFemale.familyInfo.setMale(false);
        dwarfFemale.familyInfo.setName(LOTRNames.getDwarfName(random, dwarfFemale.familyInfo.isMale()));
        this.spawnNPCAndSetHome(dwarfFemale, world, 0, 2, 0, 8);
        int maxChildren = dwarfMale.familyInfo.getRandomMaxChildren();
        dwarfMale.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.dwarvenRing));
        dwarfMale.familyInfo.spouseUniqueID = dwarfFemale.getUniqueID();
        dwarfMale.familyInfo.setMaxBreedingDelay();
        dwarfMale.familyInfo.maxChildren = maxChildren;
        dwarfFemale.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.dwarvenRing));
        dwarfFemale.familyInfo.spouseUniqueID = dwarfMale.getUniqueID();
        dwarfFemale.familyInfo.setMaxBreedingDelay();
        dwarfFemale.familyInfo.maxChildren = maxChildren;
        return true;
    }

    protected ItemStack getRandomWeaponItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.swordDwarven), new ItemStack(LOTRMod.daggerDwarven), new ItemStack(LOTRMod.hammerDwarven), new ItemStack(LOTRMod.battleaxeDwarven), new ItemStack(LOTRMod.pickaxeDwarven), new ItemStack(LOTRMod.mattockDwarven), new ItemStack(LOTRMod.throwingAxeDwarven), new ItemStack(LOTRMod.pikeDwarven), new ItemStack(LOTRMod.swordDale), new ItemStack(LOTRMod.daggerDale), new ItemStack(LOTRMod.pikeDale), new ItemStack(LOTRMod.spearDale), new ItemStack(LOTRMod.battleaxeDale)};
        return items[random.nextInt(items.length)].copy();
    }

    protected ItemStack getRandomOtherItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.helmetDwarven), new ItemStack(LOTRMod.bodyDwarven), new ItemStack(LOTRMod.legsDwarven), new ItemStack(LOTRMod.bootsDwarven), new ItemStack(LOTRMod.helmetDale), new ItemStack(LOTRMod.bodyDale), new ItemStack(LOTRMod.legsDale), new ItemStack(LOTRMod.bootsDale), new ItemStack(LOTRMod.dwarfSteel), new ItemStack(LOTRMod.bronze), new ItemStack(Items.iron_ingot), new ItemStack(LOTRMod.silver), new ItemStack(LOTRMod.silverNugget), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_nugget)};
        return items[random.nextInt(items.length)].copy();
    }
}

