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
import lotr.common.entity.npc.LOTREntityWoodElfSmith;
import lotr.common.world.structure2.LOTRWorldGenElvenForge;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenWoodElvenForge
extends LOTRWorldGenElvenForge {
    public LOTRWorldGenWoodElvenForge(boolean flag) {
        super(flag);
        this.brickBlock = LOTRMod.brick3;
        this.brickMeta = 5;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 12;
        this.slabBlock = LOTRMod.slabSingle6;
        this.slabMeta = 2;
        this.carvedBrickBlock = LOTRMod.brick2;
        this.carvedBrickMeta = 14;
        this.wallBlock = LOTRMod.wall3;
        this.wallMeta = 0;
        this.stairBlock = LOTRMod.stairsWoodElvenBrick;
        this.torchBlock = LOTRMod.woodElvenTorch;
        this.tableBlock = LOTRMod.woodElvenTable;
        this.barsBlock = LOTRMod.woodElfBars;
        this.woodBarsBlock = LOTRMod.woodElfWoodBars;
        this.roofBlock = LOTRMod.clayTileDyed;
        this.roofMeta = 13;
        this.roofStairBlock = LOTRMod.stairsClayTileDyedGreen;
    }

    @Override
    protected LOTREntityElf getElf(World world) {
        return new LOTREntityWoodElfSmith(world);
    }
}

