/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeEntry;
import lotr.common.entity.npc.LOTRTraderNPCInfo;
import lotr.common.inventory.LOTRContainerTrade;
import lotr.common.inventory.LOTRSlotProtected;
import lotr.common.item.LOTRItemCoin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRSlotTrade
extends LOTRSlotProtected {
    private LOTRContainerTrade theContainer;
    private LOTREntityNPC theEntity;
    private LOTRTradeEntries.TradeType tradeType;

    public LOTRSlotTrade(LOTRContainerTrade container, IInventory inv, int i, int j, int k, LOTREntityNPC entity, LOTRTradeEntries.TradeType type) {
        super(inv, i, j, k);
        this.theContainer = container;
        this.theEntity = entity;
        this.tradeType = type;
    }

    public int cost() {
        LOTRTradeEntry trade = this.getTrade();
        return trade == null ? 0 : trade.getCost();
    }

    public LOTRTradeEntry getTrade() {
        LOTRTradeEntry[] trades = null;
        if (this.tradeType == LOTRTradeEntries.TradeType.BUY) {
            trades = this.theEntity.traderNPCInfo.getBuyTrades();
        } else if (this.tradeType == LOTRTradeEntries.TradeType.SELL) {
            trades = this.theEntity.traderNPCInfo.getSellTrades();
        }
        if (trades == null) {
            return null;
        }
        int i = this.getSlotIndex();
        if (i >= 0 && i < trades.length) {
            return trades[i];
        }
        return null;
    }

    public boolean canTakeStack(EntityPlayer entityplayer) {
        if (this.tradeType == LOTRTradeEntries.TradeType.BUY) {
            if (this.getTrade() != null && !this.getTrade().isAvailable()) {
                return false;
            }
            int coins = LOTRItemCoin.getInventoryValue(entityplayer, false);
            if (coins < this.cost()) {
                return false;
            }
        }
        if (this.tradeType == LOTRTradeEntries.TradeType.SELL) {
            return false;
        }
        return super.canTakeStack(entityplayer);
    }

    public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
        if (this.tradeType == LOTRTradeEntries.TradeType.BUY && !entityplayer.worldObj.isRemote) {
            LOTRItemCoin.takeCoins(this.cost(), entityplayer);
        }
        super.onPickupFromSlot(entityplayer, itemstack);
        if (this.tradeType == LOTRTradeEntries.TradeType.BUY) {
            LOTRTradeEntry trade = this.getTrade();
            if (!entityplayer.worldObj.isRemote && trade != null) {
                this.putStack(trade.createTradeItem());
                ((EntityPlayerMP)entityplayer).sendContainerToPlayer((Container)this.theContainer);
                this.theEntity.traderNPCInfo.onTrade(entityplayer, trade, LOTRTradeEntries.TradeType.BUY, this.cost());
                this.theEntity.playTradeSound();
            }
        }
    }
}

