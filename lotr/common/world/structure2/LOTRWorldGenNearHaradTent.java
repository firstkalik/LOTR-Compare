/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityHarnedorWarrior;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenHarnedorStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenNearHaradTent
extends LOTRWorldGenHarnedorStructure {
    public LOTRWorldGenNearHaradTent(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        int i1;
        int k1;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, 4);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -3; i1 <= 3; ++i1) {
                for (k1 = -3; k1 <= 3; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j1, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            for (k1 = -3; k1 <= 3; ++k1) {
                int j13;
                j1 = 0;
                while (!this.isOpaque(world, i1, j1, k1) && this.getY(j1) >= 0) {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.sandstone, 0);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                    --j1;
                }
                for (j1 = 1; j1 <= 6; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                if (i2 == 3 && k2 == 3) {
                    for (j13 = 1; j13 <= 5; ++j13) {
                        this.setBlockAndMetadata(world, i1, j13, k1, this.fenceBlock, this.fenceMeta);
                    }
                    this.setBlockAndMetadata(world, i1, 6, k1, Blocks.torch, 5);
                } else if (i2 == 3 || k2 == 3) {
                    for (j13 = 1; j13 <= 4; ++j13) {
                        this.setBlockAndMetadata(world, i1, j13, k1, this.roofBlock, this.roofMeta);
                    }
                }
                if (i2 == 2 && k2 <= 2 || k2 == 2 && i2 <= 2) {
                    for (j13 = 4; j13 <= 5; ++j13) {
                        this.setBlockAndMetadata(world, i1, j13, k1, Blocks.wool, 15);
                    }
                    this.setBlockAndMetadata(world, i1, 1, k1, Blocks.carpet, 15);
                }
                if (i2 == 2 && k2 == 2) {
                    for (j13 = 1; j13 <= 4; ++j13) {
                        this.setBlockAndMetadata(world, i1, j13, k1, this.fenceBlock, this.fenceMeta);
                    }
                }
                if (!(i2 == 1 && k2 <= 1 || k2 == 1 && i2 <= 1)) continue;
                this.setBlockAndMetadata(world, i1, 6, k1, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i1, 1, k1, Blocks.carpet, 14);
            }
        }
        for (j12 = 1; j12 <= 5; ++j12) {
            this.setBlockAndMetadata(world, 0, j12, 0, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, 0, 6, 0, this.plankBlock, this.plankMeta);
        this.placeBanner(world, 0, 7, 0, LOTRItemBanner.BannerType.NEAR_HARAD, 0);
        for (j12 = 2; j12 <= 3; ++j12) {
            for (int i12 = -1; i12 <= 1; ++i12) {
                this.setBlockAndMetadata(world, i12, j12, -3, Blocks.wool, 15);
                this.setBlockAndMetadata(world, i12, j12, 3, Blocks.wool, 15);
            }
            for (k1 = -1; k1 <= 1; ++k1) {
                this.setBlockAndMetadata(world, -3, j12, k1, Blocks.wool, 15);
                this.setBlockAndMetadata(world, 3, j12, k1, Blocks.wool, 15);
            }
        }
        this.setAir(world, 0, 2, -3);
        this.setAir(world, 0, 2, 3);
        this.setAir(world, -3, 2, 0);
        this.setAir(world, 3, 2, 0);
        this.setBlockAndMetadata(world, -1, 1, -3, Blocks.wool, 15);
        this.setBlockAndMetadata(world, 0, 1, -3, Blocks.carpet, 14);
        this.setBlockAndMetadata(world, 0, 1, -2, Blocks.carpet, 14);
        this.setBlockAndMetadata(world, 1, 1, -3, Blocks.wool, 15);
        this.setBlockAndMetadata(world, -2, 1, 0, this.bedBlock, 0);
        this.setBlockAndMetadata(world, -2, 1, 1, this.bedBlock, 8);
        this.placeBarrel(world, random, -1, 1, 2, 2, LOTRFoods.HARNEDOR_DRINK);
        this.setBlockAndMetadata(world, 0, 1, 2, LOTRMod.nearHaradTable, 0);
        this.placeChest(world, random, 1, 1, 2, LOTRMod.chestBasket, 2, LOTRChestContents.HARNENNOR_HOUSE);
        this.placeChest(world, random, 2, 1, 1, LOTRMod.chestBasket, 5, LOTRChestContents.HARNENNOR_HOUSE);
        this.setBlockAndMetadata(world, 2, 1, 0, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 0, 3, -2, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 0, 3, 2, Blocks.torch, 4);
        this.placeWallBanner(world, -1, 3, 3, LOTRItemBanner.BannerType.NEAR_HARAD, 2);
        this.placeWallBanner(world, 1, 3, 3, LOTRItemBanner.BannerType.NEAR_HARAD, 2);
        this.placeWallBanner(world, 3, 3, 1, LOTRItemBanner.BannerType.NEAR_HARAD, 3);
        this.placeWallBanner(world, 3, 3, -1, LOTRItemBanner.BannerType.NEAR_HARAD, 3);
        this.placeWallBanner(world, -1, 3, -3, LOTRItemBanner.BannerType.NEAR_HARAD, 0);
        this.placeWallBanner(world, 1, 3, -3, LOTRItemBanner.BannerType.NEAR_HARAD, 0);
        this.placeWallBanner(world, -3, 3, 1, LOTRItemBanner.BannerType.NEAR_HARAD, 1);
        this.placeWallBanner(world, -3, 3, -1, LOTRItemBanner.BannerType.NEAR_HARAD, 1);
        LOTREntityHarnedorWarrior haradrim = new LOTREntityHarnedorWarrior(world);
        haradrim.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(haradrim, world, 0, 1, -1, 16);
        return true;
    }
}

