/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityStonefootCap;
import lotr.common.entity.npc.LOTREntityStonefootFlameThrower;
import lotr.common.entity.npc.LOTREntityStonefootWarrior;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenMordorStructure;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class LOTRWorldGenRedDwarvenFort
extends LOTRWorldGenMordorStructure {
    protected LOTRChestContents chestContents;

    public LOTRWorldGenRedDwarvenFort(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        LOTREntityStonefootWarrior uruk;
        int l;
        this.setOriginAndRotation(world, i, j, k, 0, 30);
        this.setupRandomBlocks(random);
        this.loadStrScan("redfort");
        this.generateStrScan(world, random, 0, 1, 0);
        this.chestContents = LOTRChestContents.URUK_TENT;
        LOTREntityStonefootCap captain = new LOTREntityStonefootCap(world);
        captain.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(captain, world, 16, 3, -16, 2);
        int uruks = 2;
        for (int l2 = 0; l2 < uruks; ++l2) {
            LOTREntityStonefootWarrior uruk2 = random.nextInt(3) == 0 ? new LOTREntityStonefootFlameThrower(world) : new LOTREntityStonefootWarrior(world);
            uruk2.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk2, world, 16, 3, -12, 2);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityStonefootWarrior.class, LOTREntityStonefootFlameThrower.class);
        respawner.setCheckRanges(32, -16, 20, 24);
        respawner.setSpawnRanges(0, 0, 0, 0);
        this.placeNPCRespawner(respawner, world, 16, 3, -16);
        for (l = 0; l < uruks; ++l) {
            uruk = random.nextInt(3) == 0 ? new LOTREntityStonefootFlameThrower(world) : new LOTREntityStonefootWarrior(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 16, 3, -22, 2);
        }
        new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityStonefootWarrior.class, LOTREntityStonefootFlameThrower.class);
        respawner.setCheckRanges(32, -16, 20, 24);
        respawner.setSpawnRanges(0, 0, 0, 0);
        for (l = 0; l < uruks; ++l) {
            uruk = random.nextInt(3) == 0 ? new LOTREntityStonefootFlameThrower(world) : new LOTREntityStonefootWarrior(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 9, 25, -28, 2);
        }
        new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityStonefootWarrior.class, LOTREntityStonefootFlameThrower.class);
        respawner.setCheckRanges(0, 0, 0, 15);
        respawner.setSpawnRanges(0, 0, 0, 0);
        for (l = 0; l < uruks; ++l) {
            uruk = random.nextInt(3) == 0 ? new LOTREntityStonefootFlameThrower(world) : new LOTREntityStonefootWarrior(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 27, 25, -28, 2);
        }
        new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityStonefootWarrior.class, LOTREntityStonefootFlameThrower.class);
        for (l = 0; l < uruks; ++l) {
            uruk = random.nextInt(3) == 0 ? new LOTREntityStonefootFlameThrower(world) : new LOTREntityStonefootWarrior(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 9, 25, -15, 2);
        }
        new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityStonefootWarrior.class, LOTREntityStonefootFlameThrower.class);
        for (l = 0; l < uruks; ++l) {
            uruk = random.nextInt(3) == 0 ? new LOTREntityStonefootFlameThrower(world) : new LOTREntityStonefootWarrior(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 27, 25, -15, 2);
        }
        new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityStonefootWarrior.class, LOTREntityStonefootFlameThrower.class);
        return true;
    }
}

