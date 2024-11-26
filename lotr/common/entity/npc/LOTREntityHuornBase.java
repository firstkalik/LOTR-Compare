/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.culling.Frustrum
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.init.Blocks
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.client.LOTRTickHandlerClient;
import lotr.common.LOTRPotions;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityTree;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class LOTREntityHuornBase
extends LOTREntityTree {
    public boolean ignoringFrustumForRender = false;

    public LOTREntityHuornBase(World world) {
        super(world);
        this.setSize(1.5f, 4.0f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.5, false));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
    }

    public boolean isHuornActive() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setHuornActive(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)60, (int)80));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(4.0);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isInRangeToRender3d(double d, double d1, double d2) {
        EntityLivingBase viewer = Minecraft.getMinecraft().renderViewEntity;
        float f = LOTRTickHandlerClient.renderTick;
        double viewX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * (double)f;
        double viewY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * (double)f;
        double viewZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * (double)f;
        Frustrum camera = new Frustrum();
        camera.setPosition(viewX, viewY, viewZ);
        AxisAlignedBB expandedBB = this.boundingBox.expand(2.0, 3.0, 2.0);
        if (camera.isBoundingBoxInFrustum(expandedBB)) {
            this.ignoringFrustumForRender = true;
            this.ignoreFrustumCheck = true;
        }
        return super.isInRangeToRender3d(d, d1, d2);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            boolean active = !this.getNavigator().noPath() || this.getAttackTarget() != null && this.getAttackTarget().isEntityAlive();
            this.setHuornActive(active);
        }
    }

    protected int decreaseAirSupply(int i) {
        return i;
    }

    public void applyEntityCollision(Entity entity) {
        if (this.isHuornActive()) {
            super.applyEntityCollision(entity);
        } else {
            double x = this.motionX;
            double y = this.motionY;
            double z = this.motionZ;
            super.applyEntityCollision(entity);
            this.motionX = x;
            this.motionY = y;
            this.motionZ = z;
        }
    }

    public void collideWithEntity(Entity entity) {
        if (this.isHuornActive()) {
            super.collideWithEntity(entity);
        } else {
            double x = this.motionX;
            double y = this.motionY;
            double z = this.motionZ;
            super.collideWithEntity(entity);
            this.motionX = x;
            this.motionY = y;
            this.motionZ = z;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && !this.worldObj.isRemote && !this.isHuornActive()) {
            this.setHuornActive(true);
        }
        return flag;
    }

    protected String getHurtSound() {
        return Blocks.log.stepSound.getBreakSound();
    }

    protected String getDeathSound() {
        return Blocks.log.stepSound.getBreakSound();
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

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.75f;
    }
}

