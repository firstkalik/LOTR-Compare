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
package lotr.common.world.mapgen.winddwarvenmine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenWindMountains;
import lotr.common.world.biome.LOTRBiomeGenWindMountainsFoothills;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponenWindDwarvenMineEntrance;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineCorridor;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineCrossing;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineStairs;
import lotr.common.world.mapgen.winddwarvenmine.LOTRStructureWindDwarvenMineStart;
import lotr.common.world.village.LOTRVillagePositionCache;
import lotr.common.world.village.LocationInfo;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class LOTRMapGenWindDwarvenMine
extends MapGenStructure {
    private static List<LOTRBiome> spawnBiomes;
    private int spawnChance = 150;

    private static void setupSpawnBiomes() {
        if (spawnBiomes == null) {
            spawnBiomes = new ArrayList<LOTRBiome>();
            for (LOTRBiome biome : LOTRDimension.MIDDLE_EARTH.biomeList) {
                boolean mine = false;
                if (biome instanceof LOTRBiomeGenWindMountains || biome instanceof LOTRBiomeGenWindMountainsFoothills) {
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
        LOTRMapGenWindDwarvenMine.setupSpawnBiomes();
        if (this.worldObj.getWorldChunkManager().areBiomesViable(i1, k1, 0, spawnBiomes) && this.rand.nextInt(this.spawnChance) == 0) {
            cache.markResult(i, k, LocationInfo.RANDOM_GEN_HERE).isPresent();
            return true;
        }
        cache.markResult(i, k, LocationInfo.NONE_HERE).isPresent();
        return false;
    }

    protected StructureStart getStructureStart(int i, int k) {
        return new LOTRStructureWindDwarvenMineStart(this.worldObj, this.rand, i, k, false);
    }

    public String func_143025_a() {
        return "LOTR.WindDwarvenMine";
    }

    public static void register() {
        MapGenStructureIO.registerStructure(LOTRStructureWindDwarvenMineStart.class, (String)"LOTR.WindDwarvenMine");
        MapGenStructureIO.func_143031_a(LOTRComponenWindDwarvenMineEntrance.class, (String)"LOTR.WindDwarvenMine.Entrance");
        MapGenStructureIO.func_143031_a(LOTRComponentWindDwarvenMineCorridor.class, (String)"LOTR.WindDwarvenMine.Corridor");
        MapGenStructureIO.func_143031_a(LOTRComponentWindDwarvenMineCrossing.class, (String)"LOTR.WindDwarvenMine.Crossing");
        MapGenStructureIO.func_143031_a(LOTRComponentWindDwarvenMineStairs.class, (String)"LOTR.WindDwarvenMine.Stairs");
    }
}

