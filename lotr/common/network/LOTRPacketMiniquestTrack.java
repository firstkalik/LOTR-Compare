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
import java.util.UUID;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.quest.LOTRMiniQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketMiniquestTrack
implements IMessage {
    private UUID questID;

    public LOTRPacketMiniquestTrack() {
    }

    public LOTRPacketMiniquestTrack(LOTRMiniQuest quest) {
        this.questID = quest == null ? null : quest.questUUID;
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
    implements IMessageHandler<LOTRPacketMiniquestTrack, IMessage> {
        public IMessage onMessage(LOTRPacketMiniquestTrack packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            if (packet.questID == null) {
                pd.setTrackingMiniQuestID(null);
            } else {
                pd.setTrackingMiniQuestID(packet.questID);
            }
            return null;
        }
    }

}

