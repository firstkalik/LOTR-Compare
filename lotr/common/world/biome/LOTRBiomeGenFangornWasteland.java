/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBlastedLand;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import net.minecraft.world.World;

public class LOTRBiomeGenFangornWasteland
extends LOTRBiome {
    public LOTRBiomeGenFangornWasteland(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 8), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 1)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ENTS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.HUORNS, 20)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 8), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DOL_GULDUR_ORCS, 8), LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRKWOOD_SPIDERS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRK_TROLLS, 1).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDINGS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDING_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ROHIRRIM_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 8), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        this.decorator.treesPerChunk = 1;
        this.decorator.willowPerChunk = 0;
        this.decorator.logsPerChunk = 3;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 5;
        this.decorator.doubleGrassPerChunk = 3;
        this.decorator.enableFern = true;
        this.decorator.addTree(LOTRTreeType.CHARRED, 500);
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 300);
        this.decorator.addTree(LOTRTreeType.BEECH_DEAD, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH_DEAD, 20);
        this.decorator.addTree(LOTRTreeType.CHARRED_FANGORN, 50);
        this.decorator.addTree(LOTRTreeType.OAK_FANGORN_DEAD, 30);
        this.decorator.addTree(LOTRTreeType.BEECH_FANGORN_DEAD, 10);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.URUK_HAI, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.ROHAN, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.FANGORN, LOTREventSpawner.EventChance.UNCOMMON);
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ISENGARD.getSubregion("fangorn");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        if (random.nextInt(60) == 0) {
            int i1 = i + random.nextInt(16) + 8;
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenBlastedLand(true).generate(world, random, i1, world.getHeightValue(i1, k1), k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.25f;
    }

    @Override
    public boolean canSpawnHostilesInDay() {
        return true;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }
}

