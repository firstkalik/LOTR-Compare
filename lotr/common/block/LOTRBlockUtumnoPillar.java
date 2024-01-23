/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockPillarBase;
import lotr.common.world.LOTRWorldProviderUtumno;
import net.minecraft.block.Block;

public class LOTRBlockUtumnoPillar
extends LOTRBlockPillarBase
implements LOTRWorldProviderUtumno.UtumnoBlock {
    public LOTRBlockUtumnoPillar() {
        this.setPillarNames("fire", "ice", "obsidian");
        this.setHardness(1.5f);
        this.setResistance(Float.MAX_VALUE);
        this.setStepSound(Block.soundTypeStone);
    }
}

