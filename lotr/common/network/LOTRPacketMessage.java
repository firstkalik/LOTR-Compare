/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRGuiMessageTypes;
import lotr.common.LOTRMod;

public class LOTRPacketMessage
implements IMessage {
    private LOTRGuiMessageTypes message;

    public LOTRPacketMessage() {
    }

    public LOTRPacketMessage(LOTRGuiMessageTypes m) {
        this.message = m;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.message.ordinal());
    }

    public void fromBytes(ByteBuf data) {
        byte messageID = data.readByte();
        if (messageID < 0 || messageID >= LOTRGuiMessageTypes.values().length) {
            FMLLog.severe((String)("Failed to display LOTR message: There is no message with ID " + messageID), (Object[])new Object[0]);
            this.message = null;
        } else {
            this.message = LOTRGuiMessageTypes.values()[messageID];
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMessage, IMessage> {
        public IMessage onMessage(LOTRPacketMessage packet, MessageContext context) {
            if (packet.message != null) {
                LOTRMod.proxy.displayMessage(packet.message);
            }
            return null;
        }
    }

}

