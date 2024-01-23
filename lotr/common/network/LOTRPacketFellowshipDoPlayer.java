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
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.network.LOTRPacketFellowshipDo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;

public class LOTRPacketFellowshipDoPlayer
extends LOTRPacketFellowshipDo {
    private String subjectUsername;
    private PlayerFunction function;

    public LOTRPacketFellowshipDoPlayer() {
    }

    public LOTRPacketFellowshipDoPlayer(LOTRFellowshipClient fs, String name, PlayerFunction f) {
        super(fs);
        this.subjectUsername = name;
        this.function = f;
    }

    @Override
    public void toBytes(ByteBuf data) {
        super.toBytes(data);
        byte[] nameBytes = this.subjectUsername.getBytes(Charsets.UTF_8);
        data.writeByte(nameBytes.length);
        data.writeBytes(nameBytes);
        data.writeByte(this.function.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf data) {
        super.fromBytes(data);
        byte nameLength = data.readByte();
        ByteBuf nameBytes = data.readBytes((int)nameLength);
        this.subjectUsername = nameBytes.toString(Charsets.UTF_8);
        this.function = PlayerFunction.values()[data.readByte()];
    }

    public UUID getSubjectPlayerUUID() {
        GameProfile profile = MinecraftServer.getServer().func_152358_ax().func_152655_a(this.subjectUsername);
        if (profile != null && profile.getId() != null) {
            return profile.getId();
        }
        return null;
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipDoPlayer, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipDoPlayer packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFellowship fellowship = packet.getFellowship();
            UUID subjectPlayer = packet.getSubjectPlayerUUID();
            if (fellowship != null && subjectPlayer != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
                if (packet.function == PlayerFunction.INVITE) {
                    playerData.invitePlayerToFellowship(fellowship, subjectPlayer);
                } else if (packet.function == PlayerFunction.REMOVE) {
                    playerData.removePlayerFromFellowship(fellowship, subjectPlayer);
                } else if (packet.function == PlayerFunction.TRANSFER) {
                    playerData.transferFellowship(fellowship, subjectPlayer);
                } else if (packet.function == PlayerFunction.OP) {
                    playerData.setFellowshipAdmin(fellowship, subjectPlayer, true);
                } else if (packet.function == PlayerFunction.DEOP) {
                    playerData.setFellowshipAdmin(fellowship, subjectPlayer, false);
                }
            }
            return null;
        }
    }

    public static enum PlayerFunction {
        INVITE,
        REMOVE,
        TRANSFER,
        OP,
        DEOP;

    }

}

