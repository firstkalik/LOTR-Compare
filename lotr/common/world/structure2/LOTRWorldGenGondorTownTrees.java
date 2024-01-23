/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.structure2;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRWorldGenGondorTownTrees
extends LOTRWorldGenGondorStructure {
    public LOTRWorldGenGondorTownTrees(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int i1;
        int j1;
        this.setOriginAndRotation(world, i, j, k, rotation, 2);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            for (i1 = -6; i1 <= 6; ++i1) {
                for (k1 = -2; k1 <= 2; ++k1) {
                    int j12 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j12, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -6; i1 <= 6; ++i1) {
            for (k1 = -2; k1 <= 2; ++k1) {
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 1; j1 <= 10; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                if (i2 % 4 != 2 && k2 <= 1) {
                    this.setBlockAndMetadata(world, i1, 0, k1, (Block)Blocks.grass, 0);
                }
                if (i2 % 4 != 2 || k2 != 2) continue;
                this.setBlockAndMetadata(world, i1, 1, k1, this.rockWallBlock, this.rockWallMeta);
                this.setBlockAndMetadata(world, i1, 2, k1, Blocks.torch, 5);
            }
        }
        for (int i12 : new int[]{-4, 0, 4}) {
            WorldGenAbstractTree treeGen;
            LOTRTreeType tree;
            j1 = 1;
            int k12 = 0;
            for (int l = 0; !(l >= 16 || (treeGen = (tree = LOTRWorldGenGondorTownTrees.getRandomTree(random)).create(this.notifyChanges, random)) != null && treeGen.generate(world, random, this.getX(i12, k12), this.getY(j1), this.getZ(i12, k12))); ++l) {
            }
        }
        return true;
    }

    public static LOTRTreeType getRandomTree(Random random) {
        ArrayList<LOTRTreeType> treeList = new ArrayList<LOTRTreeType>();
        treeList.add(LOTRTreeType.CYPRESS);
        return (LOTRTreeType)((Object)treeList.get(random.nextInt(treeList.size())));
    }
}

