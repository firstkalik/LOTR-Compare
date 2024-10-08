/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenGondor;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBerryBush;
import lotr.common.world.feature.LOTRWorldGenBerryBush2;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import lotr.common.world.structure2.LOTRWorldGenLossarnachWatchfort;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenGondor;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenLossarnach
extends LOTRBiomeGenGondor {
    public LOTRBiomeGenLossarnach(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LOSSARNACH_SOLDIERS, 10).setSpawnChance(100)};
        this.npcSpawnList.newFactionList(100, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LOSSARNACH_SOLDIERS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 4).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 1).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDHRIM, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDOR_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.COAST_SOUTHRONS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLINGS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HALF_TROLLS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        this.clearBiomeVariants();
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.DENSEFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_MAPLE, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 3.0f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 2.0f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_OLIVE, 1.0f);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 8;
        this.decorator.doubleFlowersPerChunk = 3;
        this.decorator.grassPerChunk = 5;
        this.decorator.doubleGrassPerChunk = 2;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.DARK_OAK, 400);
        this.decorator.addTree(LOTRTreeType.BIRCH, 300);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.BEECH, 50);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.MAPLE, 50);
        this.decorator.addTree(LOTRTreeType.MAPLE_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 50);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.APPLE, 40);
        this.decorator.addTree(LOTRTreeType.PEAR, 40);
        this.decorator.addTree(LOTRTreeType.PLUM, 20);
        this.decorator.addTree(LOTRTreeType.OLIVE, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.ALMOND, 5);
        this.decorator.addTree(LOTRTreeType.OAK_SHRUB, 600);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
    }

    @Override
    protected void addFiefdomStructures() {
        this.decorator.addRandomStructure(new LOTRWorldGenLossarnachWatchfort(false), 800);
        this.decorator.addVillage(new LOTRVillageGenGondor(this, LOTRWorldGenGondorStructure.GondorFiefdom.LOSSARNACH, 1.0f));
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterLossarnach;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.LOSSARNACH;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.GONDOR.getSubregion("lossarnach");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int l;
        int k1;
        int j1;
        int i1;
        super.decorate(world, random, i, k);
        for (l = 0; l < 3; ++l) {
            i1 = i + random.nextInt(16) + 8;
            j1 = random.nextInt(128);
            k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenBerryBush().generate(world, random, i1, j1, k1);
        }
        for (l = 0; l < 3; ++l) {
            i1 = i + random.nextInt(16) + 8;
            j1 = random.nextInt(128);
            k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenBerryBush2().generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.4f;
    }
}

