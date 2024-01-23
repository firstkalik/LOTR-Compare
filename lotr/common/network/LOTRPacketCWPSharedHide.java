/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.world.map.LOTRCustomWaypoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketCWPSharedHide
implements IMessage {
    private int cwpID;
    private UUID sharingPlayer;
    private boolean hideCWP;

    public LOTRPacketCWPSharedHide() {
    }

    public LOTRPacketCWPSharedHide(LOTRCustomWaypoint cwp, boolean hide) {
        this.cwpID = cwp.getID();
        this.sharingPlayer = cwp.getSharingPlayerID();
        this.hideCWP = hide;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.cwpID);
        data.writeLong(this.sharingPlayer.getMostSignificantBits());
        data.writeLong(this.sharingPlayer.getLeastSignificantBits());
        data.writeBoolean(this.hideCWP);
    }

    public void fromBytes(ByteBuf data) {
        this.cwpID = data.readInt();
        this.sharingPlayer = new UUID(data.readLong(), data.readLong());
        this.hideCWP = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketCWPSharedHide, IMessage> {
        public IMessage onMessage(LOTRPacketCWPSharedHide packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            LOTRCustomWaypoint cwp = pd.getSharedCustomWaypointByID(packet.sharingPlayer, packet.cwpID);
            if (cwp != null) {
                pd.hideOrUnhideSharedCustomWaypoint(cwp, packet.hideCWP);
            }
            return null;
        }
    }

}

