/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
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
import lotr.common.entity.npc.LOTREntityBreeCaptain;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenBreeOffice
extends LOTRWorldGenBreeStructure {
    public LOTRWorldGenBreeOffice(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int i1;
        int j1;
        this.setOriginAndRotation(world, i, j, k, rotation, 8);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -9; i1 <= 9; ++i1) {
                for (k1 = -8; k1 <= 5; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j1, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -8; i1 <= 8; ++i1) {
            for (k1 = -4; k1 <= 4; ++k1) {
                for (j1 = 1; j1 <= 13; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (k1 = -6; k1 <= -5; ++k1) {
                for (j1 = 1; j1 <= 11; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k1 = -8; k1 <= -7; ++k1) {
                for (j1 = 1; j1 <= 6; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        this.loadStrScan("bree_office");
        this.associateBlockMetaAlias("BRICK", this.brickBlock, this.brickMeta);
        this.associateBlockMetaAlias("FLOOR", this.floorBlock, this.floorMeta);
        this.associateBlockMetaAlias("STONE_WALL", this.stoneWallBlock, this.stoneWallMeta);
        this.associateBlockMetaAlias("PLANK", this.plankBlock, this.plankMeta);
        this.associateBlockMetaAlias("PLANK_SLAB", this.plankSlabBlock, this.plankSlabMeta);
        this.associateBlockMetaAlias("PLANK_SLAB_INV", this.plankSlabBlock, this.plankSlabMeta | 8);
        this.associateBlockAlias("PLANK_STAIR", this.plankStairBlock);
        this.associateBlockMetaAlias("FENCE", this.fenceBlock, this.fenceMeta);
        this.associateBlockAlias("DOOR", this.doorBlock);
        this.associateBlockAlias("TRAPDOOR", this.trapdoorBlock);
        this.associateBlockMetaAlias("BEAM", this.beamBlock, this.beamMeta);
        this.associateBlockMetaAlias("BEAM|4", this.beamBlock, this.beamMeta | 4);
        this.associateBlockMetaAlias("BEAM|8", this.beamBlock, this.beamMeta | 8);
        this.associateBlockMetaAlias("ROOF", this.roofBlock, this.roofMeta);
        this.associateBlockMetaAlias("ROOF_SLAB", this.roofSlabBlock, this.roofSlabMeta);
        this.associateBlockMetaAlias("ROOF_SLAB_INV", this.roofSlabBlock, this.roofSlabMeta | 8);
        this.associateBlockAlias("ROOF_STAIR", this.roofStairBlock);
        this.associateBlockMetaAlias("TABLE", this.tableBlock, 0);
        this.associateBlockMetaAlias("CARPET", this.carpetBlock, this.carpetMeta);
        this.associateBlockMetaAlias("LEAF", (Block)Blocks.leaves, 4);
        this.generateStrScan(world, random, 0, 0, 0);
        this.placeRandomFlowerPot(world, random, 4, 2, -2);
        this.placeRandomFlowerPot(world, random, -2, 2, -2);
        this.placeRandomFlowerPot(world, random, 2, 2, -1);
        this.placeRandomFlowerPot(world, random, 5, 2, 2);
        this.placeRandomFlowerPot(world, random, 1, 6, -4);
        this.placeRandomFlowerPot(world, random, 6, 6, -2);
        this.placeRandomFlowerPot(world, random, -6, 6, 0);
        this.plantFlower(world, random, -4, 6, 4);
        this.plantFlower(world, random, 4, 6, 4);
        this.plantFlower(world, random, 0, 6, -6);
        this.setBlockAndMetadata(world, 1, 1, 1, this.doorBlock, 3);
        this.setBlockAndMetadata(world, 1, 2, 1, this.doorBlock, 9);
        this.setBlockAndMetadata(world, 2, 1, 1, this.doorBlock, 3);
        this.setBlockAndMetadata(world, 2, 2, 1, this.doorBlock, 8);
        this.placeChest(world, random, -4, 1, -2, 3, LOTRChestContents.BREE_HOUSE);
        this.placeChest(world, random, 6, 1, 0, 5, LOTRChestContents.BREE_HOUSE);
        this.placeChest(world, random, 1, 1, 2, 2, LOTRChestContents.BREE_OFFICE_ITEMS);
        this.placeChest(world, random, 2, 1, 2, 2, LOTRChestContents.BREE_OFFICE_ITEMS);
        if (random.nextInt(3) == 0) {
            this.placeChest(world, random, 0, 9, -3, 3, LOTRChestContents.BREE_OFFICE_ITEMS);
        }
        if (random.nextInt(3) == 0) {
            this.placeChest(world, random, 6, 9, 0, 5, LOTRChestContents.BREE_OFFICE_ITEMS);
        }
        if (random.nextInt(3) == 0) {
            this.placeChest(world, random, 5, 9, 1, 2, LOTRChestContents.BREE_OFFICE_ITEMS);
        }
        this.placeMug(world, random, -5, 2, -2, 0, LOTRFoods.BREE_DRINK);
        this.placeBarrel(world, random, -6, 2, -2, 3, LOTRFoods.BREE_DRINK);
        this.placeMug(world, random, 6, 6, 1, 3, LOTRFoods.BREE_DRINK);
        this.placeMug(world, random, 6, 6, 2, 3, LOTRFoods.BREE_DRINK);
        this.placePlate(world, random, -4, 2, 2, LOTRMod.ceramicPlateBlock, LOTRFoods.BREE);
        this.placePlate(world, random, -5, 2, 2, LOTRMod.plateBlock, LOTRFoods.BREE);
        this.placePlate(world, random, 0, 6, -4, LOTRMod.ceramicPlateBlock, LOTRFoods.BREE);
        this.placePlate(world, random, -6, 6, -2, LOTRMod.plateBlock, LOTRFoods.BREE);
        this.placePlate(world, random, 5, 6, 1, LOTRMod.ceramicPlateBlock, LOTRFoods.BREE);
        this.setBlockAndMetadata(world, 5, 6, 2, LOTRWorldGenBreeOffice.getRandomPieBlock(random), 0);
        this.setBlockAndMetadata(world, -3, 6, 2, LOTRWorldGenBreeOffice.getRandomPieBlock(random), 0);
        this.setBlockAndMetadata(world, 6, 1, -1, LOTRMod.strawBed, 2);
        this.setBlockAndMetadata(world, 6, 1, -2, LOTRMod.strawBed, 10);
        this.setBlockAndMetadata(world, 6, 1, 1, LOTRMod.strawBed, 0);
        this.setBlockAndMetadata(world, 6, 1, 2, LOTRMod.strawBed, 8);
        this.setBlockAndMetadata(world, -5, 5, 1, Blocks.bed, 3);
        this.setBlockAndMetadata(world, -6, 5, 1, Blocks.bed, 11);
        this.setBlockAndMetadata(world, -5, 5, 2, Blocks.bed, 3);
        this.setBlockAndMetadata(world, -6, 5, 2, Blocks.bed, 11);
        this.setBlockAndMetadata(world, -5, 9, 0, LOTRMod.strawBed, 3);
        this.setBlockAndMetadata(world, -6, 9, 0, LOTRMod.strawBed, 11);
        this.spawnItemFrame(world, 0, 2, 1, 2, new ItemStack(Items.clock));
        LOTREntityBreeCaptain sherriff = new LOTREntityBreeCaptain(world);
        this.spawnNPCAndSetHome(sherriff, world, 0, 4, 0, 12);
        this.placeSign(world, 0, 3, -8, Blocks.wall_sign, 2, new String[]{"", "Sherriff", sherriff.getNPCName(), ""});
        return true;
    }
}

