/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityDunedain;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenRangerStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRangerStables
extends LOTRWorldGenRangerStructure {
    public LOTRWorldGenRangerStables(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i17;
        int i2;
        int i12;
        int k1;
        int i13;
        int k12;
        int j1;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, 1, -2);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i14 = -5; i14 <= 5; ++i14) {
                for (int k13 = -1; k13 <= 10; ++k13) {
                    j1 = this.getTopBlock(world, i14, k13) - 1;
                    if (!this.isSurface(world, i14, j1, k13)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 5) continue;
                    return false;
                }
            }
        }
        for (int i15 = -4; i15 <= 4; ++i15) {
            for (k1 = 0; k1 <= 9; ++k1) {
                i2 = Math.abs(i15);
                for (j12 = 0; !(j12 < 0 && this.isOpaque(world, i15, j12, k1) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i15, j12, k1, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i15, j12 - 1, k1);
                }
                for (j12 = 1; j12 <= 8; ++j12) {
                    this.setAir(world, i15, j12, k1);
                }
                if (k1 > 4 || k1 != 0 && k1 != 4 && i2 != 4) continue;
                boolean beam = false;
                if (!(k1 != 0 && k1 != 4 || i2 != 0 && i2 != 4)) {
                    beam = true;
                }
                if (beam) {
                    for (j1 = 1; j1 <= 3; ++j1) {
                        this.setBlockAndMetadata(world, i15, j1, k1, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    continue;
                }
                if (k1 == 4) {
                    for (j1 = 1; j1 <= 3; ++j1) {
                        this.setBlockAndMetadata(world, i15, j1, k1, this.plankBlock, this.plankMeta);
                    }
                    continue;
                }
                for (j1 = 1; j1 <= 3; ++j1) {
                    this.setBlockAndMetadata(world, i15, j1, k1, this.wallBlock, this.wallMeta);
                }
            }
        }
        for (int k13 : new int[]{0, 4}) {
            for (int i16 = -3; i16 <= 3; ++i16) {
                if (i16 == 0) continue;
                this.setBlockAndMetadata(world, i16, 3, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
            }
        }
        for (int i17 : new int[]{-4, 0, 4}) {
            for (k12 = 0; k12 <= 3; ++k12) {
                this.setBlockAndMetadata(world, i17, 4, k12, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
            for (j1 = 4; j1 <= 6; ++j1) {
                this.setBlockAndMetadata(world, i17, j1, 4, this.woodBeamBlock, this.woodBeamMeta);
            }
        }
        for (int i18 = -4; i18 <= 4; ++i18) {
            this.setBlockAndMetadata(world, i18, 7, 4, this.woodBeamBlock, this.woodBeamMeta | 4);
        }
        int[] i18 = new int[]{-4, 4};
        k1 = i18.length;
        for (i2 = 0; i2 < k1; ++i2) {
            i17 = i18[i2];
            this.setBlockAndMetadata(world, i17, 5, 2, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 5, 3, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 6, 3, this.wallBlock, this.wallMeta);
        }
        this.setBlockAndMetadata(world, -2, 1, 0, this.doorBlock, 1);
        this.setBlockAndMetadata(world, -2, 2, 0, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 2, 2, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -4, 2, 2, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 4, 2, 2, this.fenceBlock, this.fenceMeta);
        for (int i19 = -3; i19 <= 3; ++i19) {
            for (k1 = 1; k1 <= 3; ++k1) {
                this.setBlockAndMetadata(world, i19, 0, k1, this.plankBlock, this.plankMeta);
            }
            if (i19 == 0) continue;
            for (k1 = 1; k1 <= 3; ++k1) {
                this.setBlockAndMetadata(world, i19, 4, k1, this.plankSlabBlock, this.plankSlabMeta | 8);
            }
            this.setBlockAndMetadata(world, i19, 4, 4, this.plankBlock, this.plankMeta);
        }
        for (int i17 : new int[]{-4, 0, 4}) {
            for (j1 = 1; j1 <= 3; ++j1) {
                this.setBlockAndMetadata(world, i17, j1, 9, this.woodBeamBlock, this.woodBeamMeta);
            }
            for (k12 = 5; k12 <= 9; ++k12) {
                this.setBlockAndMetadata(world, i17, 4, k12, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
            for (k12 = 5; k12 <= 8; ++k12) {
                this.setBlockAndMetadata(world, i17, 1, k12, this.fenceBlock, this.fenceMeta);
                this.setBlockAndMetadata(world, i17, 3, k12, this.fenceBlock, this.fenceMeta);
            }
            this.setBlockAndMetadata(world, i17, 2, 5, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i17, 2, 8, this.fenceBlock, this.fenceMeta);
        }
        int[] i19 = new int[]{-4, 4};
        k1 = i19.length;
        for (i2 = 0; i2 < k1; ++i2) {
            i17 = i19[i2];
            this.setBlockAndMetadata(world, i17, 5, 5, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 5, 6, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 5, 7, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 6, 5, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 6, 6, this.wallBlock, this.wallMeta);
            this.setBlockAndMetadata(world, i17, 7, 5, this.wallBlock, this.wallMeta);
        }
        for (i13 = -3; i13 <= 3; ++i13) {
            if (i13 == 0) continue;
            this.setBlockAndMetadata(world, i13, 1, 9, this.fenceGateBlock, 2);
            for (k1 = 5; k1 <= 8; ++k1) {
                int randomFloor = random.nextInt(3);
                if (randomFloor == 0) {
                    this.setBlockAndMetadata(world, i13, 0, k1, (Block)Blocks.grass, 0);
                } else if (randomFloor == 1) {
                    this.setBlockAndMetadata(world, i13, 0, k1, Blocks.dirt, 1);
                } else if (randomFloor == 2) {
                    this.setBlockAndMetadata(world, i13, 0, k1, LOTRMod.dirtPath, 0);
                }
                if (!random.nextBoolean()) continue;
                this.setBlockAndMetadata(world, i13, 1, k1, LOTRMod.thatchFloor, 0);
            }
            this.setBlockAndMetadata(world, i13, 4, 5, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i13, 4, 6, this.plankSlabBlock, this.plankSlabMeta | 8);
        }
        this.setBlockAndMetadata(world, -3, 3, 9, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, -1, 3, 9, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 1, 3, 9, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 3, 3, 9, this.plankStairBlock, 5);
        for (i13 = -5; i13 <= 5; ++i13) {
            int l;
            int avoidBeam;
            int n = avoidBeam = IntMath.mod((int)i13, (int)4) == 0 ? 1 : 0;
            if (avoidBeam == 0) {
                this.setBlockAndMetadata(world, i13, 4, 0, this.roofStairBlock, 2);
                this.setBlockAndMetadata(world, i13, 4, 9, this.roofStairBlock, 3);
            }
            for (l = 0; l <= 2; ++l) {
                this.setBlockAndMetadata(world, i13, 5 + l, 1 + l, this.roofStairBlock, 2);
                this.setBlockAndMetadata(world, i13, 5 + l, 8 - l, this.roofStairBlock, 3);
            }
            for (int k14 = 4; k14 <= 5; ++k14) {
                this.setBlockAndMetadata(world, i13, 8, k14, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (Math.abs(i13) != 5) continue;
            for (l = 0; l <= 3; ++l) {
                this.setBlockAndMetadata(world, i13, 4 + l, 1 + l, this.roofStairBlock, 7);
                this.setBlockAndMetadata(world, i13, 4 + l, 8 - l, this.roofStairBlock, 6);
            }
        }
        for (int i17 : new int[]{-4, 0, 4}) {
            this.setBlockAndMetadata(world, i17, 3, -1, Blocks.torch, 4);
            this.setBlockAndMetadata(world, i17, 3, 10, Blocks.torch, 3);
        }
        this.setBlockAndMetadata(world, -5, 3, 4, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 5, 3, 4, Blocks.torch, 2);
        this.setBlockAndMetadata(world, -3, 1, 3, Blocks.crafting_table, 0);
        this.placeChest(world, random, -2, 1, 3, 2, this.chestContentsHouse);
        this.setBlockAndMetadata(world, -1, 1, 3, this.plankBlock, this.plankMeta);
        this.placePlateWithCertainty(world, random, -1, 2, 3, this.plateBlock, LOTRFoods.RANGER);
        this.setBlockAndMetadata(world, 0, 1, 3, this.plankBlock, this.plankMeta);
        this.placeBarrel(world, random, 0, 2, 3, 2, LOTRFoods.RANGER_DRINK);
        this.setBlockAndMetadata(world, 1, 1, 3, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 1, 2, 3, 0, LOTRFoods.RANGER_DRINK);
        this.setBlockAndMetadata(world, 3, 1, 3, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 3, 2, 3, 1, LOTRFoods.RANGER_DRINK);
        this.setBlockAndMetadata(world, 2, 1, 1, this.bedBlock, 1);
        this.setBlockAndMetadata(world, 3, 1, 1, this.bedBlock, 9);
        this.setBlockAndMetadata(world, -3, 3, 2, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 3, 3, 2, Blocks.torch, 1);
        for (int j13 = 1; j13 <= 4; ++j13) {
            this.setBlockAndMetadata(world, 2, j13, 3, Blocks.ladder, 2);
        }
        this.setBlockAndMetadata(world, 0, 7, 5, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, -3, 7, 5, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 3, 7, 5, this.plankStairBlock, 5);
        for (i12 = -3; i12 <= 3; ++i12) {
            if (random.nextInt(3) == 0) continue;
            this.setBlockAndMetadata(world, i12, 5, 2, Blocks.hay_block, 0);
        }
        for (i12 = -3; i12 <= 3; ++i12) {
            if (random.nextInt(3) == 0) continue;
            int h = 5;
            int h1 = h + random.nextInt(2);
            for (j12 = h; j12 <= h1; ++j12) {
                this.setBlockAndMetadata(world, i12, j12, 6, Blocks.hay_block, 0);
            }
        }
        for (int i17 : new int[]{-3, 3}) {
            for (k12 = 3; k12 <= 5; ++k12) {
                if (random.nextInt(3) == 0) continue;
                int h = 5;
                int h1 = h + random.nextInt(2);
                for (int j14 = h; j14 <= h1; ++j14) {
                    this.setBlockAndMetadata(world, i17, j14, k12, Blocks.hay_block, 0);
                }
            }
        }
        this.setBlockAndMetadata(world, -2, 5, 3, this.bedBlock, 3);
        this.setBlockAndMetadata(world, -3, 5, 3, this.bedBlock, 11);
        this.placeChest(world, random, -3, 5, 5, 4, this.chestContentsHouse);
        this.setBlockAndMetadata(world, -3, 6, 4, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 3, 6, 4, Blocks.torch, 1);
        int men = 1;
        for (int l = 0; l < men; ++l) {
            LOTREntityDunedain dunedain = new LOTREntityDunedain(world);
            this.spawnNPCAndSetHome(dunedain, world, 0, 1, 2, 8);
        }
        for (int i16 : new int[]{-2, 2}) {
            LOTREntityHorse horse = new LOTREntityHorse(world);
            this.spawnNPCAndSetHome((EntityCreature)horse, world, i16, 1, 7, 0);
            horse.setHorseType(0);
            horse.saddleMountForWorldGen();
            horse.detachHome();
        }
        return true;
    }
}

