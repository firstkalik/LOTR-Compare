/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenGondor;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import lotr.common.world.structure2.LOTRWorldGenLamedonWatchfort;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenGondor;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenLamedon
extends LOTRBiomeGenGondor {
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);

    public LOTRBiomeGenLamedon(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 30, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 40, 4, 4));
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_HILLMEN, 20).setSpawnChance(50), LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_SOLDIERS, 25).setSpawnChance(50)};
        this.npcSpawnList.newFactionList(100, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_HILLMEN, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_SOLDIERS, 25)};
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
        this.variantChance = 0.3f;
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LARCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 0.5f);
        this.decorator.setTreeCluster(10, 20);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 3;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 6;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH, 50);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.BEECH, 50);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 200);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.LARCH, 300);
        this.decorator.addTree(LOTRTreeType.ASPEN, 300);
        this.decorator.addTree(LOTRTreeType.APPLE, 5);
        this.decorator.addTree(LOTRTreeType.PEAR, 5);
        this.decorator.addTree(LOTRTreeType.PLUM, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE, 1);
        this.decorator.addTree(LOTRTreeType.ALMOND, 1);
        this.biomeColors.setGrass(11646287);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
    }

    @Override
    protected void addFiefdomStructures() {
        this.decorator.addRandomStructure(new LOTRWorldGenLamedonWatchfort(false), 700);
        this.decorator.addVillage(new LOTRVillageGenGondor(this, LOTRWorldGenGondorStructure.GondorFiefdom.LAMEDON, 0.8f));
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterLamedon;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.LAMEDON;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.GONDOR.getSubregion("lamedon");
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.07, (double)k * 0.07);
        if (d1 + biomeTerrainNoise.func_151601_a((double)i * 0.4, (double)k * 0.4) > 0.7) {
            this.topBlock = Blocks.dirt;
            this.topBlockMeta = 1;
            this.fillerBlock = this.topBlock;
            this.fillerBlockMeta = this.topBlockMeta;
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
        if (random.nextInt(16) == 0) {
            for (int l = 0; l < 4; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.1f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.75f;
    }
}

