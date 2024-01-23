/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.common.inventory.LOTRSlotDaleCracker;
import lotr.common.item.LOTRItemDaleCracker;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketSealCracker;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRContainerDaleCracker
extends Container {
    private ItemStack theCrackerItem;
    private IInventory crackerInventory;
    private int capacity;

    public LOTRContainerDaleCracker(EntityPlayer entityplayer) {
        int i;
        this.theCrackerItem = entityplayer.inventory.getCurrentItem();
        this.capacity = 3;
        this.crackerInventory = new InventoryBasic("cracker", false, this.capacity);
        for (i = 0; i < this.capacity; ++i) {
            this.addSlotToContainer((Slot)new LOTRSlotDaleCracker(this.crackerInventory, i, 62 + i * 18, 24));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)entityplayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)entityplayer.inventory, i, 8 + i * 18, 142));
        }
    }

    public boolean isCrackerInvEmpty() {
        for (int i = 0; i < this.crackerInventory.getSizeInventory(); ++i) {
            ItemStack itemstack = this.crackerInventory.getStackInSlot(i);
            if (itemstack == null) continue;
            return false;
        }
        return true;
    }

    public void sendSealingPacket(EntityPlayer entityplayer) {
        LOTRPacketSealCracker packet = new LOTRPacketSealCracker();
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }

    public void receiveSealingPacket(EntityPlayer entityplayer) {
        if (!this.isCrackerInvEmpty()) {
            InventoryBasic tempContents = new InventoryBasic("crackerTemp", false, this.crackerInventory.getSizeInventory());
            for (int i = 0; i < tempContents.getSizeInventory(); ++i) {
                tempContents.setInventorySlotContents(i, this.crackerInventory.getStackInSlot(i));
                this.crackerInventory.setInventorySlotContents(i, null);
            }
            LOTRItemDaleCracker.setEmpty(this.theCrackerItem, false);
            LOTRItemDaleCracker.setSealingPlayerName(this.theCrackerItem, entityplayer.getCommandSenderName());
            LOTRItemDaleCracker.setCustomCrackerContents(this.theCrackerItem, (IInventory)tempContents);
        }
    }

    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        if (!entityplayer.worldObj.isRemote) {
            for (int i = 0; i < this.crackerInventory.getSizeInventory(); ++i) {
                ItemStack itemstack = this.crackerInventory.getStackInSlotOnClosing(i);
                if (itemstack == null) continue;
                entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
            }
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return ItemStack.areItemStacksEqual((ItemStack)this.theCrackerItem, (ItemStack)entityplayer.inventory.getCurrentItem());
    }

    public ItemStack slotClick(int slotNo, int j, int k, EntityPlayer entityplayer) {
        if (slotNo >= 0 && slotNo < this.inventorySlots.size()) {
            Slot slot = (Slot)this.inventorySlots.get(slotNo);
            if (slot.inventory == entityplayer.inventory && slot.getSlotIndex() == entityplayer.inventory.currentItem) {
                return null;
            }
        }
        return super.slotClick(slotNo, j, k, entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        Slot aCrackerSlot = this.getSlotFromInventory(this.crackerInventory, 0);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < this.capacity ? !this.mergeItemStack(itemstack1, this.capacity, this.capacity + 36, true) : aCrackerSlot.isItemValid(itemstack1) && !this.mergeItemStack(itemstack1, 0, this.capacity, false)) {
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

