/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBreeRuffian;
import lotr.common.entity.npc.LOTREntityRuffianBrute;
import lotr.common.entity.npc.LOTREntityRuffianSpy;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenBreeRuffianHouse
extends LOTRWorldGenBreeStructure {
    public LOTRWorldGenBreeRuffianHouse(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int i1;
        int i12;
        int randPath;
        int k12;
        int i13;
        int j1;
        int j12;
        int j2;
        this.setOriginAndRotation(world, i, j, k, rotation, 9);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -7; i1 <= 8; ++i1) {
                for (k1 = -8; k1 <= 5; ++k1) {
                    j12 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j12, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -3; i1 <= 8; ++i1) {
            for (k1 = -5; k1 <= 3; ++k1) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (k1 = -8; k1 <= -6; ++k1) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = 0; i1 <= 7; ++i1) {
            for (k1 = 3; k1 <= 5; ++k1) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = -7; i1 <= -3; ++i1) {
            for (k1 = -4; k1 <= 2; ++k1) {
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = 0; i1 <= 5; ++i1) {
            for (k1 = -4; k1 <= 4; ++k1) {
                for (j12 = -2; j12 <= 0; ++j12) {
                    this.setAir(world, i1, j12, k1);
                }
            }
        }
        for (i1 = -2; i1 <= -1; ++i1) {
            k1 = 0;
            for (j12 = -2; j12 <= 0; ++j12) {
                this.setAir(world, i1, j12, k1);
            }
        }
        this.loadStrScan("bree_ruffian_house");
        this.associateBlockMetaAlias("BRICK", this.brickBlock, this.brickMeta);
        this.addBlockMetaAliasOption("COBBLE", 3, Blocks.cobblestone, 0);
        this.addBlockMetaAliasOption("COBBLE", 1, Blocks.mossy_cobblestone, 0);
        this.addBlockMetaAliasOption("COBBLE_SLAB_INV", 3, (Block)Blocks.stone_slab, 11);
        this.addBlockMetaAliasOption("COBBLE_SLAB_INV", 1, LOTRMod.slabSingleV, 12);
        this.addBlockAliasOption("COBBLE_STAIR", 3, Blocks.stone_stairs);
        this.addBlockAliasOption("COBBLE_STAIR", 1, LOTRMod.stairsCobblestoneMossy);
        this.addBlockMetaAliasOption("COBBLE_WALL", 3, Blocks.cobblestone_wall, 0);
        this.addBlockMetaAliasOption("COBBLE_WALL", 1, Blocks.cobblestone_wall, 1);
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
        this.addBlockMetaAliasOption("THATCH_FLOOR", 1, LOTRMod.thatchFloor, 0);
        this.setBlockAliasChance("THATCH_FLOOR", 0.2f);
        this.addBlockMetaAliasOption("LEAF_FLOOR", 1, LOTRMod.fallenLeaves, 0);
        this.setBlockAliasChance("LEAF_FLOOR", 0.3f);
        this.addBlockMetaAliasOption("WEB", 1, Blocks.web, 0);
        this.setBlockAliasChance("WEB", 0.3f);
        this.addBlockMetaAliasOption("PATH", 10, (Block)Blocks.grass, 0);
        this.addBlockMetaAliasOption("PATH", 10, Blocks.dirt, 1);
        this.addBlockMetaAliasOption("PATH", 10, LOTRMod.dirtPath, 0);
        this.addBlockMetaAliasOption("PATH", 5, Blocks.cobblestone, 0);
        this.addBlockMetaAliasOption("PATH", 5, Blocks.mossy_cobblestone, 0);
        this.associateBlockMetaAlias("LEAF", (Block)Blocks.leaves, 4);
        this.generateStrScan(world, random, 0, 0, 0);
        int maxSteps = 12;
        for (i13 = 4; i13 <= 6; ++i13) {
            for (int step = 0; step < 12 && !this.isOpaque(world, i13, j1 = -1 - step, k12 = 5 + step); ++step) {
                randPath = random.nextInt(4);
                if (randPath == 0) {
                    this.setBlockAndMetadata(world, i13, j1, k12, (Block)Blocks.grass, 0);
                } else if (randPath == 1) {
                    this.setBlockAndMetadata(world, i13, j1, k12, Blocks.dirt, 1);
                } else if (randPath == 2) {
                    this.setBlockAndMetadata(world, i13, j1, k12, LOTRMod.dirtPath, 0);
                } else if (randPath == 3) {
                    if (random.nextBoolean()) {
                        this.setBlockAndMetadata(world, i13, j1, k12, Blocks.cobblestone, 0);
                    } else {
                        this.setBlockAndMetadata(world, i13, j1, k12, Blocks.mossy_cobblestone, 0);
                    }
                }
                this.setGrassToDirt(world, i13, j1 - 1, k12);
                j2 = j1 - 1;
                while (!this.isOpaque(world, i13, j2, k12) && this.getY(j2) >= 0) {
                    this.setBlockAndMetadata(world, i13, j2, k12, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i13, j2 - 1, k12);
                    --j2;
                }
            }
        }
        for (int step = 0; step < 12 && !this.isOpaque(world, i12 = -5, j1 = 0 - step, k12 = -5 - step); ++step) {
            randPath = random.nextInt(4);
            if (randPath == 0) {
                this.setBlockAndMetadata(world, i12, j1, k12, (Block)Blocks.grass, 0);
            } else if (randPath == 1) {
                this.setBlockAndMetadata(world, i12, j1, k12, Blocks.dirt, 1);
            } else if (randPath == 2) {
                this.setBlockAndMetadata(world, i12, j1, k12, LOTRMod.dirtPath, 0);
            } else if (randPath == 3) {
                if (random.nextBoolean()) {
                    this.setBlockAndMetadata(world, i12, j1, k12, Blocks.cobblestone, 0);
                } else {
                    this.setBlockAndMetadata(world, i12, j1, k12, Blocks.mossy_cobblestone, 0);
                }
            }
            this.setGrassToDirt(world, i12, j1 - 1, k12);
            j2 = j1 - 1;
            while (!this.isOpaque(world, i12, j2, k12) && this.getY(j2) >= 0) {
                this.setBlockAndMetadata(world, i12, j2, k12, Blocks.dirt, 0);
                this.setGrassToDirt(world, i12, j2 - 1, k12);
                --j2;
            }
        }
        this.setBlockAndMetadata(world, 4, -2, -1, this.bedBlock, 3);
        this.setBlockAndMetadata(world, 3, -2, -1, this.bedBlock, 11);
        this.setBlockAndMetadata(world, 5, -2, 1, this.bedBlock, 9);
        this.setBlockAndMetadata(world, 4, -2, 1, this.bedBlock, 1);
        this.setBlockAndMetadata(world, 0, 5, 0, this.bedBlock, 3);
        this.setBlockAndMetadata(world, -1, 5, 0, this.bedBlock, 11);
        this.placePlateWithCertainty(world, random, 1, -1, -4, LOTRMod.ceramicPlateBlock, LOTRFoods.BREE);
        this.placeMug(world, random, 0, -1, -4, 0, LOTRFoods.BREE_DRINK);
        this.placeBarrel(world, random, 5, -2, -4, 5, LOTRFoods.BREE_DRINK);
        this.placeBarrel(world, random, 4, -2, -3, 2, LOTRFoods.BREE_DRINK);
        this.placeChest(world, random, 3, -2, -3, 2, LOTRChestContents.BREE_RUFFIAN_PIPEWEED);
        this.placeChest(world, random, -2, -2, 0, 4, LOTRChestContents.BREE_TREASURE);
        this.placeChest(world, random, 3, -2, 1, 2, LOTRChestContents.BREE_TREASURE);
        this.placePlateWithCertainty(world, random, 3, 2, -3, LOTRMod.plateBlock, LOTRFoods.BREE);
        this.placeMug(world, random, 3, 2, -2, 3, LOTRFoods.BREE_DRINK);
        this.placeChest(world, random, -1, 1, 1, 4, LOTRChestContents.BREE_HOUSE);
        this.placeChest(world, random, 1, 5, 1, 2, LOTRChestContents.BREE_HOUSE);
        for (i13 = -6; i13 <= -3; ++i13) {
            for (int k13 = -3; k13 <= 1; ++k13) {
                j1 = 1;
                Block gardenBlock = this.getBlock(world, i13, j1 - 1, k13);
                if (gardenBlock != Blocks.grass && gardenBlock != Blocks.dirt || random.nextInt(3) != 0) continue;
                if (random.nextInt(3) == 0) {
                    this.setBlockAndMetadata(world, i13, j1, k13, (Block)Blocks.double_plant, 2);
                    this.setBlockAndMetadata(world, i13, j1 + 1, k13, (Block)Blocks.double_plant, 10);
                    continue;
                }
                this.plantTallGrass(world, random, i13, j1, k13);
            }
        }
        LOTREntityBreeRuffian ruffian = random.nextInt(3) == 0 ? new LOTREntityRuffianBrute(world) : new LOTREntityRuffianSpy(world);
        this.spawnNPCAndSetHome(ruffian, world, 0, 1, 0, 16);
        this.placeSign(world, 2, 2, -8, Blocks.standing_sign, 9, LOTRNames.getBreeRuffianSign(random));
        return true;
    }
}

