/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.world.World;

public class LOTRPacketParticles
implements IMessage {
    private String particleName;
    private int count;
    private double velocityX;
    private double velocityY;
    private double velocityZ;
    private double posX;
    private double posY;
    private double posZ;

    public LOTRPacketParticles() {
    }

    public LOTRPacketParticles(String particle, int count, double x, double y, double z, double velX, double velY, double velZ) {
        this.particleName = particle;
        this.count = count;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.velocityX = velX;
        this.velocityY = velY;
        this.velocityZ = velZ;
    }

    public void toBytes(ByteBuf data) {
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.particleName);
        data.writeInt(this.count);
        data.writeDouble(this.posX);
        data.writeDouble(this.posY);
        data.writeDouble(this.posZ);
        data.writeDouble(this.velocityX);
        data.writeDouble(this.velocityY);
        data.writeDouble(this.velocityZ);
    }

    public void fromBytes(ByteBuf data) {
        this.particleName = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.count = data.readInt();
        this.posX = data.readDouble();
        this.posY = data.readDouble();
        this.posZ = data.readDouble();
        this.velocityX = data.readDouble();
        this.velocityY = data.readDouble();
        this.velocityZ = data.readDouble();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketParticles, IMessage> {
        public IMessage onMessage(LOTRPacketParticles packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            for (int i = 0; i < packet.count; ++i) {
                world.spawnParticle(packet.particleName, packet.posX, packet.posY, packet.posZ, packet.velocityX, packet.velocityY, packet.velocityZ);
            }
            return null;
        }
    }

}

