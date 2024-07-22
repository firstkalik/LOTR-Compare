/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenShire;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
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
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenWhiteDowns
extends LOTRBiomeGenShire {
    private WorldGenerator chalkBoulder = new LOTRWorldGenBoulder(LOTRMod.rock, 5, 1, 3);

    public LOTRBiomeGenWhiteDowns(int i, boolean major) {
        super(i, major);
        this.fillerBlock = LOTRMod.rock;
        this.fillerBlockMeta = 5;
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HOBBITS, 10).setSpawnChance(100), LOTRBiomeSpawnList.entry(LOTRSpawnList.HOBBITS, 1).setConquestOnly()};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 3).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 3).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ISENGARD_SNAGA, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 5)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.npcSpawnList.conquestGainRate = 0.2f;
        this.clearBiomeVariants();
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.biomeColors.resetGrass();
        this.decorator.clearRandomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitHole(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitBurrow(false), 150);
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitHouse(false), 150);
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitTavern(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitWindmill(false), 600);
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitFarm(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenHobbitPicnicBench(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 4), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.ARNOR(1, 4), 1500);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterWhiteDowns;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.SHIRE;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.SHIRE.getSubregion("whiteDowns");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        if (random.nextInt(80) == 0) {
            for (int l = 0; l < 3; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.chalkBoulder.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
    }

    @Override
    public int spawnCountMultiplier() {
        return 5;
    }
}

