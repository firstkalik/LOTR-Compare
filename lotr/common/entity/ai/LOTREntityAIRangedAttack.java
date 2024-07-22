/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package lotr.common.entity.ai;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class LOTREntityAIRangedAttack
extends EntityAIBase {
    private EntityLiving theOwner;
    private IRangedAttackMob theOwnerRanged;
    private EntityLivingBase attackTarget;
    private int rangedAttackTime = -1;
    private double moveSpeed;
    private double moveSpeedFlee = 1.5;
    private int repathDelay;
    private int attackTimeMin;
    private int attackTimeMax;
    private float attackRange;
    private float attackRangeSq;

    public LOTREntityAIRangedAttack(IRangedAttackMob entity, double speed, int time, float range) {
        this(entity, speed, time, time, range);
    }

    public LOTREntityAIRangedAttack(IRangedAttackMob entity, double speed, int min, int max, float range) {
        this.theOwnerRanged = entity;
        this.theOwner = (EntityLiving)entity;
        this.moveSpeed = speed;
        this.attackTimeMin = min;
        this.attackTimeMax = max;
        this.attackRange = range;
        this.attackRangeSq = range * range;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        EntityLivingBase target = this.theOwner.getAttackTarget();
        if (target == null) {
            return false;
        }
        this.attackTarget = target;
        return true;
    }

    public boolean continueExecuting() {
        if (!this.theOwner.isEntityAlive()) {
            return false;
        }
        this.attackTarget = this.theOwner.getAttackTarget();
        return this.attackTarget != null && this.attackTarget.isEntityAlive();
    }

    public void resetTask() {
        this.attackTarget = null;
        this.repathDelay = 0;
        this.rangedAttackTime = -1;
    }

    public void updateTask() {
        double distanceSq = this.theOwner.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
        boolean canSee = this.theOwner.getEntitySenses().canSee((Entity)this.attackTarget);
        int n = this.repathDelay = canSee ? (this.repathDelay = this.repathDelay + 1) : 0;
        if (distanceSq <= (double)this.attackRangeSq) {
            if (this.theOwner.getDistanceSqToEntity((Entity)this.attackTarget) < 25.0) {
                Vec3 vec = LOTREntityAIRangedAttack.findPositionAwayFrom((EntityLivingBase)this.theOwner, this.attackTarget, 8, 16);
                if (vec != null) {
                    this.theOwner.getNavigator().tryMoveToXYZ(vec.xCoord, vec.yCoord, vec.zCoord, this.moveSpeedFlee);
                }
            } else if (this.repathDelay >= 20) {
                this.theOwner.getNavigator().clearPathEntity();
                this.repathDelay = 0;
            }
        } else {
            this.theOwner.getNavigator().tryMoveToEntityLiving((Entity)this.attackTarget, this.moveSpeed);
        }
        this.theOwner.getLookHelper().setLookPositionWithEntity((Entity)this.attackTarget, 30.0f, 30.0f);
        --this.rangedAttackTime;
        if (this.rangedAttackTime == 0) {
            float distanceRatio;
            if (distanceSq > (double)this.attackRangeSq || !canSee) {
                return;
            }
            float power = distanceRatio = MathHelper.sqrt_double((double)distanceSq) / this.attackRange;
            power = MathHelper.clamp_float((float)power, (float)0.1f, (float)1.0f);
            this.theOwnerRanged.attackEntityWithRangedAttack(this.attackTarget, power);
            this.rangedAttackTime = MathHelper.floor_float((float)(distanceRatio * (float)(this.attackTimeMax - this.attackTimeMin) + (float)this.attackTimeMin));
        } else if (this.rangedAttackTime < 0) {
            float distanceRatio = MathHelper.sqrt_double((double)distanceSq) / this.attackRange;
            this.rangedAttackTime = MathHelper.floor_float((float)(distanceRatio * (float)(this.attackTimeMax - this.attackTimeMin) + (float)this.attackTimeMin));
        }
    }

    public static Vec3 findPositionAwayFrom(EntityLivingBase entity, EntityLivingBase target, int min, int max) {
        Random random = entity.getRNG();
        for (int l = 0; l < 24; ++l) {
            int j;
            int k;
            int i = MathHelper.floor_double((double)entity.posX) - max + random.nextInt(max * 2 + 1);
            double d = target.getDistanceSq((double)i, (double)(j = MathHelper.floor_double((double)entity.boundingBox.minY) - 4 + random.nextInt(9)), (double)(k = MathHelper.floor_double((double)entity.posZ) - max + random.nextInt(max * 2 + 1)));
            if (d <= (double)(min * min) || d >= (double)(max * max)) continue;
            return Vec3.createVectorHelper((double)i, (double)j, (double)k);
        }
        return null;
    }
}

