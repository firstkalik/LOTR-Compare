/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditRhun;
import lotr.common.entity.npc.LOTREntityPallando;
import lotr.common.entity.npc.LOTREntityRedDwarfMerchant;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenAvariElfTower;
import lotr.common.world.structure2.LOTRWorldGenAvariElfHouse;
import lotr.common.world.structure2.LOTRWorldGenAvariElvenForge;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenRhunForest2
extends LOTRBiome {
    public LOTRBiomeGenRhunForest2(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 16, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 20, 4, 6));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.AVARI_ELVES, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.AVARI_ELF_WARRIORS, 3)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.CAVE, 1).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_SPIDERS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        this.clearBiomeVariants();
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreQuendite, 6), 6.0f, 0, 48);
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.decorator.treesPerChunk = 1;
        this.decorator.logsPerChunk = 1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 2;
        this.decorator.generateWater = false;
        this.decorator.generateLava = false;
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 2000);
        this.decorator.addTree(LOTRTreeType.OAK_PARTY, 100);
        this.decorator.addTree(LOTRTreeType.LARCH, 100);
        this.decorator.addTree(LOTRTreeType.PLUM, 50);
        this.decorator.addTree(LOTRTreeType.APPLE, 50);
        this.registerTravellingTrader(LOTREntityRedDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityPallando.class);
        this.setBanditEntityClass(LOTREntityBanditRhun.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.decorator.addRandomStructure(new LOTRWorldGenAvariElfHouse(false), 16);
        this.decorator.addRandomStructure(new LOTRWorldGenAvariElfTower(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenAvariElvenForge(false), 25);
        this.registerRhunForestFlowers();
        this.biomeColors.resetGrass();
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterAvariForest;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.5f;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.AVARI;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.RHUN.getSubregion("rhun");
    }
}

