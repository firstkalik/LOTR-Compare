/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenDoublePlant
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityGaladhrimTrader;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenGondorRuins;
import lotr.common.world.structure.LOTRWorldGenRuinedGondorTower;
import lotr.common.world.structure2.LOTRWorldGenGondorObelisk;
import lotr.common.world.structure2.LOTRWorldGenIthilienHideout;
import lotr.common.world.structure2.LOTRWorldGenRuinedBeaconTower;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenIthilien
extends LOTRBiome {
    public LOTRBiomeGenIthilien(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 10, 4, 6));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 3).setSpawnChance(100);
        this.npcSpawnList.newFactionList(10).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[10];
        arrspawnListContainer2[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10);
        arrspawnListContainer2[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 10);
        arrspawnListContainer2[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.LOSSARNACH_SOLDIERS, 4);
        arrspawnListContainer2[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.LEBENNIN_SOLDIERS, 4).setConquestThreshold(50.0f);
        arrspawnListContainer2[4] = LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_HILLMEN, 1).setConquestThreshold(50.0f);
        arrspawnListContainer2[5] = LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_SOLDIERS, 1).setConquestThreshold(50.0f);
        arrspawnListContainer2[6] = LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACKROOT_SOLDIERS, 1).setConquestThreshold(50.0f);
        arrspawnListContainer2[7] = LOTRBiomeSpawnList.entry(LOTRSpawnList.PINNATH_GELIN_SOLDIERS, 1).setConquestThreshold(50.0f);
        arrspawnListContainer2[8] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DOL_AMROTH_SOLDIERS, 2).setConquestThreshold(100.0f);
        arrspawnListContainer2[9] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_MEN, 10).setConquestThreshold(500.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[6];
        arrspawnListContainer3[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10);
        arrspawnListContainer3[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_BOMBARDIERS, 1);
        arrspawnListContainer3[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 2);
        arrspawnListContainer3[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(50.0f);
        arrspawnListContainer3[4] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_SPIDERS, 1).setConquestThreshold(100.0f);
        arrspawnListContainer3[5] = LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(150.0f);
        this.npcSpawnList.newFactionList(90).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer4[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 10);
        arrspawnListContainer4[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 2).setConquestThreshold(50.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer5[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.ROHIRRIM_WARRIORS, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[4];
        arrspawnListContainer6[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDHRIM, 2).setConquestThreshold(100.0f);
        arrspawnListContainer6[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDOR_WARRIORS, 10);
        arrspawnListContainer6[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.COAST_SOUTHRONS, 2).setConquestThreshold(100.0f);
        arrspawnListContainer6[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer7[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH_WARRIORS, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer8 = new LOTRBiomeSpawnList.SpawnListContainer[3];
        arrspawnListContainer8[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLINGS, 2).setConquestThreshold(100.0f);
        arrspawnListContainer8[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10);
        arrspawnListContainer8[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer8);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer9 = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer9[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.HALF_TROLLS, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer9);
        this.variantChance = 0.7f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK_NOSTEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT, 4.0f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST, 2.0f);
        this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK, 4.0f);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND, 3.0f);
        this.addBiomeVariant(LOTRBiomeVariant.DENSEFOREST_LEBETHRON);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_OLIVE, 0.1f);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 1, 60, Blocks.stone), 2.0f, 0, 64);
        this.decorator.treesPerChunk = 0;
        this.decorator.willowPerChunk = 2;
        this.decorator.logsPerChunk = 1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 4;
        this.decorator.waterlilyPerChunk = 2;
        this.decorator.generateAthelas = true;
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 100);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 200);
        this.decorator.addTree(LOTRTreeType.LEBETHRON, 100);
        this.decorator.addTree(LOTRTreeType.LEBETHRON_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.BIRCH, 150);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.CEDAR, 200);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 100);
        this.decorator.addTree(LOTRTreeType.CHESTNUT_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.PINE, 100);
        this.decorator.addTree(LOTRTreeType.CYPRESS, 100);
        this.decorator.addTree(LOTRTreeType.CYPRESS_LARGE, 10);
        this.decorator.addTree(LOTRTreeType.APPLE, 5);
        this.decorator.addTree(LOTRTreeType.PEAR, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE_LARGE, 5);
        this.decorator.addTree(LOTRTreeType.ALMOND, 15);
        this.registerForestFlowers();
        this.addFlower(LOTRMod.asphodel, 0, 10);
        this.addFlower((Block)Blocks.red_flower, 2, 5);
        this.decorator.generateOrcDungeon = true;
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedBeaconTower(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorRuins(), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedGondorTower(false), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorObelisk(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenIthilienHideout(false), 50);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 400);
        this.registerTravellingTrader(LOTREntityGaladhrimTrader.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GONDOR, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GONDOR_ITHILIEN, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GONDOR_DOL_AMROTH, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_BLACK_URUK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_NAN_UNGOL, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_WARG, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_HARNEDOR, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_COAST, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_CORSAIR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.RHUN, LOTREventSpawner.EventChance.UNCOMMON);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterIthilien;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.ITHILIEN;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.GONDOR.getSubregion("ithilien");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.GONDOR_MIX.setRepair(0.7f);
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int k1;
        int i1;
        int j1;
        super.decorate(world, random, i, k);
        if (random.nextInt(3) == 0) {
            i1 = i + random.nextInt(16) + 8;
            j1 = random.nextInt(128);
            k1 = k + random.nextInt(16) + 8;
            WorldGenDoublePlant doubleFlowerGen = new WorldGenDoublePlant();
            doubleFlowerGen.func_150548_a(0);
            doubleFlowerGen.generate(world, random, i1, j1, k1);
        }
        if (random.nextInt(24) == 0) {
            i1 = i + random.nextInt(16) + 8;
            j1 = random.nextInt(128);
            k1 = k + random.nextInt(16) + 8;
            new WorldGenFlowers(LOTRMod.pipeweedPlant).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.4f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.5f;
    }
}

