/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRSlotMillstone
extends Slot {
    private EntityPlayer thePlayer;
    private int itemsTaken;

    public LOTRSlotMillstone(EntityPlayer entityplayer, IInventory inv, int i, int j, int k) {
        super(inv, i, j, k);
        this.thePlayer = entityplayer;
    }

    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    public ItemStack decrStackSize(int i) {
        if (this.getHasStack()) {
            this.itemsTaken += Math.min(i, this.getStack().stackSize);
        }
        return super.decrStackSize(i);
    }

    public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
        this.onCrafting(itemstack);
        super.onPickupFromSlot(entityplayer, itemstack);
    }

    protected void onCrafting(ItemStack itemstack, int i) {
        this.itemsTaken += i;
        this.onCrafting(itemstack);
    }

    protected void onCrafting(ItemStack itemstack) {
        itemstack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.itemsTaken);
        this.itemsTaken = 0;
        if (!this.thePlayer.worldObj.isRemote && itemstack.getItem() == LOTRMod.obsidianShard) {
            LOTRLevelData.getData(this.thePlayer).addAchievement(LOTRAchievement.smeltObsidianShard);
        }
    }
}

