/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityBreeGuard;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenBreeGatehouse
extends LOTRWorldGenBreeStructure {
    private String villageName = "Village";

    public LOTRWorldGenBreeGatehouse(boolean flag) {
        super(flag);
    }

    public LOTRWorldGenBreeGatehouse setName(String name) {
        this.villageName = name;
        return this;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int stepsDown;
        int i1;
        int k12;
        int j1;
        int i12;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, 2);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -10; i1 <= 5; ++i1) {
                for (k1 = -5; k1 <= 10; ++k1) {
                    j12 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j12, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -4; i1 <= 4; ++i1) {
            for (k1 = -1; k1 <= 1; ++k1) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = -9; i1 <= -4; ++i1) {
            for (k1 = 4; k1 <= 9; ++k1) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (int k13 = 6; k13 <= 7; ++k13) {
            int i13 = -3;
            for (j12 = 1; j12 <= 7; ++j12) {
                this.setAir(world, i13, j12, k13);
            }
        }
        this.loadStrScan("bree_gatehouse");
        this.associateBlockMetaAlias("BRICK", this.brickBlock, this.brickMeta);
        this.associateBlockMetaAlias("COBBLE", Blocks.cobblestone, 0);
        this.associateBlockAlias("COBBLE_STAIR", Blocks.stone_stairs);
        this.associateBlockMetaAlias("COBBLE_WALL", Blocks.cobblestone_wall, 0);
        this.associateBlockMetaAlias("PLANK", this.plankBlock, this.plankMeta);
        this.associateBlockMetaAlias("PLANK_SLAB", this.plankSlabBlock, this.plankSlabMeta);
        this.associateBlockAlias("PLANK_STAIR", this.plankStairBlock);
        this.associateBlockMetaAlias("FENCE", this.fenceBlock, this.fenceMeta);
        this.associateBlockAlias("DOOR", this.doorBlock);
        this.associateBlockAlias("TRAPDOOR", this.trapdoorBlock);
        this.associateBlockMetaAlias("BEAM", this.beamBlock, this.beamMeta);
        this.associateBlockMetaAlias("BEAM|4", this.beamBlock, this.beamMeta | 4);
        this.associateBlockMetaAlias("BEAM|8", this.beamBlock, this.beamMeta | 8);
        this.associateBlockMetaAlias("ROOF", this.roofBlock, this.roofMeta);
        this.associateBlockAlias("ROOF_STAIR", this.roofStairBlock);
        this.addBlockMetaAliasOption("THATCH_FLOOR", 1, LOTRMod.thatchFloor, 0);
        this.setBlockAliasChance("THATCH_FLOOR", 0.4f);
        this.addBlockMetaAliasOption("PATH", 5, LOTRMod.dirtPath, 0);
        this.addBlockMetaAliasOption("PATH", 3, Blocks.cobblestone, 0);
        this.addBlockMetaAliasOption("PATH", 2, Blocks.gravel, 0);
        this.generateStrScan(world, random, 0, 0, 0);
        int maxSteps = 12;
        for (int step = 0; step < maxSteps && !this.isOpaque(world, i12 = -3, j1 = 0 - (stepsDown = Math.max(0, step - 2)), k12 = 6 + step); ++step) {
            if (step < 2) {
                this.setBlockAndMetadata(world, i12, j1, k12, Blocks.cobblestone, 0);
            } else {
                this.setBlockAndMetadata(world, i12, j1, k12, Blocks.stone_stairs, 3);
            }
            this.setGrassToDirt(world, i12, j1 - 1, k12);
            int j2 = j1 - 1;
            while (!this.isOpaque(world, i12, j2, k12) && this.getY(j2) >= 0) {
                this.setBlockAndMetadata(world, i12, j2, k12, Blocks.cobblestone, 0);
                this.setGrassToDirt(world, i12, j2 - 1, k12);
                --j2;
            }
        }
        this.placeChest(world, random, -5, 2, 8, 5, LOTRChestContents.BREE_HOUSE);
        this.placeMug(world, random, -7, 3, 5, 2, LOTRFoods.BREE_DRINK);
        this.placePlateWithCertainty(world, random, -8, 3, 5, LOTRMod.plateBlock, LOTRFoods.BREE);
        this.setBlockAndMetadata(world, -7, 2, 8, this.bedBlock, 3);
        this.setBlockAndMetadata(world, -8, 2, 8, this.bedBlock, 11);
        this.spawnItemFrame(world, -7, 4, 4, 0, new ItemStack(Items.clock));
        LOTREntityBreeGuard guard = new LOTREntityBreeGuard(world);
        this.spawnNPCAndSetHome(guard, world, -7, 2, 6, 8);
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClass(LOTREntityBreeGuard.class);
        respawner.setCheckRanges(20, -12, 12, 1);
        respawner.setSpawnRanges(4, -2, 2, 8);
        this.placeNPCRespawner(respawner, world, -7, 2, 6);
        this.placeSign(world, -4, 3, -5, Blocks.wall_sign, 2, new String[]{"", "Welcome to", this.villageName, ""});
        this.placeWallBanner(world, -4, 6, -1, LOTRItemBanner.BannerType.BREE, 2);
        this.placeWallBanner(world, 4, 6, -1, LOTRItemBanner.BannerType.BREE, 2);
        this.placeWallBanner(world, 4, 6, 1, LOTRItemBanner.BannerType.BREE, 0);
        this.placeWallBanner(world, -4, 6, 6, LOTRItemBanner.BannerType.BREE, 1);
        return true;
    }
}

