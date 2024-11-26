/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.structure.MapGenStructure
 *  net.minecraft.world.gen.structure.MapGenStructureIO
 *  net.minecraft.world.gen.structure.StructureStart
 */
package lotr.common.world.mapgen.greydwarvenmine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenGreyMountains;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineCorridor;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineCrossing;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineEntrance;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineStairs;
import lotr.common.world.mapgen.greydwarvenmine.LOTRStructureGreyDwarvenMineStart;
import lotr.common.world.village.LOTRVillagePositionCache;
import lotr.common.world.village.LocationInfo;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class LOTRMapGenGreyDwarvenMine
extends MapGenStructure {
    private static List spawnBiomes;
    private int spawnChance = 150;
    private static List spawnBiomesRuined;
    private int spawnChanceRuined = 200;

    private static void setupSpawnBiomes() {
        if (spawnBiomes == null) {
            spawnBiomes = new ArrayList();
            spawnBiomesRuined = new ArrayList();
            for (LOTRBiome biome : LOTRDimension.MIDDLE_EARTH.biomeList) {
                boolean mine = false;
                boolean ruined = false;
                if (biome instanceof LOTRBiomeGenGreyMountains) {
                    ruined = true;
                }
                if (mine) {
                    spawnBiomes.add(biome);
                }
                if (!ruined) continue;
                spawnBiomesRuined.add(biome);
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
        LOTRMapGenGreyDwarvenMine.setupSpawnBiomes();
        if (this.worldObj.getWorldChunkManager().areBiomesViable(i1, k1, 0, spawnBiomes) ? this.rand.nextInt(this.spawnChance) == 0 : this.worldObj.getWorldChunkManager().areBiomesViable(i1, k1, 0, spawnBiomesRuined) && this.rand.nextInt(this.spawnChanceRuined) == 0) {
            return cache.markResult(i, k, LocationInfo.RANDOM_GEN_HERE).isPresent();
        }
        return cache.markResult(i, k, LocationInfo.NONE_HERE).isPresent();
    }

    protected StructureStart getStructureStart(int i, int k) {
        int i1 = i * 16 + 8;
        int k1 = k * 16 + 8;
        BiomeGenBase biome = this.worldObj.getWorldChunkManager().getBiomeGenAt(i1, k1);
        boolean ruined = spawnBiomesRuined.contains((Object)biome);
        return new LOTRStructureGreyDwarvenMineStart(this.worldObj, this.rand, i, k, ruined);
    }

    public String func_143025_a() {
        return "LOTR.GreyDwarvenMine";
    }

    public static void register() {
        MapGenStructureIO.registerStructure(LOTRStructureGreyDwarvenMineStart.class, (String)"LOTR.GreyDwarvenMine");
        MapGenStructureIO.func_143031_a(LOTRComponentGreyDwarvenMineEntrance.class, (String)"LOTR.GreyDwarvenMine.Entrance");
        MapGenStructureIO.func_143031_a(LOTRComponentGreyDwarvenMineCorridor.class, (String)"LOTR.GreyDwarvenMine.Corridor");
        MapGenStructureIO.func_143031_a(LOTRComponentGreyDwarvenMineCrossing.class, (String)"LOTR.GreyDwarvenMine.Crossing");
        MapGenStructureIO.func_143031_a(LOTRComponentGreyDwarvenMineStairs.class, (String)"LOTR.GreyDwarvenMine.Stairs");
    }
}

