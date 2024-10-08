/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeEntry;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionData;
import lotr.common.inventory.LOTRContainerTrade;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketTraderInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRTraderNPCInfo {
    private LOTREntityNPC theEntity;
    private LOTRTradeEntry[] buyTrades;
    private LOTRTradeEntry[] sellTrades;
    private int timeUntilAdvertisement;
    private int timeSinceTrade;
    private boolean shouldRefresh = true;
    private int valueSinceRefresh;

    public LOTRTraderNPCInfo(LOTREntityNPC npc) {
        this.theEntity = npc;
        if (this.theEntity instanceof LOTRTradeable && !this.theEntity.worldObj.isRemote) {
            this.refreshTrades();
        }
    }

    public LOTRTradeEntry[] getBuyTrades() {
        return this.buyTrades;
    }

    public LOTRTradeEntry[] getSellTrades() {
        return this.sellTrades;
    }

    public void setBuyTrades(LOTRTradeEntry[] trades) {
        this.buyTrades = trades;
    }

    public void setSellTrades(LOTRTradeEntry[] trades) {
        this.sellTrades = trades;
    }

    public void onTrade(EntityPlayer entityplayer, LOTRTradeEntry trade, LOTRTradeEntries.TradeType type, int value) {
        ((LOTRTradeable)((Object)this.theEntity)).onPlayerTrade(entityplayer, type, trade.createTradeItem());
        LOTRLevelData.getData(entityplayer).getFactionData(this.theEntity.getFaction()).addTrade();
        trade.doTransaction(value);
        this.timeSinceTrade = 0;
        this.valueSinceRefresh += value;
        this.sendClientPacket(entityplayer);
    }

    private void refreshTrades() {
        LOTRTradeable theTrader = (LOTRTradeable)((Object)this.theEntity);
        Random rand = this.theEntity.getRNG();
        this.setBuyTrades(theTrader.getBuyPool().getRandomTrades(rand));
        this.setSellTrades(theTrader.getSellPool().getRandomTrades(rand));
        this.valueSinceRefresh = 0;
        for (Object element : this.theEntity.worldObj.playerEntities) {
            EntityPlayer entityplayer = (EntityPlayer)element;
            Container container = entityplayer.openContainer;
            if (!(container instanceof LOTRContainerTrade) || ((LOTRContainerTrade)container).theTraderNPC != this.theEntity) continue;
            ((LOTRContainerTrade)container).updateAllTradeSlots();
        }
    }

    private void setAllTradesDelayed() {
        int delay = 6000;
        for (LOTRTradeEntry trade : this.buyTrades) {
            if (trade == null) continue;
            trade.setLockedForTicks(delay);
        }
        for (LOTRTradeEntry trade : this.sellTrades) {
            if (trade == null) continue;
            trade.setLockedForTicks(delay);
        }
    }

    public void onUpdate() {
        if (!this.theEntity.worldObj.isRemote) {
            if (this.timeUntilAdvertisement > 0) {
                --this.timeUntilAdvertisement;
            }
            ++this.timeSinceTrade;
            boolean sendUpdate = false;
            for (LOTRTradeEntry trade : this.buyTrades) {
                if (trade == null || !trade.updateAvailable(this.theEntity)) continue;
                sendUpdate = true;
            }
            for (LOTRTradeEntry trade : this.sellTrades) {
                if (trade == null || !trade.updateAvailable(this.theEntity)) continue;
                sendUpdate = true;
            }
            if (this.shouldRefresh && this.valueSinceRefresh >= 5000) {
                this.refreshTrades();
                this.setAllTradesDelayed();
                sendUpdate = true;
            }
            if (sendUpdate) {
                for (Object element : this.theEntity.worldObj.playerEntities) {
                    EntityPlayer entityplayer = (EntityPlayer)element;
                    Container container = entityplayer.openContainer;
                    if (!(container instanceof LOTRContainerTrade) || ((LOTRContainerTrade)container).theTraderNPC != this.theEntity) continue;
                    this.sendClientPacket(entityplayer);
                }
            }
            if (this.theEntity.isEntityAlive() && this.theEntity.getAttackTarget() == null && this.timeUntilAdvertisement == 0 && this.timeSinceTrade > 600) {
                double range = 10.0;
                List players = this.theEntity.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.theEntity.boundingBox.expand(range, range, range));
                for (Object obj : players) {
                    String speechBank;
                    EntityPlayer entityplayer = (EntityPlayer)obj;
                    if (!entityplayer.isEntityAlive() || entityplayer.capabilities.isCreativeMode || entityplayer.openContainer != null && entityplayer.openContainer != entityplayer.inventoryContainer || (speechBank = this.theEntity.getSpeechBank(entityplayer)) == null || this.theEntity.getRNG().nextInt(3) != 0) continue;
                    this.theEntity.sendSpeechBank(entityplayer, speechBank);
                }
                this.timeUntilAdvertisement = 20 * MathHelper.getRandomIntegerInRange((Random)this.theEntity.getRNG(), (int)5, (int)20);
            }
        }
    }

    public void writeToNBT(NBTTagCompound data) {
        NBTTagCompound nbt;
        if (this.buyTrades != null) {
            NBTTagList buyTradesTags = new NBTTagList();
            for (LOTRTradeEntry trade : this.buyTrades) {
                if (trade == null) continue;
                nbt = new NBTTagCompound();
                trade.writeToNBT(nbt);
                buyTradesTags.appendTag((NBTBase)nbt);
            }
            NBTTagCompound buyTradesData = new NBTTagCompound();
            buyTradesData.setTag("Trades", (NBTBase)buyTradesTags);
            data.setTag("LOTRBuyTrades", (NBTBase)buyTradesData);
        }
        if (this.sellTrades != null) {
            NBTTagList sellTradesTags = new NBTTagList();
            for (LOTRTradeEntry trade : this.sellTrades) {
                if (trade == null) continue;
                nbt = new NBTTagCompound();
                trade.writeToNBT(nbt);
                sellTradesTags.appendTag((NBTBase)nbt);
            }
            NBTTagCompound sellTradesData = new NBTTagCompound();
            sellTradesData.setTag("Trades", (NBTBase)sellTradesTags);
            data.setTag("LOTRSellTrades", (NBTBase)sellTradesData);
        }
        data.setBoolean("ShouldRefresh", this.shouldRefresh);
        data.setInteger("TimeSinceTrade", this.timeSinceTrade);
        data.setInteger("ValueSinceRefresh", this.valueSinceRefresh);
    }

    public void readFromNBT(NBTTagCompound data) {
        int i;
        NBTTagCompound sellTradesData;
        NBTTagCompound buyTradesData;
        NBTTagCompound nbt;
        if (data.hasKey("LOTRBuyTrades") && (buyTradesData = data.getCompoundTag("LOTRBuyTrades")).hasKey("Trades")) {
            NBTTagList buyTradesTags = buyTradesData.getTagList("Trades", 10);
            this.buyTrades = new LOTRTradeEntry[buyTradesTags.tagCount()];
            for (i = 0; i < buyTradesTags.tagCount(); ++i) {
                nbt = buyTradesTags.getCompoundTagAt(i);
                this.buyTrades[i] = LOTRTradeEntry.readFromNBT(nbt);
            }
        }
        if (data.hasKey("LOTRSellTrades") && (sellTradesData = data.getCompoundTag("LOTRSellTrades")).hasKey("Trades")) {
            NBTTagList sellTradesTags = sellTradesData.getTagList("Trades", 10);
            this.sellTrades = new LOTRTradeEntry[sellTradesTags.tagCount()];
            for (i = 0; i < sellTradesTags.tagCount(); ++i) {
                nbt = sellTradesTags.getCompoundTagAt(i);
                this.sellTrades[i] = LOTRTradeEntry.readFromNBT(nbt);
            }
        }
        if (data.hasKey("ShouldRefresh")) {
            this.shouldRefresh = data.getBoolean("ShouldRefresh");
        }
        this.timeSinceTrade = data.getInteger("TimeSinceTrade");
        this.valueSinceRefresh = data.getInteger("ValueSinceRefresh");
    }

    public void sendClientPacket(EntityPlayer entityplayer) {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        LOTRPacketTraderInfo packet = new LOTRPacketTraderInfo(nbt);
        LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, (EntityPlayerMP)entityplayer);
    }

    public void receiveClientPacket(LOTRPacketTraderInfo packet) {
        NBTTagCompound nbt = packet.traderData;
        this.readFromNBT(nbt);
    }
}

