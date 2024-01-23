/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenHarnedorPalisade;
import net.minecraft.block.Block;

public class LOTRWorldGenHarnedorPalisadeRuined
extends LOTRWorldGenHarnedorPalisade {
    public LOTRWorldGenHarnedorPalisadeRuined(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean isRuined() {
        return true;
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        if (random.nextBoolean()) {
            this.woodBlock = LOTRMod.wood;
            this.woodMeta = 3;
        }
    }
}

