/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.npc.LOTREntityEasterlingMarketTrader;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.item.LOTRItemHaradRobes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTREntityEasterlingGoldsmith
extends LOTREntityEasterlingMarketTrader {
    public LOTREntityEasterlingGoldsmith(World world) {
        super(world);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.RHUN_GOLDSMITH_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.RHUN_GOLDSMITH_SELL;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)source.getEntity();
            player.addPotionEffect(new PotionEffect(LOTRPotions.curse.id, 24000, 0));
        }
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.goldRing));
        int robeColor = 16237060;
        ItemStack body = new ItemStack(LOTRMod.bodyKaftan);
        ItemStack legs = new ItemStack(LOTRMod.legsKaftan);
        LOTRItemHaradRobes.setRobesColor(body, robeColor);
        LOTRItemHaradRobes.setRobesColor(legs, robeColor);
        this.setCurrentItemOrArmor(3, body);
        this.setCurrentItemOrArmor(2, legs);
        return data;
    }
}

