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
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.world.map.LOTRCustomWaypoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketShareCWP
implements IMessage {
    private int wpID;
    private String fsName;
    private boolean adding;

    public LOTRPacketShareCWP() {
    }

    public LOTRPacketShareCWP(LOTRCustomWaypoint wp, String s, boolean add) {
        this.wpID = wp.getID();
        this.fsName = s;
        this.adding = add;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.wpID);
        byte[] nameBytes = this.fsName.getBytes(Charsets.UTF_8);
        data.writeShort(nameBytes.length);
        data.writeBytes(nameBytes);
        data.writeBoolean(this.adding);
    }

    public void fromBytes(ByteBuf data) {
        this.wpID = data.readInt();
        short length = data.readShort();
        this.fsName = data.readBytes((int)length).toString(Charsets.UTF_8);
        this.adding = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketShareCWP, IMessage> {
        public IMessage onMessage(LOTRPacketShareCWP packet, MessageContext context) {
            LOTRFellowship fellowship;
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            LOTRCustomWaypoint cwp = pd.getCustomWaypointByID(packet.wpID);
            if (cwp != null && (fellowship = pd.getFellowshipByName(packet.fsName)) != null) {
                if (packet.adding) {
                    pd.customWaypointAddSharedFellowship(cwp, fellowship);
                } else {
                    pd.customWaypointRemoveSharedFellowship(cwp, fellowship);
                }
            }
            return null;
        }
    }

}

