/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityEasterling;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityEasterlingBartender
extends LOTREntityEasterling
implements LOTRTradeable.Bartender {
    public LOTREntityEasterlingBartender(World world) {
        super(world);
        this.addTargetTasks(false);
        this.npcLocationName = "entity.lotr.EasterlingBartender.locationName";
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.RHUN_BARTENDER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.RHUN_BARTENDER_SELL;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.mug));
        return data;
    }

    @Override
    public void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int drinks = 1 + this.rand.nextInt(4) + i;
        for (int l = 0; l < drinks; ++l) {
            ItemStack drink = LOTRFoods.RHUN_DRINK.getRandomFood(this.rand);
            this.entityDropItem(drink, 0.0f);
        }
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return this.isFriendly(entityplayer);
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeRhunBartender);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "rhun/bartender/friendly";
        }
        return "rhun/bartender/hostile";
    }
}

