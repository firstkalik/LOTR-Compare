/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.world.World
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
import lotr.common.entity.npc.LOTREntityMallornEnt;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class LOTRPacketMallornEntHeal
implements IMessage {
    private int entityID;
    public NBTTagCompound healingData;

    public LOTRPacketMallornEntHeal() {
    }

    public LOTRPacketMallornEntHeal(int id, NBTTagCompound nbt) {
        this.entityID = id;
        this.healingData = nbt;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.healingData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing MEnt healing data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        try {
            this.healingData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading MEnt healing data", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMallornEntHeal, IMessage> {
        public IMessage onMessage(LOTRPacketMallornEntHeal packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity instanceof LOTREntityMallornEnt) {
                LOTREntityMallornEnt ent = (LOTREntityMallornEnt)entity;
                ent.receiveClientHealing(packet.healingData);
            }
            return null;
        }
    }

}

