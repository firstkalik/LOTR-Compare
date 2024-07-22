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
import lotr.common.entity.animal.LOTREntityGorcrow;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenMirkwoodCorrupted;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenDolGuldurAltar;
import lotr.common.world.structure2.LOTRWorldGenDolGuldurCamp;
import lotr.common.world.structure2.LOTRWorldGenDolGuldurSpiderPit;
import lotr.common.world.structure2.LOTRWorldGenDolGuldurTower;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenSouthMirkwoodCorrupted
extends LOTRBiomeGenMirkwoodCorrupted {
    public LOTRBiomeGenSouthMirkwoodCorrupted(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityGorcrow.class, 8, 4, 4));
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRKWOOD_SPIDERS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DOL_GULDUR_ORCS, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRK_TROLLS, 3)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.WOOD_ELF_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.WOOD_ELVES, 5).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.WOOD_ELVES, 5).setConquestThreshold(200.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.WOOD_ELVES, 5).setConquestThreshold(400.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GALADHRIM_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GALADHRIM_WARDENS, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        this.npcSpawnList.conquestGainRate = 0.2f;
        this.decorator.treesPerChunk = 4;
        this.decorator.vinesPerChunk = 10;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 4;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.MIRK_OAK_LARGE, 2000);
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 100);
        this.decorator.addTree(LOTRTreeType.MIRK_OAK, 300);
        this.decorator.addTree(LOTRTreeType.MIRK_OAK_DEAD, 800);
        this.biomeColors.setGrass(3032099);
        this.biomeColors.setFoliage(3032113);
        this.biomeColors.setSky(4411729);
        this.biomeColors.setClouds(3489333);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.DOL_GULDUR(1, 4), 5);
        this.decorator.addRandomStructure(new LOTRWorldGenDolGuldurAltar(false), 260);
        this.decorator.addRandomStructure(new LOTRWorldGenDolGuldurTower(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenDolGuldurCamp(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenDolGuldurSpiderPit(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 400);
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
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterSouthMirkwood;
    }
}

