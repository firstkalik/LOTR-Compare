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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityFurnace
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.inventory.LOTRSlotUnsmeltResult;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class LOTRContainerUnsmeltery
extends Container {
    private LOTRTileEntityUnsmeltery theUnsmeltery;
    private int currentSmeltTime = 0;
    private int forgeSmeltTime = 0;
    private int currentItemFuelValue = 0;

    public LOTRContainerUnsmeltery(InventoryPlayer inv, LOTRTileEntityUnsmeltery unsmeltery) {
        int i;
        this.theUnsmeltery = unsmeltery;
        this.addSlotToContainer(new Slot((IInventory)unsmeltery, 0, 56, 17));
        this.addSlotToContainer(new Slot((IInventory)unsmeltery, 1, 56, 53));
        this.addSlotToContainer((Slot)new LOTRSlotUnsmeltResult(unsmeltery, 2, 116, 35));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate((Container)this, 0, this.theUnsmeltery.currentSmeltTime);
        crafting.sendProgressBarUpdate((Container)this, 1, this.theUnsmeltery.forgeSmeltTime);
        crafting.sendProgressBarUpdate((Container)this, 2, this.theUnsmeltery.currentItemFuelValue);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting crafting = (ICrafting)this.crafters.get(i);
            if (this.currentSmeltTime != this.theUnsmeltery.currentSmeltTime) {
                crafting.sendProgressBarUpdate((Container)this, 0, this.theUnsmeltery.currentSmeltTime);
            }
            if (this.forgeSmeltTime != this.theUnsmeltery.forgeSmeltTime) {
                crafting.sendProgressBarUpdate((Container)this, 1, this.theUnsmeltery.forgeSmeltTime);
            }
            if (this.currentItemFuelValue == this.theUnsmeltery.currentItemFuelValue) continue;
            crafting.sendProgressBarUpdate((Container)this, 2, this.theUnsmeltery.currentItemFuelValue);
        }
        this.currentSmeltTime = this.theUnsmeltery.currentSmeltTime;
        this.forgeSmeltTime = this.theUnsmeltery.forgeSmeltTime;
        this.currentItemFuelValue = this.theUnsmeltery.currentItemFuelValue;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.theUnsmeltery.currentSmeltTime = j;
        }
        if (i == 1) {
            this.theUnsmeltery.forgeSmeltTime = j;
        }
        if (i == 2) {
            this.theUnsmeltery.currentItemFuelValue = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theUnsmeltery.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (i != 1 && i != 0 ? (this.theUnsmeltery.canBeUnsmelted(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) : (TileEntityFurnace.isItemFuel((ItemStack)itemstack1) ? !this.mergeItemStack(itemstack1, 1, 2, false) : (i >= 3 && i < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : i >= 30 && i < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)))) : !this.mergeItemStack(itemstack1, 3, 39, false)) {
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

