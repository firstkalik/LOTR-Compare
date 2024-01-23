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
import lotr.common.entity.npc.LOTREntityQuestInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketMiniquestOfferClose
implements IMessage {
    private int npcID;
    private boolean accept;

    public LOTRPacketMiniquestOfferClose() {
    }

    public LOTRPacketMiniquestOfferClose(int id, boolean flag) {
        this.npcID = id;
        this.accept = flag;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.npcID);
        data.writeBoolean(this.accept);
    }

    public void fromBytes(ByteBuf data) {
        this.npcID = data.readInt();
        this.accept = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMiniquestOfferClose, IMessage> {
        public IMessage onMessage(LOTRPacketMiniquestOfferClose packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity npcEntity = world.getEntityByID(packet.npcID);
            if (npcEntity instanceof LOTREntityNPC) {
                LOTREntityNPC npc = (LOTREntityNPC)npcEntity;
                npc.questInfo.receiveOfferResponse((EntityPlayer)entityplayer, packet.accept);
            }
            return null;
        }
    }

}

