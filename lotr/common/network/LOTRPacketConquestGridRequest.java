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
import lotr.common.fac.LOTRFaction;
import lotr.common.world.map.LOTRConquestGrid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketConquestGridRequest
implements IMessage {
    private LOTRFaction conqFac;

    public LOTRPacketConquestGridRequest() {
    }

    public LOTRPacketConquestGridRequest(LOTRFaction fac) {
        this.conqFac = fac;
    }

    public void toBytes(ByteBuf data) {
        int facID = this.conqFac.ordinal();
        data.writeByte(facID);
    }

    public void fromBytes(ByteBuf data) {
        byte facID = data.readByte();
        this.conqFac = LOTRFaction.forID(facID);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketConquestGridRequest, IMessage> {
        public IMessage onMessage(LOTRPacketConquestGridRequest packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFaction fac = packet.conqFac;
            if (fac != null) {
                LOTRConquestGrid.ConquestViewableQuery query = LOTRConquestGrid.canPlayerViewConquest((EntityPlayer)entityplayer, fac);
                if (query.result == LOTRConquestGrid.ConquestViewable.CAN_VIEW) {
                    LOTRConquestGrid.sendConquestGridTo(entityplayer, fac);
                }
            }
            return null;
        }
    }

}

