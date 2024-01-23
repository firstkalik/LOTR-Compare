/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.entity.animal.LOTREntitySeagull;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenOcean;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenBeach
extends LOTRBiomeGenOcean {
    public LOTRBiomeGenBeach(int i, boolean major) {
        super(i, major);
        this.setMinMaxHeight(0.1f, 0.0f);
        this.setTemperatureRainfall(0.8f, 0.4f);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntitySeagull.class, 20, 4, 4));
    }

    public LOTRBiomeGenBeach setBeachBlock(Block block, int meta) {
        this.topBlock = block;
        this.topBlockMeta = meta;
        this.fillerBlock = block;
        this.fillerBlockMeta = meta;
        return this;
    }
}

