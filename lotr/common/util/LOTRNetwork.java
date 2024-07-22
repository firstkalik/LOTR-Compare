/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 *  net.minecraft.network.PacketBuffer
 */
package lotr.common.util;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import lotr.common.LOTRMod;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.util.LOTRPacketClientsideGUI;
import lotr.common.util.LOTRPacketMoneyChange;
import lotr.common.util.LOTRPacketMoneyGet;
import lotr.common.util.LOTRPacketMoneyGive;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.network.PacketBuffer;

public class LOTRNetwork {
    public final boolean dedicatedServer;
    public final SimpleNetworkWrapper network_wrapped;
    private final AtomicInteger disc;

    public LOTRNetwork() {
        boolean dedicated = true;
        try {
            Class.forName("net.minecraft.client.Minecraft", true, (ClassLoader)Launch.classLoader);
            dedicated = false;
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.dedicatedServer = dedicated;
        this.network_wrapped = NetworkRegistry.INSTANCE.newSimpleChannel("lotrrp_");
        this.disc = new AtomicInteger(0);
        this.setup();
    }

    private final void setup() {
        this.register(LOTRRPMessage::process, LOTRPacketMoneyGive.class, Side.SERVER);
        this.register(LOTRRPMessage::process, LOTRPacketMoneyGet.class, Side.SERVER);
        this.register(LOTRRPMessage::process, LOTRPacketMoneyChange.class, Side.CLIENT);
        LOTRPacketHandler.networkWrapper.registerMessage(LOTRPacketClientsideGUI.Handler.class, LOTRPacketClientsideGUI.class, 150, Side.CLIENT);
    }

    public final <T extends LOTRRPMessage> void register(IMessageHandler<T, IMessage> processMethodReference, Class<T> packetClass, Side recievingSide) {
        this.network_wrapped.registerMessage(processMethodReference, packetClass, this.disc.getAndIncrement(), recievingSide);
    }

    public final void execute(Side side, Supplier<Runnable> task) {
        if (side == Side.CLIENT && this.dedicatedServer) {
            return;
        }
        try {
            task.get().run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static interface LOTRRPMessage
    extends IMessage {
        public IMessage process(MessageContext var1);

        @Deprecated
        default public void fromBytes(ByteBuf buf) {
            try {
                this.readBuffer(new PacketBuffer(buf));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Deprecated
        default public void toBytes(ByteBuf buf) {
            try {
                this.writeBuffer(new PacketBuffer(buf));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void readBuffer(PacketBuffer var1) throws IOException;

        public void writeBuffer(PacketBuffer var1) throws IOException;

        default public void sendToServer() {
            LOTRMod.network.network_wrapped.sendToServer((IMessage)this);
        }

        default public void sendToAll() {
            LOTRMod.network.network_wrapped.sendToAll((IMessage)this);
        }

        default public void sendTos(int dimensionId) {
            LOTRMod.network.network_wrapped.sendToDimension((IMessage)this, dimensionId);
        }

        default public void sendToAllAround(int dimension, double x, double y, double z, double range) {
            this.sendToAllAround(new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
        }

        default public void sendToAllAround(NetworkRegistry.TargetPoint point) {
            LOTRMod.network.network_wrapped.sendToAllAround((IMessage)this, point);
        }

        default public void sendTo(EntityPlayerMP player) {
            LOTRMod.network.network_wrapped.sendTo((IMessage)this, player);
        }
    }

}

