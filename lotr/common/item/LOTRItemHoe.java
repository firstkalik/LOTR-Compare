/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class LOTRItemHoe
extends ItemHoe {
    public LOTRItemHoe(LOTRMaterial material) {
        this(material.toToolMaterial());
        this.setMaxDamage(0);
    }

    public LOTRItemHoe(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabTools);
    }

    public float func_150893_a(ItemStack itemstack, Block block) {
        if (block == Blocks.hay_block) {
            return 10.0f;
        }
        return super.func_150893_a(itemstack, block);
    }
}

