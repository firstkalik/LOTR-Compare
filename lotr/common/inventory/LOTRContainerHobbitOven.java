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
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.tileentity.TileEntityFurnace
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.tileentity.LOTRTileEntityHobbitOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class LOTRContainerHobbitOven
extends Container {
    private LOTRTileEntityHobbitOven theOven;
    private int currentCookTime = 0;
    private int ovenCookTime = 0;
    private int currentItemFuelValue = 0;

    public LOTRContainerHobbitOven(InventoryPlayer inv, LOTRTileEntityHobbitOven oven) {
        int i;
        this.theOven = oven;
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)oven, i, 8 + i * 18, 21));
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer((Slot)new SlotFurnace(inv.player, (IInventory)oven, i + 9, 8 + i * 18, 67));
        }
        this.addSlotToContainer(new Slot((IInventory)oven, 18, 80, 111));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 133 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 191));
        }
    }

    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate((Container)this, 0, this.theOven.currentCookTime);
        crafting.sendProgressBarUpdate((Container)this, 1, this.theOven.ovenCookTime);
        crafting.sendProgressBarUpdate((Container)this, 2, this.theOven.currentItemFuelValue);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting crafting = (ICrafting)this.crafters.get(i);
            if (this.currentCookTime != this.theOven.currentCookTime) {
                crafting.sendProgressBarUpdate((Container)this, 0, this.theOven.currentCookTime);
            }
            if (this.ovenCookTime != this.theOven.ovenCookTime) {
                crafting.sendProgressBarUpdate((Container)this, 1, this.theOven.ovenCookTime);
            }
            if (this.currentItemFuelValue == this.theOven.currentItemFuelValue) continue;
            crafting.sendProgressBarUpdate((Container)this, 2, this.theOven.currentItemFuelValue);
        }
        this.currentCookTime = this.theOven.currentCookTime;
        this.ovenCookTime = this.theOven.ovenCookTime;
        this.currentItemFuelValue = this.theOven.currentItemFuelValue;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.theOven.currentCookTime = j;
        }
        if (i == 1) {
            this.theOven.ovenCookTime = j;
        }
        if (i == 2) {
            this.theOven.currentItemFuelValue = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theOven.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i >= 9 && i < 18) {
                if (!this.mergeItemStack(itemstack1, 19, 55, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (i >= 9 && i != 18 ? (LOTRTileEntityHobbitOven.isCookResultAcceptable(FurnaceRecipes.smelting().getSmeltingResult(itemstack1)) ? !this.mergeItemStack(itemstack1, 0, 9, false) : (TileEntityFurnace.isItemFuel((ItemStack)itemstack1) ? !this.mergeItemStack(itemstack1, 18, 19, false) : (i >= 19 && i < 46 ? !this.mergeItemStack(itemstack1, 46, 55, false) : i >= 46 && i < 55 && !this.mergeItemStack(itemstack1, 19, 46, false)))) : !this.mergeItemStack(itemstack1, 19, 55, false)) {
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

