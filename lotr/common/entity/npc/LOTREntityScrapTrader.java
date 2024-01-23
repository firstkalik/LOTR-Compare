/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.awt.Color;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRDimension;
import lotr.common.LOTRFoods;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIDrink;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.npc.LOTREntityMan;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.entity.npc.LOTRTravellingTrader;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityScrapTrader
extends LOTREntityMan
implements LOTRTravellingTrader,
LOTRTradeable.Smith {
    private int timeUntilFadeOut;
    public static final int maxFadeoutTick = 60;

    public LOTREntityScrapTrader(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.3, true));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIEat(this, LOTRFoods.DUNLENDING, 8000));
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIDrink(this, LOTRFoods.DUNLENDING_DRINK, 8000));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 10.0f, 0.1f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 5.0f, 0.05f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(false);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.SCRAP_TRADER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.SCRAP_TRADER_SELL;
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)-1);
    }

    public int getFadeoutTick() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setFadeoutTick(int i) {
        this.dataWatcher.updateObject(20, (Object)i);
    }

    public float getFadeoutProgress(float f) {
        int i = this.getFadeoutTick();
        if (i >= 0) {
            return ((float)(60 - i) + f) / 60.0f;
        }
        return 0.0f;
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public void setupNPCName() {
        int i = this.rand.nextInt(4);
        if (i == 0) {
            this.familyInfo.setName(LOTRNames.getBreeName(this.rand, this.familyInfo.isMale()));
        } else if (i == 1) {
            this.familyInfo.setName(LOTRNames.getDunlendingName(this.rand, this.familyInfo.isMale()));
        } else if (i == 2) {
            this.familyInfo.setName(LOTRNames.getRohirricName(this.rand, this.familyInfo.isMale()));
        } else if (i == 3) {
            this.familyInfo.setName(LOTRNames.getGondorName(this.rand, this.familyInfo.isMale()));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int weapon = this.rand.nextInt(2);
        if (weapon == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerIron));
        } else if (weapon == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerBronze));
        }
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.shireHeather));
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
    public String getNPCName() {
        return this.familyInfo.getName();
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UNALIGNED;
    }

    @Override
    public LOTREntityNPC createTravellingEscort() {
        return null;
    }

    @Override
    public String getDepartureSpeech() {
        return "misc/scrapTrader/departure";
    }

    @Override
    public void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
    }

    public int getTotalArmorValue() {
        return 5;
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        boolean flag = super.interact(entityplayer);
        if (flag && !this.worldObj.isRemote && LOTRDimension.getCurrentDimensionWithFallback(this.worldObj) == LOTRDimension.UTUMNO && this.timeUntilFadeOut <= 0) {
            this.timeUntilFadeOut = 100;
        }
        return flag;
    }

    @Override
    public boolean canBeFreelyTargetedBy(EntityLiving attacker) {
        if (LOTRDimension.getCurrentDimensionWithFallback(this.worldObj) == LOTRDimension.UTUMNO) {
            return false;
        }
        return super.canBeFreelyTargetedBy(attacker);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        if (damagesource.getEntity() != null && LOTRDimension.getCurrentDimensionWithFallback(this.worldObj) == LOTRDimension.UTUMNO) {
            if (!this.worldObj.isRemote && this.getFadeoutTick() < 0) {
                this.setFadeoutTick(60);
            }
            return false;
        }
        return super.attackEntityFrom(damagesource, f);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && LOTRDimension.getCurrentDimensionWithFallback(this.worldObj) == LOTRDimension.UTUMNO) {
            if (this.timeUntilFadeOut > 0) {
                --this.timeUntilFadeOut;
                if (this.timeUntilFadeOut <= 0) {
                    this.setFadeoutTick(60);
                }
            }
            if (this.getFadeoutTick() > 0) {
                this.setFadeoutTick(this.getFadeoutTick() - 1);
                if (this.getFadeoutTick() <= 0) {
                    this.setDead();
                }
            }
        }
    }

    @Override
    public float getAlignmentBonus() {
        return 0.0f;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(Items.bone, 1);
        }
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return this.isFriendly(entityplayer) && LOTRDimension.getCurrentDimensionWithFallback(this.worldObj) != LOTRDimension.UTUMNO;
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeScrapTrader);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (LOTRDimension.getCurrentDimensionWithFallback(this.worldObj) == LOTRDimension.UTUMNO) {
                return "misc/scrapTrader/utumno";
            }
            return "misc/scrapTrader/friendly";
        }
        return "misc/scrapTrader/hostile";
    }

    public String getSmithSpeechBank() {
        return "misc/scrapTrader/smith";
    }
}

