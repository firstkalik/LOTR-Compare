/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import lotr.common.entity.npc.LOTREntityDaleMerchant;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenWilderland;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenBurntHouse;
import lotr.common.world.structure2.LOTRWorldGenGundabadCamp;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenWilderlandNorth
extends LOTRBiomeGenWilderland {
    public LOTRBiomeGenWilderlandNorth(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DOL_GULDUR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRKWOOD_SPIDERS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRK_TROLLS, 1).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DALE_SOLDIERS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DALE_MEN, 2).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.DALE_MEN, 2).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DWARVES, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLINGS, 5).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACKLOCK, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.STIFFBEARD, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.STONEFOOT, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK_SPRUCE);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE);
        this.decorator.setTreeCluster(6, 8);
        this.decorator.flowersPerChunk = 2;
        this.decorator.doubleFlowersPerChunk = 0;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 5;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 500);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 300);
        this.decorator.addTree(LOTRTreeType.SPRUCE_DEAD, 100);
        this.decorator.addTree(LOTRTreeType.FIR, 200);
        this.decorator.addTree(LOTRTreeType.PINE, 200);
        this.registerPlainsFlowers();
        this.decorator.clearRandomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenGundabadCamp(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenBurntHouse(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 4), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 600);
        this.clearTravellingTraders();
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityDaleMerchant.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.clearInvasions();
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.DOL_GULDUR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DOL_GULDUR_URUK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DALE, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public int spawnCountMultiplier() {
        return 1;
    }
}

