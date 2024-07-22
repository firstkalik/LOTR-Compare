/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityIronfistCap;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenRuinedRedDwarvenTower4;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRedDwarvenTower
extends LOTRWorldGenRuinedRedDwarvenTower4 {
    private boolean isDwarf;

    public LOTRWorldGenRedDwarvenTower(boolean flag) {
        super(flag);
        this.ruined = true;
        this.glowBrickBlock = this.brickBlock;
        this.glowBrickMeta = this.brickMeta;
    }

    @Override
    protected LOTREntityNPC getCommanderNPC(World world) {
        if (this.isDwarf) {
            return new LOTREntityIronfistCap(world);
        }
        return null;
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        if (random.nextInt(3) == 0) {
            this.plankBlock = LOTRMod.planks;
            this.plankMeta = 3;
            this.plankSlabBlock = LOTRMod.woodSlabSingle;
            this.plankSlabMeta = 3;
        }
        if (random.nextInt(3) == 0) {
            this.barsBlock = Blocks.air;
        } else {
            int randomBars = random.nextInt(1);
            if (randomBars == 0) {
                this.barsBlock = LOTRMod.silverBars;
            }
        }
        boolean bl = this.isDwarf = random.nextInt(3) == 0;
        if (this.isDwarf) {
            this.gateBlock = LOTRMod.gateSilver;
            this.tableBlock = LOTRMod.reddwarvenTable;
            this.forgeBlock = LOTRMod.dwarvenForge;
            this.bannerType = LOTRItemBanner.BannerType.IRONFISTS;
            this.chestContents = LOTRChestContents.LOTRChestContents2.IRONFISTS;
        } else {
            this.gateBlock = Blocks.air;
            this.tableBlock = LOTRMod.reddwarvenTable;
            this.forgeBlock = LOTRMod.dwarvenForge;
            this.bannerType = null;
            this.chestContents = LOTRChestContents.LOTRChestContents2.IRONFISTS;
        }
    }

    @Override
    protected void placeBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick7, 0);
        } else {
            super.placeBrick(world, random, i, j, k);
        }
    }

    @Override
    protected void placeBrickSlab(World world, Random random, int i, int j, int k, boolean flip) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle122, 0 | (flip ? 8 : 0));
        } else {
            super.placeBrickSlab(world, random, i, j, k, flip);
        }
    }

    @Override
    protected void placeBrickStair(World world, Random random, int i, int j, int k, int meta) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsSarnkaranBrickCracked, meta);
        } else {
            super.placeBrickStair(world, random, i, j, k, meta);
        }
    }

    @Override
    protected void placeBrickWall(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.wallSarnkaran, 0);
        } else {
            super.placeBrickWall(world, random, i, j, k);
        }
    }

    @Override
    protected void placePillar(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.pillar4, 0);
        } else {
            super.placePillar(world, random, i, j, k);
        }
    }
}

