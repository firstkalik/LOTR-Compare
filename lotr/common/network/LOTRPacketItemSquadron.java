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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.util.StringUtils
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRSquadrons;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.StringUtils;

public class LOTRPacketItemSquadron
implements IMessage {
    private String squadron;

    public LOTRPacketItemSquadron() {
    }

    public LOTRPacketItemSquadron(String s) {
        this.squadron = s;
    }

    public void toBytes(ByteBuf data) {
        if (StringUtils.isNullOrEmpty((String)this.squadron)) {
            data.writeInt(-1);
        } else {
            byte[] sqBytes = this.squadron.getBytes(Charsets.UTF_8);
            data.writeInt(sqBytes.length);
            data.writeBytes(sqBytes);
        }
    }

    public void fromBytes(ByteBuf data) {
        int length = data.readInt();
        if (length > -1) {
            this.squadron = data.readBytes(length).toString(Charsets.UTF_8);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketItemSquadron, IMessage> {
        public IMessage onMessage(LOTRPacketItemSquadron packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            ItemStack itemstack = entityplayer.getCurrentEquippedItem();
            if (itemstack != null && itemstack.getItem() instanceof LOTRSquadrons.SquadronItem) {
                String squadron = packet.squadron;
                if (!StringUtils.isNullOrEmpty((String)squadron)) {
                    squadron = LOTRSquadrons.checkAcceptableLength(squadron);
                    LOTRSquadrons.setSquadron(itemstack, squadron);
                } else {
                    LOTRSquadrons.setSquadron(itemstack, "");
                }
            }
            return null;
        }
    }

}

