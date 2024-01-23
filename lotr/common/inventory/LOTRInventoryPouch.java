/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import lotr.common.inventory.LOTRContainerPouch;
import lotr.common.item.LOTRItemPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class LOTRInventoryPouch
extends InventoryBasic {
    private LOTRContainerPouch theContainer;
    private EntityPlayer thePlayer;
    private boolean isTemporary;
    private ItemStack tempPouchItem;

    public LOTRInventoryPouch(EntityPlayer entityplayer, LOTRContainerPouch container) {
        super(entityplayer.inventory.getCurrentItem().getDisplayName(), true, LOTRItemPouch.getCapacity(entityplayer.inventory.getCurrentItem()));
        this.isTemporary = false;
        this.thePlayer = entityplayer;
        this.theContainer = container;
        if (!this.thePlayer.worldObj.isRemote) {
            this.loadPouchContents();
        }
    }

    public LOTRInventoryPouch(ItemStack itemstack) {
        super("tempPouch", true, LOTRItemPouch.getCapacity(itemstack));
        this.isTemporary = true;
        this.tempPouchItem = itemstack;
        this.loadPouchContents();
    }

    public ItemStack getPouchItem() {
        if (this.isTemporary) {
            return this.tempPouchItem;
        }
        return this.thePlayer.inventory.getCurrentItem();
    }

    public String getInventoryName() {
        return this.getPouchItem().getDisplayName();
    }

    public void markDirty() {
        super.markDirty();
        if (this.isTemporary || !this.thePlayer.worldObj.isRemote) {
            this.savePouchContents();
        }
    }

    private void loadPouchContents() {
        if (this.getPouchItem().hasTagCompound() && this.getPouchItem().getTagCompound().hasKey("LOTRPouchData")) {
            NBTTagCompound nbt = this.getPouchItem().getTagCompound().getCompoundTag("LOTRPouchData");
            NBTTagList items = nbt.getTagList("Items", 10);
            for (int i = 0; i < items.tagCount(); ++i) {
                NBTTagCompound itemData = items.getCompoundTagAt(i);
                byte slot = itemData.getByte("Slot");
                if (slot < 0 || slot >= this.getSizeInventory()) continue;
                this.setInventorySlotContents((int)slot, ItemStack.loadItemStackFromNBT((NBTTagCompound)itemData));
            }
        }
        if (!this.isTemporary) {
            this.theContainer.syncPouchItem(this.getPouchItem());
        }
    }

    private void savePouchContents() {
        if (!this.getPouchItem().hasTagCompound()) {
            this.getPouchItem().setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            ItemStack itemstack = this.getStackInSlot(i);
            if (itemstack == null) continue;
            NBTTagCompound itemData = new NBTTagCompound();
            itemData.setByte("Slot", (byte)i);
            itemstack.writeToNBT(itemData);
            items.appendTag((NBTBase)itemData);
        }
        nbt.setTag("Items", (NBTBase)items);
        this.getPouchItem().getTagCompound().setTag("LOTRPouchData", (NBTBase)nbt);
        if (!this.isTemporary) {
            this.theContainer.syncPouchItem(this.getPouchItem());
        }
    }
}

