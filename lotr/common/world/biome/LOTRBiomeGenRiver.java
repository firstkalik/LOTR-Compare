/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;

public class LOTRBiomeGenRiver
extends LOTRBiome {
    public LOTRBiomeGenRiver(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.npcSpawnList.clear();
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
        this.invasionSpawns.clearInvasions();
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return null;
    }

    @Override
    public boolean isRiver() {
        return true;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.5f;
    }
}

