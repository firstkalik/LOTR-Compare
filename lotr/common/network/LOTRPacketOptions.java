/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketOptions
implements IMessage {
    private int option;
    private boolean enable;

    public LOTRPacketOptions() {
    }

    public LOTRPacketOptions(int i, boolean flag) {
        this.option = i;
        this.enable = flag;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.option);
        data.writeBoolean(this.enable);
    }

    public void fromBytes(ByteBuf data) {
        this.option = data.readByte();
        this.enable = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketOptions, IMessage> {
        public IMessage onMessage(LOTRPacketOptions packet, MessageContext context) {
            if (!LOTRMod.proxy.isSingleplayer()) {
                EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
                int option = packet.option;
                boolean enable = packet.enable;
                if (option == 0) {
                    LOTRLevelData.getData(entityplayer).setFriendlyFire(enable);
                } else if (option == 1) {
                    LOTRLevelData.getData(entityplayer).setEnableHiredDeathMessages(enable);
                } else if (option == 3) {
                    LOTRLevelData.getData(entityplayer).setHideMapLocation(enable);
                } else if (option == 4) {
                    LOTRLevelData.getData(entityplayer).setFemRankOverride(enable);
                } else if (option == 5) {
                    LOTRLevelData.getData(entityplayer).setEnableConquestKills(enable);
                }
            }
            return null;
        }
    }

}

