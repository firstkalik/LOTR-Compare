/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemHammer
extends LOTRItemSword {
    public LOTRItemHammer(LOTRMaterial material) {
        this(material.toToolMaterial());
        this.lotrWeaponDamage += 2.5f;
    }

    public LOTRItemHammer(Item.ToolMaterial material) {
        super(material);
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.none;
    }
}

