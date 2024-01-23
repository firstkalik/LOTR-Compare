/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTRRandomSkinEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTRPacketEntityUUID
implements IMessage {
    private int entityID;
    private UUID entityUUID;

    public LOTRPacketEntityUUID() {
    }

    public LOTRPacketEntityUUID(int id, UUID uuid) {
        this.entityID = id;
        this.entityUUID = uuid;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeLong(this.entityUUID.getMostSignificantBits());
        data.writeLong(this.entityUUID.getLeastSignificantBits());
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.entityUUID = new UUID(data.readLong(), data.readLong());
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketEntityUUID, IMessage> {
        public IMessage onMessage(LOTRPacketEntityUUID packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity instanceof LOTRRandomSkinEntity) {
                LOTRRandomSkinEntity npc = (LOTRRandomSkinEntity)entity;
                npc.setUniqueID(packet.entityUUID);
            }
            return null;
        }
    }

}

