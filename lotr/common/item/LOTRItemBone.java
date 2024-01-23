/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemBone
extends Item {
    public LOTRItemBone() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
        this.setFull3D();
    }
}

