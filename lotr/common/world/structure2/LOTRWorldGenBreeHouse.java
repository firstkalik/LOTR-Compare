/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
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
import lotr.common.entity.npc.LOTREntityBreeMan;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenBreeHouse
extends LOTRWorldGenBreeStructure {
    public LOTRWorldGenBreeHouse(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int randPath;
        int i1;
        int i12;
        int i13;
        int k12;
        int j1;
        int j12;
        int j2;
        this.setOriginAndRotation(world, i, j, k, rotation, 9);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -7; i1 <= 8; ++i1) {
                for (k1 = -8; k1 <= 5; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j1, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -3; i1 <= 8; ++i1) {
            for (k1 = -5; k1 <= 3; ++k1) {
                for (j1 = 1; j1 <= 8; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (k1 = -8; k1 <= -6; ++k1) {
                for (j1 = 1; j1 <= 8; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        for (i1 = 2; i1 <= 7; ++i1) {
            for (k1 = 3; k1 <= 5; ++k1) {
                for (j1 = 1; j1 <= 8; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        for (i1 = -7; i1 <= -3; ++i1) {
            for (k1 = -4; k1 <= 2; ++k1) {
                for (j1 = 1; j1 <= 8; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        this.loadStrScan("bree_house");
        this.associateBlockMetaAlias("BRICK", this.brickBlock, this.brickMeta);
        this.associateBlockMetaAlias("FLOOR", this.floorBlock, this.floorMeta);
        this.associateBlockMetaAlias("STONE_WALL", this.stoneWallBlock, this.stoneWallMeta);
        this.associateBlockMetaAlias("PLANK", this.plankBlock, this.plankMeta);
        this.associateBlockMetaAlias("PLANK_SLAB", this.plankSlabBlock, this.plankSlabMeta);
        this.associateBlockMetaAlias("PLANK_SLAB_INV", this.plankSlabBlock, this.plankSlabMeta | 8);
        this.associateBlockAlias("PLANK_STAIR", this.plankStairBlock);
        this.associateBlockMetaAlias("FENCE", this.fenceBlock, this.fenceMeta);
        this.associateBlockAlias("FENCE_GATE", this.fenceGateBlock);
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
        this.addBlockMetaAliasOption("PATH", 5, (Block)Blocks.grass, 0);
        this.addBlockMetaAliasOption("PATH", 5, Blocks.dirt, 1);
        this.addBlockMetaAliasOption("PATH", 5, LOTRMod.dirtPath, 0);
        this.addBlockMetaAliasOption("PATH", 5, Blocks.cobblestone, 0);
        this.associateBlockMetaAlias("LEAF", (Block)Blocks.leaves, 4);
        this.generateStrScan(world, random, 0, 0, 0);
        int maxSteps = 12;
        for (i13 = 3; i13 <= 6; ++i13) {
            for (int step = 0; step < 12 && !this.isOpaque(world, i13, j12 = -1 - step, k12 = 6 + step); ++step) {
                randPath = random.nextInt(4);
                if (randPath == 0) {
                    this.setBlockAndMetadata(world, i13, j12, k12, (Block)Blocks.grass, 0);
                } else if (randPath == 1) {
                    this.setBlockAndMetadata(world, i13, j12, k12, Blocks.dirt, 1);
                } else if (randPath == 2) {
                    this.setBlockAndMetadata(world, i13, j12, k12, LOTRMod.dirtPath, 0);
                } else if (randPath == 3) {
                    this.setBlockAndMetadata(world, i13, j12, k12, Blocks.cobblestone, 0);
                }
                this.setGrassToDirt(world, i13, j12 - 1, k12);
                j2 = j12 - 1;
                while (!this.isOpaque(world, i13, j2, k12) && this.getY(j2) >= 0) {
                    this.setBlockAndMetadata(world, i13, j2, k12, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i13, j2 - 1, k12);
                    --j2;
                }
            }
        }
        for (int step = 0; step < 12 && !this.isOpaque(world, i12 = -5, j12 = 0 - step, k12 = -5 - step); ++step) {
            randPath = random.nextInt(4);
            if (randPath == 0) {
                this.setBlockAndMetadata(world, i12, j12, k12, (Block)Blocks.grass, 0);
            } else if (randPath == 1) {
                this.setBlockAndMetadata(world, i12, j12, k12, Blocks.dirt, 1);
            } else if (randPath == 2) {
                this.setBlockAndMetadata(world, i12, j12, k12, LOTRMod.dirtPath, 0);
            } else if (randPath == 3) {
                this.setBlockAndMetadata(world, i12, j12, k12, Blocks.cobblestone, 0);
            }
            this.setGrassToDirt(world, i12, j12 - 1, k12);
            j2 = j12 - 1;
            while (!this.isOpaque(world, i12, j2, k12) && this.getY(j2) >= 0) {
                this.setBlockAndMetadata(world, i12, j2, k12, Blocks.dirt, 0);
                this.setGrassToDirt(world, i12, j2 - 1, k12);
                --j2;
            }
        }
        for (i13 = -6; i13 <= -3; ++i13) {
            for (int k13 = -3; k13 <= 1; ++k13) {
                j12 = 1;
                if (this.getBlock(world, i13, j12 - 1, k13) != Blocks.grass || random.nextInt(4) != 0) continue;
                this.plantFlower(world, random, i13, j12, k13);
            }
        }
        this.placeRandomFlowerPot(world, random, 6, 1, 3);
        this.placeRandomFlowerPot(world, random, 3, 1, 3);
        this.placeRandomFlowerPot(world, random, -1, 5, -1);
        this.placeRandomFlowerPot(world, random, 2, 5, 1);
        this.plantFlower(world, random, 0, 2, 3);
        this.plantFlower(world, random, 8, 6, -1);
        this.placeChest(world, random, -1, 1, 1, 4, LOTRChestContents.BREE_HOUSE);
        this.placeChest(world, random, 1, 5, 1, 2, LOTRChestContents.BREE_HOUSE);
        this.placeMug(world, random, 3, 2, -2, 3, LOTRFoods.BREE_DRINK);
        this.placePlateWithCertainty(world, random, 3, 2, -3, LOTRMod.plateBlock, LOTRFoods.BREE);
        this.setBlockAndMetadata(world, 0, 5, 0, this.bedBlock, 3);
        this.setBlockAndMetadata(world, -1, 5, 0, this.bedBlock, 11);
        if (random.nextBoolean()) {
            this.spawnItemFrame(world, 2, 3, 0, 3, new ItemStack(Items.clock));
        }
        String[] breeNames = LOTRNames.getBreeCoupleAndHomeNames(random);
        LOTREntityBreeMan man = new LOTREntityBreeMan(world);
        man.familyInfo.setMale(true);
        man.familyInfo.setName(breeNames[0]);
        this.spawnNPCAndSetHome(man, world, 0, 1, 0, 16);
        LOTREntityBreeMan woman = new LOTREntityBreeMan(world);
        woman.familyInfo.setMale(false);
        woman.familyInfo.setName(breeNames[1]);
        this.spawnNPCAndSetHome(woman, world, 0, 1, 0, 16);
        this.placeSign(world, 2, 2, -8, Blocks.standing_sign, 9, new String[]{"", breeNames[2], breeNames[3], ""});
        return true;
    }
}

