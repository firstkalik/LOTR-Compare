/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenDoublePlant
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditHarad;
import lotr.common.entity.npc.LOTREntityNomadMerchant;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenFarHarad;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenSand;
import lotr.common.world.feature.LOTRWorldGenYams;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenMoredainCamp;
import lotr.common.world.structure2.LOTRWorldGenMoredainVillage;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenFarHaradSavannah
extends LOTRBiomeGenFarHarad {
    private static NoiseGeneratorPerlin populatedNoise = new NoiseGeneratorPerlin(new Random(100L), 1);
    protected LOTRBiomeSpawnList populatedSpawnList = new LOTRBiomeSpawnList(this);
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);

    public LOTRBiomeGenFarHaradSavannah(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH, 10).setSpawnChance(10000), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH_WARRIORS, 5).setSpawnChance(10000)};
        this.npcSpawnList.newFactionList(100, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 1).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMAD_WARRIORS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.GULF_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.TAURETHRIM_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.TAURETHRIM, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.TAURETHRIM, 5).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.TAURETHRIM, 5).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HALF_TROLLS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer8 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH_WARRIORS, 5)};
        this.populatedSpawnList.newFactionList(0).add(arrspawnListContainer8);
        this.variantChance = 0.3f;
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE_BARREN);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SAVANNAH_BAOBAB, 3.0f);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND, 2.0f);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_SCRUBLAND);
        this.addBiomeVariant(LOTRBiomeVariant.WASTELAND);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.redClay, 32, Blocks.dirt), 40.0f, 0, 80);
        this.decorator.setTreeCluster(3, 60);
        this.decorator.clayGen = new LOTRWorldGenSand(LOTRMod.redClay, 5, 1);
        this.decorator.clayPerChunk = 4;
        this.decorator.lichenPerChunk = 0;
        this.decorator.lichenPerChunk2 = 0;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 12;
        this.decorator.flowersPerChunk = 3;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.melonPerChunk = 0.01f;
        this.decorator.addRandomStructure(new LOTRWorldGenMoredainVillage(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenMoredainCamp(false), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.TAUREDAIN(1, 2), 5000);
        this.registerTravellingTrader(LOTREntityNomadMerchant.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.setBanditEntityClass(LOTREntityBanditHarad.class);
        this.invasionSpawns.addInvasion(LOTRInvasions.MOREDAIN, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.TAUREDAIN, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public void addBiomeF3Info(List info, World world, LOTRBiomeVariant variant, int i, int j, int k) {
        super.addBiomeF3Info(info, world, variant, i, j, k);
        boolean populated = LOTRBiomeGenFarHaradSavannah.isBiomePopulated(i, j, k);
        info.add("HaradPopulated: " + populated);
    }

    public static boolean isBiomePopulated(int i, int j, int k) {
        double scale = 8.0E-4;
        double d = populatedNoise.func_151601_a((double)i * scale, (double)k * scale);
        return d > 0.5;
    }

    @Override
    public LOTRBiomeSpawnList getNPCSpawnList(World world, Random random, int i, int j, int k, LOTRBiomeVariant variant) {
        if (LOTRBiomeGenFarHaradSavannah.isBiomePopulated(i, j, k)) {
            return this.populatedSpawnList;
        }
        return super.getNPCSpawnList(world, random, i, j, k, variant);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterFarHaradSavannah;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.FAR_HARAD.getSubregion("savannah");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        if (random.nextInt(32) == 0) {
            int boulders = 1 + random.nextInt(4);
            for (int l = 0; l < boulders; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
        if (random.nextInt(6) == 0) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = random.nextInt(128);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenYams().generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.1f;
    }

    @Override
    public WorldGenerator getRandomWorldGenForDoubleFlower(Random random) {
        if (random.nextInt(6) == 0) {
            WorldGenDoublePlant gen = new WorldGenDoublePlant();
            gen.func_150548_a(0);
            return gen;
        }
        return super.getRandomWorldGenForDoubleFlower(random);
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.75f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }
}

