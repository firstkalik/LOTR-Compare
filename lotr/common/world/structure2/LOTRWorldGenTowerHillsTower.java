/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenHighElvenTower;
import net.minecraft.block.Block;

public class LOTRWorldGenTowerHillsTower
extends LOTRWorldGenHighElvenTower {
    public LOTRWorldGenTowerHillsTower(boolean flag) {
        super(flag);
        this.brickBlock = LOTRMod.brick4;
        this.brickMeta = 15;
        this.brickSlabBlock = LOTRMod.slabSingle9;
        this.brickSlabMeta = 0;
        this.brickStairBlock = LOTRMod.stairsChalkBrick;
        this.brickWallBlock = LOTRMod.wall3;
        this.brickWallMeta = 6;
        this.pillarBlock = LOTRMod.pillar2;
        this.pillarMeta = 1;
        this.floorBlock = LOTRMod.slabDouble8;
        this.floorMeta = 7;
        this.roofBlock = this.brickBlock;
        this.roofMeta = this.brickMeta;
        this.roofSlabBlock = this.brickSlabBlock;
        this.roofSlabMeta = this.brickSlabMeta;
        this.roofStairBlock = this.brickStairBlock;
    }
}

