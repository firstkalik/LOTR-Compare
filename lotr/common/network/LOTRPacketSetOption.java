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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketSetOption
implements IMessage {
    private int option;

    public LOTRPacketSetOption() {
    }

    public LOTRPacketSetOption(int i) {
        this.option = i;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.option);
    }

    public void fromBytes(ByteBuf data) {
        this.option = data.readByte();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSetOption, IMessage> {
        public IMessage onMessage(LOTRPacketSetOption packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            if (packet.option == 0) {
                boolean flag = pd.getFriendlyFire();
                pd.setFriendlyFire(!flag);
            } else if (packet.option == 1) {
                boolean flag = pd.getEnableHiredDeathMessages();
                pd.setEnableHiredDeathMessages(!flag);
            } else if (packet.option == 2) {
                boolean flag = pd.getHideAlignment();
                pd.setHideAlignment(!flag);
            } else if (packet.option == 3) {
                boolean flag = pd.getHideMapLocation();
                pd.setHideMapLocation(!flag);
            } else if (packet.option == 4) {
                boolean flag = pd.getFemRankOverride();
                pd.setFemRankOverride(!flag);
            } else if (packet.option == 5) {
                boolean flag = pd.getEnableConquestKills();
                pd.setEnableConquestKills(!flag);
            }
            return null;
        }
    }

}

