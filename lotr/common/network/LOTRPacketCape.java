/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;

public class LOTRPacketCape
implements IMessage {
    public UUID player;
    public LOTRCapes cape;

    public LOTRPacketCape() {
    }

    public LOTRPacketCape(UUID uuid) {
        this.player = uuid;
        LOTRPlayerData pd = LOTRLevelData.getData(this.player);
        this.cape = pd.getCape();
    }

    public void toBytes(ByteBuf data) {
        data.writeLong(this.player.getMostSignificantBits());
        data.writeLong(this.player.getLeastSignificantBits());
        boolean hasCape = this.cape != null;
        data.writeBoolean(hasCape);
        if (hasCape) {
            data.writeByte(this.cape.capeID);
            data.writeByte(this.cape.capeType.ordinal());
        }
    }

    public void fromBytes(ByteBuf data) {
        this.player = new UUID(data.readLong(), data.readLong());
        boolean hasCape = data.readBoolean();
        if (hasCape) {
            byte capeID = data.readByte();
            byte capeTypeID = data.readByte();
            if (capeTypeID < 0 || capeTypeID >= LOTRCapes.CapeType.values().length) {
                FMLLog.severe((String)("Failed to update LOTR cape on client side: There is no capetype with ID " + capeTypeID), (Object[])new Object[0]);
            } else {
                LOTRCapes.CapeType capeType = LOTRCapes.CapeType.values()[capeTypeID];
                if (capeID < 0 || capeID >= capeType.list.size()) {
                    FMLLog.severe((String)("Failed to update LOTR cape on client side: There is no cape with ID " + capeID + " for capetype " + capeTypeID), (Object[])new Object[0]);
                } else {
                    this.cape = capeType.list.get(capeID);
                }
            }
        } else {
            this.cape = null;
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketCape, IMessage> {
        public IMessage onMessage(LOTRPacketCape packet, MessageContext context) {
            LOTRPlayerData pd = LOTRLevelData.getData(packet.player);
            pd.setCape(packet.cape);
            return null;
        }
    }

}

