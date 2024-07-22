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
import lotr.common.quest.LOTRMiniQuestEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketClientMQEvent
implements IMessage {
    private ClientMQEvent type;

    public LOTRPacketClientMQEvent() {
    }

    public LOTRPacketClientMQEvent(ClientMQEvent t) {
        this.type = t;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.type.ordinal());
    }

    public void fromBytes(ByteBuf data) {
        byte typeID = data.readByte();
        if (typeID >= 0 && typeID < ClientMQEvent.values().length) {
            this.type = ClientMQEvent.values()[typeID];
        }
    }

    public static enum ClientMQEvent {
        MAP,
        FACTIONS;

    }

    public static class Handler
    implements IMessageHandler<LOTRPacketClientMQEvent, IMessage> {
        public IMessage onMessage(LOTRPacketClientMQEvent packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            if (packet.type == ClientMQEvent.MAP) {
                pd.distributeMQEvent(new LOTRMiniQuestEvent.ViewMap());
            } else if (packet.type == ClientMQEvent.FACTIONS) {
                pd.distributeMQEvent(new LOTRMiniQuestEvent.ViewFactions());
            }
            return null;
        }
    }

}

