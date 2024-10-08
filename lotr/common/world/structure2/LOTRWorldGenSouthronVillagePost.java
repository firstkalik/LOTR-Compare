/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenSouthronStructure;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenSouthronVillagePost
extends LOTRWorldGenSouthronStructure {
    public LOTRWorldGenSouthronVillagePost(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.plankSlabBlock = LOTRMod.woodSlabSingle3;
        this.plankSlabMeta = 2;
        this.woodBeamBlock = LOTRMod.woodBeam4;
        this.woodBeamMeta = 2;
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
        for (int j12 = 0; !(j12 < 0 && this.isOpaque(world, 0, j12, 0) || this.getY(j12) < 0); --j12) {
            this.setBlockAndMetadata(world, 0, j12, 0, this.woodBeamBlock, this.woodBeamMeta);
            this.setGrassToDirt(world, 0, j12 - 1, 0);
        }
        this.setBlockAndMetadata(world, 0, 1, 0, this.woodBeamBlock, this.woodBeamMeta);
        this.setBlockAndMetadata(world, 0, 2, 0, this.woodBeamBlock, this.woodBeamMeta);
        this.setBlockAndMetadata(world, 0, 3, 0, this.plankSlabBlock, this.plankSlabMeta);
        return true;
    }
}

