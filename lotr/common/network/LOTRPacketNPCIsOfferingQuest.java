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
import lotr.common.entity.npc.LOTREntityQuestInfo;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTRPacketNPCIsOfferingQuest
implements IMessage {
    private int entityID;
    public boolean offering;
    public int offerColor;

    public LOTRPacketNPCIsOfferingQuest() {
    }

    public LOTRPacketNPCIsOfferingQuest(int id, boolean flag, int color) {
        this.entityID = id;
        this.offering = flag;
        this.offerColor = color;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeBoolean(this.offering);
        data.writeInt(this.offerColor);
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.offering = data.readBoolean();
        this.offerColor = data.readInt();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketNPCIsOfferingQuest, IMessage> {
        public IMessage onMessage(LOTRPacketNPCIsOfferingQuest packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity instanceof LOTREntityNPC) {
                ((LOTREntityNPC)entity).questInfo.receiveData(packet);
            }
            return null;
        }
    }

}

