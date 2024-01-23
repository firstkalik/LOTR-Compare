/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRTileEntityEntJar
extends TileEntity {
    public int drinkMeta = -1;
    public int drinkAmount;
    public static int MAX_CAPACITY = 6;

    public void updateEntity() {
        if (!this.worldObj.isRemote && (this.worldObj.canLightningStrikeAt(this.xCoord, this.yCoord, this.zCoord) || this.worldObj.canLightningStrikeAt(this.xCoord, this.yCoord + 1, this.zCoord)) && this.worldObj.rand.nextInt(2500) == 0) {
            this.fillWithWater();
        }
    }

    public boolean fillFromBowl(ItemStack itemstack) {
        if (this.drinkMeta == -1 && this.drinkAmount == 0) {
            this.drinkMeta = itemstack.getItemDamage();
            ++this.drinkAmount;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
            return true;
        }
        if (this.drinkMeta == itemstack.getItemDamage() && this.drinkAmount < MAX_CAPACITY) {
            ++this.drinkAmount;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
            return true;
        }
        return false;
    }

    public void fillWithWater() {
        if (this.drinkMeta == -1 && this.drinkAmount < MAX_CAPACITY) {
            ++this.drinkAmount;
        }
        this.drinkAmount = Math.min(this.drinkAmount, MAX_CAPACITY);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public void consume() {
        --this.drinkAmount;
        if (this.drinkAmount <= 0) {
            this.drinkMeta = -1;
        }
        this.drinkAmount = Math.max(this.drinkAmount, 0);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("DrinkMeta", this.drinkMeta);
        nbt.setInteger("DrinkAmount", this.drinkAmount);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.drinkMeta = nbt.getInteger("DrinkMeta");
        this.drinkAmount = nbt.getInteger("DrinkAmount");
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

