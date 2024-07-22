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
 *  net.minecraft.item.ItemStack
 */
package lotr.common.inventory;

import java.util.List;
import lotr.common.entity.npc.LOTREntityGollum;
import lotr.common.inventory.LOTRInventoryNPC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LOTRContainerGollum
extends Container {
    public LOTREntityGollum theGollum;

    public LOTRContainerGollum(InventoryPlayer inv, LOTREntityGollum gollum) {
        int j;
        int i;
        this.theGollum = gollum;
        for (i = 0; i < LOTREntityGollum.INV_ROWS; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)gollum.inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 144));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theGollum != null && this.theGollum.getGollumOwner() == entityplayer && entityplayer.getDistanceSqToEntity((Entity)this.theGollum) <= 144.0 && this.theGollum.isEntityAlive();
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < LOTREntityGollum.INV_ROWS * 9 ? !this.mergeItemStack(itemstack1, LOTREntityGollum.INV_ROWS * 9, this.inventorySlots.size(), true) : !this.mergeItemStack(itemstack1, 0, LOTREntityGollum.INV_ROWS * 9, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(entityplayer, itemstack1);
        }
        return itemstack;
    }
}

