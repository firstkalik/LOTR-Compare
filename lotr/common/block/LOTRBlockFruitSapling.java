/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.block.LOTRBlockSaplingBase;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRBlockFruitSapling
extends LOTRBlockSaplingBase {
    public LOTRBlockFruitSapling() {
        this.setSaplingNames("apple", "pear", "cherry", "mango");
    }

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMetadata(i, j, k) & 7;
        WorldGenAbstractTree treeGen = null;
        if (meta == 0) {
            treeGen = LOTRTreeType.APPLE.create(true, random);
        } else if (meta == 1) {
            treeGen = LOTRTreeType.PEAR.create(true, random);
        } else if (meta == 2) {
            treeGen = LOTRTreeType.CHERRY.create(true, random);
        } else if (meta == 3) {
            treeGen = LOTRTreeType.MANGO.create(true, random);
        }
        world.setBlock(i, j, k, Blocks.air, 0, 4);
        if (treeGen != null && !treeGen.generate(world, random, i, j, k)) {
            world.setBlock(i, j, k, (Block)this, meta, 4);
        }
    }
}

