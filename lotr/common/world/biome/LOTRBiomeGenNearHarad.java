/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenCactus
 *  net.minecraft.world.gen.feature.WorldGenDeadBush
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityCamel;
import lotr.common.entity.animal.LOTREntityDesertScorpion;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.entity.animal.LOTREntityFrog;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditHarad;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenObsidianGravel2;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenHaradObelisk;
import lotr.common.world.structure2.LOTRWorldGenHaradPyramid;
import lotr.common.world.structure2.LOTRWorldGenHaradRuinedFort;
import lotr.common.world.structure2.LOTRWorldGenMumakSkeleton;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenHaradNomad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenNearHarad
extends LOTRBiome {
    private static NoiseGeneratorPerlin noiseAridGrass = new NoiseGeneratorPerlin(new Random(62926025827260L), 1);
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);
    private WorldGenerator boulderGenSandstone = new LOTRWorldGenBoulder(Blocks.sandstone, 0, 1, 3);
    private WorldGenerator obsidianGen = new LOTRWorldGenObsidianGravel2();
    protected int obsidianGravelRarity = 20;

    public LOTRBiomeGenNearHarad(int i, boolean major) {
        super(i, major);
        this.setDisableRain();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityCamel.class, 10, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 1, 1, 3));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFrog.class, 1, 1, 3));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityRabbit.class, 1, 1, 3));
        this.spawnableLOTRAmbientList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDesertScorpion.class, 10, 4, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMADS, 20).setSpawnChance(10000), LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMAD_WARRIORS, 15).setSpawnChance(10000)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DESERT_SPIDERS, 10).setSpawnChance(1000)};
        this.npcSpawnList.newFactionList(33).add(arrspawnListContainer4);
        this.variantChance = 0.8f;
        this.addBiomeVariant(LOTRBiomeVariant.DUNES, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.BOULDERS_RED);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND_SAND);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(Blocks.lapis_ore, 6), 1.0f, 0, 48);
        this.decorator.grassPerChunk = 0;
        this.decorator.doubleGrassPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.lichenPerChunk = 0;
        this.decorator.lichenPerChunk2 = 0;
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 800);
        this.decorator.addTree(LOTRTreeType.OAK_DESERT, 200);
        this.registerHaradFlowers();
        this.addFlower(LOTRMod.miniCactus, 0, 2);
        this.biomeColors.setFog(16180681);
        this.decorator.addRandomStructure(new LOTRWorldGenHaradObelisk(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenHaradPyramid(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.NEAR_HARAD(1, 4), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenMumakSkeleton(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenHaradRuinedFort(false), 3000);
        this.decorator.addVillage(new LOTRVillageGenHaradNomad(this, 0.05f));
        this.clearTravellingTraders();
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.setBanditEntityClass(LOTREntityBanditHarad.class);
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.07, (double)k * 0.07);
        double d2 = biomeTerrainNoise.func_151601_a((double)i * 0.4, (double)k * 0.4);
        double d3 = biomeTerrainNoise.func_151601_a((double)i * 0.07, (double)k * 0.07);
        double d4 = biomeTerrainNoise.func_151601_a((double)i * 0.04, (double)k * 0.04);
        if (d1 + (d2 *= 0.6) > 0.7) {
            this.topBlock = Blocks.sand;
            this.topBlockMeta = 0;
            this.fillerBlock = Blocks.sand;
            this.fillerBlockMeta = 0;
        } else if (d3 + d4 > 1.5) {
            this.topBlock = LOTRMod.quicksand;
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
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterNearHarad;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.HARAD_DESERT;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.NEAR_HARAD.getSubregion("desert");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.HARAD.setRepair(0.5f);
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int k12;
        int l;
        int i1;
        int j1;
        int i12;
        int k1;
        int j12;
        int preGrasses;
        int grasses = preGrasses = this.decorator.grassPerChunk;
        double d1 = noiseAridGrass.func_151601_a((double)i * 0.002, (double)k * 0.002);
        if (d1 > 0.5) {
            ++grasses;
        }
        this.decorator.grassPerChunk = grasses;
        super.decorate(world, random, i, k);
        this.decorator.grassPerChunk = preGrasses;
        if (random.nextInt(50) == 0) {
            i12 = i + random.nextInt(16) + 8;
            k12 = k + random.nextInt(16) + 8;
            j12 = world.getHeightValue(i12, k12);
            new WorldGenCactus().generate(world, random, i12, j12, k12);
        }
        if (this.obsidianGravelRarity > 0 && random.nextInt(this.obsidianGravelRarity) == 0) {
            int i11 = i + random.nextInt(16) + 8;
            int k11 = k + random.nextInt(16) + 8;
            j1 = world.getTopSolidOrLiquidBlock(i11, k11);
            this.obsidianGen.generate(world, random, i11, j1, k11);
        }
        if (random.nextInt(16) == 0) {
            i12 = i + random.nextInt(16) + 8;
            k12 = k + random.nextInt(16) + 8;
            j12 = world.getHeightValue(i12, k12);
            new WorldGenDeadBush((Block)Blocks.deadbush).generate(world, random, i12, j12, k12);
        }
        if (random.nextInt(120) == 0) {
            int boulders = 1 + random.nextInt(4);
            for (l = 0; l < boulders; ++l) {
                i1 = i + random.nextInt(16) + 8;
                k1 = k + random.nextInt(16) + 8;
                j1 = world.getHeightValue(i1, k1);
                if (random.nextBoolean()) {
                    this.boulderGen.generate(world, random, i1, j1, k1);
                    continue;
                }
                this.boulderGenSandstone.generate(world, random, i1, j1, k1);
            }
        }
        if (random.nextInt(2000) == 0) {
            int trees = 1 + random.nextInt(4);
            for (l = 0; l < trees; ++l) {
                i1 = i + random.nextInt(8) + 8;
                k1 = k + random.nextInt(8) + 8;
                j1 = world.getHeightValue(i1, k1);
                this.decorator.genTree(world, random, i1, j1, k1);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 5.0E-4f;
    }

    @Override
    public LOTRBiome.GrassBlockAndMeta getRandomGrass(Random random) {
        return new LOTRBiome.GrassBlockAndMeta(LOTRMod.aridGrass, 0);
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.05f;
    }

    public static interface ImmuneToFrost {
    }

    public static interface ImmuneToHeat {
    }

}

