/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.network.LOTRPacketFellowshipDo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketFellowshipRename
extends LOTRPacketFellowshipDo {
    private String newName;

    public LOTRPacketFellowshipRename() {
    }

    public LOTRPacketFellowshipRename(LOTRFellowshipClient fs, String name) {
        super(fs);
        this.newName = name;
    }

    @Override
    public void toBytes(ByteBuf data) {
        super.toBytes(data);
        byte[] nameBytes = this.newName.getBytes(Charsets.UTF_8);
        data.writeByte(nameBytes.length);
        data.writeBytes(nameBytes);
    }

    @Override
    public void fromBytes(ByteBuf data) {
        super.fromBytes(data);
        byte nameLength = data.readByte();
        ByteBuf nameBytes = data.readBytes((int)nameLength);
        this.newName = nameBytes.toString(Charsets.UTF_8);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipRename, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipRename packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFellowship fellowship = packet.getFellowship();
            if (fellowship != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
                playerData.renameFellowship(fellowship, packet.newName);
            }
            return null;
        }
    }

}

