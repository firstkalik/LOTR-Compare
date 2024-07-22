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
import lotr.common.entity.npc.LOTREntityGundabadOrcMercenaryCaptain;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDwarvenTower2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRuinedDwarvenTower
extends LOTRWorldGenDwarvenTower2 {
    private boolean isGundabad;

    public LOTRWorldGenRuinedDwarvenTower(boolean flag) {
        super(flag);
        this.ruined = true;
        this.glowBrickBlock = this.brickBlock;
        this.glowBrickMeta = this.brickMeta;
    }

    @Override
    protected LOTREntityNPC getCommanderNPC(World world) {
        if (this.isGundabad) {
            return new LOTREntityGundabadOrcMercenaryCaptain(world);
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
        if (random.nextInt(4) == 0) {
            this.barsBlock = Blocks.air;
        } else {
            int randomBars = random.nextInt(4);
            if (randomBars == 0) {
                this.barsBlock = LOTRMod.winddwarfBars;
            } else if (randomBars == 1) {
                this.barsBlock = LOTRMod.orcSteelBars;
            } else if (randomBars == 2) {
                this.barsBlock = Blocks.iron_bars;
            } else if (randomBars == 3) {
                this.barsBlock = LOTRMod.bronzeBars;
            }
        }
        boolean bl = this.isGundabad = random.nextInt(3) == 0;
        if (this.isGundabad) {
            this.gateBlock = LOTRMod.gateOrc;
            this.tableBlock = LOTRMod.gundabadTable;
            this.forgeBlock = LOTRMod.orcForge;
            this.bannerType = LOTRItemBanner.BannerType.GUNDABAD;
            this.chestContents = LOTRChestContents.GUNDABAD_TENT;
        } else {
            this.gateBlock = LOTRMod.gateDwarven;
            this.tableBlock = LOTRMod.dwarvenTable;
            this.forgeBlock = LOTRMod.dwarvenForge;
            this.bannerType = null;
            this.chestContents = LOTRChestContents.LOTRChestContents2.DWARVEN_TOWER2;
        }
    }

    @Override
    protected void placeBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick9, 6);
        } else {
            super.placeBrick(world, random, i, j, k);
        }
    }

    @Override
    protected void placeBrickSlab(World world, Random random, int i, int j, int k, boolean flip) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle120, 2 | (flip ? 8 : 0));
        } else {
            super.placeBrickSlab(world, random, i, j, k, flip);
        }
    }

    @Override
    protected void placeBrickStair(World world, Random random, int i, int j, int k, int meta) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsGreyDwarfCracked, meta);
        } else {
            super.placeBrickStair(world, random, i, j, k, meta);
        }
    }

    @Override
    protected void placeBrickWall(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.wallStone8, 5);
        } else {
            super.placeBrickWall(world, random, i, j, k);
        }
    }

    @Override
    protected void placePillar(World world, Random random, int i, int j, int k) {
        if (random.nextInt(4) == 0) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.pillar4, 5);
        } else {
            super.placePillar(world, random, i, j, k);
        }
    }
}

