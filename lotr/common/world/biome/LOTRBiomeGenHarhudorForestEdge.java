/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenHarhudorForest;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;

public class LOTRBiomeGenHarhudorForestEdge
extends LOTRBiomeGenHarhudorForest {
    public LOTRBiomeGenHarhudorForestEdge(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.addBiomeVariant(LOTRBiomeVariant.FLOWERS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.decorator.treesPerChunk = 8;
        this.decorator.vinesPerChunk = 20;
        this.decorator.melonPerChunk = 0.03f;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.JUNGLE, 200);
        this.decorator.addTree(LOTRTreeType.JUNGLE_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.MAHOGANY, 50);
        this.decorator.addTree(LOTRTreeType.JUNGLE_SHRUB, 1000);
        this.decorator.addTree(LOTRTreeType.MANGO, 5);
        this.decorator.addTree(LOTRTreeType.BANANA, 20);
        this.biomeColors.resetSky();
        this.biomeColors.resetFog();
        this.invasionSpawns.clearInvasions();
    }

    @Override
    public boolean hasJungleLakes() {
        return false;
    }

    @Override
    public boolean isMuddy() {
        return false;
    }
}

