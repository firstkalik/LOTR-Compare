/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome.variant;

import lotr.common.world.biome.variant.LOTRBiomeVariant;

public class LOTRBiomeVariantDenseForest
extends LOTRBiomeVariant {
    public LOTRBiomeVariantDenseForest(int i, String s) {
        super(i, s, LOTRBiomeVariant.VariantScale.LARGE);
        this.setTemperatureRainfall(0.1f, 0.3f);
        this.setHeight(0.5f, 2.0f);
        this.setTrees(8.0f);
        this.setGrass(2.0f);
    }
}

