/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenRhunLand;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;

public class LOTRBiomeGenRhunIsland
extends LOTRBiomeGenRhunLand {
    public LOTRBiomeGenRhunIsland(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK);
        this.decorator.clearRandomStructures();
        this.decorator.clearVillages();
        this.clearTravellingTraders();
        this.invasionSpawns.clearInvasions();
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterRhunIsland;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.TOL_RHUNAER;
    }
}

