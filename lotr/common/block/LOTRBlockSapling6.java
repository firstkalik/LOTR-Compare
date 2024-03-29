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

public class LOTRBlockSapling6
extends LOTRBlockSaplingBase {
    public LOTRBlockSapling6() {
        this.setSaplingNames("mahogany", "willow", "cypress", "olive");
    }

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        int k1;
        int i1;
        int meta = world.getBlockMetadata(i, j, k) & 7;
        WorldGenAbstractTree treeGen = null;
        int extraTrunkWidth = 0;
        int xOffset = 0;
        int zOffset = 0;
        if (meta == 0) {
            treeGen = LOTRTreeType.MAHOGANY.create(true, random);
        } else if (meta == 1) {
            treeGen = LOTRTreeType.WILLOW.create(true, random);
        } else if (meta == 2) {
            for (i1 = 0; i1 >= -1; --i1) {
                for (k1 = 0; k1 >= -1; --k1) {
                    if (!this.isSameSapling(world, i + i1, j, k + k1, meta) || !this.isSameSapling(world, i + i1 + 1, j, k + k1, meta) || !this.isSameSapling(world, i + i1, j, k + k1 + 1, meta) || !this.isSameSapling(world, i + i1 + 1, j, k + k1 + 1, meta)) continue;
                    treeGen = LOTRTreeType.CYPRESS_LARGE.create(true, random);
                    extraTrunkWidth = 1;
                    xOffset = i1;
                    zOffset = k1;
                    break;
                }
                if (treeGen != null) break;
            }
            if (treeGen == null) {
                xOffset = 0;
                zOffset = 0;
                treeGen = LOTRTreeType.CYPRESS.create(true, random);
            }
        } else if (meta == 3) {
            for (i1 = 0; i1 >= -1; --i1) {
                for (k1 = 0; k1 >= -1; --k1) {
                    if (!this.isSameSapling(world, i + i1, j, k + k1, meta) || !this.isSameSapling(world, i + i1 + 1, j, k + k1, meta) || !this.isSameSapling(world, i + i1, j, k + k1 + 1, meta) || !this.isSameSapling(world, i + i1 + 1, j, k + k1 + 1, meta)) continue;
                    treeGen = LOTRTreeType.OLIVE_LARGE.create(true, random);
                    extraTrunkWidth = 1;
                    xOffset = i1;
                    zOffset = k1;
                    break;
                }
                if (treeGen != null) break;
            }
            if (treeGen == null) {
                xOffset = 0;
                zOffset = 0;
                treeGen = LOTRTreeType.OLIVE.create(true, random);
            }
        }
        for (i1 = 0; i1 <= extraTrunkWidth; ++i1) {
            for (k1 = 0; k1 <= extraTrunkWidth; ++k1) {
                world.setBlock(i + xOffset + i1, j, k + zOffset + k1, Blocks.air, 0, 4);
            }
        }
        if (treeGen != null && !treeGen.generate(world, random, i + xOffset, j, k + zOffset)) {
            for (i1 = 0; i1 <= extraTrunkWidth; ++i1) {
                for (k1 = 0; k1 <= extraTrunkWidth; ++k1) {
                    world.setBlock(i + xOffset + i1, j, k + zOffset + k1, (Block)this, meta, 4);
                }
            }
        }
    }
}

