/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.npc.LOTREntityUmbarian;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemHaradRobes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTREntityUmbarBlacksmith
extends LOTREntityUmbarian
implements LOTRTradeable.Smith {
    public LOTREntityUmbarBlacksmith(World world) {
        super(world);
        this.addTargetTasks(false);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.UMBAR_BLACKSMITH_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.UMBAR_BLACKSMITH_SELL;
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
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
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.blacksmithHammer));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        ItemStack turban = new ItemStack(LOTRMod.helmetHaradRobes);
        LOTRItemHaradRobes.setRobesColor(turban, 4462600);
        this.setCurrentItemOrArmor(4, turban);
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int ingots = 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int l = 0; l < ingots; ++l) {
            this.dropItem(Items.iron_ingot, 1);
        }
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 50.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeUmbarBlacksmith);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.canTradeWith(entityplayer)) {
                return "nearHarad/umbar/blacksmith/friendly";
            }
            return "nearHarad/umbar/blacksmith/neutral";
        }
        return "nearHarad/umbar/blacksmith/hostile";
    }
}

