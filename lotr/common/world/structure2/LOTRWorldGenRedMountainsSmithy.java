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
import lotr.common.entity.npc.LOTREntityBlacklockSmith;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDwarfSmithy;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenRedMountainsSmithy
extends LOTRWorldGenDwarfSmithy {
    public LOTRWorldGenRedMountainsSmithy(boolean flag) {
        super(flag);
        this.baseBrickBlock = LOTRMod.smoothStone;
        this.baseBrickMeta = 4;
        this.carvedBrickBlock = LOTRMod.brick8;
        this.carvedBrickMeta = 0;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 4;
        this.tableBlock = LOTRMod.reddwarvenTable;
        this.barsBlock = LOTRMod.silverBars;
        this.brickBlock = LOTRMod.brick2;
        this.brickMeta = 2;
        this.brickSlabBlock = LOTRMod.slabSingle3;
        this.brickSlabMeta = 6;
        this.brickStairBlock = LOTRMod.stairsSarnkaranBrickGlow;
    }

    @Override
    protected LOTREntityBlacklockSmith createSmith(World world) {
        return new LOTREntityBlacklockSmith(world);
    }

    @Override
    protected LOTRChestContents getChestContents() {
        return LOTRChestContents.LOTRChestContents2.REDDWARF_SMITHY;
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        int randomWood = random.nextInt(1);
        if (randomWood == 0) {
            this.plankBlock = LOTRMod.planks;
            this.plankMeta = 9;
            this.gateBlock = LOTRMod.fenceGateBeech;
        } else if (randomWood == 1) {
            this.plankBlock = LOTRMod.planks;
            this.plankMeta = 13;
            this.gateBlock = LOTRMod.fenceGateLarch;
        } else if (randomWood == 2) {
            this.plankBlock = LOTRMod.planks2;
            this.plankMeta = 4;
            this.gateBlock = LOTRMod.fenceGatePine;
        } else if (randomWood == 3) {
            this.plankBlock = LOTRMod.planks2;
            this.plankMeta = 3;
            this.gateBlock = LOTRMod.fenceGateFir;
        }
    }
}

