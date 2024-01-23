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
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.inventory.LOTRSlotMillstone;
import lotr.common.recipe.LOTRMillstoneRecipes;
import lotr.common.tileentity.LOTRTileEntityMillstone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LOTRContainerMillstone
extends Container {
    private LOTRTileEntityMillstone theMillstone;
    private int currentMillTime = 0;
    private boolean isMilling;

    public LOTRContainerMillstone(InventoryPlayer inv, LOTRTileEntityMillstone millstone) {
        int i;
        this.theMillstone = millstone;
        this.addSlotToContainer(new Slot((IInventory)millstone, 0, 84, 25));
        this.addSlotToContainer((Slot)new LOTRSlotMillstone(inv.player, millstone, 1, 84, 71));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 100 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 158));
        }
    }

    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate((Container)this, 0, this.theMillstone.currentMillTime);
        crafting.sendProgressBarUpdate((Container)this, 1, this.theMillstone.isMilling ? 1 : 0);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting crafting = (ICrafting)this.crafters.get(i);
            if (this.currentMillTime != this.theMillstone.currentMillTime) {
                crafting.sendProgressBarUpdate((Container)this, 0, this.theMillstone.currentMillTime);
            }
            if (this.isMilling == this.theMillstone.isMilling) continue;
            crafting.sendProgressBarUpdate((Container)this, 1, this.theMillstone.isMilling ? 1 : 0);
        }
        this.currentMillTime = this.theMillstone.currentMillTime;
        this.isMilling = this.theMillstone.isMilling;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.theMillstone.currentMillTime = j;
        }
        if (i == 1) {
            this.theMillstone.isMilling = j == 1;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theMillstone.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 1) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (i != 0 ? (LOTRMillstoneRecipes.getMillingResult(itemstack1) != null ? !this.mergeItemStack(itemstack1, 0, 1, false) : (i >= 2 && i < 29 ? !this.mergeItemStack(itemstack1, 29, 38, false) : i >= 29 && i < 38 && !this.mergeItemStack(itemstack1, 2, 29, false))) : !this.mergeItemStack(itemstack1, 2, 38, false)) {
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

