/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenBamboo
extends WorldGenAbstractTree {
    private final int minTreeHeight;
    private final int maxTreeHeight;
    private final Block wood;
    private final int metaWood;

    public LOTRWorldGenBamboo(Block wood, int metaWood, int minTreeHeight, int maxTreeHeight) {
        super(false);
        this.wood = wood;
        this.metaWood = metaWood;
        this.minTreeHeight = minTreeHeight;
        this.maxTreeHeight = maxTreeHeight;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int l = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minTreeHeight, (int)this.maxTreeHeight);
        boolean flag = true;
        if (y >= 1 && y + l + 1 <= 256) {
            int b0;
            Block block;
            int k1;
            for (int i1 = y; i1 <= y + 1 + l; ++i1) {
                b0 = 1;
                if (i1 == y) {
                    b0 = 0;
                }
                if (i1 >= y + 1 + l - 2) {
                    b0 = 2;
                }
                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            block = world.getBlock(j1, i1, k1);
                            if (this.isReplaceable(world, j1, i1, k1)) continue;
                            flag = false;
                            continue;
                        }
                        flag = false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            Block block2 = world.getBlock(x, y - 1, z);
            boolean isSoil = block2.canSustainPlant((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.sapling));
            if (isSoil && y < 256 - l - 1) {
                b0 = 3;
                for (k1 = 0; k1 < l; ++k1) {
                    block = world.getBlock(x, y + k1, z);
                    if (!block.isAir((IBlockAccess)world, x, y + k1, z) && !block.isLeaves((IBlockAccess)world, x, y + k1, z) && block != Blocks.vine) continue;
                    this.setBlockAndNotifyAdequately(world, x, y + k1, z, this.wood, this.metaWood);
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

