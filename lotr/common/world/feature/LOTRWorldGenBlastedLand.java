/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenBlastedLand
extends WorldGenerator {
    private boolean aflame;

    public LOTRWorldGenBlastedLand(boolean flag) {
        this.aflame = flag;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        Block block = world.getBlock(i, j - 1, k);
        if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.stone) {
            return false;
        }
        int radius = 5 + random.nextInt(8);
        for (int i1 = i - radius; i1 <= i + radius; ++i1) {
            for (int j1 = j - radius / 2; j1 <= j + radius / 2; ++j1) {
                for (int k1 = k - radius; k1 <= k + radius; ++k1) {
                    Block block2 = world.getBlock(i1, j1, k1);
                    if (block2 != Blocks.grass && block2 != Blocks.dirt && block2 != Blocks.stone) continue;
                    int i2 = i1 - i;
                    int j2 = j1 - j;
                    int k2 = k1 - k;
                    double d = Math.sqrt(i2 * i2 + j2 * j2 + k2 * k2);
                    int chance = MathHelper.floor_double((double)(d / 2.0));
                    if (chance < 1) {
                        chance = 1;
                    }
                    if (random.nextInt(chance) == 0) {
                        world.setBlock(i1, j1, k1, LOTRMod.wasteBlock, 0, 2);
                    }
                    if (!this.aflame || d >= (double)radius / 2.0 || random.nextInt(10) != 0 || world.getBlock(i1, j1 + 1, k1).isOpaqueCube()) continue;
                    world.setBlock(i1, j1, k1, LOTRMod.wasteBlock, 0, 2);
                    world.setBlock(i1, j1 + 1, k1, (Block)Blocks.fire, 0, 2);
                }
            }
        }
        return true;
    }
}

