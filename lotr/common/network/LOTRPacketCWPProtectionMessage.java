/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IChatComponent$Serializer
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.util.IChatComponent;

public class LOTRPacketCWPProtectionMessage
implements IMessage {
    private IChatComponent message;

    public LOTRPacketCWPProtectionMessage() {
    }

    public LOTRPacketCWPProtectionMessage(IChatComponent c) {
        this.message = c;
    }

    public void toBytes(ByteBuf data) {
        String serialised = IChatComponent.Serializer.func_150696_a((IChatComponent)this.message);
        byte[] srlBytes = serialised.getBytes(Charsets.UTF_8);
        data.writeInt(srlBytes.length);
        data.writeBytes(srlBytes);
    }

    public void fromBytes(ByteBuf data) {
        int length = data.readInt();
        ByteBuf srlBytes = data.readBytes(length);
        String serialised = srlBytes.toString(Charsets.UTF_8);
        this.message = IChatComponent.Serializer.func_150699_a((String)serialised);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketCWPProtectionMessage, IMessage> {
        public IMessage onMessage(LOTRPacketCWPProtectionMessage packet, MessageContext context) {
            LOTRMod.proxy.setMapCWPProtectionMessage(packet.message);
            return null;
        }
    }

}

