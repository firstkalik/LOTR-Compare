/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.world.genlayer;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.genlayer.LOTRGenLayer;
import lotr.common.world.genlayer.LOTRIntCache;
import net.minecraft.world.World;

public class LOTRGenLayerBiomeSubtypes
extends LOTRGenLayer {
    private LOTRGenLayer biomeLayer;
    private LOTRGenLayer variantsLayer;

    public LOTRGenLayerBiomeSubtypes(long l, LOTRGenLayer biomes, LOTRGenLayer subtypes) {
        super(l);
        this.biomeLayer = biomes;
        this.variantsLayer = subtypes;
    }

    @Override
    public void initWorldGenSeed(long l) {
        this.biomeLayer.initWorldGenSeed(l);
        this.variantsLayer.initWorldGenSeed(l);
        super.initWorldGenSeed(l);
    }

    @Override
    public int[] getInts(World world, int i, int k, int xSize, int zSize) {
        int[] biomes = this.biomeLayer.getInts(world, i, k, xSize, zSize);
        int[] variants = this.variantsLayer.getInts(world, i, k, xSize, zSize);
        int[] ints = LOTRIntCache.get(world).getIntArray(xSize * zSize);
        for (int k1 = 0; k1 < zSize; ++k1) {
            for (int i1 = 0; i1 < xSize; ++i1) {
                this.initChunkSeed((long)(i + i1), (long)(k + k1));
                int biome = biomes[i1 + k1 * xSize];
                int variant = variants[i1 + k1 * xSize];
                int newBiome = biome;
                if (biome == LOTRBiome.shire.biomeID && variant < 15 && variant != 0) {
                    newBiome = LOTRBiome.shireWoodlands.biomeID;
                } else if (biome == LOTRBiome.forodwaithMountains.biomeID && variant < 5) {
                    newBiome = LOTRBiome.forodwaithGlacier.biomeID;
                } else if (biome == LOTRBiome.redMountains4.biomeID && variant < 5) {
                    newBiome = LOTRBiome.redGlacier.biomeID;
                } else if (biome == LOTRBiome.farHarad.biomeID && variant < 20) {
                    newBiome = LOTRBiome.farHaradForest.biomeID;
                } else if (biome == LOTRBiome.farHaradJungle.biomeID && variant < 15) {
                    newBiome = LOTRBiome.tauredainClearing.biomeID;
                } else if (biome == LOTRBiome.pertorogwaith.biomeID && variant < 15) {
                    newBiome = LOTRBiome.farHaradVolcano.biomeID;
                } else if (biome == LOTRBiome.ocean.biomeID && variant < 2) {
                    newBiome = LOTRBiome.island.biomeID;
                }
                ints[i1 + k1 * xSize] = newBiome != biome ? newBiome : biome;
            }
        }
        return ints;
    }
}

