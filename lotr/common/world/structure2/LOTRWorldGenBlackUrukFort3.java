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
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityAngbandCap;
import lotr.common.entity.npc.LOTREntityAngbandCrossbow;
import lotr.common.entity.npc.LOTREntityAngbandUruc;
import lotr.common.world.structure2.LOTRWorldGenMordorStructure;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class LOTRWorldGenBlackUrukFort3
extends LOTRWorldGenMordorStructure {
    protected Block tableBlock;

    public LOTRWorldGenBlackUrukFort3(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tableBlock = LOTRMod.angbandtable;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        this.setOriginAndRotation(world, i, j, k, rotation, 19);
        this.setupRandomBlocks(random);
        this.loadStrScan("angband_town");
        this.generateStrScan(world, random, 0, 0, 0);
        LOTREntityAngbandCap captain = new LOTREntityAngbandCap(world);
        captain.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(captain, world, 0, 1, 0, 8);
        int uruks = 4;
        for (int l = 0; l < uruks; ++l) {
            LOTREntityAngbandUruc uruk = random.nextInt(3) == 0 ? new LOTREntityAngbandCrossbow(world) : new LOTREntityAngbandUruc(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 0, 1, 0, 32);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityAngbandUruc.class, LOTREntityAngbandCrossbow.class);
        respawner.setCheckRanges(32, -16, 20, 24);
        respawner.setSpawnRanges(24, -4, 8, 24);
        this.placeNPCRespawner(respawner, world, 0, 0, 0);
        return true;
    }
}

