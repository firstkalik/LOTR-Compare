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
import lotr.common.LOTRPlayerData;
import lotr.common.world.map.LOTRWaypoint;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketWaypointRegion
implements IMessage {
    private LOTRWaypoint.Region region;
    private boolean unlocking;

    public LOTRPacketWaypointRegion() {
    }

    public LOTRPacketWaypointRegion(LOTRWaypoint.Region r, boolean flag) {
        this.region = r;
        this.unlocking = flag;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.region.ordinal());
        data.writeBoolean(this.unlocking);
    }

    public void fromBytes(ByteBuf data) {
        this.region = LOTRWaypoint.regionForID(data.readByte());
        this.unlocking = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketWaypointRegion, IMessage> {
        public IMessage onMessage(LOTRPacketWaypointRegion packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            LOTRWaypoint.Region region = packet.region;
            if (region != null) {
                if (packet.unlocking) {
                    pd.unlockFTRegion(region);
                } else {
                    pd.lockFTRegion(region);
                }
            }
            return null;
        }
    }

}

