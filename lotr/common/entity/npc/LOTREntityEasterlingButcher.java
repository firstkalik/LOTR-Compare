/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityEasterlingMarketTrader;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.item.LOTRItemHaradRobes;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityEasterlingButcher
extends LOTREntityEasterlingMarketTrader {
    public LOTREntityEasterlingButcher(World world) {
        super(world);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.RHUN_BUTCHER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.RHUN_BUTCHER_SELL;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.rabbitRaw));
        int robeColor = 14509656;
        ItemStack body = new ItemStack(LOTRMod.bodyKaftan);
        ItemStack legs = new ItemStack(LOTRMod.legsKaftan);
        LOTRItemHaradRobes.setRobesColor(body, robeColor);
        LOTRItemHaradRobes.setRobesColor(legs, robeColor);
        this.setCurrentItemOrArmor(3, body);
        this.setCurrentItemOrArmor(2, legs);
        return data;
    }
}

