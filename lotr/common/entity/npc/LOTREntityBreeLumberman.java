/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBreeMarketTrader;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityBreeLumberman
extends LOTREntityBreeMarketTrader {
    public LOTREntityBreeLumberman(World world) {
        super(world);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.BREE_LUMBERMAN_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.BREE_LUMBERMAN_SELL;
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.iron_axe));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        ItemStack hat = new ItemStack(LOTRMod.leatherHat);
        LOTRItemLeatherHat.setHatColor(hat, 6834742);
        LOTRItemLeatherHat.setFeatherColor(hat, 3916082);
        this.setCurrentItemOrArmor(4, hat);
        return data;
    }
}

