/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
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
import lotr.common.LOTRDate;
import lotr.common.LOTRMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketDate
implements IMessage {
    public NBTTagCompound dateData;
    private boolean doUpdate;

    public LOTRPacketDate() {
    }

    public LOTRPacketDate(NBTTagCompound nbt, boolean flag) {
        this.dateData = nbt;
        this.doUpdate = flag;
    }

    public void toBytes(ByteBuf data) {
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.dateData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing LOTR date", (Object[])new Object[0]);
            e.printStackTrace();
        }
        data.writeBoolean(this.doUpdate);
    }

    public void fromBytes(ByteBuf data) {
        try {
            this.dateData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading LOTR date", (Object[])new Object[0]);
            e.printStackTrace();
        }
        this.doUpdate = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketDate, IMessage> {
        public IMessage onMessage(LOTRPacketDate packet, MessageContext context) {
            LOTRDate.loadDates(packet.dateData);
            if (packet.doUpdate) {
                LOTRMod.proxy.displayNewDate();
            }
            return null;
        }
    }

}

