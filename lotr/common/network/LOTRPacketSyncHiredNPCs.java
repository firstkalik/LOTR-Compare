/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketSyncHiredNPCs
implements IMessage {
    private int hiredNPCCount;
    private int maxHiredNPCs;

    public LOTRPacketSyncHiredNPCs() {
    }

    public LOTRPacketSyncHiredNPCs(int hiredNPCCount, int maxHiredNPCs) {
        this.hiredNPCCount = hiredNPCCount;
        this.maxHiredNPCs = maxHiredNPCs;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.hiredNPCCount);
        buf.writeInt(this.maxHiredNPCs);
    }

    public void fromBytes(ByteBuf buf) {
        this.hiredNPCCount = buf.readInt();
        this.maxHiredNPCs = buf.readInt();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSyncHiredNPCs, IMessage> {
        public IMessage onMessage(LOTRPacketSyncHiredNPCs message, MessageContext ctx) {
            EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            if (player != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)player);
                playerData.updateGlobalHiredNPCCount(message.hiredNPCCount);
                playerData.setCustomMaxHiredNPCs(message.maxHiredNPCs);
            }
            return null;
        }
    }

}

