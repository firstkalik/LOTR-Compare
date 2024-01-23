/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.inventory;

import java.util.List;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRContainerNPCMountInventory
extends Container {
    private IInventory theMountInv;
    private LOTREntityNPCRideable theMount;

    public LOTRContainerNPCMountInventory(IInventory playerInv, IInventory mountInv, final LOTREntityNPCRideable mount) {
        int j;
        this.theMountInv = mountInv;
        this.theMount = mount;
        mountInv.openInventory();
        this.addSlotToContainer(new Slot(mountInv, 0, 8, 18){

            public boolean isItemValid(ItemStack itemstack) {
                return super.isItemValid(itemstack) && itemstack.getItem() == Items.saddle && !this.getHasStack();
            }
        });
        this.addSlotToContainer(new Slot(mountInv, 1, 8, 36){

            public boolean isItemValid(ItemStack itemstack) {
                return super.isItemValid(itemstack) && mount.isMountArmorValid(itemstack);
            }
        });
        int chestRows = 3;
        int yOffset = (chestRows - 4) * 18;
        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(playerInv, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + yOffset));
            }
        }
        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(playerInv, j, 8 + j * 18, 160 + yOffset));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theMountInv.isUseableByPlayer(entityplayer) && this.theMount.isEntityAlive() && this.theMount.getDistanceToEntity((Entity)entityplayer) < 8.0f;
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (slotIndex < this.theMountInv.getSizeInventory() ? !this.mergeItemStack(itemstack1, this.theMountInv.getSizeInventory(), this.inventorySlots.size(), true) : (this.getSlot(1).isItemValid(itemstack1) && !this.getSlot(1).getHasStack() ? !this.mergeItemStack(itemstack1, 1, 2, false) : (this.getSlot(0).isItemValid(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) : this.theMountInv.getSizeInventory() <= 2 || !this.mergeItemStack(itemstack1, 2, this.theMountInv.getSizeInventory(), false)))) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        this.theMountInv.closeInventory();
    }

}

