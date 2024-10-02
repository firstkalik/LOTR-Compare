/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.ChunkCoordIntPair
 */
package lotr.common.world.village;

import java.util.HashMap;
import java.util.Map;
import lotr.common.world.village.LocationInfo;
import net.minecraft.world.ChunkCoordIntPair;

public class LOTRVillagePositionCache {
    public Map<ChunkCoordIntPair, LocationInfo> cacheMap = new HashMap<ChunkCoordIntPair, LocationInfo>();

    public void clearCache() {
        this.cacheMap.clear();
    }

    public ChunkCoordIntPair getChunkKey(int chunkX, int chunkZ) {
        return new ChunkCoordIntPair(chunkX, chunkZ);
    }

    public LocationInfo getLocationAt(int chunkX, int chunkZ) {
        return this.cacheMap.get((Object)this.getChunkKey(chunkX, chunkZ));
    }

    public LocationInfo markResult(int chunkX, int chunkZ, LocationInfo result) {
        if (this.cacheMap.size() >= 20000) {
            this.clearCache();
        }
        this.cacheMap.put(this.getChunkKey(chunkX, chunkZ), result);
        return result;
    }
}

