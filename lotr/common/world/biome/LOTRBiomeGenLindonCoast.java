/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenLindon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class LOTRBiomeGenLindonCoast
extends LOTRBiomeGenLindon {
    public LOTRBiomeGenLindonCoast(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.stone;
        this.topBlockMeta = 0;
        this.fillerBlock = this.topBlock;
        this.fillerBlockMeta = this.topBlockMeta;
        this.biomeTerrain.setXZScale(30.0);
        this.clearBiomeVariants();
        this.decorator.clearRandomStructures();
    }
}

