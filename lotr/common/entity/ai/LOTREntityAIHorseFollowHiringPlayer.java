/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathNavigate
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRNPCMount;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;

public class LOTREntityAIHorseFollowHiringPlayer
extends EntityAIBase {
    private LOTRNPCMount theHorse;
    private EntityCreature livingHorse;
    private EntityPlayer theHiringPlayer;
    private double moveSpeed;
    private int followTick;
    private float maxNearDist;
    private float minFollowDist;
    private boolean avoidsWater;
    private boolean initSpeed;

    public LOTREntityAIHorseFollowHiringPlayer(LOTRNPCMount entity) {
        this.theHorse = entity;
        this.livingHorse = (EntityCreature)this.theHorse;
        this.minFollowDist = 8.0f;
        this.maxNearDist = 6.0f;
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
        LOTREntityNPC ridingNPC = (LOTREntityNPC)rider;
        if (!ridingNPC.hiredNPCInfo.isActive) {
            return false;
        }
        EntityPlayer entityplayer = ridingNPC.hiredNPCInfo.getHiringPlayer();
        if (entityplayer == null) {
            return false;
        }
        if (!ridingNPC.hiredNPCInfo.shouldFollowPlayer()) {
            return false;
        }
        if (this.livingHorse.getDistanceSqToEntity((Entity)entityplayer) < (double)(this.minFollowDist * this.minFollowDist)) {
            return false;
        }
        this.theHiringPlayer = entityplayer;
        return true;
    }

    public boolean continueExecuting() {
        if (this.livingHorse.riddenByEntity == null || !this.livingHorse.riddenByEntity.isEntityAlive() || !(this.livingHorse.riddenByEntity instanceof LOTREntityNPC)) {
            return false;
        }
        LOTREntityNPC ridingNPC = (LOTREntityNPC)this.livingHorse.riddenByEntity;
        return ridingNPC.hiredNPCInfo.isActive && ridingNPC.hiredNPCInfo.getHiringPlayer() != null && ridingNPC.hiredNPCInfo.shouldFollowPlayer() && !this.livingHorse.getNavigator().noPath() && this.livingHorse.getDistanceSqToEntity((Entity)this.theHiringPlayer) > (double)(this.maxNearDist * this.maxNearDist);
    }

    public void startExecuting() {
        this.followTick = 0;
        this.avoidsWater = this.livingHorse.getNavigator().getAvoidsWater();
        this.livingHorse.getNavigator().setAvoidsWater(false);
        if (!this.initSpeed) {
            double d = this.livingHorse.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
            this.moveSpeed = 1.0 / d * 0.37;
            this.initSpeed = true;
        }
    }

    public void resetTask() {
        this.theHiringPlayer = null;
        this.livingHorse.getNavigator().clearPathEntity();
        this.livingHorse.getNavigator().setAvoidsWater(this.avoidsWater);
    }

    public void updateTask() {
        LOTREntityNPC ridingNPC = (LOTREntityNPC)this.livingHorse.riddenByEntity;
        this.livingHorse.getLookHelper().setLookPositionWithEntity((Entity)this.theHiringPlayer, 10.0f, (float)this.livingHorse.getVerticalFaceSpeed());
        ridingNPC.rotationYaw = this.livingHorse.rotationYaw;
        ridingNPC.rotationYawHead = this.livingHorse.rotationYawHead;
        if (ridingNPC.hiredNPCInfo.shouldFollowPlayer() && --this.followTick <= 0) {
            this.followTick = 10;
            if (!this.livingHorse.getNavigator().tryMoveToEntityLiving((Entity)this.theHiringPlayer, this.moveSpeed) && ridingNPC.hiredNPCInfo.teleportAutomatically) {
                double d = ridingNPC.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
                d = Math.max(d, 24.0);
                if (ridingNPC.getDistanceSqToEntity((Entity)this.theHiringPlayer) > d * d) {
                    ridingNPC.hiredNPCInfo.tryTeleportToHiringPlayer(false);
                }
            }
        }
    }
}

