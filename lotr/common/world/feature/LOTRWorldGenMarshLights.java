/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenMarshLights
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 4; ++l) {
            int k1;
            int j1;
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = j, k1 = k + random.nextInt(8) - random.nextInt(8)) || !LOTRMod.marshLights.canPlaceBlockAt(world, i1, j1, k1)) continue;
            world.setBlock(i1, j1, k1, LOTRMod.marshLights, 0, 2);
        }
        return true;
    }
}

