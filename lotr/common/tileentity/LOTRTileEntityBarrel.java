/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import java.util.ArrayList;
import java.util.List;
import lotr.common.item.LOTRItemMug;
import lotr.common.item.LOTRPoisonedDrinks;
import lotr.common.recipe.LOTRBrewingRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRTileEntityBarrel
extends TileEntity
implements IInventory {
    public static final int EMPTY = 0;
    public static final int BREWING = 1;
    public static final int FULL = 2;
    public static final int brewTime = 12000;
    public static final int brewAnimTime = 32;
    private ItemStack[] inventory = new ItemStack[10];
    public int barrelMode;
    public int brewingTime;
    public int brewingAnim;
    public int brewingAnimPrev;
    private String specialBarrelName;
    public List players = new ArrayList();
    public static final int BARREL_SLOT = 9;

    public ItemStack getBrewedDrink() {
        if (this.barrelMode == 2 && this.inventory[9] != null) {
            ItemStack itemstack = this.inventory[9].copy();
            return itemstack;
        }
        return null;
    }

    public void consumeMugRefill() {
        if (this.barrelMode == 2 && this.inventory[9] != null) {
            --this.inventory[9].stackSize;
            if (this.inventory[9].stackSize <= 0) {
                this.inventory[9] = null;
                this.barrelMode = 0;
            }
        }
    }

    private void updateBrewingRecipe() {
        if (this.barrelMode == 0) {
            this.inventory[9] = LOTRBrewingRecipes.findMatchingRecipe(this);
        }
    }

    public void handleBrewingButtonPress() {
        if (this.barrelMode == 0 && this.inventory[9] != null) {
            int i;
            this.barrelMode = 1;
            for (i = 0; i < 9; ++i) {
                if (this.inventory[i] == null) continue;
                ItemStack containerItem = null;
                if (this.inventory[i].getItem().hasContainerItem(this.inventory[i]) && (containerItem = this.inventory[i].getItem().getContainerItem(this.inventory[i])).isItemStackDamageable() && containerItem.getItemDamage() > containerItem.getMaxDamage()) {
                    containerItem = null;
                }
                --this.inventory[i].stackSize;
                if (this.inventory[i].stackSize > 0) continue;
                this.inventory[i] = null;
                if (containerItem == null) continue;
                this.inventory[i] = containerItem;
            }
            if (!this.worldObj.isRemote) {
                for (i = 0; i < this.players.size(); ++i) {
                    EntityPlayerMP entityplayer = (EntityPlayerMP)this.players.get(i);
                    entityplayer.openContainer.detectAndSendChanges();
                    entityplayer.sendContainerToPlayer(entityplayer.openContainer);
                }
            }
        } else if (this.barrelMode == 1 && this.inventory[9] != null && this.inventory[9].getItemDamage() > 0) {
            this.barrelMode = 2;
            this.brewingTime = 0;
            ItemStack itemstack = this.inventory[9].copy();
            itemstack.setItemDamage(itemstack.getItemDamage() - 1);
            this.inventory[9] = itemstack;
        }
    }

    public boolean canPoisonBarrel() {
        if (this.barrelMode != 0 && this.inventory[9] != null) {
            ItemStack itemstack = this.inventory[9];
            return LOTRPoisonedDrinks.canPoison(itemstack) && !LOTRPoisonedDrinks.isDrinkPoisoned(itemstack);
        }
        return false;
    }

    public void poisonBarrel(EntityPlayer entityplayer) {
        ItemStack itemstack = this.inventory[9];
        LOTRPoisonedDrinks.setDrinkPoisoned(itemstack, true);
        LOTRPoisonedDrinks.setPoisonerPlayer(itemstack, entityplayer);
    }

    public int getSizeInventory() {
        return this.inventory.length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.inventory[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.inventory[i] != null) {
            if (this.inventory[i].stackSize <= j) {
                ItemStack itemstack = this.inventory[i];
                this.inventory[i] = null;
                if (i != 9) {
                    this.updateBrewingRecipe();
                }
                return itemstack;
            }
            ItemStack itemstack = this.inventory[i].splitStack(j);
            if (this.inventory[i].stackSize == 0) {
                this.inventory[i] = null;
            }
            if (i != 9) {
                this.updateBrewingRecipe();
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        if (i != 9) {
            this.updateBrewingRecipe();
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.specialBarrelName : StatCollector.translateToLocal((String)"container.lotr.barrel");
    }

    public String getInvSubtitle() {
        ItemStack brewingItem = this.getStackInSlot(9);
        if (this.barrelMode == 0) {
            return StatCollector.translateToLocal((String)"container.lotr.barrel.empty");
        }
        if (this.barrelMode == 1 && brewingItem != null) {
            String strengthSubtitle = LOTRItemMug.getStrengthSubtitle(brewingItem);
            return StatCollector.translateToLocalFormatted((String)"container.lotr.barrel.brewing", (Object[])new Object[]{brewingItem.getDisplayName(), strengthSubtitle});
        }
        if (this.barrelMode == 2 && brewingItem != null) {
            String strengthSubtitle = LOTRItemMug.getStrengthSubtitle(brewingItem);
            return StatCollector.translateToLocalFormatted((String)"container.lotr.barrel.full", (Object[])new Object[]{brewingItem.getDisplayName(), strengthSubtitle, brewingItem.stackSize});
        }
        return "";
    }

    public boolean hasCustomInventoryName() {
        return this.specialBarrelName != null && this.specialBarrelName.length() > 0;
    }

    public void setBarrelName(String s) {
        this.specialBarrelName = s;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.readBarrelFromNBT(nbt);
    }

    public void readBarrelFromNBT(NBTTagCompound nbt) {
        NBTTagList items = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound itemData = items.getCompoundTagAt(i);
            byte slot = itemData.getByte("Slot");
            if (slot < 0 || slot >= this.inventory.length) continue;
            this.inventory[slot] = ItemStack.loadItemStackFromNBT((NBTTagCompound)itemData);
        }
        this.barrelMode = nbt.getByte("BarrelMode");
        this.brewingTime = nbt.getInteger("BrewingTime");
        if (nbt.hasKey("CustomName")) {
            this.specialBarrelName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeBarrelToNBT(nbt);
    }

    public void writeBarrelToNBT(NBTTagCompound nbt) {
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound itemData = new NBTTagCompound();
            itemData.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(itemData);
            items.appendTag((NBTBase)itemData);
        }
        nbt.setTag("Items", (NBTBase)items);
        nbt.setByte("BarrelMode", (byte)this.barrelMode);
        nbt.setInteger("BrewingTime", this.brewingTime);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.specialBarrelName);
        }
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public int getBrewProgressScaled(int i) {
        return this.brewingTime * i / 12000;
    }

    public int getBrewAnimationProgressScaled(int i) {
        return this.brewingAnim * i / 32;
    }

    public float getBrewAnimationProgressScaledF(int i, float f) {
        float f1 = (float)this.brewingAnimPrev * (float)i / 32.0f;
        float f2 = (float)this.brewingAnim * (float)i / 32.0f;
        return f1 + (f2 - f1) * f;
    }

    public int getBarrelFullAmountScaled(int i) {
        return this.inventory[9] == null ? 0 : this.inventory[9].stackSize * i / LOTRBrewingRecipes.BARREL_CAPACITY;
    }

    public void updateEntity() {
        boolean needUpdate = false;
        if (!this.worldObj.isRemote) {
            if (this.barrelMode == 1) {
                if (this.inventory[9] != null) {
                    ++this.brewingTime;
                    if (this.brewingTime >= 12000) {
                        this.brewingTime = 0;
                        if (this.inventory[9].getItemDamage() < 4) {
                            this.inventory[9].setItemDamage(this.inventory[9].getItemDamage() + 1);
                            needUpdate = true;
                        } else {
                            this.barrelMode = 2;
                        }
                    }
                } else {
                    this.barrelMode = 0;
                }
            } else {
                this.brewingTime = 0;
            }
            if (this.barrelMode == 2 && this.inventory[9] == null) {
                this.barrelMode = 0;
            }
        } else {
            this.brewingAnimPrev = this.brewingAnim++;
            if (this.barrelMode == 1) {
                if (this.brewingAnim >= 32) {
                    this.brewingAnim = 0;
                    this.brewingAnimPrev = 0;
                }
            } else {
                this.brewingAnim = 0;
                this.brewingAnimPrev = 0;
            }
        }
        if (needUpdate) {
            this.markDirty();
        }
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return false;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeBarrelToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.readBarrelFromNBT(data);
    }
}

