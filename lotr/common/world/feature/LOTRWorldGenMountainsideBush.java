/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenMountainsideBush
extends WorldGenerator {
    private Block leafBlock;
    private int leafMeta;

    public LOTRWorldGenMountainsideBush(Block block, int meta) {
        this.leafBlock = block;
        this.leafMeta = meta;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 64; ++l) {
            int k1;
            int j1;
            int i1 = i + MathHelper.getRandomIntegerInRange((Random)random, (int)-2, (int)2);
            if (!world.isAirBlock(i1, j1 = j + MathHelper.getRandomIntegerInRange((Random)random, (int)-2, (int)2), k1 = k + MathHelper.getRandomIntegerInRange((Random)random, (int)-2, (int)2)) || !this.isStone(world, i1 - 1, j1, k1) && !this.isStone(world, i1 + 1, j1, k1) && !this.isStone(world, i1, j1, k1 - 1) && !this.isStone(world, i1, j1, k1 + 1)) continue;
            world.setBlock(i1, j1, k1, this.leafBlock, this.leafMeta | 4, 2);
        }
        return true;
    }

    private boolean isStone(World world, int i, int j, int k) {
        return world.getBlock(i, j, k).getMaterial() == Material.rock;
    }
}

