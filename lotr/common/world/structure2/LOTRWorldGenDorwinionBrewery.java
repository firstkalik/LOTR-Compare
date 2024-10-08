/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDorwinionElfVintner;
import lotr.common.item.LOTRItemBanner;
import lotr.common.item.LOTRItemMug;
import lotr.common.world.structure2.LOTRWorldGenDorwinionHouse;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenDorwinionBrewery
extends LOTRWorldGenDorwinionHouse {
    public LOTRWorldGenDorwinionBrewery(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int k12;
        int k13;
        int i12;
        int j1;
        int i1;
        int i13;
        this.setOriginAndRotation(world, i, j, k, rotation, 1);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i13 = -6; i13 <= 6; ++i13) {
                for (k13 = 0; k13 <= 19; ++k13) {
                    j1 = this.getTopBlock(world, i13, k13) - 1;
                    Block block = this.getBlock(world, i13, j1, k13);
                    if (block == Blocks.grass) continue;
                    return false;
                }
            }
        }
        for (i13 = -6; i13 <= 6; ++i13) {
            for (k13 = 0; k13 <= 19; ++k13) {
                this.setBlockAndMetadata(world, i13, 0, k13, (Block)Blocks.grass, 0);
                j1 = -1;
                while (!this.isOpaque(world, i13, j1, k13) && this.getY(j1) >= 0) {
                    this.setBlockAndMetadata(world, i13, j1, k13, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i13, j1 - 1, k13);
                    --j1;
                }
                for (j1 = 1; j1 <= 10; ++j1) {
                    this.setAir(world, i13, j1, k13);
                }
                if (i13 < -5 || i13 > 5 || k13 < 1 || k13 > 18) continue;
                if (!(i13 != -5 && i13 != 5 || k13 != 1 && k13 != 18)) {
                    for (j1 = 0; j1 <= 5; ++j1) {
                        this.setBlockAndMetadata(world, i13, j1, k13, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    continue;
                }
                if (i13 == -5 || i13 == 5 || k13 == 1 || k13 == 18) {
                    for (j1 = 0; j1 <= 5; ++j1) {
                        this.setBlockAndMetadata(world, i13, j1, k13, this.brickBlock, this.brickMeta);
                    }
                    continue;
                }
                if (i13 >= -2 && i13 <= 2) {
                    this.setBlockAndMetadata(world, i13, 0, k13, this.floorBlock, this.floorMeta);
                    continue;
                }
                this.setBlockAndMetadata(world, i13, 0, k13, this.plankBlock, this.plankMeta);
            }
        }
        for (i13 = -1; i13 <= 1; ++i13) {
            this.setBlockAndMetadata(world, i13, 0, 1, this.floorBlock, this.floorMeta);
            for (int j12 = 1; j12 <= 3; ++j12) {
                this.setBlockAndMetadata(world, i13, j12, 1, LOTRMod.gateWooden, 2);
            }
        }
        for (k1 = 2; k1 <= 17; ++k1) {
            if (k1 % 3 == 2) {
                this.setBlockAndMetadata(world, -6, 1, k1, this.brickStairBlock, 1);
                this.setGrassToDirt(world, -6, 0, k1);
                this.setBlockAndMetadata(world, 6, 1, k1, this.brickStairBlock, 0);
                this.setGrassToDirt(world, 6, 0, k1);
                continue;
            }
            this.setBlockAndMetadata(world, -6, 1, k1, this.leafBlock, this.leafMeta);
            this.setBlockAndMetadata(world, 6, 1, k1, this.leafBlock, this.leafMeta);
        }
        for (i13 = -4; i13 <= 4; ++i13) {
            if (Math.abs(i13) == 4) {
                this.setBlockAndMetadata(world, i13, 1, 19, this.brickStairBlock, 3);
                this.setGrassToDirt(world, i13, 0, 19);
                continue;
            }
            this.setBlockAndMetadata(world, i13, 1, 19, this.leafBlock, this.leafMeta);
        }
        for (i13 = -4; i13 <= 4; ++i13) {
            if (Math.abs(i13) == 4 || Math.abs(i13) == 2) {
                this.setBlockAndMetadata(world, i13, 1, 0, this.brickStairBlock, 2);
                this.setGrassToDirt(world, i13, 0, 0);
                continue;
            }
            if (Math.abs(i13) != 3) continue;
            this.setBlockAndMetadata(world, i13, 1, 0, this.leafBlock, this.leafMeta);
        }
        for (i13 = -5; i13 <= 5; ++i13) {
            this.setBlockAndMetadata(world, i13, 5, 0, this.brickStairBlock, 6);
            this.setBlockAndMetadata(world, i13, 5, 19, this.brickStairBlock, 7);
        }
        for (k1 = 0; k1 <= 19; ++k1) {
            if (k1 >= 3 && k1 <= 16) {
                if (k1 % 3 == 0) {
                    this.setAir(world, -5, 3, k1);
                    this.setBlockAndMetadata(world, -5, 4, k1, this.brickStairBlock, 7);
                    this.setAir(world, 5, 3, k1);
                    this.setBlockAndMetadata(world, 5, 4, k1, this.brickStairBlock, 7);
                } else if (k1 % 3 == 1) {
                    this.setAir(world, -5, 3, k1);
                    this.setBlockAndMetadata(world, -5, 4, k1, this.brickStairBlock, 6);
                    this.setAir(world, 5, 3, k1);
                    this.setBlockAndMetadata(world, 5, 4, k1, this.brickStairBlock, 6);
                }
            }
            this.setBlockAndMetadata(world, -6, 5, k1, this.brickStairBlock, 5);
            this.setBlockAndMetadata(world, 6, 5, k1, this.brickStairBlock, 4);
            if (k1 <= 7 && k1 % 2 == 0 || k1 >= 12 && k1 % 2 == 1) {
                this.setBlockAndMetadata(world, -6, 6, k1, this.brickSlabBlock, this.brickSlabMeta);
                this.setBlockAndMetadata(world, 6, 6, k1, this.brickSlabBlock, this.brickSlabMeta);
            }
            if (k1 == 8 || k1 == 11) {
                this.setBlockAndMetadata(world, -6, 4, k1, this.brickSlabBlock, this.brickSlabMeta | 8);
                this.setBlockAndMetadata(world, -6, 5, k1, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, -6, 6, k1, this.brickSlabBlock, this.brickSlabMeta);
                this.setBlockAndMetadata(world, 6, 4, k1, this.brickSlabBlock, this.brickSlabMeta | 8);
                this.setBlockAndMetadata(world, 6, 5, k1, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, 6, 6, k1, this.brickSlabBlock, this.brickSlabMeta);
                this.placeWallBanner(world, -5, 3, k1, LOTRItemBanner.BannerType.DORWINION, 3);
                this.placeWallBanner(world, 5, 3, k1, LOTRItemBanner.BannerType.DORWINION, 1);
            }
            if (k1 != 9 && k1 != 10) continue;
            this.setBlockAndMetadata(world, -6, 6, k1, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 6, 6, k1, this.brickBlock, this.brickMeta);
        }
        for (i13 = -3; i13 <= 3; ++i13) {
            if (Math.abs(i13) == 3) {
                this.setBlockAndMetadata(world, i13, 2, 1, this.brickSlabBlock, this.brickSlabMeta);
                this.setAir(world, i13, 3, 1);
            }
            if (Math.abs(i13) == 2) {
                this.placeWallBanner(world, i13, 4, 1, LOTRItemBanner.BannerType.DORWINION, 2);
            }
            if (IntMath.mod((int)i13, (int)2) != 1) continue;
            this.setBlockAndMetadata(world, i13, 2, 18, this.brickSlabBlock, this.brickSlabMeta);
            this.setAir(world, i13, 3, 18);
        }
        for (int k14 : new int[]{1, 18}) {
            int i14;
            this.setBlockAndMetadata(world, -4, 6, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -3, 6, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -2, 6, k14, this.brickSlabBlock, this.brickSlabMeta);
            this.setBlockAndMetadata(world, -1, 6, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 0, 6, k14, this.brickSlabBlock, this.brickSlabMeta);
            this.setBlockAndMetadata(world, 1, 6, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 2, 6, k14, this.brickSlabBlock, this.brickSlabMeta);
            this.setBlockAndMetadata(world, 3, 6, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 4, 6, k14, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, -3, 7, k14, this.brickBlock, this.brickMeta);
            this.setAir(world, -2, 7, k14);
            this.setBlockAndMetadata(world, -1, 7, k14, this.brickBlock, this.brickMeta);
            this.setAir(world, 0, 7, k14);
            this.setBlockAndMetadata(world, 1, 7, k14, this.brickBlock, this.brickMeta);
            this.setAir(world, 2, 7, k14);
            this.setBlockAndMetadata(world, 3, 7, k14, this.brickBlock, this.brickMeta);
            for (i14 = -2; i14 <= 2; ++i14) {
                this.setBlockAndMetadata(world, i14, 8, k14, this.brickBlock, this.brickMeta);
            }
            for (i14 = -1; i14 <= 1; ++i14) {
                this.setBlockAndMetadata(world, i14, 9, k14, this.brickBlock, this.brickMeta);
            }
            this.setBlockAndMetadata(world, 0, 10, k14, this.brickBlock, this.brickMeta);
        }
        for (k12 = 2; k12 <= 17; ++k12) {
            this.setBlockAndMetadata(world, -4, 6, k12, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, -3, 7, k12, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, -2, 8, k12, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, -1, 9, k12, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, 0, 10, k12, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 1, 9, k12, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, 2, 8, k12, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, 3, 7, k12, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, 4, 6, k12, this.plankStairBlock, 5);
        }
        for (k12 = 0; k12 <= 19; ++k12) {
            this.setBlockAndMetadata(world, -5, 6, k12, this.clayStairBlock, 1);
            this.setBlockAndMetadata(world, -4, 7, k12, this.clayStairBlock, 1);
            this.setBlockAndMetadata(world, -3, 8, k12, this.clayStairBlock, 1);
            this.setBlockAndMetadata(world, -2, 9, k12, this.clayStairBlock, 1);
            this.setBlockAndMetadata(world, -1, 10, k12, this.clayStairBlock, 1);
            this.setBlockAndMetadata(world, 0, 11, k12, this.claySlabBlock, this.claySlabMeta);
            this.setBlockAndMetadata(world, 1, 10, k12, this.clayStairBlock, 0);
            this.setBlockAndMetadata(world, 2, 9, k12, this.clayStairBlock, 0);
            this.setBlockAndMetadata(world, 3, 8, k12, this.clayStairBlock, 0);
            this.setBlockAndMetadata(world, 4, 7, k12, this.clayStairBlock, 0);
            this.setBlockAndMetadata(world, 5, 6, k12, this.clayStairBlock, 0);
        }
        for (int k14 : new int[]{0, 19}) {
            this.setBlockAndMetadata(world, -4, 6, k14, this.clayStairBlock, 4);
            this.setBlockAndMetadata(world, -3, 7, k14, this.clayStairBlock, 4);
            this.setBlockAndMetadata(world, -2, 8, k14, this.clayStairBlock, 4);
            this.setBlockAndMetadata(world, -1, 9, k14, this.clayStairBlock, 4);
            this.setBlockAndMetadata(world, 0, 10, k14, this.clayBlock, this.clayMeta);
            this.setBlockAndMetadata(world, 1, 9, k14, this.clayStairBlock, 5);
            this.setBlockAndMetadata(world, 2, 8, k14, this.clayStairBlock, 5);
            this.setBlockAndMetadata(world, 3, 7, k14, this.clayStairBlock, 5);
            this.setBlockAndMetadata(world, 4, 6, k14, this.clayStairBlock, 5);
        }
        for (int k15 = 2; k15 <= 17; ++k15) {
            if (k15 % 3 != 2) continue;
            for (i1 = -4; i1 <= 4; ++i1) {
                this.setBlockAndMetadata(world, i1, 6, k15, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
            this.setBlockAndMetadata(world, -4, 5, k15, Blocks.torch, 2);
            this.setBlockAndMetadata(world, 4, 5, k15, Blocks.torch, 1);
        }
        this.setBlockAndMetadata(world, -2, 5, 2, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 2, 5, 2, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -2, 5, 17, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 5, 17, Blocks.torch, 4);
        this.placeWallBanner(world, 0, 5, 1, LOTRItemBanner.BannerType.DORWINION, 0);
        this.placeWallBanner(world, 0, 5, 18, LOTRItemBanner.BannerType.DORWINION, 2);
        ItemStack drink = LOTRFoods.DORWINION_DRINK.getRandomBrewableDrink(random);
        for (k13 = 2; k13 <= 17; ++k13) {
            for (i12 = -4; i12 <= 4; ++i12) {
                if (Math.abs(i12) < 3) continue;
                if (k13 == 2 || k13 == 17) {
                    this.setBlockAndMetadata(world, i12, 1, k13, this.plankBlock, this.plankMeta);
                    continue;
                }
                if (k13 % 3 == 0) {
                    this.setBlockAndMetadata(world, i12, 1, k13, Blocks.spruce_stairs, 6);
                    this.setBlockAndMetadata(world, i12, 2, k13, Blocks.spruce_stairs, 2);
                    continue;
                }
                if (k13 % 3 != 1) continue;
                this.setBlockAndMetadata(world, i12, 1, k13, Blocks.spruce_stairs, 7);
                this.setBlockAndMetadata(world, i12, 2, k13, Blocks.spruce_stairs, 3);
            }
            if (k13 < 5 || k13 > 15 || k13 % 3 != 2) continue;
            this.setBlockAndMetadata(world, -4, 1, k13, this.plankBlock, this.plankMeta);
            this.placeBarrel(world, random, -4, 2, k13, 4, drink);
            this.setBlockAndMetadata(world, 4, 1, k13, this.plankBlock, this.plankMeta);
            this.placeBarrel(world, random, 4, 2, k13, 5, drink);
        }
        for (k13 = 8; k13 <= 11; ++k13) {
            for (i12 = -1; i12 <= 1; ++i12) {
                if (Math.abs(i12) == 1 && (k13 == 8 || k13 == 11)) {
                    this.setBlockAndMetadata(world, i12, 1, k13, this.plankBlock, this.plankMeta);
                    continue;
                }
                this.setBlockAndMetadata(world, i12, 1, k13, this.plankSlabBlock, this.plankSlabMeta | 8);
            }
            this.placeMug(world, random, -1, 2, k13, 1, drink, LOTRFoods.DORWINION_DRINK);
            this.placeMug(world, random, 1, 2, k13, 3, drink, LOTRFoods.DORWINION_DRINK);
        }
        this.setBlockAndMetadata(world, 0, 1, 17, Blocks.crafting_table, 0);
        for (i1 = -2; i1 <= 2; ++i1) {
            if (Math.abs(i1) < 1) continue;
            this.setBlockAndMetadata(world, i1, 1, 17, (Block)Blocks.chest, 2);
            TileEntity tileentity = this.getTileEntity(world, i1, 1, 17);
            if (!(tileentity instanceof TileEntityChest)) continue;
            TileEntityChest chest = (TileEntityChest)tileentity;
            int wines = MathHelper.getRandomIntegerInRange((Random)random, (int)3, (int)6);
            for (int l = 0; l < wines; ++l) {
                ItemStack chestDrinkItem = drink.copy();
                chestDrinkItem.stackSize = 1;
                LOTRItemMug.setStrengthMeta(chestDrinkItem, MathHelper.getRandomIntegerInRange((Random)random, (int)1, (int)4));
                LOTRItemMug.Vessel[] chestVessels = LOTRFoods.DORWINION_DRINK.getDrinkVessels();
                LOTRItemMug.Vessel v = chestVessels[random.nextInt(chestVessels.length)];
                LOTRItemMug.setVessel(chestDrinkItem, v, true);
                chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), chestDrinkItem);
            }
        }
        LOTREntityDorwinionElfVintner vintner = new LOTREntityDorwinionElfVintner(world);
        this.spawnNPCAndSetHome(vintner, world, 0, 1, 13, 16);
        return true;
    }
}

