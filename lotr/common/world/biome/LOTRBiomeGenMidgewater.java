/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.entity.animal.LOTREntityFrog;
import lotr.common.entity.animal.LOTREntityMidges;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenMidgewater
extends LOTRBiome {
    public LOTRBiomeGenMidgewater(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFrog.class, 8, 1, 3));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityMidges.class, 10, 4, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10).setSpawnChance(500)};
        this.npcSpawnList.newFactionList(8).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_HILLMEN, 10).setSpawnChance(10000)};
        this.npcSpawnList.newFactionList(50).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_HILLMEN, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.clearBiomeVariants();
        this.variantChance = 1.0f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_SWAMP);
        this.decorator.sandPerChunk = 0;
        this.decorator.quagmirePerChunk = 1;
        this.decorator.treesPerChunk = 0;
        this.decorator.willowPerChunk = 1;
        this.decorator.logsPerChunk = 3;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 8;
        this.decorator.enableFern = true;
        this.decorator.mushroomsPerChunk = 3;
        this.decorator.waterlilyPerChunk = 3;
        this.decorator.canePerChunk = 10;
        this.decorator.reedPerChunk = 5;
        this.decorator.generateAthelas = true;
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 500);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 500);
        this.decorator.addTree(LOTRTreeType.OAK_SWAMP, 1500);
        this.registerSwampFlowers();
        this.biomeColors.setGrass(8553036);
        this.biomeColors.setFoliage(8546875);
        this.biomeColors.setWater(5656380);
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 100);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.RANGER_NORTH, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterMidgewater;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.MIDGEWATER;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ERIADOR.getSubregion("midgewater");
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.25f;
    }
}

