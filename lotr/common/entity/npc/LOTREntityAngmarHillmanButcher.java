/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import addon.drealm.database.DRAchievement;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngmarHillmanMarketTrader;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeEntry;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAngmarHillmanButcher
extends LOTREntityAngmarHillmanMarketTrader {
    public LOTREntityAngmarHillmanButcher(World world) {
        super(world);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return new LOTRTradeEntries(LOTRTradeEntries.TradeType.BUY, new LOTRTradeEntry(new ItemStack(Items.beef), 5), new LOTRTradeEntry(new ItemStack(Items.porkchop), 5), new LOTRTradeEntry(new ItemStack(LOTRMod.gammon), 6), new LOTRTradeEntry(new ItemStack(LOTRMod.muttonRaw), 5), new LOTRTradeEntry(new ItemStack(Items.chicken), 4), new LOTRTradeEntry(new ItemStack(LOTRMod.rabbitRaw), 4), new LOTRTradeEntry(new ItemStack(LOTRMod.deerRaw), 4), new LOTRTradeEntry(new ItemStack(Items.leather), 3), new LOTRTradeEntry(new ItemStack(Items.feather), 3));
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return new LOTRTradeEntries(LOTRTradeEntries.TradeType.SELL, new LOTRTradeEntry(new ItemStack(LOTRMod.daggerIron), 3), new LOTRTradeEntry(new ItemStack(LOTRMod.daggerBronze), 3), new LOTRTradeEntry(new ItemStack(Items.iron_ingot), 3), new LOTRTradeEntry(new ItemStack(Items.lead), 4), new LOTRTradeEntry(new ItemStack(Items.wheat, 3), 1), new LOTRTradeEntry(new ItemStack(LOTRMod.salt), 10));
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(DRAchievement.trade_rhudaur_butcher);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(Items.porkchop));
        return data;
    }
}

