/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBreeMarketTrader;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityBreeFlorist
extends LOTREntityBreeMarketTrader {
    public LOTREntityBreeFlorist(World world) {
        super(world);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.BREE_FLORIST_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.BREE_FLORIST_SELL;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.lavender, 1, 0));
        return data;
    }
}

