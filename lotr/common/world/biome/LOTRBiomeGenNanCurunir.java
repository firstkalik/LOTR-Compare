/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityCrebain;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBlastedLand;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenSkullPile;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.map.LOTRWorldGenIsengardWalls;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenBurntHouse;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenUrukCamp;
import lotr.common.world.structure2.LOTRWorldGenUrukWargPit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenNanCurunir
extends LOTRBiome {
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);
    private WorldGenerator deadMoundGen = new LOTRWorldGenBoulder(LOTRMod.wasteBlock, 0, 1, 3);

    public LOTRBiomeGenNanCurunir(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityRabbit.class, 4, 4, 4));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityCrebain.class, 12, 4, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ISENGARD_SNAGA, 25), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 15)};
        this.npcSpawnList.newFactionList(65).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RUFFIANS, 10)};
        this.npcSpawnList.newFactionList(5).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDINGS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDING_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(30).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ENTS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.HUORNS, 20)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ROHIRRIM_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        this.npcSpawnList.conquestGainRate = 0.2f;
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
        this.decorator.treesPerChunk = 0;
        this.decorator.willowPerChunk = 1;
        this.decorator.logsPerChunk = 1;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 4;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.addTree(LOTRTreeType.OAK, 100);
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 100);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 100);
        this.decorator.addTree(LOTRTreeType.SPRUCE_DEAD, 100);
        this.decorator.addTree(LOTRTreeType.CHARRED, 300);
        this.registerPlainsFlowers();
        this.biomeColors.setSky(8683640);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenBurntHouse(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 5), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenUrukCamp(false), 120);
        this.decorator.addRandomStructure(new LOTRWorldGenUrukWargPit(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenBlastedLand(true), 100);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.ROHAN, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterNanCurunir;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.NAN_CURUNIR;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ISENGARD.getSubregion("isengard");
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
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.09, (double)k * 0.09);
        if (d1 + biomeTerrainNoise.func_151601_a((double)i * 0.4, (double)k * 0.4) > 0.3) {
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
        LOTRWorldGenIsengardWalls.INSTANCE.generateWithSetRotation(world, random, i, 0, k, 0);
        if (random.nextInt(40) == 0) {
            int boulders = 1 + random.nextInt(3);
            for (int l = 0; l < boulders; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
        if (random.nextInt(32) == 0) {
            for (int l = 0; l < 3; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                int j1 = world.getHeightValue(i1, k1);
                this.deadMoundGen.generate(world, random, i1, j1, k1);
                new LOTRWorldGenSkullPile().generate(world, random, i1, j1, k1);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.05f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.0f;
    }

    @Override
    public boolean canSpawnHostilesInDay() {
        return true;
    }

    @Override
    public int spawnCountMultiplier() {
        return 2;
    }
}

