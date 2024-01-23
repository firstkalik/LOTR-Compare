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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketLoginPlayerData
implements IMessage {
    private NBTTagCompound playerData;

    public LOTRPacketLoginPlayerData() {
    }

    public LOTRPacketLoginPlayerData(NBTTagCompound nbt) {
        this.playerData = nbt;
    }

    public void toBytes(ByteBuf data) {
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.playerData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing LOTR login player data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public void fromBytes(ByteBuf data) {
        try {
            this.playerData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading LOTR login player data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketLoginPlayerData, IMessage> {
        public IMessage onMessage(LOTRPacketLoginPlayerData packet, MessageContext context) {
            NBTTagCompound nbt = packet.playerData;
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            if (!LOTRMod.proxy.isSingleplayer()) {
                pd.load(nbt);
            }
            LOTRMod.proxy.setWaypointModes(pd.showWaypoints(), pd.showCustomWaypoints(), pd.showHiddenSharedWaypoints());
            return null;
        }
    }

}

