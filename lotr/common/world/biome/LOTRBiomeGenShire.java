/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenDoublePlant
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityShirePony;
import lotr.common.entity.npc.LOTREntityBlueDwarfMerchant;
import lotr.common.entity.npc.LOTREntityDaleMerchant;
import lotr.common.entity.npc.LOTREntityGaladhrimTrader;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityRivendellTrader;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.biome.variant.LOTRBiomeVariantOrchard;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenClover;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenHobbitPicnicBench;
import lotr.common.world.structure2.LOTRWorldGenHobbitBurrow;
import lotr.common.world.structure2.LOTRWorldGenHobbitFarm;
import lotr.common.world.structure2.LOTRWorldGenHobbitHole;
import lotr.common.world.structure2.LOTRWorldGenHobbitHouse;
import lotr.common.world.structure2.LOTRWorldGenHobbitTavern;
import lotr.common.world.structure2.LOTRWorldGenHobbitWindmill;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenShire
extends LOTRBiome {
    private LOTRBiomeSpawnList orcharderSpawnList = new LOTRBiomeSpawnList(this);

    public LOTRBiomeGenShire(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityShirePony.class, 12, 2, 6));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HOBBITS, 10)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 3).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 3).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RUFFIANS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ISENGARD_SNAGA, 10).setConquestThreshold(25.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 5).setConquestThreshold(25.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.npcSpawnList.conquestGainRate = 0.2f;
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HOBBITS_ORCHARD)};
        this.orcharderSpawnList.newFactionList(100).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 3).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        this.variantChance = 0.25f;
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_ASPEN, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_SHIRE, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 0.3f);
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 3;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 6;
        this.decorator.generateLava = false;
        this.decorator.addTree(LOTRTreeType.OAK, 1000);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 400);
        this.decorator.addTree(LOTRTreeType.OAK_PARTY, 10);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 250);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH, 25);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.ASPEN, 50);
        this.decorator.addTree(LOTRTreeType.ASPEN_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.APPLE, 5);
        this.decorator.addTree(LOTRTreeType.PEAR, 5);
        this.decorator.addTree(LOTRTreeType.CHERRY, 2);
        this.decorator.addTree(LOTRTreeType.PLUM, 5);
        this.registerPlainsFlowers();
        this.biomeColors.setGrass(8111137);
        if (this.hasShireStructures()) {
            if (((Object)((Object)this)).getClass() == LOTRBiomeGenShire.class) {
                this.decorator.addRandomStructure(new LOTRWorldGenHobbitHole(false), 90);
                this.decorator.addRandomStructure(new LOTRWorldGenHobbitBurrow(false), 45);
                this.decorator.addRandomStructure(new LOTRWorldGenHobbitHouse(false), 45);
                this.decorator.addRandomStructure(new LOTRWorldGenHobbitTavern(false), 70);
                this.decorator.addRandomStructure(new LOTRWorldGenHobbitWindmill(false), 600);
                this.decorator.addRandomStructure(new LOTRWorldGenHobbitFarm(false), 500);
            }
            this.decorator.addRandomStructure(new LOTRWorldGenHobbitPicnicBench(false), 40);
            this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 4), 1500);
            this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.ARNOR(1, 4), 1500);
        }
        this.registerTravellingTrader(LOTREntityGaladhrimTrader.class);
        this.registerTravellingTrader(LOTREntityBlueDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityDaleMerchant.class);
        this.registerTravellingTrader(LOTREntityRivendellTrader.class);
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
    }

    @Override
    public LOTRBiomeSpawnList getNPCSpawnList(World world, Random random, int i, int j, int k, LOTRBiomeVariant variant) {
        if (variant instanceof LOTRBiomeVariantOrchard && random.nextFloat() < 0.3f) {
            return this.orcharderSpawnList;
        }
        return super.getNPCSpawnList(world, random, i, j, k, variant);
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.SHIRE;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.SHIRE.getSubregion("shire");
    }

    @Override
    public boolean hasDomesticAnimals() {
        return true;
    }

    public boolean hasShireStructures() {
        return true;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int k1;
        int j1;
        int i1;
        super.decorate(world, random, i, k);
        for (int l = 0; l < this.decorator.grassPerChunk / 2; ++l) {
            int i12 = i + random.nextInt(16) + 8;
            int j12 = random.nextInt(128);
            int k12 = k + random.nextInt(16) + 8;
            new LOTRWorldGenClover().generate(world, random, i12, j12, k12);
        }
        if (random.nextInt(6) == 0) {
            i1 = i + random.nextInt(16) + 8;
            j1 = random.nextInt(128);
            k1 = k + random.nextInt(16) + 8;
            new WorldGenFlowers(LOTRMod.pipeweedPlant).generate(world, random, i1, j1, k1);
        }
        if (this.decorator.doubleFlowersPerChunk > 0 && random.nextInt(6) == 0) {
            i1 = i + random.nextInt(16) + 8;
            j1 = random.nextInt(128);
            k1 = k + random.nextInt(16) + 8;
            WorldGenDoublePlant doubleFlowerGen = new WorldGenDoublePlant();
            doubleFlowerGen.func_150548_a(0);
            doubleFlowerGen.generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.5f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }
}

