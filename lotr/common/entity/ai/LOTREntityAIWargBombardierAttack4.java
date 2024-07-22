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
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;
import lotr.common.entity.npc.LOTREntityWargBombardier4;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntityAIWargBombardierAttack4
extends EntityAIBase {
    private World worldObj;
    private LOTREntityWargBombardier4 theWarg;
    private EntityLivingBase entityTarget;
    private double moveSpeed;
    private PathEntity entityPathEntity;
    private int randomMoveTick;

    public LOTREntityAIWargBombardierAttack4(LOTREntityWargBombardier4 lotrEntityWargBombardier4, double speed) {
        this.theWarg = lotrEntityWargBombardier4;
        this.worldObj = lotrEntityWargBombardier4.worldObj;
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
                this.createWitherExplosion();
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

    private void createWitherExplosion() {
        this.worldObj.createExplosion((Entity)this.theWarg, this.theWarg.posX, this.theWarg.posY, this.theWarg.posZ, (float)(this.theWarg.getBombStrengthLevel() + 1) * 4.0f, true);
        double explosionRadius = (float)(this.theWarg.getBombStrengthLevel() + 1) * 4.0f;
        double effectRadius = explosionRadius + 5.0;
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((double)(this.theWarg.posX - effectRadius), (double)(this.theWarg.posY - effectRadius), (double)(this.theWarg.posZ - effectRadius), (double)(this.theWarg.posX + effectRadius), (double)(this.theWarg.posY + effectRadius), (double)(this.theWarg.posZ + effectRadius));
        List entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bb);
        entities.parallelStream().forEach(entity -> entity.addPotionEffect(new PotionEffect(Potion.wither.id, 200, 1)));
    }
}
