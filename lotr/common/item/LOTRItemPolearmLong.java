/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 */
package lotr.common.item;

import lotr.common.item.LOTRItemPolearm;
import lotr.common.item.LOTRMaterial;
import net.minecraft.item.Item;

public class LOTRItemPolearmLong
extends LOTRItemPolearm {
    public LOTRItemPolearmLong(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemPolearmLong(Item.ToolMaterial material) {
        super(material);
    }
}

