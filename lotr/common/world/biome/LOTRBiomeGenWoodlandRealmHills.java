/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenWoodlandRealm;
import lotr.common.world.feature.LOTRTreeType;

public class LOTRBiomeGenWoodlandRealmHills
extends LOTRBiomeGenWoodlandRealm {
    public LOTRBiomeGenWoodlandRealmHills(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.decorator.treesPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        this.decorator.addTree(LOTRTreeType.GREEN_OAK_EXTREME, 500);
    }
}

