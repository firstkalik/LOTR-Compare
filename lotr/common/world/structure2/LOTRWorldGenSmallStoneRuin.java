/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Direction
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenSmallStoneRuin
extends LOTRWorldGenStructureBase2 {
    private Block brickBlock;
    private int brickMeta;
    private Block plankBlock;
    private int plankMeta;
    private Block plankSlabBlock;
    private int plankSlabMeta;
    private Block woodBeamBlock;
    private int woodBeamMeta;
    private Block barsBlock;

    public LOTRWorldGenSmallStoneRuin(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        block218: {
            int i2;
            int k1;
            int k2;
            RuinType ruinType;
            int k12;
            int i1;
            int width;
            int height;
            int i122;
            int i13;
            int i22;
            block227: {
                int k13;
                block226: {
                    block225: {
                        int centreWidth;
                        int j1;
                        int j12;
                        int radius;
                        block224: {
                            int d;
                            int i14;
                            int i23;
                            int j2;
                            block223: {
                                block222: {
                                    int pitDepth;
                                    block221: {
                                        int k22;
                                        int i24;
                                        int height2;
                                        int k14;
                                        block220: {
                                            int j13;
                                            int j14;
                                            block219: {
                                                block217: {
                                                    ruinType = RuinType.getRandomType(random);
                                                    this.setOriginAndRotation(world, i, j, k, rotation, ruinType.centreShift);
                                                    radius = ruinType.checkRadius;
                                                    if (this.restrictions) {
                                                        int minHeight = 0;
                                                        int maxHeight = 0;
                                                        for (i13 = -radius; i13 <= radius; ++i13) {
                                                            for (k1 = -radius; k1 <= radius; ++k1) {
                                                                if (!this.isSuitableSpawnBlock(world, i13, k1)) {
                                                                    return false;
                                                                }
                                                                j1 = this.getTopBlock(world, i13, k1) - 1;
                                                                if (j1 < minHeight) {
                                                                    minHeight = j1;
                                                                }
                                                                if (j1 > maxHeight) {
                                                                    maxHeight = j1;
                                                                }
                                                                if (maxHeight - minHeight <= 7) continue;
                                                                return false;
                                                            }
                                                        }
                                                    }
                                                    if (ruinType != RuinType.COLUMN) break block217;
                                                    this.brickBlock = Blocks.stonebrick;
                                                    this.brickMeta = 0;
                                                    this.layFoundation(world, 0, 0, 0, this.brickBlock, this.brickMeta);
                                                    this.layFoundation(world, -1, 0, 0, this.brickBlock, this.brickMeta);
                                                    this.layFoundation(world, 1, 0, 0, this.brickBlock, this.brickMeta);
                                                    this.layFoundation(world, 0, 0, -1, this.brickBlock, this.brickMeta);
                                                    this.layFoundation(world, 0, 0, 1, this.brickBlock, this.brickMeta);
                                                    int height3 = 3 + random.nextInt(3);
                                                    for (j13 = 1; j13 <= height3; ++j13) {
                                                        if (random.nextInt(3) == 0) {
                                                            this.setBlockAndMetadata(world, 0, j13, 0, Blocks.stonebrick, 3);
                                                            continue;
                                                        }
                                                        this.setBlockAndMetadata(world, 0, j13, 0, this.brickBlock, this.brickMeta);
                                                    }
                                                    this.setBlockAndMetadata(world, -1, 1, 0, Blocks.stone_brick_stairs, 1);
                                                    this.setBlockAndMetadata(world, 1, 1, 0, Blocks.stone_brick_stairs, 0);
                                                    this.setBlockAndMetadata(world, 0, 1, -1, Blocks.stone_brick_stairs, 2);
                                                    this.setBlockAndMetadata(world, 0, 1, 1, Blocks.stone_brick_stairs, 3);
                                                    break block218;
                                                }
                                                if (ruinType != RuinType.ROOM) break block219;
                                                for (i1 = -2; i1 <= 2; ++i1) {
                                                    for (k12 = -2; k12 <= 2; ++k12) {
                                                        i22 = Math.abs(i1);
                                                        k2 = Math.abs(k12);
                                                        this.layFoundationRandomStoneBrick(world, random, i1, 0, k12);
                                                        if (i22 <= 1 && k2 <= 1) {
                                                            this.setBlockAndMetadata(world, i1, 0, k12, Blocks.cobblestone, 0);
                                                        }
                                                        if (i22 != 2 && k2 != 2 || i1 == 0 && k12 == -2) continue;
                                                        height2 = 1 + random.nextInt(3);
                                                        for (int j15 = 1; j15 <= height2; ++j15) {
                                                            this.placeRandomStoneBrick(world, random, i1, j15, k12);
                                                        }
                                                    }
                                                }
                                                if (random.nextInt(4) != 0) break block218;
                                                this.placeChest(world, random, 0, 1, 1, LOTRMod.chestStone, 2, LOTRChestContents.RUINED_HOUSE);
                                                break block218;
                                            }
                                            if (ruinType != RuinType.BAR_TOWER) break block220;
                                            int randomBar = random.nextInt(2);
                                            if (randomBar == 0) {
                                                this.barsBlock = Blocks.iron_bars;
                                            } else if (randomBar == 1) {
                                                this.barsBlock = LOTRMod.bronzeBars;
                                            }
                                            for (i14 = -2; i14 <= 2; ++i14) {
                                                for (k13 = -2; k13 <= 2; ++k13) {
                                                    int i25 = Math.abs(i14);
                                                    int k23 = Math.abs(k13);
                                                    if ((i25 != 2 || k23 > 1) && (k23 != 2 || i25 > 1)) continue;
                                                    this.layFoundationRandomStoneBrick(world, random, i14, 0, k13);
                                                    int height4 = 4 + random.nextInt(3);
                                                    for (j14 = 1; j14 <= height4; ++j14) {
                                                        this.placeRandomStoneBrick(world, random, i14, j14, k13);
                                                    }
                                                }
                                            }
                                            for (j13 = 1; j13 <= 2; ++j13) {
                                                this.setAir(world, 0, j13, -2);
                                                this.setBlockAndMetadata(world, 0, j13, 2, this.barsBlock, 0);
                                                this.setBlockAndMetadata(world, -2, j13, 0, this.barsBlock, 0);
                                                this.setBlockAndMetadata(world, 2, j13, 0, this.barsBlock, 0);
                                            }
                                            this.setBlockAndMetadata(world, 0, 3, -2, (Block)Blocks.stone_slab, 8);
                                            for (int i122 : new int[]{-1, 1}) {
                                                int k15 = 1;
                                                j14 = this.getTopBlock(world, i122, k15);
                                                if (random.nextInt(10) != 0) continue;
                                                this.placeChest(world, random, i122, j14, k15, LOTRMod.chestStone, 2, LOTRChestContents.RUINED_HOUSE);
                                            }
                                            break block218;
                                        }
                                        if (ruinType != RuinType.PIT_MINE) break block221;
                                        for (i1 = -2; i1 <= 2; ++i1) {
                                            for (k12 = -2; k12 <= 2; ++k12) {
                                                i22 = Math.abs(i1);
                                                k2 = Math.abs(k12);
                                                if (i22 == 2 || k2 == 2) {
                                                    j1 = this.getTopBlock(world, i1, k12);
                                                    for (int j22 = 1; j22 >= (j1 -= random.nextInt(3)); --j22) {
                                                        this.placeRandomStoneBrick(world, random, i1, j22, k12);
                                                        this.setGrassToDirt(world, i1, j22 - 1, k12);
                                                    }
                                                }
                                                if (i22 != 2 || k2 != 2) continue;
                                                height2 = random.nextInt(3);
                                                for (int j16 = 1; j16 <= 1 + height2; ++j16) {
                                                    this.placeRandomStoneBrick(world, random, i1, j16, k12);
                                                    this.setGrassToDirt(world, i1, j16 - 1, k12);
                                                }
                                            }
                                        }
                                        int pitWidth = 4 + random.nextInt(5);
                                        int pitHeight = 2 + random.nextInt(3);
                                        pitDepth = 60 - random.nextInt(5);
                                        int pitBottom = (pitDepth -= this.originY) - pitHeight - 1;
                                        for (i122 = -1; i122 <= 1; ++i122) {
                                            for (k14 = -1; k14 <= 1; ++k14) {
                                                int j17;
                                                i24 = Math.abs(i122);
                                                k22 = Math.abs(k14);
                                                for (int j23 = j17 = this.getTopBlock(world, i122, k14); j23 >= pitDepth; --j23) {
                                                    this.setAir(world, i122, j23, k14);
                                                    if (i24 != 1 || k22 != 1 || random.nextInt(10) != 0) continue;
                                                    this.setBlockAndMetadata(world, i122, j23, k14, (Block)Blocks.stone_slab, 0);
                                                }
                                            }
                                        }
                                        for (i122 = -pitWidth; i122 <= pitWidth; ++i122) {
                                            for (k14 = -pitWidth; k14 <= pitWidth; ++k14) {
                                                int j18;
                                                i24 = Math.abs(i122);
                                                k22 = Math.abs(k14);
                                                int randomFloor = random.nextInt(5);
                                                if (randomFloor == 0) {
                                                    this.placeRandomStoneBrick(world, random, i122, pitBottom, k14);
                                                } else if (randomFloor == 1) {
                                                    this.setBlockAndMetadata(world, i122, pitBottom, k14, Blocks.cobblestone, 0);
                                                } else if (randomFloor == 2) {
                                                    this.setBlockAndMetadata(world, i122, pitBottom, k14, Blocks.stone, 0);
                                                } else if (randomFloor == 3) {
                                                    this.setBlockAndMetadata(world, i122, pitBottom, k14, Blocks.gravel, 0);
                                                } else if (randomFloor == 4) {
                                                    this.setBlockAndMetadata(world, i122, pitBottom, k14, Blocks.dirt, 0);
                                                }
                                                for (j18 = pitBottom + 1; j18 <= pitBottom + pitHeight; ++j18) {
                                                    this.setAir(world, i122, j18, k14);
                                                }
                                                if (i24 == 2 && k22 == 2) {
                                                    for (j18 = pitBottom + 1; j18 <= pitBottom + pitHeight; ++j18) {
                                                        this.setBlockAndMetadata(world, i122, j18, k14, LOTRMod.woodBeamV1, 0);
                                                    }
                                                    continue;
                                                }
                                                if (i24 <= 2 && k22 <= 2 && (i24 == 2 || k22 == 2)) {
                                                    if (random.nextInt(4) == 0) continue;
                                                    this.setBlockAndMetadata(world, i122, pitBottom + pitHeight, k14, Blocks.fence, 0);
                                                    continue;
                                                }
                                                if (random.nextInt(60) == 0) {
                                                    this.placeSkull(world, random, i122, pitBottom + 1, k14);
                                                    continue;
                                                }
                                                if (random.nextInt(120) != 0) continue;
                                                int chestMeta = Direction.directionToFacing[random.nextInt(4)];
                                                this.placeChest(world, random, i122, pitBottom + 1, k14, chestMeta, LOTRChestContents.RUINED_HOUSE);
                                            }
                                        }
                                        break block218;
                                    }
                                    if (ruinType != RuinType.PLINTH) break block222;
                                    for (i1 = -3; i1 <= 2; ++i1) {
                                        for (k12 = -3; k12 <= 2; ++k12) {
                                            this.layFoundation(world, i1, 0, k12, Blocks.cobblestone, 0);
                                        }
                                    }
                                    for (i1 = -2; i1 <= 1; ++i1) {
                                        for (k12 = -2; k12 <= 1; ++k12) {
                                            this.placeRandomStoneBrick(world, random, i1, 1, k12);
                                        }
                                    }
                                    for (int i15 : new int[]{-3, 2}) {
                                        this.setBlockAndMetadata(world, i15, 1, -2, Blocks.stone_brick_stairs, 2);
                                        this.setBlockAndMetadata(world, i15, 1, -1, (Block)Blocks.stone_slab, 8);
                                        this.setBlockAndMetadata(world, i15, 1, 0, (Block)Blocks.stone_slab, 8);
                                        this.setBlockAndMetadata(world, i15, 1, 1, Blocks.stone_brick_stairs, 3);
                                    }
                                    int[] i16 = new int[]{-3, 2};
                                    k12 = i16.length;
                                    for (pitDepth = 0; pitDepth < k12; ++pitDepth) {
                                        k1 = i16[pitDepth];
                                        this.setBlockAndMetadata(world, -2, 1, k1, Blocks.stone_brick_stairs, 1);
                                        this.setBlockAndMetadata(world, -1, 1, k1, (Block)Blocks.stone_slab, 8);
                                        this.setBlockAndMetadata(world, 0, 1, k1, (Block)Blocks.stone_slab, 8);
                                        this.setBlockAndMetadata(world, 1, 1, k1, Blocks.stone_brick_stairs, 0);
                                    }
                                    for (int i17 = -1; i17 <= 0; ++i17) {
                                        for (k12 = -1; k12 <= 0; ++k12) {
                                            if (random.nextInt(3) != 0) continue;
                                            height = 2 + random.nextInt(4);
                                            for (j12 = 2; j12 < 2 + height; ++j12) {
                                                this.setBlockAndMetadata(world, i17, j12, k12, LOTRMod.pillar2, 2);
                                            }
                                            this.setBlockAndMetadata(world, i17, 2 + height, k12, Blocks.stone_brick_stairs, random.nextInt(4));
                                        }
                                    }
                                    break block218;
                                }
                                if (ruinType != RuinType.RUBBLE) break block223;
                                width = 3 + random.nextInt(5);
                                centreWidth = 2;
                                for (i13 = -width; i13 <= width; ++i13) {
                                    for (k1 = -width; k1 <= width; ++k1) {
                                        int d2 = i13 * i13 + k1 * k1;
                                        if (d2 >= width * width) continue;
                                        int j19 = this.getTopBlock(world, i13, k1) - 1;
                                        if (!this.isSuitableSpawnBlock(world, i13, k1)) continue;
                                        if (random.nextInt(3) == 0) {
                                            if (random.nextBoolean()) {
                                                this.setBlockAndMetadata(world, i13, j19, k1, Blocks.cobblestone, 0);
                                            } else {
                                                this.placeRandomStoneBrick(world, random, i13, j19, k1);
                                            }
                                            this.setGrassToDirt(world, i13, j19 - 1, k1);
                                        }
                                        if (d2 <= centreWidth * centreWidth || random.nextInt(6) != 0) continue;
                                        int height5 = 1 + random.nextInt(4);
                                        for (int j24 = j19 + 1; j24 <= j19 + height5; ++j24) {
                                            this.setBlockAndMetadata(world, i13, j24, k1, Blocks.stone, 0);
                                            this.setGrassToDirt(world, i13, j24 - 1, k1);
                                        }
                                    }
                                }
                                if (random.nextInt(6) != 0) break block218;
                                for (i13 = -1; i13 <= 1; ++i13) {
                                    for (k1 = -1; k1 <= 1; ++k1) {
                                        this.layFoundation(world, i13, 0, k1, (Block)Blocks.double_stone_slab, 0);
                                        this.setBlockAndMetadata(world, i13, 1, k1, (Block)Blocks.stone_slab, 0);
                                    }
                                }
                                this.setBlockAndMetadata(world, 0, 1, 0, Blocks.stonebrick, 3);
                                this.placeChest(world, random, 0, 0, 0, LOTRMod.chestStone, 2, LOTRChestContents.RUINED_HOUSE);
                                break block218;
                            }
                            if (ruinType != RuinType.QUARRY) break block224;
                            int r = 9;
                            for (i14 = -r; i14 <= r; ++i14) {
                                for (k13 = -r; k13 <= r; ++k13) {
                                    for (j12 = r; j12 >= 1; --j12) {
                                        j2 = j12 - -5;
                                        d = i14 * i14 + j2 * j2 + k13 * k13;
                                        if (d >= r * r) continue;
                                        boolean grass = !this.isOpaque(world, i14, j12 + 1, k13);
                                        this.setBlockAndMetadata(world, i14, j12, k13, (Block)(grass ? Blocks.grass : Blocks.dirt), 0);
                                        this.setGrassToDirt(world, i14, j12 - 1, k13);
                                        if (j12 != 1) continue;
                                        this.layFoundation(world, i14, 0, k13, Blocks.dirt, 0);
                                    }
                                }
                            }
                            r = 5;
                            for (i14 = -r; i14 <= r; ++i14) {
                                for (k13 = -r; k13 <= r; ++k13) {
                                    for (j12 = -r; j12 <= r; ++j12) {
                                        j2 = j12 - 1;
                                        d = i14 * i14 + j2 * j2 + k13 * k13;
                                        if (d >= r * r) continue;
                                        if (random.nextInt(4) == 0) {
                                            this.setBlockAndMetadata(world, i14, j12, k13, Blocks.cobblestone, 0);
                                        } else {
                                            this.setBlockAndMetadata(world, i14, j12, k13, Blocks.stone, 0);
                                        }
                                        this.setGrassToDirt(world, i14, j12 - 1, k13);
                                    }
                                }
                            }
                            r = 5;
                            int r1 = 3;
                            for (i13 = -r; i13 <= r; ++i13) {
                                for (k1 = -r; k1 <= r; ++k1) {
                                    for (j1 = -r; j1 <= r; ++j1) {
                                        i23 = i13 - 2;
                                        int j25 = j1 - 1;
                                        int k24 = k1 - 2;
                                        int d3 = i23 * i23 + j25 * j25 + k24 * k24;
                                        if (d3 >= r1 * r1) continue;
                                        this.setAir(world, i13, j1, k1);
                                        if (this.getBlock(world, i13, j1 - 1, k1) != Blocks.dirt) continue;
                                        this.setBlockAndMetadata(world, i13, j1 - 1, k1, (Block)Blocks.grass, 0);
                                    }
                                }
                            }
                            boolean rotten = random.nextBoolean();
                            for (j12 = -1; j12 <= 3; ++j12) {
                                if (rotten) {
                                    this.setBlockAndMetadata(world, 1, j12, 1, LOTRMod.woodBeamRotten, 0);
                                    continue;
                                }
                                this.setBlockAndMetadata(world, 1, j12, 1, LOTRMod.woodBeamV1, 0);
                            }
                            if (rotten) {
                                this.setBlockAndMetadata(world, 4, 1, 3, LOTRMod.stairsRotten, 1);
                                this.setBlockAndMetadata(world, 4, 0, 3, LOTRMod.stairsRotten, 4);
                                this.setBlockAndMetadata(world, 3, 0, 3, LOTRMod.stairsRotten, 1);
                                this.setBlockAndMetadata(world, 3, -1, 3, LOTRMod.stairsRotten, 4);
                                this.setBlockAndMetadata(world, 2, -1, 3, LOTRMod.planksRotten, 0);
                                this.setBlockAndMetadata(world, 2, -1, 2, LOTRMod.stairsRotten, 2);
                                this.setBlockAndMetadata(world, 5, 2, 2, LOTRMod.stairsRotten, 3);
                                this.setGrassToDirt(world, 5, 1, 2);
                            } else {
                                this.setBlockAndMetadata(world, 4, 1, 3, Blocks.oak_stairs, 1);
                                this.setBlockAndMetadata(world, 4, 0, 3, Blocks.oak_stairs, 4);
                                this.setBlockAndMetadata(world, 3, 0, 3, Blocks.oak_stairs, 1);
                                this.setBlockAndMetadata(world, 3, -1, 3, Blocks.oak_stairs, 4);
                                this.setBlockAndMetadata(world, 2, -1, 3, Blocks.planks, 0);
                                this.setBlockAndMetadata(world, 2, -1, 2, Blocks.oak_stairs, 2);
                                this.setBlockAndMetadata(world, 5, 2, 2, Blocks.oak_stairs, 3);
                                this.setGrassToDirt(world, 5, 1, 2);
                            }
                            for (int i15 = -2; i15 <= 2; ++i15) {
                                for (int k16 = -2; k16 <= 2; ++k16) {
                                    i23 = Math.abs(i15);
                                    int k25 = Math.abs(k16);
                                    if (i23 != 2 && k25 != 2 || !random.nextBoolean()) continue;
                                    if (rotten) {
                                        this.setBlockAndMetadata(world, i15, 6, k16, LOTRMod.fenceRotten, 0);
                                        continue;
                                    }
                                    this.setBlockAndMetadata(world, i15, 6, k16, Blocks.fence, 0);
                                }
                            }
                            break block218;
                        }
                        if (ruinType != RuinType.OBELISK) break block225;
                        width = radius;
                        centreWidth = 2;
                        for (i13 = -width; i13 <= width; ++i13) {
                            for (k1 = -width; k1 <= width; ++k1) {
                                j1 = this.getTopBlock(world, i13, k1) - 1;
                                if (!this.isSuitableSpawnBlock(world, i13, k1)) continue;
                                if (random.nextInt(3) == 0) {
                                    if (random.nextBoolean()) {
                                        this.setBlockAndMetadata(world, i13, j1, k1, Blocks.cobblestone, 0);
                                    } else {
                                        this.setBlockAndMetadata(world, i13, j1, k1, Blocks.gravel, 0);
                                    }
                                    this.setGrassToDirt(world, i13, j1 - 1, k1);
                                }
                                if (i13 < 4 && k1 < 4 || random.nextInt(6) != 0) continue;
                                this.setGrassToDirt(world, i13, j1, k1);
                                this.placeRandomStoneBrick(world, random, i13, j1 + 1, k1);
                                if (random.nextInt(3) != 0) continue;
                                int rb = random.nextInt(3);
                                if (rb == 0) {
                                    this.placeRandomStoneBrick(world, random, i13, j1 + 2, k1);
                                    continue;
                                }
                                if (rb == 1) {
                                    this.setBlockAndMetadata(world, i13, j1 + 2, k1, Blocks.stone_brick_stairs, random.nextInt(4));
                                    continue;
                                }
                                if (rb != 2) continue;
                                this.setBlockAndMetadata(world, i13, j1 + 2, k1, LOTRMod.wallStoneV, 1);
                            }
                        }
                        for (i13 = -1; i13 <= 1; ++i13) {
                            for (k1 = -1; k1 <= 1; ++k1) {
                                i2 = Math.abs(i13);
                                int k26 = Math.abs(k1);
                                this.layFoundationRandomStoneBrick(world, random, i13, 0, k1);
                                this.placeRandomStoneBrick(world, random, i13, 1, k1);
                                if (i2 != 0 && k26 != 0) continue;
                                this.setBlockAndMetadata(world, i13, 1, k1, Blocks.stonebrick, 3);
                                this.placeRandomStoneBrick(world, random, i13, 2, k1);
                            }
                        }
                        this.setBlockAndMetadata(world, -1, 3, 0, Blocks.stone_brick_stairs, 1);
                        this.setBlockAndMetadata(world, 1, 3, 0, Blocks.stone_brick_stairs, 0);
                        this.setBlockAndMetadata(world, 0, 3, -1, Blocks.stone_brick_stairs, 2);
                        this.setBlockAndMetadata(world, 0, 3, 1, Blocks.stone_brick_stairs, 3);
                        int top = 4 + random.nextInt(4);
                        for (j12 = 3; j12 <= top; ++j12) {
                            this.placeRandomStoneBrick(world, random, 0, j12, 0);
                        }
                        for (j12 = top + 1; j12 <= top + 2; ++j12) {
                            this.setBlockAndMetadata(world, 0, j12, 0, LOTRMod.wallStoneV, 1);
                        }
                        break block218;
                    }
                    if (ruinType != RuinType.WELL) break block226;
                    int wellDepth = 4 + random.nextInt(5);
                    int wellBottom = -wellDepth - 1;
                    boolean water = random.nextBoolean();
                    int waterDepth = 2 + random.nextInt(5);
                    if (waterDepth > wellDepth) {
                        waterDepth = wellDepth;
                    }
                    for (i122 = -2; i122 <= 1; ++i122) {
                        for (int k17 = -2; k17 <= 1; ++k17) {
                            int j1;
                            if (i122 == -2 || i122 == 1 || k17 == -2 || k17 == 1) {
                                this.placeRandomStoneBrick(world, random, i122, 1, k17);
                                this.setBlockAndMetadata(world, i122, 0, k17, (Block)Blocks.double_stone_slab, 0);
                                for (j1 = -1; j1 >= wellBottom; --j1) {
                                    this.placeRandomStoneBrick(world, random, i122, j1, k17);
                                }
                                this.setGrassToDirt(world, i122, wellBottom - 1, k17);
                                continue;
                            }
                            for (j1 = 1; j1 >= wellBottom; --j1) {
                                if (water && j1 <= wellBottom + waterDepth) {
                                    this.setBlockAndMetadata(world, i122, j1, k17, Blocks.water, 0);
                                    continue;
                                }
                                this.setAir(world, i122, j1, k17);
                            }
                            this.setGrassToDirt(world, i122, wellBottom - 1, k17);
                            this.setBlockAndMetadata(world, i122, wellBottom, k17, Blocks.stone, 0);
                            this.setBlockAndMetadata(world, i122, wellBottom + 1, k17, Blocks.gravel, 0);
                            if (random.nextBoolean()) {
                                this.setBlockAndMetadata(world, i122, wellBottom + 2, k17, Blocks.gravel, 0);
                            }
                            if (random.nextInt(8) == 0) {
                                int chestMeta = Direction.directionToFacing[random.nextInt(4)];
                                this.placeChest(world, random, i122, wellBottom + 1, k17, LOTRMod.chestStone, chestMeta, LOTRChestContents.RUINED_HOUSE);
                            }
                            if (random.nextInt(3) != 0) continue;
                            this.setBlockAndMetadata(world, i122, 0, k17, LOTRMod.fenceRotten, 0);
                        }
                    }
                    this.setBlockAndMetadata(world, -2, 1, -2, (Block)Blocks.stone_slab, 0);
                    this.setBlockAndMetadata(world, 1, 1, -2, (Block)Blocks.stone_slab, 0);
                    this.setBlockAndMetadata(world, -2, 1, 1, (Block)Blocks.stone_slab, 0);
                    this.setBlockAndMetadata(world, 1, 1, 1, (Block)Blocks.stone_slab, 0);
                    break block218;
                }
                if (ruinType != RuinType.TURRET) break block227;
                int randomWood = random.nextInt(3);
                if (randomWood == 0) {
                    this.plankBlock = Blocks.planks;
                    this.plankMeta = 0;
                    this.plankSlabBlock = Blocks.wooden_slab;
                    this.plankSlabMeta = 0;
                    this.woodBeamBlock = LOTRMod.woodBeamV1;
                    this.woodBeamMeta = 0;
                } else if (randomWood == 1) {
                    this.plankBlock = Blocks.planks;
                    this.plankMeta = 1;
                    this.plankSlabBlock = Blocks.wooden_slab;
                    this.plankSlabMeta = 1;
                    this.woodBeamBlock = LOTRMod.woodBeamV1;
                    this.woodBeamMeta = 1;
                } else if (randomWood == 2) {
                    this.plankBlock = LOTRMod.planksRotten;
                    this.plankMeta = 0;
                    this.plankSlabBlock = LOTRMod.rottenSlabSingle;
                    this.plankSlabMeta = 0;
                    this.woodBeamBlock = LOTRMod.woodBeamRotten;
                    this.woodBeamMeta = 0;
                }
                int randomBar = random.nextInt(2);
                if (randomBar == 0) {
                    this.barsBlock = Blocks.iron_bars;
                } else if (randomBar == 1) {
                    this.barsBlock = LOTRMod.bronzeBars;
                }
                for (i13 = -4; i13 <= 4; ++i13) {
                    for (k1 = -4; k1 <= 4; ++k1) {
                        i2 = Math.abs(i13);
                        int k27 = Math.abs(k1);
                        this.layFoundationRandomStoneBrick(world, random, i13, 0, k1);
                        this.placeRandomStoneBrick(world, random, i13, 1, k1);
                        if (i2 <= 3 && k27 <= 3) {
                            int j1;
                            if (i2 == 3 && k27 == 3) {
                                this.placeRandomStoneBrick(world, random, i13, 2, k1);
                                for (j1 = 3; j1 <= 5; ++j1) {
                                    this.setBlockAndMetadata(world, i13, j1, k1, this.woodBeamBlock, this.woodBeamMeta);
                                }
                                this.placeRandomStoneBrick(world, random, i13, 6, k1);
                            } else if (i2 == 3 || k27 == 3) {
                                for (j1 = 2; j1 <= 6; ++j1) {
                                    if (random.nextInt(8) == 0) {
                                        this.setAir(world, i13, j1, k1);
                                        continue;
                                    }
                                    this.placeRandomStoneBrick(world, random, i13, j1, k1);
                                }
                                if (i2 <= 1 || k27 <= 1) {
                                    if (random.nextInt(4) == 0) {
                                        this.setAir(world, i13, 4, k1);
                                    } else {
                                        this.setBlockAndMetadata(world, i13, 4, k1, this.barsBlock, 0);
                                    }
                                }
                            } else {
                                if (random.nextInt(4) == 0) {
                                    if (random.nextBoolean()) {
                                        this.setBlockAndMetadata(world, i13, 1, k1, Blocks.gravel, 0);
                                    } else {
                                        this.setBlockAndMetadata(world, i13, 1, k1, Blocks.cobblestone, 0);
                                    }
                                } else if (random.nextInt(4) == 0) {
                                    this.setAir(world, i13, 1, k1);
                                } else {
                                    this.setBlockAndMetadata(world, i13, 1, k1, this.plankBlock, this.plankMeta);
                                }
                                for (j1 = 2; j1 <= 5; ++j1) {
                                    this.setAir(world, i13, j1, k1);
                                }
                                if (random.nextInt(5) == 0) {
                                    this.setAir(world, i13, 6, k1);
                                } else {
                                    this.setBlockAndMetadata(world, i13, 6, k1, this.plankSlabBlock, this.plankSlabMeta);
                                }
                            }
                        }
                        if (i2 != 4 && k27 != 4 || random.nextInt(3) == 0) continue;
                        if (random.nextInt(3) == 0) {
                            if (random.nextBoolean()) {
                                this.setBlockAndMetadata(world, i13, 7, k1, Blocks.stone_brick_stairs, random.nextInt(4));
                                continue;
                            }
                            this.setBlockAndMetadata(world, i13, 7, k1, (Block)Blocks.stone_slab, 0);
                            continue;
                        }
                        this.placeRandomStoneBrick(world, random, i13, 7, k1);
                    }
                }
                for (i13 = -4; i13 <= 4; ++i13) {
                    this.setBlockAndMetadata(world, i13, 2, -4, Blocks.stone_brick_stairs, 2);
                    this.setBlockAndMetadata(world, i13, 2, 4, Blocks.stone_brick_stairs, 3);
                    this.setBlockAndMetadata(world, i13, 6, -4, Blocks.stone_brick_stairs, 6);
                    this.setBlockAndMetadata(world, i13, 6, 4, Blocks.stone_brick_stairs, 7);
                }
                for (k13 = -3; k13 <= 3; ++k13) {
                    this.setBlockAndMetadata(world, -4, 2, k13, Blocks.stone_brick_stairs, 1);
                    this.setBlockAndMetadata(world, 4, 2, k13, Blocks.stone_brick_stairs, 0);
                    this.setBlockAndMetadata(world, -4, 6, k13, Blocks.stone_brick_stairs, 5);
                    this.setBlockAndMetadata(world, 4, 6, k13, Blocks.stone_brick_stairs, 4);
                }
                if (random.nextInt(3) == 0) {
                    this.setBlockAndMetadata(world, 0, 1, 2, this.plankBlock, this.plankMeta);
                    this.placeChest(world, random, 0, 2, 2, 2, LOTRChestContents.RUINED_HOUSE);
                }
                if (random.nextInt(3) != 0) break block218;
                this.placeRandomStoneBrick(world, random, 0, 6, 3);
                this.placeChest(world, random, 0, 7, 3, 2, LOTRChestContents.RUINED_HOUSE);
                break block218;
            }
            if (ruinType == RuinType.WALLS) {
                int length = 3 + random.nextInt(7);
                int width2 = 2 + random.nextInt(3);
                height = 2 + random.nextInt(6);
                int gravelChance = 2 + random.nextInt(7);
                for (i122 = -width2; i122 <= width2; ++i122) {
                    block66: for (int k18 = -length; k18 <= length; ++k18) {
                        int h;
                        int i26 = Math.abs(i122);
                        int k28 = Math.abs(k18);
                        if (!this.isSuitableSpawnBlock(world, i122, k18)) continue;
                        int j1 = this.getTopBlock(world, i122, k18) - 1;
                        if (i26 == width2) {
                            h = height - random.nextInt(3);
                            if (random.nextInt(8) == 0) {
                                h = random.nextInt(3);
                            }
                            float factor = (float)k28 / (float)length;
                            factor = 1.0f / (factor + 0.01f);
                            factor *= 0.5f + random.nextFloat() * 0.5f;
                            factor = Math.min(factor, 1.0f);
                            h = (int)((float)h * factor);
                            int top = j1 + h;
                            for (int j2 = j1 + 1; j2 <= top; ++j2) {
                                if (random.nextInt(8) == 0) {
                                    this.setBlockAndMetadata(world, i122, j2, k18, Blocks.stone_brick_stairs, random.nextInt(8));
                                } else if (random.nextInt(8) == 0) {
                                    this.setBlockAndMetadata(world, i122, j2, k18, Blocks.cobblestone, 0);
                                } else {
                                    this.placeRandomStoneBrick(world, random, i122, j2, k18);
                                }
                                this.setGrassToDirt(world, i122, j2 - 1, k18);
                            }
                            if (k28 >= length / 2 || h < 3 || h < height - 3 || !random.nextBoolean()) continue;
                            int h1 = top - random.nextInt(2);
                            int w = random.nextInt(width2 * 2);
                            for (int w1 = 1; w1 <= w; ++w1) {
                                if (i122 < 0) {
                                    this.setBlockAndMetadata(world, i122 + w1, h1, k18, (Block)Blocks.stone_slab, 8);
                                } else {
                                    this.setBlockAndMetadata(world, i122 - w1, h1, k18, (Block)Blocks.stone_slab, 8);
                                }
                                if (random.nextInt(4) == 0) continue block66;
                            }
                            continue;
                        }
                        if (random.nextInt(5) != 0) {
                            if (random.nextInt(4) == 0) {
                                this.setBlockAndMetadata(world, i122, j1, k18, Blocks.mossy_cobblestone, 0);
                            } else {
                                this.setBlockAndMetadata(world, i122, j1, k18, Blocks.cobblestone, 0);
                            }
                        }
                        if ((i26 != width2 - 1 || random.nextInt(Math.max(2, gravelChance / 2)) != 0) && random.nextInt(gravelChance) != 0) continue;
                        h = 1;
                        if (random.nextInt(4) == 0) {
                            ++h;
                        }
                        this.setGrassToDirt(world, i122, j1, k18);
                        for (int j2 = j1 + 1; j2 <= j1 + h; ++j2) {
                            this.setBlockAndMetadata(world, i122, j2, k18, Blocks.gravel, 0);
                        }
                    }
                }
            } else if (ruinType == RuinType.SHRINE) {
                for (i1 = -4; i1 <= 4; ++i1) {
                    for (k12 = -4; k12 <= 4; ++k12) {
                        i22 = Math.abs(i1);
                        k2 = Math.abs(k12);
                        this.layFoundationRandomStoneBrick(world, random, i1, 0, k12);
                        if (i22 <= 3 && k2 <= 3) {
                            if (i22 <= 1 && k2 <= 1) {
                                this.setBlockAndMetadata(world, i1, 1, k12, (Block)Blocks.double_stone_slab, 0);
                                this.setBlockAndMetadata(world, i1, 2, k12, (Block)Blocks.stone_slab, 0);
                            } else if (random.nextInt(16) == 0) {
                                this.setBlockAndMetadata(world, i1, 1, k12, Blocks.gravel, 0);
                            } else if (random.nextInt(16) == 0) {
                                this.setBlockAndMetadata(world, i1, 1, k12, Blocks.cobblestone, 0);
                            } else if (random.nextInt(16) == 0) {
                                this.setBiomeTop(world, i1, 1, k12);
                            } else if (i22 <= 2 && k2 <= 2) {
                                this.setBlockAndMetadata(world, i1, 1, k12, (Block)Blocks.double_stone_slab, 0);
                            } else {
                                this.placeRandomStoneBrick(world, random, i1, 1, k12);
                            }
                        }
                        if (random.nextInt(5) != 0) {
                            if (i1 == -4) {
                                this.setBlockAndMetadata(world, i1, 1, k12, Blocks.stone_brick_stairs, 1);
                            } else if (i1 == 4) {
                                this.setBlockAndMetadata(world, i1, 1, k12, Blocks.stone_brick_stairs, 0);
                            } else if (k12 == -4) {
                                this.setBlockAndMetadata(world, i1, 1, k12, Blocks.stone_brick_stairs, 2);
                            } else if (k12 == 4) {
                                this.setBlockAndMetadata(world, i1, 1, k12, Blocks.stone_brick_stairs, 3);
                            }
                        }
                        if (i22 == 3 && k2 == 3) {
                            int h = 2 + random.nextInt(4);
                            int top = 1 + h;
                            for (int j1 = 2; j1 <= top; ++j1) {
                                this.placeRandomStoneBrick(world, random, i1, j1, k12);
                                this.setGrassToDirt(world, i1, j1 - 1, k12);
                            }
                            if (h >= 4) {
                                this.setBlockAndMetadata(world, i1 - 1, 1 + h, k12 - 1, Blocks.stone_brick_stairs, 6);
                                this.setBlockAndMetadata(world, i1, 1 + h, k12 - 1, Blocks.stone_brick_stairs, 6);
                                this.setBlockAndMetadata(world, i1 + 1, 1 + h, k12 - 1, Blocks.stone_brick_stairs, 6);
                                this.setBlockAndMetadata(world, i1 + 1, 1 + h, k12, Blocks.stone_brick_stairs, 4);
                                this.setBlockAndMetadata(world, i1 + 1, 1 + h, k12 + 1, Blocks.stone_brick_stairs, 7);
                                this.setBlockAndMetadata(world, i1, 1 + h, k12 + 1, Blocks.stone_brick_stairs, 7);
                                this.setBlockAndMetadata(world, i1 - 1, 1 + h, k12 + 1, Blocks.stone_brick_stairs, 7);
                                this.setBlockAndMetadata(world, i1 - 1, 1 + h, k12, Blocks.stone_brick_stairs, 5);
                            }
                        }
                        if ((i22 != 3 || k2 > 1) && (k2 != 3 || i22 > 1) || random.nextInt(4) == 0) continue;
                        this.setBlockAndMetadata(world, i1, 2, k12, (Block)Blocks.stone_slab, 0);
                        this.setGrassToDirt(world, i1, 1, k12);
                    }
                }
                this.setBlockAndMetadata(world, 0, 2, 0, Blocks.stonebrick, 3);
                this.placeChest(world, random, 0, 1, 0, LOTRMod.chestStone, 2, LOTRChestContents.RUINED_HOUSE);
            } else if (ruinType == RuinType.BRICK_HOUSE) {
                width = MathHelper.getRandomIntegerInRange((Random)random, (int)3, (int)5);
                int height6 = MathHelper.getRandomIntegerInRange((Random)random, (int)1, (int)4);
                for (i13 = -width; i13 <= width; ++i13) {
                    block74: for (k1 = -width; k1 <= width; ++k1) {
                        int randomWall;
                        i2 = Math.abs(i13);
                        int k29 = Math.abs(k1);
                        this.layFoundation(world, i13, 0, k1, Blocks.cobblestone, 0);
                        int randomFloor = random.nextInt(5);
                        if (randomFloor == 0) {
                            this.setBlockAndMetadata(world, i13, 0, k1, Blocks.cobblestone, 0);
                        } else if (randomFloor == 1) {
                            this.setBlockAndMetadata(world, i13, 0, k1, Blocks.mossy_cobblestone, 0);
                        } else if (randomFloor == 2) {
                            this.setBlockAndMetadata(world, i13, 0, k1, Blocks.gravel, 0);
                        } else if (randomFloor == 3) {
                            this.setBlockAndMetadata(world, i13, 0, k1, Blocks.dirt, 1);
                        } else if (randomFloor == 4) {
                            this.setBlockAndMetadata(world, i13, 0, k1, Blocks.brick_block, 0);
                        }
                        if (i2 == width || k29 == width) {
                            if (random.nextInt(10) == 0) continue;
                            for (int j1 = 1; j1 <= height6; ++j1) {
                                if (random.nextInt(6) != 0) {
                                    if (random.nextInt(3) == 0) {
                                        if (random.nextBoolean()) {
                                            this.setBlockAndMetadata(world, i13, j1, k1, LOTRMod.redBrick, 0);
                                        } else {
                                            this.setBlockAndMetadata(world, i13, j1, k1, LOTRMod.redBrick, 1);
                                        }
                                    } else {
                                        this.setBlockAndMetadata(world, i13, j1, k1, Blocks.brick_block, 0);
                                    }
                                } else {
                                    int stairDir;
                                    int randomWall2 = random.nextInt(7);
                                    if (randomWall2 == 0) {
                                        this.setBlockAndMetadata(world, i13, j1, k1, (Block)Blocks.double_stone_slab, 0);
                                    } else if (randomWall2 == 1) {
                                        this.setBlockAndMetadata(world, i13, j1, k1, LOTRMod.pillar2, 3);
                                    } else if (randomWall2 == 2) {
                                        this.setBlockAndMetadata(world, i13, j1, k1, LOTRMod.clayTile, 0);
                                    } else if (randomWall2 == 3) {
                                        stairDir = random.nextInt(8);
                                        this.setBlockAndMetadata(world, i13, j1, k1, Blocks.brick_stairs, stairDir);
                                    } else if (randomWall2 == 4) {
                                        stairDir = random.nextInt(8);
                                        this.setBlockAndMetadata(world, i13, j1, k1, LOTRMod.stairsClayTile, stairDir);
                                    } else if (randomWall2 == 5) {
                                        this.setBlockAndMetadata(world, i13, j1, k1, Blocks.cobblestone, 0);
                                    } else if (randomWall2 == 6) {
                                        this.setBlockAndMetadata(world, i13, j1, k1, LOTRMod.wallStoneV, 6);
                                    }
                                }
                                if (random.nextInt(6) == 0) continue block74;
                            }
                            continue;
                        }
                        if (i2 == width - 1 || k29 == width - 1) {
                            if (random.nextInt(3) != 0) continue;
                            randomWall = random.nextInt(4);
                            if (randomWall == 0) {
                                this.setBlockAndMetadata(world, i13, 1, k1, Blocks.brick_block, 0);
                                continue;
                            }
                            if (randomWall == 1) {
                                int stairDir = random.nextInt(8);
                                this.setBlockAndMetadata(world, i13, 1, k1, Blocks.brick_stairs, stairDir);
                                continue;
                            }
                            if (randomWall == 2) {
                                this.setBlockAndMetadata(world, i13, 1, k1, LOTRMod.planksRotten, 0);
                                continue;
                            }
                            if (randomWall != 3) continue;
                            this.setBlockAndMetadata(world, i13, 1, k1, LOTRMod.fenceRotten, 0);
                            continue;
                        }
                        if (random.nextInt(8) != 0) continue;
                        randomWall = random.nextInt(2);
                        if (randomWall == 0) {
                            this.setBlockAndMetadata(world, i13, 1, k1, LOTRMod.rottenSlabSingle, 0);
                            continue;
                        }
                        if (randomWall != 1) continue;
                        this.setBlockAndMetadata(world, i13, 1, k1, (Block)Blocks.stone_slab, 4);
                    }
                }
            }
        }
        return true;
    }

    private boolean isSuitableSpawnBlock(World world, int i, int k) {
        int j = this.getTopBlock(world, i, k);
        if (!this.isSurface(world, i, j - 1, k)) {
            return false;
        }
        Block above = this.getBlock(world, i, j, k);
        return !above.getMaterial().isLiquid();
    }

    private void layFoundation(World world, int i, int j, int k, Block block, int meta) {
        for (int j1 = j; !(j1 < 0 && this.isOpaque(world, i, j1, k) || this.getY(j1) < 0); --j1) {
            this.setBlockAndMetadata(world, i, j1, k, block, meta);
            this.setGrassToDirt(world, i, j1 - 1, k);
        }
    }

    private void layFoundationRandomStoneBrick(World world, Random random, int i, int j, int k) {
        for (int j1 = j; !(j1 < 0 && this.isOpaque(world, i, j1, k) || this.getY(j1) < 0); --j1) {
            this.placeRandomStoneBrick(world, random, i, j1, k);
            this.setGrassToDirt(world, i, j1 - 1, k);
        }
    }

    private void placeRandomStoneBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 1);
            } else {
                this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 2);
            }
        } else {
            this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 0);
        }
    }

    private static enum RuinType {
        COLUMN(0, 1),
        ROOM(3, 2),
        BAR_TOWER(3, 2),
        PIT_MINE(0, 2),
        PLINTH(0, 3),
        RUBBLE(0, 0),
        QUARRY(0, 7),
        OBELISK(0, 5),
        WELL(0, 2),
        TURRET(5, 4),
        WALLS(0, 3),
        SHRINE(0, 4),
        BRICK_HOUSE(0, 5);

        private int centreShift;
        private int checkRadius;

        private RuinType(int i, int j) {
            this.centreShift = i;
            this.checkRadius = j;
        }

        public static RuinType getRandomType(Random random) {
            return RuinType.values()[random.nextInt(RuinType.values().length)];
        }
    }

}

