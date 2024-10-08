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

public class LOTRBlockSapling4
extends LOTRBlockSaplingBase {
    public LOTRBlockSapling4() {
        this.setSaplingNames("chestnut", "baobab", "cedar", "fir");
    }

    @Override
    public void incrementGrowth(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMetadata(i, j, k) & 7;
        if (meta == 1 && random.nextInt(4) > 0) {
            return;
        }
        super.incrementGrowth(world, i, j, k, random);
    }

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        int i1;
        int k1;
        int meta = world.getBlockMetadata(i, j, k) & 7;
        WorldGenAbstractTree treeGen = null;
        boolean trunkNeg = false;
        int trunkPos = 0;
        int xOffset = 0;
        int zOffset = 0;
        if (meta == 0) {
            int[] partyTree = LOTRBlockSaplingBase.findPartyTree(world, i, j, k, (Block)this, meta);
            if (partyTree != null) {
                treeGen = LOTRTreeType.CHESTNUT_PARTY.create(true, random);
                trunkPos = 1;
                trunkNeg = true;
                xOffset = partyTree[0];
                zOffset = partyTree[1];
            }
            if (treeGen == null) {
                treeGen = random.nextInt(10) == 0 ? LOTRTreeType.CHESTNUT_LARGE.create(true, random) : LOTRTreeType.CHESTNUT.create(true, random);
                trunkPos = 0;
                trunkNeg = false;
                xOffset = 0;
                zOffset = 0;
            }
        }
        if (meta == 1) {
            treeGen = LOTRTreeType.BAOBAB.create(true, random);
        }
        if (meta == 2) {
            treeGen = LOTRTreeType.CEDAR.create(true, random);
        }
        if (meta == 3) {
            treeGen = LOTRTreeType.FIR.create(true, random);
        }
        for (i1 = -trunkNeg; i1 <= trunkPos; ++i1) {
            for (k1 = -trunkNeg; k1 <= trunkPos; ++k1) {
                world.setBlock(i + xOffset + i1, j, k + zOffset + k1, Blocks.air, 0, 4);
            }
        }
        if (treeGen != null && !treeGen.generate(world, random, i + xOffset, j, k + zOffset)) {
            for (i1 = -trunkNeg; i1 <= trunkPos; ++i1) {
                for (k1 = -trunkNeg; k1 <= trunkPos; ++k1) {
                    world.setBlock(i + xOffset + i1, j, k + zOffset + k1, (Block)this, meta, 4);
                }
            }
        }
    }
}

