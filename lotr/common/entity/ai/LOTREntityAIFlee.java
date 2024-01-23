/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.Vec3
 */
package lotr.common.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

public class LOTREntityAIFlee
extends EntityAIBase {
    private EntityCreature theEntity;
    private double speed;
    private double attackerX;
    private double attackerY;
    private double attackerZ;
    private int timer;
    private boolean firstPath;

    public LOTREntityAIFlee(EntityCreature entity, double d) {
        this.theEntity = entity;
        this.speed = d;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        EntityLivingBase attacker = this.theEntity.getAITarget();
        if (attacker == null) {
            return false;
        }
        this.attackerX = attacker.posX;
        this.attackerY = attacker.posY;
        this.attackerZ = attacker.posZ;
        return true;
    }

    public void startExecuting() {
        this.timer = 60 + this.theEntity.getRNG().nextInt(50);
    }

    public boolean continueExecuting() {
        return this.timer > 0;
    }

    public void updateTask() {
        Vec3 vec3;
        --this.timer;
        if ((!this.firstPath || this.theEntity.getNavigator().noPath()) && (vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom((EntityCreature)this.theEntity, (int)16, (int)7, (Vec3)Vec3.createVectorHelper((double)this.attackerX, (double)this.attackerY, (double)this.attackerZ))) != null && this.theEntity.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, this.speed)) {
            this.theEntity.setRevengeTarget(null);
            this.firstPath = true;
        }
    }

    public void resetTask() {
        this.theEntity.getNavigator().clearPathEntity();
        this.timer = 0;
        this.firstPath = false;
    }
}

