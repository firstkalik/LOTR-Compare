/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenMordor;

public class LOTRBiomeGenGorgoroth
extends LOTRBiomeGenMordor {
    public LOTRBiomeGenGorgoroth(int i, boolean major) {
        super(i, major);
        this.enableMordorBoulders = false;
        this.decorator.grassPerChunk = 0;
        this.biomeColors.setSky(5843484);
    }
}

