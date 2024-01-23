/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemOrcSkullStaff
extends LOTRItemSword {
    public LOTRItemOrcSkullStaff() {
        super(LOTRMaterial.MORDOR);
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == Items.skull;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return null;
    }
}

