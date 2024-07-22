/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.world.map.LOTRCustomWaypoint;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketShareCWPClient
implements IMessage {
    private int cwpID;
    private UUID fellowshipID;
    private boolean adding;

    public LOTRPacketShareCWPClient() {
    }

    public LOTRPacketShareCWPClient(int id, UUID fsID, boolean add) {
        this.cwpID = id;
        this.fellowshipID = fsID;
        this.adding = add;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.cwpID);
        data.writeLong(this.fellowshipID.getMostSignificantBits());
        data.writeLong(this.fellowshipID.getLeastSignificantBits());
        data.writeBoolean(this.adding);
    }

    public void fromBytes(ByteBuf data) {
        this.cwpID = data.readInt();
        this.fellowshipID = new UUID(data.readLong(), data.readLong());
        this.adding = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketShareCWPClient, IMessage> {
        public IMessage onMessage(LOTRPacketShareCWPClient packet, MessageContext context) {
            LOTRCustomWaypoint cwp;
            LOTRPlayerData pd;
            LOTRFellowshipClient fsClient;
            if (!LOTRMod.proxy.isSingleplayer() && (cwp = (pd = LOTRLevelData.getData(LOTRMod.proxy.getClientPlayer())).getCustomWaypointByID(packet.cwpID)) != null && (fsClient = pd.getClientFellowshipByID(packet.fellowshipID)) != null) {
                if (packet.adding) {
                    pd.customWaypointAddSharedFellowshipClient(cwp, fsClient);
                } else {
                    pd.customWaypointRemoveSharedFellowshipClient(cwp, fsClient);
                }
            }
            return null;
        }
    }

}

