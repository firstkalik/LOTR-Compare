/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
import lotr.common.entity.animal.LOTREntityDeer2;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditNorth;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityRedDwarfMerchant;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenDwarvenMineEntranceRuined2;
import lotr.common.world.structure2.LOTRWorldGenRedDwarvenTower;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsHouseWickedDwarf;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsSmithy;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenRedMountainsIronfist
extends LOTRBiome {
    public LOTRBiomeGenRedMountainsIronfist(int i, boolean major) {
        super(i, major);
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.decorator.clearTrees();
        this.setEnableSnow();
        this.topBlock = Blocks.snow;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 10, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer2.class, 2, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.IRONFIST, 10)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 3), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS_WARRIORS, 4), LOTRBiomeSpawnList.entry(LOTRSpawnList.MOUNTAIN_SNOW_TROLLS, 1)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer61 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.SNOW_TROLLS, 5)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer61);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 5)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.WICKED_DWARF_WARRIOR, 1)};
        this.npcSpawnList.newFactionList(20, 0.0f).add(arrspawnListContainer4);
        this.decorator.biomeOreFactor = 2.0f;
        this.decorator.biomeGemFactor = 1.8f;
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 6, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 7, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 8, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 4, 60, Blocks.stone), 12.0f, 0, 96);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.sarnlumin, 4), 8.0f, 0, 48);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreSilver, 4), 8.0f, 0, 48);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;
        this.decorator.lichenPerChunk = 0;
        this.decorator.lichenPerChunk2 = 0;
        this.decorator.doubleGrassPerChunk = 0;
        this.decorator.generateWater = true;
        this.decorator.generateLava = true;
        this.decorator.generateCobwebs = true;
        this.decorator.generateOrcDungeon = true;
        this.decorator.generateTrollHoard2 = true;
        this.registerMountainsFlowers();
        this.addFlower(LOTRMod.dwarfHerb, 0, 1);
        this.addFlower(LOTRMod.khamCrop, 0, 1);
        this.biomeColors.setSky(13541522);
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.setBanditEntityClass(LOTREntityBanditNorth.class);
        this.registerTravellingTrader(LOTREntityRedDwarfMerchant.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.STIFFBEARD, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.IRONFIST, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.AVARI_ELF, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH_WARG, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH, LOTREventSpawner.EventChance.COMMON);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.REDDWARVENSNOWY(1, 4), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenRedDwarvenTower(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarvenMineEntranceRuined2(true), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenRedMountainsSmithy(false), 150);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterRedMountainsSnow;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.NORTH_RED_MOUNTAINS1;
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
        return LOTRRoadType.REDDWARVEN;
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
            blocks[index] = Blocks.snow;
            meta[index] = 0;
        }
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 90 + random.nextInt(30);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenRedMountainsHouseWickedDwarf(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.1f;
    }
}

