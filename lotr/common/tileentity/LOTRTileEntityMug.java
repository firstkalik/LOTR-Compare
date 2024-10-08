/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemMug;
import lotr.common.item.LOTRPoisonedDrinks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRTileEntityMug
extends TileEntity {
    private ItemStack mugItem;
    private LOTRItemMug.Vessel mugVessel;

    public ItemStack getMugItem() {
        if (this.mugItem == null) {
            return this.getVessel().getEmptyVessel();
        }
        ItemStack copy = this.mugItem.copy();
        if (LOTRItemMug.isItemFullDrink(copy)) {
            LOTRItemMug.setVessel(copy, this.getVessel(), true);
        }
        return copy;
    }

    public void setMugItem(ItemStack itemstack) {
        if (itemstack != null && itemstack.stackSize <= 0) {
            itemstack = null;
        }
        this.mugItem = itemstack;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public ItemStack getMugItemForRender() {
        return LOTRItemMug.getEquivalentDrink(this.getMugItem());
    }

    public void setEmpty() {
        this.setMugItem(null);
    }

    public boolean isEmpty() {
        return !LOTRItemMug.isItemFullDrink(this.getMugItem());
    }

    public LOTRItemMug.Vessel getVessel() {
        if (this.mugVessel == null) {
            for (LOTRItemMug.Vessel v : LOTRItemMug.Vessel.values()) {
                if (!v.canPlace || v.getBlock() != this.getBlockType()) continue;
                return v;
            }
            return LOTRItemMug.Vessel.MUG;
        }
        return this.mugVessel;
    }

    public void setVessel(LOTRItemMug.Vessel v) {
        this.mugVessel = v;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public boolean canPoisonMug() {
        ItemStack itemstack = this.getMugItem();
        if (itemstack != null) {
            return LOTRPoisonedDrinks.canPoison(itemstack) && !LOTRPoisonedDrinks.isDrinkPoisoned(itemstack);
        }
        return false;
    }

    public void poisonMug(EntityPlayer entityplayer) {
        ItemStack itemstack = this.getMugItem();
        LOTRPoisonedDrinks.setDrinkPoisoned(itemstack, true);
        LOTRPoisonedDrinks.setPoisonerPlayer(itemstack, entityplayer);
        this.setMugItem(itemstack);
    }

    public void updateEntity() {
        if (!this.worldObj.isRemote && this.isEmpty() && this.worldObj.canLightningStrikeAt(this.xCoord, this.yCoord, this.zCoord) && this.worldObj.rand.nextInt(6000) == 0) {
            ItemStack waterItem = new ItemStack(LOTRMod.mugWater);
            LOTRItemMug.setVessel(waterItem, this.getVessel(), false);
            this.setMugItem(waterItem);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("HasMugItem", this.mugItem != null);
        if (this.mugItem != null) {
            nbt.setTag("MugItem", (NBTBase)this.mugItem.writeToNBT(new NBTTagCompound()));
        }
        if (this.mugVessel != null) {
            nbt.setByte("Vessel", (byte)this.mugVessel.id);
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("ItemID")) {
            Item item = Item.getItemById((int)nbt.getInteger("ItemID"));
            if (item != null) {
                int damage = nbt.getInteger("ItemDamage");
                this.mugItem = new ItemStack(item, 1, damage);
            }
        } else {
            boolean hasItem = nbt.getBoolean("HasMugItem");
            ItemStack itemStack = this.mugItem = !hasItem ? null : ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("MugItem"));
        }
        if (nbt.hasKey("Vessel")) {
            this.mugVessel = LOTRItemMug.Vessel.forMeta(nbt.getByte("Vessel"));
        }
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.readFromNBT(data);
    }
}

