/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.world.genlayer;

import lotr.common.world.genlayer.LOTRGenLayer;
import lotr.common.world.genlayer.LOTRIntCache;
import net.minecraft.world.World;

public class LOTRGenLayerClassicRemoveOcean
extends LOTRGenLayer {
    public LOTRGenLayerClassicRemoveOcean(long l, LOTRGenLayer layer) {
        super(l);
        this.lotrParent = layer;
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int[] oceans = this.lotrParent.getInts(world, i, k, xSize, zSize);
        int[] ints = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k1 = 0; k1 < zSize; ++k1) {
            for (int i1 = 0; i1 < xSize; ++i1) {
                this.initChunkSeed((long)(i + i1), (long)(k + k1));
                int isOcean = oceans[i1 + k1 * xSize];
                if (Math.abs(i + i1) <= 1 && Math.abs(k + k1) <= 1) {
                    isOcean = 0;
                }
                ints[i1 + k1 * xSize] = isOcean;
            }
        }
        return ints;
    }
}

