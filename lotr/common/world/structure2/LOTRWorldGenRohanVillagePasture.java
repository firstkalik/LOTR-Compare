/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenGondorBarn;
import lotr.common.world.structure2.LOTRWorldGenRohanStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRohanVillagePasture
extends LOTRWorldGenRohanStructure {
    public LOTRWorldGenRohanVillagePasture(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int j1;
        int i1;
        this.setOriginAndRotation(world, i, j, k, rotation, 5);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i12 = -4; i12 <= 4; ++i12) {
                for (int k12 = -4; k12 <= 4; ++k12) {
                    j1 = this.getTopBlock(world, i12, k12) - 1;
                    if (!this.isSurface(world, i12, j1, k12)) {
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
        for (i1 = -4; i1 <= 4; ++i1) {
            for (k1 = -4; k1 <= 4; ++k1) {
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    if (j1 == 0) {
                        int randomFloor = random.nextInt(3);
                        if (randomFloor == 0) {
                            this.setBlockAndMetadata(world, i1, j1, k1, (Block)Blocks.grass, 0);
                        } else if (randomFloor == 1) {
                            this.setBlockAndMetadata(world, i1, j1, k1, Blocks.dirt, 1);
                        } else if (randomFloor == 2) {
                            this.setBlockAndMetadata(world, i1, j1, k1, LOTRMod.dirtPath, 0);
                        }
                    } else {
                        this.setBlockAndMetadata(world, i1, j1, k1, Blocks.dirt, 0);
                    }
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 1; j1 <= 5; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                if (i2 == 4 && k2 == 4) {
                    this.setGrassToDirt(world, i1, -1, k1);
                    for (j1 = 1; j1 <= 2; ++j1) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.logBlock, this.logMeta);
                    }
                    this.setBlockAndMetadata(world, i1, 3, k1, Blocks.torch, 5);
                    continue;
                }
                if (i2 != 4 && k2 != 4) continue;
                this.setBlockAndMetadata(world, i1, 1, k1, this.fenceBlock, this.fenceMeta);
            }
        }
        this.setBlockAndMetadata(world, 0, 1, -4, this.fenceGateBlock, 0);
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k1 = -1; k1 <= 1; ++k1) {
                if (random.nextInt(3) != 0) continue;
                int j12 = 1;
                int j2 = 1;
                if (i1 == 0 && k1 == 0 && random.nextBoolean()) {
                    ++j2;
                }
                for (int j3 = j12; j3 <= j2; ++j3) {
                    this.setBlockAndMetadata(world, i1, j3, k1, Blocks.hay_block, 0);
                }
            }
        }
        int animals = 4 + random.nextInt(5);
        for (int l = 0; l < animals; ++l) {
            EntityAnimal animal = LOTRWorldGenGondorBarn.getRandomAnimal(world, random);
            int i13 = 3 * (random.nextBoolean() ? 1 : -1);
            int k13 = 3 * (random.nextBoolean() ? 1 : -1);
            if (random.nextBoolean()) {
                this.spawnNPCAndSetHome((EntityCreature)animal, world, i13, 1, 0, 0);
            } else {
                this.spawnNPCAndSetHome((EntityCreature)animal, world, 0, 1, k13, 0);
            }
            animal.detachHome();
        }
        return true;
    }
}

