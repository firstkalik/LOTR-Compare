/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityWickedElf;
import lotr.common.world.structure.LOTRWorldGenMoredainMercTent2;
import lotr.common.world.structure2.LOTRWorldGenCampBase;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class LOTRWorldGenMoredainMercCamp2
extends LOTRWorldGenCampBase {
    public LOTRWorldGenMoredainMercCamp2(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean generateFarm() {
        return false;
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tableBlock = LOTRMod.commandTable;
        this.brickBlock = LOTRMod.utumnoBrick;
        this.brickMeta = 2;
        this.brickSlabBlock = LOTRMod.slabUtumnoSingle;
        this.brickSlabMeta = 4;
        this.fenceBlock = LOTRMod.fence2;
        this.fenceMeta = 2;
        this.fenceGateBlock = LOTRMod.fenceGateCedar;
    }

    @Override
    protected LOTRWorldGenStructureBase2 createTent(boolean flag, Random random) {
        return new LOTRWorldGenMoredainMercTent2(false);
    }

    @Override
    protected LOTREntityNPC getCampCaptain(World world, Random random) {
        return null;
    }

    @Override
    protected void placeNPCRespawner(World world, Random random, int i, int j, int k) {
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClass(LOTREntityWickedElf.class);
        respawner.setCheckRanges(24, -12, 12, 10);
        respawner.setSpawnRanges(8, -4, 4, 16);
        this.placeNPCRespawner(respawner, world, i, j, k);
        int mercs = 2 + random.nextInt(5);
        for (int l = 0; l < mercs; ++l) {
            LOTREntityWickedElf merc = new LOTREntityWickedElf(world);
            this.spawnNPCAndSetHome(merc, world, 0, 1, 0, 16);
        }
    }
}

