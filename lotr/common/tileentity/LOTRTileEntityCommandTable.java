/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRTileEntityCommandTable
extends TileEntity {
    private int zoomExp;
    private static final int MIN_ZOOM = -2;
    private static final int MAX_ZOOM = 2;

    public int getZoomExp() {
        return this.zoomExp;
    }

    public void setZoomExp(int i) {
        this.zoomExp = MathHelper.clamp_int((int)i, (int)-2, (int)2);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public void toggleZoomExp() {
        int z = this.zoomExp;
        z = z <= -2 ? 2 : --z;
        this.setZoomExp(z);
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeTableToNBT(nbt);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.readTableFromNBT(nbt);
    }

    private void writeTableToNBT(NBTTagCompound nbt) {
        nbt.setByte("Zoom", (byte)this.zoomExp);
    }

    private void readTableFromNBT(NBTTagCompound nbt) {
        this.zoomExp = nbt.getByte("Zoom");
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeTableToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.readTableFromNBT(data);
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox((double)(this.xCoord - 1), (double)this.yCoord, (double)(this.zCoord - 1), (double)(this.xCoord + 2), (double)(this.yCoord + 2), (double)(this.zCoord + 2));
    }
}

