/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 */
package lotr.common.quest;

import java.util.Random;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemMug;
import lotr.common.quest.IPickpocketable;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestCollectBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class LOTRMiniQuestCollect
extends LOTRMiniQuestCollectBase {
    public ItemStack collectItem;

    public LOTRMiniQuestCollect(LOTRPlayerData pd) {
        super(pd);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (this.collectItem != null) {
            NBTTagCompound itemData = new NBTTagCompound();
            this.collectItem.writeToNBT(itemData);
            nbt.setTag("Item", (NBTBase)itemData);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("Item")) {
            NBTTagCompound itemData = nbt.getCompoundTag("Item");
            this.collectItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)itemData);
        }
    }

    @Override
    public boolean isValidQuest() {
        return super.isValidQuest() && this.collectItem != null;
    }

    @Override
    public String getQuestObjective() {
        return StatCollector.translateToLocalFormatted((String)"lotr.miniquest.collect", (Object[])new Object[]{this.collectTarget, this.collectItem.getDisplayName()});
    }

    @Override
    public String getObjectiveInSpeech() {
        return this.collectTarget + " " + this.collectItem.getDisplayName();
    }

    @Override
    public String getProgressedObjectiveInSpeech() {
        return this.collectTarget - this.amountGiven + " " + this.collectItem.getDisplayName();
    }

    @Override
    public ItemStack getQuestIcon() {
        return this.collectItem;
    }

    @Override
    protected boolean isQuestItem(ItemStack itemstack) {
        if (IPickpocketable.Helper.isPickpocketed(itemstack)) {
            return false;
        }
        if (LOTRItemMug.isItemFullDrink(this.collectItem)) {
            ItemStack collectDrink = LOTRItemMug.getEquivalentDrink(this.collectItem);
            ItemStack offerDrink = LOTRItemMug.getEquivalentDrink(itemstack);
            return collectDrink.getItem() == offerDrink.getItem();
        }
        return itemstack.getItem() == this.collectItem.getItem() && (this.collectItem.getItemDamage() == 32767 || itemstack.getItemDamage() == this.collectItem.getItemDamage());
    }

    public static class QFCollect<Q extends LOTRMiniQuestCollect>
    extends LOTRMiniQuest.QuestFactoryBase<Q> {
        private ItemStack collectItem;
        private int minTarget;
        private int maxTarget;

        public QFCollect(String name) {
            super(name);
        }

        public QFCollect setCollectItem(ItemStack itemstack, int min, int max) {
            this.collectItem = itemstack;
            if (this.collectItem.isItemStackDamageable()) {
                this.collectItem.setItemDamage(32767);
            }
            this.minTarget = min;
            this.maxTarget = max;
            return this;
        }

        @Override
        public Class getQuestClass() {
            return LOTRMiniQuestCollect.class;
        }

        @Override
        public Q createQuest(LOTREntityNPC npc, Random rand) {
            LOTRMiniQuestCollect quest = (LOTRMiniQuestCollect)super.createQuest(npc, rand);
            quest.collectItem = this.collectItem.copy();
            quest.collectTarget = MathHelper.getRandomIntegerInRange((Random)rand, (int)this.minTarget, (int)this.maxTarget);
            return (Q)quest;
        }
    }

}

