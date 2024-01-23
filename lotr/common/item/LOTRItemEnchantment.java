/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.enchant.LOTREnchantment;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemEnchantment
extends Item {
    public final LOTREnchantment theEnchant;

    public LOTRItemEnchantment(LOTREnchantment ench) {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
        this.theEnchant = ench;
    }
}

