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
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import java.util.List;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.inventory.LOTRInventoryNPC;
import lotr.common.inventory.LOTRSlotBonemeal;
import lotr.common.inventory.LOTRSlotProtected;
import lotr.common.inventory.LOTRSlotSeeds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRContainerHiredFarmerInventory
extends Container {
    private LOTREntityNPC theNPC;

    public LOTRContainerHiredFarmerInventory(InventoryPlayer inv, LOTREntityNPC entity) {
        int i;
        this.theNPC = entity;
        this.addSlotToContainer((Slot)new LOTRSlotSeeds((IInventory)this.theNPC.hiredNPCInfo.getHiredInventory(), 0, 80, 21, this.theNPC.worldObj));
        for (i = 0; i < 2; ++i) {
            this.addSlotToContainer((Slot)new LOTRSlotProtected((IInventory)this.theNPC.hiredNPCInfo.getHiredInventory(), i + 1, 71 + i * 18, 47));
        }
        this.addSlotToContainer((Slot)new LOTRSlotBonemeal((IInventory)this.theNPC.hiredNPCInfo.getHiredInventory(), 3, 123, 34, this.theNPC.worldObj));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 79 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 137));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theNPC != null && this.theNPC.isEntityAlive() && this.theNPC.hiredNPCInfo.isActive && this.theNPC.hiredNPCInfo.getHiringPlayer() == entityplayer && this.theNPC.hiredNPCInfo.getTask() == LOTRHiredNPCInfo.Task.FARMER && entityplayer.getDistanceSqToEntity((Entity)this.theNPC) <= 144.0;
    }

    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        if (!this.theNPC.worldObj.isRemote) {
            this.theNPC.hiredNPCInfo.sendClientPacket(true);
        }
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
            } else {
                if (((Slot)this.inventorySlots.get(0)).isItemValid(itemstack1) && !this.mergeItemStack(itemstack1, 0, 1, false)) {
                    return null;
                }
                if (((Slot)this.inventorySlots.get(3)).isItemValid(itemstack1) && !this.mergeItemStack(itemstack1, 3, 4, false)) {
                    return null;
                }
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

