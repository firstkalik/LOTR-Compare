/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
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
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.inventory.LOTRSlotAlignmentReward;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRContainerUnitTrade
extends Container {
    public LOTRHireableBase theUnitTrader;
    public LOTREntityNPC theLivingTrader;
    private LOTRFaction traderFaction;
    private IInventory alignmentRewardInv;
    public int alignmentRewardSlots;

    public LOTRContainerUnitTrade(EntityPlayer entityplayer, LOTRHireableBase trader, World world) {
        int i;
        this.theUnitTrader = trader;
        this.theLivingTrader = (LOTREntityNPC)((Object)this.theUnitTrader);
        this.traderFaction = this.theLivingTrader.getFaction();
        ItemStack reward = null;
        if (this.theUnitTrader instanceof LOTRUnitTradeable) {
            LOTRInvasions conquestType = ((LOTRUnitTradeable)this.theUnitTrader).getConquestHorn();
            reward = conquestType == null ? null : conquestType.createConquestHorn();
        }
        boolean hasReward = reward != null;
        this.alignmentRewardSlots = hasReward ? 1 : 0;
        this.alignmentRewardInv = new InventoryBasic("specialItem", false, this.alignmentRewardSlots);
        if (hasReward) {
            this.addSlotToContainer((Slot)new LOTRSlotAlignmentReward(this, this.alignmentRewardInv, 0, 174, 78, this.theUnitTrader, reward.copy()));
            if (!world.isRemote && LOTRLevelData.getData(entityplayer).getAlignment(this.traderFaction) >= 3000.0f) {
                this.alignmentRewardInv.setInventorySlotContents(0, reward.copy());
            }
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)entityplayer.inventory, j + i * 9 + 9, 30 + j * 18, 174 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)entityplayer.inventory, i, 30 + i * 18, 232));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theLivingTrader != null && (double)entityplayer.getDistanceToEntity((Entity)this.theLivingTrader) <= 12.0 && this.theLivingTrader.isEntityAlive() && this.theLivingTrader.getAttackTarget() == null && this.theUnitTrader.canTradeWith(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i >= 0 && i < this.alignmentRewardSlots ? !this.mergeItemStack(itemstack1, this.alignmentRewardSlots, 36 + this.alignmentRewardSlots, true) : (i >= this.alignmentRewardSlots && i < 27 + this.alignmentRewardSlots ? !this.mergeItemStack(itemstack1, 27 + this.alignmentRewardSlots, 36 + this.alignmentRewardSlots, false) : (i >= 27 + this.alignmentRewardSlots && i < 36 + this.alignmentRewardSlots ? !this.mergeItemStack(itemstack1, this.alignmentRewardSlots, 27 + this.alignmentRewardSlots, false) : !this.mergeItemStack(itemstack1, this.alignmentRewardSlots, 27 + this.alignmentRewardSlots, false)))) {
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

