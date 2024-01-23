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
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTRPacketNPCIsEating
implements IMessage {
    private int entityID;
    private boolean isEating;

    public LOTRPacketNPCIsEating() {
    }

    public LOTRPacketNPCIsEating(int id, boolean flag) {
        this.entityID = id;
        this.isEating = flag;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeBoolean(this.isEating);
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.isEating = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketNPCIsEating, IMessage> {
        public IMessage onMessage(LOTRPacketNPCIsEating packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity instanceof LOTREntityNPC) {
                ((LOTREntityNPC)entity).clientIsEating = packet.isEating;
            }
            return null;
        }
    }

}

