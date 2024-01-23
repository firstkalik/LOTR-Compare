/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTRAmbientSpawnChecks;
import lotr.common.entity.npc.LOTREntityBalrog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntitySwan
extends EntityCreature
implements LOTRAmbientCreature {
    public float flapPhase;
    public float flapPower;
    public float prevFlapPower;
    public float prevFlapPhase;
    public float flapAccel = 1.0f;
    private int peckTime;
    private int peckLength;
    private int timeUntilHiss;
    private static Random violenceRand = new Random();
    private boolean assignedAttackOrFlee = false;
    private EntityAIBase attackAI = new LOTREntityAIAttackOnCollide(this, 1.4, true);
    private EntityAIBase fleeAI = new EntityAIPanic((EntityCreature)this, 1.8);
    private IEntitySelector swanAttackRange = new IEntitySelector(){

        public boolean isEntityApplicable(Entity entity) {
            return entity instanceof EntityLivingBase && entity.isEntityAlive() && LOTREntitySwan.this.getDistanceSqToEntity(entity) < 16.0;
        }
    };
    private static boolean wreckBalrogs = false;

    public LOTREntitySwan(World world) {
        super(world);
        this.setSize(0.5f, 0.7f);
        this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, this.attackAI);
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLivingBase.class, 10.0f, 0.05f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        if (wreckBalrogs) {
            this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, LOTREntityBalrog.class, 0, true));
        }
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true, false, this.swanAttackRange));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void onLivingUpdate() {
        EntityLivingBase target;
        super.onLivingUpdate();
        this.prevFlapPhase = this.flapPhase;
        this.prevFlapPower = this.flapPower;
        this.flapPower += this.onGround ? -0.02f : 0.05f;
        this.flapPower = Math.max(0.0f, Math.min(this.flapPower, 1.0f));
        if (!this.onGround) {
            this.flapAccel = 0.6f;
        }
        this.flapPhase += this.flapAccel;
        this.flapAccel *= 0.95f;
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        if (this.inWater && this.motionY < 0.0) {
            this.motionY *= 0.01;
        }
        if (!this.worldObj.isRemote && this.getAttackTarget() != null && (!(target = this.getAttackTarget()).isEntityAlive() || target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.isCreativeMode)) {
            this.setAttackTarget(null);
        }
        if (this.peckLength > 0) {
            ++this.peckTime;
            if (this.peckTime >= this.peckLength) {
                this.peckTime = 0;
                this.peckLength = 0;
            }
        } else {
            this.peckTime = 0;
        }
    }

    private boolean isViolentSwan() {
        long seed = this.getUniqueID().getLeastSignificantBits();
        violenceRand.setSeed(seed);
        return violenceRand.nextBoolean();
    }

    public void updateAITasks() {
        if (!this.assignedAttackOrFlee) {
            this.tasks.removeTask(this.attackAI);
            this.tasks.removeTask(this.fleeAI);
            boolean violent = this.isViolentSwan();
            if (violent) {
                this.tasks.addTask(1, this.attackAI);
            } else {
                this.tasks.addTask(1, this.fleeAI);
            }
            this.assignedAttackOrFlee = true;
        }
        super.updateAITasks();
        if (this.timeUntilHiss <= 0) {
            List nearbyPlayers;
            double range;
            if (this.getAttackTarget() == null && this.rand.nextInt(3) == 0 && !(nearbyPlayers = this.worldObj.selectEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(range = 8.0, range, range), LOTRMod.selectNonCreativePlayers())).isEmpty()) {
                EntityPlayer entityplayer = (EntityPlayer)nearbyPlayers.get(this.rand.nextInt(nearbyPlayers.size()));
                this.getNavigator().clearPathEntity();
                float hissLook = (float)Math.toDegrees(Math.atan2(entityplayer.posZ - this.posZ, entityplayer.posX - this.posX));
                this.rotationYaw = this.rotationYawHead = (hissLook -= 90.0f);
                this.worldObj.setEntityState((Entity)this, (byte)21);
                this.playSound("lotr:swan.hiss", this.getSoundVolume(), this.getSoundPitch());
                this.timeUntilHiss = 80 + this.rand.nextInt(80);
            }
        } else {
            --this.timeUntilHiss;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte b) {
        if (b == 20) {
            this.peckLength = 10;
        } else if (b == 21) {
            this.peckLength = 40;
        } else {
            super.handleHealthUpdate(b);
        }
    }

    public float getPeckAngle(float tick) {
        if (this.peckLength == 0) {
            return 0.0f;
        }
        float peck = ((float)this.peckTime + tick) / (float)this.peckLength;
        float cutoff = 0.2f;
        if (peck < cutoff) {
            return peck / cutoff;
        }
        if (peck < 1.0f - cutoff) {
            return 1.0f;
        }
        return (1.0f - peck) / cutoff;
    }

    public boolean attackEntityAsMob(Entity entity) {
        boolean flag;
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        if (wreckBalrogs && entity instanceof LOTREntityBalrog) {
            f *= 50.0f;
        }
        if (flag = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), f)) {
            this.worldObj.setEntityState((Entity)this, (byte)20);
            if (wreckBalrogs && entity instanceof LOTREntityBalrog) {
                entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * 2.0f), 0.2, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * 2.0f));
                this.setFire(0);
            }
            return true;
        }
        return false;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag;
        Entity entity = damagesource.getEntity();
        if (wreckBalrogs && entity instanceof LOTREntityBalrog) {
            f /= 20.0f;
        }
        if (flag = super.attackEntityFrom(damagesource, f)) {
            if (wreckBalrogs && entity instanceof LOTREntityBalrog) {
                this.setFire(0);
            }
            return true;
        }
        return false;
    }

    protected void fall(float f) {
    }

    public void dropFewItems(boolean flag, int i) {
        int feathers = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < feathers; ++l) {
            this.dropItem(LOTRMod.swanFeather, 1);
        }
    }

    protected boolean canDespawn() {
        return true;
    }

    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            return LOTRAmbientSpawnChecks.canSpawn((EntityLiving)this, 16, 8, 40, 2, Material.water);
        }
        return false;
    }

    public float getBlockPathWeight(int i, int j, int k) {
        return this.worldObj.getBlock(i, j - 1, k) == this.worldObj.getBiomeGenForCoords((int)i, (int)k).topBlock ? 10.0f : this.worldObj.getLightBrightness(i, j, k) - 0.5f;
    }

    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 1 + this.worldObj.rand.nextInt(2);
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

}

