/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemEru2
extends Item {
    public LOTRItemEru2() {
        this.maxStackSize = 1;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDevelopers);
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }
}

