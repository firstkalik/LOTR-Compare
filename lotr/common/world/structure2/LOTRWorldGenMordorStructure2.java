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
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public abstract class LOTRWorldGenMordorStructure2
extends LOTRWorldGenStructureBase2 {
    protected Block brickBlock;
    protected int brickMeta;
    protected Block brickSlabBlock;
    protected int brickSlabMeta;
    protected Block brickStairBlock;
    protected Block brickWallBlock;
    protected int brickWallMeta;
    protected Block brickCarvedBlock;
    protected int brickCarvedMeta;
    protected Block pillarBlock;
    protected int pillarMeta;
    protected Block smoothBlock;
    protected int smoothMeta;
    protected Block smoothSlabBlock;
    protected int smoothSlabMeta;
    protected Block tileBlock;
    protected int tileMeta;
    protected Block tileSlabBlock;
    protected int tileSlabMeta;
    protected Block tileStairBlock;
    protected Block plankBlock;
    protected int plankMeta;
    protected Block plankSlabBlock;
    protected int plankSlabMeta;
    protected Block plankStairBlock;
    protected Block fenceBlock;
    protected int fenceMeta;
    protected Block trapdoorBlock;
    protected Block beamBlock;
    protected int beamMeta;
    protected Block bedBlock;
    protected Block gateIronBlock;
    protected Block gateOrcBlock;
    protected Block barsBlock;
    protected Block chandelierBlock;
    protected int chandelierMeta;

    public LOTRWorldGenMordorStructure2(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.brickBlock = LOTRMod.utumnoBrick;
        this.brickMeta = 2;
        this.brickSlabBlock = LOTRMod.slabUtumnoSingle;
        this.brickSlabMeta = 4;
        this.brickStairBlock = LOTRMod.stairsUtumnoBrickIce;
        this.brickWallBlock = LOTRMod.wallUtumno;
        this.brickWallMeta = 1;
        this.brickCarvedBlock = LOTRMod.utumnoBrick;
        this.brickCarvedMeta = 2;
        this.pillarBlock = LOTRMod.utumnoPillar;
        this.pillarMeta = 1;
        this.smoothBlock = LOTRMod.utumnoBrick;
        this.smoothMeta = 3;
        this.smoothSlabBlock = LOTRMod.slabUtumnoSingle;
        this.smoothSlabMeta = 1;
        this.tileBlock = LOTRMod.clayTileDyed;
        this.tileMeta = 15;
        this.tileSlabBlock = LOTRMod.slabClayTileDyedSingle2;
        this.tileSlabMeta = 7;
        this.tileStairBlock = LOTRMod.stairsClayTileDyedBlack;
        this.plankBlock = LOTRMod.planks;
        this.plankMeta = 3;
        this.plankSlabBlock = LOTRMod.woodSlabSingle;
        this.plankSlabMeta = 3;
        this.plankStairBlock = LOTRMod.stairsCharred;
        this.fenceBlock = LOTRMod.fence;
        this.fenceMeta = 3;
        this.trapdoorBlock = LOTRMod.trapdoorCharred;
        this.beamBlock = LOTRMod.woodBeam1;
        this.beamMeta = 3;
        this.bedBlock = LOTRMod.orcBed;
        this.gateIronBlock = LOTRMod.gateIronBars;
        this.gateOrcBlock = LOTRMod.gateOrc;
        this.barsBlock = LOTRMod.orcSteelBars;
        this.chandelierBlock = LOTRMod.chandelier;
        this.chandelierMeta = 7;
    }

    @Override
    protected void placeOrcTorch(World world, int i, int j, int k) {
        this.setBlockAndMetadata(world, i, j, k, LOTRMod.orcTorch, 0);
        this.setBlockAndMetadata(world, i, j + 1, k, LOTRMod.orcTorch, 1);
    }
}

