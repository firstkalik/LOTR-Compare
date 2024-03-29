/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRWorldGenNurnFarmBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenNurnWheatFarm
extends LOTRWorldGenNurnFarmBase {
    public LOTRWorldGenNurnWheatFarm(boolean flag) {
        super(flag);
    }

    @Override
    public void generateCrops(World world, Random random, int i, int j, int k) {
        for (int i1 = i - 4; i1 <= i + 4; ++i1) {
            for (int k1 = k - 4; k1 <= k + 4; ++k1) {
                if (Math.abs(i1 - i) == 4 && Math.abs(k1 - k) == 4) {
                    this.setBlockAndNotifyAdequately(world, i1, j + 1, k1, LOTRMod.brick, 0);
                    this.setBlockAndNotifyAdequately(world, i1, j + 2, k1, LOTRMod.brick, 0);
                    this.setBlockAndNotifyAdequately(world, i1, j + 3, k1, LOTRMod.fence, 3);
                    this.setBlockAndNotifyAdequately(world, i1, j + 4, k1, Blocks.wool, 12);
                    this.placeSkull(world, random, i1, j + 5, k1);
                    continue;
                }
                if (Math.abs(i1 - i) <= 1 && Math.abs(k1 - k) <= 1) {
                    this.setBlockAndNotifyAdequately(world, i1, j + 1, k1, LOTRMod.brick, 0);
                    if (Math.abs(i1 - i) == 0 || Math.abs(k1 - k) == 0) continue;
                    this.placeOrcTorch(world, i1, j + 2, k1);
                    continue;
                }
                if (i1 == i || k1 == k) {
                    if (Math.abs(i1 - i) > 3 || Math.abs(k1 - k) > 3) continue;
                    this.setBlockAndNotifyAdequately(world, i1, j, k1, Blocks.water, 0);
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, i1, j, k1, Blocks.farmland, 7);
                this.setBlockAndNotifyAdequately(world, i1, j + 1, k1, Blocks.wheat, 7);
            }
        }
        this.setBlockAndNotifyAdequately(world, i, j + 1, k, LOTRMod.morgulTable, 0);
    }
}

