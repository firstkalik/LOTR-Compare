/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemSoup
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.entity.LOTRPlateFallingInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRTileEntityPlate
extends TileEntity {
    private ItemStack foodItem;
    public LOTRPlateFallingInfo plateFallInfo;

    public static boolean isValidFoodItem(ItemStack itemstack) {
        Item item;
        if (itemstack != null && (item = itemstack.getItem()) instanceof ItemFood) {
            if (item instanceof ItemSoup) {
                return false;
            }
            return !item.hasContainerItem(itemstack);
        }
        return false;
    }

    public ItemStack getFoodItem() {
        return this.foodItem;
    }

    public void setFoodItem(ItemStack item) {
        if (item != null && item.stackSize <= 0) {
            item = null;
        }
        this.foodItem = item;
        if (this.worldObj != null) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        this.markDirty();
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("PlateEmpty", this.foodItem == null);
        if (this.foodItem != null) {
            nbt.setTag("FoodItem", (NBTBase)this.foodItem.writeToNBT(new NBTTagCompound()));
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("FoodID")) {
            Item item = Item.getItemById((int)nbt.getInteger("FoodID"));
            if (item != null) {
                int damage = nbt.getInteger("FoodDamage");
                this.foodItem = new ItemStack(item, 1, damage);
            }
        } else {
            boolean empty = nbt.getBoolean("PlateEmpty");
            this.foodItem = empty ? null : ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("FoodItem"));
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

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = super.getRenderBoundingBox();
        if (this.foodItem != null) {
            bb = bb.addCoord(0.0, (double)((float)this.foodItem.stackSize * 0.03125f), 0.0);
        }
        return bb;
    }
}

