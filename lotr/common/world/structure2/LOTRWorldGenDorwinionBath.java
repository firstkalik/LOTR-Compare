/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.entity.npc.LOTREntityDorwinionMan;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.structure2.LOTRWorldGenDorwinionHouse;
import lotr.common.world.structure2.LOTRWorldGenGondorBath;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenDorwinionBath
extends LOTRWorldGenGondorBath {
    private LOTRWorldGenDorwinionHouse houseGenForBlocks;

    public LOTRWorldGenDorwinionBath(boolean flag) {
        super(flag);
        this.houseGenForBlocks = new LOTRWorldGenDorwinionHouse(flag);
    }

    @Override
    protected LOTREntityNPC createBather(World world) {
        return new LOTREntityDorwinionMan(world);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.houseGenForBlocks.setupRandomBlocks(random);
        this.brickBlock = this.houseGenForBlocks.brickBlock;
        this.brickMeta = this.houseGenForBlocks.brickMeta;
        this.brickSlabBlock = this.houseGenForBlocks.brickSlabBlock;
        this.brickSlabMeta = this.houseGenForBlocks.brickSlabMeta;
        this.brickStairBlock = this.houseGenForBlocks.brickStairBlock;
        this.brickWallBlock = this.houseGenForBlocks.brickWallBlock;
        this.brickWallMeta = this.houseGenForBlocks.brickWallMeta;
        this.pillarBlock = this.houseGenForBlocks.pillarBlock;
        this.pillarMeta = this.houseGenForBlocks.pillarMeta;
        this.brick2Block = this.houseGenForBlocks.clayBlock;
        this.brick2Meta = this.houseGenForBlocks.clayMeta;
        this.brick2SlabBlock = this.houseGenForBlocks.claySlabBlock;
        this.brick2SlabMeta = this.houseGenForBlocks.claySlabMeta;
        this.brick2StairBlock = this.houseGenForBlocks.clayStairBlock;
    }
}

