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
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketPledgeSet
implements IMessage {
    private LOTRFaction pledgeFac;

    public LOTRPacketPledgeSet() {
    }

    public LOTRPacketPledgeSet(LOTRFaction f) {
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
    implements IMessageHandler<LOTRPacketPledgeSet, IMessage> {
        public IMessage onMessage(LOTRPacketPledgeSet packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            LOTRFaction fac = packet.pledgeFac;
            if (fac == null) {
                pd.revokePledgeFaction((EntityPlayer)entityplayer, true);
            } else if (pd.canPledgeTo(fac) && pd.canMakeNewPledge()) {
                pd.setPledgeFaction(fac);
            }
            return null;
        }
    }

}

