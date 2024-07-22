/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAvariElfSmith;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.world.structure2.LOTRWorldGenElvenForge;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenAvariElvenForge
extends LOTRWorldGenElvenForge {
    public LOTRWorldGenAvariElvenForge(boolean flag) {
        super(flag);
        this.brickBlock = LOTRMod.brick10;
        this.brickMeta = 1;
        this.pillarBlock = LOTRMod.pillar4;
        this.pillarMeta = 6;
        this.slabBlock = LOTRMod.slabSingle121;
        this.slabMeta = 0;
        this.carvedBrickBlock = LOTRMod.brick10;
        this.carvedBrickMeta = 6;
        this.wallBlock = LOTRMod.wallStone10;
        this.wallMeta = 1;
        this.stairBlock = LOTRMod.stairsAvariBrick;
        this.torchBlock = LOTRMod.avariTorch;
        this.tableBlock = LOTRMod.avariTable;
        this.barsBlock = LOTRMod.avariBars;
        this.woodBarsBlock = LOTRMod.avariElfWoodBars;
        this.roofBlock = LOTRMod.clayTileDyed;
        this.roofMeta = 13;
        this.roofStairBlock = LOTRMod.stairsClayTileDyedGreen;
    }

    @Override
    protected LOTREntityElf getElf(World world) {
        return new LOTREntityAvariElfSmith(world);
    }
}

