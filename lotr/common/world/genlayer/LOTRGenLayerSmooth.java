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

public class LOTRGenLayerSmooth
extends LOTRGenLayer {
    public LOTRGenLayerSmooth(long l, LOTRGenLayer layer) {
        super(l);
        this.lotrParent = layer;
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int i1 = i - 1;
        int k1 = k - 1;
        int xSizeP = xSize + 2;
        int zSizeP = zSize + 2;
        int[] biomes = this.lotrParent.getInts(world, i1, k1, xSizeP, zSizeP);
        int[] ints = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k2 = 0; k2 < zSize; ++k2) {
            for (int i2 = 0; i2 < xSize; ++i2) {
                int centre = biomes[i2 + 1 + (k2 + 1) * xSizeP];
                int xn = biomes[i2 + 0 + (k2 + 1) * xSizeP];
                int xp = biomes[i2 + 2 + (k2 + 1) * xSizeP];
                int zn = biomes[i2 + 1 + (k2 + 0) * xSizeP];
                int zp = biomes[i2 + 1 + (k2 + 2) * xSizeP];
                if (xn == xp && zn == zp) {
                    this.initChunkSeed((long)(i2 + i), (long)(k2 + k));
                    centre = this.nextInt(2) == 0 ? xn : zn;
                } else {
                    if (xn == xp) {
                        centre = xn;
                    }
                    if (zn == zp) {
                        centre = zn;
                    }
                }
                ints[i2 + k2 * xSize] = centre;
            }
        }
        return ints;
    }
}

