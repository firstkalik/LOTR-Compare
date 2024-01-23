/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungle;

public class LOTRBiomeGenFarHaradJungleLake
extends LOTRBiomeGenFarHaradJungle {
    public LOTRBiomeGenFarHaradJungleLake(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.decorator.sandPerChunk = 0;
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }
}

