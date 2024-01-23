/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemAxe
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class LOTRItemAxe
extends ItemAxe {
    public LOTRItemAxe(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemAxe(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabTools);
        this.setHarvestLevel("axe", material.getHarvestLevel());
    }
}

