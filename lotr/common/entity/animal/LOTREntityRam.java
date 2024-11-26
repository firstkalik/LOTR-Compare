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
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTRRandomSkinEntity;
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

public class LOTREntityRam
extends LOTREntityHorse
implements LOTRRandomSkinEntity {
    public LOTREntityRam(World world) {
        super(world);
        this.setSize(1.1f, 1.3f);
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    @Override
    protected boolean isMountHostile() {
        return true;
    }

    public int getTalkInterval() {
        return 300;
    }

    @Override
    protected EntityAIBase createMountAttackAI() {
        return new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.2, true);
    }

    public int getHorseType() {
        return 0;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            if (this.riddenByEntity instanceof EntityLivingBase) {
                EntityLivingBase momentum = (EntityLivingBase)this.riddenByEntity;
                float momentum1 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
                if (momentum1 > 0.2f) {
                    this.setSprinting(true);
                } else {
                    this.setSprinting(false);
                }
                if (momentum1 >= 0.32f) {
                    float strength = momentum1 * 15.0f;
                    Vec3 look = this.getLookVec();
                    float sightWidth = 1.0f;
                    double range = 0.5;
                    List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.contract(1.0, 1.0, 1.0).addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expand((double)sightWidth, (double)sightWidth, (double)sightWidth));
                    boolean hitAnyEntities = false;
                    for (Object obj : list) {
                        boolean flag;
                        EntityLiving entityliving;
                        EntityLivingBase entity;
                        if (!(obj instanceof EntityLivingBase) || (entity = (EntityLivingBase)obj) == momentum || momentum instanceof EntityPlayer && !LOTRMod.canPlayerAttackEntity((EntityPlayer)momentum, entity, false) || momentum instanceof EntityCreature && !LOTRMod.canNPCAttackEntity((EntityCreature)momentum, entity, false) || !(flag = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), strength))) continue;
                        float knockback = strength * 0.05f;
                        entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback), (double)knockback, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback));
                        hitAnyEntities = true;
                        if (!(entity instanceof EntityLiving) || (entityliving = (EntityLiving)entity).getAttackTarget() != this) continue;
                        entityliving.getNavigator().clearPathEntity();
                        entityliving.setAttackTarget(momentum);
                    }
                    if (hitAnyEntities) {
                        this.worldObj.playSoundAtEntity((Entity)this, "lotr:troll.ologHai_hammer", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
                    }
                }
            } else if (this.getAttackTarget() != null) {
                float var17 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
                if (var17 > 0.2f) {
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
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.65);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.wheat;
    }

    @Override
    public void dropFewItems(boolean flag, int i) {
        int meat = this.rand.nextInt(4) + 1 + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.muttonCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.muttonRaw, 1);
        }
    }

    @Override
    public double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)20.0, (double)55.0);
    }

    @Override
    public double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.4, (double)1.1);
    }

    @Override
    public double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.12, (double)0.42);
    }

    public String getLivingSound() {
        return "lotr:ram.say";
    }

    public String getHurtSound() {
        return "lotr:ram.hurt";
    }

    public String getDeathSound() {
        return "lotr:ram.death";
    }

    public String getAngrySoundName() {
        return "lotr:ram.angry";
    }
}

