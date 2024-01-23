/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.oredict.OreDictionary
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemMug;
import lotr.common.quest.IPickpocketable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class LOTRTradeEntry {
    private final ItemStack tradeItem;
    private int tradeCost;
    private int recentTradeValue;
    private static final int valueThreshold = 200;
    private static final int valueDecayTime = 60;
    private int lockedTicks;

    public LOTRTradeEntry(ItemStack itemstack, int cost) {
        this.tradeItem = itemstack;
        this.tradeCost = cost;
    }

    public ItemStack createTradeItem() {
        return this.tradeItem.copy();
    }

    public int getCost() {
        return this.tradeCost;
    }

    public void setCost(int cost) {
        this.tradeCost = cost;
    }

    public boolean isAvailable() {
        return this.recentTradeValue < 200 && this.lockedTicks <= 0;
    }

    public float getLockedProgress() {
        return (float)this.recentTradeValue / 200.0f;
    }

    public int getLockedProgressInt(int i) {
        float f = this.getLockedProgress();
        return Math.round(f * (float)i);
    }

    public int getLockedProgressSlot() {
        return this.getLockedProgressInt(16);
    }

    public boolean updateAvailable(LOTREntityNPC entity) {
        boolean prevAvailable = this.isAvailable();
        int prevLockProgress = this.getLockedProgressSlot();
        if (entity.ticksExisted % 60 == 0 && this.recentTradeValue > 0) {
            --this.recentTradeValue;
        }
        if (this.lockedTicks > 0) {
            --this.lockedTicks;
        }
        if (this.isAvailable() != prevAvailable) {
            return true;
        }
        return this.getLockedProgressSlot() != prevLockProgress;
    }

    public boolean matches(ItemStack itemstack) {
        if (IPickpocketable.Helper.isPickpocketed(itemstack)) {
            return false;
        }
        ItemStack tradeCreated = this.createTradeItem();
        if (LOTRItemMug.isItemFullDrink(tradeCreated)) {
            ItemStack tradeDrink = LOTRItemMug.getEquivalentDrink(tradeCreated);
            ItemStack offerDrink = LOTRItemMug.getEquivalentDrink(itemstack);
            return tradeDrink.getItem() == offerDrink.getItem();
        }
        return OreDictionary.itemMatches((ItemStack)tradeCreated, (ItemStack)itemstack, (boolean)false);
    }

    public void doTransaction(int value) {
        this.recentTradeValue += value;
    }

    public void setLockedForTicks(int ticks) {
        this.lockedTicks = ticks;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        this.tradeItem.writeToNBT(nbt);
        nbt.setInteger("Cost", this.tradeCost);
        nbt.setInteger("RecentTradeValue", this.recentTradeValue);
        nbt.setInteger("LockedTicks", this.lockedTicks);
    }

    public static LOTRTradeEntry readFromNBT(NBTTagCompound nbt) {
        ItemStack savedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt);
        if (savedItem != null) {
            int cost = nbt.getInteger("Cost");
            LOTRTradeEntry trade = new LOTRTradeEntry(savedItem, cost);
            if (nbt.hasKey("RecentTradeValue")) {
                trade.recentTradeValue = nbt.getInteger("RecentTradeValue");
            }
            trade.lockedTicks = nbt.getInteger("LockedTicks");
            return trade;
        }
        return null;
    }
}

