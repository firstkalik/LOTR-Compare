/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRWorldGenDeadTreesAlternative
extends WorldGenAbstractTree {
    private Block woodBlock;
    private int woodMeta;

    public LOTRWorldGenDeadTreesAlternative(Block block, int i) {
        super(false);
        this.woodBlock = block;
        this.woodMeta = i;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        Block block = world.getBlock(x, y, z);
        if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.hardened_clay) {
            return false;
        }
        for (int var7 = -2; var7 <= 2; ++var7) {
            for (int var8 = -2; var8 <= 2; ++var8) {
                if (!world.isAirBlock(x + var7, y - 1, z + var8) || !world.isAirBlock(x + var7, y - 2, z + var8) || world.isAirBlock(x + var7, y, z + var8)) continue;
                return false;
            }
        }
        int var999 = random.nextInt(8);
        if (var999 == 0) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 3, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 2, this.woodBlock, this.woodMeta);
        } else if (var999 == 1) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 3, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 2, this.woodBlock, this.woodMeta);
        } else if (var999 == 2) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 3, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 2, this.woodBlock, this.woodMeta);
        } else if (var999 == 3) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z - 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 3, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 7, z, this.woodBlock, this.woodMeta);
        } else if (var999 == 4) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z + 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 3, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 7, z, this.woodBlock, this.woodMeta);
        } else if (var999 == 5) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z + 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 3, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 7, z, this.woodBlock, this.woodMeta);
        } else if (var999 == 6) {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z - 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 3, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 7, z, this.woodBlock, this.woodMeta);
        } else {
            this.buildBlock(world, x, y + 1, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 9, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 3, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 3, y + 5, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x - 2, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 2, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 4, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 6, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 1, y + 7, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x + 2, y + 8, z, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 5, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z - 3, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 8, z - 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 3, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 4, z + 2, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 6, z + 1, this.woodBlock, this.woodMeta);
            this.buildBlock(world, x, y + 7, z + 2, this.woodBlock, this.woodMeta);
        }
        return true;
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta) {
        if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves((IBlockAccess)world, x, y, z)) {
            this.setBlockAndNotifyAdequately(world, x, y, z, block, meta | 0xC);
        }
    }
}

