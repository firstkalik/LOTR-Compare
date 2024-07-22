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

public class LOTRWorldGenRohanVillagePalisade
extends LOTRWorldGenRohanStructure {
    public LOTRWorldGenRohanVillagePalisade(boolean flag) {
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
        if (this.restrictions) {
            int i1 = 0;
            int k1 = 0;
            if (!this.isSurface(world, 0, this.getTopBlock(world, i1, 0) - 1, k1)) {
                return false;
            }
        }
        for (int j12 = 1; !(j12 < 0 && this.isOpaque(world, 0, j12, 0) || this.getY(j12) < 0); --j12) {
            this.setBlockAndMetadata(world, 0, j12, 0, this.cobbleBlock, this.cobbleMeta);
            this.setGrassToDirt(world, 0, j12 - 1, 0);
        }
        int height = 5 + random.nextInt(2);
        for (int j13 = 2; j13 <= height; ++j13) {
            this.setBlockAndMetadata(world, 0, j13, 0, this.logBlock, this.logMeta);
        }
        return true;
    }
}

