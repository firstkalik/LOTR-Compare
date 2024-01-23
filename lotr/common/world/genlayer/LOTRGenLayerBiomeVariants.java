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

public class LOTRGenLayerBiomeVariants
extends LOTRGenLayer {
    public static int RANDOM_MAX = 10000;

    public LOTRGenLayerBiomeVariants(long l) {
        super(l);
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int[] ints = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k1 = 0; k1 < zSize; ++k1) {
            for (int i1 = 0; i1 < xSize; ++i1) {
                this.initChunkSeed((long)(i + i1), (long)(k + k1));
                ints[i1 + k1 * xSize] = this.nextInt(RANDOM_MAX);
            }
        }
        return ints;
    }
}

