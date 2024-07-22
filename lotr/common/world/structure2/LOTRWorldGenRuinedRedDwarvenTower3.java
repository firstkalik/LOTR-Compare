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
import lotr.common.entity.npc.LOTREntityDurmethOrcMercenaryCaptain;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenRuinedRedDwarvenTower5;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRuinedRedDwarvenTower3
extends LOTRWorldGenRuinedRedDwarvenTower5 {
    private boolean isGundabad;

    public LOTRWorldGenRuinedRedDwarvenTower3(boolean flag) {
        super(flag);
        this.ruined = true;
        this.glowBrickBlock = this.brickBlock;
        this.glowBrickMeta = this.brickMeta;
    }

    @Override
    protected LOTREntityNPC getCommanderNPC(World world) {
        if (this.isGundabad) {
            return new LOTREntityDurmethOrcMercenaryCaptain(world);
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
                this.barsBlock = LOTRMod.silverBars;
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
            this.bannerType = LOTRItemBanner.BannerType.DURMETH;
            this.chestContents = LOTRChestContents.GUNDABAD_TENT;
        } else {
            this.gateBlock = LOTRMod.gateSilver;
            this.tableBlock = LOTRMod.reddwarvenTable;
            this.forgeBlock = LOTRMod.dwarvenForge;
            this.bannerType = null;
            this.chestContents = LOTRChestContents.LOTRChestContents2.REDDWARFTOWER;
        }
    }
}

