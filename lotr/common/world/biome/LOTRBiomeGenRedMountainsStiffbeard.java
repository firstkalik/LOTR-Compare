/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
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
import lotr.common.entity.animal.LOTREntityRam;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditNorth;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityNearHaradMerchant;
import lotr.common.entity.npc.LOTREntityNomadMerchant;
import lotr.common.entity.npc.LOTREntityPallando;
import lotr.common.entity.npc.LOTREntityRedDwarfMerchant;
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
import lotr.common.world.structure2.LOTRWorldGenDwarvenMineEntrance2;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsHouseStiffbeard;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsSmithy;
import lotr.common.world.structure2.LOTRWorldGenRuinedRedDwarvenTower;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenRedMountainsStiffbeard
extends LOTRBiome {
    public LOTRBiomeGenRedMountainsStiffbeard(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 10, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 2, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 4, 1, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityRam.class, 50, 4, 4));
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_SPRUCE, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK_SPRUCE, 0.2f);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.STIFFBEARD, 20)};
        this.npcSpawnList.newFactionList(600, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.CAVE, 4), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS_WARRIORS, 2)};
        this.npcSpawnList.newFactionList(50, 0.0f).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer1 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 1)};
        this.npcSpawnList.newFactionList(30, 0.0f).add(arrspawnListContainer1);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer61 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.CAVE_SPIDERS, 8)};
        this.npcSpawnList.newFactionList(110, 0.0f).add(arrspawnListContainer61);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer611 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLINGS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer611);
        this.decorator.biomeOreFactor = 2.0f;
        this.decorator.biomeGemFactor = 1.8f;
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 4, 60, Blocks.stone), 12.0f, 0, 96);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.sarnlumin, 4), 8.0f, 0, 48);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreSilver, 4), 8.0f, 0, 48);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 6, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 7, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 8, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.treesPerChunk = 1;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 4;
        this.decorator.lichenPerChunk = 2;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.addTree(LOTRTreeType.OAK, 300);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 500);
        this.decorator.addTree(LOTRTreeType.LARCH, 300);
        this.decorator.addTree(LOTRTreeType.MAPLE, 300);
        this.decorator.addTree(LOTRTreeType.MAPLE_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.FIR, 500);
        this.decorator.addTree(LOTRTreeType.PINE, 500);
        this.registerMountainsFlowers();
        this.addFlower(LOTRMod.dwarfHerb, 0, 1);
        this.addFlower(LOTRMod.khamCrop, 0, 1);
        this.biomeColors.setSky(13541522);
        this.decorator.generateWater = true;
        this.decorator.generateLava = true;
        this.decorator.generateCobwebs = true;
        this.decorator.generateOrcDungeon = false;
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityNearHaradMerchant.class);
        this.registerTravellingTrader(LOTREntityNomadMerchant.class);
        this.registerTravellingTrader(LOTREntityRedDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityPallando.class);
        this.setBanditEntityClass(LOTREntityBanditNorth.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH_URUK, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.AVARI_ELF, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.IRONFIST, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH_WARG, LOTREventSpawner.EventChance.COMMON);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.REDDWARVENMOSSY(1, 4), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenRedMountainsSmithy(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarvenMineEntrance2(false), 700);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedRedDwarvenTower(false), 300);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterStiffbeard;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.NORTH_RED_MOUNTAINS;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.DWARVEN.getSubregion("redMountains");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.REDDWARVEN_RUINED;
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
        int stoneHeight = 110 - rockDepth;
        int sandHeight = stoneHeight - 6;
        for (int j = ySize - 1; j >= sandHeight; --j) {
            int index = xzIndex * ySize + j;
            Block block = blocks[index];
            if (block != this.topBlock && block != this.fillerBlock) continue;
            if (j >= stoneHeight) {
                blocks[index] = LOTRMod.rock;
                meta[index] = 4;
                continue;
            }
            blocks[index] = Blocks.sand;
            meta[index] = 1;
        }
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 110 + random.nextInt(20);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenRedMountainsHouseStiffbeard(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.2f;
    }
}

