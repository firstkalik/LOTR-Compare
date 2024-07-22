/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemCoin;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRContainerCoinExchange
extends Container {
    public IInventory coinInputInv = new InventoryCoinExchangeSlot(1);
    public IInventory exchangeInv = new InventoryCoinExchangeSlot(2);
    public LOTREntityNPC theTraderNPC;
    public boolean exchanged = false;

    public LOTRContainerCoinExchange(EntityPlayer entityplayer, LOTREntityNPC npc) {
        int i;
        this.theTraderNPC = npc;
        this.addSlotToContainer(new Slot(this.coinInputInv, 0, 80, 46){

            public boolean isItemValid(ItemStack itemstack) {
                return super.isItemValid(itemstack) && itemstack != null && itemstack.getItem() == LOTRMod.silverCoin;
            }
        });
        class SlotCoinResult
        extends Slot {
            public SlotCoinResult(IInventory inv, int i, int j, int k) {
                super(inv, i, j, k);
            }

            public boolean isItemValid(ItemStack itemstack) {
                return false;
            }

            public boolean canTakeStack(EntityPlayer entityplayer) {
                return LOTRContainerCoinExchange.this.exchanged;
            }
        }
        this.addSlotToContainer((Slot)new SlotCoinResult(this.exchangeInv, 0, 26, 46));
        this.addSlotToContainer((Slot)new SlotCoinResult(this.exchangeInv, 1, 134, 46));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)entityplayer.inventory, j + i * 9 + 9, 8 + j * 18, 106 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)entityplayer.inventory, i, 8 + i * 18, 164));
        }
        this.onCraftMatrixChanged(this.coinInputInv);
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    public void handleExchangePacket(int slot) {
        if (!this.exchanged && this.coinInputInv.getStackInSlot(0) != null && slot >= 0 && slot < this.exchangeInv.getSizeInventory() && this.exchangeInv.getStackInSlot(slot) != null) {
            this.exchanged = true;
            int coins = this.exchangeInv.getStackInSlot((int)slot).stackSize;
            int coinsTaken = 0;
            if (slot == 0) {
                coinsTaken = coins / 10;
            } else if (slot == 1) {
                coinsTaken = coins * 10;
            }
            this.coinInputInv.decrStackSize(0, coinsTaken);
            for (int i = 0; i < this.exchangeInv.getSizeInventory(); ++i) {
                if (i == slot) continue;
                this.exchangeInv.setInventorySlotContents(i, null);
            }
            this.detectAndSendChanges();
            this.theTraderNPC.playTradeSound();
        }
    }

    public void addCraftingToCrafters(ICrafting crafting) {
        this.sendClientExchangedData(crafting);
        super.addCraftingToCrafters(crafting);
    }

    public void detectAndSendChanges() {
        for (Object element : this.crafters) {
            ICrafting crafting = (ICrafting)element;
            this.sendClientExchangedData(crafting);
        }
        super.detectAndSendChanges();
    }

    private void sendClientExchangedData(ICrafting crafting) {
        crafting.sendProgressBarUpdate((Container)this, 0, this.exchanged ? 1 : 0);
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.exchanged = j == 1;
        }
    }

    public void onCraftMatrixChanged(IInventory inv) {
        if (inv == this.coinInputInv) {
            if (!this.exchanged) {
                ItemStack coin = this.coinInputInv.getStackInSlot(0);
                if (coin != null && coin.getItem() == LOTRMod.silverCoin && coin.stackSize > 0) {
                    int coins = coin.stackSize;
                    int coinType = coin.getItemDamage();
                    if (coinType > 0) {
                        int coinsFloor = coins;
                        while (coinsFloor * 10 > this.exchangeInv.getInventoryStackLimit()) {
                            --coinsFloor;
                        }
                        this.exchangeInv.setInventorySlotContents(0, new ItemStack(LOTRMod.silverCoin, coinsFloor * 10, coinType - 1));
                    } else {
                        this.exchangeInv.setInventorySlotContents(0, null);
                    }
                    if (coinType < LOTRItemCoin.values.length - 1 && coins >= 10) {
                        this.exchangeInv.setInventorySlotContents(1, new ItemStack(LOTRMod.silverCoin, coins / 10, coinType + 1));
                    } else {
                        this.exchangeInv.setInventorySlotContents(1, null);
                    }
                } else {
                    this.exchangeInv.setInventorySlotContents(0, null);
                    this.exchangeInv.setInventorySlotContents(1, null);
                }
            }
        } else if (inv == this.exchangeInv && this.exchanged) {
            boolean anyItems = false;
            for (int i = 0; i < this.exchangeInv.getSizeInventory(); ++i) {
                if (this.exchangeInv.getStackInSlot(i) == null) continue;
                anyItems = true;
            }
            if (!anyItems) {
                this.exchanged = false;
                this.onCraftMatrixChanged(this.coinInputInv);
            }
        }
        super.onCraftMatrixChanged(inv);
    }

    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        if (!entityplayer.worldObj.isRemote) {
            int i;
            ItemStack itemstack;
            for (i = 0; i < this.coinInputInv.getSizeInventory(); ++i) {
                itemstack = this.coinInputInv.getStackInSlotOnClosing(i);
                if (itemstack == null) continue;
                entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
            }
            if (this.exchanged) {
                for (i = 0; i < this.exchangeInv.getSizeInventory(); ++i) {
                    itemstack = this.exchangeInv.getStackInSlotOnClosing(i);
                    if (itemstack == null) continue;
                    entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < 3) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                this.onCraftMatrixChanged(slot.inventory);
            } else {
                boolean flag = false;
                Slot coinSlot = (Slot)this.inventorySlots.get(0);
                coinSlot.getStack();
                if (coinSlot.isItemValid(itemstack1) && this.mergeItemStack(itemstack1, 0, 1, true)) {
                    flag = true;
                }
                if (!flag && (i >= 3 && i < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : !this.mergeItemStack(itemstack1, 3, 30, false))) {
                    return null;
                }
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
                this.detectAndSendChanges();
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

    protected void retrySlotClick(int i, int j, boolean flag, EntityPlayer entityplayer) {
    }

    public class InventoryCoinExchangeSlot
    extends InventoryBasic {
        public InventoryCoinExchangeSlot(int i) {
            super("coinExchange", true, i);
        }

        public void markDirty() {
            super.markDirty();
            LOTRContainerCoinExchange.this.onCraftMatrixChanged((IInventory)this);
        }
    }

}

