/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDwarfSmithy2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class LOTRWorldGenRedMountainsSmithy2
extends LOTRWorldGenDwarfSmithy2 {
    public LOTRWorldGenRedMountainsSmithy2(boolean flag) {
        super(flag);
        this.baseBrickBlock = LOTRMod.smoothStone;
        this.baseBrickMeta = 4;
        this.carvedBrickBlock = LOTRMod.brick3;
        this.carvedBrickMeta = 1;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 4;
        this.tableBlock = Blocks.air;
        this.barsBlock = Blocks.air;
        this.brickBlock = LOTRMod.brick2;
        this.brickMeta = 2;
        this.brickSlabBlock = LOTRMod.slabSingle3;
        this.brickSlabMeta = 6;
        this.brickStairBlock = LOTRMod.stairsSarnkaranBrickCracked;
    }

    @Override
    protected LOTRChestContents getChestContents() {
        return LOTRChestContents.LOTRChestContents2.REDDWARF_SMITHY;
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        int randomWood = random.nextInt(1);
        if (randomWood == 0) {
            this.plankBlock = LOTRMod.brick7;
            this.plankMeta = 0;
            this.gateBlock = Blocks.air;
        }
    }
}

