/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.block.LOTRBlockGoran;
import lotr.common.item.LOTRItemBlockMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class LOTRItemBlockGoran
extends LOTRItemBlockMetadata {
    public LOTRItemBlockGoran(Block block) {
        super(block);
    }

    public String getItemStackDisplayName(ItemStack itemstack) {
        int i = itemstack.getItemDamage();
        if (i >= LOTRBlockGoran.displayNames.length) {
            i = 0;
        }
        return LOTRBlockGoran.displayNames[i];
    }
}

