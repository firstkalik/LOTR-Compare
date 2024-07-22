/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Direction
 *  net.minecraft.util.Facing
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenVines
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        int l = i;
        int i1 = k;
        while (j < 128) {
            if (world.isAirBlock(i, j, k)) {
                for (int j1 = 2; j1 <= 5; ++j1) {
                    if (!Blocks.vine.canPlaceBlockOnSide(world, i, j, k, j1)) continue;
                    world.setBlock(i, j, k, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
                    break;
                }
            } else {
                i = l + random.nextInt(4) - random.nextInt(4);
                k = i1 + random.nextInt(4) - random.nextInt(4);
            }
            ++j;
        }
        return true;
    }
}

