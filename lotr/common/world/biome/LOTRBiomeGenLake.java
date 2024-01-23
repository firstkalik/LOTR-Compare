/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import net.minecraft.block.Block;

public class LOTRBiomeGenLake
extends LOTRBiome {
    public LOTRBiomeGenLake(int i, boolean major) {
        super(i, major);
        this.setMinMaxHeight(-0.5f, 0.2f);
        this.spawnableCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.npcSpawnList.clear();
        this.decorator.sandPerChunk = 0;
    }

    public LOTRBiomeGenLake setLakeBlock(Block block) {
        this.topBlock = block;
        this.fillerBlock = block;
        return this;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.SEA.getSubregion("lake");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }
}

