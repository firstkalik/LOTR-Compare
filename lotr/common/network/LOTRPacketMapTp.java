/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.server.CommandTeleport
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.world.World
 */
package lotr.common.network;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.World;

public class LOTRPacketMapTp
implements IMessage {
    private int xCoord;
    private int zCoord;

    public LOTRPacketMapTp() {
    }

    public LOTRPacketMapTp(int x, int z) {
        this.xCoord = x;
        this.zCoord = z;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.xCoord);
        data.writeInt(this.zCoord);
    }

    public void fromBytes(ByteBuf data) {
        this.xCoord = data.readInt();
        this.zCoord = data.readInt();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMapTp, IMessage> {
        public IMessage onMessage(LOTRPacketMapTp packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            boolean isOp = MinecraftServer.getServer().getConfigurationManager().func_152596_g(entityplayer.getGameProfile());
            if (isOp) {
                int i = packet.xCoord;
                int k = packet.zCoord;
                int j = world.getTopSolidOrLiquidBlock(i, k);
                String[] args = new String[]{Integer.toString(i), Integer.toString(j), Integer.toString(k)};
                new CommandTeleport().processCommand((ICommandSender)entityplayer, args);
            }
            return null;
        }
    }

}

