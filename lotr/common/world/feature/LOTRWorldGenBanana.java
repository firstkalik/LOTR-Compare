/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenBanana
extends WorldGenAbstractTree {
    public LOTRWorldGenBanana(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int l1;
        ForgeDirection dir;
        int l;
        int height = 2 + random.nextInt(3);
        int[] leaves = new int[4];
        for (int l2 = 0; l2 < 4; ++l2) {
            leaves[l2] = 1 + random.nextInt(3);
        }
        if (j < 1 || j + height + 5 > 256) {
            return false;
        }
        if (!this.isReplaceable(world, i, j, k)) {
            return false;
        }
        Block below = world.getBlock(i, j - 1, k);
        if (!below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)LOTRMod.sapling2)) {
            return false;
        }
        for (l = 0; l < height + 2; ++l) {
            if (this.isReplaceable(world, i, j + l, k)) continue;
            return false;
        }
        for (l = 0; l < 4; ++l) {
            dir = ForgeDirection.getOrientation((int)(l + 2));
            for (l1 = -1; l1 < leaves[l]; ++l1) {
                if (this.isReplaceable(world, i + dir.offsetX, j + height + l1, k + dir.offsetZ)) continue;
                return false;
            }
            for (l1 = -1; l1 < 1; ++l1) {
                if (this.isReplaceable(world, i + dir.offsetX * 2, j + height + leaves[l] + l1, k + dir.offsetZ * 2)) continue;
                return false;
            }
        }
        for (l = 0; l < height + 2; ++l) {
            this.setBlockAndNotifyAdequately(world, i, j + l, k, LOTRMod.wood2, 3);
        }
        for (l = 0; l < 4; ++l) {
            dir = ForgeDirection.getOrientation((int)(l + 2));
            for (l1 = 0; l1 < leaves[l]; ++l1) {
                this.setBlockAndNotifyAdequately(world, i + dir.offsetX, j + height + l1, k + dir.offsetZ, LOTRMod.leaves2, 3);
            }
            this.setBlockAndNotifyAdequately(world, i + dir.getOpposite().offsetX, j + height - 1, k + dir.getOpposite().offsetZ, LOTRMod.bananaBlock, l);
            for (l1 = -1; l1 < 1; ++l1) {
                this.setBlockAndNotifyAdequately(world, i + dir.offsetX * 2, j + height + leaves[l] + l1, k + dir.offsetZ * 2, LOTRMod.leaves2, 3);
            }
        }
        world.getBlock(i, j - 1, k).onPlantGrow(world, i, j - 1, k, i, j, k);
        return true;
    }
}

