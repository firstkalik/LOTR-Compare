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
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure2.LOTRWorldGenGulfStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenGulfTownWall
extends LOTRWorldGenGulfStructure {
    private boolean isTall = false;

    public LOTRWorldGenGulfTownWall(boolean flag) {
        super(flag);
    }

    public void setTall() {
        this.isTall = true;
    }

    @Override
    protected boolean canUseRedBrick() {
        return false;
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
        for (j12 = 1; !(j12 < 0 && this.isOpaque(world, 0, j12, 0) || this.getY(j12) < 0); --j12) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, 0, j12, 0, Blocks.sandstone, 0);
            } else {
                this.setBlockAndMetadata(world, 0, j12, 0, this.brickBlock, this.brickMeta);
            }
            this.setGrassToDirt(world, 0, j12 - 1, 0);
        }
        for (j12 = 2; j12 <= 4; ++j12) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, 0, j12, 0, Blocks.sandstone, 0);
                continue;
            }
            this.setBlockAndMetadata(world, 0, j12, 0, this.brickBlock, this.brickMeta);
        }
        if (this.isTall) {
            for (j12 = 5; j12 <= 6; ++j12) {
                this.setBlockAndMetadata(world, 0, j12, 0, this.boneWallBlock, this.boneWallMeta);
            }
            this.setBlockAndMetadata(world, 0, 7, 0, this.boneBlock, this.boneMeta);
            this.placeWallBanner(world, 0, 7, 0, LOTRItemBanner.BannerType.HARAD_GULF, 2);
        } else {
            this.setBlockAndMetadata(world, 0, 5, 0, this.fenceBlock, this.fenceMeta);
        }
        return true;
    }
}

