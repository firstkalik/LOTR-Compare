/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.structure.MapGenStructure
 *  net.minecraft.world.gen.structure.MapGenStructureIO
 *  net.minecraft.world.gen.structure.StructureStart
 */
package lotr.common.world.mapgen.bluedwarvenmine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenBlueMountains;
import lotr.common.world.biome.LOTRBiomeGenBlueMountainsFoothills;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineCorridor;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineCrossing;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineEntrance;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineStairs;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRStructureBlueDwarvenMineStart;
import lotr.common.world.village.LOTRVillagePositionCache;
import lotr.common.world.village.LocationInfo;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class LOTRMapGenBlueDwarvenMine
extends MapGenStructure {
    private static List<LOTRBiome> spawnBiomes;
    private int spawnChance = 150;

    private static void setupSpawnBiomes() {
        if (spawnBiomes == null) {
            spawnBiomes = new ArrayList<LOTRBiome>();
            for (LOTRBiome biome : LOTRDimension.MIDDLE_EARTH.biomeList) {
                boolean mine = false;
                if (biome instanceof LOTRBiomeGenBlueMountains || biome instanceof LOTRBiomeGenBlueMountainsFoothills) {
                    mine = true;
                }
                if (!mine) continue;
                spawnBiomes.add(biome);
            }
        }
    }

    protected boolean canSpawnStructureAtCoords(int i, int k) {
        LOTRWorldChunkManager worldChunkMgr = (LOTRWorldChunkManager)this.worldObj.getWorldChunkManager();
        LOTRVillagePositionCache cache = worldChunkMgr.getStructureCache(this);
        LocationInfo cacheLocation = cache.getLocationAt(i, k);
        if (cacheLocation != null) {
            return cacheLocation.isPresent();
        }
        int i1 = i * 16 + 8;
        int k1 = k * 16 + 8;
        LOTRMapGenBlueDwarvenMine.setupSpawnBiomes();
        if (this.worldObj.getWorldChunkManager().areBiomesViable(i1, k1, 0, spawnBiomes) && this.rand.nextInt(this.spawnChance) == 0) {
            cache.markResult(i, k, LocationInfo.RANDOM_GEN_HERE).isPresent();
            return true;
        }
        cache.markResult(i, k, LocationInfo.NONE_HERE).isPresent();
        return false;
    }

    protected StructureStart getStructureStart(int i, int k) {
        return new LOTRStructureBlueDwarvenMineStart(this.worldObj, this.rand, i, k, false);
    }

    public String func_143025_a() {
        return "LOTR.BlueDwarvenMine";
    }

    public static void register() {
        MapGenStructureIO.registerStructure(LOTRStructureBlueDwarvenMineStart.class, (String)"LOTR.BlueDwarvenMine");
        MapGenStructureIO.func_143031_a(LOTRComponentBlueDwarvenMineEntrance.class, (String)"LOTR.BlueDwarvenMine.Entrance");
        MapGenStructureIO.func_143031_a(LOTRComponentBlueDwarvenMineCorridor.class, (String)"LOTR.BlueDwarvenMine.Corridor");
        MapGenStructureIO.func_143031_a(LOTRComponentBlueDwarvenMineCrossing.class, (String)"LOTR.BlueDwarvenMine.Crossing");
        MapGenStructureIO.func_143031_a(LOTRComponentBlueDwarvenMineStairs.class, (String)"LOTR.BlueDwarvenMine.Stairs");
    }
}

