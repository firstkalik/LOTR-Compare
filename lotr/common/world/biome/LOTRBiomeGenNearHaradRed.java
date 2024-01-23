/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenNearHaradRed
extends LOTRBiomeGenNearHarad {
    public LOTRBiomeGenNearHaradRed(int i, boolean major) {
        super(i, major);
        this.setDisableRain();
        this.topBlock = Blocks.sand;
        this.topBlockMeta = 1;
        this.fillerBlock = Blocks.sand;
        this.fillerBlockMeta = 1;
        this.decorator.clearRandomStructures();
        this.decorator.clearVillages();
    }
}

