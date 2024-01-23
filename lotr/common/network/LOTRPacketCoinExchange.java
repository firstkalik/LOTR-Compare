/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.inventory.LOTRContainerCoinExchange;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketCoinExchange
implements IMessage {
    private int button;

    public LOTRPacketCoinExchange() {
    }

    public LOTRPacketCoinExchange(int i) {
        this.button = i;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.button);
    }

    public void fromBytes(ByteBuf data) {
        this.button = data.readByte();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketCoinExchange, IMessage> {
        public IMessage onMessage(LOTRPacketCoinExchange packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            Container container = entityplayer.openContainer;
            if (container != null && container instanceof LOTRContainerCoinExchange) {
                LOTRContainerCoinExchange coinExchange = (LOTRContainerCoinExchange)container;
                coinExchange.handleExchangePacket(packet.button);
            }
            return null;
        }
    }

}

