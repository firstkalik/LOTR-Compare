/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.animal.LOTREntitySeagull;
import lotr.common.entity.npc.LOTREntityBlueDwarfMerchant;
import lotr.common.entity.npc.LOTREntityEreborDwarfMerchant;
import lotr.common.entity.npc.LOTREntityGaladhrimTrader;
import lotr.common.entity.npc.LOTREntityRivendellTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenHighElvenHall;
import lotr.common.world.structure2.LOTRWorldGenHighElfHouse;
import lotr.common.world.structure2.LOTRWorldGenHighElvenForge;
import lotr.common.world.structure2.LOTRWorldGenHighElvenTower;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenLindon
extends LOTRBiome {
    public LOTRBiomeGenLindon(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 5, 2, 6));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntitySeagull.class, 6, 4, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LINDON_ELVES, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.LINDON_WARRIORS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.DARK_WARRIORS, 2)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 2)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        this.npcSpawnList.conquestGainRate = 0.5f;
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.DENSEFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.DENSEFOREST_BIRCH);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_ASPEN, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 1.0f);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreQuendite, 6), 6.0f, 0, 48);
        this.decorator.setTreeCluster(10, 20);
        this.decorator.treesPerChunk = 0;
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 3;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.whiteSand = true;
        this.decorator.addTree(LOTRTreeType.OAK, 100);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 25);
        this.decorator.addTree(LOTRTreeType.BIRCH, 500);
        this.decorator.addTree(LOTRTreeType.BIRCH_TALL, 500);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 200);
        this.decorator.addTree(LOTRTreeType.BIRCH_PARTY, 50);
        this.decorator.addTree(LOTRTreeType.BEECH, 100);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 25);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 40);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.ASPEN, 300);
        this.decorator.addTree(LOTRTreeType.ASPEN_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.APPLE, 2);
        this.decorator.addTree(LOTRTreeType.PEAR, 2);
        this.registerPlainsFlowers();
        this.decorator.addRandomStructure(new LOTRWorldGenHighElfHouse(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenHighElvenHall(false), 600);
        this.decorator.addRandomStructure(new LOTRWorldGenHighElvenForge(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenHighElvenTower(false), 900);
        this.registerTravellingTrader(LOTREntityGaladhrimTrader.class);
        this.registerTravellingTrader(LOTREntityBlueDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityEreborDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityRivendellTrader.class);
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterLindon;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.LINDON;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.LINDON.getSubregion("lindon");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.HIGH_ELVEN;
    }

    @Override
    public boolean hasSeasonalGrass() {
        return false;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 5;
    }
}

