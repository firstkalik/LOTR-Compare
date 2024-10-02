/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiomeGenKhand;
import lotr.common.world.biome.variant.LOTRBiomeVariant;

public class LOTRBiomeGenKhandHills
extends LOTRBiomeGenKhand {
    public LOTRBiomeGenKhandHills(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
    }
}

