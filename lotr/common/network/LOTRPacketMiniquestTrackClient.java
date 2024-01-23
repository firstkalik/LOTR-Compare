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
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketMiniquestTrackClient
implements IMessage {
    private UUID questID;

    public LOTRPacketMiniquestTrackClient() {
    }

    public LOTRPacketMiniquestTrackClient(UUID uuid) {
        this.questID = uuid;
    }

    public void toBytes(ByteBuf data) {
        boolean hasQuest = this.questID != null;
        data.writeBoolean(hasQuest);
        if (hasQuest) {
            data.writeLong(this.questID.getMostSignificantBits());
            data.writeLong(this.questID.getLeastSignificantBits());
        }
    }

    public void fromBytes(ByteBuf data) {
        boolean hasQuest = data.readBoolean();
        this.questID = hasQuest ? new UUID(data.readLong(), data.readLong()) : null;
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMiniquestTrackClient, IMessage> {
        public IMessage onMessage(LOTRPacketMiniquestTrackClient packet, MessageContext context) {
            if (!LOTRMod.proxy.isSingleplayer()) {
                EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
                LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
                pd.setTrackingMiniQuestID(packet.questID);
            }
            return null;
        }
    }

}

