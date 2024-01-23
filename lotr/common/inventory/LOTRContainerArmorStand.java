/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package lotr.common.inventory;

import java.util.List;
import lotr.common.inventory.LOTRSlotArmorStand;
import lotr.common.tileentity.LOTRTileEntityArmorStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class LOTRContainerArmorStand
extends Container {
    private LOTRTileEntityArmorStand theArmorStand;

    public LOTRContainerArmorStand(InventoryPlayer inv, LOTRTileEntityArmorStand armorStand) {
        int i;
        this.theArmorStand = armorStand;
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer((Slot)new LOTRSlotArmorStand(armorStand, i, 80, 21 + i * 18, i, (Entity)inv.player));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 107 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 165));
        }
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer((Slot)new LOTRSlotArmorStand((IInventory)inv, 36 + (3 - i), 8, 21 + i * 18, i, (Entity)inv.player));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theArmorStand.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < 4) {
                if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
                    return null;
                }
            } else if (itemstack.getItem() instanceof ItemArmor && !this.getSlotFromInventory((IInventory)this.theArmorStand, ((ItemArmor)itemstack.getItem()).armorType).getHasStack()) {
                int j = ((ItemArmor)itemstack.getItem()).armorType;
                if (!this.mergeItemStack(itemstack1, j, j + 1, false)) {
                    return null;
                }
            } else {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize != itemstack.stackSize) {
                slot.onPickupFromSlot(entityplayer, itemstack1);
            } else {
                return null;
            }
        }
        return itemstack;
    }
}

