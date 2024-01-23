/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.entity.animal.LOTREntityLionBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityAILionChase
extends EntityAIBase {
    private LOTREntityLionBase theLion;
    private EntityCreature targetEntity;
    private double speed;
    private int chaseTimer;
    private int lionRePathDelay;
    private int targetRePathDelay;

    public LOTREntityAILionChase(LOTREntityLionBase lion, double d) {
        this.theLion = lion;
        this.speed = d;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (this.theLion.isChild() || this.theLion.isInLove()) {
            return false;
        }
        if (this.theLion.getRNG().nextInt(800) != 0) {
            return false;
        }
        List entities = this.theLion.worldObj.getEntitiesWithinAABB(EntityAnimal.class, this.theLion.boundingBox.expand(12.0, 12.0, 12.0));
        ArrayList<EntityAnimal> validTargets = new ArrayList<EntityAnimal>();
        for (int i = 0; i < entities.size(); ++i) {
            EntityAnimal entity = (EntityAnimal)entities.get(i);
            if (entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage) != null) continue;
            validTargets.add(entity);
        }
        if (validTargets.isEmpty()) {
            return false;
        }
        this.targetEntity = (EntityAnimal)validTargets.get(this.theLion.getRNG().nextInt(validTargets.size()));
        return true;
    }

    public void startExecuting() {
        this.chaseTimer = 300 + this.theLion.getRNG().nextInt(400);
    }

    public void updateTask() {
        Vec3 vec3;
        --this.chaseTimer;
        this.theLion.getLookHelper().setLookPositionWithEntity((Entity)this.targetEntity, 30.0f, 30.0f);
        --this.lionRePathDelay;
        if (this.lionRePathDelay <= 0) {
            this.lionRePathDelay = 10;
            this.theLion.getNavigator().tryMoveToEntityLiving((Entity)this.targetEntity, this.speed);
        }
        if (this.targetEntity.getNavigator().noPath() && (vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom((EntityCreature)this.targetEntity, (int)16, (int)7, (Vec3)Vec3.createVectorHelper((double)this.theLion.posX, (double)this.theLion.posY, (double)this.theLion.posZ))) != null) {
            this.targetEntity.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, 2.0);
        }
    }

    public boolean continueExecuting() {
        return this.targetEntity != null && this.targetEntity.isEntityAlive() && this.chaseTimer > 0 && this.theLion.getDistanceSqToEntity((Entity)this.targetEntity) < 256.0;
    }

    public void resetTask() {
        this.chaseTimer = 0;
        this.lionRePathDelay = 0;
        this.targetRePathDelay = 0;
    }
}

