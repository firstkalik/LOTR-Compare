/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.PlayerProfileCache
 *  net.minecraft.world.World
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import java.util.UUID;
import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.network.LOTRPacketFellowshipDo;
import lotr.common.util.LOTRLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.world.World;
import org.apache.logging.log4j.Logger;

public class LOTRPacketFellowshipInvitePlayer
extends LOTRPacketFellowshipDo {
    private String invitedUsername;

    public LOTRPacketFellowshipInvitePlayer() {
    }

    public LOTRPacketFellowshipInvitePlayer(LOTRFellowshipClient fs, String username) {
        super(fs);
        this.invitedUsername = username;
    }

    @Override
    public void toBytes(ByteBuf data) {
        super.toBytes(data);
        byte[] nameBytes = this.invitedUsername.getBytes(Charsets.UTF_8);
        data.writeByte(nameBytes.length);
        data.writeBytes(nameBytes);
    }

    @Override
    public void fromBytes(ByteBuf data) {
        super.fromBytes(data);
        byte nameLength = data.readByte();
        ByteBuf nameBytes = data.readBytes((int)nameLength);
        this.invitedUsername = nameBytes.toString(Charsets.UTF_8);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipInvitePlayer, IMessage> {
        private UUID findInvitedPlayerUUID(String invitedUsername) {
            GameProfile profile = MinecraftServer.getServer().func_152358_ax().func_152655_a(invitedUsername);
            if (profile != null && profile.getId() != null) {
                return profile.getId();
            }
            return null;
        }

        public IMessage onMessage(LOTRPacketFellowshipInvitePlayer packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFellowship fellowship = packet.getActiveFellowship();
            if (fellowship != null) {
                int limit = LOTRConfig.getFellowshipMaxSize(entityplayer.worldObj);
                if (limit >= 0 && fellowship.getPlayerCount() >= limit) {
                    LOTRLog.logger.warn(String.format("Player %s tried to invite a player with username %s to fellowship %s, but fellowship size %d is already >= the maximum of %d", entityplayer.getCommandSenderName(), packet.invitedUsername, fellowship.getName(), fellowship.getPlayerCount(), limit));
                } else {
                    UUID invitedPlayer = this.findInvitedPlayerUUID(packet.invitedUsername);
                    if (invitedPlayer != null) {
                        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
                        playerData.invitePlayerToFellowship(fellowship, invitedPlayer, entityplayer.getCommandSenderName());
                    } else {
                        LOTRLog.logger.warn(String.format("Player %s tried to invite a player with username %s to fellowship %s, but couldn't find the invited player's UUID", entityplayer.getCommandSenderName(), packet.invitedUsername, fellowship.getName()));
                    }
                }
            }
            return null;
        }
    }

}

