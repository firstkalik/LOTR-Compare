/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
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
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.awt.Color;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTravellingTrader;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityHobbitBartender2
extends LOTREntityHobbit
implements LOTRTravellingTrader {
    public LOTREntityHobbitBartender2(World world) {
        super(world);
        this.tasks.addTask(0, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.3, true));
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    public int getTotalArmorValue() {
        return 5;
    }

    @Override
    public LOTREntityNPC createTravellingEscort() {
        return null;
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.HOBBIT_TRADER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.HOBBIT_TRADER_SELL;
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
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerIron));
        this.npcItemsInv.setIdleItem(null);
        ItemStack hat = new ItemStack(LOTRMod.leatherHat);
        float h = 0.06111111f;
        float s = MathHelper.randomFloatClamp((Random)this.rand, (float)0.0f, (float)0.5f);
        float b = MathHelper.randomFloatClamp((Random)this.rand, (float)0.0f, (float)0.5f);
        int hatColor = Color.HSBtoRGB(h, s, b) & 0xFFFFFF;
        LOTRItemLeatherHat.setHatColor(hat, hatColor);
        if (this.rand.nextInt(3) == 0) {
            h = this.rand.nextFloat();
            s = MathHelper.randomFloatClamp((Random)this.rand, (float)0.7f, (float)0.9f);
            b = MathHelper.randomFloatClamp((Random)this.rand, (float)0.8f, (float)1.0f);
        } else {
            h = 0.0f;
            s = 0.0f;
            b = this.rand.nextFloat();
        }
        int featherColor = Color.HSBtoRGB(h, s, b) & 0xFFFFFF;
        LOTRItemLeatherHat.setFeatherColor(hat, featherColor);
        this.setCurrentItemOrArmor(4, hat);
        return data;
    }

    @Override
    protected void dropHobbitItems(boolean flag, int i) {
        int count = this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        block9: for (int k = 0; k < count; ++k) {
            int j = this.rand.nextInt(10);
            switch (j) {
                case 0: 
                case 1: {
                    this.entityDropItem(LOTRFoods.HOBBIT.getRandomFood(this.rand), 0.0f);
                    continue block9;
                }
                case 2: {
                    this.entityDropItem(new ItemStack(Items.gold_nugget, 2 + this.rand.nextInt(3)), 0.0f);
                    continue block9;
                }
                case 3: {
                    this.entityDropItem(new ItemStack(Items.bowl, 1 + this.rand.nextInt(4)), 0.0f);
                    continue block9;
                }
                case 4: {
                    this.entityDropItem(new ItemStack(LOTRMod.hobbitPipe, 1, this.rand.nextInt(100)), 0.0f);
                    continue block9;
                }
                case 5: {
                    this.entityDropItem(new ItemStack(LOTRMod.pipeweed, 1 + this.rand.nextInt(2)), 0.0f);
                    continue block9;
                }
                case 6: 
                case 7: 
                case 8: {
                    this.entityDropItem(new ItemStack(LOTRMod.mug), 0.0f);
                    continue block9;
                }
                case 9: {
                    Item drink = LOTRFoods.HOBBIT_DRINK.getRandomFood(this.rand).getItem();
                    this.entityDropItem(new ItemStack(drink, 1, 1 + this.rand.nextInt(3)), 0.0f);
                }
            }
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
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeBartender);
        if (type == LOTRTradeEntries.TradeType.SELL && itemstack.getItem() == LOTRMod.pipeweedLeaf) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.sellPipeweedLeaf);
        }
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "misc/hobbitTrader/friendly";
        }
        return "misc/hobbitTrader/hostile";
    }

    @Override
    public String getDepartureSpeech() {
        return "misc/hobbitTrader/departure";
    }
}

