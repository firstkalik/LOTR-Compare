/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.block.LOTRBlockCorruptMallorn;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIEntHealSapling;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityQuestInfo;
import lotr.common.entity.npc.LOTREntityTree;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemEntDraught;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityEnt
extends LOTREntityTree {
    private Random branchRand = new Random();
    public int eyesClosed;
    public ChunkCoordinates saplingHealTarget;
    public boolean canHealSapling = true;

    public LOTREntityEnt(World world) {
        super(world);
        this.questInfo.setOfferChance(4000);
        this.questInfo.setMinAlignment(150.0f);
        this.setSize(1.4f, 4.6f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIEntHealSapling(this, 1.5));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 2.0, false));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 12.0f, 0.02f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 8.0f, 0.02f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 10.0f, 0.02f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.addTargetTasks(true);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
    }

    public boolean isHealingSapling() {
        return this.dataWatcher.getWatchableObjectByte(18) == 1;
    }

    public void setHealingSapling(boolean flag) {
        this.dataWatcher.updateObject(18, (Object)(flag ? (byte)1 : 0));
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getEntName(this.rand));
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.ENT.createQuest(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)100, (int)120));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
        this.getEntityAttribute(npcAttackDamageExtra).setBaseValue(1.0);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(8.0);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.FANGORN;
    }

    @Override
    public void setAttackTarget(EntityLivingBase target) {
        super.setAttackTarget(target);
        if (this.getAttackTarget() == null) {
            this.canHealSapling = true;
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        if (this.saplingHealTarget != null) {
            nbt.setInteger("SaplingHealX", this.saplingHealTarget.posX);
            nbt.setInteger("SaplingHealY", this.saplingHealTarget.posY);
            nbt.setInteger("SaplingHealZ", this.saplingHealTarget.posZ);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("EntName")) {
            this.familyInfo.setName(nbt.getString("EntName"));
        }
        if (nbt.hasKey("SaplingHealX")) {
            int x = nbt.getInteger("SaplingHealX");
            int y = nbt.getInteger("SaplingHealY");
            int z = nbt.getInteger("SaplingHealZ");
            this.saplingHealTarget = new ChunkCoordinates(x, y, z);
        }
    }

    @Override
    public String getNPCName() {
        return this.familyInfo.getName();
    }

    public int getExtraHeadBranches() {
        long l = this.getUniqueID().getLeastSignificantBits();
        l = l * 365620672396L ^ l * 12784892284L ^ l;
        l = l * l * 18569660L + l * 6639092L;
        this.branchRand.setSeed(l);
        if (this.branchRand.nextBoolean()) {
            return 0;
        }
        return MathHelper.getRandomIntegerInRange((Random)this.branchRand, (int)2, (int)5);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) {
            if (this.eyesClosed > 0) {
                --this.eyesClosed;
            } else if (this.rand.nextInt(400) == 0) {
                this.eyesClosed = 30;
            }
            if (this.isHealingSapling()) {
                for (int l = 0; l < 2; ++l) {
                    float angle = this.rotationYawHead + 90.0f + MathHelper.randomFloatClamp((Random)this.rand, (float)-40.0f, (float)40.0f);
                    angle = (float)Math.toRadians(angle);
                    double d = this.posX + (double)MathHelper.cos((float)angle) * 1.5;
                    double d1 = this.boundingBox.minY + (double)(this.height * MathHelper.randomFloatClamp((Random)this.rand, (float)0.3f, (float)0.6f));
                    double d2 = this.posZ + (double)MathHelper.sin((float)angle) * 1.5;
                    double d3 = (double)MathHelper.cos((float)angle) * 0.06;
                    double d4 = -0.03;
                    double d5 = (double)MathHelper.sin((float)angle) * 0.06;
                    LOTRMod.proxy.spawnParticle("leafGold_30", d, d1, d2, d3, d4, d5);
                }
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            List entities;
            float attackDamage = (float)this.getEntityAttribute(LOTREntityNPC.npcAttackDamage).getAttributeValue();
            float knockbackModifier = 0.25f * attackDamage;
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f), 0.0, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f));
            this.worldObj.playSoundAtEntity(entity, "lotr:ent.step", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            if (!this.worldObj.isRemote && !(entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entity.boundingBox.expand(4.0, 4.0, 4.0))).isEmpty()) {
                for (Object entitie : entities) {
                    float f;
                    EntityLivingBase hitEntity = (EntityLivingBase)entitie;
                    if (hitEntity == this || hitEntity == entity || !LOTRMod.canNPCAttackEntity(this, hitEntity, false)) continue;
                    float strength = 4.0f - entity.getDistanceToEntity((Entity)hitEntity);
                    strength += 1.0f;
                    if (f > 4.0f) {
                        strength = 4.0f;
                    }
                    if (!hitEntity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), strength / 4.0f * attackDamage)) continue;
                    float knockback = strength * 0.25f;
                    if (knockback < 0.75f) {
                        knockback = 0.75f;
                    }
                    hitEntity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f), 0.2 + 0.12 * (double)knockback, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (!this.worldObj.isRemote && flag) {
            if (damagesource.getEntity() != null) {
                this.setHealingSapling(false);
            }
            if (this.getAttackTarget() != null) {
                this.canHealSapling = false;
            }
        }
        return flag;
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote && damagesource.getEntity() instanceof EntityPlayer && this.saplingHealTarget != null) {
            int i = this.saplingHealTarget.posX;
            int j = this.saplingHealTarget.posY;
            int k = this.saplingHealTarget.posZ;
            Block block = this.worldObj.getBlock(i, j, k);
            int meta = this.worldObj.getBlockMetadata(i, j, k);
            if (block == LOTRMod.corruptMallorn) {
                if (++meta >= LOTRBlockCorruptMallorn.ENT_KILLS) {
                    LOTRBlockCorruptMallorn.summonEntBoss(this.worldObj, i, j, k);
                } else {
                    this.worldObj.setBlockMetadataWithNotify(i, j, k, meta, 3);
                }
            }
        }
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killEnt;
    }

    @Override
    public float getAlignmentBonus() {
        return 3.0f;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag) {
            int dropChance = 10 - i * 2;
            if (dropChance < 1) {
                dropChance = 1;
            }
            if (this.rand.nextInt(dropChance) == 0) {
                this.entityDropItem(new ItemStack(LOTRMod.entDraught, 1, this.rand.nextInt(LOTRItemEntDraught.draughtTypes.length)), 0.0f);
            }
        }
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 5 + this.rand.nextInt(6);
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        this.playSound("lotr:ent.step", 0.75f, this.getSoundPitch());
    }

    @Override
    protected LOTRAchievement getTalkAchievement() {
        return LOTRAchievement.talkEnt;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "ent/ent/friendly";
        }
        return "ent/ent/hostile";
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotionID() == Potion.wither.id) {
            return;
        }
        if (effect.getPotionID() == LOTRPotions.blood.id) {
            return;
        }
        if (effect.getPotionID() == LOTRPotions.infection.id) {
            return;
        }
        if (effect.getPotionID() == LOTRPotions.broken.id) {
            return;
        }
        super.addPotionEffect(effect);
    }
}

