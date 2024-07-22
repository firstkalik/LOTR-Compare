/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.world.World
 */
package lotr.common.world.genlayer;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lotr.common.LOTRDimension;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.genlayer.LOTRGenLayer;
import lotr.common.world.genlayer.LOTRIntCache;
import net.minecraft.world.World;

public class LOTRGenLayerRemoveMapRivers
extends LOTRGenLayer {
    private static int MAX_PIXEL_RANGE = 4;
    private LOTRDimension dimension;

    public LOTRGenLayerRemoveMapRivers(long l, LOTRGenLayer biomes, LOTRDimension dim) {
        super(l);
        this.lotrParent = biomes;
        this.dimension = dim;
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int maxRange = MAX_PIXEL_RANGE;
        int[] biomes = this.lotrParent.getInts(world, i - maxRange, k - maxRange, xSize + maxRange * 2, zSize + maxRange * 2);
        int[] ints = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k1 = 0; k1 < zSize; ++k1) {
            for (int i1 = 0; i1 < xSize; ++i1) {
                this.initChunkSeed((long)(i + i1), (long)(k + k1));
                int biomeID = biomes[i1 + maxRange + (k1 + maxRange) * (xSize + maxRange * 2)];
                if (biomeID == LOTRBiome.river.biomeID) {
                    int replaceID = -1;
                    for (int range = 1; range <= maxRange; ++range) {
                        int id;
                        int count;
                        HashMap viableBiomes = new HashMap();
                        HashMap<Integer, Integer> viableBiomesWateryAdjacent = new HashMap<Integer, Integer>();
                        for (int k2 = k1 - range; k2 <= k1 + range; ++k2) {
                            for (int i2 = i1 - range; i2 <= i1 + range; ++i2) {
                                int subBiomeID;
                                LOTRBiome subBiome;
                                if (Math.abs(i2 - i1) != range && Math.abs(k2 - k1) != range || (subBiome = this.dimension.biomeList[subBiomeID = biomes[i2 + maxRange + (k2 + maxRange) * (xSize + maxRange * 2)]]) == LOTRBiome.river) continue;
                                Object wateryAdjacent = subBiome.isWateryBiome() && range == 1;
                                HashMap<Integer, Integer> srcMap = wateryAdjacent != false ? viableBiomesWateryAdjacent : viableBiomes;
                                int count2 = 0;
                                if (srcMap.containsKey(subBiomeID)) {
                                    count2 = (Integer)srcMap.get(subBiomeID);
                                }
                                srcMap.put(subBiomeID, ++count2);
                            }
                        }
                        HashMap priorityMap = viableBiomes;
                        if (!viableBiomesWateryAdjacent.isEmpty()) {
                            priorityMap = viableBiomesWateryAdjacent;
                        }
                        if (priorityMap.isEmpty()) continue;
                        ArrayList<Integer> maxCountBiomes = new ArrayList<Integer>();
                        int maxCount = 0;
                        for (Map.Entry e : priorityMap.entrySet()) {
                            id = (Integer)e.getKey();
                            count = (Integer)e.getValue();
                            if (count <= maxCount) continue;
                            maxCount = count;
                        }
                        for (Map.Entry e : priorityMap.entrySet()) {
                            id = (Integer)e.getKey();
                            count = (Integer)e.getValue();
                            if (count != maxCount) continue;
                            maxCountBiomes.add(id);
                        }
                        replaceID = (Integer)maxCountBiomes.get(this.nextInt(maxCountBiomes.size()));
                        break;
                    }
                    if (replaceID == -1) {
                        FMLLog.warning((String)"WARNING! LOTR map generation failed to replace map river at %d, %d", (Object[])new Object[]{i, k});
                        ints[i1 + k1 * xSize] = 0;
                        continue;
                    }
                    ints[i1 + k1 * xSize] = replaceID;
                    continue;
                }
                ints[i1 + k1 * xSize] = biomeID;
            }
        }
        return ints;
    }
}

