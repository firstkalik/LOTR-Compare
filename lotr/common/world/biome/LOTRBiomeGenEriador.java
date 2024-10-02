/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$FlowerEntry
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityBlueDwarfMerchant;
import lotr.common.entity.npc.LOTREntityGaladhrimTrader;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityRivendellTrader;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBiomeFlowers;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenRuinedDunedainTower;
import lotr.common.world.structure2.LOTRWorldGenBurntHouse;
import lotr.common.world.structure2.LOTRWorldGenGundabadCamp;
import lotr.common.world.structure2.LOTRWorldGenRangerCamp;
import lotr.common.world.structure2.LOTRWorldGenRangerWatchtower;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenEriador
extends LOTRBiome {
    public static NoiseGeneratorPerlin lavenderNoise = new NoiseGeneratorPerlin(new Random(2571548905158015L), 1);

    public LOTRBiomeGenEriador(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 6, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 4, 1, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10).setSpawnChance(200)};
        this.npcSpawnList.newFactionList(10, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 2).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_HILLMEN, 4).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LINDON_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.RIVENDELL_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_SCRUBLAND, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.MOUNTAIN);
        this.addBiomeVariant(LOTRBiomeVariant.WASTELAND);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_ASPEN, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_MAPLE, 0.2f);
        this.decorator.setTreeCluster(8, 12);
        this.decorator.willowPerChunk = 1;
        this.decorator.grassPerChunk = 9;
        this.decorator.doubleGrassPerChunk = 4;
        this.decorator.generateAthelas = true;
        this.decorator.addTree(LOTRTreeType.OAK, 1000);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 200);
        this.decorator.addTree(LOTRTreeType.BEECH, 20);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 2);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 100);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.ASPEN, 50);
        this.decorator.addTree(LOTRTreeType.ASPEN_LARGE, 5);
        this.decorator.addTree(LOTRTreeType.APPLE, 2);
        this.decorator.addTree(LOTRTreeType.PEAR, 2);
        this.registerPlainsFlowers();
        this.addFlower(LOTRMod.lavender, 0, 20);
        this.decorator.generateOrcDungeon = true;
        this.decorator.addRandomStructure(new LOTRWorldGenGundabadCamp(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedDunedainTower(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenBurntHouse(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenRangerCamp(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 3), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.ARNOR(1, 3), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenRangerWatchtower(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 400);
        this.registerTravellingTrader(LOTREntityGaladhrimTrader.class);
        this.registerTravellingTrader(LOTREntityBlueDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityRivendellTrader.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.RANGER_NORTH, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.ANGMAR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.ANGMAR_HILLMEN, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.ANGMAR_WARG, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterEriador;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.ERIADOR;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ERIADOR.getSubregion("eriador");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.ARNOR.setRepair(0.92f);
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        LOTRBiomeVariant biomeVariant = ((LOTRWorldChunkManager)world.getWorldChunkManager()).getBiomeVariantAt(i + 8, k + 8);
        if (biomeVariant.flowerFactor >= 1.0f) {
            double d;
            double lavNoise = lavenderNoise.func_151601_a((double)i * 0.001, (double)k * 0.001);
            lavNoise += lavenderNoise.func_151601_a((double)i * 0.03, (double)k * 0.03);
            lavNoise /= 2.0;
            lavNoise -= 0.75;
            if (d >= 0.0) {
                int num = (int)Math.round(lavNoise * 16.0);
                num = Math.max(num, 4);
                LOTRWorldGenBiomeFlowers lavGen = new LOTRWorldGenBiomeFlowers(LOTRMod.lavender, 0);
                for (int l = 0; l < num; ++l) {
                    int i1 = i + random.nextInt(16) + 8;
                    int k1 = k + random.nextInt(16) + 8;
                    int j1 = world.getTopSolidOrLiquidBlock(i1, k1);
                    lavGen.generate(world, random, i1, j1, k1);
                }
            }
        }
    }

    @Override
    public BiomeGenBase.FlowerEntry getRandomFlower(World world, Random rand, int i, int j, int k) {
        LOTRBiomeVariant variant = ((LOTRWorldChunkManager)world.getWorldChunkManager()).getBiomeVariantAt(i, k);
        if (variant.flowerFactor > 1.0f && rand.nextFloat() < 0.9f) {
            return new BiomeGenBase.FlowerEntry(LOTRMod.lavender, 0, 100);
        }
        return (BiomeGenBase.FlowerEntry)WeightedRandom.getRandomItem((Random)rand, (Collection)this.flowers);
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.1f;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.05f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 4;
    }
}

