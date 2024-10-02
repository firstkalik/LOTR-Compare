/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.entity.animal.LOTREntityFrog;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenFarHarad;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenFarHaradSwamp
extends LOTRBiomeGenFarHarad {
    public LOTRBiomeGenFarHaradSwamp(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.clearBiomeVariants();
        this.variantChance = 1.0f;
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFrog.class, 8, 1, 3));
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_SWAMP);
        this.decorator.sandPerChunk = 0;
        this.decorator.quagmirePerChunk = 1;
        this.decorator.treesPerChunk = 0;
        this.decorator.vinesPerChunk = 20;
        this.decorator.logsPerChunk = 3;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 8;
        this.decorator.enableFern = true;
        this.decorator.mushroomsPerChunk = 3;
        this.decorator.waterlilyPerChunk = 3;
        this.decorator.canePerChunk = 10;
        this.decorator.reedPerChunk = 3;
        this.decorator.addTree(LOTRTreeType.OAK_SWAMP, 1000);
        this.registerSwampFlowers();
        this.biomeColors.setWater(5607038);
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.FAR_HARAD.getSubregion("swamp");
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.25f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }
}

