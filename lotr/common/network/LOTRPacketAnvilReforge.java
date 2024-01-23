/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.inventory.LOTRContainerAnvil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketAnvilReforge
implements IMessage {
    public void toBytes(ByteBuf data) {
    }

    public void fromBytes(ByteBuf data) {
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketAnvilReforge, IMessage> {
        public IMessage onMessage(LOTRPacketAnvilReforge packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            Container container = entityplayer.openContainer;
            if (container instanceof LOTRContainerAnvil) {
                LOTRContainerAnvil anvil = (LOTRContainerAnvil)container;
                anvil.reforgeItem();
            }
            return null;
        }
    }

}

