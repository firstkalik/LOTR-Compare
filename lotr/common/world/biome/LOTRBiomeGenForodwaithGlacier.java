/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiomeGenForodwaithMountains;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class LOTRBiomeGenForodwaithGlacier
extends LOTRBiomeGenForodwaithMountains {
    public LOTRBiomeGenForodwaithGlacier(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.ice;
        this.fillerBlock = Blocks.ice;
    }
}

