/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityWargBombardier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntityAIWargBombardierAttack
extends EntityAIBase {
    private World worldObj;
    private LOTREntityWargBombardier theWarg;
    private EntityLivingBase entityTarget;
    private double moveSpeed;
    private PathEntity entityPathEntity;
    private int randomMoveTick;

    public LOTREntityAIWargBombardierAttack(LOTREntityWargBombardier entity, double speed) {
        this.theWarg = entity;
        this.worldObj = entity.worldObj;
        this.moveSpeed = speed;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        EntityLivingBase entity = this.theWarg.getAttackTarget();
        if (entity == null) {
            return false;
        }
        this.entityTarget = entity;
        this.entityPathEntity = this.theWarg.getNavigator().getPathToEntityLiving((Entity)this.entityTarget);
        return this.entityPathEntity != null;
    }

    public boolean continueExecuting() {
        EntityLivingBase entity = this.theWarg.getAttackTarget();
        return entity != null && this.entityTarget.isEntityAlive();
    }

    public void startExecuting() {
        this.theWarg.getNavigator().setPath(this.entityPathEntity, this.moveSpeed);
        this.randomMoveTick = 0;
    }

    public void resetTask() {
        this.entityTarget = null;
        this.theWarg.getNavigator().clearPathEntity();
        this.theWarg.setBombFuse(35);
    }

    public void updateTask() {
        this.theWarg.getLookHelper().setLookPositionWithEntity((Entity)this.entityTarget, 30.0f, 30.0f);
        if (this.theWarg.getEntitySenses().canSee((Entity)this.entityTarget) && --this.randomMoveTick <= 0) {
            this.randomMoveTick = 4 + this.theWarg.getRNG().nextInt(7);
            this.theWarg.getNavigator().tryMoveToEntityLiving((Entity)this.entityTarget, this.moveSpeed);
        }
        if (this.theWarg.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= 16.0) {
            if (this.theWarg.getBombFuse() > 20) {
                int i;
                for (i = this.theWarg.getBombFuse(); i > 20; i -= 10) {
                }
                this.theWarg.setBombFuse(i);
            } else if (this.theWarg.getBombFuse() > 0) {
                this.theWarg.setBombFuse(this.theWarg.getBombFuse() - 1);
            } else {
                this.worldObj.createExplosion((Entity)this.theWarg, this.theWarg.posX, this.theWarg.posY, this.theWarg.posZ, (float)(this.theWarg.getBombStrengthLevel() + 1) * 4.0f, LOTRMod.canGrief(this.worldObj));
                this.theWarg.setDead();
            }
        } else if (this.theWarg.getBombFuse() <= 20) {
            int i;
            for (i = this.theWarg.getBombFuse(); i <= 20; i += 10) {
            }
            this.theWarg.setBombFuse(i);
        } else {
            this.theWarg.setBombFuse(this.theWarg.getBombFuse() - 1);
        }
    }
}

