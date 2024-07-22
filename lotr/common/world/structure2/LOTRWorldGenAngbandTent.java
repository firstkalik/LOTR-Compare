/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenTentBase;
import net.minecraft.block.Block;

public class LOTRWorldGenAngbandTent
extends LOTRWorldGenTentBase {
    public LOTRWorldGenAngbandTent(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        int randomWool = random.nextInt(3);
        if (randomWool == 0 || randomWool == 1) {
            this.tentBlock = LOTRMod.utumnoBrick;
            this.tentMeta = 2;
        } else if (randomWool == 2) {
            this.tentBlock = LOTRMod.utumnoBrick;
            this.tentMeta = 4;
        }
        this.fenceBlock = LOTRMod.fence;
        this.fenceMeta = 3;
        this.tableBlock = LOTRMod.angbandtable;
        this.chestContents = LOTRChestContents.LOTRChestContents2.ANGBAND_TENT;
        this.hasOrcTorches = true;
    }
}

