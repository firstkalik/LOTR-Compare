/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.world.biome.LOTRBiomeGenForodwaith;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRBiomeGenForodwaithMountains
extends LOTRBiomeGenForodwaith {
    public LOTRBiomeGenForodwaithMountains(int i, boolean major) {
        super(i, major);
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
        int snowHeight = 100 - rockDepth;
        int stoneHeight = snowHeight - 30;
        for (int j = ySize - 1; j >= stoneHeight; --j) {
            int index = xzIndex * ySize + j;
            Block block = blocks[index];
            if (j >= snowHeight && block == this.topBlock) {
                blocks[index] = Blocks.snow;
                meta[index] = 0;
                continue;
            }
            if (block != this.topBlock && block != this.fillerBlock) continue;
            blocks[index] = Blocks.stone;
            meta[index] = 0;
        }
    }
}

