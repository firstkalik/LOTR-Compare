/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.block.LOTRBlockFallenLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenFallenLeaves
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int i1;
        int l;
        int k1;
        Block fallenLeaf = null;
        int fallenMeta = 0;
        for (l = 0; l < 40; ++l) {
            Object[] obj;
            i1 = i - random.nextInt(6) + random.nextInt(6);
            Block block = world.getBlock(i1, j1 = j + random.nextInt(12), k1 = k - random.nextInt(6) + random.nextInt(6));
            if (!(block instanceof BlockLeavesBase) || (obj = LOTRBlockFallenLeaves.fallenBlockMetaFromLeafBlockMeta(block, world.getBlockMetadata(i1, j1, k1))) == null) continue;
            fallenLeaf = (Block)obj[0];
            fallenMeta = (Integer)obj[1];
            break;
        }
        if (fallenLeaf == null) {
            return false;
        }
        for (l = 0; l < 64; ++l) {
            i1 = i - random.nextInt(5) + random.nextInt(5);
            j1 = j - random.nextInt(3) + random.nextInt(3);
            k1 = k - random.nextInt(5) + random.nextInt(5);
            world.getBlock(i1, j1 - 1, k1);
            Block block = world.getBlock(i1, j1, k1);
            if (!fallenLeaf.canBlockStay(world, i1, j1, k1) || block.getMaterial().isLiquid() || !block.isReplaceable((IBlockAccess)world, i1, j1, k1)) continue;
            world.setBlock(i1, j1, k1, fallenLeaf, fallenMeta, 2);
        }
        return true;
    }
}

