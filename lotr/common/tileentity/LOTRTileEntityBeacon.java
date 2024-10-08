/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  org.apache.commons.lang3.StringUtils
 */
package lotr.common.tileentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.commons.lang3.StringUtils;

public class LOTRTileEntityBeacon
extends TileEntity {
    private int ticksExisted;
    private boolean isLit;
    private int litCounter;
    private int unlitCounter;
    private long stateChangeTime = -1L;
    private String beaconName;
    private UUID beaconFellowshipID;
    private List<EntityPlayer> editingPlayers = new ArrayList<EntityPlayer>();

    public void setLit(boolean flag) {
        boolean wasLit = this.isLit;
        this.isLit = flag;
        if (!this.isLit) {
            this.litCounter = 0;
        } else {
            this.unlitCounter = 0;
        }
        this.updateLight();
        this.stateChangeTime = this.worldObj.getTotalWorldTime();
        if (wasLit && !this.isLit) {
            this.sendFellowshipMessage(false);
        }
    }

    private void updateLight() {
        this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public boolean isLit() {
        return this.isLit;
    }

    public boolean isFullyLit() {
        return this.isLit() && this.litCounter == 100;
    }

    public void updateEntity() {
        ++this.ticksExisted;
        if (!this.worldObj.isRemote) {
            if (this.isLit && this.litCounter < 100) {
                ++this.litCounter;
                if (this.litCounter == 100) {
                    this.updateLight();
                    this.sendFellowshipMessage(true);
                }
            }
            if (!this.isLit && this.unlitCounter < 100) {
                ++this.unlitCounter;
                if (this.unlitCounter == 100) {
                    this.updateLight();
                }
            }
            if (this.ticksExisted % 10 == 0) {
                boolean spreadUnlit;
                boolean spreadLit = this.isLit && this.litCounter >= 100;
                boolean bl = spreadUnlit = !this.isLit && this.unlitCounter >= 100;
                if (spreadLit || spreadUnlit) {
                    ArrayList<LOTRTileEntityBeacon> nearbyTiles = new ArrayList<LOTRTileEntityBeacon>();
                    int range = 88;
                    int chunkRange = range >> 4;
                    int chunkX = this.xCoord >> 4;
                    int chunkZ = this.zCoord >> 4;
                    ChunkCoordinates coordsThis = new ChunkCoordinates(this.xCoord, this.yCoord, this.zCoord);
                    for (int i1 = -chunkRange; i1 <= chunkRange; ++i1) {
                        for (int k1 = -chunkRange; k1 <= chunkRange; ++k1) {
                            Chunk chunk;
                            int i2 = chunkX + i1;
                            int k2 = chunkZ + k1;
                            if (!this.worldObj.getChunkProvider().chunkExists(i2, k2) || (chunk = this.worldObj.getChunkFromChunkCoords(i2, k2)) == null) continue;
                            for (Object obj : chunk.chunkTileEntityMap.values()) {
                                TileEntity te = (TileEntity)obj;
                                if (te.isInvalid() || !(te instanceof LOTRTileEntityBeacon)) continue;
                                LOTRTileEntityBeacon beacon = (LOTRTileEntityBeacon)te;
                                if (coordsThis.getDistanceSquared(beacon.xCoord, beacon.yCoord, beacon.zCoord) > 6400.0f) continue;
                                nearbyTiles.add(beacon);
                            }
                        }
                    }
                    if (spreadLit) {
                        for (LOTRTileEntityBeacon other : nearbyTiles) {
                            if (other.isLit || this.stateChangeTime <= other.stateChangeTime) continue;
                            other.setLit(true);
                        }
                    }
                    if (spreadUnlit) {
                        for (LOTRTileEntityBeacon other : nearbyTiles) {
                            if (!other.isLit || this.stateChangeTime <= other.stateChangeTime) continue;
                            other.setLit(false);
                        }
                    }
                }
            }
        }
        HashSet<EntityPlayer> removePlayers = new HashSet<EntityPlayer>();
        for (EntityPlayer entityplayer : this.editingPlayers) {
            if (!entityplayer.isDead) continue;
            removePlayers.add(entityplayer);
        }
        this.editingPlayers.removeAll(removePlayers);
    }

    public boolean isPlayerEditing(EntityPlayer entityplayer) {
        return this.editingPlayers.contains((Object)entityplayer);
    }

    public void addEditingPlayer(EntityPlayer entityplayer) {
        if (!this.editingPlayers.contains((Object)entityplayer)) {
            this.editingPlayers.add(entityplayer);
        }
    }

    public void releaseEditingPlayer(EntityPlayer entityplayer) {
        this.editingPlayers.remove((Object)entityplayer);
    }

    public UUID getFellowshipID() {
        return this.beaconFellowshipID;
    }

    public String getBeaconName() {
        return this.beaconName;
    }

    public void setFellowship(LOTRFellowship fs) {
        this.beaconFellowshipID = fs != null ? fs.getFellowshipID() : null;
        this.beaconFellowshipID = fs == null ? null : fs.getFellowshipID();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public void setBeaconName(String name) {
        this.beaconName = name;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    private void sendFellowshipMessage(boolean lit) {
        LOTRFellowship fs;
        if (this.beaconFellowshipID != null && (fs = LOTRFellowshipData.getFellowship(this.beaconFellowshipID)) != null && !fs.isDisbanded()) {
            String beaconMessageName = this.beaconName;
            if (StringUtils.isBlank((CharSequence)beaconMessageName)) {
                beaconMessageName = fs.getName();
            }
            ChatComponentTranslation message = new ChatComponentTranslation(lit ? "container.lotr.beacon.lit" : "container.lotr.beacon.unlit", new Object[]{beaconMessageName});
            message.getChatStyle().setColor(EnumChatFormatting.YELLOW);
            for (UUID player : fs.getAllPlayerUUIDs()) {
                EntityPlayer entityplayer = this.worldObj.func_152378_a(player);
                if (entityplayer == null) continue;
                entityplayer.addChatMessage((IChatComponent)message);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("IsLit", this.isLit);
        nbt.setByte("LitCounter", (byte)this.litCounter);
        nbt.setByte("UnlitCounter", (byte)this.unlitCounter);
        nbt.setLong("StateChangeTime", this.stateChangeTime);
        if (this.beaconName != null) {
            nbt.setString("BeaconName", this.beaconName);
        }
        if (this.beaconFellowshipID != null) {
            nbt.setString("BeaconFellowship", this.beaconFellowshipID.toString());
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.isLit = nbt.getBoolean("IsLit");
        this.litCounter = nbt.getByte("LitCounter");
        this.unlitCounter = nbt.getByte("UnlitCounter");
        this.stateChangeTime = nbt.getLong("StateChangeTime");
        this.beaconName = nbt.hasKey("BeaconName") ? nbt.getString("BeaconName") : null;
        this.beaconFellowshipID = nbt.hasKey("BeaconFellowship") ? UUID.fromString(nbt.getString("BeaconFellowship")) : null;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.readFromNBT(data);
        this.updateLight();
    }
}

