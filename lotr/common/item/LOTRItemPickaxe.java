/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemPickaxe
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class LOTRItemPickaxe
extends ItemPickaxe {
    public LOTRItemPickaxe(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemPickaxe(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabTools);
    }
}

