/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common;

import net.minecraft.block.Block;

public class BlockChance {
    private final Block block;
    private final int meta;
    private final float chance;

    public BlockChance(Block block, int meta, float chance) {
        this.block = block;
        this.meta = meta;
        this.chance = chance;
    }

    public Block getBlock() {
        return this.block;
    }

    public int getMeta() {
        return this.meta;
    }

    public float getChance() {
        return this.chance;
    }
}

