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
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityDeer2;
import lotr.common.entity.animal.LOTREntityPolarBear;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenMirkOak;
import lotr.common.world.feature.LOTRWorldGenStalactites;
import lotr.common.world.map.LOTRFixedStructures;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.map.LOTRWorldGenUtumnoEntrance;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenMoredainMercCamp2;
import lotr.common.world.structure.LOTRWorldGenRAngbandBarrow;
import lotr.common.world.structure.LOTRWorldGenUrukWargPit;
import lotr.common.world.structure2.LOTRWorldGenAngbandTower2;
import lotr.common.world.structure2.LOTRWorldGenBlackUrukFort2;
import lotr.common.world.structure2.LOTRWorldGenBlackUrukFort4;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenFallForodwaith
extends LOTRBiome {
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 2);
    private LOTRWorldGenStalactites stalactiteIceGen = new LOTRWorldGenStalactites(LOTRMod.stalactiteIce);
    public static final int WIGHT_FOG = 0;
    public static final int WIGHT_CLOUDS = 0;
    public static final int WIGHT_SKY = 0;

    public LOTRBiomeGenFallForodwaith(int i, boolean major) {
        super(i, major);
        this.setEnableSnow();
        this.topBlock = Blocks.snow;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.npcSpawnList.clear();
        this.biomeColors.setSky(10069160);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityPolarBear.class, 1, 2, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer2.class, 1, 2, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBAND, 1).setSpawnChance(550), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBAND2, 1).setSpawnChance(600), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBANDTROLLS2, 1).setSpawnChance(850), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBANDWARGICE, 1).setSpawnChance(950), LOTRBiomeSpawnList.entry(LOTRSpawnList.UTUMNO_MOUNRAIN_SNOW_TROLLS, 1).setSpawnChance(1000)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(Blocks.packed_ice, 16), 40.0f, 32, 256);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;
        this.decorator.lichenPerChunk = 0;
        this.decorator.generateWater = false;
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.UTUMNO(1, 5), 600);
        this.decorator.addRandomStructure(new LOTRWorldGenBlackUrukFort4(false), 900);
        this.decorator.addRandomStructure(new LOTRWorldGenBlackUrukFort2(false), 900);
        this.decorator.addRandomStructure(new LOTRWorldGenAngbandTower2(false), 900);
        this.decorator.addRandomStructure(new LOTRWorldGenRAngbandBarrow(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenUrukWargPit(false), 900);
        this.decorator.addRandomStructure(new LOTRWorldGenMoredainMercCamp2(false), 900);
        this.setBanditChance(LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterFallForodwaith;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.FORODWAITH;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.FORODWAITH.getSubregion("forodwaith");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.UTUMNO;
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.07, (double)k * 0.07);
        double d2 = biomeTerrainNoise.func_151601_a((double)i * 0.4, (double)k * 0.4);
        if (d1 + (d2 *= 0.6) > 0.7) {
            this.topBlock = Blocks.snow;
            this.topBlockMeta = 0;
            this.fillerBlock = Blocks.snow;
            this.fillerBlockMeta = 0;
        } else if (d1 + d1 > 1.5) {
            this.topBlock = LOTRMod.snowdrift;
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
        int i1;
        int k1;
        super.decorate(world, random, i, k);
        if (LOTRFixedStructures.UTUMNO_ENTRANCE.isAt(world, i, k)) {
            new LOTRWorldGenUtumnoEntrance().generate(world, random, i, world.getHeightValue(i, k), k);
        }
        if (random.nextInt(32) == 0) {
            int boulders = 1 + random.nextInt(5);
            for (int l = 0; l < boulders; ++l) {
                int i12 = i + random.nextInt(16) + 8;
                k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i12, world.getHeightValue(i12, k1), k1);
            }
        }
        for (int l = 0; l < 2; ++l) {
            i1 = i + random.nextInt(16) + 8;
            int j1 = random.nextInt(60);
            k1 = k + random.nextInt(16) + 8;
            this.stalactiteIceGen.generate(world, random, i1, j1, k1);
        }
        if (random.nextInt(20000) == 0) {
            LOTRWorldGenMirkOak tree = ((LOTRWorldGenMirkOak)LOTRTreeType.RED_OAK_WEIRWOOD.create(false, random)).disableRestrictions();
            i1 = i + random.nextInt(16) + 8;
            int k12 = k + random.nextInt(16) + 8;
            int j1 = world.getHeightValue(i1, k12);
            tree.generate(world, random, i1, j1, k12);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.0f;
    }

    @Override
    public boolean canSpawnHostilesInDay() {
        return true;
    }
}

