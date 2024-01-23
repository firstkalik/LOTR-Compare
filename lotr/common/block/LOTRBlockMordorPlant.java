/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockFlower;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import net.minecraft.world.World;

public class LOTRBlockMordorPlant
extends LOTRBlockFlower {
    public boolean canBlockStay(World world, int i, int j, int k) {
        return LOTRBiomeGenMordor.isSurfaceMordorBlock(world, i, j - 1, k);
    }
}

