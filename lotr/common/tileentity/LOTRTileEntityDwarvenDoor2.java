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
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  org.apache.commons.lang3.tuple.Pair
 */
package lotr.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Map;
import lotr.common.block.LOTRBlockGateDwarvenIthildin;
import lotr.common.tileentity.LOTRDwarvenGlowLogic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

public class LOTRTileEntityDwarvenDoor2
extends TileEntity {
    private static Map<ChunkCoordinates, Pair<Long, Integer>> replacementGlowTicks = new HashMap<ChunkCoordinates, Pair<Long, Integer>>();
    private static int GLOW_RANGE = 12;
    private LOTRDwarvenGlowLogic glowLogic = new LOTRDwarvenGlowLogic().setPlayerRange(GLOW_RANGE);
    private LOTRBlockGateDwarvenIthildin.DoorSize doorSize;
    private int doorPosX;
    private int doorPosY;
    private int doorBaseX;
    private int doorBaseY;
    private int doorBaseZ;

    public void setDoorSizeAndPos(LOTRBlockGateDwarvenIthildin.DoorSize size, int i, int j) {
        if (size == null) {
            size = LOTRBlockGateDwarvenIthildin.DoorSize._1x1;
        }
        this.doorSize = size;
        this.doorPosX = i;
        this.doorPosY = j;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public LOTRBlockGateDwarvenIthildin.DoorSize getDoorSize() {
        if (this.doorSize == null) {
            this.doorSize = LOTRBlockGateDwarvenIthildin.DoorSize._1x1;
        }
        return this.doorSize;
    }

    public int getDoorPosX() {
        if (this.doorPosX < 0 || this.doorSize != null && this.doorPosX >= this.doorSize.width) {
            this.doorPosX = 0;
        }
        return this.doorPosX;
    }

    public int getDoorPosY() {
        if (this.doorPosY < 0 || this.doorSize != null && this.doorPosY >= this.doorSize.height) {
            this.doorPosY = 0;
        }
        return this.doorPosY;
    }

    public void setDoorBasePos(int i, int j, int k) {
        this.doorBaseX = i;
        this.doorBaseY = j;
        this.doorBaseZ = k;
        this.glowLogic.resetGlowTick();
        this.markDirty();
    }

    public boolean isSameDoor(LOTRTileEntityDwarvenDoor2 other) {
        return this.doorBaseX == other.doorBaseX && this.doorBaseY == other.doorBaseY && this.doorBaseZ == other.doorBaseZ;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeDoorToNBT(nbt);
    }

    private void writeDoorToNBT(NBTTagCompound nbt) {
        if (this.doorSize != null) {
            nbt.setString("DoorSize", this.doorSize.doorName);
            nbt.setByte("DoorX", (byte)this.doorPosX);
            nbt.setByte("DoorY", (byte)this.doorPosY);
            nbt.setInteger("DoorBaseX", this.doorBaseX);
            nbt.setInteger("DoorBaseY", this.doorBaseY);
            nbt.setInteger("DoorBaseZ", this.doorBaseZ);
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.readDoorFromNBT(nbt);
    }

    private void readDoorFromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("DoorSize")) {
            this.doorSize = LOTRBlockGateDwarvenIthildin.DoorSize.forName(nbt.getString("DoorSize"));
            this.doorPosX = nbt.getByte("DoorX");
            this.doorPosY = nbt.getByte("DoorY");
            this.doorBaseX = nbt.getInteger("DoorBaseX");
            this.doorBaseY = nbt.getInteger("DoorBaseY");
            this.doorBaseZ = nbt.getInteger("DoorBaseZ");
        }
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeDoorToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.readDoorFromNBT(data);
    }

    public void updateEntity() {
        super.updateEntity();
        if (this.doorSize != null) {
            this.glowLogic.update(this.worldObj, this.doorBaseX, this.doorBaseY, this.doorBaseZ);
        } else {
            this.glowLogic.update(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public void invalidate() {
        super.invalidate();
        if (this.worldObj.isRemote) {
            long time = this.worldObj.getTotalWorldTime();
            int glow = this.glowLogic.getGlowTick();
            ChunkCoordinates coords = new ChunkCoordinates(this.xCoord, this.yCoord, this.zCoord);
            replacementGlowTicks.put(coords, (Pair<Long, Integer>)Pair.of((Object)time, (Object)glow));
        }
    }

    public void validate() {
        super.validate();
        if (this.worldObj.isRemote) {
            ChunkCoordinates coords = new ChunkCoordinates(this.xCoord, this.yCoord, this.zCoord);
            long time = this.worldObj.getTotalWorldTime();
            if (replacementGlowTicks.containsKey((Object)coords)) {
                Pair<Long, Integer> stored = replacementGlowTicks.get((Object)coords);
                long storedTime = (Long)stored.getLeft();
                int glow = (Integer)stored.getRight();
                if (time == storedTime) {
                    this.glowLogic.setGlowTick(glow);
                }
                replacementGlowTicks.remove((Object)coords);
            }
        }
    }

    public float getGlowBrightness(float f) {
        TileEntity te;
        if (this.doorSize != null && this.worldObj.blockExists(this.doorBaseX, this.doorBaseY, this.doorBaseZ) && (te = this.worldObj.getTileEntity(this.doorPosX, this.doorBaseY, this.doorBaseZ)) instanceof LOTRTileEntityDwarvenDoor2) {
            LOTRTileEntityDwarvenDoor2 otherDoor = (LOTRTileEntityDwarvenDoor2)te;
            return otherDoor.glowLogic.getGlowBrightness(this.worldObj, this.doorPosX, this.doorBaseY, this.doorBaseZ, f);
        }
        return this.glowLogic.getGlowBrightness(this.worldObj, this.xCoord, this.yCoord, this.zCoord, f);
    }

    @SideOnly(value=Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        double range = GLOW_RANGE + 20;
        return range * range;
    }
}

