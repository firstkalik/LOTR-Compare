/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.ListenableFuture
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import com.google.common.util.concurrent.ListenableFuture;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRLevelData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;

public class PacketAlcoholTolerance
implements IMessage {
    private int alcoholTolerance;

    public PacketAlcoholTolerance() {
    }

    public PacketAlcoholTolerance(int alcoholTolerance) {
        this.alcoholTolerance = alcoholTolerance;
    }

    public void fromBytes(ByteBuf buf) {
        this.alcoholTolerance = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.alcoholTolerance);
    }

    public static class Handler
    implements IMessageHandler<PacketAlcoholTolerance, IMessage> {
        public IMessage onMessage(final PacketAlcoholTolerance message, MessageContext ctx) {
            Minecraft.getMinecraft().func_152344_a(new Runnable(){

                @Override
                public void run() {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    LOTRLevelData.getData((EntityPlayer)player).setAlcoholTolerance(message.alcoholTolerance);
                }
            });
            return null;
        }

    }

}

