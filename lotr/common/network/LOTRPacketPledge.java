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
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketPledge
implements IMessage {
    private LOTRFaction pledgeFac;

    public LOTRPacketPledge() {
    }

    public LOTRPacketPledge(LOTRFaction f) {
        this.pledgeFac = f;
    }

    public void toBytes(ByteBuf data) {
        int facID = this.pledgeFac == null ? -1 : this.pledgeFac.ordinal();
        data.writeByte(facID);
    }

    public void fromBytes(ByteBuf data) {
        byte facID = data.readByte();
        this.pledgeFac = facID == -1 ? null : LOTRFaction.forID(facID);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketPledge, IMessage> {
        public IMessage onMessage(LOTRPacketPledge packet, MessageContext context) {
            if (!LOTRMod.proxy.isSingleplayer()) {
                EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
                LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
                pd.setPledgeFaction(packet.pledgeFac);
            }
            return null;
        }
    }

}

