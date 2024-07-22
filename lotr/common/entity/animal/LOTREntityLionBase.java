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
 *  net.minecraft.entity.ai.EntityAIFollowParent
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAILionChase;
import lotr.common.entity.ai.LOTREntityAIMFMate;
import lotr.common.entity.animal.LOTREntityAnimalMF;
import lotr.common.entity.animal.LOTREntityLion;
import lotr.common.entity.animal.LOTREntityLioness;
import lotr.common.item.LOTRItemLionRug;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class LOTREntityLionBase
extends LOTREntityAnimalMF {
    private EntityAIBase attackAI = new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.5, false);
    private EntityAIBase panicAI = new EntityAIPanic((EntityCreature)this, 1.5);
    private EntityAIBase targetNearAI = new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true);
    private int hostileTick = 0;
    private boolean prevIsChild = true;

    public LOTREntityLionBase(World world) {
        super(world);
        this.setSize(1.4f, 1.6f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, this.panicAI);
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIMFMate(this, 1.0));
        this.tasks.addTask(4, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.4, Items.fish, false));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.4));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAILionChase(this, 1.5));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.targetTasks.addTask(1, this.targetNearAI);
    }

    @Override
    public Class getAnimalMFBaseClass() {
        return LOTREntityLionBase.class;
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)0);
    }

    public boolean isHostile() {
        return this.dataWatcher.getWatchableObjectByte(20) == 1;
    }

    public void setHostile(boolean flag) {
        this.dataWatcher.updateObject(20, (Object)(flag ? (byte)1 : 0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public void onLivingUpdate() {
        boolean isChild;
        EntityLivingBase entity;
        if (!this.worldObj.isRemote && (isChild = this.isChild()) != this.prevIsChild) {
            if (isChild) {
                this.tasks.removeTask(this.attackAI);
                this.tasks.addTask(2, this.panicAI);
                this.targetTasks.removeTask(this.targetNearAI);
            } else {
                this.tasks.removeTask(this.panicAI);
                if (this.hostileTick > 0) {
                    this.tasks.addTask(1, this.attackAI);
                    this.targetTasks.addTask(1, this.targetNearAI);
                } else {
                    this.tasks.removeTask(this.attackAI);
                    this.targetTasks.removeTask(this.targetNearAI);
                }
            }
        }
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.getAttackTarget() != null && (!(entity = this.getAttackTarget()).isEntityAlive() || entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)) {
            this.setAttackTarget(null);
        }
        if (!this.worldObj.isRemote) {
            if (this.hostileTick > 0 && this.getAttackTarget() == null) {
                --this.hostileTick;
            }
            this.setHostile(this.hostileTick > 0);
            if (this.isHostile()) {
                this.resetInLove();
            }
        }
    }

    protected void dropFewItems(boolean flag, int i) {
        int furs = 1 + this.rand.nextInt(3) + 1;
        for (int l = 0; l < furs; ++l) {
            this.dropItem(LOTRMod.lionFur, 1);
        }
        int k = 1 + this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int j = 0; j < k; ++j) {
            this.dropItem(Items.bone, 1);
        }
        int meats = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + i);
        for (int l = 0; l < meats; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.lionCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.lionRaw, 1);
        }
        if (flag) {
            int rugChance = 30 - i * 5;
            if (this.rand.nextInt(rugChance = Math.max(rugChance, 1)) == 0) {
                this.entityDropItem(new ItemStack(LOTRMod.lionRug, 1, this.getLionRugType().lionID), 0.0f);
            }
        }
    }

    protected abstract LOTRItemLionRug.LionRugType getLionRugType();

    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 2 + this.worldObj.rand.nextInt(3);
    }

    public boolean attackEntityAsMob(Entity entity) {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), f);
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return this.rand.nextBoolean() ? new LOTREntityLion(this.worldObj) : new LOTREntityLioness(this.worldObj);
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity attacker;
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && (attacker = damagesource.getEntity()) instanceof EntityLivingBase) {
            if (this.isChild()) {
                double range = 12.0;
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(range, range, range));
                for (LOTREntityLionBase obj : list) {
                    LOTREntityLionBase lion;
                    LOTREntityLionBase entity = obj;
                    if (!(entity instanceof LOTREntityLionBase) || (lion = entity).isChild()) continue;
                    lion.becomeAngryAt((EntityLivingBase)attacker);
                }
            } else {
                this.becomeAngryAt((EntityLivingBase)attacker);
            }
        }
        return flag;
    }

    private void becomeAngryAt(EntityLivingBase entity) {
        this.setAttackTarget(entity);
        this.hostileTick = 200;
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("Angry", this.hostileTick);
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.hostileTick = nbt.getInteger("Angry");
    }

    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack.getItem() == Items.fish;
    }

    public boolean interact(EntityPlayer entityplayer) {
        if (this.isHostile()) {
            return false;
        }
        return super.interact(entityplayer);
    }

    public int getTalkInterval() {
        return 300;
    }

    protected String getLivingSound() {
        return "lotr:lion.say";
    }

    protected String getHurtSound() {
        return "lotr:lion.hurt";
    }

    protected String getDeathSound() {
        return "lotr:lion.death";
    }
}

