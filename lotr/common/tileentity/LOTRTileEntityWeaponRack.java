/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemHoe
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

import lotr.common.item.LOTRWeaponStats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRTileEntityWeaponRack
extends TileEntity {
    private ItemStack weaponItem;
    private EntityLivingBase rackEntity;

    public boolean canAcceptItem(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (LOTRWeaponStats.isMeleeWeapon(itemstack)) {
                return true;
            }
            if (LOTRWeaponStats.isRangedWeapon(itemstack)) {
                return true;
            }
            if (item instanceof ItemHoe) {
                return true;
            }
            if (item instanceof ItemFishingRod) {
                return true;
            }
        }
        return false;
    }

    public ItemStack getWeaponItem() {
        return this.weaponItem;
    }

    public void setWeaponItem(ItemStack item) {
        if (item != null && item.stackSize <= 0) {
            item = null;
        }
        this.weaponItem = item;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public EntityLivingBase getEntityForRender() {
        if (this.rackEntity == null) {
            this.rackEntity = new EntityLiving(this.worldObj){};
        }
        return this.rackEntity;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("HasWeapon", this.weaponItem != null);
        if (this.weaponItem != null) {
            nbt.setTag("WeaponItem", (NBTBase)this.weaponItem.writeToNBT(new NBTTagCompound()));
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        boolean hasWeapon = nbt.getBoolean("HasWeapon");
        this.weaponItem = hasWeapon ? ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("WeaponItem")) : null;
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

