/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;

public class LOTRPacketCancelItemHighlight
implements IMessage {
    public void toBytes(ByteBuf data) {
    }

    public void fromBytes(ByteBuf data) {
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketCancelItemHighlight, IMessage> {
        public IMessage onMessage(LOTRPacketCancelItemHighlight packet, MessageContext context) {
            LOTRMod.proxy.cancelItemHighlight();
            return null;
        }
    }

}

