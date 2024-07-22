/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenLilyPad
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 10; ++l) {
            int k1;
            int j1;
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = j + random.nextInt(4) - random.nextInt(4), k1 = k + random.nextInt(8) - random.nextInt(8)) || !Blocks.waterlily.canPlaceBlockAt(world, i1, j1, k1)) continue;
            world.setBlock(i1, j1, k1, Blocks.waterlily, random.nextInt(5), 2);
        }
        return true;
    }
}

