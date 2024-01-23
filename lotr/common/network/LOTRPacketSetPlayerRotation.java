/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketSetPlayerRotation
implements IMessage {
    private float rotYaw;

    public LOTRPacketSetPlayerRotation() {
    }

    public LOTRPacketSetPlayerRotation(float f) {
        this.rotYaw = f;
    }

    public void toBytes(ByteBuf data) {
        data.writeFloat(this.rotYaw);
    }

    public void fromBytes(ByteBuf data) {
        this.rotYaw = data.readFloat();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSetPlayerRotation, IMessage> {
        public IMessage onMessage(LOTRPacketSetPlayerRotation packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            if (entityplayer != null) {
                entityplayer.rotationYaw = packet.rotYaw;
            }
            return null;
        }
    }

}

