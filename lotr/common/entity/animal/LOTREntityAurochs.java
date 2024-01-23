/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTREntityUtils;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityAurochs
extends EntityCow
implements LOTRRandomSkinEntity {
    private EntityAIBase attackAI;
    private EntityAIBase panicAI;
    private boolean prevIsChild = true;
    protected final float aurochsWidth;
    protected final float aurochsHeight;

    public LOTREntityAurochs(World world) {
        super(world);
        this.aurochsWidth = 1.5f;
        this.aurochsHeight = 1.7f;
        this.setSize(this.aurochsWidth, this.aurochsHeight);
        EntityAITasks.EntityAITaskEntry panic = LOTREntityUtils.removeAITask((EntityCreature)this, EntityAIPanic.class);
        this.tasks.addTask(panic.priority, panic.action);
        this.panicAI = panic.action;
        this.attackAI = this.createAurochsAttackAI();
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    }

    protected EntityAIBase createAurochsAttackAI() {
        return new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.7, true);
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
    }

    public boolean isAurochsEnraged() {
        return this.dataWatcher.getWatchableObjectByte(20) == 1;
    }

    public void setAurochsEnraged(boolean flag) {
        this.dataWatcher.updateObject(20, (Object)(flag ? (byte)1 : 0));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            EntityLivingBase target;
            boolean isChild = this.isChild();
            if (isChild != this.prevIsChild) {
                EntityAITasks.EntityAITaskEntry taskEntry;
                if (isChild) {
                    taskEntry = LOTREntityUtils.removeAITask((EntityCreature)this, this.attackAI.getClass());
                    this.tasks.addTask(taskEntry.priority, this.panicAI);
                } else {
                    taskEntry = LOTREntityUtils.removeAITask((EntityCreature)this, this.panicAI.getClass());
                    this.tasks.addTask(taskEntry.priority, this.attackAI);
                }
            }
            if (this.getAttackTarget() != null && (!(target = this.getAttackTarget()).isEntityAlive() || target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.isCreativeMode)) {
                this.setAttackTarget(null);
            }
            if (this.riddenByEntity instanceof EntityLiving) {
                target = ((EntityLiving)this.riddenByEntity).getAttackTarget();
                this.setAttackTarget(target);
            } else if (this.riddenByEntity instanceof EntityPlayer) {
                this.setAttackTarget(null);
            }
            this.setAurochsEnraged(this.getAttackTarget() != null);
        }
        this.prevIsChild = this.isChild();
    }

    public boolean interact(EntityPlayer entityplayer) {
        if (this.isAurochsEnraged()) {
            return false;
        }
        return super.interact(entityplayer);
    }

    public boolean attackEntityAsMob(Entity entity) {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), f);
        if (flag) {
            float kb = 0.75f;
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * kb * 0.5f), 0.0, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * kb * 0.5f));
        }
        return flag;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity attacker;
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && this.isChild() && (attacker = damagesource.getEntity()) instanceof EntityLivingBase) {
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(12.0, 12.0, 12.0));
            for (int i = 0; i < list.size(); ++i) {
                LOTREntityAurochs aurochs;
                Entity entity = (Entity)list.get(i);
                if (entity.getClass() != this.getClass() || (aurochs = (LOTREntityAurochs)entity).isChild()) continue;
                aurochs.setAttackTarget((EntityLivingBase)attacker);
            }
        }
        return flag;
    }

    protected void dropFewItems(boolean flag, int i) {
        int hides = 2 + this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < hides; ++l) {
            this.dropItem(Items.leather, 1);
        }
        int meats = 2 + this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meats; ++l) {
            if (this.isBurning()) {
                this.dropItem(Items.cooked_beef, 1);
                continue;
            }
            this.dropItem(Items.beef, 1);
        }
        this.dropHornItem(flag, i);
    }

    protected void dropHornItem(boolean flag, int i) {
        this.dropItem(LOTRMod.horn, 1);
    }

    public EntityCow createChild(EntityAgeable entity) {
        return new LOTREntityAurochs(this.worldObj);
    }

    protected String getLivingSound() {
        return "lotr:aurochs.say";
    }

    protected String getHurtSound() {
        return "lotr:aurochs.hurt";
    }

    protected String getDeathSound() {
        return "lotr:aurochs.hurt";
    }

    protected float getSoundVolume() {
        return 1.0f;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.75f;
    }

    public int getTalkInterval() {
        return 200;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

