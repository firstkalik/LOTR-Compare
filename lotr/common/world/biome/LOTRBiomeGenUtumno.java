/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.LOTRMod;
import lotr.common.world.LOTRUtumnoLevel;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRWorldGenSkullPile;
import lotr.common.world.feature.LOTRWorldGenStalactites;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenUtumno
extends LOTRBiome {
    private static List<LOTRBiome> utumnoBiomes = new ArrayList<LOTRBiome>();
    private LOTRWorldGenStalactites stalactiteGen = new LOTRWorldGenStalactites(LOTRMod.stalactite);
    private LOTRWorldGenStalactites stalactiteIceGen = new LOTRWorldGenStalactites(LOTRMod.stalactiteIce);
    private LOTRWorldGenStalactites stalactiteObsidianGen = new LOTRWorldGenStalactites(LOTRMod.stalactiteObsidian);
    private LOTRBiomeSpawnList spawnableGuestList = new LOTRBiomeSpawnList(this);

    public LOTRBiomeGenUtumno(int i) {
        super(i, false, LOTRDimension.UTUMNO);
        utumnoBiomes.add(this);
        this.setDisableRain();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.spawnableCaveCreatureList.clear();
        this.npcSpawnList.clear();
        this.biomeColors.setGrass(0);
        this.biomeColors.setFoliage(0);
        this.biomeColors.setSky(0);
        this.biomeColors.setFoggy(true);
        this.biomeColors.setWater(0);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.UTUMNO_GUESTS, 10)};
        this.spawnableGuestList.newFactionList(100).add(arrspawnListContainer);
    }

    @Override
    public LOTRBiomeSpawnList getNPCSpawnList(World world, Random random, int i, int j, int k, LOTRBiomeVariant variant) {
        if (random.nextInt(1000) == 0) {
            return this.spawnableGuestList;
        }
        return LOTRUtumnoLevel.forY(j).getNPCSpawnList();
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.UTUMNO.getSubregion("utumno");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        this.generateHoles(world, random, i, k);
        this.generatePits(world, random, i, k);
        this.generateBridges(world, random, i, k);
        this.generateStairs(world, random, i, k);
        this.generatePillars(world, random, i, k);
        this.generatePortalBases(world, random, i, k);
        this.generateBars(world, random, i, k);
        this.generateStalactites(world, random, i, k);
        this.generateSkulls(world, random, i, k);
    }

    private void generateHoles(World world, Random random, int i, int k) {
        for (int l = 0; l < 8; ++l) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            int j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)20, (int)240);
            if (!world.isAirBlock(i1, j1, k1)) continue;
            LOTRUtumnoLevel level = LOTRUtumnoLevel.forY(j1);
            for (int j2 = j1; !(j2 < level.corridorBaseLevels[0] - 1 || world.isAirBlock(i1, j2, k1) && random.nextInt(10) == 0 || LOTRUtumnoLevel.forY(j2 - 1) != level); --j2) {
                for (int i2 = i1 - 1; i2 <= i1; ++i2) {
                    for (int k2 = k1 - 1; k2 <= k1; ++k2) {
                        world.setBlockToAir(i2, j2, k2);
                    }
                }
            }
        }
    }

    private void generatePits(World world, Random random, int i, int k) {
        if (random.nextInt(5) == 0) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            int j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)20, (int)220);
            if (world.isAirBlock(i1, j1, k1)) {
                int radius = 8 + random.nextInt(30);
                LOTRUtumnoLevel level = LOTRUtumnoLevel.forY(j1);
                int yMin = Math.max(j1 - radius, level.baseLevel + 5);
                int yMax = Math.min(j1 + radius, level.topLevel - 5);
                for (int i2 = i1 - radius; i2 <= i1 + radius; ++i2) {
                    for (int j2 = yMin; j2 <= yMax; ++j2) {
                        for (int k2 = k1 - radius; k2 <= k1 + radius; ++k2) {
                            int k3;
                            int j3;
                            int i3 = Math.abs(i2 - i1);
                            double dist = i3 * i3 + (j3 = Math.abs(j2 - j1)) * j3 + (k3 = Math.abs(k2 - k1)) * k3;
                            if (dist < (double)((radius - 5) * (radius - 5))) {
                                world.setBlockToAir(i2, j2, k2);
                                continue;
                            }
                            if (dist >= (double)(radius * radius) || random.nextInt(6) != 0) continue;
                            world.setBlockToAir(i2, j2, k2);
                        }
                    }
                }
            }
        }
    }

    private void generateBridges(World world, Random random, int i, int k) {
        block0: for (int l = 0; l < 20; ++l) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            LOTRUtumnoLevel utumnoLevel = LOTRUtumnoLevel.values()[random.nextInt(LOTRUtumnoLevel.values().length)];
            int j1 = utumnoLevel.corridorBaseLevels[random.nextInt(utumnoLevel.corridorBaseLevels.length)] - 1;
            int fuzz = 2;
            for (int j2 = j1 - fuzz; j2 <= j1 + fuzz; ++j2) {
                Block block = world.getBlock(i1, j2, k1);
                world.getBlockMetadata(i1, j2, k1);
                if (!block.isOpaqueCube() || !world.isAirBlock(i1, j2 + 1, k1) || !world.isAirBlock(i1 - 1, j2, k1) && !world.isAirBlock(i1 + 1, j2, k1) && !world.isAirBlock(i1, j2, k1 - 1) && !world.isAirBlock(i1, j2, k1 + 1)) continue;
                int[] bridge = this.searchForBridge(world, i1, j2, k1, -1, 0);
                if (bridge == null && (bridge = this.searchForBridge(world, i1, j2, k1, 1, 0)) == null && (bridge = this.searchForBridge(world, i1, j2, k1, 0, -1)) == null) {
                    bridge = this.searchForBridge(world, i1, j2, k1, 0, 1);
                }
                if (bridge == null) continue;
                int xRange = bridge[0];
                int zRange = bridge[1];
                int startX = i1;
                int endX = i1;
                int startZ = k1;
                int endZ = k1;
                if (xRange >= 0) {
                    endX += xRange;
                } else {
                    startX -= -xRange;
                }
                if (zRange >= 0) {
                    endZ += zRange;
                } else {
                    startZ -= -zRange;
                }
                if (xRange == 0) {
                    int xWidth = random.nextInt(3);
                    startX -= xWidth;
                    endX += xWidth;
                }
                if (zRange == 0) {
                    int zWidth = random.nextInt(3);
                    startZ -= zWidth;
                    endZ += zWidth;
                }
                for (int x = startX; x <= endX; ++x) {
                    for (int z = startZ; z <= endZ; ++z) {
                        if (random.nextInt(8) == 0) continue;
                        world.setBlock(x, j2, z, utumnoLevel.brickBlock, utumnoLevel.brickMeta, 2);
                    }
                }
                continue block0;
            }
        }
    }

    private int[] searchForBridge(World world, int i, int j, int k, int xDirection, int zDirection) {
        LOTRUtumnoLevel utumnoLevel = LOTRUtumnoLevel.forY(j);
        int maxBridgeLength = 16;
        int minBridgeLength = 2 + utumnoLevel.corridorWidth / 2;
        int foundAir = 0;
        int foundBrick = 0;
        int x = 0;
        int z = 0;
        while (Math.abs(x) < maxBridgeLength && Math.abs(z) < maxBridgeLength) {
            if (xDirection == -1) {
                --x;
            }
            if (xDirection == 1) {
                ++x;
            }
            if (zDirection == -1) {
                --z;
            }
            if (zDirection == 1) {
                ++z;
            }
            int i1 = i + x;
            int k1 = k + z;
            if (foundAir == 0 && world.isAirBlock(i1, j, k1)) {
                if (xDirection == 0) {
                    foundAir = z;
                } else if (zDirection == 0) {
                    foundAir = x;
                }
            }
            if (foundAir == 0 || !world.getBlock(i1, j, k1).isOpaqueCube()) continue;
            if (xDirection == 0) {
                foundBrick = z;
                break;
            }
            if (zDirection != 0) break;
            foundBrick = x;
            break;
        }
        if (foundBrick != 0 && Math.abs(foundBrick - foundAir) >= minBridgeLength) {
            return new int[]{x, z};
        }
        return null;
    }

    private void generateStairs(World world, Random random, int i, int k) {
        block0: for (int l = 0; l < 8; ++l) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            LOTRUtumnoLevel utumnoLevel = LOTRUtumnoLevel.values()[random.nextInt(LOTRUtumnoLevel.values().length)];
            int j1 = utumnoLevel.corridorBaseLevels[1 + random.nextInt(utumnoLevel.corridorBaseLevels.length - 1)] - 1;
            int fuzz = 2;
            for (int j2 = j1 - fuzz; j2 <= j1 + fuzz; ++j2) {
                if (!world.getBlock(i1, j2, k1).isOpaqueCube() || !world.isAirBlock(i1, j2 + 1, k1)) continue;
                int xDirection = 0;
                int zDirection = 0;
                int stairMeta = 0;
                if (random.nextBoolean()) {
                    xDirection = random.nextBoolean() ? 1 : -1;
                    stairMeta = xDirection > 0 ? 1 : 0;
                } else {
                    zDirection = random.nextBoolean() ? 1 : -1;
                    stairMeta = zDirection > 0 ? 3 : 2;
                }
                int stairX = i1;
                int stairY = j2;
                int stairZ = k1;
                int minStairRange = 6;
                int maxStairRange = 20;
                int stairWidth = 1 + random.nextInt(3);
                int stairHeight = stairWidth + 2;
                int stair = 0;
                do {
                    for (int w = 0; w < stairWidth; ++w) {
                        int i2 = stairX + w * zDirection;
                        int k2 = stairZ + w * xDirection;
                        world.setBlock(i2, stairY, k2, utumnoLevel.brickStairBlock, stairMeta, 2);
                        if (world.isAirBlock(i2, stairY - 1, k2)) {
                            world.setBlock(i2, stairY - 1, k2, utumnoLevel.brickStairBlock, stairMeta ^ 1 | 4, 2);
                        }
                        for (int j3 = stairY + 1; j3 <= stairY + stairHeight; ++j3) {
                            world.setBlock(i2, j3, k2, Blocks.air);
                        }
                    }
                    if (++stair >= maxStairRange || stair >= minStairRange && random.nextInt(10) == 0) continue block0;
                    if (xDirection == -1) {
                        --stairX;
                    }
                    if (xDirection == 1) {
                        ++stairX;
                    }
                    if (zDirection == -1) {
                        --stairZ;
                    }
                    if (zDirection != 1) continue;
                    ++stairZ;
                } while (--stairY > utumnoLevel.corridorBaseLevels[0]);
                continue block0;
            }
        }
    }

    private void generatePillars(World world, Random random, int i, int k) {
        block0: for (int l = 0; l < 40; ++l) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            LOTRUtumnoLevel utumnoLevel = LOTRUtumnoLevel.values()[random.nextInt(LOTRUtumnoLevel.values().length)];
            int j1 = utumnoLevel.corridorBaseLevels[random.nextInt(utumnoLevel.corridorBaseLevels.length)];
            int pillarHeight = MathHelper.getRandomIntegerInRange((Random)random, (int)1, (int)utumnoLevel.corridorHeight);
            int fuzz = 2;
            for (int j2 = j1 - fuzz; j2 <= j1 + fuzz; ++j2) {
                if (world.isAirBlock(i1, j2 - 1, k1)) continue;
                boolean generated = false;
                for (int j3 = j2; j3 <= j2 + pillarHeight && world.isAirBlock(i1, j3, k1); ++j3) {
                    world.setBlock(i1, j3, k1, utumnoLevel.pillarBlock, utumnoLevel.pillarMeta, 2);
                    generated = true;
                }
                if (generated) continue block0;
            }
        }
    }

    private void generatePortalBases(World world, Random random, int i, int k) {
        block0: for (int l = 0; l < 1; ++l) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            float f = random.nextFloat();
            LOTRUtumnoLevel utumnoLevel = f < 0.15f ? LOTRUtumnoLevel.ICE : (f < 0.5f ? LOTRUtumnoLevel.OBSIDIAN : LOTRUtumnoLevel.FIRE);
            int j1 = utumnoLevel.corridorBaseLevels[random.nextInt(utumnoLevel.corridorBaseLevels.length)];
            int fuzz = 2;
            for (int j2 = j1 - fuzz; j2 <= j1 + fuzz; ++j2) {
                if (!world.isAirBlock(i1, j2, k1) || !World.doesBlockHaveSolidTopSurface((IBlockAccess)world, (int)i1, (int)(j2 - 1), (int)k1)) continue;
                world.setBlock(i1, j2, k1, LOTRMod.utumnoReturnPortalBase, 0, 2);
                continue block0;
            }
        }
    }

    private void generateBars(World world, Random random, int i, int k) {
        for (int l = 0; l < 200; ++l) {
            int i1 = i + 8 + random.nextInt(16);
            int k1 = k + 8 + random.nextInt(16);
            int j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)4, (int)250);
            if (!world.getBlock(i1, j1, k1).isOpaqueCube()) continue;
            int barWidth = 1 + random.nextInt(3);
            int barHeight = 2 + random.nextInt(3);
            boolean fire = random.nextInt(3) == 0;
            int facingX = 0;
            int facingZ = 0;
            if (random.nextBoolean()) {
                facingX = random.nextBoolean() ? -1 : 1;
            } else {
                facingZ = random.nextBoolean() ? -1 : 1;
            }
            boolean generate = true;
            block1: for (int pass = 0; pass <= 1; ++pass) {
                for (int xz = 0; xz < barWidth; ++xz) {
                    for (int y = -1; y < barHeight + 1; ++y) {
                        int i2 = i1 + xz * facingZ;
                        int j2 = j1 + y;
                        int k2 = k1 + xz * facingX;
                        boolean flag = true;
                        if (!world.getBlock(i2, j2, k2).isOpaqueCube()) {
                            flag = false;
                        }
                        if (y >= 0 && y < barHeight && !world.isAirBlock(i2 + facingX, j2, k2 + facingZ)) {
                            flag = false;
                        }
                        if (flag) continue;
                        if (pass == 0) {
                            generate = true;
                            int fx = facingX;
                            facingX = facingZ;
                            facingZ = fx;
                            continue block1;
                        }
                        generate = false;
                        break block1;
                    }
                }
            }
            if (!generate) continue;
            for (int xz = 0; xz < barWidth; ++xz) {
                for (int y = 0; y < barHeight; ++y) {
                    int k3;
                    int i3;
                    int i2 = i1 + xz * facingZ;
                    int j2 = j1 + y;
                    int k2 = k1 + xz * facingX;
                    world.setBlock(i2, j2, k2, LOTRMod.orcSteelBars, 0, 2);
                    if (!fire || y != 0 || !world.getBlock(i3 = i2 - facingX, j2, k3 = k2 - facingZ).isOpaqueCube()) continue;
                    world.setBlock(i3, j2 - 1, k3, LOTRMod.hearth, 0, 2);
                    world.setBlock(i3, j2, k3, (Block)Blocks.fire, 0, 2);
                }
            }
        }
    }

    private void generateStalactites(World world, Random random, int i, int k) {
        int j1;
        int l;
        int k1;
        int i1;
        for (l = 0; l < 2; ++l) {
            i1 = i + 8 + random.nextInt(16);
            k1 = k + 8 + random.nextInt(16);
            j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)LOTRUtumnoLevel.ICE.baseLevel, (int)LOTRUtumnoLevel.ICE.topLevel);
            if (random.nextBoolean()) {
                this.stalactiteGen.generate(world, random, i1, j1, k1);
                continue;
            }
            this.stalactiteIceGen.generate(world, random, i1, j1, k1);
        }
        for (l = 0; l < 2; ++l) {
            i1 = i + 8 + random.nextInt(16);
            k1 = k + 8 + random.nextInt(16);
            j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)LOTRUtumnoLevel.OBSIDIAN.baseLevel, (int)LOTRUtumnoLevel.OBSIDIAN.topLevel);
            if (random.nextBoolean()) {
                this.stalactiteGen.generate(world, random, i1, j1, k1);
                continue;
            }
            this.stalactiteObsidianGen.generate(world, random, i1, j1, k1);
        }
        for (l = 0; l < 2; ++l) {
            i1 = i + 8 + random.nextInt(16);
            k1 = k + 8 + random.nextInt(16);
            j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)LOTRUtumnoLevel.FIRE.baseLevel, (int)LOTRUtumnoLevel.FIRE.topLevel);
            this.stalactiteObsidianGen.generate(world, random, i1, j1, k1);
        }
    }

    private void generateSkulls(World world, Random random, int i, int k) {
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int k1 = k + random.nextInt(16) + 8;
            int j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)4, (int)250);
            new LOTRWorldGenSkullPile().generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public boolean canSpawnHostilesInDay() {
        return true;
    }

    public static void updateFogColor(int i, int j, int k) {
        LOTRUtumnoLevel utumnoLevel = LOTRUtumnoLevel.forY(j);
        for (LOTRBiome biome : utumnoBiomes) {
            biome.biomeColors.setFog(utumnoLevel.fogColor);
        }
    }
}

