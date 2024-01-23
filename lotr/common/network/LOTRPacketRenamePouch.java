/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.inventory.LOTRContainerPouch;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketRenamePouch
implements IMessage {
    private String name;

    public LOTRPacketRenamePouch() {
    }

    public LOTRPacketRenamePouch(String s) {
        this.name = s;
    }

    public void toBytes(ByteBuf data) {
        byte[] nameData = this.name.getBytes(Charsets.UTF_8);
        data.writeShort(nameData.length);
        data.writeBytes(nameData);
    }

    public void fromBytes(ByteBuf data) {
        short length = data.readShort();
        this.name = data.readBytes((int)length).toString(Charsets.UTF_8);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketRenamePouch, IMessage> {
        public IMessage onMessage(LOTRPacketRenamePouch packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            Container container = entityplayer.openContainer;
            if (container != null && container instanceof LOTRContainerPouch) {
                LOTRContainerPouch pouch = (LOTRContainerPouch)container;
                pouch.renamePouch(packet.name);
            }
            return null;
        }
    }

}

