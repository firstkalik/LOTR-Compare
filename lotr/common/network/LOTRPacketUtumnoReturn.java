/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class LOTRPacketUtumnoReturn
implements IMessage {
    private double posX;
    private double posZ;

    public LOTRPacketUtumnoReturn() {
    }

    public LOTRPacketUtumnoReturn(double x, double z) {
        this.posX = x;
        this.posZ = z;
    }

    public void toBytes(ByteBuf data) {
        data.writeDouble(this.posX);
        data.writeDouble(this.posZ);
    }

    public void fromBytes(ByteBuf data) {
        this.posX = data.readDouble();
        this.posZ = data.readDouble();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketUtumnoReturn, IMessage> {
        public IMessage onMessage(LOTRPacketUtumnoReturn packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            entityplayer.setPosition(packet.posX, entityplayer.posY, packet.posZ);
            LOTRMod.proxy.setUtumnoReturnPortalCoords(entityplayer, MathHelper.floor_double((double)packet.posX), MathHelper.floor_double((double)packet.posZ));
            return null;
        }
    }

}

