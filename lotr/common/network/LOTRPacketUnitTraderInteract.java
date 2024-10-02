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
import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketUnitTraderInteract
implements IMessage {
    private int traderID;
    private int traderAction;
    private static final int BASE_MAX_HIRED_NPCS = LOTRConfig.maxHiredNPCs;
    private static final int ADDITIONAL_NPCS_PER_1000_REP = 5;

    public LOTRPacketUnitTraderInteract() {
    }

    public LOTRPacketUnitTraderInteract(int idt, int a) {
        this.traderID = idt;
        this.traderAction = a;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.traderID);
        data.writeByte(this.traderAction);
    }

    public void fromBytes(ByteBuf data) {
        this.traderID = data.readInt();
        this.traderAction = data.readByte();
    }

    protected void openTradeGUI(EntityPlayer entityplayer, LOTREntityNPC trader) {
        LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
        int maxHiredNPCs = this.getMaxHiredNPCs(entityplayer, trader.getFaction());
        int hiredNPCCount = this.getGlobalHiredNPCCount(entityplayer);
        entityplayer.openGui((Object)LOTRMod.instance, 7, entityplayer.worldObj, trader.getEntityId(), 0, 0);
    }

    private int getMaxHiredNPCs(EntityPlayer entityplayer, LOTRFaction faction) {
        LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
        float alignment = playerData.getAlignment(faction);
        int additionalNPCs = (int)(alignment / 1000.0f) * 5;
        int customMaxHiredNPCs = playerData.getCustomMaxHiredNPCs();
        if (customMaxHiredNPCs > 0) {
            return customMaxHiredNPCs;
        }
        return BASE_MAX_HIRED_NPCS + additionalNPCs;
    }

    private int getGlobalHiredNPCCount(EntityPlayer entityplayer) {
        LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
        return playerData.getGlobalHiredNPCCount();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketUnitTraderInteract, IMessage> {
        public IMessage onMessage(LOTRPacketUnitTraderInteract packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity trader = world.getEntityByID(packet.traderID);
            if (trader != null && trader instanceof LOTRHireableBase) {
                LOTRHireableBase tradeableTrader = (LOTRHireableBase)trader;
                LOTREntityNPC livingTrader = (LOTREntityNPC)trader;
                int action = packet.traderAction;
                boolean closeScreen = false;
                if (action == 0) {
                    livingTrader.npcTalkTick = livingTrader.getNPCTalkInterval();
                    closeScreen = livingTrader.interactFirst((EntityPlayer)entityplayer);
                } else if (action == 1 && tradeableTrader.canTradeWith((EntityPlayer)entityplayer)) {
                    packet.openTradeGUI((EntityPlayer)entityplayer, livingTrader);
                }
                if (closeScreen) {
                    entityplayer.closeScreen();
                }
            }
            return null;
        }
    }

}

