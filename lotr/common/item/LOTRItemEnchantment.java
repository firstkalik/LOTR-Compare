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
import lotr.common.enchant.LOTREnchantment;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemEnchantment
extends Item {
    public final LOTREnchantment theEnchant;

    public LOTRItemEnchantment(LOTREnchantment ench) {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
        this.theEnchant = ench;
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }
}

