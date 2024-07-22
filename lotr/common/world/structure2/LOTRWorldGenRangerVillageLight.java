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
import lotr.common.world.structure2.LOTRWorldGenRangerStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRangerVillageLight
extends LOTRWorldGenRangerStructure {
    public LOTRWorldGenRangerVillageLight(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        this.setOriginAndRotation(world, i, j, k, rotation, 0);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int i1 = 0;
            int k1 = 0;
            if (!this.isSurface(world, 0, this.getTopBlock(world, i1, 0) - 1, k1)) {
                return false;
            }
        }
        for (j1 = 0; !(j1 < 0 && this.isOpaque(world, 0, j1, 0) || this.getY(j1) < 0); --j1) {
            this.setBlockAndMetadata(world, 0, j1, 0, this.logBlock, this.logMeta);
            this.setGrassToDirt(world, 0, j1 - 1, 0);
        }
        for (j1 = 1; j1 <= 2; ++j1) {
            this.setBlockAndMetadata(world, 0, j1, 0, this.logBlock, this.logMeta);
        }
        this.setBlockAndMetadata(world, 0, 3, 0, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, 0, 4, 0, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 5, 0, Blocks.torch, 5);
        return true;
    }
}

