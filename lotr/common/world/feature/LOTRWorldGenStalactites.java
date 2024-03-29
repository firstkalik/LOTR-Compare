/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenStalactites
extends WorldGenerator {
    private Block stalactiteBlock;

    public LOTRWorldGenStalactites() {
        this(LOTRMod.stalactite);
    }

    public LOTRWorldGenStalactites(Block block) {
        this.stalactiteBlock = block;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 64; ++l) {
            int k1;
            int j1;
            int i1 = i - random.nextInt(8) + random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = j - random.nextInt(4) + random.nextInt(4), k1 = k - random.nextInt(8) + random.nextInt(8))) continue;
            Block above = world.getBlock(i1, j1 + 1, k1);
            Block below = world.getBlock(i1, j1 - 1, k1);
            if (above.isOpaqueCube() && above.getMaterial() == Material.rock) {
                world.setBlock(i1, j1, k1, this.stalactiteBlock, 0, 2);
                continue;
            }
            if (!below.isOpaqueCube() || below.getMaterial() != Material.rock) continue;
            world.setBlock(i1, j1, k1, this.stalactiteBlock, 1, 2);
        }
        return true;
    }
}

