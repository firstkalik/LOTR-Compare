/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenGundabadTent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class LOTRWorldGenGundabadForgeTent
extends LOTRWorldGenGundabadTent {
    public LOTRWorldGenGundabadForgeTent(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tentBlock = Blocks.cobblestone;
        this.tentMeta = 0;
        this.fenceBlock = Blocks.cobblestone_wall;
        this.fenceMeta = 0;
        this.hasOrcForge = true;
    }
}

