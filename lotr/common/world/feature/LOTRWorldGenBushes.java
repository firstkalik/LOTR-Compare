/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenBushes
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        Block block;
        int i1;
        int k1;
        Block leafBlock = null;
        int leafMeta = 0;
        for (int l = 0; l < 40; ++l) {
            int j1;
            i1 = i - random.nextInt(6) + random.nextInt(6);
            block = world.getBlock(i1, j1 = j + random.nextInt(12), k1 = k - random.nextInt(6) + random.nextInt(6));
            if (!(block instanceof BlockLeavesBase)) continue;
            int meta = world.getBlockMetadata(i1, j1, k1);
            leafBlock = block;
            leafMeta = meta;
            break;
        }
        if (leafBlock == null) {
            return false;
        }
        Block below = world.getBlock(i, j - 1, k);
        if (below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.sapling)) {
            int size = 0;
            if (random.nextInt(3) == 0) {
                ++size;
            }
            for (i1 = -size; i1 <= size; ++i1) {
                for (k1 = -size; k1 <= size; ++k1) {
                    int i2 = i + i1;
                    int k2 = k + k1;
                    if (size != 0 && Math.abs(i1) == size && Math.abs(k1) == size && random.nextInt(3) != 0 || (block = world.getBlock(i2, j, k2)).getMaterial().isLiquid() || !block.isReplaceable((IBlockAccess)world, i2, j, k2)) continue;
                    world.setBlock(i2, j, k2, leafBlock, leafMeta | 4, 2);
                }
            }
        }
        return true;
    }
}

