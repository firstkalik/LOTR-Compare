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
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAISauronUseMace;
import lotr.common.entity.npc.LOTRBoss;
import lotr.common.entity.npc.LOTRBossInfo;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntitySauron
extends LOTREntityNPC
implements LOTRBoss {
    public LOTREntitySauron(World world) {
        super(world);
        this.setSize(0.8f, 2.2f);
        this.isImmuneToFire = true;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAISauronUseMace(this));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 2.0, false));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0f, 0.02f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true);
    }

    private void doGandalfFX() {
        this.playSound("lotr:item.nazghul", 2.0f, 0.5f + this.rand.nextFloat() * 0.5f);
        this.worldObj.setEntityState((Entity)this, (byte)16);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(8.0);
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killSauron;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.setCurrentItemOrArmor(0, new ItemStack(LOTRMod.sauronMace));
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.MORDOR;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        ItemStack itemstack;
        Entity entity = damagesource.getEntity();
        if (damagesource.isFireDamage()) {
            return super.attackEntityFrom(damagesource, f);
        }
        if (entity instanceof EntityLivingBase && entity == damagesource.getSourceOfDamage() && (itemstack = ((EntityLivingBase)entity).getHeldItem()) != null && (itemstack.getItem() == LOTRMod.anduril || itemstack.getItem() == LOTRMod.ringil || itemstack.getItem() == LOTRMod.melkor_sword || itemstack.getItem() == LOTRMod.balrogWhip || itemstack.getItem() == LOTRMod.sauronMace || itemstack.getItem() == LOTRMod.grond || itemstack.getItem() == LOTRMod.battleaxe_melkor || itemstack.getItem() == LOTRMod.gandalfStaffWhite || itemstack.getItem() == LOTRMod.gandalfStaffGrey || itemstack.getItem() == LOTRMod.gandalfStaffGrey || itemstack.getItem() == LOTRMod.hammerAule || itemstack.getItem() == LOTRMod.glamdring)) {
            return super.attackEntityFrom(damagesource, f);
        }
        return super.attackEntityFrom(damagesource, 0.0f);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            List entities;
            float attackDamage = (float)this.getEntityAttribute(LOTREntityNPC.npcAttackDamage).getAttributeValue();
            float knockbackModifier = 0.25f * attackDamage;
            entity.setFire(2);
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f), 0.0, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f));
            this.worldObj.playSoundAtEntity(entity, "lotr:troll.ologHai_hammer", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
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

    public int getTotalArmorValue() {
        return 17;
    }

    @Override
    public float getAlignmentBonus() {
        return 5000.0f;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.getHealth() < this.getMaxHealth() && this.ticksExisted % 10 == 0) {
            this.heal(1.5f);
        }
        if (this.getIsUsingMace() && this.worldObj.isRemote) {
            for (int i = 0; i < 6; ++i) {
                double d = this.posX - 2.0 + (double)(this.rand.nextFloat() * 4.0f);
                double d1 = this.posY + (double)(this.rand.nextFloat() * 3.0f);
                double d2 = this.posZ - 2.0 + (double)(this.rand.nextFloat() * 4.0f);
                double d3 = (this.posX - d) / 8.0;
                double d4 = (this.posY + 0.5 - d1) / 8.0;
                double d5 = (this.posZ - d2) / 8.0;
                double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                double d7 = 1.0 - d6;
                double d8 = 0.0;
                double d9 = 0.0;
                double d10 = 0.0;
                if (d7 > 0.0) {
                    d7 *= d7;
                    d8 += d3 / d6 * d7 * 0.2;
                    d9 += d4 / d6 * d7 * 0.2;
                    d10 += d5 / d6 * d7 * 0.2;
                }
                this.worldObj.spawnParticle("smoke", d, d1, d2, d8, d9, d10);
            }
        }
    }

    @Override
    protected void fall(float f) {
    }

    public void addPotionEffect(PotionEffect effect) {
    }

    protected int decreaseAirSupply(int i) {
        return i;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        int l;
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (l = 1; l < bones; ++l) {
            this.dropItem(LOTRMod.sauronMace, 1);
        }
        for (l = 1; l < bones; ++l) {
            this.dropItem(LOTRMod.mithril, 16);
        }
        for (l = 1; l < bones; ++l) {
            this.dropItem(LOTRMod.bodyBilbo, 1);
        }
        for (l = 1; l < bones; ++l) {
            this.dropItem(LOTRMod.theOneRing, 1);
        }
        if (flag) {
            int rareDropChance = 0 - i * 4;
            if (this.rand.nextInt(rareDropChance = Math.max(rareDropChance, 1)) == 0) {
                int randDrop = this.rand.nextInt(4);
                switch (randDrop) {
                    case 0: {
                        this.entityDropItem(new ItemStack(Items.iron_ingot), 0.0f);
                        break;
                    }
                    case 1: {
                        this.entityDropItem(new ItemStack(this.getDwarfSteelDrop()), 0.0f);
                        break;
                    }
                    case 2: {
                        this.entityDropItem(new ItemStack(LOTRMod.blockOreStorage, 6, 7), 0.0f);
                        break;
                    }
                    case 3: {
                        this.entityDropItem(new ItemStack(LOTRMod.bodyBilbo, 1 + this.rand.nextInt(1)), 0.0f);
                    }
                }
            }
            int mithrilBookChance = 1 - i * 5;
            if (this.rand.nextInt(mithrilBookChance = Math.max(mithrilBookChance, 1)) == 0) {
                this.entityDropItem(new ItemStack(LOTRMod.sauronMace), 0.0f);
                this.entityDropItem(new ItemStack(LOTRMod.blockOreStorage, 5, 4), 0.0f);
                this.entityDropItem(new ItemStack(LOTRMod.blockOreStorage, 5, 3), 0.0f);
                this.entityDropItem(new ItemStack(LOTRMod.blockOreStorage, 5, 2), 0.0f);
                this.entityDropItem(new ItemStack(Blocks.gold_block, 10, 4), 0.0f);
                this.entityDropItem(new ItemStack(LOTRMod.h1, 1), 0.0f);
            }
        }
    }

    protected Item getDwarfSteelDrop() {
        return LOTRMod.mithril;
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 10.0f, false);
            this.setDead();
        }
    }

    public boolean getIsUsingMace() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setIsUsingMace(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
    }

    @Override
    public LOTRAchievement getBossKillAchievement() {
        return LOTRAchievement.killSauron;
    }

    @Override
    public float getBaseChanceModifier() {
        return this.bossInfo.getHealthChanceModifier();
    }

    @Override
    public void onJumpAttackFall() {
        this.worldObj.setEntityState((Entity)this, (byte)20);
        this.playSound("lotr:troll.ologHai_hammer", 1.5f, 0.75f);
    }
}

