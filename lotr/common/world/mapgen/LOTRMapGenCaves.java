/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.MapGenBase
 */
package lotr.common.world.mapgen;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lotr.common.LOTRMod;
import lotr.common.world.LOTRChunkProvider;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;

public class LOTRMapGenCaves
extends MapGenBase {
    public LOTRChunkProvider.ChunkFlags chunkFlags;
    private int numThreads;

    public void LOTRMapGenCavesMultiThreaded(int numThreads) {
        this.numThreads = numThreads;
    }

    public void generate(World world, int chunkX, int chunkZ, Block[] blockArray) {
        ExecutorService executor = Executors.newFixedThreadPool(this.numThreads);
        int i = 0;
        while (i < this.numThreads) {
            int threadIndex = i++;
            executor.execute(() -> {
                int startX = chunkX + threadIndex;
                int startZ = chunkZ + threadIndex;
                this.generateChunk(world, startX, startZ, blockArray);
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
    }

    private void generateChunk(World world, int chunkX, int chunkZ, Block[] blockArray) {
        int startX = chunkX * 16;
        int startZ = chunkZ * 16;
        int endX = startX + 15;
        int endZ = startZ + 15;
        for (int x = startX; x <= endX; ++x) {
            for (int z = startZ; z <= endZ; ++z) {
                this.generateChunk(world, x, z, blockArray);
            }
        }
    }

    protected void generateLargeCaveNode(long seed, int par3, int par4, Block[] blockArray, double par6, double par8, double par10, boolean cutSurface) {
        this.generateCaveNode(seed, par3, par4, blockArray, par6, par8, par10, 1.0f + this.rand.nextFloat() * 6.0f, 0.0f, 0.0f, -1, -1, 0.5, cutSurface);
    }

    protected void generateCaveNode(long seed, int par3, int par4, Block[] blockArray, double par6, double par8, double par10, float par12, float angle, float par14, int par15, int par16, double par17, boolean cutSurface) {
        boolean var28;
        double var19 = par3 * 16 + 8;
        double var21 = par4 * 16 + 8;
        float var23 = 0.0f;
        float var24 = 0.0f;
        Random caveRand = new Random(seed);
        if (par16 <= 0) {
            int var26 = this.range * 16 - 16;
            par16 = var26 - caveRand.nextInt(var26 / 4);
        }
        boolean var54 = false;
        if (par15 == -1) {
            par15 = par16 / 2;
            var54 = true;
        }
        int var27 = caveRand.nextInt(par16 / 2) + par16 / 4;
        boolean bl = var28 = caveRand.nextInt(6) == 0;
        while (par15 < par16) {
            double var29 = 1.5 + (double)(MathHelper.sin((float)((float)par15 * 3.1415927f / (float)par16)) * par12 * 1.0f);
            double var31 = var29 * par17;
            float var33 = MathHelper.cos((float)par14);
            float var34 = MathHelper.sin((float)par14);
            par6 += (double)(MathHelper.cos((float)angle) * var33);
            par8 += (double)var34;
            par10 += (double)(MathHelper.sin((float)angle) * var33);
            par14 = var28 ? (par14 = par14 * 0.92f) : (par14 = par14 * 0.7f);
            par14 += var24 * 0.1f;
            angle += var23 * 0.1f;
            var24 *= 0.9f;
            var23 *= 0.75f;
            var24 += (caveRand.nextFloat() - caveRand.nextFloat()) * caveRand.nextFloat() * 2.0f;
            var23 += (caveRand.nextFloat() - caveRand.nextFloat()) * caveRand.nextFloat() * 4.0f;
            if (!var54 && par15 == var27 && par12 > 1.0f && par16 > 0) {
                this.generateCaveNode(caveRand.nextLong(), par3, par4, blockArray, par6, par8, par10, caveRand.nextFloat() * 0.5f + 0.5f, angle - 1.5707964f, par14 / 3.0f, par15, par16, 1.0, cutSurface);
                this.generateCaveNode(caveRand.nextLong(), par3, par4, blockArray, par6, par8, par10, caveRand.nextFloat() * 0.5f + 0.5f, angle + 1.5707964f, par14 / 3.0f, par15, par16, 1.0, cutSurface);
                return;
            }
            if (var54 || caveRand.nextInt(4) != 0) {
                double var35 = par6 - var19;
                double var37 = par10 - var21;
                double var39 = par16 - par15;
                double var41 = par12 + 2.0f + 16.0f;
                if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
                    return;
                }
                if (par6 >= var19 - 16.0 - var29 * 2.0 && par10 >= var21 - 16.0 - var29 * 2.0 && par6 <= var19 + 16.0 + var29 * 2.0 && par10 <= var21 + 16.0 + var29 * 2.0) {
                    int var45;
                    int var42;
                    int var55 = MathHelper.floor_double((double)(par6 - var29)) - par3 * 16 - 1;
                    int var36 = MathHelper.floor_double((double)(par6 + var29)) - par3 * 16 + 1;
                    int var57 = MathHelper.floor_double((double)(par8 - var31)) - 1;
                    int var38 = MathHelper.floor_double((double)(par8 + var31)) + 1;
                    int var56 = MathHelper.floor_double((double)(par10 - var29)) - par4 * 16 - 1;
                    int var40 = MathHelper.floor_double((double)(par10 + var29)) - par4 * 16 + 1;
                    var55 = Math.max(var55, 0);
                    var36 = Math.min(var36, 16);
                    var57 = Math.max(var57, 1);
                    var38 = Math.min(var38, 248);
                    var56 = Math.max(var56, 0);
                    var40 = Math.min(var40, 16);
                    boolean anyWater = false;
                    for (var42 = var55; !anyWater && var42 < var36; ++var42) {
                        for (int var43 = var56; !anyWater && var43 < var40; ++var43) {
                            for (int var44 = var38 + 1; !anyWater && var44 >= var57 - 1; --var44) {
                                var45 = (var42 * 16 + var43) * 256 + var44;
                                if (var44 < 0 || var44 >= 256) continue;
                                if (blockArray[var45] == Blocks.flowing_water || blockArray[var45] == Blocks.water) {
                                    anyWater = true;
                                }
                                if (var44 == var57 - 1 || var42 == var55 || var42 == var36 - 1 || var43 == var56 || var43 == var40 - 1) continue;
                                var44 = var57;
                            }
                        }
                    }
                    if (!anyWater) {
                        for (var42 = var55; var42 < var36; ++var42) {
                            double var59 = ((double)(var42 + par3 * 16) + 0.5 - par6) / var29;
                            for (var45 = var56; var45 < var40; ++var45) {
                                double var46 = ((double)(var45 + par4 * 16) + 0.5 - par10) / var29;
                                int xzIndex = var42 * 16 + var45;
                                int blockIndex = xzIndex * 256 + (var57 + 1);
                                if (var59 * var59 + var46 * var46 >= 1.0) continue;
                                for (int var50 = var57; var50 <= var38 - 1; ++var50) {
                                    double var51 = ((double)var50 + 0.5 - par8) / var31;
                                    if (var51 > -0.7 && var59 * var59 + var51 * var51 + var46 * var46 < 1.0) {
                                        LOTRBiome biome = (LOTRBiome)this.worldObj.getBiomeGenForCoords(var42 + par3 * 16, var45 + par4 * 16);
                                        this.digBlock(blockArray, blockIndex, xzIndex, var42, var50, var45, par3, par4, biome, cutSurface);
                                    }
                                    ++blockIndex;
                                }
                            }
                        }
                        if (var54) break;
                    }
                }
            }
            ++par15;
        }
    }

    protected void digBlock(Block[] blockArray, int index, int xzIndex, int i, int j, int k, int chunkX, int chunkZ, LOTRBiome biome, boolean cutSurface) {
        boolean dig;
        int checkAboveMax;
        int j1;
        Block block = blockArray[index];
        boolean isTop = false;
        boolean belowVillageOrRoad = false;
        int topCheckDepth = 1;
        if (j >= 59 - topCheckDepth) {
            isTop = true;
            checkAboveMax = 5;
            for (int j12 = topCheckDepth + 1; j12 <= topCheckDepth + checkAboveMax && j + j12 <= 255; ++j12) {
                if (!blockArray[index + j12].isOpaqueCube()) continue;
                isTop = false;
                break;
            }
        }
        if (this.chunkFlags.isVillage || this.chunkFlags.roadFlags[xzIndex]) {
            int roadDepth = 4;
            if (j >= 59 - 4) {
                belowVillageOrRoad = true;
                checkAboveMax = 5;
                for (j1 = roadDepth + 1; j1 <= roadDepth + checkAboveMax && j + j1 <= 255; ++j1) {
                    if (!blockArray[index + j1].isOpaqueCube()) continue;
                    belowVillageOrRoad = false;
                    break;
                }
            }
        }
        boolean bl = dig = LOTRMapGenCaves.isTerrainBlock(block, biome) || block.getMaterial().isLiquid();
        if (belowVillageOrRoad) {
            dig = false;
        }
        if (isTop && (!cutSurface || this.chunkFlags.isVillage)) {
            dig = false;
        }
        if (dig) {
            if (j < 10) {
                blockArray[index] = Blocks.lava;
            } else {
                blockArray[index] = Blocks.air;
                if (isTop) {
                    int grassCheckMax = 5;
                    for (j1 = 1; j1 <= grassCheckMax && j - j1 > 0; ++j1) {
                        if (blockArray[index - j1] != biome.fillerBlock) continue;
                        blockArray[index - j1] = biome.topBlock;
                        break;
                    }
                }
            }
        }
    }

    protected int caveRarity() {
        return 10;
    }

    protected int getCaveGenerationHeight() {
        return this.rand.nextInt(this.rand.nextInt(120) + 8);
    }

    protected void func_151538_a(World world, int i, int k, int chunkX, int chunkZ, Block[] blocks) {
        int caves = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);
        if (this.rand.nextInt(this.caveRarity()) != 0) {
            caves = 0;
        }
        for (int l = 0; l < caves; ++l) {
            int i1 = i * 16 + this.rand.nextInt(16);
            int j1 = this.getCaveGenerationHeight();
            int k1 = k * 16 + this.rand.nextInt(16);
            boolean cutSurface = this.rand.nextInt(5) == 0;
            int nodes = 1;
            if (this.rand.nextInt(4) == 0) {
                this.generateLargeCaveNode(this.rand.nextLong(), chunkX, chunkZ, blocks, i1, j1, k1, cutSurface);
                nodes += this.rand.nextInt(4);
            }
            for (int n = 0; n < nodes; ++n) {
                float angle = this.rand.nextFloat() * 3.1415927f * 2.0f;
                float var18 = (this.rand.nextFloat() - 0.5f) * 2.0f / 8.0f;
                float size = this.rand.nextFloat() * 2.0f + this.rand.nextFloat();
                if (this.rand.nextInt(10) == 0) {
                    size *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0f + 1.0f;
                }
                this.generateCaveNode(this.rand.nextLong(), chunkX, chunkZ, blocks, i1, j1, k1, size, angle, var18, 0, 0, 1.0, cutSurface);
            }
        }
    }

    public static boolean isTerrainBlock(Block block, BiomeGenBase biome) {
        if (block == biome.topBlock || block == biome.fillerBlock) {
            return true;
        }
        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand || block == LOTRMod.whiteSand || block == Blocks.gravel || block == LOTRMod.mudGrass || block == LOTRMod.mud) {
            return true;
        }
        if (block == LOTRMod.dirtPath) {
            return true;
        }
        if (block == Blocks.stone || block == LOTRMod.rock || block == Blocks.sandstone || block == LOTRMod.redSandstone || block == LOTRMod.whiteSandstone) {
            return true;
        }
        return block == LOTRMod.mordorDirt || block == LOTRMod.mordorGravel;
    }
}

