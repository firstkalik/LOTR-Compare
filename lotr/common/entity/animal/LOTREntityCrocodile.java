/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.biome.LOTRBiomeGenFarHaradSwamp;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityCrocodile
extends EntityMob {
    private EntityAIBase targetAI;
    private boolean prevCanTarget = true;

    public LOTREntityCrocodile(World world) {
        super(world);
        this.setSize(2.1f, 0.7f);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.5, false));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 12.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.targetAI = new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true);
        this.targetTasks.addTask(1, this.targetAI);
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, LOTREntityNPC.class, 400, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)0);
    }

    public int getSnapTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setSnapTime(int i) {
        this.dataWatcher.updateObject(20, (Object)i);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected String getLivingSound() {
        return "lotr:crocodile.say";
    }

    protected String getDeathSound() {
        return "lotr:crocodile.death";
    }

    public void onLivingUpdate() {
        int i;
        EntityAnimal entityanimal;
        List list;
        EntityLivingBase entity;
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.isInWater()) {
            this.motionY += 0.02;
        }
        if (!this.worldObj.isRemote && this.getAttackTarget() != null && (!(entity = this.getAttackTarget()).isEntityAlive() || entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)) {
            this.setAttackTarget(null);
        }
        if (!this.worldObj.isRemote) {
            boolean canTarget;
            boolean bl = canTarget = this.getBrightness(1.0f) < 0.5f;
            if (canTarget != this.prevCanTarget) {
                if (canTarget) {
                    this.targetTasks.addTask(1, this.targetAI);
                } else {
                    this.targetTasks.removeTask(this.targetAI);
                }
            }
            this.prevCanTarget = canTarget;
        }
        if (!this.worldObj.isRemote && (i = this.getSnapTime()) > 0) {
            this.setSnapTime(i - 1);
        }
        if (this.getAttackTarget() == null && this.worldObj.rand.nextInt(1000) == 0 && !(list = this.worldObj.getEntitiesWithinAABB(EntityAnimal.class, this.boundingBox.expand(12.0, 6.0, 12.0))).isEmpty() && (entityanimal = (EntityAnimal)list.get(this.rand.nextInt(list.size()))).getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage) == null) {
            this.setAttackTarget((EntityLivingBase)entityanimal);
        }
    }

    protected Item getDropItem() {
        return Items.rotten_flesh;
    }

    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int count = this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        block7: for (int j = 0; j < count; ++j) {
            int drop = this.rand.nextInt(5);
            switch (drop) {
                case 0: {
                    this.dropItem(Items.bone, 1);
                    continue block7;
                }
                case 1: {
                    this.dropItem(Items.fish, 1);
                    continue block7;
                }
                case 2: {
                    this.dropItem(Items.leather, 1);
                    continue block7;
                }
                case 3: {
                    this.dropItem(LOTRMod.zebraRaw, 1);
                    continue block7;
                }
                case 4: {
                    this.dropItem(LOTRMod.gemsbokHide, 1);
                }
            }
        }
    }

    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);
        if (flag) {
            if (!this.worldObj.isRemote) {
                this.setSnapTime(20);
            }
            this.worldObj.playSoundAtEntity((Entity)this, "lotr:crocodile.snap", this.getSoundVolume(), this.getSoundPitch());
        }
        return flag;
    }

    public boolean getCanSpawnHere() {
        List nearbyCrocodiles = this.worldObj.getEntitiesWithinAABB(((Object)((Object)this)).getClass(), this.boundingBox.expand(24.0, 12.0, 24.0));
        if (nearbyCrocodiles.size() > 3) {
            return false;
        }
        if (this.worldObj.checkNoEntityCollision(this.boundingBox) && this.isValidLightLevel() && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).size() == 0) {
            for (int i = -8; i <= 8; ++i) {
                for (int j = -8; j <= 8; ++j) {
                    for (int k = -8; k <= 8; ++k) {
                        int k1;
                        int j1;
                        Block block;
                        int i1 = MathHelper.floor_double((double)this.posX) + i;
                        if (!this.worldObj.blockExists(i1, j1 = MathHelper.floor_double((double)this.posY) + j, k1 = MathHelper.floor_double((double)this.posZ) + k) || (block = this.worldObj.getBlock(i1, j1, k1)).getMaterial() != Material.water) continue;
                        if (this.posY > 60.0) {
                            return true;
                        }
                        if (this.rand.nextInt(50) != 0) continue;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean isValidLightLevel() {
        int k;
        int i = MathHelper.floor_double((double)this.posX);
        if (this.worldObj.getBiomeGenForCoords(i, k = MathHelper.floor_double((double)this.posZ)) instanceof LOTRBiomeGenFarHaradSwamp) {
            return true;
        }
        return super.isValidLightLevel();
    }

    public void moveEntityWithHeading(float f, float f1) {
        if (!this.worldObj.isRemote && this.isInWater() && this.getAttackTarget() != null) {
            this.moveFlying(f, f1, 0.1f);
        }
        super.moveEntityWithHeading(f, f1);
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

