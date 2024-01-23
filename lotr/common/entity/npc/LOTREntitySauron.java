/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAISauronUseMace;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntitySauron
extends LOTREntityNPC {
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

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.18);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(8.0);
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

    public int getTotalArmorValue() {
        return 20;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.getHealth() < this.getMaxHealth() && this.ticksExisted % 10 == 0) {
            this.heal(2.0f);
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
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
            this.setDead();
        }
    }

    public boolean getIsUsingMace() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setIsUsingMace(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
    }
}

