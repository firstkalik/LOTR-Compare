/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.world.map.LOTRCustomWaypoint;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketRenameCWPClient
implements IMessage {
    private int cwpID;
    private String name;
    private UUID sharingPlayer;

    public LOTRPacketRenameCWPClient() {
    }

    public LOTRPacketRenameCWPClient(int id, String s) {
        this.cwpID = id;
        this.name = s;
    }

    public LOTRPacketRenameCWPClient setSharingPlayer(UUID player) {
        this.sharingPlayer = player;
        return this;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.cwpID);
        byte[] nameBytes = this.name.getBytes(Charsets.UTF_8);
        data.writeShort(nameBytes.length);
        data.writeBytes(nameBytes);
        boolean shared = this.sharingPlayer != null;
        data.writeBoolean(shared);
        if (shared) {
            data.writeLong(this.sharingPlayer.getMostSignificantBits());
            data.writeLong(this.sharingPlayer.getLeastSignificantBits());
        }
    }

    public void fromBytes(ByteBuf data) {
        this.cwpID = data.readInt();
        short length = data.readShort();
        this.name = data.readBytes((int)length).toString(Charsets.UTF_8);
        boolean shared = data.readBoolean();
        if (shared) {
            this.sharingPlayer = new UUID(data.readLong(), data.readLong());
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketRenameCWPClient, IMessage> {
        public IMessage onMessage(LOTRPacketRenameCWPClient packet, MessageContext context) {
            LOTRCustomWaypoint cwp;
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            if (packet.sharingPlayer != null) {
                LOTRCustomWaypoint cwp2;
                if (!LOTRMod.proxy.isSingleplayer() && (cwp2 = pd.getSharedCustomWaypointByID(packet.sharingPlayer, packet.cwpID)) != null) {
                    pd.renameSharedCustomWaypoint(cwp2, packet.name);
                }
            } else if (!LOTRMod.proxy.isSingleplayer() && (cwp = pd.getCustomWaypointByID(packet.cwpID)) != null) {
                pd.renameCustomWaypoint(cwp, packet.name);
            }
            return null;
        }
    }

}

