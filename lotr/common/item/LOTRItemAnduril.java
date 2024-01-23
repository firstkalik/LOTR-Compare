/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemAnduril
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemAnduril() {
        super(Item.ToolMaterial.IRON);
        this.setMaxDamage(1500);
        this.lotrWeaponDamage = 9.0f;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }
}

