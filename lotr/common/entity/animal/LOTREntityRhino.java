/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.LOTRReflection;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityRhino
extends LOTREntityHorse {
    public LOTREntityRhino(World world) {
        super(world);
        this.setSize(1.7f, 1.9f);
    }

    @Override
    protected boolean isMountHostile() {
        return true;
    }

    @Override
    protected EntityAIBase createMountAttackAI() {
        return new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.0, true);
    }

    public int getHorseType() {
        return 0;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
    }

    @Override
    protected void onLOTRHorseSpawn() {
        double maxHealth = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
        maxHealth *= 1.5;
        maxHealth = Math.max(maxHealth, 40.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed *= 1.2);
        double jumpStrength = this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue();
        this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength *= 0.5);
    }

    @Override
    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)20.0, (double)50.0);
    }

    @Override
    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.2, (double)0.8);
    }

    @Override
    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.12, (double)0.42);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.wheat;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            if (this.riddenByEntity instanceof EntityLivingBase) {
                EntityLivingBase rhinoRider = (EntityLivingBase)this.riddenByEntity;
                float momentum = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
                if (momentum > 0.2f) {
                    this.setSprinting(true);
                } else {
                    this.setSprinting(false);
                }
                if (momentum >= 0.32f) {
                    float strength = momentum * 15.0f;
                    Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ);
                    Vec3 look = this.getLookVec();
                    float sightWidth = 1.0f;
                    double range = 0.5;
                    List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.contract(1.0, 1.0, 1.0).addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expand((double)sightWidth, (double)sightWidth, (double)sightWidth));
                    boolean hitAnyEntities = false;
                    for (Object element : list) {
                        EntityLiving entityliving;
                        EntityLivingBase entity;
                        Entity obj = (Entity)element;
                        if (!(obj instanceof EntityLivingBase) || (entity = (EntityLivingBase)obj) == rhinoRider || rhinoRider instanceof EntityPlayer && !LOTRMod.canPlayerAttackEntity((EntityPlayer)rhinoRider, entity, false) || rhinoRider instanceof EntityCreature && !LOTRMod.canNPCAttackEntity((EntityCreature)rhinoRider, entity, false) || !entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), strength)) continue;
                        float knockback = strength * 0.05f;
                        entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback), (double)knockback, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback));
                        hitAnyEntities = true;
                        if (!(entity instanceof EntityLiving) || (entityliving = (EntityLiving)entity).getAttackTarget() != this) continue;
                        entityliving.getNavigator().clearPathEntity();
                        entityliving.setAttackTarget(rhinoRider);
                    }
                    if (hitAnyEntities) {
                        this.worldObj.playSoundAtEntity((Entity)this, "lotr:troll.ologHai_hammer", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
                    }
                }
            } else if (this.getAttackTarget() != null) {
                float momentum = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
                if (momentum > 0.2f) {
                    this.setSprinting(true);
                } else {
                    this.setSprinting(false);
                }
            } else {
                this.setSprinting(false);
            }
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        int k;
        int j = this.rand.nextInt(2) + this.rand.nextInt(1 + i);
        for (int k2 = 0; k2 < j; ++k2) {
            this.dropItem(LOTRMod.rhinoHorn, 1);
        }
        int j3 = this.rand.nextInt(2) + this.rand.nextInt(1 + i);
        for (k = 0; k < j3; ++k) {
            this.dropItem(Items.bone, 1);
        }
        k = 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int j1 = 0; j1 < k; ++j1) {
            this.dropItem(Items.leather, 1);
        }
        int meat = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.rhinoCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.rhinoRaw, 1);
        }
    }

    protected String getLivingSound() {
        return "lotr:rhino.say";
    }

    protected String getHurtSound() {
        return "lotr:rhino.hurt";
    }

    protected String getDeathSound() {
        return "lotr:rhino.death";
    }

    protected String getAngrySoundName() {
        return "lotr:rhino.say";
    }
}

