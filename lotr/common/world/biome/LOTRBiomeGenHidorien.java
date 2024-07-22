/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenUnchartedRhun;
import lotr.common.world.biome.variant.LOTRBiomeVariant;

public class LOTRBiomeGenHidorien
extends LOTRBiomeGenUnchartedRhun {
    public LOTRBiomeGenHidorien(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.decorator.treesPerChunk = 7;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 12;
        this.decorator.doubleGrassPerChunk = 4;
        this.biomeColors.setGrass(7448126);
        this.registerForestFlowers();
        this.decorator.clearVillages();
    }

    @Override
    public int spawnCountMultiplier() {
        return super.spawnCountMultiplier() * 2;
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterHildorien;
    }
}

