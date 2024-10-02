/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemDriedKelp
extends LOTRItemFood {
    public LOTRItemDriedKelp() {
        super(2, 0.2f, false);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
    }
}

