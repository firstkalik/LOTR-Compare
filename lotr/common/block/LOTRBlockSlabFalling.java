/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.block.LOTRBlockSlabBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;

public abstract class LOTRBlockSlabFalling
extends LOTRBlockSlabBase {
    public LOTRBlockSlabFalling(boolean flag, Material material, int n) {
        super(flag, material, n);
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        world.scheduleBlockUpdate(i, j, k, (Block)this, this.tickRate(world));
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        world.scheduleBlockUpdate(i, j, k, (Block)this, this.tickRate(world));
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (!world.isRemote) {
            this.tryBlockFall(world, i, j, k);
        }
    }

    private void tryBlockFall(World world, int i, int j, int k) {
        if (BlockFalling.func_149831_e((World)world, (int)i, (int)(j - 1), (int)k) && j >= 0) {
            int range = 32;
            if (!BlockFalling.fallInstantly && world.checkChunksExist(i - range, j - range, k - range, i + range, j + range, k + range)) {
                if (!world.isRemote) {
                    EntityFallingBlock fallingBlock = new EntityFallingBlock(world, (double)((float)i + 0.5f), (double)((float)j + 0.5f), (double)((float)k + 0.5f), (Block)this, world.getBlockMetadata(i, j, k) & 7);
                    world.spawnEntityInWorld((Entity)fallingBlock);
                }
            } else {
                world.setBlockToAir(i, j, k);
                while (BlockFalling.func_149831_e((World)world, (int)i, (int)(j - 1), (int)k) && j > 0) {
                    --j;
                }
                if (j > 0) {
                    world.setBlock(i, j, k, (Block)this);
                }
            }
        }
    }

    public int tickRate(World world) {
        return 2;
    }
}

