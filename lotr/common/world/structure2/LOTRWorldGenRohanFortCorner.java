/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenRohanStructure;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenRohanFortCorner
extends LOTRWorldGenRohanStructure {
    public LOTRWorldGenRohanFortCorner(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean oneWoodType() {
        return true;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        this.setOriginAndRotation(world, i, j, k, rotation, 0);
        this.setupRandomBlocks(random);
        int width = 1;
        for (int i1 = -width; i1 <= width; ++i1) {
            for (int k1 = -width; k1 <= width; ++k1) {
                int j1;
                Math.abs(i1);
                Math.abs(k1);
                for (j1 = 1; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.rockSlabDoubleBlock, this.rockSlabDoubleMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 2; j1 <= 3; ++j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.brickBlock, this.brickMeta);
                }
                for (j1 = 4; j1 <= 7; ++j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.woodBeamBlock, this.woodBeamMeta);
                }
                this.setBlockAndMetadata(world, i1, 8, k1, this.plankSlabBlock, this.plankSlabMeta);
            }
        }
        return true;
    }
}

