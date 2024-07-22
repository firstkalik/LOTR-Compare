/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.map.LOTRConquestZone;
import net.minecraft.world.World;

public class LOTRPacketConquestGrid
implements IMessage {
    private LOTRFaction conqFac;
    private List<LOTRConquestZone> allZones;
    private long worldTime;

    public LOTRPacketConquestGrid() {
    }

    public LOTRPacketConquestGrid(LOTRFaction fac, Collection<LOTRConquestZone> grid, World world) {
        this.conqFac = fac;
        this.allZones = new ArrayList<LOTRConquestZone>(grid);
        this.worldTime = world.getTotalWorldTime();
    }

    public void toBytes(ByteBuf data) {
        int facID = this.conqFac.ordinal();
        data.writeByte(facID);
        for (LOTRConquestZone zone : this.allZones) {
            float str = zone.getConquestStrength(this.conqFac, this.worldTime);
            if (str <= 0.0f) continue;
            float strRaw = zone.getConquestStrengthRaw(this.conqFac);
            data.writeShort(zone.gridX);
            data.writeShort(zone.gridZ);
            data.writeLong(zone.getLastChangeTime());
            data.writeFloat(strRaw);
        }
        data.writeShort(-15000);
    }

    public void fromBytes(ByteBuf data) {
        byte facID = data.readByte();
        this.conqFac = LOTRFaction.forID(facID);
        this.allZones = new ArrayList<LOTRConquestZone>();
        short gridX = 0;
        short gridZ = 0;
        float str = 0.0f;
        while ((gridX = data.readShort()) != -15000) {
            gridZ = data.readShort();
            long time = data.readLong();
            str = data.readFloat();
            LOTRConquestZone zone = new LOTRConquestZone(gridX, gridZ);
            zone.setClientSide();
            zone.setLastChangeTime(time);
            zone.setConquestStrengthRaw(this.conqFac, str);
            this.allZones.add(zone);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketConquestGrid, IMessage> {
        public IMessage onMessage(LOTRPacketConquestGrid packet, MessageContext context) {
            LOTRMod.proxy.receiveConquestGrid(packet.conqFac, packet.allZones);
            return null;
        }
    }

}

