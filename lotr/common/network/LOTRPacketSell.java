/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeEntry;
import lotr.common.entity.npc.LOTRTradeSellResult;
import lotr.common.entity.npc.LOTRTraderNPCInfo;
import lotr.common.inventory.LOTRContainerTrade;
import lotr.common.item.LOTRItemCoin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketSell
implements IMessage {
    public void toBytes(ByteBuf data) {
    }

    public void fromBytes(ByteBuf data) {
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSell, IMessage> {
        public IMessage onMessage(LOTRPacketSell packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            Container container = entityplayer.openContainer;
            if (container != null && container instanceof LOTRContainerTrade) {
                LOTRContainerTrade tradeContainer = (LOTRContainerTrade)container;
                LOTREntityNPC trader = tradeContainer.theTraderNPC;
                IInventory invSellOffer = tradeContainer.tradeInvSellOffer;
                HashMap<LOTRTradeEntry, Integer> tradesUsed = new HashMap<LOTRTradeEntry, Integer>();
                int totalCoins = 0;
                for (int i = 0; i < invSellOffer.getSizeInventory(); ++i) {
                    LOTRTradeSellResult sellResult;
                    ItemStack itemstack = invSellOffer.getStackInSlot(i);
                    if (itemstack == null || (sellResult = LOTRTradeEntries.getItemSellResult(itemstack, trader)) == null) continue;
                    int tradeIndex = sellResult.tradeIndex;
                    int value = sellResult.totalSellValue;
                    int itemsSold = sellResult.itemsSold;
                    LOTRTradeEntry[] sellTrades = trader.traderNPCInfo.getSellTrades();
                    LOTRTradeEntry trade = null;
                    if (sellTrades != null) {
                        trade = sellTrades[tradeIndex];
                    }
                    totalCoins += value;
                    if (trade != null) {
                        Integer prevValue = (Integer)tradesUsed.get(trade);
                        if (prevValue == null) {
                            tradesUsed.put(trade, value);
                        } else {
                            tradesUsed.put(trade, prevValue + value);
                        }
                    }
                    itemstack.stackSize -= itemsSold;
                    if (itemstack.stackSize > 0) continue;
                    invSellOffer.setInventorySlotContents(i, null);
                }
                if (totalCoins > 0) {
                    for (Map.Entry e : tradesUsed.entrySet()) {
                        LOTRTradeEntry trade = (LOTRTradeEntry)e.getKey();
                        int value = (Integer)e.getValue();
                        trader.traderNPCInfo.onTrade((EntityPlayer)entityplayer, trade, LOTRTradeEntries.TradeType.SELL, value);
                    }
                    LOTRItemCoin.giveCoins(totalCoins, (EntityPlayer)entityplayer);
                    if (totalCoins >= 1000) {
                        LOTRLevelData.getData((EntityPlayer)entityplayer).addAchievement(LOTRAchievement.earnManyCoins);
                    }
                    trader.playTradeSound();
                }
            }
            return null;
        }
    }

}

