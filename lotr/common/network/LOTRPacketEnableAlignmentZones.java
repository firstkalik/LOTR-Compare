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
import lotr.common.LOTRLevelData;

public class LOTRPacketEnableAlignmentZones
implements IMessage {
    private boolean enable;

    public LOTRPacketEnableAlignmentZones() {
    }

    public LOTRPacketEnableAlignmentZones(boolean flag) {
        this.enable = flag;
    }

    public void toBytes(ByteBuf data) {
        data.writeBoolean(this.enable);
    }

    public void fromBytes(ByteBuf data) {
        this.enable = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketEnableAlignmentZones, IMessage> {
        public IMessage onMessage(LOTRPacketEnableAlignmentZones packet, MessageContext context) {
            LOTRLevelData.setEnableAlignmentZones(packet.enable);
            return null;
        }
    }

}

