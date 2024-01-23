/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
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

public class LOTRPacketDeleteMiniquest
implements IMessage {
    private UUID questUUID;
    private boolean completed;

    public LOTRPacketDeleteMiniquest() {
    }

    public LOTRPacketDeleteMiniquest(LOTRMiniQuest quest) {
        this.questUUID = quest.questUUID;
        this.completed = quest.isCompleted();
    }

    public void toBytes(ByteBuf data) {
        data.writeLong(this.questUUID.getMostSignificantBits());
        data.writeLong(this.questUUID.getLeastSignificantBits());
        data.writeBoolean(this.completed);
    }

    public void fromBytes(ByteBuf data) {
        this.questUUID = new UUID(data.readLong(), data.readLong());
        this.completed = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketDeleteMiniquest, IMessage> {
        public IMessage onMessage(LOTRPacketDeleteMiniquest packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
            LOTRMiniQuest removeQuest = pd.getMiniQuestForID(packet.questUUID, packet.completed);
            if (removeQuest != null) {
                pd.removeMiniQuest(removeQuest, packet.completed);
            } else {
                FMLLog.warning((String)"Tried to remove a LOTR miniquest that doesn't exist", (Object[])new Object[0]);
            }
            return null;
        }
    }

}

