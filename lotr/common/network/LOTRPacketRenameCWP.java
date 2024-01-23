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
import lotr.common.world.map.LOTRCustomWaypoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketRenameCWP
implements IMessage {
    private int wpID;
    private String name;

    public LOTRPacketRenameCWP() {
    }

    public LOTRPacketRenameCWP(LOTRCustomWaypoint wp, String s) {
        this.wpID = wp.getID();
        this.name = s;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.wpID);
        byte[] nameBytes = this.name.getBytes(Charsets.UTF_8);
        data.writeShort(nameBytes.length);
        data.writeBytes(nameBytes);
    }

    public void fromBytes(ByteBuf data) {
        this.wpID = data.readInt();
        short length = data.readShort();
        this.name = data.readBytes((int)length).toString(Charsets.UTF_8);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketRenameCWP, IMessage> {
        public IMessage onMessage(LOTRPacketRenameCWP packet, MessageContext context) {
            String wpName;
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            LOTRCustomWaypoint cwp = pd.getCustomWaypointByID(packet.wpID);
            if (cwp != null && (wpName = LOTRCustomWaypoint.validateCustomName(packet.name)) != null) {
                pd.renameCustomWaypoint(cwp, wpName);
            }
            return null;
        }
    }

}

