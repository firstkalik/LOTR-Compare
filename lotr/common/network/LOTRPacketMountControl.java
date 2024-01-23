/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRNetHandlerPlayServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketMountControl
implements IMessage {
    public double posX;
    public double posY;
    public double posZ;
    public float rotationYaw;
    public float rotationPitch;

    public LOTRPacketMountControl() {
    }

    public LOTRPacketMountControl(Entity entity) {
        this.posX = entity.posX;
        this.posY = entity.posY;
        this.posZ = entity.posZ;
        this.rotationYaw = entity.rotationYaw;
        this.rotationPitch = entity.rotationPitch;
    }

    public void toBytes(ByteBuf data) {
        data.writeDouble(this.posX);
        data.writeDouble(this.posY);
        data.writeDouble(this.posZ);
        data.writeFloat(this.rotationYaw);
        data.writeFloat(this.rotationPitch);
    }

    public void fromBytes(ByteBuf data) {
        this.posX = data.readDouble();
        this.posY = data.readDouble();
        this.posZ = data.readDouble();
        this.rotationYaw = data.readFloat();
        this.rotationPitch = data.readFloat();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMountControl, IMessage> {
        public IMessage onMessage(LOTRPacketMountControl packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            NetHandlerPlayServer handler = entityplayer.playerNetServerHandler;
            if (handler instanceof LOTRNetHandlerPlayServer) {
                ((LOTRNetHandlerPlayServer)handler).processMountControl(packet);
            }
            return null;
        }
    }

}

