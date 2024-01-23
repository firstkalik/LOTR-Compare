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
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketHiredUnitCommand
implements IMessage {
    private int entityID;
    private int page;
    private int action;
    private int value;

    public LOTRPacketHiredUnitCommand() {
    }

    public LOTRPacketHiredUnitCommand(int eid, int p, int a, int v) {
        this.entityID = eid;
        this.page = p;
        this.action = a;
        this.value = v;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeByte(this.page);
        data.writeByte(this.action);
        data.writeByte(this.value);
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.page = data.readByte();
        this.action = data.readByte();
        this.value = data.readByte();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketHiredUnitCommand, IMessage> {
        public IMessage onMessage(LOTRPacketHiredUnitCommand packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity npc = world.getEntityByID(packet.entityID);
            if (npc != null && npc instanceof LOTREntityNPC) {
                LOTREntityNPC hiredNPC = (LOTREntityNPC)npc;
                if (hiredNPC.hiredNPCInfo.isActive && hiredNPC.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                    int page = packet.page;
                    int action = packet.action;
                    int value = packet.value;
                    if (action == -1) {
                        hiredNPC.hiredNPCInfo.isGuiOpen = false;
                    } else {
                        LOTRHiredNPCInfo.Task task = hiredNPC.hiredNPCInfo.getTask();
                        if (task == LOTRHiredNPCInfo.Task.WARRIOR) {
                            if (page == 0) {
                                entityplayer.openGui((Object)LOTRMod.instance, 46, world, hiredNPC.getEntityId(), 0, 0);
                            } else if (page == 1) {
                                if (action == 0) {
                                    hiredNPC.hiredNPCInfo.teleportAutomatically = !hiredNPC.hiredNPCInfo.teleportAutomatically;
                                } else if (action == 1) {
                                    hiredNPC.hiredNPCInfo.setGuardMode(!hiredNPC.hiredNPCInfo.isGuardMode());
                                } else if (action == 2) {
                                    hiredNPC.hiredNPCInfo.setGuardRange(value);
                                }
                            }
                        } else if (task == LOTRHiredNPCInfo.Task.FARMER) {
                            if (action == 0) {
                                hiredNPC.hiredNPCInfo.setGuardMode(!hiredNPC.hiredNPCInfo.isGuardMode());
                            } else if (action == 1) {
                                hiredNPC.hiredNPCInfo.setGuardRange(value);
                            } else if (action == 2) {
                                entityplayer.openGui((Object)LOTRMod.instance, 22, world, hiredNPC.getEntityId(), 0, 0);
                            }
                        }
                        hiredNPC.hiredNPCInfo.sendClientPacket(false);
                    }
                }
            }
            return null;
        }
    }

}

