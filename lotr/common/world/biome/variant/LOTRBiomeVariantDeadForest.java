/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome.variant;

import lotr.common.world.biome.variant.LOTRBiomeVariant;

public class LOTRBiomeVariantDeadForest
extends LOTRBiomeVariant {
    public LOTRBiomeVariantDeadForest(int i, String s) {
        super(i, s, LOTRBiomeVariant.VariantScale.ALL);
        this.setTemperatureRainfall(0.0f, -0.3f);
        this.setTrees(3.0f);
        this.setGrass(0.5f);
    }
}

