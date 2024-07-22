/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenObsidianGravel2
extends WorldGenerator {
    private Block genBlock = LOTRMod.susSand;
    private int genMeta = 0;

    public boolean generate(World world, Random random, int i, int j, int k) {
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
        Block below = world.getBlock(i, j - 1, k);
        if (below != biome.topBlock && below != biome.fillerBlock && below != Blocks.sand) {
            return false;
        }
        int numBlocks = MathHelper.getRandomIntegerInRange((Random)random, (int)6, (int)16);
        float angle = random.nextFloat() * 3.1415927f;
        float sin = MathHelper.sin((float)angle);
        float cos = MathHelper.sin((float)angle);
        float div = 8.0f;
        double xMin = (float)i - sin * (float)numBlocks / div;
        double xMax = (float)i + sin * (float)numBlocks / div;
        double zMin = (float)k - cos * (float)numBlocks / div;
        double zMax = (float)k + cos * (float)numBlocks / div;
        double yMin = j + random.nextInt(2) - 2;
        double yMax = j + random.nextInt(2) - 2;
        for (int l = 0; l <= numBlocks; ++l) {
            float lerp = (float)l / (float)numBlocks;
            double xLerp = xMin + (xMax - xMin) * (double)lerp;
            double yLerp = yMin + (yMax - yMin) * (double)lerp;
            double zLerp = zMin + (zMax - zMin) * (double)lerp;
            double d9 = random.nextDouble() * (double)numBlocks / 16.0;
            double d10 = (double)(MathHelper.sin((float)((float)l * 3.1415927f / (float)numBlocks)) + 1.0f) * d9 + 1.0;
            double d11 = (double)(MathHelper.sin((float)((float)l * 3.1415927f / (float)numBlocks)) + 1.0f) * d9 + 1.0;
            int i1 = MathHelper.floor_double((double)(xLerp - d10 / 2.0));
            int j1 = MathHelper.floor_double((double)(yLerp - d11 / 1.0));
            int k1 = MathHelper.floor_double((double)(zLerp - d10 / 2.0));
            int l1 = MathHelper.floor_double((double)(xLerp + d10 / 2.0));
            int i2 = MathHelper.floor_double((double)(yLerp + d11 / 1.0));
            int j2 = MathHelper.floor_double((double)(zLerp + d10 / 2.0));
            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double)k2 + 0.5 - xLerp) / (d10 / 2.0);
                if (d12 * d12 >= 1.0) continue;
                for (int l2 = j1; l2 <= i2; ++l2) {
                    double d13 = ((double)l2 + 0.5 - yLerp) / (d11 / 2.0);
                    if (d12 * d12 + d13 * d13 >= 1.0) continue;
                    for (int i3 = k1; i3 <= j2; ++i3) {
                        double d14 = ((double)i3 + 0.5 - zLerp) / (d10 / 2.0);
                        if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0 || !this.canReplace(world, k2, l2, i3)) continue;
                        world.setBlock(k2, l2, i3, this.genBlock, this.genMeta, 2);
                    }
                }
            }
        }
        return true;
    }

    private boolean canReplace(World world, int i, int j, int k) {
        Block block = world.getBlock(i, j, k);
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
        return block == biome.topBlock || block == biome.fillerBlock || block == Blocks.sand || block.isReplaceable((IBlockAccess)world, i, j, k);
    }
}

