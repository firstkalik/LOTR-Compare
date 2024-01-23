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
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.projectile.LOTREntityMarshWraithBall;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityMarshWraith
extends LOTREntityNPC {
    public UUID attackTargetUUID;
    private boolean checkedForAttackTarget;
    private int timeUntilDespawn = -1;
    private static final int maxTimeUntilDespawn = 100;

    public LOTREntityMarshWraith(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.tasks.addTask(0, (EntityAIBase)new LOTREntityAIRangedAttack(this, 1.6, 40, 12.0f));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f, 0.02f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.ignoreFrustumCheck = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
    }

    public int getSpawnFadeTime() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setSpawnFadeTime(int i) {
        this.dataWatcher.updateObject(16, (Object)i);
    }

    public int getDeathFadeTime() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    public void setDeathFadeTime(int i) {
        this.dataWatcher.updateObject(17, (Object)i);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.HOSTILE;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("SpawnFadeTime", this.getSpawnFadeTime());
        nbt.setInteger("DeathFadeTime", this.getDeathFadeTime());
        if (this.attackTargetUUID != null) {
            nbt.setLong("TargetUUIDMost", this.attackTargetUUID.getMostSignificantBits());
            nbt.setLong("TargetUUIDLeast", this.attackTargetUUID.getLeastSignificantBits());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setSpawnFadeTime(nbt.getInteger("SpawnFadeTime"));
        this.setDeathFadeTime(nbt.getInteger("DeathFadeTime"));
        if (nbt.hasKey("TargetUUIDMost") && nbt.hasKey("TargetUUIDLeast")) {
            this.attackTargetUUID = new UUID(nbt.getLong("TargetUUIDMost"), nbt.getLong("TargetUUIDLeast"));
        }
    }

    public void setInWeb() {
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && !this.isDead) {
            int hover = 2;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.posY);
            int k = MathHelper.floor_double((double)this.posZ);
            double newY = this.posY;
            for (int j1 = 0; j1 <= hover; ++j1) {
                int j2 = j - j1;
                Block block = this.worldObj.getBlock(i, j2, k);
                Material material = block.getMaterial();
                if (!material.isSolid() && !material.isLiquid()) continue;
                newY = Math.max(newY, (double)(j + j1 + 1));
            }
            this.motionY += (newY - this.posY) * 0.04;
        }
        if (this.rand.nextBoolean()) {
            this.worldObj.spawnParticle("smoke", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote) {
            int i;
            if (this.getAttackTarget() == null && this.attackTargetUUID != null && !this.checkedForAttackTarget) {
                for (i = 0; i < this.worldObj.loadedEntityList.size(); ++i) {
                    Entity entity = (Entity)this.worldObj.loadedEntityList.get(i);
                    if (!(entity instanceof EntityLiving) || !entity.getUniqueID().equals(this.attackTargetUUID)) continue;
                    this.setAttackTarget((EntityLivingBase)((EntityLiving)entity));
                    break;
                }
                this.checkedForAttackTarget = true;
            }
            if (this.getSpawnFadeTime() < 30) {
                this.setSpawnFadeTime(this.getSpawnFadeTime() + 1);
            }
            if (this.getDeathFadeTime() > 0) {
                this.setDeathFadeTime(this.getDeathFadeTime() - 1);
            }
            if (this.getSpawnFadeTime() == 30 && this.getDeathFadeTime() == 0) {
                if (this.getAttackTarget() == null || this.getAttackTarget().isDead) {
                    this.setDeathFadeTime(30);
                } else {
                    int j;
                    int k;
                    if (this.timeUntilDespawn == -1) {
                        this.timeUntilDespawn = 100;
                    }
                    if (this.worldObj.getBlock(i = MathHelper.floor_double((double)this.getAttackTarget().posX), j = MathHelper.floor_double((double)this.getAttackTarget().boundingBox.minY), k = MathHelper.floor_double((double)this.getAttackTarget().posZ)).getMaterial() == Material.water || this.worldObj.getBlock(i, j - 1, k).getMaterial() == Material.water) {
                        this.timeUntilDespawn = 100;
                    } else if (this.timeUntilDespawn > 0) {
                        --this.timeUntilDespawn;
                    } else {
                        this.setDeathFadeTime(30);
                        this.setAttackTarget(null);
                    }
                }
            }
            if (this.getDeathFadeTime() == 1) {
                this.setDead();
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        if (this.getSpawnFadeTime() == 30 && this.getDeathFadeTime() == 0) {
            LOTREntityMarshWraithBall ball = new LOTREntityMarshWraithBall(this.worldObj, (EntityLivingBase)this, target);
            this.playSound("lotr:wraith.marshWraith_shoot", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
            this.worldObj.spawnEntityInWorld((Entity)ball);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        ItemStack itemstack;
        boolean vulnerable = false;
        Entity entity = damagesource.getEntity();
        if (entity instanceof EntityLivingBase && entity == damagesource.getSourceOfDamage() && (itemstack = ((EntityLivingBase)entity).getHeldItem()) != null && LOTREnchantmentHelper.hasEnchant(itemstack, LOTREnchantment.baneWraith)) {
            vulnerable = true;
        }
        if (vulnerable && this.getDeathFadeTime() == 0) {
            boolean flag = super.attackEntityFrom(damagesource, f);
            if (flag) {
                this.timeUntilDespawn = 100;
            }
            return flag;
        }
        return false;
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote) {
            this.setDeathFadeTime(30);
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int flesh = 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int l = 0; l < flesh; ++l) {
            this.dropItem(Items.rotten_flesh, 1);
        }
        this.dropChestContents(LOTRChestContents.MARSH_REMAINS, 1, 3 + i);
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killMarshWraith;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected String getHurtSound() {
        return "lotr:wight.hurt";
    }

    protected String getDeathSound() {
        return "lotr:wight.death";
    }

    public boolean handleWaterMovement() {
        return false;
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
    }

    @Override
    public boolean canReEquipHired(int slot, ItemStack itemstack) {
        return false;
    }
}

