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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.quest.LOTRMiniQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketMiniquest
implements IMessage {
    private NBTTagCompound miniquestData;
    private boolean completed;

    public LOTRPacketMiniquest() {
    }

    public LOTRPacketMiniquest(NBTTagCompound nbt, boolean flag) {
        this.miniquestData = nbt;
        this.completed = flag;
    }

    public void toBytes(ByteBuf data) {
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.miniquestData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"LOTR: Error writing miniquest data", (Object[])new Object[0]);
            e.printStackTrace();
        }
        data.writeBoolean(this.completed);
    }

    public void fromBytes(ByteBuf data) {
        try {
            this.miniquestData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"LOTR: Error reading miniquest data", (Object[])new Object[0]);
            e.printStackTrace();
        }
        this.completed = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMiniquest, IMessage> {
        public IMessage onMessage(LOTRPacketMiniquest packet, MessageContext context) {
            if (!LOTRMod.proxy.isSingleplayer()) {
                EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
                LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
                LOTRMiniQuest miniquest = LOTRMiniQuest.loadQuestFromNBT(packet.miniquestData, pd);
                if (miniquest != null) {
                    LOTRMiniQuest existingQuest = pd.getMiniQuestForID(miniquest.questUUID, packet.completed);
                    if (existingQuest == null) {
                        if (packet.completed) {
                            pd.addMiniQuestCompleted(miniquest);
                        } else {
                            pd.addMiniQuest(miniquest);
                        }
                    } else {
                        existingQuest.readFromNBT(packet.miniquestData);
                    }
                }
            }
            return null;
        }
    }

}

