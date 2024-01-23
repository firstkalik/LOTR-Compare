/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.block.LOTRBlockGravel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class LOTRBlockObsidianGravel
extends LOTRBlockGravel {
    public Item getItemDropped(int i, Random rand, int j) {
        return Item.getItemFromBlock((Block)this);
    }
}

