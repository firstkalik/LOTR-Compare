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
 *  net.minecraft.inventory.Container
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
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTraderNPCInfo;
import lotr.common.inventory.LOTRContainerTrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketTraderInfo
implements IMessage {
    public NBTTagCompound traderData;

    public LOTRPacketTraderInfo() {
    }

    public LOTRPacketTraderInfo(NBTTagCompound nbt) {
        this.traderData = nbt;
    }

    public void toBytes(ByteBuf data) {
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.traderData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing trader data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public void fromBytes(ByteBuf data) {
        try {
            this.traderData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading trader data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketTraderInfo, IMessage> {
        public IMessage onMessage(LOTRPacketTraderInfo packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            Container container = entityplayer.openContainer;
            if (container instanceof LOTRContainerTrade) {
                LOTRContainerTrade containerTrade = (LOTRContainerTrade)container;
                containerTrade.theTraderNPC.traderNPCInfo.receiveClientPacket(packet);
            }
            return null;
        }
    }

}

