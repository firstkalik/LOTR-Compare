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
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class LOTRPacketNPCRespawner
implements IMessage {
    private int spawnerID;
    private NBTTagCompound spawnerData;

    public LOTRPacketNPCRespawner() {
    }

    public LOTRPacketNPCRespawner(LOTREntityNPCRespawner spawner) {
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
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketNPCRespawner, IMessage> {
        public IMessage onMessage(LOTRPacketNPCRespawner packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            Entity entity = world.getEntityByID(packet.spawnerID);
            if (entity instanceof LOTREntityNPCRespawner) {
                LOTREntityNPCRespawner spawner = (LOTREntityNPCRespawner)entity;
                spawner.readSpawnerDataFromNBT(packet.spawnerData);
                entityplayer.openGui((Object)LOTRMod.instance, 45, world, entity.getEntityId(), 0, 0);
            }
            return null;
        }
    }

}

