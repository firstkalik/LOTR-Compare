/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenBreeWell
extends LOTRWorldGenBreeStructure {
    public LOTRWorldGenBreeWell(boolean flag) {
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
            for (i1 = -1; i1 <= 2; ++i1) {
                for (k1 = -1; k1 <= 2; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (this.isSurface(world, i1, j1, k1)) continue;
                    return false;
                }
            }
        }
        for (i1 = -1; i1 <= 2; ++i1) {
            for (k1 = -1; k1 <= 2; ++k1) {
                for (j1 = 1; j1 <= 4; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        this.loadStrScan("bree_well");
        this.associateBlockMetaAlias("BRICK", this.brickBlock, this.brickMeta);
        this.associateBlockMetaAlias("PLANK_SLAB", this.plankSlabBlock, this.plankSlabMeta);
        this.associateBlockMetaAlias("PLANK_SLAB_INV", this.plankSlabBlock, this.plankSlabMeta | 8);
        this.associateBlockMetaAlias("FENCE", this.fenceBlock, this.fenceMeta);
        this.associateBlockAlias("FENCE_GATE", this.fenceGateBlock);
        this.associateBlockMetaAlias("BEAM", this.beamBlock, this.beamMeta);
        this.generateStrScan(world, random, 0, 0, 0);
        return true;
    }
}

