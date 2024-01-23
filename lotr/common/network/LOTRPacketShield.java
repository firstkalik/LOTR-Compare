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
import java.util.List;
import java.util.UUID;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRShields;

public class LOTRPacketShield
implements IMessage {
    private UUID player;
    private LOTRShields shield;

    public LOTRPacketShield() {
    }

    public LOTRPacketShield(UUID uuid) {
        this.player = uuid;
        LOTRPlayerData pd = LOTRLevelData.getData(this.player);
        this.shield = pd.getShield();
    }

    public void toBytes(ByteBuf data) {
        data.writeLong(this.player.getMostSignificantBits());
        data.writeLong(this.player.getLeastSignificantBits());
        boolean hasShield = this.shield != null;
        data.writeBoolean(hasShield);
        if (hasShield) {
            data.writeByte(this.shield.shieldID);
            data.writeByte(this.shield.shieldType.ordinal());
        }
    }

    public void fromBytes(ByteBuf data) {
        this.player = new UUID(data.readLong(), data.readLong());
        boolean hasShield = data.readBoolean();
        if (hasShield) {
            byte shieldID = data.readByte();
            byte shieldTypeID = data.readByte();
            if (shieldTypeID < 0 || shieldTypeID >= LOTRShields.ShieldType.values().length) {
                FMLLog.severe((String)("Failed to update LOTR shield on client side: There is no shieldtype with ID " + shieldTypeID), (Object[])new Object[0]);
            } else {
                LOTRShields.ShieldType shieldType = LOTRShields.ShieldType.values()[shieldTypeID];
                if (shieldID < 0 || shieldID >= shieldType.list.size()) {
                    FMLLog.severe((String)("Failed to update LOTR shield on client side: There is no shield with ID " + shieldID + " for shieldtype " + shieldTypeID), (Object[])new Object[0]);
                } else {
                    this.shield = shieldType.list.get(shieldID);
                }
            }
        } else {
            this.shield = null;
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketShield, IMessage> {
        public IMessage onMessage(LOTRPacketShield packet, MessageContext context) {
            LOTRPlayerData pd = LOTRLevelData.getData(packet.player);
            pd.setShield(packet.shield);
            return null;
        }
    }

}

