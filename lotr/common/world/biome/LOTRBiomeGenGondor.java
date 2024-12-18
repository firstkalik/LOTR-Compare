/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityDaleMerchant;
import lotr.common.entity.npc.LOTREntityDorwinionMerchantElf;
import lotr.common.entity.npc.LOTREntityDorwinionMerchantMan;
import lotr.common.entity.npc.LOTREntityEreborDwarfMerchant;
import lotr.common.entity.npc.LOTREntityGaladhrimTrader;
import lotr.common.entity.npc.LOTREntityHobbitBartender2;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityScrapTrader;
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
import lotr.common.world.structure.LOTRWorldGenGondorRuin;
import lotr.common.world.structure.LOTRWorldGenGondorRuins;
import lotr.common.world.structure.LOTRWorldGenRuinedGondorTower;
import lotr.common.world.structure2.LOTRWorldGenGondorObelisk;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import lotr.common.world.structure2.LOTRWorldGenGondorTurret;
import lotr.common.world.structure2.LOTRWorldGenGondorWatchfort;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenGondor;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenGondor
extends LOTRBiome {
    public LOTRBiomeGenGondor(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 3, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10).setSpawnChance(100)};
        this.npcSpawnList.newFactionList(100, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_BOMBARDIERS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 4).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 1).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(5).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDHRIM, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDOR_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.COAST_SOUTHRONS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLINGS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HALF_TROLLS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 0.2f);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 1, 60, Blocks.stone), 2.0f, 0, 64);
        this.decorator.setTreeCluster(10, 30);
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.generateAthelas = true;
        this.decorator.addTree(LOTRTreeType.OAK, 1000);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 300);
        this.decorator.addTree(LOTRTreeType.BIRCH, 50);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 20);
        this.decorator.addTree(LOTRTreeType.BEECH, 50);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 20);
        this.decorator.addTree(LOTRTreeType.APPLE, 5);
        this.decorator.addTree(LOTRTreeType.PEAR, 5);
        this.decorator.addTree(LOTRTreeType.PLUM, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE, 1);
        this.decorator.addTree(LOTRTreeType.ALMOND, 1);
        this.registerPlainsFlowers();
        this.addFiefdomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenGondorRuins(), 600);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedGondorTower(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorObelisk(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorRuin(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 500);
        this.registerTravellingTrader(LOTREntityGaladhrimTrader.class);
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityEreborDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityDorwinionMerchantElf.class);
        this.registerTravellingTrader(LOTREntityDaleMerchant.class);
        this.registerTravellingTrader(LOTREntityDorwinionMerchantMan.class);
        this.registerTravellingTrader(LOTREntityHobbitBartender2.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_BLACK_URUK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_NAN_UNGOL, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_WARG, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_HARNEDOR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_COAST, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.UMBAR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.UMBAR_CORSAIR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.RHUN, LOTREventSpawner.EventChance.RARE);
    }

    protected void addFiefdomStructures() {
        this.decorator.addRandomStructure(new LOTRWorldGenGondorWatchfort(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorTurret(false), 1000);
        this.decorator.addVillage(new LOTRVillageGenGondor(this, LOTRWorldGenGondorStructure.GondorFiefdom.GONDOR, 0.75f));
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterGondor;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.GONDOR;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.GONDOR.getSubregion("gondor");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.GONDOR_MIX;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        if (random.nextInt(24) == 0) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = random.nextInt(128);
            int k1 = k + random.nextInt(16) + 8;
            new WorldGenFlowers(LOTRMod.pipeweedPlant).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.02f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }
}

