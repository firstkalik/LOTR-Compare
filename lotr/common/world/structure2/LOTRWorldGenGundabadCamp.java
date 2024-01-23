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
import lotr.common.entity.npc.LOTREntityGundabadOrc;
import lotr.common.entity.npc.LOTREntityGundabadOrcArcher;
import lotr.common.entity.npc.LOTREntityGundabadOrcMercenaryCaptain;
import lotr.common.entity.npc.LOTREntityGundabadOrcTrader;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.structure2.LOTRWorldGenCampBase;
import lotr.common.world.structure2.LOTRWorldGenGundabadForgeTent;
import lotr.common.world.structure2.LOTRWorldGenGundabadTent;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenGundabadCamp
extends LOTRWorldGenCampBase {
    public LOTRWorldGenGundabadCamp(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tableBlock = LOTRMod.commandTable;
        this.fenceBlock = LOTRMod.fence;
        this.fenceMeta = 3;
        this.fenceGateBlock = LOTRMod.fenceGateCharred;
        this.hasOrcTorches = true;
        this.hasSkulls = true;
    }

    @Override
    protected LOTRWorldGenStructureBase2 createTent(boolean flag, Random random) {
        if (random.nextInt(6) == 0) {
            return new LOTRWorldGenGundabadForgeTent(false);
        }
        return new LOTRWorldGenGundabadTent(false);
    }

    @Override
    protected LOTREntityNPC getCampCaptain(World world, Random random) {
        return random.nextBoolean() ? new LOTREntityGundabadOrcTrader(world) : new LOTREntityGundabadOrcMercenaryCaptain(world);
    }

    @Override
    protected void placeNPCRespawner(World world, Random random, int i, int j, int k) {
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityGundabadOrc.class, LOTREntityGundabadOrcArcher.class);
        respawner.setCheckRanges(24, -12, 12, 12);
        respawner.setSpawnRanges(8, -4, 4, 16);
        this.placeNPCRespawner(respawner, world, i, j, k);
    }
}

