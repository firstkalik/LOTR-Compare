/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.structure.LOTRWorldGenMordorTower;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenMordorMountains
extends LOTRBiomeGenMordor {
    public LOTRBiomeGenMordorMountains(int i, boolean major) {
        super(i, major);
        this.decorator.clearRandomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenMordorTower(false), 400);
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return null;
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return null;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.MORDOR.getSubregion("mountains");
    }
}

