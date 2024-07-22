/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.network.LOTRPacketFTBounceServer;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketFTBounceClient
implements IMessage {
    public void toBytes(ByteBuf data) {
    }

    public void fromBytes(ByteBuf data) {
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFTBounceClient, IMessage> {
        public IMessage onMessage(LOTRPacketFTBounceClient packet, MessageContext context) {
            LOTRMod.proxy.getClientPlayer();
            LOTRPacketFTBounceServer packetResponse = new LOTRPacketFTBounceServer();
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packetResponse);
            return null;
        }
    }

}

