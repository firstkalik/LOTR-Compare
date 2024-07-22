/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.network.internal.EntitySpawnHandler
 *  cpw.mods.fml.common.network.internal.FMLMessage
 *  cpw.mods.fml.common.network.internal.FMLMessage$EntityMessage
 *  cpw.mods.fml.common.network.internal.FMLMessage$EntitySpawnMessage
 *  cpw.mods.fml.common.network.internal.FMLRuntimeCodec
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.network;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.network.internal.EntitySpawnHandler;
import cpw.mods.fml.common.network.internal.FMLMessage;
import cpw.mods.fml.common.network.internal.FMLRuntimeCodec;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lotr.common.util.LOTRLog;
import org.apache.logging.log4j.Logger;

public class LOTRPacketSpawnMob
implements IMessage {
    private ByteBuf spawnData;

    public LOTRPacketSpawnMob() {
    }

    public LOTRPacketSpawnMob(ByteBuf data) {
        this.spawnData = data.copy();
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.spawnData.readableBytes());
        data.writeBytes(this.spawnData);
    }

    public void fromBytes(ByteBuf data) {
        int len = data.readInt();
        this.spawnData = data.readBytes(len);
    }

    private static class AdhocEntitySpawnHandler
    extends EntitySpawnHandler {
        private AdhocEntitySpawnHandler() {
        }

        public void channelRead0(ChannelHandlerContext ctx, FMLMessage.EntityMessage msg) throws Exception {
            super.channelRead0(ctx, msg);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSpawnMob, IMessage> {
        public IMessage onMessage(LOTRPacketSpawnMob packet, MessageContext context) {
            FMLMessage.EntitySpawnMessage msg = new FMLMessage.EntitySpawnMessage();
            new FMLRuntimeCodec().decodeInto(null, packet.spawnData, (FMLMessage)msg);
            int modEntityID = 999999999;
            double x = 999.0;
            double y = 999.0;
            double z = 999.0;
            try {
                modEntityID = (Integer)ObfuscationReflectionHelper.getPrivateValue(FMLMessage.EntitySpawnMessage.class, (Object)msg, (String[])new String[]{"modEntityTypeId"});
                x = (Double)ObfuscationReflectionHelper.getPrivateValue(FMLMessage.EntitySpawnMessage.class, (Object)msg, (String[])new String[]{"scaledX"});
                y = (Double)ObfuscationReflectionHelper.getPrivateValue(FMLMessage.EntitySpawnMessage.class, (Object)msg, (String[])new String[]{"scaledY"});
                z = (Double)ObfuscationReflectionHelper.getPrivateValue(FMLMessage.EntitySpawnMessage.class, (Object)msg, (String[])new String[]{"scaledZ"});
            }
            catch (Exception exception) {
                // empty catch block
            }
            LOTRLog.logger.info("LOTR: Received mob spawn packet: " + modEntityID + "[" + x + ", " + y + ", " + z + "]");
            try {
                new AdhocEntitySpawnHandler().channelRead0((ChannelHandlerContext)null, (FMLMessage.EntityMessage)msg);
            }
            catch (Exception e) {
                LOTRLog.logger.error("LOTR: FATAL ERROR spawning entity!!!");
                e.printStackTrace();
            }
            return null;
        }
    }

}

