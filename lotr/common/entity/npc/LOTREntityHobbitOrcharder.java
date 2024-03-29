/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityUtils;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityHobbitOrcharder
extends LOTREntityHobbit
implements LOTRTradeable {
    public LOTREntityHobbitOrcharder(World world) {
        super(world);
        LOTREntityUtils.removeAITask(this, EntityAIPanic.class);
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.2, false));
        this.addTargetTasks(false);
        this.isNPCPersistent = false;
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.HOBBIT_ORCHARDER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.HOBBIT_ORCHARDER_SELL;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        ItemStack hat = new ItemStack(LOTRMod.leatherHat);
        LOTRItemLeatherHat.setHatColor(hat, 4818735);
        this.setCurrentItemOrArmor(4, hat);
        int i = this.rand.nextInt(3);
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.iron_axe));
        } else if (i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.stone_axe));
        } else if (i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.axeBronze));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        return data;
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
        if (type == LOTRTradeEntries.TradeType.BUY && itemstack.getItem() instanceof ItemFood) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.buyOrcharderFood);
        }
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "hobbit/orcharder/friendly";
        }
        return "hobbit/hobbit/hostile";
    }
}

