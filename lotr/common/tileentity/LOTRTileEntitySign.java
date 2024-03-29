/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package lotr.common.tileentity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketOpenSignEditor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class LOTRTileEntitySign
extends TileEntity {
    public String[] signText = new String[this.getNumLines()];
    public static final int MAX_LINE_LENGTH = 15;
    public int lineBeingEdited = -1;
    private boolean editable = true;
    private EntityPlayer editingPlayer;
    public boolean isFakeGuiSign = false;

    public LOTRTileEntitySign() {
        for (int l = 0; l < this.signText.length; ++l) {
            this.signText[l] = "";
        }
    }

    public abstract int getNumLines();

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeSignText(nbt);
    }

    private void writeSignText(NBTTagCompound nbt) {
        for (int i = 0; i < this.signText.length; ++i) {
            nbt.setString("Text" + (i + 1), this.signText[i]);
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.editable = false;
        super.readFromNBT(nbt);
        this.readSignText(nbt);
    }

    private void readSignText(NBTTagCompound nbt) {
        for (int i = 0; i < this.signText.length; ++i) {
            this.signText[i] = nbt.getString("Text" + (i + 1));
            if (this.signText[i].length() <= 15) continue;
            this.signText[i] = this.signText[i].substring(0, 15);
        }
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeSignText(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.readSignText(data);
    }

    public boolean isEditable() {
        return this.editable;
    }

    public void setEditable(boolean flag) {
        this.editable = flag;
        if (!flag) {
            this.editingPlayer = null;
        }
    }

    public void setEditingPlayer(EntityPlayer entityplayer) {
        this.editingPlayer = entityplayer;
    }

    public EntityPlayer getEditingPlayer() {
        return this.editingPlayer;
    }

    public void openEditGUI(EntityPlayerMP entityplayer) {
        this.setEditingPlayer((EntityPlayer)entityplayer);
        LOTRPacketOpenSignEditor packet = new LOTRPacketOpenSignEditor(this);
        LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, entityplayer);
    }
}

