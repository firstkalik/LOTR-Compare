/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
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

import java.util.List;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryHiredReplacedItems;
import lotr.common.inventory.LOTRSlotArmorStand;
import lotr.common.inventory.LOTRSlotBomb;
import lotr.common.inventory.LOTRSlotHiredReplaceItem;
import lotr.common.inventory.LOTRSlotMeleeWeapon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRContainerHiredWarriorInventory
extends Container {
    private LOTREntityNPC theNPC;
    public LOTRInventoryHiredReplacedItems npcInv;
    public IInventory proxyInv;
    private final int npcFullInvSize;
    private int npcActiveSlotCount;

    public LOTRContainerHiredWarriorInventory(InventoryPlayer inv, LOTREntityNPC entity) {
        int i;
        this.theNPC = entity;
        this.npcInv = this.theNPC.hiredReplacedInv;
        this.npcFullInvSize = this.npcInv.getSizeInventory();
        this.proxyInv = new InventoryBasic("npcTemp", false, this.npcFullInvSize);
        for (int i2 = 0; i2 < 4; ++i2) {
            LOTRSlotHiredReplaceItem slot = new LOTRSlotHiredReplaceItem(new LOTRSlotArmorStand(this.proxyInv, i2, 80, 21 + i2 * 18, i2, (Entity)inv.player), this.theNPC);
            this.addSlotToContainer((Slot)slot);
        }
        int[] arrn = new int[]{4};
        for (int i3 : arrn) {
            LOTRSlotHiredReplaceItem slot = new LOTRSlotHiredReplaceItem(new LOTRSlotMeleeWeapon(this.proxyInv, i3, 50, 48), this.theNPC);
            this.addSlotToContainer((Slot)slot);
        }
        if (this.theNPC instanceof LOTREntityOrc && ((LOTREntityOrc)this.theNPC).isOrcBombardier()) {
            int i4 = 5;
            LOTRSlotHiredReplaceItem slot = new LOTRSlotHiredReplaceItem(new LOTRSlotBomb(this.proxyInv, i4, 110, 48), this.theNPC);
            this.addSlotToContainer((Slot)slot);
        }
        for (i = 0; i < this.npcFullInvSize; ++i) {
            if (this.getSlotFromInventory(this.proxyInv, i) == null) continue;
            ++this.npcActiveSlotCount;
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 107 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 165));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theNPC != null && this.theNPC.isEntityAlive() && this.theNPC.hiredNPCInfo.isActive && this.theNPC.hiredNPCInfo.getHiringPlayer() == entityplayer && this.theNPC.hiredNPCInfo.getTask() == LOTRHiredNPCInfo.Task.WARRIOR && entityplayer.getDistanceSqToEntity((Entity)this.theNPC) <= 144.0;
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
            if (slot.inventory == this.proxyInv) {
                if (!this.mergeItemStack(itemstack1, this.npcActiveSlotCount, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else {
                for (int j = 0; j < this.npcFullInvSize; ++j) {
                    Slot npcSlot = this.getSlotFromInventory(this.proxyInv, j);
                    if (npcSlot == null) continue;
                    int npcSlotNo = npcSlot.slotNumber;
                    if (!npcSlot.isItemValid(itemstack1) || this.mergeItemStack(itemstack1, npcSlotNo, npcSlotNo + 1, false)) continue;
                    return null;
                }
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

