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
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetHandlerPlayServer
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
import lotr.common.entity.LOTREntityNPCRespawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class LOTRPacketEditNPCRespawner
implements IMessage {
    private int spawnerID;
    private NBTTagCompound spawnerData;
    public boolean destroy;

    public LOTRPacketEditNPCRespawner() {
    }

    public LOTRPacketEditNPCRespawner(LOTREntityNPCRespawner spawner) {
        this.spawnerID = spawner.getEntityId();
        this.spawnerData = new NBTTagCompound();
        spawner.writeSpawnerDataToNBT(this.spawnerData);
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.spawnerID);
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(this.spawnerData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error writing spawner data", (Object[])new Object[0]);
            e.printStackTrace();
        }
        data.writeBoolean(this.destroy);
    }

    public void fromBytes(ByteBuf data) {
        this.spawnerID = data.readInt();
        try {
            this.spawnerData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Error reading spawner data", (Object[])new Object[0]);
            e.printStackTrace();
        }
        this.destroy = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketEditNPCRespawner, IMessage> {
        public IMessage onMessage(LOTRPacketEditNPCRespawner packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity sEntity = world.getEntityByID(packet.spawnerID);
            if (sEntity instanceof LOTREntityNPCRespawner) {
                LOTREntityNPCRespawner spawner = (LOTREntityNPCRespawner)sEntity;
                if (entityplayer.capabilities.isCreativeMode) {
                    spawner.readSpawnerDataFromNBT(packet.spawnerData);
                }
                if (packet.destroy) {
                    spawner.onBreak();
                }
            }
            return null;
        }
    }

}

