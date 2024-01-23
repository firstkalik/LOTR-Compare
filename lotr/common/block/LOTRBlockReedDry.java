/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockReed;
import net.minecraft.world.World;

public class LOTRBlockReedDry
extends LOTRBlockReed {
    @Override
    protected boolean canReedGrow(World world, int i, int j, int k) {
        return false;
    }
}

