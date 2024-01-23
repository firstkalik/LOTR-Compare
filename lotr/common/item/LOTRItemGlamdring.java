/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemGlamdring
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemGlamdring() {
        super(LOTRMaterial.GONDOLIN);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.setIsElvenBlade();
    }
}

