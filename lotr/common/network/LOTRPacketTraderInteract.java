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
import lotr.common.entity.npc.LOTRTradeable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketTraderInteract
implements IMessage {
    private int traderID;
    private int traderAction;

    public LOTRPacketTraderInteract() {
    }

    public LOTRPacketTraderInteract(int idt, int a) {
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

    public static class Handler
    implements IMessageHandler<LOTRPacketTraderInteract, IMessage> {
        public IMessage onMessage(LOTRPacketTraderInteract packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            World world = entityplayer.worldObj;
            Entity trader = world.getEntityByID(packet.traderID);
            if (trader != null && trader instanceof LOTRTradeable) {
                LOTRTradeable tradeableTrader = (LOTRTradeable)trader;
                LOTREntityNPC livingTrader = (LOTREntityNPC)trader;
                int action = packet.traderAction;
                boolean closeScreen = false;
                if (action == 0) {
                    livingTrader.npcTalkTick = livingTrader.getNPCTalkInterval();
                    closeScreen = livingTrader.interactFirst((EntityPlayer)entityplayer);
                } else if (action == 1 && tradeableTrader.canTradeWith((EntityPlayer)entityplayer)) {
                    entityplayer.openGui((Object)LOTRMod.instance, 3, world, livingTrader.getEntityId(), 0, 0);
                } else if (action == 2 && tradeableTrader.canTradeWith((EntityPlayer)entityplayer)) {
                    entityplayer.openGui((Object)LOTRMod.instance, 35, world, livingTrader.getEntityId(), 0, 0);
                } else if (action == 3 && tradeableTrader.canTradeWith((EntityPlayer)entityplayer) && tradeableTrader instanceof LOTRTradeable.Smith) {
                    entityplayer.openGui((Object)LOTRMod.instance, 54, world, livingTrader.getEntityId(), 0, 0);
                }
                if (closeScreen) {
                    entityplayer.closeScreen();
                }
            }
            return null;
        }
    }

}

