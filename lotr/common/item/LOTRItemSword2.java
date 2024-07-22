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
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemSword2
extends LOTRItemSword {
    public LOTRItemSword2(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemSword2(Item.ToolMaterial material) {
        super(material);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemSword2.class, 1.12f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemSword2.class, 1.0f);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemSword2.class, 1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
    }
}

