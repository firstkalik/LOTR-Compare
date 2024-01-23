/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityHighElfSmith;
import lotr.common.world.structure2.LOTRWorldGenElvenForge;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenHighElvenForge
extends LOTRWorldGenElvenForge {
    public LOTRWorldGenHighElvenForge(boolean flag) {
        super(flag);
        this.brickBlock = LOTRMod.brick3;
        this.brickMeta = 2;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 10;
        this.slabBlock = LOTRMod.slabSingle5;
        this.slabMeta = 5;
        this.carvedBrickBlock = LOTRMod.brick2;
        this.carvedBrickMeta = 13;
        this.wallBlock = LOTRMod.wall2;
        this.wallMeta = 11;
        this.stairBlock = LOTRMod.stairsHighElvenBrick;
        this.torchBlock = LOTRMod.highElvenTorch;
        this.tableBlock = LOTRMod.highElvenTable;
        this.barsBlock = LOTRMod.highElfBars;
        this.woodBarsBlock = LOTRMod.highElfWoodBars;
        this.roofBlock = LOTRMod.clayTileDyed;
        this.roofMeta = 3;
        this.roofStairBlock = LOTRMod.stairsClayTileDyedLightBlue;
    }

    @Override
    protected LOTREntityElf getElf(World world) {
        return new LOTREntityHighElfSmith(world);
    }
}

