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

public class LOTRGenLayerZoom
extends LOTRGenLayer {
    public LOTRGenLayerZoom(long l, LOTRGenLayer layer) {
        super(l);
        this.lotrParent = layer;
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int i1 = i >> 1;
        int k1 = k >> 1;
        int xSizeZoom = (xSize >> 1) + 2;
        int zSizeZoom = (zSize >> 1) + 2;
        int[] variants = this.lotrParent.getInts(world, i1, k1, xSizeZoom, zSizeZoom);
        int i2 = xSizeZoom - 1 << 1;
        int k2 = zSizeZoom - 1 << 1;
        int[] ints = LOTRIntCache.get(world).getIntArray(i2 * k2);
        for (int k3 = 0; k3 < zSizeZoom - 1; ++k3) {
            for (int i3 = 0; i3 < xSizeZoom - 1; ++i3) {
                this.initChunkSeed((long)(i3 + i1 << 1), (long)(k3 + k1 << 1));
                int int00 = variants[i3 + 0 + (k3 + 0) * xSizeZoom];
                int int01 = variants[i3 + 0 + (k3 + 1) * xSizeZoom];
                int int10 = variants[i3 + 1 + (k3 + 0) * xSizeZoom];
                int int11 = variants[i3 + 1 + (k3 + 1) * xSizeZoom];
                int index = (i3 << 1) + (k3 << 1) * i2;
                ints[index] = int00;
                ints[index + 1] = this.selectRandom(new int[]{int00, int10});
                ints[index + i2] = this.selectRandom(new int[]{int00, int01});
                ints[index + i2 + 1] = this.selectModeOrRandom(int00, int10, int01, int11);
            }
        }
        int[] zoomedInts = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k3 = 0; k3 < zSize; ++k3) {
            System.arraycopy(ints, (k3 + (k & 1)) * i2 + (i & 1), zoomedInts, k3 * xSize, xSize);
        }
        return zoomedInts;
    }

    public static LOTRGenLayer magnify(long seed, LOTRGenLayer source, int zooms) {
        LOTRGenLayer layer = source;
        for (int i = 0; i < zooms; ++i) {
            layer = new LOTRGenLayerZoom(seed + (long)i, layer);
        }
        return layer;
    }
}

