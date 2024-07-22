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
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityAngbandCrossbow;
import lotr.common.entity.npc.LOTREntityAngbandElf2;
import lotr.common.entity.npc.LOTREntityAngbandUruc;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.structure2.LOTRWorldGenAngbandForge;
import lotr.common.world.structure2.LOTRWorldGenAngbandTent;
import lotr.common.world.structure2.LOTRWorldGenCampBase;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenBlackUrukFort4
extends LOTRWorldGenCampBase {
    public LOTRWorldGenBlackUrukFort4(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tableBlock = LOTRMod.angbandtable;
        this.brickBlock = LOTRMod.utumnoBrick;
        this.brickMeta = 2;
        this.brickSlabBlock = LOTRMod.slabUtumnoSingle;
        this.brickSlabMeta = 1;
        this.fenceBlock = LOTRMod.fence;
        this.fenceMeta = 3;
        this.fenceGateBlock = LOTRMod.fenceGateCharred;
        this.hasOrcTorches = true;
        this.hasSkulls = true;
    }

    @Override
    protected LOTRWorldGenStructureBase2 createTent(boolean flag, Random random) {
        if (random.nextInt(6) == 0) {
            return new LOTRWorldGenAngbandForge(false);
        }
        return new LOTRWorldGenAngbandTent(false);
    }

    @Override
    protected LOTREntityNPC getCampCaptain(World world, Random random) {
        return random.nextBoolean() ? new LOTREntityAngbandElf2(world) : new LOTREntityAngbandElf2(world);
    }

    @Override
    protected void placeNPCRespawner(World world, Random random, int i, int j, int k) {
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityAngbandUruc.class, LOTREntityAngbandCrossbow.class);
        respawner.setCheckRanges(24, -12, 12, 12);
        respawner.setSpawnRanges(8, -4, 4, 16);
        this.placeNPCRespawner(respawner, world, i, j, k);
    }
}

