/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.item.LOTRItemBlockMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class LOTRItemFenceVanilla
extends LOTRItemBlockMetadata {
    public LOTRItemFenceVanilla(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "tile.lotr.fenceVanilla." + itemstack.getItemDamage();
    }
}

