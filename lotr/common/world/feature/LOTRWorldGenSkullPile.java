/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenSkullPile
extends WorldGenerator {
    public LOTRWorldGenSkullPile() {
        super(false);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 4; ++l) {
            int k1;
            int j1;
            int i1 = i - 4 + random.nextInt(9);
            if (!world.getBlock(i1, (j1 = world.getHeightValue(i1, k1 = k - 4 + random.nextInt(9))) - 1, k1).isOpaqueCube() || !world.getBlock(i1, j1, k1).isReplaceable((IBlockAccess)world, i1, j1, k1)) continue;
            world.setBlock(i1, j1, k1, Blocks.skull, 1, 2);
            TileEntity tileentity = world.getTileEntity(i1, j1, k1);
            if (!(tileentity instanceof TileEntitySkull)) continue;
            TileEntitySkull skull = (TileEntitySkull)tileentity;
            skull.func_145903_a(random.nextInt(16));
        }
        return true;
    }
}

