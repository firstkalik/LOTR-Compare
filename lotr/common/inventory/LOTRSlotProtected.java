/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package lotr.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LOTRSlotProtected
extends Slot {
    public LOTRSlotProtected(IInventory inv, int i, int j, int k) {
        super(inv, i, j, k);
    }

    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }
}

