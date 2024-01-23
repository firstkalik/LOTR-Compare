/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemDagger;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemSting
extends LOTRItemDagger
implements LOTRStoryItem {
    public LOTRItemSting() {
        super(Item.ToolMaterial.IRON);
        this.setMaxDamage(700);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage += 2.0f;
    }

    public float func_150893_a(ItemStack itemstack, Block block) {
        if (block == LOTRMod.webUngoliant) {
            return 15.0f;
        }
        return super.func_150893_a(itemstack, block);
    }
}

