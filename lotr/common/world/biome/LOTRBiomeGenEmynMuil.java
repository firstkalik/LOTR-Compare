/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
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
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenGrassPatch;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenEmynMuil
extends LOTRBiome {
    private WorldGenerator boulderGenSmall = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 4);
    private WorldGenerator boulderGenLarge = new LOTRWorldGenBoulder(Blocks.stone, 0, 5, 8).setHeightCheck(6);
    private WorldGenerator clayBoulderGenSmall = new LOTRWorldGenBoulder(Blocks.hardened_clay, 0, 1, 4);
    private WorldGenerator clayBoulderGenLarge = new LOTRWorldGenBoulder(Blocks.hardened_clay, 0, 5, 10).setHeightCheck(6);
    private WorldGenerator grassPatchGen = new LOTRWorldGenGrassPatch();

    public LOTRBiomeGenEmynMuil(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;
        this.spawnableCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 1)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 2;
        this.registerMountainsFlowers();
        this.biomeColors.setGrass(9539937);
        this.biomeColors.setSky(10000788);
        this.decorator.generateOrcDungeon = true;
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_WARG, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterEmynMuil;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.EMYN_MUIL;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.BROWN_LANDS.getSubregion("emynMuil");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int k1;
        int l;
        int i1;
        super.decorate(world, random, i, k);
        for (l = 0; l < 20; ++l) {
            i1 = i + random.nextInt(16) + 8;
            k1 = k + random.nextInt(16) + 8;
            if (random.nextInt(5) == 0) {
                this.clayBoulderGenSmall.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
                continue;
            }
            this.boulderGenSmall.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
        }
        for (l = 0; l < 20; ++l) {
            i1 = i + random.nextInt(16) + 8;
            k1 = k + random.nextInt(16) + 8;
            if (random.nextInt(5) == 0) {
                this.clayBoulderGenLarge.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
                continue;
            }
            this.boulderGenLarge.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
        }
        for (l = 0; l < 10; ++l) {
            Block block = Blocks.stone;
            if (random.nextInt(5) == 0) {
                block = Blocks.hardened_clay;
            }
            for (int l1 = 0; l1 < 10; ++l1) {
                int k12;
                int j1;
                int i12 = i + random.nextInt(16) + 8;
                if (world.getBlock(i12, (j1 = world.getHeightValue(i12, k12 = k + random.nextInt(16) + 8)) - 1, k12) != block) continue;
                int height = j1 + random.nextInt(4);
                for (int j2 = j1; j2 < height && !LOTRMod.isOpaque(world, i12, j2, k12); ++j2) {
                    world.setBlock(i12, j2, k12, block, 0, 3);
                }
            }
        }
        for (l = 0; l < 3; ++l) {
            i1 = i + random.nextInt(16) + 8;
            k1 = k + random.nextInt(16) + 8;
            this.grassPatchGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
        }
    }
}

