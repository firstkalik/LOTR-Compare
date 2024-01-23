/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRCapes;
import lotr.common.LOTRGreyWandererTracker;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIGandalfSmoke;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.npc.LOTREntityBalrog;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityQuestInfo;
import lotr.common.entity.npc.LOTREntitySaruman;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRSpeech;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestWelcome;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTREntityGandalf
extends LOTREntityNPC {
    private static final double msgRange = 64.0;

    public LOTREntityGandalf(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.8, false));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIGandalfSmoke(this, 3000));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 8.0f, 0.05f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 5.0f, 0.05f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        int target = this.addTargetTasks(false);
        this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityBalrog.class, 0, true));
        this.targetTasks.addTask(target + 2, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntitySaruman.class, 0, true));
        this.npcCape = LOTRCapes.GANDALF;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.glamdring));
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.gandalfStaffGrey));
        return data;
    }

    @Override
    public void onArtificalSpawn() {
        LOTRGreyWandererTracker.addNewWanderer(this.getUniqueID());
        this.arriveAt(null);
    }

    @Override
    public void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
    }

    @Override
    public ItemStack getHeldItemLeft() {
        ItemStack heldItem = this.getHeldItem();
        if (heldItem != null && heldItem.getItem() == LOTRMod.glamdring) {
            return new ItemStack(LOTRMod.gandalfStaffGrey);
        }
        return super.getHeldItemLeft();
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UNALIGNED;
    }

    @Override
    public boolean canBeFreelyTargetedBy(EntityLiving attacker) {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity entity = damagesource.getEntity();
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode) {
            return super.attackEntityFrom(damagesource, f);
        }
        f = 0.0f;
        return super.attackEntityFrom(damagesource, f);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && !LOTRGreyWandererTracker.isWandererActive(this.getUniqueID()) && this.getAttackTarget() == null) {
            this.depart();
        }
    }

    private void doGandalfFX() {
        this.playSound("random.pop", 2.0f, 0.5f + this.rand.nextFloat() * 0.5f);
        this.worldObj.setEntityState((Entity)this, (byte)16);
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte b) {
        if (b == 16) {
            for (int i = 0; i < 20; ++i) {
                double d0 = this.posX + (double)(MathHelper.randomFloatClamp((Random)this.rand, (float)-1.0f, (float)1.0f) * this.width);
                double d1 = this.posY + (double)(MathHelper.randomFloatClamp((Random)this.rand, (float)0.0f, (float)1.0f) * this.height);
                double d2 = this.posZ + (double)(MathHelper.randomFloatClamp((Random)this.rand, (float)-1.0f, (float)1.0f) * this.width);
                double d3 = this.rand.nextGaussian() * 0.02;
                double d4 = 0.05 + this.rand.nextGaussian() * 0.02;
                double d5 = this.rand.nextGaussian() * 0.02;
                this.worldObj.spawnParticle("explode", d0, d1, d2, d3, d4, d5);
            }
        } else {
            super.handleHealthUpdate(b);
        }
    }

    public void arriveAt(EntityPlayer entityplayer) {
        ArrayList<EntityPlayer> msgPlayers = new ArrayList<EntityPlayer>();
        if (entityplayer != null) {
            msgPlayers.add(entityplayer);
        }
        List worldPlayers = this.worldObj.playerEntities;
        for (Object obj : worldPlayers) {
            EntityPlayer player = (EntityPlayer)obj;
            if (msgPlayers.contains((Object)player)) continue;
            double d = 64.0;
            double dSq = d * d;
            if (!(this.getDistanceSqToEntity((Entity)player) < dSq)) continue;
            msgPlayers.add(player);
        }
        for (EntityPlayer player : msgPlayers) {
            LOTRSpeech.sendSpeechAndChatMessage(player, this, "char/gandalf/arrive");
        }
        this.doGandalfFX();
    }

    private void depart() {
        ArrayList<EntityPlayer> msgPlayers = new ArrayList<EntityPlayer>();
        List worldPlayers = this.worldObj.playerEntities;
        for (Object obj : worldPlayers) {
            EntityPlayer player = (EntityPlayer)obj;
            if (msgPlayers.contains((Object)player)) continue;
            double d = 64.0;
            double dSq = d * d;
            if (!(this.getDistanceSqToEntity((Entity)player) < dSq)) continue;
            msgPlayers.add(player);
        }
        for (EntityPlayer player : msgPlayers) {
            LOTRSpeech.sendSpeechAndChatMessage(player, this, "char/gandalf/depart");
        }
        this.doGandalfFX();
        this.setDead();
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "char/gandalf/friendly";
        }
        return "char/gandalf/hostile";
    }

    @Override
    public boolean speakTo(EntityPlayer entityplayer) {
        if (LOTRGreyWandererTracker.isWandererActive(this.getUniqueID())) {
            if (this.questInfo.getOfferFor(entityplayer) != null) {
                return super.speakTo(entityplayer);
            }
            if (this.addMQOfferFor(entityplayer)) {
                LOTRGreyWandererTracker.setWandererActive(this.getUniqueID());
                String speechBank = "char/gandalf/welcome";
                this.sendSpeechBank(entityplayer, speechBank);
                return true;
            }
        }
        return super.speakTo(entityplayer);
    }

    private boolean addMQOfferFor(EntityPlayer entityplayer) {
        LOTRMiniQuestWelcome quest;
        LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
        if (pd.getMiniQuestsForEntity(this, true).isEmpty() && ((LOTRMiniQuest)(quest = new LOTRMiniQuestWelcome(null, this))).canPlayerAccept(entityplayer)) {
            this.questInfo.setPlayerSpecificOffer(entityplayer, quest);
            return true;
        }
        return false;
    }

    @Override
    public int getMiniquestColor() {
        return 10526880;
    }
}

