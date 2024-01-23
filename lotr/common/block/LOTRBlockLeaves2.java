/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockLeavesBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class LOTRBlockLeaves2
extends LOTRBlockLeavesBase {
    public LOTRBlockLeaves2() {
        this.setLeafNames("lebethron", "beech", "holly", "banana");
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)LOTRMod.sapling2);
    }

    @Override
    protected int getSaplingChance(int meta) {
        if (meta == 3) {
            return 9;
        }
        return super.getSaplingChance(meta);
    }
}

