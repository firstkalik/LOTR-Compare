/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenGondorRuin;
import lotr.common.world.structure2.LOTRWorldGenBurntHouse;
import lotr.common.world.structure2.LOTRWorldGenGondorObelisk;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenEnedwaith
extends LOTRBiome {
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 4);

    public LOTRBiomeGenEnedwaith(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 4, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDINGS, 1).setSpawnChance(10000), LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDING_WARRIORS, 1).setSpawnChance(10000), LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDINGS, 10).setConquestOnly(), LOTRBiomeSpawnList.entry(LOTRSpawnList.DUNLENDING_WARRIORS, 10).setConquestOnly()};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ISENGARD_SNAGA, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ROHIRRIM_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK_SPRUCE);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND, 3.0f);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_SCRUBLAND, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.MOUNTAIN);
        this.addBiomeVariant(LOTRBiomeVariant.WASTELAND);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LARCH, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE, 0.1f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_ASPEN, 0.1f);
        this.decorator.treesPerChunk = 0;
        this.decorator.setTreeCluster(8, 30);
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 4;
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 300);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 1000);
        this.decorator.addTree(LOTRTreeType.CHESTNUT, 1000);
        this.registerPlainsFlowers();
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenBurntHouse(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorObelisk(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorRuin(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 5), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 400);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.RANGER_NORTH, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.DUNLAND, LOTREventSpawner.EventChance.UNCOMMON);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterEnedwaith;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.ENEDWAITH;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ENEDWAITH.getSubregion("enedwaith");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int i1;
        super.decorate(world, random, i, k);
        if (random.nextInt(24) == 0) {
            int boulders = 1 + random.nextInt(6);
            for (int l = 0; l < boulders; ++l) {
                i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
        for (int l = 0; l < 2; ++l) {
            int k1;
            i1 = i + random.nextInt(16) + 8;
            int j1 = world.getHeightValue(i1, k1 = k + random.nextInt(16) + 8);
            if (j1 <= 75) continue;
            this.decorator.genTree(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.05f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.1f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }
}

