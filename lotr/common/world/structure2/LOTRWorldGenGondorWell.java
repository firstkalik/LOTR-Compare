/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenGondorWell
extends LOTRWorldGenGondorStructure {
    public LOTRWorldGenGondorWell(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, 4);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i1 = -3; i1 <= 3; ++i1) {
                for (int k1 = -3; k1 <= 3; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (!this.isSurface(world, i1, j1, k1)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 8) continue;
                    return false;
                }
            }
        }
        for (int i1 = -3; i1 <= 3; ++i1) {
            for (int k1 = -3; k1 <= 3; ++k1) {
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.rockBlock, this.rockMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 1; j1 <= 6; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                if (i2 == 2 && k2 == 2) {
                    this.setBlockAndMetadata(world, i1, 1, k1, this.rockBlock, this.rockMeta);
                    this.setBlockAndMetadata(world, i1, 2, k1, this.rockBlock, this.rockMeta);
                    this.setBlockAndMetadata(world, i1, 3, k1, this.rockWallBlock, this.rockWallMeta);
                    this.setBlockAndMetadata(world, i1, 4, k1, this.rockBlock, this.rockMeta);
                    this.setBlockAndMetadata(world, i1, 5, k1, this.rockSlabBlock, this.rockSlabMeta);
                }
                if (i2 <= 2 && k2 <= 2) {
                    int d = i2 + k2;
                    if (d == 3) {
                        this.setBlockAndMetadata(world, i1, 4, k1, this.rockSlabBlock, this.rockSlabMeta | 8);
                        this.setBlockAndMetadata(world, i1, 5, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                    }
                    if (d == 2) {
                        this.setBlockAndMetadata(world, i1, 5, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                        this.setBlockAndMetadata(world, i1, 6, k1, this.rockSlabBlock, this.rockSlabMeta);
                    }
                    if (d == 1) {
                        this.setBlockAndMetadata(world, i1, 5, k1, this.rockSlabBlock, this.rockSlabMeta | 8);
                        this.setBlockAndMetadata(world, i1, 6, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                    }
                    if (d == 0) {
                        this.setBlockAndMetadata(world, i1, 6, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                        this.setBlockAndMetadata(world, i1, 7, k1, this.rockSlabBlock, this.rockSlabMeta);
                    }
                }
                if ((i2 != 2 || k2 > 1) && (k2 != 2 || i2 > 1)) continue;
                this.setBlockAndMetadata(world, i1, 1, k1, this.fenceBlock, this.fenceMeta);
                this.setBlockAndMetadata(world, i1, 0, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
            }
        }
        int waterDepth = 1 + random.nextInt(4);
        int depth = waterDepth + 1 + random.nextInt(3);
        for (j12 = 0; j12 < depth; ++j12) {
            int j2 = 0 - j12;
            boolean watery = j12 >= depth - waterDepth;
            for (int i1 = -1; i1 <= 1; ++i1) {
                for (int k1 = -1; k1 <= 1; ++k1) {
                    if (watery) {
                        this.setBlockAndMetadata(world, i1, j2, k1, Blocks.water, 0);
                        continue;
                    }
                    this.setAir(world, i1, j2, k1);
                }
            }
            if (watery) continue;
            this.setBlockAndMetadata(world, 0, j2, -1, Blocks.ladder, 3);
            this.setBlockAndMetadata(world, 0, j2, 1, Blocks.ladder, 2);
            this.setBlockAndMetadata(world, -1, j2, 0, Blocks.ladder, 4);
            this.setBlockAndMetadata(world, 1, j2, 0, Blocks.ladder, 5);
        }
        this.setBlockAndMetadata(world, 0, 1, -2, this.fenceGateBlock, 0);
        this.setBlockAndMetadata(world, 0, 1, 2, this.fenceGateBlock, 2);
        this.setBlockAndMetadata(world, -2, 1, 0, this.fenceGateBlock, 1);
        this.setBlockAndMetadata(world, 2, 1, 0, this.fenceGateBlock, 3);
        for (j12 = 4; j12 <= 5; ++j12) {
            this.setBlockAndMetadata(world, 0, j12, 0, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -3, 5, 0, Blocks.torch, 1);
        this.setBlockAndMetadata(world, 3, 5, 0, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 0, 5, -3, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 0, 5, 3, Blocks.torch, 3);
        return true;
    }
}

