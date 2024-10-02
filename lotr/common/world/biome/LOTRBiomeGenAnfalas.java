/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenGondor;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenGondorRuin;
import lotr.common.world.structure.LOTRWorldGenGondorRuins;
import lotr.common.world.structure2.LOTRWorldGenGondorObelisk;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenAnfalas
extends LOTRBiomeGenGondor {
    public LOTRBiomeGenAnfalas(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 30, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDHRIM, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDOR_WARRIORS, 10).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.COAST_SOUTHRONS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 10).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.CORSAIRS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.UMBAR_SOLDIERS, 10).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_RENEGADES, 10).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        this.npcSpawnList.conquestGainRate = 0.5f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL);
        this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.DENSEFOREST_BIRCH);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_ORANGE, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_LEMON, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_LIME, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_OLIVE, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_ALMOND, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 0.1f);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 1, 60, Blocks.stone), 2.0f, 0, 64);
        this.decorator.treesPerChunk = 0;
        this.decorator.setTreeCluster(10, 30);
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 4;
        this.decorator.generateAthelas = true;
        this.decorator.whiteSand = true;
        this.decorator.addTree(LOTRTreeType.BIRCH, 200);
        this.decorator.addTree(LOTRTreeType.BIRCH_TALL, 200);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 200);
        this.decorator.addTree(LOTRTreeType.OAK, 100);
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 100);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.CEDAR, 100);
        this.decorator.addTree(LOTRTreeType.CYPRESS, 35);
        this.decorator.addTree(LOTRTreeType.CYPRESS_LARGE, 5);
        this.decorator.addTree(LOTRTreeType.PEAR, 7);
        this.decorator.addTree(LOTRTreeType.LEMON, 1);
        this.decorator.addTree(LOTRTreeType.ORANGE, 1);
        this.decorator.addTree(LOTRTreeType.LIME, 1);
        this.decorator.addTree(LOTRTreeType.OLIVE_LARGE, 2);
        this.decorator.addTree(LOTRTreeType.ALMOND, 5);
        this.decorator.addTree(LOTRTreeType.PLUM, 5);
        this.decorator.setTreeCluster(i, i);
        this.registerPlainsFlowers();
        this.decorator.addRandomStructure(new LOTRWorldGenGondorObelisk(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorRuin(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 600);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorRuins(), 600);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.clearInvasions();
        this.biomeColors.setGrass(8237903);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterAnfalas;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.ANFALAS;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.GONDOR.getSubregion("dolAmroth");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.GONDOR_MIX;
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        double d2;
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.07, (double)k * 0.07);
        if (d1 + (d2 = biomeTerrainNoise.func_151601_a((double)i * 0.4, (double)k * 0.4)) > 0.9) {
            if (random.nextBoolean()) {
                this.topBlock = Blocks.stone;
                this.topBlockMeta = 1;
                this.fillerBlock = this.topBlock;
                this.fillerBlockMeta = this.topBlockMeta;
            } else {
                this.topBlock = Blocks.sand;
                this.topBlockMeta = 0;
                this.fillerBlock = this.topBlock;
                this.fillerBlockMeta = this.topBlockMeta;
            }
        }
        super.generateBiomeTerrain(world, random, blocks, meta, i, k, stoneNoise, height, variant);
        this.topBlock = topBlock_pre;
        this.topBlockMeta = topBlockMeta_pre;
        this.fillerBlock = fillerBlock_pre;
        this.fillerBlockMeta = fillerBlockMeta_pre;
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
    public float getTreeIncreaseChance() {
        return 0.03f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.5f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 4;
    }
}

