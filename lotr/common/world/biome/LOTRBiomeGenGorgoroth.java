/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnList;

public class LOTRBiomeGenGorgoroth
extends LOTRBiomeGenMordor {
    public LOTRBiomeGenGorgoroth(int i, boolean major) {
        super(i, major);
        this.enableMordorBoulders = false;
        this.decorator.grassPerChunk = 0;
        this.biomeColors.setSky(5843484);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_BOMBARDIERS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 30), LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 7), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_TROLL, 10)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.WICKED_DWARVES, 10)};
        this.npcSpawnList.newFactionList(1).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ROHIRRIM_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 3)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.npcSpawnList.conquestGainRate = 0.5f;
    }
}

