/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 */
package lotr.common.world;

import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.world.LOTRChunkProvider;
import lotr.common.world.LOTRWorldProvider;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class LOTRWorldProviderMiddleEarth
extends LOTRWorldProvider {
    @Override
    public LOTRDimension getLOTRDimension() {
        return LOTRDimension.MIDDLE_EARTH;
    }

    public IChunkProvider createChunkGenerator() {
        return new LOTRChunkProvider(this.worldObj, this.worldObj.getSeed());
    }

    public ChunkCoordinates getSpawnPoint() {
        return new ChunkCoordinates(LOTRLevelData.middleEarthPortalX, LOTRLevelData.middleEarthPortalY, LOTRLevelData.middleEarthPortalZ);
    }

    public void setSpawnPoint(int i, int j, int k) {
    }

    public void setRingPortalLocation(int i, int j, int k) {
        LOTRLevelData.markMiddleEarthPortalLocation(i, j, k);
    }
}

