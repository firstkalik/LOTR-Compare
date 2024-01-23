/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.network.LOTRPacketFellowshipDo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketFellowshipDisband
extends LOTRPacketFellowshipDo {
    public LOTRPacketFellowshipDisband() {
    }

    public LOTRPacketFellowshipDisband(LOTRFellowshipClient fs) {
        super(fs);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipDisband, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipDisband packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFellowship fellowship = packet.getFellowship();
            if (fellowship != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
                playerData.disbandFellowship(fellowship);
            }
            return null;
        }
    }

}

