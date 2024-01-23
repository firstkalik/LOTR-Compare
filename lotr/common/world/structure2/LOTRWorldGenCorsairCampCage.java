/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.entity.npc.LOTREntityHaradSlave;
import lotr.common.world.structure2.LOTRWorldGenCorsairStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class LOTRWorldGenCorsairCampCage
extends LOTRWorldGenCorsairStructure {
    public LOTRWorldGenCorsairCampCage(boolean flag) {
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
        this.loadStrScan("corsair_camp_cage");
        this.associateBlockMetaAlias("PLANK_SLAB", this.plankSlabBlock, this.plankSlabMeta);
        this.associateBlockAlias("PLANK_STAIR", this.plankStairBlock);
        this.associateBlockMetaAlias("FENCE", this.fenceBlock, this.fenceMeta);
        this.generateStrScan(world, random, 0, 0, 0);
        int slaves = 1 + random.nextInt(3);
        for (int l = 0; l < slaves; ++l) {
            LOTREntityHaradSlave slave = new LOTREntityHaradSlave(world);
            this.spawnNPCAndSetHome(slave, world, 0, 1, 0, 4);
        }
        return true;
    }
}

