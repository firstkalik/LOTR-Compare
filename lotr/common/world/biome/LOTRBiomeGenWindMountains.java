/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBlueDwarfMerchant;
import lotr.common.entity.npc.LOTREntityDorwinionMerchantElf;
import lotr.common.entity.npc.LOTREntityDorwinionMerchantMan;
import lotr.common.entity.npc.LOTREntityIronHillsMerchant;
import lotr.common.entity.npc.LOTREntityNearHaradMerchant;
import lotr.common.entity.npc.LOTREntityNomadMerchant;
import lotr.common.entity.npc.LOTREntityRedDwarfMerchant;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenMountainsideBush;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenDwarvenMineEntrance3;
import lotr.common.world.structure2.LOTRWorldGenDwarvenMineEntranceRuined3;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenWindDwarvenTower;
import lotr.common.world.structure2.LOTRWorldGenWindMountainsHouse;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenWindMountains
extends LOTRBiome {
    public LOTRBiomeGenWindMountains(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.npcSpawnList.clear();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LARCH, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_MAPLE, 0.3f);
        this.decorator.biomeGemFactor = 1.3f;
        this.decorator.flowersPerChunk = 1;
        this.decorator.doubleFlowersPerChunk = 0;
        this.decorator.grassPerChunk = 4;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.addTree(LOTRTreeType.SPRUCE, 400);
        this.decorator.addTree(LOTRTreeType.SPRUCE_THIN, 400);
        this.decorator.addTree(LOTRTreeType.SPRUCE_MEGA, 50);
        this.decorator.addTree(LOTRTreeType.SPRUCE_MEGA_THIN, 10);
        this.decorator.addTree(LOTRTreeType.LARCH, 500);
        this.decorator.addTree(LOTRTreeType.FIR, 500);
        this.decorator.addTree(LOTRTreeType.PINE, 500);
        this.decorator.addTree(LOTRTreeType.MAPLE, 300);
        this.registerTravellingTrader(LOTREntityIronHillsMerchant.class);
        this.registerTravellingTrader(LOTREntityRedDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityBlueDwarfMerchant.class);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityDorwinionMerchantElf.class);
        this.registerTravellingTrader(LOTREntityDorwinionMerchantMan.class);
        this.registerTravellingTrader(LOTREntityNearHaradMerchant.class);
        this.registerTravellingTrader(LOTREntityNomadMerchant.class);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND, 10)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 1)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 5)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        this.registerMountainsFlowers();
        this.decorator.addRandomStructure(new LOTRWorldGenWindDwarvenTower(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarvenMineEntranceRuined3(false), 150);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarvenMineEntrance3(false), 600);
        this.biomeColors.setSky(11653858);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.DWARVEN(1, 4), 500);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.AVARI_ELF, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.STONEFOOT, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.BLACKLOCK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DURMETH_WARG, LOTREventSpawner.EventChance.UNCOMMON);
        this.addFlower(LOTRMod.dwarfHerb, 0, 1);
        this.addFlower(LOTRMod.khamCrop, 0, 1);
        this.decorator.generateOrcDungeon = true;
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_COMMON);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 6, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 7, 32, Blocks.stone), 1.0f, 0, 100);
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(LOTRMod.rock, 8, 32, Blocks.stone), 1.0f, 0, 100);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterMountainsWind;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.WIND_MOUNTAINS;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.RHUN.getSubregion("windMountains");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
        int snowHeight = 150 - rockDepth;
        int stoneHeight = snowHeight - 40;
        for (int j = ySize - 1; j >= stoneHeight; --j) {
            int index = xzIndex * ySize + j;
            Block block = blocks[index];
            if (j >= snowHeight && block == this.topBlock) {
                blocks[index] = Blocks.snow;
                meta[index] = 0;
            } else if (block == this.topBlock || block == this.fillerBlock) {
                blocks[index] = Blocks.stone;
                meta[index] = 0;
            }
            block = blocks[index];
            if (block != Blocks.stone) continue;
            if (random.nextInt(6) == 0) {
                int h = 1 + random.nextInt(6);
                for (int j1 = j; j1 > j - h && j1 > 0; --j1) {
                    int indexH = xzIndex * ySize + j1;
                    if (blocks[indexH] != Blocks.stone) continue;
                    blocks[indexH] = Blocks.stained_hardened_clay;
                    meta[indexH] = 9;
                }
                continue;
            }
            if (random.nextInt(16) != 0) continue;
            blocks[index] = Blocks.clay;
            meta[index] = 0;
        }
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int l;
        int k1;
        int j1;
        int i1;
        super.decorate(world, random, i, k);
        for (l = 0; l < 3; ++l) {
            i1 = i + random.nextInt(16) + 8;
            j1 = MathHelper.getRandomIntegerInRange((Random)random, (int)70, (int)160);
            k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenMountainsideBush(LOTRMod.leaves5, 0).generate(world, random, i1, j1, k1);
        }
        for (l = 0; l < 4; ++l) {
            i1 = i + random.nextInt(16) + 8;
            j1 = 70 + random.nextInt(80);
            k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenWindMountainsHouse(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.0f;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.2f;
    }
}

