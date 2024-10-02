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

import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionData;
import lotr.common.inventory.LOTRContainerUnitTrade;
import lotr.common.inventory.LOTRSlotProtected;
import lotr.common.item.LOTRItemCoin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRSlotAlignmentReward
extends LOTRSlotProtected {
    public static int REWARD_COST;
    private LOTRContainerUnitTrade theContainer;
    private LOTRHireableBase theTrader;
    private LOTREntityNPC theLivingTrader;
    private ItemStack alignmentReward;

    public LOTRSlotAlignmentReward(LOTRContainerUnitTrade container, IInventory inv, int i, int j, int k, LOTRHireableBase entity, ItemStack item) {
        super(inv, i, j, k);
        this.theContainer = container;
        this.theTrader = entity;
        this.theLivingTrader = (LOTREntityNPC)((Object)this.theTrader);
        this.alignmentReward = item.copy();
        REWARD_COST = (int)(2000.0 * (1.0 + (double)(LOTRConfig.maxHiredNPCsCost - 1) * 0.5));
    }

    private int getMetaIndexForRewardCost(int cost) {
        if (cost < 10) {
            return 0;
        }
        if (cost < 100) {
            return 1;
        }
        if (cost < 1000) {
            return 2;
        }
        if (cost < 10000) {
            return 3;
        }
        if (cost < 100000) {
            return 4;
        }
        if (cost < 1000000) {
            return 5;
        }
        return 6;
    }

    public boolean canTakeStack(EntityPlayer entityplayer) {
        if (LOTRLevelData.getData(entityplayer).getAlignment(this.theTrader.getFaction()) < 1500.0f) {
            return false;
        }
        int coins = LOTRItemCoin.getInventoryValue(entityplayer, false);
        if (coins < REWARD_COST) {
            return false;
        }
        return super.canTakeStack(entityplayer);
    }

    public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
        LOTRFaction faction = this.theLivingTrader.getFaction();
        if (!entityplayer.worldObj.isRemote) {
            LOTRItemCoin.takeCoins(REWARD_COST, entityplayer);
            LOTRLevelData.getData(entityplayer).getFactionData(faction).takeConquestHorn();
            this.theLivingTrader.playTradeSound();
        }
        super.onPickupFromSlot(entityplayer, itemstack);
        if (!entityplayer.worldObj.isRemote) {
            int metaIndex = this.getMetaIndexForRewardCost(REWARD_COST);
            ItemStack reward = this.alignmentReward.copy();
            reward.setItemDamage(metaIndex);
            this.putStack(reward);
            ((EntityPlayerMP)entityplayer).sendContainerToPlayer((Container)this.theContainer);
        }
    }
}

