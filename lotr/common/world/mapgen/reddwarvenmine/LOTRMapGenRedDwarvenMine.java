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
package lotr.common.world.mapgen.reddwarvenmine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsBlacklock;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsFoothillsBlacklock;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsFoothillsStiffbeard;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsFoothillsStonefoot;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStiffbeard;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStonefoot;
import lotr.common.world.mapgen.reddwarvenmine.LOTRComponentRedDwarvenMineCorridor;
import lotr.common.world.mapgen.reddwarvenmine.LOTRComponentRedDwarvenMineCrossing;
import lotr.common.world.mapgen.reddwarvenmine.LOTRComponentRedDwarvenMineEntrance;
import lotr.common.world.mapgen.reddwarvenmine.LOTRComponentRedDwarvenMineStairs;
import lotr.common.world.mapgen.reddwarvenmine.LOTRStructureRedDwarvenMineStart;
import lotr.common.world.village.LOTRVillagePositionCache;
import lotr.common.world.village.LocationInfo;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class LOTRMapGenRedDwarvenMine
extends MapGenStructure {
    private static List<LOTRBiome> spawnBiomes;
    private int spawnChance = 200;
    private static List<LOTRBiome> spawnBiomesRuined;
    private int spawnChanceRuined = 500;

    private static void setupSpawnBiomes() {
        if (spawnBiomes == null) {
            spawnBiomes = new ArrayList<LOTRBiome>();
            spawnBiomesRuined = new ArrayList<LOTRBiome>();
            for (LOTRBiome biome : LOTRDimension.MIDDLE_EARTH.biomeList) {
                boolean mine = false;
                boolean ruined = false;
                if (biome instanceof LOTRBiomeGenRedMountainsStonefoot || biome instanceof LOTRBiomeGenRedMountainsFoothillsStonefoot) {
                    mine = true;
                }
                if (biome instanceof LOTRBiomeGenRedMountainsBlacklock || biome instanceof LOTRBiomeGenRedMountainsFoothillsBlacklock) {
                    mine = true;
                }
                if (biome instanceof LOTRBiomeGenRedMountainsStiffbeard || biome instanceof LOTRBiomeGenRedMountainsFoothillsStiffbeard) {
                    mine = true;
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
        LOTRMapGenRedDwarvenMine.setupSpawnBiomes();
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
        return new LOTRStructureRedDwarvenMineStart(this.worldObj, this.rand, i, k, ruined);
    }

    public String func_143025_a() {
        return "LOTR.RedDwarvenMine";
    }

    public static void register() {
        MapGenStructureIO.registerStructure(LOTRStructureRedDwarvenMineStart.class, (String)"LOTR.RedDwarvenMine");
        MapGenStructureIO.func_143031_a(LOTRComponentRedDwarvenMineEntrance.class, (String)"LOTR.RedDwarvenMine.Entrance");
        MapGenStructureIO.func_143031_a(LOTRComponentRedDwarvenMineCorridor.class, (String)"LOTR.RedDwarvenMine.Corridor");
        MapGenStructureIO.func_143031_a(LOTRComponentRedDwarvenMineCrossing.class, (String)"LOTR.RedDwarvenMine.Crossing");
        MapGenStructureIO.func_143031_a(LOTRComponentRedDwarvenMineStairs.class, (String)"LOTR.RedDwarvenMine.Stairs");
    }
}

