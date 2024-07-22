/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenUrukTent;
import net.minecraft.block.Block;

public class LOTRWorldGenAngbandForge
extends LOTRWorldGenUrukTent {
    public LOTRWorldGenAngbandForge(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tentBlock = LOTRMod.utumnoBrick;
        this.tentMeta = 0;
        this.fenceBlock = LOTRMod.wallUtumno;
        this.fenceMeta = 0;
        this.hasOrcForge = true;
    }
}

