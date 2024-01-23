/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 */
package lotr.common.item;

import lotr.common.item.LOTRItemPolearmLong;
import lotr.common.item.LOTRMaterial;
import net.minecraft.item.Item;

public class LOTRItemPike
extends LOTRItemPolearmLong {
    public LOTRItemPike(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemPike(Item.ToolMaterial material) {
        super(material);
    }
}

