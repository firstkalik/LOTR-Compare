/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome.variant;

import lotr.common.world.biome.variant.LOTRBiomeVariant;

public class LOTRBiomeVariantForest
extends LOTRBiomeVariant {
    public LOTRBiomeVariantForest(int i, String s) {
        super(i, s, LOTRBiomeVariant.VariantScale.ALL);
        this.setTemperatureRainfall(0.0f, 0.3f);
        this.setTrees(8.0f);
        this.setGrass(2.0f);
    }
}

