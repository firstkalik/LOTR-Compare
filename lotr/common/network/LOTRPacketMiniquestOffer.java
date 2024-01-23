/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketMiniquestOfferClose;
import lotr.common.quest.LOTRMiniQuest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class LOTRPacketMiniquestOffer
implements IMessage {
    private int entityID;
    private NBTTagCompound miniquestData;

    public LOTRPacketMiniquestOffer() {
    }

    public LOTRPacketMiniquestOffer(int id, NBTTagCompound nbt) {
        this.entityID = id;
        this.miniquestData = nbt;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.miniquestData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing miniquest data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        try {
            this.miniquestData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading miniquest data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static void sendClosePacket(EntityPlayer entityplayer, LOTREntityNPC npc, boolean accept) {
        if (entityplayer == null) {
            FMLLog.warning((String)"LOTR Warning: Tried to send miniquest offer close packet, but player == null", (Object[])new Object[0]);
            return;
        }
        LOTRPacketMiniquestOfferClose packet = new LOTRPacketMiniquestOfferClose(npc.getEntityId(), accept);
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMiniquestOffer, IMessage> {
        public IMessage onMessage(LOTRPacketMiniquestOffer packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity instanceof LOTREntityNPC) {
                LOTREntityNPC npc = (LOTREntityNPC)entity;
                LOTRMiniQuest quest = LOTRMiniQuest.loadQuestFromNBT(packet.miniquestData, pd);
                if (quest != null) {
                    LOTRMod.proxy.displayMiniquestOffer(quest, npc);
                } else {
                    LOTRPacketMiniquestOffer.sendClosePacket(entityplayer, npc, false);
                }
            }
            return null;
        }
    }

}

