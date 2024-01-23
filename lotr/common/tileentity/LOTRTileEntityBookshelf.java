/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBook
 *  net.minecraft.item.ItemEditableBook
 *  net.minecraft.item.ItemEnchantedBook
 *  net.minecraft.item.ItemMapBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemWritableBook
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockBookshelfStorage;
import lotr.common.inventory.LOTRContainerBookshelf;
import lotr.common.item.LOTRItemModifierTemplate;
import lotr.common.item.LOTRItemRedBook;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRTileEntityBookshelf
extends TileEntity
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[this.getSizeInventory()];
    public int numPlayersUsing;
    private int ticksSinceSync;

    public int getSizeInventory() {
        return 27;
    }

    public ItemStack getStackInSlot(int i) {
        return this.chestContents[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.chestContents[i] != null) {
            if (this.chestContents[i].stackSize <= j) {
                ItemStack itemstack = this.chestContents[i];
                this.chestContents[i] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[i].splitStack(j);
            if (this.chestContents[i].stackSize == 0) {
                this.chestContents[i] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.chestContents[i] != null) {
            ItemStack itemstack = this.chestContents[i];
            this.chestContents[i] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.chestContents[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return "container.lotr.bookshelf";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList itemTags = nbt.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < itemTags.tagCount(); ++i) {
            NBTTagCompound slotData = itemTags.getCompoundTagAt(i);
            int slot = slotData.getByte("Slot") & 0xFF;
            if (slot < 0 || slot >= this.chestContents.length) continue;
            this.chestContents[slot] = ItemStack.loadItemStackFromNBT((NBTTagCompound)slotData);
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList itemTags = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound slotData = new NBTTagCompound();
            slotData.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(slotData);
            itemTags.appendTag((NBTBase)slotData);
        }
        nbt.setTag("Items", (NBTBase)itemTags);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void updateEntity() {
        super.updateEntity();
        ++this.ticksSinceSync;
        if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
            this.numPlayersUsing = 0;
            float range = 16.0f;
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - range), (double)((float)this.yCoord - range), (double)((float)this.zCoord - range), (double)((float)(this.xCoord + 1) + range), (double)((float)(this.yCoord + 1) + range), (double)((float)(this.zCoord + 1) + range)));
            for (Object obj : players) {
                LOTRTileEntityBookshelf playerShelfInv;
                EntityPlayer entityplayer = (EntityPlayer)obj;
                if (!(entityplayer.openContainer instanceof LOTRContainerBookshelf) || (playerShelfInv = ((LOTRContainerBookshelf)entityplayer.openContainer).shelfInv) != this) continue;
                ++this.numPlayersUsing;
            }
        }
    }

    public void openInventory() {
        if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
        }
        ++this.numPlayersUsing;
    }

    public void closeInventory() {
        if (this.getBlockType() instanceof LOTRBlockBookshelfStorage) {
            --this.numPlayersUsing;
        }
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return LOTRTileEntityBookshelf.isBookItem(itemstack);
    }

    public void invalidate() {
        super.invalidate();
        this.updateContainingBlockInfo();
    }

    public static boolean isBookItem(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (item instanceof ItemBook || item instanceof ItemWritableBook || item instanceof ItemEditableBook) {
                return true;
            }
            if (item instanceof LOTRItemRedBook || item == LOTRMod.mithrilBook) {
                return true;
            }
            if (item instanceof ItemEnchantedBook) {
                return true;
            }
            if (item instanceof ItemMapBase) {
                return true;
            }
            if (item == Items.paper) {
                return true;
            }
            if (item instanceof LOTRItemModifierTemplate) {
                return true;
            }
        }
        return false;
    }
}

