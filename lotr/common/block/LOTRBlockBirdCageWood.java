/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockBirdCage;
import net.minecraft.block.Block;

public class LOTRBlockBirdCageWood
extends LOTRBlockBirdCage {
    public LOTRBlockBirdCageWood() {
        this.setStepSound(Block.soundTypeWood);
        this.setCageTypes("wood");
    }
}

