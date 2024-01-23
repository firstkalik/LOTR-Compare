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

public class LOTRGenLayerZoomVoronoi
extends LOTRGenLayer {
    private int zoomScale = 1024;
    private double zoomDivisor = (double)this.zoomScale - 0.5;

    public LOTRGenLayerZoomVoronoi(long seed, LOTRGenLayer layer) {
        super(seed);
        this.lotrParent = layer;
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int i1 = (i -= 2) >> 2;
        int k1 = (k -= 2) >> 2;
        int xSizeZoom = (xSize >> 2) + 2;
        int zSizeZoom = (zSize >> 2) + 2;
        int[] variants = this.lotrParent.getInts(world, i1, k1, xSizeZoom, zSizeZoom);
        int i2 = xSizeZoom - 1 << 2;
        int k2 = zSizeZoom - 1 << 2;
        int[] ints = LOTRIntCache.get(world).getIntArray(i2 * k2);
        for (int k3 = 0; k3 < zSizeZoom - 1; ++k3) {
            int i3;
            int int00 = variants[i3 + 0 + (k3 + 0) * xSizeZoom];
            int int01 = variants[i3 + 0 + (k3 + 1) * xSizeZoom];
            for (i3 = 0; i3 < xSizeZoom - 1; ++i3) {
                double d0 = 3.6;
                this.initChunkSeed((long)(i3 + i1 << 2), (long)(k3 + k1 << 2));
                double d00_a = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0;
                double d00_b = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0;
                this.initChunkSeed((long)(i3 + i1 + 1 << 2), (long)(k3 + k1 << 2));
                double d10_a = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0 + 4.0;
                double d10_b = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0;
                this.initChunkSeed((long)(i3 + i1 << 2), (long)(k3 + k1 + 1 << 2));
                double d01_a = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0;
                double d01_b = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0 + 4.0;
                this.initChunkSeed((long)(i3 + i1 + 1 << 2), (long)(k3 + k1 + 1 << 2));
                double d11_a = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0 + 4.0;
                double d11_b = (double)this.nextInt(this.zoomScale) / this.zoomDivisor * d0 + 4.0;
                int int10 = variants[i3 + 1 + (k3 + 0) * xSizeZoom];
                int int11 = variants[i3 + 1 + (k3 + 1) * xSizeZoom];
                for (int k4 = 0; k4 < 4; ++k4) {
                    int index = ((k3 << 2) + k4) * i2 + (i3 << 2);
                    for (int i4 = 0; i4 < 4; ++i4) {
                        double d00 = ((double)k4 - d00_b) * ((double)k4 - d00_b) + ((double)i4 - d00_a) * ((double)i4 - d00_a);
                        double d10 = ((double)k4 - d10_b) * ((double)k4 - d10_b) + ((double)i4 - d10_a) * ((double)i4 - d10_a);
                        double d01 = ((double)k4 - d01_b) * ((double)k4 - d01_b) + ((double)i4 - d01_a) * ((double)i4 - d01_a);
                        double d11 = ((double)k4 - d11_b) * ((double)k4 - d11_b) + ((double)i4 - d11_a) * ((double)i4 - d11_a);
                        if (d00 < d10 && d00 < d01 && d00 < d11) {
                            ints[index] = int00;
                            ++index;
                            continue;
                        }
                        if (d10 < d00 && d10 < d01 && d10 < d11) {
                            ints[index] = int10;
                            ++index;
                            continue;
                        }
                        if (d01 < d00 && d01 < d10 && d01 < d11) {
                            ints[index] = int01;
                            ++index;
                            continue;
                        }
                        ints[index] = int11;
                        ++index;
                    }
                }
                int00 = int10;
                int01 = int11;
            }
        }
        int[] zoomedInts = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k3 = 0; k3 < zSize; ++k3) {
            System.arraycopy(ints, (k3 + (k & 3)) * i2 + (i & 3), zoomedInts, k3 * xSize, xSize);
        }
        return zoomedInts;
    }
}

