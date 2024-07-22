/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 */
package lotr.common.item;

import java.util.UUID;
import lotr.common.item.LOTRItemPolearmLong;
import lotr.common.item.LOTRMaterial;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;

public class LOTRItemLance
extends LOTRItemPolearmLong {
    public static final UUID lanceSpeedBoost_id = UUID.fromString("cffdd9e5-bdd4-4127-8097-c8cafbf56c63");
    public static final AttributeModifier lanceSpeedBoost = new AttributeModifier(lanceSpeedBoost_id, "Lance speed boost", -0.2, 2).setSaved(false);

    public LOTRItemLance(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemLance(Item.ToolMaterial material) {
        super(material);
    }
}

