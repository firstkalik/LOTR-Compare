/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.network.LOTRPacketFellowshipDo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketFellowshipRespondInvite
extends LOTRPacketFellowshipDo {
    private boolean acceptOrReject;

    public LOTRPacketFellowshipRespondInvite() {
    }

    public LOTRPacketFellowshipRespondInvite(LOTRFellowshipClient fs, boolean accept) {
        super(fs);
        this.acceptOrReject = accept;
    }

    @Override
    public void toBytes(ByteBuf data) {
        super.toBytes(data);
        data.writeBoolean(this.acceptOrReject);
    }

    @Override
    public void fromBytes(ByteBuf data) {
        super.fromBytes(data);
        this.acceptOrReject = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipRespondInvite, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipRespondInvite packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFellowship fellowship = packet.getFellowship();
            if (fellowship != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
                if (packet.acceptOrReject) {
                    playerData.acceptFellowshipInvite(fellowship);
                } else {
                    playerData.rejectFellowshipInvite(fellowship);
                }
            }
            return null;
        }
    }

}

