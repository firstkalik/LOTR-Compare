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
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRStoryItem;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemAnduril3
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemAnduril3(LOTRMaterial GALVORN) {
        super(LOTRMaterial.GALVORN);
        this.setMaxDamage(2000);
        this.lotrWeaponDamage = 9.5f;
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemAnduril3.class, 1.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemAnduril3.class, 1.0f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.epic;
    }
}

