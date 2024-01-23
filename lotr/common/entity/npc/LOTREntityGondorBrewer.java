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
import lotr.common.entity.npc.LOTREntityGondorMarketTrader;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityGondorBrewer
extends LOTREntityGondorMarketTrader {
    public LOTREntityGondorBrewer(World world) {
        super(world);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.GONDOR_BREWER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.GONDOR_BREWER_SELL;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.mugAle));
        return data;
    }
}

