/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketHiredUnitInteract
implements IMessage {
    private int entityID;
    private int entityAction;

    public LOTRPacketHiredUnitInteract() {
    }

    public LOTRPacketHiredUnitInteract(int id, int a) {
        this.entityID = id;
        this.entityAction = a;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeByte(this.entityAction);
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.entityAction = data.readByte();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketHiredUnitInteract, IMessage> {
        public IMessage onMessage(LOTRPacketHiredUnitInteract packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity npc = world.getEntityByID(packet.entityID);
            if (npc != null && npc instanceof LOTREntityNPC) {
                LOTREntityNPC hiredNPC = (LOTREntityNPC)npc;
                if (hiredNPC.hiredNPCInfo.isActive && hiredNPC.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                    int action = packet.entityAction;
                    boolean closeScreen = false;
                    if (action == 0) {
                        hiredNPC.npcTalkTick = hiredNPC.getNPCTalkInterval();
                        closeScreen = hiredNPC.speakTo((EntityPlayer)entityplayer);
                    } else if (action == 1) {
                        hiredNPC.hiredNPCInfo.sendClientPacket(true);
                    } else if (action == 2) {
                        // empty if block
                    }
                    if (closeScreen) {
                        entityplayer.closeScreen();
                    }
                }
            }
            return null;
        }
    }

}

