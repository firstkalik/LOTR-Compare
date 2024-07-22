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
 *  org.apache.commons.lang3.StringUtils
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.inventory.LOTRContainerAnvil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetHandlerPlayServer;
import org.apache.commons.lang3.StringUtils;

public class LOTRPacketAnvilRename
implements IMessage {
    private String rename;

    public LOTRPacketAnvilRename() {
    }

    public LOTRPacketAnvilRename(String s) {
        this.rename = s;
    }

    public void toBytes(ByteBuf data) {
        byte[] nameBytes = this.rename.getBytes(Charsets.UTF_8);
        data.writeShort(nameBytes.length);
        data.writeBytes(nameBytes);
    }

    public void fromBytes(ByteBuf data) {
        short nameLength = data.readShort();
        ByteBuf nameBytes = data.readBytes((int)nameLength);
        this.rename = nameBytes.toString(Charsets.UTF_8);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketAnvilRename, IMessage> {
        public IMessage onMessage(LOTRPacketAnvilRename packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            Container container = entityplayer.openContainer;
            if (container instanceof LOTRContainerAnvil) {
                LOTRContainerAnvil anvil = (LOTRContainerAnvil)container;
                String rename = packet.rename;
                if (rename != null && !StringUtils.isBlank((CharSequence)rename)) {
                    if (rename.length() <= 30) {
                        anvil.updateItemName(rename);
                    }
                } else {
                    anvil.updateItemName("");
                }
            }
            return null;
        }
    }

}

