/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.Vec3
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRNPCMount;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

public class LOTREntityAIHorseMoveToRiderTarget
extends EntityAIBase {
    private LOTRNPCMount theHorse;
    private EntityCreature livingHorse;
    private double speed;
    private PathEntity entityPathEntity;
    private int pathCheckTimer;

    public LOTREntityAIHorseMoveToRiderTarget(LOTRNPCMount horse) {
        this.theHorse = horse;
        this.livingHorse = (EntityCreature)this.theHorse;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        if (!this.theHorse.getBelongsToNPC()) {
            return false;
        }
        Entity rider = this.livingHorse.riddenByEntity;
        if (rider == null || !rider.isEntityAlive() || !(rider instanceof LOTREntityNPC)) {
            return false;
        }
        EntityLivingBase riderTarget = ((LOTREntityNPC)rider).getAttackTarget();
        if (riderTarget == null || !riderTarget.isEntityAlive()) {
            return false;
        }
        this.entityPathEntity = this.livingHorse.getNavigator().getPathToEntityLiving((Entity)riderTarget);
        return this.entityPathEntity != null;
    }

    public boolean continueExecuting() {
        if (this.livingHorse.riddenByEntity == null || !this.livingHorse.riddenByEntity.isEntityAlive() || !(this.livingHorse.riddenByEntity instanceof LOTREntityNPC)) {
            return false;
        }
        LOTREntityNPC rider = (LOTREntityNPC)this.livingHorse.riddenByEntity;
        EntityLivingBase riderTarget = rider.getAttackTarget();
        return riderTarget != null && riderTarget.isEntityAlive() && !this.livingHorse.getNavigator().noPath();
    }

    public void startExecuting() {
        this.speed = ((LOTREntityNPC)this.livingHorse.riddenByEntity).getEntityAttribute(LOTREntityNPC.horseAttackSpeed).getAttributeValue();
        this.livingHorse.getNavigator().setPath(this.entityPathEntity, this.speed);
        this.pathCheckTimer = 0;
    }

    public void resetTask() {
        this.livingHorse.getNavigator().clearPathEntity();
    }

    public void updateTask() {
        boolean aimingBow;
        LOTREntityNPC rider = (LOTREntityNPC)this.livingHorse.riddenByEntity;
        EntityLivingBase riderTarget = rider.getAttackTarget();
        boolean bl = aimingBow = rider.isAimingRanged() && this.livingHorse.getEntitySenses().canSee((Entity)riderTarget);
        if (!aimingBow) {
            this.livingHorse.getLookHelper().setLookPositionWithEntity((Entity)riderTarget, 30.0f, 30.0f);
            rider.rotationYaw = this.livingHorse.rotationYaw;
            rider.rotationYawHead = this.livingHorse.rotationYawHead;
        }
        if (--this.pathCheckTimer <= 0) {
            this.pathCheckTimer = 4 + this.livingHorse.getRNG().nextInt(7);
            this.livingHorse.getNavigator().tryMoveToEntityLiving((Entity)riderTarget, this.speed);
        }
        if (aimingBow) {
            if (rider.getDistanceSqToEntity((Entity)riderTarget) < 25.0) {
                Vec3 vec = LOTREntityAIRangedAttack.findPositionAwayFrom((EntityLivingBase)rider, riderTarget, 8, 16);
                if (vec != null) {
                    this.livingHorse.getNavigator().tryMoveToXYZ(vec.xCoord, vec.yCoord, vec.zCoord, this.speed);
                }
            } else {
                this.livingHorse.getNavigator().clearPathEntity();
            }
        }
    }
}

