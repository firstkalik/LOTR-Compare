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

public class LOTRPacketEnvironmentOverlay
implements IMessage {
    private Overlay overlay;

    public LOTRPacketEnvironmentOverlay() {
    }

    public LOTRPacketEnvironmentOverlay(Overlay o) {
        this.overlay = o;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.overlay.ordinal());
    }

    public void fromBytes(ByteBuf data) {
        byte overlayID = data.readByte();
        this.overlay = Overlay.values()[overlayID];
    }

    public static enum Overlay {
        FROST,
        BURN;

    }

    public static class Handler
    implements IMessageHandler<LOTRPacketEnvironmentOverlay, IMessage> {
        public IMessage onMessage(LOTRPacketEnvironmentOverlay packet, MessageContext context) {
            if (packet.overlay == Overlay.FROST) {
                LOTRMod.proxy.showFrostOverlay();
            } else if (packet.overlay == Overlay.BURN) {
                LOTRMod.proxy.showBurnOverlay();
            }
            return null;
        }
    }

}

