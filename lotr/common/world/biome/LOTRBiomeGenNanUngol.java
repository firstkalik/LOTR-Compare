/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.feature.LOTRWorldGenWebOfUngoliant;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenMordorSpiderPit;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenNanUngol
extends LOTRBiomeGenMordor {
    public LOTRBiomeGenNanUngol(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_BOMBARDIERS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_SPIDERS, 100), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORGUL_SPIDERS, 10)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        this.npcSpawnList.conquestGainRate = 0.5f;
        this.clearBiomeVariants();
        this.decorator.generateCobwebs = false;
        this.decorator.clearRandomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenMordorSpiderPit(false), 40);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterNanUngol;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.NAN_UNGOL;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.MORDOR.getSubregion("nanUngol");
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = random.nextInt(128);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenWebOfUngoliant(false, 64).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.75f;
    }
}

