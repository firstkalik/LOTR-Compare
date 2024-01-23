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

public class LOTRPacketFellowshipToggle
extends LOTRPacketFellowshipDo {
    private ToggleFunction function;

    public LOTRPacketFellowshipToggle() {
    }

    public LOTRPacketFellowshipToggle(LOTRFellowshipClient fs, ToggleFunction f) {
        super(fs);
        this.function = f;
    }

    @Override
    public void toBytes(ByteBuf data) {
        super.toBytes(data);
        data.writeByte(this.function.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf data) {
        super.fromBytes(data);
        this.function = ToggleFunction.values()[data.readByte()];
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipToggle, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipToggle packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRFellowship fellowship = packet.getFellowship();
            if (fellowship != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
                if (packet.function == ToggleFunction.PVP) {
                    boolean current = fellowship.getPreventPVP();
                    playerData.setFellowshipPreventPVP(fellowship, !current);
                } else if (packet.function == ToggleFunction.HIRED_FF) {
                    boolean current = fellowship.getPreventHiredFriendlyFire();
                    playerData.setFellowshipPreventHiredFF(fellowship, !current);
                } else if (packet.function == ToggleFunction.MAP_SHOW) {
                    boolean current = fellowship.getShowMapLocations();
                    playerData.setFellowshipShowMapLocations(fellowship, !current);
                }
            }
            return null;
        }
    }

    public static enum ToggleFunction {
        PVP,
        HIRED_FF,
        MAP_SHOW;

    }

}

