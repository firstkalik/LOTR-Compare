/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$FlowerEntry
 */
package lotr.common.world.map;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRRoads;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRRoadGenerator {
    public static final int ROAD_DEPTH = 4;

    public static boolean generateRoad(World world, Random rand, int i, int k, LOTRBiome biome, Block[] blocks, byte[] metadata, double[] heightNoise) {
        int chunkX = i & 0xF;
        int chunkZ = k & 0xF;
        int xzIndex = chunkX * 16 + chunkZ;
        int ySize = blocks.length / 256;
        LOTRRoadType roadType = biome.getRoadBlock();
        LOTRRoadType.BridgeType bridgeType = biome.getBridgeBlock();
        if (LOTRRoads.isRoadAt(i, k)) {
            int j;
            int index;
            int indexLower;
            int roadTop = 0;
            int bridgeBase = 0;
            boolean bridge = false;
            boolean bridgeSlab = false;
            for (j = ySize - 1; j > 0; --j) {
                index = xzIndex * ySize + j;
                Block block = blocks[index];
                if (block.isOpaqueCube()) {
                    roadTop = j;
                    bridge = false;
                    break;
                }
                if (!block.getMaterial().isLiquid()) continue;
                bridgeBase = roadTop = j + 1;
                int maxBridgeTop = j + 6;
                float bridgeHeight = 0.0f;
                for (int j1 = j - 1; j1 > 0 && blocks[indexLower = xzIndex * ySize + j1].getMaterial().isLiquid(); --j1) {
                    bridgeHeight += 0.5f;
                }
                int bridgeHeightInt = (int)Math.floor(bridgeHeight);
                roadTop += bridgeHeightInt;
                if ((roadTop = Math.min(roadTop, maxBridgeTop)) >= maxBridgeTop) {
                    bridgeSlab = true;
                } else {
                    float bridgeHeightR = bridgeHeight - (float)bridgeHeightInt;
                    if (bridgeHeightR < 0.5f) {
                        bridgeSlab = true;
                    }
                }
                bridge = true;
                break;
            }
            if (bridge) {
                LOTRRoadType.RoadBlock bridgeBlock = bridgeType.getBlock(rand, false);
                LOTRRoadType.RoadBlock bridgeBlockSlab = bridgeType.getBlock(rand, true);
                LOTRRoadType.RoadBlock bridgeEdge = bridgeType.getEdge(rand);
                LOTRRoadType.RoadBlock bridgeFence = bridgeType.getFence(rand);
                boolean fence = LOTRRoadGenerator.isFenceAt(i, k);
                int index2 = xzIndex * ySize + roadTop;
                if (fence) {
                    boolean pillar = LOTRRoadGenerator.isPillarAt(i, k);
                    if (pillar) {
                        int pillarIndex;
                        for (int j2 = roadTop + 4; j2 > 0 && !blocks[pillarIndex = xzIndex * ySize + j2].isOpaqueCube(); --j2) {
                            if (j2 >= roadTop + 4) {
                                blocks[pillarIndex] = bridgeFence.block;
                                metadata[pillarIndex] = (byte)bridgeFence.meta;
                                continue;
                            }
                            if (j2 >= roadTop + 3) {
                                blocks[pillarIndex] = bridgeBlock.block;
                                metadata[pillarIndex] = (byte)bridgeBlock.meta;
                                continue;
                            }
                            blocks[pillarIndex] = bridgeEdge.block;
                            metadata[pillarIndex] = (byte)bridgeEdge.meta;
                        }
                    } else {
                        int support;
                        blocks[index2] = bridgeEdge.block;
                        metadata[index2] = (byte)bridgeEdge.meta;
                        int indexUpper = index2 + 1;
                        blocks[indexUpper] = bridgeFence.block;
                        metadata[indexUpper] = (byte)bridgeFence.meta;
                        if (roadTop > bridgeBase) {
                            int indexLower2 = index2 - 1;
                            blocks[indexLower2] = bridgeEdge.block;
                            metadata[indexLower2] = (byte)bridgeEdge.meta;
                        }
                        if (roadTop - 1 > (support = bridgeBase + 2)) {
                            int indexSupport = xzIndex * ySize + support;
                            blocks[indexSupport] = bridgeFence.block;
                            metadata[indexSupport] = (byte)bridgeFence.meta;
                        }
                    }
                } else {
                    if (bridgeSlab) {
                        blocks[index2] = bridgeBlockSlab.block;
                        metadata[index2] = (byte)bridgeBlockSlab.meta;
                    } else {
                        blocks[index2] = bridgeBlock.block;
                        metadata[index2] = (byte)bridgeBlock.meta;
                    }
                    if (roadTop > bridgeBase) {
                        indexLower = index2 - 1;
                        blocks[indexLower] = bridgeBlock.block;
                        metadata[indexLower] = (byte)bridgeBlock.meta;
                    }
                }
            } else {
                for (j = roadTop; j > roadTop - 4 && j > 0; --j) {
                    index = xzIndex * ySize + j;
                    float repair = roadType.getRepair();
                    if (rand.nextFloat() >= repair) continue;
                    boolean isTop = j == roadTop;
                    boolean isSlab = false;
                    if (isTop && j >= 63) {
                        double avgNoise = (heightNoise[index] + heightNoise[index + 1]) / 2.0;
                        isSlab = avgNoise < 0.0;
                    }
                    LOTRRoadType.RoadBlock roadblock = roadType.getBlock(rand, biome, isTop, isSlab);
                    blocks[index] = roadblock.block;
                    metadata[index] = (byte)roadblock.meta;
                }
            }
            return true;
        }
        if (roadType.hasFlowers()) {
            int i1;
            int index;
            int roadTop = 0;
            for (int j = ySize - 1; j > 0; --j) {
                index = xzIndex * ySize + j;
                Block block = blocks[index];
                if (!block.isOpaqueCube()) continue;
                roadTop = j;
                break;
            }
            boolean adjRoad = false;
            block5: for (i1 = -2; i1 <= 2; ++i1) {
                for (int k1 = -2; k1 <= 2; ++k1) {
                    if (i1 == 0 && k1 == 0 || !LOTRRoads.isRoadAt(i + i1, k + k1)) continue;
                    adjRoad = true;
                    break block5;
                }
            }
            if (adjRoad) {
                index = xzIndex * ySize + roadTop + 1;
                BiomeGenBase.FlowerEntry flower = biome.getRandomFlower(world, rand, i, roadTop, k);
                blocks[index] = flower.block;
                metadata[index] = (byte)flower.metadata;
            } else {
                adjRoad = false;
                block7: for (i1 = -3; i1 <= 3; ++i1) {
                    for (int k1 = -3; k1 <= 3; ++k1) {
                        if (Math.abs(i1) <= 2 && Math.abs(k1) <= 2 || !LOTRRoads.isRoadAt(i + i1, k + k1)) continue;
                        adjRoad = true;
                        break block7;
                    }
                }
                if (adjRoad) {
                    index = xzIndex * ySize + roadTop + 1;
                    blocks[index] = Blocks.leaves;
                    metadata[index] = 4;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isFenceAt(int i, int k) {
        for (int i1 = -1; i1 <= 1; ++i1) {
            for (int k1 = -1; k1 <= 1; ++k1) {
                if (i1 == 0 && k1 == 0 || LOTRRoads.isRoadAt(i + i1, k + k1)) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean isPillarAt(int i, int k) {
        int pRange = 8;
        int xmod = IntMath.mod((int)i, (int)pRange);
        if (IntMath.mod((int)(xmod + IntMath.mod((int)k, (int)pRange)), (int)pRange) == 0) {
            return !LOTRRoadGenerator.isBridgeEdgePillar(i + 1, k - 1) && !LOTRRoadGenerator.isBridgeEdgePillar(i + 1, k + 1);
        }
        return false;
    }

    private static boolean isBridgeEdgePillar(int i, int k) {
        return LOTRRoads.isRoadAt(i, k) && LOTRRoadGenerator.isFenceAt(i, k) && LOTRRoadGenerator.isPillarAt(i, k);
    }
}

