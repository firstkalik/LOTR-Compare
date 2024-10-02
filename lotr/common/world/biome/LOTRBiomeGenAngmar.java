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
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBlastedLand;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenAngmarShrine;
import lotr.common.world.structure.LOTRWorldGenAngmarTower;
import lotr.common.world.structure2.LOTRWorldGenAngmarCamp;
import lotr.common.world.structure2.LOTRWorldGenAngmarHillmanVillage;
import lotr.common.world.structure2.LOTRWorldGenAngmarWargPit;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenAngmar
extends LOTRBiome {
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 2);

    public LOTRBiomeGenAngmar(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 10, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 2, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 4, 1, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_BOMBARDIERS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.TROLLS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.HILL_TROLLS, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.SNOW_TROLLS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_HILLMEN, 20)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.WICKED_DWARVES, 10)};
        this.npcSpawnList.newFactionList(2, 0.0f).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RIVENDELL_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
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
        this.decorator.grassPerChunk = 4;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.addTree(LOTRTreeType.SPRUCE_THIN, 100);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE_DEAD, 150);
        this.decorator.addTree(LOTRTreeType.CHARRED, 150);
        this.decorator.addTree(LOTRTreeType.FIR, 100);
        this.decorator.addTree(LOTRTreeType.PINE, 200);
        this.biomeColors.setGrass(7896151);
        this.biomeColors.setSky(5324595);
        this.biomeColors.setClouds(1644825);
        this.biomeColors.setFog(1644825);
        this.decorator.generateOrcDungeon = true;
        this.decorator.generateTrollHoard = true;
        this.decorator.addRandomStructure(new LOTRWorldGenAngmarTower(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.ANGMAR(1, 4), 40);
        this.decorator.addRandomStructure(new LOTRWorldGenBlastedLand(false), 40);
        this.decorator.addRandomStructure(new LOTRWorldGenAngmarShrine(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenAngmarWargPit(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenAngmarCamp(false), 50);
        this.decorator.addRandomStructure(new LOTRWorldGenAngmarHillmanVillage(false), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 300);
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
        this.invasionSpawns.addInvasion(LOTRInvasions.RANGER_NORTH, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.HIGH_ELF_LINDON, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.HIGH_ELF_RIVENDELL, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterAngmar;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.ANGMAR;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ANGMAR.getSubregion("angmar");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.07, (double)k * 0.07);
        if (d1 + biomeTerrainNoise.func_151601_a((double)i * 0.4, (double)k * 0.4) > 0.5) {
            this.topBlock = Blocks.stone;
            this.topBlockMeta = 0;
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

