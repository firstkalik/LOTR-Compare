/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.util.StringUtils
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRSquadrons;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.npc.LOTRMercenaryTradeEntry;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeEntry;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.inventory.LOTRContainerUnitTrade;
import lotr.common.util.LOTRLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.Logger;

public class LOTRPacketBuyUnit
implements IMessage {
    private int tradeIndex;
    private String squadron;

    public LOTRPacketBuyUnit() {
    }

    public LOTRPacketBuyUnit(int tr, String s) {
        this.tradeIndex = tr;
        this.squadron = s;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.tradeIndex);
        if (!StringUtils.isNullOrEmpty((String)this.squadron)) {
            byte[] squadronBytes = this.squadron.getBytes(Charsets.UTF_8);
            data.writeInt(squadronBytes.length);
            data.writeBytes(squadronBytes);
        } else {
            data.writeInt(-1);
        }
    }

    public void fromBytes(ByteBuf data) {
        this.tradeIndex = data.readByte();
        int squadronLength = data.readInt();
        if (squadronLength > -1) {
            this.squadron = data.readBytes(squadronLength).toString(Charsets.UTF_8);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketBuyUnit, IMessage> {
        public IMessage onMessage(LOTRPacketBuyUnit packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            Container container = entityplayer.openContainer;
            if (container != null && container instanceof LOTRContainerUnitTrade) {
                LOTRContainerUnitTrade tradeContainer = (LOTRContainerUnitTrade)container;
                LOTRHireableBase unitTrader = tradeContainer.theUnitTrader;
                int tradeIndex = packet.tradeIndex;
                LOTRUnitTradeEntry trade = null;
                if (unitTrader instanceof LOTRUnitTradeable) {
                    LOTRUnitTradeEntry[] tradeList = ((LOTRUnitTradeable)unitTrader).getUnits().tradeEntries;
                    if (tradeIndex >= 0 && tradeIndex < tradeList.length) {
                        trade = tradeList[tradeIndex];
                    }
                } else if (unitTrader instanceof LOTRMercenary) {
                    trade = LOTRMercenaryTradeEntry.createFor((LOTRMercenary)unitTrader);
                }
                String squadron = packet.squadron;
                squadron = LOTRSquadrons.checkAcceptableLength(squadron);
                if (trade != null) {
                    trade.hireUnit((EntityPlayer)entityplayer, unitTrader, squadron);
                    if (unitTrader instanceof LOTRMercenary) {
                        entityplayer.closeScreen();
                    }
                } else {
                    LOTRLog.logger.error("LOTR: Error player " + entityplayer.getCommandSenderName() + " trying to hire unit from " + unitTrader.getNPCName() + " - trade is null or bad index!");
                }
            }
            return null;
        }
    }

}

