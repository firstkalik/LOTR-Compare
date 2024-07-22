/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRStoryItem;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemAnduril2
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemAnduril2() {
        super(Item.ToolMaterial.IRON);
        this.setMaxDamage(0);
        this.lotrWeaponDamage = 100000.0f;
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemAnduril2.class, 2.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemAnduril2.class, 2.0f);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemAnduril2.class, 1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.epic;
    }
}

