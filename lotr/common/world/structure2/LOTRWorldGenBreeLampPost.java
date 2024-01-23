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
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenBreeLampPost
extends LOTRWorldGenBreeStructure {
    public LOTRWorldGenBreeLampPost(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int i1;
        int j1;
        int j12;
        this.setOriginAndRotation(world, i, j, k, rotation, 0);
        this.setupRandomBlocks(random);
        if (this.restrictions && !this.isSurface(world, i1 = 0, j1 = this.getTopBlock(world, i1, k1 = 0) - 1, k1)) {
            return false;
        }
        for (j12 = 0; !(j12 < 0 && this.isOpaque(world, 0, j12, 0) || this.getY(j12) < 0); --j12) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, 0, j12, 0, Blocks.cobblestone, 0);
            } else {
                this.setBlockAndMetadata(world, 0, j12, 0, Blocks.mossy_cobblestone, 0);
            }
            this.setGrassToDirt(world, 0, j12 - 1, 0);
        }
        if (random.nextBoolean()) {
            this.setBlockAndMetadata(world, 0, 1, 0, Blocks.cobblestone_wall, 0);
        } else {
            this.setBlockAndMetadata(world, 0, 1, 0, Blocks.cobblestone_wall, 1);
        }
        for (j12 = 2; j12 <= 3; ++j12) {
            this.setBlockAndMetadata(world, 0, j12, 0, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, 0, 4, 0, Blocks.torch, 5);
        return true;
    }
}

