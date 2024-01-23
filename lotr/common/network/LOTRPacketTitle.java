/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRTitle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class LOTRPacketTitle
implements IMessage {
    private LOTRTitle.PlayerTitle playerTitle;

    public LOTRPacketTitle() {
    }

    public LOTRPacketTitle(LOTRTitle.PlayerTitle t) {
        this.playerTitle = t;
    }

    public void toBytes(ByteBuf data) {
        if (this.playerTitle == null) {
            data.writeShort(-1);
            data.writeByte(-1);
        } else {
            data.writeShort(this.playerTitle.getTitle().titleID);
            data.writeByte((int)this.playerTitle.getColor().getFormattingCode());
        }
    }

    public void fromBytes(ByteBuf data) {
        short titleID = data.readShort();
        LOTRTitle title = LOTRTitle.forID(titleID);
        byte colorID = data.readByte();
        EnumChatFormatting color = LOTRTitle.PlayerTitle.colorForID(colorID);
        if (title != null && color != null) {
            this.playerTitle = new LOTRTitle.PlayerTitle(title, color);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketTitle, IMessage> {
        public IMessage onMessage(LOTRPacketTitle packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            LOTRTitle.PlayerTitle title = packet.playerTitle;
            if (title == null) {
                pd.setPlayerTitle(null);
            } else {
                pd.setPlayerTitle(title);
            }
            return null;
        }
    }

}

