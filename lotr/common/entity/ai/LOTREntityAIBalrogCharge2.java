/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityAngbandBalrog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityAIBalrogCharge2
extends LOTREntityAIAttackOnCollide {
    private LOTREntityAngbandBalrog theBalrog;
    private float chargeDist;
    private int frustrationTime;
    private boolean hitChargeTarget = false;
    private int chargingTick;

    public LOTREntityAIBalrogCharge2(LOTREntityAngbandBalrog balrog, double speed, float dist, int fr) {
        super(balrog, speed, false);
        this.theBalrog = balrog;
        this.chargeDist = dist;
        this.frustrationTime = fr;
    }

    @Override
    public boolean shouldExecute() {
        if (this.theBalrog.isBalrogCharging()) {
            return false;
        }
        boolean flag = super.shouldExecute();
        if (flag) {
            if (this.theBalrog.chargeFrustration >= this.frustrationTime) {
                return true;
            }
            double dist = this.theBalrog.getDistanceSqToEntity((Entity)this.attackTarget);
            return dist >= (double)(this.chargeDist * this.chargeDist);
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        if (!this.theBalrog.isEntityAlive()) {
            return false;
        }
        this.attackTarget = this.theBalrog.getAttackTarget();
        if (this.attackTarget == null || !this.attackTarget.isEntityAlive()) {
            return false;
        }
        return this.chargingTick > 0 && !this.hitChargeTarget;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.theBalrog.setBalrogCharging(true);
        this.hitChargeTarget = false;
        this.chargingTick = 200;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.theBalrog.setBalrogCharging(false);
        this.hitChargeTarget = false;
        this.chargingTick = 0;
    }

    @Override
    public void updateTask() {
        this.updateLookAndPathing();
        if (this.chargingTick > 0) {
            --this.chargingTick;
        }
        AxisAlignedBB targetBB = this.theBalrog.boundingBox.expand(0.5, 0.0, 0.5);
        double motionExtent = 2.0;
        float angleRad = (float)Math.atan2(this.theBalrog.motionZ, this.theBalrog.motionX);
        targetBB = targetBB.getOffsetBoundingBox((double)MathHelper.cos((float)angleRad) * motionExtent, 0.0, (double)MathHelper.sin((float)angleRad) * motionExtent);
        List hitTargets = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this.theBalrog, targetBB);
        for (Object hitTarget : hitTargets) {
            EntityLivingBase hitEntity;
            Entity obj = (Entity)hitTarget;
            if (!(obj instanceof EntityLivingBase) || (hitEntity = (EntityLivingBase)obj) == this.theBalrog.riddenByEntity) continue;
            float attackStr = (float)this.theBalrog.getEntityAttribute(LOTREntityAngbandBalrog.balrogChargeDamage).getAttributeValue();
            boolean flag = hitEntity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.theBalrog), attackStr);
            if (!flag) continue;
            float knock = 2.5f;
            float knockY = 0.5f;
            hitEntity.addVelocity((double)(-MathHelper.sin((float)((float)Math.toRadians(this.theBalrog.rotationYaw))) * knock), (double)knockY, (double)(MathHelper.cos((float)((float)Math.toRadians(this.theBalrog.rotationYaw))) * knock));
            hitEntity.setFire(6);
            if (hitEntity != this.attackTarget) continue;
            this.hitChargeTarget = true;
        }
    }
}

