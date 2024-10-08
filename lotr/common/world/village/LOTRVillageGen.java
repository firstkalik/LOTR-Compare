/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.storage.WorldInfo
 */
package lotr.common.world.village;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRConfig;
import lotr.common.LOTRMod;
import lotr.common.util.CentredSquareArray;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.genlayer.LOTRGenLayerWorld;
import lotr.common.world.map.LOTRFixedStructures;
import lotr.common.world.map.LOTRMountains;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRRoads;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import lotr.common.world.village.LOTRVillagePositionCache;
import lotr.common.world.village.LocationInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.storage.WorldInfo;

public abstract class LOTRVillageGen {
    public static Random villageRand = new Random();
    public static double SQRT2 = Math.sqrt(2.0);
    public LOTRBiome villageBiome;
    public List<BiomeGenBase> spawnBiomes;
    public int gridScale;
    public int gridRandomDisplace;
    public float spawnChance;
    public int villageChunkRadius;
    public int fixedVillageChunkRadius;
    public List<LocationInfo> fixedLocations = new ArrayList<LocationInfo>();

    public LOTRVillageGen(LOTRBiome biome) {
        this.villageBiome = biome;
        this.spawnBiomes = new ArrayList<BiomeGenBase>();
        this.spawnBiomes.add(this.villageBiome);
    }

    public LocationInfo addFixedLocation(LOTRWaypoint wp, int addX, int addZ, int rotation, String name) {
        LocationInfo loc = new LocationInfo(wp.getXCoord() + addX, wp.getZCoord() + addZ, rotation, name).setFixedLocation(wp);
        this.fixedLocations.add(loc);
        return loc;
    }

    public LocationInfo addFixedLocation(LOTRWaypoint wp, int rotation, String name) {
        return this.addFixedLocation(wp, 0, 0, rotation, name);
    }

    public LocationInfo addFixedLocationMapOffset(LOTRWaypoint wp, int addX, int addZ, int rotation, String name) {
        return this.addFixedLocation(wp, addX * LOTRGenLayerWorld.scale, addZ * LOTRGenLayerWorld.scale, rotation, name);
    }

    public boolean anyFixedVillagesAt(World world, int i, int k) {
        if (!LOTRVillageGen.hasFixedSettlements(world)) {
            return false;
        }
        int checkRange = this.fixedVillageChunkRadius + 2;
        checkRange <<= 4;
        for (LocationInfo loc : this.fixedLocations) {
            int dx = Math.abs(loc.posX - i);
            int dz = Math.abs(loc.posZ - k);
            if (dx > checkRange || dz > checkRange) continue;
            return true;
        }
        return false;
    }

    public AbstractInstance<?> createAndSetupVillageInstance(World world, int i, int k, Random random, LocationInfo location) {
        AbstractInstance<?> instance = this.createVillageInstance(world, i, k, random, location);
        instance.setupBaseAndVillageProperties();
        return instance;
    }

    public abstract AbstractInstance<?> createVillageInstance(World var1, int var2, int var3, Random var4, LocationInfo var5);

    public void generateCompleteVillageInstance(AbstractInstance<?> instance, World world, int i, int k) {
        instance.setupVillageStructures();
        int checkRange = Math.max(this.villageChunkRadius, this.fixedVillageChunkRadius);
        for (int i1 = -checkRange; i1 <= checkRange; ++i1) {
            for (int k1 = -checkRange; k1 <= checkRange; ++k1) {
                int i2 = i - 8 + i1 * 16;
                int k2 = k - 8 + k1 * 16;
                this.generateInstanceInChunk(instance, world, i2, k2);
            }
        }
    }

    public void generateInChunk(World world, int i, int k) {
        List<AbstractInstance<?>> villages = this.getNearbyVillagesAtPosition(world, i, k);
        for (AbstractInstance<?> instance : villages) {
            instance.setupVillageStructures();
            this.generateInstanceInChunk(instance, world, i, k);
        }
    }

    public void generateInstanceInChunk(AbstractInstance<?> instance, World world, int i, int k) {
        for (int i1 = i; i1 <= i + 15; ++i1) {
            for (int k1 = k; k1 <= k + 15; ++k1) {
                BiomeGenBase biome = world.getBiomeGenForCoords(i1, k1);
                Object[] pathData = this.getHeight_getPath_isSlab(instance, world, i1, k1, biome);
                LOTRRoadType pathType = (LOTRRoadType)pathData[1];
                if (pathType != null) {
                    int j1 = (Integer)pathData[0];
                    boolean isSlab = (Boolean)pathData[2];
                    instance.setupWorldPositionSeed(i1, k1);
                    LOTRRoadType.RoadBlock roadblock = pathType.getBlock(instance.instanceRand, biome, true, isSlab);
                    LOTRRoadType.RoadBlock roadblockSolid = pathType.getBlock(instance.instanceRand, biome, false, false);
                    world.setBlock(i1, j1, k1, roadblock.block, roadblock.meta, 2);
                    world.setBlock(i1, j1 - 1, k1, roadblockSolid.block, roadblockSolid.meta, 2);
                    Block above = world.getBlock(i1, j1 + 1, k1);
                    if (!above.canBlockStay(world, i1, j1 + 1, k1)) {
                        world.setBlock(i1, j1 + 1, k1, Blocks.air, 0, 3);
                    }
                }
                instance.setupWorldPositionSeed(i1, k1);
                for (StructureInfo struct : instance.structures) {
                    int[] coords = instance.getWorldCoords(struct.posX, struct.posZ);
                    if (i1 != coords[0] || k1 != coords[1]) continue;
                    int j1 = world.getTopSolidOrLiquidBlock(i1, k1);
                    int minHeight = 62;
                    int terrainTypeMinHeight = world.provider.terrainType.getMinimumSpawnHeight(world);
                    if (terrainTypeMinHeight < 62) {
                        minHeight = terrainTypeMinHeight - 1;
                    }
                    if (j1 <= minHeight) continue;
                    struct.structure.generateWithSetRotation(world, instance.instanceRand, i1, j1, k1, instance.getStructureRotation(struct.rotation));
                }
            }
        }
    }

    public Object[] getHeight_getPath_isSlab(AbstractInstance<?> instance, World world, int i, int k, BiomeGenBase biome) {
        instance.setupWorldPositionSeed(i, k);
        int[] coords = instance.getRelativeCoords(i, k);
        int i1 = coords[0];
        int k1 = coords[1];
        LOTRRoadType road = instance.getPath(instance.instanceRand, i1, k1);
        boolean isPath = false;
        boolean isSlab = false;
        int j1 = this.getTopTerrainBlock(world, i, k, biome, true);
        if (road != null && j1 > 0 && LOTRWorldGenStructureBase2.isSurfaceStatic(world, i, j1, k)) {
            isPath = true;
            int slabRange = 1;
            CentredSquareArray<Integer> slabArray = new CentredSquareArray<Integer>(slabRange);
            slabArray.fill(j1);
            for (int i2 = -slabRange; i2 <= slabRange; ++i2) {
                for (int k2 = -slabRange; k2 <= slabRange; ++k2) {
                    int j2;
                    int i3 = i + i2;
                    int k3 = k + k2;
                    if (i2 == 0 && k2 == 0 || (j2 = this.getTopTerrainBlock(world, i3, k3, biome, true)) <= 0 || j2 >= j1) continue;
                    slabArray.set(i2, k2, j2);
                }
            }
            if ((Integer)slabArray.get(-1, 0) < j1 || (Integer)slabArray.get(1, 0) < j1 || (Integer)slabArray.get(0, -1) < j1 || (Integer)slabArray.get(0, 1) < j1) {
                isSlab = true;
            } else if ((Integer)slabArray.get(-1, -1) < j1 || (Integer)slabArray.get(1, -1) < j1 || (Integer)slabArray.get(-1, 1) < j1 || (Integer)slabArray.get(1, 1) < j1) {
                isSlab = true;
            }
            if (isSlab && world.getBlock(i, j1 + 1, k).isOpaqueCube()) {
                isSlab = false;
            }
        }
        Object[] ret = new Object[3];
        if (isPath) {
            ret[0] = j1;
            ret[1] = road;
            ret[2] = isSlab;
        } else {
            ret[0] = -1;
            ret[1] = null;
            ret[2] = false;
        }
        return ret;
    }

    public List<AbstractInstance<?>> getNearbyVillages(World world, int chunkX, int chunkZ) {
        ArrayList<AbstractInstance<?>> villages = new ArrayList<AbstractInstance<?>>();
        int checkRange = Math.max(this.villageChunkRadius, this.fixedVillageChunkRadius);
        for (int i = chunkX - checkRange; i <= chunkX + checkRange; ++i) {
            for (int k = chunkZ - checkRange; k <= chunkZ + checkRange; ++k) {
                int centreX;
                int centreZ;
                LocationInfo loc = this.isVillageCentre(world, i, k);
                if (!loc.isPresent()) continue;
                if (loc.isFixedLocation()) {
                    centreX = loc.posX;
                    centreZ = loc.posZ;
                } else {
                    centreX = (i << 4) + 8;
                    centreZ = (k << 4) + 8;
                }
                LOTRVillageGen.seedVillageRand(world, centreX, centreZ);
                AbstractInstance<?> instance = this.createAndSetupVillageInstance(world, centreX, centreZ, villageRand, loc);
                villages.add(instance);
            }
        }
        return villages;
    }

    public List<AbstractInstance<?>> getNearbyVillagesAtPosition(World world, int i, int k) {
        int chunkX = i >> 4;
        int chunkZ = k >> 4;
        return this.getNearbyVillages(world, chunkX, chunkZ);
    }

    public int getTopTerrainBlock(World world, int i, int k, BiomeGenBase biome, boolean acceptSlab) {
        int j = world.getTopSolidOrLiquidBlock(i, k) - 1;
        while (!world.getBlock(i, j + 1, k).getMaterial().isLiquid()) {
            Block block = world.getBlock(i, j, k);
            Block below = world.getBlock(i, j - 1, k);
            if (block.isOpaqueCube() || acceptSlab && block instanceof BlockSlab && below.isOpaqueCube()) {
                return j;
            }
            if (--j > 62) continue;
        }
        return -1;
    }

    public LocationInfo isVillageCentre(World world, int chunkX, int chunkZ) {
        LOTRWorldChunkManager worldChunkMgr = (LOTRWorldChunkManager)world.getWorldChunkManager();
        LOTRVillagePositionCache cache = worldChunkMgr.getVillageCache(this);
        LocationInfo cacheLocation = cache.getLocationAt(chunkX, chunkZ);
        if (cacheLocation != null) {
            return cacheLocation;
        }
        if (LOTRVillageGen.hasFixedSettlements(world)) {
            for (LocationInfo loc : this.fixedLocations) {
                int locChunkX = loc.posX >> 4;
                int locChunkZ = loc.posZ >> 4;
                if (chunkX == locChunkX && chunkZ == locChunkZ) {
                    return cache.markResult(chunkX, chunkZ, loc);
                }
                int locCheckSize = Math.max(this.villageChunkRadius, this.fixedVillageChunkRadius);
                if (Math.abs(chunkX - locChunkX) > locCheckSize || Math.abs(chunkZ - locChunkZ) > locCheckSize) continue;
                return cache.markResult(chunkX, chunkZ, LocationInfo.NONE_HERE);
            }
        }
        int i2 = MathHelper.floor_double((double)((double)chunkX / (double)this.gridScale));
        int k2 = MathHelper.floor_double((double)((double)chunkZ / (double)this.gridScale));
        LOTRVillageGen.seedVillageRand(world, i2, k2);
        i2 *= this.gridScale;
        k2 *= this.gridScale;
        if (chunkX == (i2 += MathHelper.getRandomIntegerInRange((Random)villageRand, (int)(-this.gridRandomDisplace), (int)this.gridRandomDisplace)) && chunkZ == (k2 += MathHelper.getRandomIntegerInRange((Random)villageRand, (int)(-this.gridRandomDisplace), (int)this.gridRandomDisplace))) {
            int i1 = chunkX * 16 + 8;
            int k1 = chunkZ * 16 + 8;
            int villageRange = this.villageChunkRadius * 16;
            if (villageRand.nextFloat() < this.spawnChance) {
                boolean anythingNear;
                int diagRange = (int)Math.round((double)(villageRange + 8) * SQRT2);
                boolean bl = anythingNear = LOTRRoads.isRoadNear(i1, k1, diagRange) >= 0.0f;
                if (!anythingNear && !(anythingNear = LOTRMountains.mountainNear(i1, k1, diagRange))) {
                    anythingNear = LOTRFixedStructures.structureNear(world, i1, k1, diagRange);
                }
                if (!anythingNear) {
                    LOTRVillageGen.seedVillageRand(world, i1, k1);
                    LocationInfo loc = LocationInfo.RANDOM_GEN_HERE;
                    AbstractInstance<?> instance = this.createAndSetupVillageInstance(world, i1, k1, villageRand, loc);
                    boolean flat = instance.isFlat();
                    if (worldChunkMgr.areBiomesViable(i1, k1, villageRange, this.spawnBiomes) && worldChunkMgr.areVariantsSuitableVillage(i1, k1, villageRange, flat)) {
                        return cache.markResult(chunkX, chunkZ, loc);
                    }
                }
            }
        }
        return cache.markResult(chunkX, chunkZ, LocationInfo.NONE_HERE);
    }

    public static boolean hasFixedSettlements(World world) {
        if (!LOTRConfig.generateFixedSettlements) {
            return false;
        }
        return world.getWorldInfo().getTerrainType() != LOTRMod.worldTypeMiddleEarthClassic;
    }

    public static void seedVillageRand(World world, int i, int k) {
        long seed = (long)i * 6890360793007L + (long)k * 456879569029062L + world.getWorldInfo().getSeed() + 274893855L;
        villageRand.setSeed(seed);
    }

    public static class StructureInfo {
        public LOTRWorldGenStructureBase2 structure;
        public int posX;
        public int posZ;
        public int rotation;

        public StructureInfo(LOTRWorldGenStructureBase2 s, int x, int z, int r) {
            this.structure = s;
            this.posX = x;
            this.posZ = z;
            this.rotation = r;
        }
    }

    public static abstract class AbstractInstance<V extends LOTRVillageGen> {
        public LOTRBiome instanceVillageBiome;
        public World theWorld;
        public Random instanceRand;
        public long instanceRandSeed;
        public int centreX;
        public int centreZ;
        public int rotationMode;
        public List<StructureInfo> structures = new ArrayList<StructureInfo>();
        public LocationInfo locationInfo;

        public AbstractInstance(V village, World world, int i, int k, Random random, LocationInfo loc) {
            this.instanceVillageBiome = ((LOTRVillageGen)village).villageBiome;
            this.theWorld = world;
            this.instanceRand = new Random();
            this.instanceRandSeed = random.nextLong();
            this.centreX = i;
            this.centreZ = k;
            this.locationInfo = loc;
        }

        public void addStructure(LOTRWorldGenStructureBase2 structure, int x, int z, int r) {
            this.addStructure(structure, x, z, r, false);
        }

        public void addStructure(LOTRWorldGenStructureBase2 structure, int x, int z, int r, boolean force) {
            structure.villageInstance = this;
            boolean bl = structure.restrictions = !force;
            if (force) {
                structure.shouldFindSurface = true;
            }
            this.structures.add(new StructureInfo(structure, x, z, r));
        }

        public abstract void addVillageStructures(Random var1);

        public abstract LOTRRoadType getPath(Random var1, int var2, int var3);

        public int[] getRelativeCoords(int xWorld, int zWorld) {
            int xRel = 0;
            int zRel = 0;
            switch (this.rotationMode) {
                case 0: {
                    xRel = this.centreX - xWorld;
                    zRel = this.centreZ - zWorld;
                    break;
                }
                case 1: {
                    xRel = this.centreZ - zWorld;
                    zRel = xWorld - this.centreX;
                    break;
                }
                case 2: {
                    xRel = xWorld - this.centreX;
                    zRel = zWorld - this.centreZ;
                    break;
                }
                case 3: {
                    xRel = zWorld - this.centreZ;
                    zRel = this.centreX - xWorld;
                }
            }
            return new int[]{xRel, zRel};
        }

        public int getStructureRotation(int r) {
            return (r + this.rotationMode + 2) % 4;
        }

        public int[] getWorldCoords(int xRel, int zRel) {
            int xWorld = this.centreX;
            int zWorld = this.centreZ;
            switch (this.rotationMode) {
                case 0: {
                    xWorld = this.centreX - xRel;
                    zWorld = this.centreZ - zRel;
                    break;
                }
                case 1: {
                    xWorld = this.centreX + zRel;
                    zWorld = this.centreZ - xRel;
                    break;
                }
                case 2: {
                    xWorld = this.centreX + xRel;
                    zWorld = this.centreZ + zRel;
                    break;
                }
                case 3: {
                    xWorld = this.centreX - zRel;
                    zWorld = this.centreZ + xRel;
                }
            }
            return new int[]{xWorld, zWorld};
        }

        public abstract boolean isFlat();

        public abstract boolean isVillageSpecificSurface(World var1, int var2, int var3, int var4);

        public void setRotation(int i) {
            this.rotationMode = i;
        }

        public void setupBaseAndVillageProperties() {
            this.setupVillageSeed();
            this.rotationMode = this.locationInfo.isFixedLocation() ? (this.locationInfo.rotation + 2) % 4 : this.instanceRand.nextInt(4);
            this.setupVillageProperties(this.instanceRand);
        }

        public abstract void setupVillageProperties(Random var1);

        public void setupVillageSeed() {
            long seed = (long)this.centreX * 580682095692076767L + (long)this.centreZ * 12789948968296726L + this.theWorld.getWorldInfo().getSeed() + 49920968939865L;
            this.instanceRand.setSeed(seed += this.instanceRandSeed);
        }

        public void setupVillageStructures() {
            this.setupVillageSeed();
            this.structures.clear();
            this.addVillageStructures(this.instanceRand);
        }

        public void setupWorldPositionSeed(int i, int k) {
            this.setupVillageSeed();
            int[] coords = this.getRelativeCoords(i, k);
            int i1 = coords[0];
            int k1 = coords[1];
            long seed1 = this.instanceRand.nextLong();
            long seed2 = this.instanceRand.nextLong();
            long seed = (long)i1 * seed1 + (long)k1 * seed2 ^ this.theWorld.getWorldInfo().getSeed();
            this.instanceRand.setSeed(seed);
        }
    }

}

