/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditRhun;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenDurmethCamp;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenDurmeth
extends LOTRBiome {
    protected static NoiseGeneratorPerlin noiseDirt = new NoiseGeneratorPerlin(new Random(47684796930956L), 1);
    protected static NoiseGeneratorPerlin noiseStone = new NoiseGeneratorPerlin(new Random(8894086030764L), 1);
    protected static NoiseGeneratorPerlin noiseSnow = new NoiseGeneratorPerlin(new Random(2490309256000602L), 1);
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 2);

    public LOTRBiomeGenDurmeth(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 10, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 2, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 4, 1, 4));
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS_WARRIORS, 20)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACKLOCK, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.STIFFBEARD, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.STONEFOOT, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        this.npcSpawnList.conquestGainRate = 0.5f;
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE_BARREN);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE, 1.0f);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreMorgulIron, 8), 20.0f, 0, 64);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreGulduril, 8), 8.0f, 0, 32);
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 1;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.addTree(LOTRTreeType.SPRUCE_THIN, 100);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE_DEAD, 150);
        this.decorator.addTree(LOTRTreeType.CHARRED, 150);
        this.decorator.addTree(LOTRTreeType.FIR, 100);
        this.decorator.addTree(LOTRTreeType.PINE, 200);
        this.biomeColors.setGrass(6908744);
        this.biomeColors.setSky(2364434);
        this.biomeColors.setClouds(2364434);
        this.biomeColors.setFog(2364434);
        this.decorator.addRandomStructure(new LOTRWorldGenDurmethCamp(false), 300);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.setBanditEntityClass(LOTREntityBanditRhun.class);
        this.invasionSpawns.addInvasion(LOTRInvasions.IRONFIST, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.STIFFBEARD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.BLACKLOCK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.STONEFOOT, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.AVARI_ELF, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterDurmeth;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.DURMETH;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ANGMAR.getSubregion("angmar");
    }

    @Override
    public boolean getEnableRiver() {
        return true;
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = noiseDirt.func_151601_a((double)i * 0.07, (double)k * 0.07);
        double d2 = noiseDirt.func_151601_a((double)i * 0.3, (double)k * 0.3);
        double d3 = noiseStone.func_151601_a((double)i * 0.07, (double)k * 0.07);
        if (d3 + noiseStone.func_151601_a((double)i * 0.3, (double)k * 0.3) > 1.2) {
            this.topBlock = Blocks.snow;
            this.topBlockMeta = 0;
            this.fillerBlock = this.topBlock;
            this.fillerBlockMeta = this.topBlockMeta;
        } else if (d1 + d2 > 0.8) {
            this.topBlock = Blocks.dirt;
            this.topBlockMeta = 2;
        }
        super.generateBiomeTerrain(world, random, blocks, meta, i, k, stoneNoise, height, variant);
        this.topBlock = topBlock_pre;
        this.topBlockMeta = topBlockMeta_pre;
        this.fillerBlock = fillerBlock_pre;
        this.fillerBlockMeta = fillerBlockMeta_pre;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int k1;
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = world.getHeightValue(i1, k1 = k + random.nextInt(16) + 8);
            if (j1 <= 80) continue;
            this.decorator.genTree(world, random, i1, j1, k1);
        }
        if (random.nextInt(6) == 0) {
            int i1 = i + random.nextInt(16) + 8;
            k1 = k + random.nextInt(16) + 8;
            this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.1f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }

    @Override
    public boolean canSpawnHostilesInDay() {
        return true;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.25f;
    }
}

