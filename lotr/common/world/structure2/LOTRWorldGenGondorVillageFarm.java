/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.structure2;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityGondorFarmhand;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.structure2.LOTRWorldGenGondorBarn;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class LOTRWorldGenGondorVillageFarm
extends LOTRWorldGenGondorStructure {
    public LOTRWorldGenGondorVillageFarm(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        this.setOriginAndRotation(world, i, j, k, rotation, 6);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i1 = -5; i1 <= 5; ++i1) {
                for (int k1 = -5; k1 <= 5; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (!this.isSurface(world, i1, j1, k1)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 4) continue;
                    return false;
                }
            }
        }
        if (this.restrictions) {
            int highestHeight = 0;
            for (int i1 = -6; i1 <= 6; ++i1) {
                for (int k1 = -6; k1 <= 6; ++k1) {
                    int j12;
                    int i2 = Math.abs(i1);
                    int k2 = Math.abs(k1);
                    if ((i2 != 6 || k2 != 0) && (k2 != 6 || i2 != 0) || !this.isSurface(world, i1, j12 = this.getTopBlock(world, i1, k1) - 1, k1) || j12 <= highestHeight) continue;
                    highestHeight = j12;
                }
            }
            this.originY = this.getY(highestHeight);
        }
        for (int i1 = -5; i1 <= 5; ++i1) {
            for (int k1 = -5; k1 <= 5; ++k1) {
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.rockBlock, this.rockMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 1; j1 <= 10; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                if (i2 == 5 && k2 == 5) {
                    this.setBlockAndMetadata(world, i1, 1, k1, this.rockBlock, this.rockMeta);
                    this.setBlockAndMetadata(world, i1, 2, k1, this.rockSlabBlock, this.rockSlabMeta);
                    continue;
                }
                if (i2 == 5 || k2 == 5) {
                    this.setBlockAndMetadata(world, i1, 1, k1, this.rockWallBlock, this.rockWallMeta);
                    if (i2 == 3 || k2 == 3) {
                        this.setBlockAndMetadata(world, i1, 2, k1, Blocks.torch, 5);
                    }
                    if (i2 != 0 && k2 != 0) continue;
                    this.setAir(world, i1, 1, k1);
                    continue;
                }
                this.setBlockAndMetadata(world, i1, 0, k1, (Block)Blocks.grass, 0);
            }
        }
        return true;
    }

    public static class Crops
    extends LOTRWorldGenGondorVillageFarm {
        public Crops(boolean flag) {
            super(flag);
        }

        @Override
        public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
            if (!super.generateWithSetRotation(world, random, i, j, k, rotation)) {
                return false;
            }
            for (int i1 = -4; i1 <= 4; ++i1) {
                for (int k1 = -4; k1 <= 4; ++k1) {
                    int i2 = Math.abs(i1);
                    int k2 = Math.abs(k1);
                    if (i2 <= 2 && k2 <= 2) {
                        if (i2 == 0 && k2 == 0) {
                            this.setBlockAndMetadata(world, i1, 0, k1, Blocks.water, 0);
                            this.setBlockAndMetadata(world, i1, 1, k1, this.rockBlock, this.rockMeta);
                            this.setBlockAndMetadata(world, i1, 2, k1, Blocks.hay_block, 0);
                            this.setBlockAndMetadata(world, i1, 3, k1, this.fenceBlock, this.fenceMeta);
                            this.setBlockAndMetadata(world, i1, 4, k1, Blocks.hay_block, 0);
                            this.setBlockAndMetadata(world, i1, 5, k1, Blocks.pumpkin, 2);
                            continue;
                        }
                        this.setBlockAndMetadata(world, i1, 0, k1, Blocks.farmland, 7);
                        this.setBlockAndMetadata(world, i1, 1, k1, this.cropBlock, this.cropMeta);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i1, 0, k1, LOTRMod.dirtPath, 0);
                }
            }
            this.setBlockAndMetadata(world, 0, 1, -5, this.fenceGateBlock, 0);
            this.setBlockAndMetadata(world, 0, 1, 5, this.fenceGateBlock, 2);
            this.setBlockAndMetadata(world, -5, 1, 0, this.fenceGateBlock, 1);
            this.setBlockAndMetadata(world, 5, 1, 0, this.fenceGateBlock, 3);
            int farmhands = 1 + random.nextInt(2);
            for (int l = 0; l < farmhands; ++l) {
                LOTREntityGondorFarmhand farmhand = new LOTREntityGondorFarmhand(world);
                this.spawnNPCAndSetHome(farmhand, world, 0, 1, -1, 8);
                farmhand.seedsItem = this.seedItem;
            }
            return true;
        }
    }

    public static class Animals
    extends LOTRWorldGenGondorVillageFarm {
        public Animals(boolean flag) {
            super(flag);
        }

        @Override
        public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
            int k1;
            int i1;
            if (!super.generateWithSetRotation(world, random, i, j, k, rotation)) {
                return false;
            }
            for (i1 = -1; i1 <= 1; ++i1) {
                this.setBlockAndMetadata(world, i1, 1, -5, this.fenceGateBlock, 0);
                this.setBlockAndMetadata(world, i1, 1, 5, this.fenceGateBlock, 2);
            }
            for (k1 = -1; k1 <= 1; ++k1) {
                this.setBlockAndMetadata(world, -5, 1, k1, this.fenceGateBlock, 1);
                this.setBlockAndMetadata(world, 5, 1, k1, this.fenceGateBlock, 3);
            }
            for (i1 = -1; i1 <= 1; ++i1) {
                for (k1 = -1; k1 <= 1; ++k1) {
                    if (random.nextInt(3) != 0) continue;
                    int j1 = 1;
                    int j2 = 1;
                    if (i1 == 0 && k1 == 0 && random.nextBoolean()) {
                        ++j2;
                    }
                    for (int j3 = j1; j3 <= j2; ++j3) {
                        this.setBlockAndMetadata(world, i1, j3, k1, Blocks.hay_block, 0);
                    }
                }
            }
            int animals = 4 + random.nextInt(5);
            for (int l = 0; l < animals; ++l) {
                EntityAnimal animal = LOTRWorldGenGondorBarn.getRandomAnimal(world, random);
                int i12 = 3 * (random.nextBoolean() ? 1 : -1);
                int k12 = 3 * (random.nextBoolean() ? 1 : -1);
                this.spawnNPCAndSetHome((EntityCreature)animal, world, i12, 1, k12, 0);
                animal.detachHome();
            }
            return true;
        }
    }

    public static class Tree
    extends LOTRWorldGenGondorVillageFarm {
        public Tree(boolean flag) {
            super(flag);
        }

        @Override
        public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
            int k1;
            int i1;
            if (!super.generateWithSetRotation(world, random, i, j, k, rotation)) {
                return false;
            }
            for (i1 = -5; i1 <= 5; ++i1) {
                for (k1 = -5; k1 <= 5; ++k1) {
                    int i2 = Math.abs(i1);
                    int k2 = Math.abs(k1);
                    if (i2 != 5 || k2 != 5) continue;
                    this.setBlockAndMetadata(world, i1, 2, k1, this.rockWallBlock, this.rockWallMeta);
                    this.setBlockAndMetadata(world, i1, 3, k1, (Block)Blocks.leaves, 4);
                }
            }
            for (int l = 0; l < 16; ++l) {
                LOTRTreeType tree = Tree.getRandomTree(random);
                WorldGenAbstractTree treeGen = tree.create(this.notifyChanges, random);
                if (treeGen == null) continue;
                int i12 = 0;
                int j1 = 1;
                int k12 = 0;
                if (treeGen.generate(world, random, this.getX(i12, k12), this.getY(j1), this.getZ(i12, k12))) break;
            }
            for (i1 = -4; i1 <= 4; ++i1) {
                for (k1 = -4; k1 <= 4; ++k1) {
                    int j1 = 1;
                    if (this.isOpaque(world, i1, j1, k1) || random.nextInt(8) != 0) continue;
                    this.plantFlower(world, random, i1, j1, k1);
                }
            }
            return true;
        }

        public static LOTRTreeType getRandomTree(Random random) {
            ArrayList<LOTRTreeType> treeList = new ArrayList<LOTRTreeType>();
            treeList.add(LOTRTreeType.OAK);
            treeList.add(LOTRTreeType.OAK_LARGE);
            treeList.add(LOTRTreeType.BIRCH);
            treeList.add(LOTRTreeType.BIRCH_TALL);
            treeList.add(LOTRTreeType.BIRCH_LARGE);
            treeList.add(LOTRTreeType.BEECH);
            treeList.add(LOTRTreeType.BEECH_LARGE);
            treeList.add(LOTRTreeType.LEBETHRON);
            treeList.add(LOTRTreeType.LEBETHRON_LARGE);
            treeList.add(LOTRTreeType.CEDAR);
            treeList.add(LOTRTreeType.APPLE);
            treeList.add(LOTRTreeType.PEAR);
            treeList.add(LOTRTreeType.PLUM);
            treeList.add(LOTRTreeType.ALMOND);
            treeList.add(LOTRTreeType.OLIVE);
            return (LOTRTreeType)((Object)treeList.get(random.nextInt(treeList.size())));
        }
    }

}

