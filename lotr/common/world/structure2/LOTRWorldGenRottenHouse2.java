/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import net.minecraft.block.Block;

public class LOTRWorldGenRottenHouse2
extends LOTRWorldGenRuinedHouse {
    public LOTRWorldGenRottenHouse2(boolean flag) {
        super(flag);
        this.woodBlock = LOTRMod.rottenLog;
        this.woodMeta = 0;
        this.plankBlock = LOTRMod.planksRotten;
        this.plankMeta = 0;
        this.fenceBlock = LOTRMod.fenceRotten;
        this.fenceMeta = 0;
        this.stairBlock = LOTRMod.stairsRotten;
    }
}

