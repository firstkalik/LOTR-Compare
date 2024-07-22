/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenVines
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBird;
import lotr.common.entity.animal.LOTREntityButterfly;
import lotr.common.entity.animal.LOTREntityFlamingo;
import lotr.common.entity.animal.LOTREntityJungleScorpion;
import lotr.common.entity.npc.LOTREntityNomadMerchant;
import lotr.common.entity.npc.LOTREntityPallando;
import lotr.common.entity.npc.LOTREntityRedDwarfMerchant;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenHarhudor;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenVines;

public class LOTRBiomeGenHarhudorForest
extends LOTRBiomeGenHarhudor {
    public LOTRBiomeGenHarhudorForest(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 4, 1, 3));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFlamingo.class, 10, 4, 4));
        this.spawnableLOTRAmbientList.clear();
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBird.class, 10, 4, 4));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityButterfly.class, 15, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(LOTREntityJungleScorpion.class, 30, 4, 4));
        this.npcSpawnList.clear();
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.JUNGLE_DENSE);
        this.decorator.treesPerChunk = 25;
        this.decorator.vinesPerChunk = 30;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 4;
        this.decorator.grassPerChunk = 15;
        this.decorator.doubleGrassPerChunk = 10;
        this.decorator.enableFern = true;
        this.decorator.canePerChunk = 5;
        this.decorator.cornPerChunk = 10;
        this.decorator.melonPerChunk = 0.2f;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.JUNGLE, 1000);
        this.decorator.addTree(LOTRTreeType.JUNGLE_LARGE, 500);
        this.decorator.addTree(LOTRTreeType.MAHOGANY, 500);
        this.decorator.addTree(LOTRTreeType.JUNGLE_SHRUB, 1000);
        this.decorator.addTree(LOTRTreeType.MANGO, 20);
        this.decorator.addTree(LOTRTreeType.BANANA, 40);
        this.decorator.addTree(LOTRTreeType.ORANGE, 40);
        this.registerJungleFlowers();
        this.biomeColors.setGrass(7919940);
        this.biomeColors.setFoliage(8711465);
        this.biomeColors.setSky(11005393);
        this.biomeColors.setFog(11254938);
        this.biomeColors.setWater(10087913);
        this.registerTravellingTrader(LOTREntityNomadMerchant.class);
        this.registerTravellingTrader(LOTREntityRedDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityPallando.class);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterHarhudorJungle;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.RHUN.getSubregion("RHUN");
    }

    public boolean hasJungleLakes() {
        return true;
    }

    public boolean isMuddy() {
        return true;
    }

    @Override
    protected double modifyStoneNoiseForFiller(double stoneNoise) {
        if (this.isMuddy()) {
            stoneNoise += 40.0;
        }
        return stoneNoise;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        WorldGenVines vines = new WorldGenVines();
        for (int l = 0; l < 10; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 24;
            int k1 = k + random.nextInt(16) + 8;
            vines.generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public LOTRBiome.GrassBlockAndMeta getRandomGrass(Random random) {
        if (random.nextInt(4) == 0) {
            return new LOTRBiome.GrassBlockAndMeta(LOTRMod.tallGrass, 5);
        }
        return super.getRandomGrass(random);
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }
}

