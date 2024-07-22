/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenMirkOak;
import lotr.common.world.feature.LOTRWorldGenSkullPile;
import lotr.common.world.feature.LOTRWorldGenStalactites;
import lotr.common.world.map.LOTRFixedStructures;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.map.LOTRWorldGenUtumnoEntrance;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenMoredainMercCamp2;
import lotr.common.world.structure.LOTRWorldGenRAngbandBarrow;
import lotr.common.world.structure2.LOTRWorldGenAngbandFort;
import lotr.common.world.structure2.LOTRWorldGenAngbandTower;
import lotr.common.world.structure2.LOTRWorldGenBlackUrukFort2;
import lotr.common.world.structure2.LOTRWorldGenBlackUrukFort4;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenUtumnoWargPit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenAngband
extends LOTRBiome {
    private LOTRWorldGenStalactites stalactiteIceGen = new LOTRWorldGenStalactites(LOTRMod.stalactiteIce);
    private WorldGenerator deadMoundGen = new LOTRWorldGenBoulder(LOTRMod.utumnoBrick, 2, 1, 3);
    public static final int WIGHT_FOG = 0;
    public static final int WIGHT_CLOUDS = 0;
    public static final int WIGHT_SKY = 0;

    public LOTRBiomeGenAngband(int i, boolean major) {
        super(i, major);
        this.setEnableSnow();
        this.topBlock = Blocks.snow;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.npcSpawnList.clear();
        this.biomeColors.setSky(10069160);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBAND, 1).setSpawnChance(400), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBAND2, 1).setSpawnChance(600), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBANDWARGICE, 1).setSpawnChance(700), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGBAND3, 1).setSpawnChance(600)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(Blocks.packed_ice, 16), 40.0f, 32, 256);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;
        this.decorator.generateWater = false;
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 4000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.UTUMNO(1, 5), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenBlackUrukFort2(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenBlackUrukFort4(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenAngbandTower(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenUtumnoWargPit(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenRAngbandBarrow(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenMoredainMercCamp2(false), 800);
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterAngband;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.UTUMNO;
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
    public void decorate(World world, Random random, int i, int k) {
        int i1;
        int l;
        super.decorate(world, random, i, k);
        if (LOTRFixedStructures.UTUMNO_ENTRANCE.isAt(world, i, k)) {
            new LOTRWorldGenUtumnoEntrance().generate(world, random, i, world.getHeightValue(i, k), k);
        }
        for (l = 0; l < 2; ++l) {
            i1 = i + random.nextInt(16) + 8;
            int j1 = random.nextInt(60);
            int k1 = k + random.nextInt(16) + 8;
            this.stalactiteIceGen.generate(world, random, i1, j1, k1);
        }
        if (random.nextInt(20000) == 0) {
            LOTRWorldGenMirkOak tree = ((LOTRWorldGenMirkOak)LOTRTreeType.RED_OAK_WEIRWOOD.create(false, random)).disableRestrictions();
            i1 = i + random.nextInt(16) + 8;
            int k12 = k + random.nextInt(16) + 8;
            int j1 = world.getHeightValue(i1, k12);
            tree.generate(world, random, i1, j1, k12);
        }
        if (LOTRWorldGenAngbandFort.generatesAt(world, i, k)) {
            int x = i;
            int z = k;
            int y = world.getTopSolidOrLiquidBlock(x, z);
            LOTRWorldGenAngbandFort genst = new LOTRWorldGenAngbandFort(false);
            genst.restrictions = false;
            genst.generate(world, random, x, y, z);
        }
        if (random.nextInt(32) == 0) {
            for (l = 0; l < 3; ++l) {
                int i11 = i + random.nextInt(16) + 8;
                int k11 = k + random.nextInt(16) + 8;
                int j11 = world.getHeightValue(i11, k11);
                this.deadMoundGen.generate(world, random, i11, j11, k11);
                new LOTRWorldGenSkullPile().generate(world, random, i11, j11, k11);
            }
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

