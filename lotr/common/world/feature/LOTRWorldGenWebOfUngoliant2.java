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

public class LOTRWorldGenWebOfUngoliant2
extends WorldGenerator {
    private int attempts;

    public LOTRWorldGenWebOfUngoliant2(boolean flag, int i) {
        super(flag);
        this.attempts = i;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < this.attempts; ++l) {
            int k1;
            int j1;
            int i1 = i - random.nextInt(8) + random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = j - random.nextInt(6) + random.nextInt(6), k1 = k - random.nextInt(8) + random.nextInt(8))) continue;
            boolean flag = false;
            if (this.isSuitableBlock(world, i1 - 1, j1, k1)) {
                flag = true;
            }
            if (this.isSuitableBlock(world, i1 + 1, j1, k1)) {
                flag = true;
            }
            if (this.isSuitableBlock(world, i1, j1 - 1, k1)) {
                flag = true;
            }
            if (this.isSuitableBlock(world, i1, j1 + 1, k1)) {
                flag = true;
            }
            if (this.isSuitableBlock(world, i1, j1, k1 - 1)) {
                flag = true;
            }
            if (this.isSuitableBlock(world, i1, j1, k1 + 1)) {
                flag = true;
            }
            if (!flag) continue;
            this.setBlockAndNotifyAdequately(world, i1, j1, k1, Blocks.packed_ice, 0);
        }
        return true;
    }

    private boolean isSuitableBlock(World world, int i, int j, int k) {
        return world.getBlock(i, j, k).isNormalCube();
    }
}

