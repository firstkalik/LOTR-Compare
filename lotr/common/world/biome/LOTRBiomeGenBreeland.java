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
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityBlueDwarfMerchant;
import lotr.common.entity.npc.LOTREntityDaleMerchant;
import lotr.common.entity.npc.LOTREntityEreborDwarfMerchant;
import lotr.common.entity.npc.LOTREntityGaladhrimTrader;
import lotr.common.entity.npc.LOTREntityHobbitBartender2;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityRivendellTrader;
import lotr.common.entity.npc.LOTREntityScrapTrader;
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
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenBree;
import lotr.common.world.village.LocationInfo;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenBreeland
extends LOTRBiome {
    public LOTRBiomeGenBreeland(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 8, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.BREE_MEN, 10).setSpawnChance(500), LOTRBiomeSpawnList.entry(LOTRSpawnList.BREE_GUARDS, 4).setSpawnChance(500)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RUFFIANS, 10).setSpawnChance(500)};
        this.npcSpawnList.newFactionList(5).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 2).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_HILLMEN, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_ASPEN, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_MAPLE, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 1.0f);
        this.decorator.setTreeCluster(8, 20);
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 2;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.generateAthelas = true;
        this.decorator.addTree(LOTRTreeType.OAK, 1000);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 300);
        this.decorator.addTree(LOTRTreeType.BEECH, 300);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 75);
        this.decorator.addTree(LOTRTreeType.MAPLE, 200);
        this.decorator.addTree(LOTRTreeType.MAPLE_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 300);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 75);
        this.decorator.addTree(LOTRTreeType.BIRCH, 50);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 20);
        this.decorator.addTree(LOTRTreeType.ASPEN, 50);
        this.decorator.addTree(LOTRTreeType.ASPEN_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.APPLE, 3);
        this.decorator.addTree(LOTRTreeType.PEAR, 3);
        this.registerPlainsFlowers();
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 3), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.ARNOR(1, 3), 1000);
        LOTRVillageGenBree villageGen = new LOTRVillageGenBree(this, 1.0f);
        villageGen.addFixedLocationMapOffset(LOTRWaypoint.BREE, 2, 0, 3, "Bree");
        villageGen.addFixedLocationMapOffset(LOTRWaypoint.STADDLE, -2, 0, 1, "Staddle");
        villageGen.addFixedLocationMapOffset(LOTRWaypoint.COMBE, 2, 0, 3, "Combe");
        villageGen.addFixedLocationMapOffset(LOTRWaypoint.ARCHET, 0, -2, 2, "Archet");
        this.decorator.addVillage(villageGen);
        this.registerTravellingTrader(LOTREntityGaladhrimTrader.class);
        this.registerTravellingTrader(LOTREntityBlueDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityEreborDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityDaleMerchant.class);
        this.registerTravellingTrader(LOTREntityRivendellTrader.class);
        this.registerTravellingTrader(LOTREntityHobbitBartender2.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public boolean hasDomesticAnimals() {
        return true;
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterBreeland;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.BREE_LAND;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.BREE.getSubregion("bree");
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.05f;
    }
}

