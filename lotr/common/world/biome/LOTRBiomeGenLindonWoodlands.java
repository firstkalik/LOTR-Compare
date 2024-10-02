/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenLindon;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenLindonWoodlands
extends LOTRBiomeGenLindon {
    public LOTRBiomeGenLindonWoodlands(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 30, 4, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_FOREST);
        this.decorator.treesPerChunk = 6;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 3;
        this.registerForestFlowers();
    }
}

