/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.util.StringUtils
 *  net.minecraft.world.World
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRSquadrons;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class LOTRPacketNPCSquadron
implements IMessage {
    private int npcID;
    private String squadron;

    public LOTRPacketNPCSquadron() {
    }

    public LOTRPacketNPCSquadron(LOTREntityNPC npc, String s) {
        this.npcID = npc.getEntityId();
        this.squadron = s;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.npcID);
        if (StringUtils.isNullOrEmpty((String)this.squadron)) {
            data.writeInt(-1);
        } else {
            byte[] sqBytes = this.squadron.getBytes(Charsets.UTF_8);
            data.writeInt(sqBytes.length);
            data.writeBytes(sqBytes);
        }
    }

    public void fromBytes(ByteBuf data) {
        this.npcID = data.readInt();
        int length = data.readInt();
        if (length > -1) {
            this.squadron = data.readBytes(length).toString(Charsets.UTF_8);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketNPCSquadron, IMessage> {
        public IMessage onMessage(LOTRPacketNPCSquadron packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity npc = world.getEntityByID(packet.npcID);
            if (npc != null && npc instanceof LOTREntityNPC) {
                LOTREntityNPC hiredNPC = (LOTREntityNPC)npc;
                if (hiredNPC.hiredNPCInfo.isActive && hiredNPC.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                    String squadron = packet.squadron;
                    if (!StringUtils.isNullOrEmpty((String)squadron)) {
                        squadron = LOTRSquadrons.checkAcceptableLength(squadron);
                        hiredNPC.hiredNPCInfo.setSquadron(squadron);
                    } else {
                        hiredNPC.hiredNPCInfo.setSquadron("");
                    }
                }
            }
            return null;
        }
    }

}

