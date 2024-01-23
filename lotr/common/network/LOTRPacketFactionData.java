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
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketFactionData
implements IMessage {
    private LOTRFaction faction;
    private NBTTagCompound factionNBT;

    public LOTRPacketFactionData() {
    }

    public LOTRPacketFactionData(LOTRFaction f, NBTTagCompound nbt) {
        this.faction = f;
        this.factionNBT = nbt;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.faction.ordinal());
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.factionNBT);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing faction data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public void fromBytes(ByteBuf data) {
        byte factionID = data.readByte();
        this.faction = LOTRFaction.forID(factionID);
        try {
            this.factionNBT = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading faction data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFactionData, IMessage> {
        public IMessage onMessage(LOTRPacketFactionData packet, MessageContext context) {
            if (!LOTRMod.proxy.isSingleplayer()) {
                EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
                LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
                LOTRFaction faction = packet.faction;
                if (faction != null) {
                    LOTRFactionData factionData = pd.getFactionData(faction);
                    factionData.load(packet.factionNBT);
                }
            }
            return null;
        }
    }

}

