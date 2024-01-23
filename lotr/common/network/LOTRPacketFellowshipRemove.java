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
import lotr.common.fellowship.LOTRFellowship;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketFellowshipRemove
implements IMessage {
    private UUID fellowshipID;
    private boolean isInvite;

    public LOTRPacketFellowshipRemove() {
    }

    public LOTRPacketFellowshipRemove(LOTRFellowship fs, boolean invite) {
        this.fellowshipID = fs.getFellowshipID();
        this.isInvite = invite;
    }

    public void toBytes(ByteBuf data) {
        data.writeLong(this.fellowshipID.getMostSignificantBits());
        data.writeLong(this.fellowshipID.getLeastSignificantBits());
        data.writeBoolean(this.isInvite);
    }

    public void fromBytes(ByteBuf data) {
        this.fellowshipID = new UUID(data.readLong(), data.readLong());
        this.isInvite = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipRemove, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipRemove packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            if (packet.isInvite) {
                LOTRLevelData.getData(entityplayer).removeClientFellowshipInvite(packet.fellowshipID);
            } else {
                LOTRLevelData.getData(entityplayer).removeClientFellowship(packet.fellowshipID);
            }
            return null;
        }
    }

}

