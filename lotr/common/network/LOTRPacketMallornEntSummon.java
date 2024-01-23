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
import lotr.common.entity.npc.LOTREntityMallornEnt;
import lotr.common.entity.npc.LOTREntityTree;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTRPacketMallornEntSummon
implements IMessage {
    private int mallornEntID;
    private int summonedID;

    public LOTRPacketMallornEntSummon() {
    }

    public LOTRPacketMallornEntSummon(int id1, int id2) {
        this.mallornEntID = id1;
        this.summonedID = id1;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.mallornEntID);
        data.writeInt(this.summonedID);
    }

    public void fromBytes(ByteBuf data) {
        this.mallornEntID = data.readInt();
        this.summonedID = data.readInt();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMallornEntSummon, IMessage> {
        public IMessage onMessage(LOTRPacketMallornEntSummon packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.mallornEntID);
            if (entity instanceof LOTREntityMallornEnt) {
                LOTREntityMallornEnt ent = (LOTREntityMallornEnt)entity;
                Entity summoned = world.getEntityByID(packet.summonedID);
                if (summoned instanceof LOTREntityTree) {
                    ent.spawnEntSummonParticles((LOTREntityTree)summoned);
                }
            }
            return null;
        }
    }

}

