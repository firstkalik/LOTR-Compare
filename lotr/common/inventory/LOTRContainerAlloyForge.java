/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.inventory.SlotFurnace
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityFurnace
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.tileentity.LOTRTileEntityAlloyForgeBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class LOTRContainerAlloyForge
extends Container {
    private LOTRTileEntityAlloyForgeBase theForge;
    private int currentSmeltTime = 0;
    private int forgeSmeltTime = 0;
    private int currentItemFuelValue = 0;

    public LOTRContainerAlloyForge(InventoryPlayer inv, LOTRTileEntityAlloyForgeBase forge) {
        int i;
        this.theForge = forge;
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer(new Slot((IInventory)forge, i, 53 + i * 18, 21));
        }
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer(new Slot((IInventory)forge, i + 4, 53 + i * 18, 39));
        }
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer((Slot)new SlotFurnace(inv.player, (IInventory)forge, i + 8, 53 + i * 18, 85));
        }
        this.addSlotToContainer(new Slot((IInventory)forge, 12, 80, 129));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 209));
        }
    }

    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate((Container)this, 0, this.theForge.currentSmeltTime);
        crafting.sendProgressBarUpdate((Container)this, 1, this.theForge.forgeSmeltTime);
        crafting.sendProgressBarUpdate((Container)this, 2, this.theForge.currentItemFuelValue);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object element : this.crafters) {
            ICrafting crafting = (ICrafting)element;
            if (this.currentSmeltTime != this.theForge.currentSmeltTime) {
                crafting.sendProgressBarUpdate((Container)this, 0, this.theForge.currentSmeltTime);
            }
            if (this.forgeSmeltTime != this.theForge.forgeSmeltTime) {
                crafting.sendProgressBarUpdate((Container)this, 1, this.theForge.forgeSmeltTime);
            }
            if (this.currentItemFuelValue == this.theForge.currentItemFuelValue) continue;
            crafting.sendProgressBarUpdate((Container)this, 2, this.theForge.currentItemFuelValue);
        }
        this.currentSmeltTime = this.theForge.currentSmeltTime;
        this.forgeSmeltTime = this.theForge.forgeSmeltTime;
        this.currentItemFuelValue = this.theForge.currentItemFuelValue;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.theForge.currentSmeltTime = j;
        }
        if (i == 1) {
            this.theForge.forgeSmeltTime = j;
        }
        if (i == 2) {
            this.theForge.currentItemFuelValue = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theForge.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i >= 8 && i < 12) {
                if (!this.mergeItemStack(itemstack1, 13, 49, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (i >= 8 && i != 12 ? (this.theForge.getSmeltingResult(itemstack1) != null ? !this.mergeItemStack(itemstack1, 4, 8, false) : (TileEntityFurnace.isItemFuel((ItemStack)itemstack1) ? !this.mergeItemStack(itemstack1, 12, 13, false) : (i >= 13 && i < 40 ? !this.mergeItemStack(itemstack1, 40, 49, false) : i >= 40 && i < 49 && !this.mergeItemStack(itemstack1, 13, 40, false)))) : !this.mergeItemStack(itemstack1, 13, 49, false)) {
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

