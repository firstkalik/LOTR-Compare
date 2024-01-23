/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenLogs
extends WorldGenerator {
    public LOTRWorldGenLogs() {
        super(false);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        if (!this.isSuitablePositionForLog(world, i, j, k)) {
            return false;
        }
        int logType = random.nextInt(5);
        if (logType == 0) {
            int length = 2 + random.nextInt(6);
            for (int i1 = i; i1 < i + length && this.isSuitablePositionForLog(world, i1, j, k); ++i1) {
                this.setBlockAndNotifyAdequately(world, i1, j, k, LOTRMod.rottenLog, 4);
                world.getBlock(i1, j - 1, k).onPlantGrow(world, i1, j - 1, k, i1, j, k);
            }
            return true;
        }
        if (logType == 1) {
            int length = 2 + random.nextInt(6);
            for (int k1 = k; k1 < k + length && this.isSuitablePositionForLog(world, i, j, k1); ++k1) {
                this.setBlockAndNotifyAdequately(world, i, j, k1, LOTRMod.rottenLog, 8);
                world.getBlock(i, j - 1, k1).onPlantGrow(world, i, j - 1, k1, i, j, k1);
            }
            return true;
        }
        this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.rottenLog, 0);
        world.getBlock(i, j - 1, k).onPlantGrow(world, i, j - 1, k, i, j, k);
        return true;
    }

    private boolean isSuitablePositionForLog(World world, int i, int j, int k) {
        if (!world.getBlock(i, j - 1, k).canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling)) {
            return false;
        }
        return world.getBlock(i, j, k).isReplaceable((IBlockAccess)world, i, j, k);
    }
}

