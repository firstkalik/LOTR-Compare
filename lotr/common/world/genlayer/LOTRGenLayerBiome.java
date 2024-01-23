/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.genlayer;

import java.util.Arrays;
import lotr.common.world.genlayer.LOTRGenLayer;
import lotr.common.world.genlayer.LOTRIntCache;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRGenLayerBiome
extends LOTRGenLayer {
    private BiomeGenBase theBiome;

    public LOTRGenLayerBiome(BiomeGenBase biome) {
        super(0L);
        this.theBiome = biome;
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int[] ints = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        Arrays.fill(ints, this.theBiome.biomeID);
        return ints;
    }
}

