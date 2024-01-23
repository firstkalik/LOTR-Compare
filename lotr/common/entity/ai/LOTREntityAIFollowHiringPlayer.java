/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTREntityAIFollowHiringPlayer
extends EntityAIBase {
    private LOTREntityNPC theNPC;
    private final boolean isBannerBearer;
    private EntityPlayer theHiringPlayer;
    private double moveSpeed;
    private int followTick;
    private float maxNearDist;
    private float minFollowDist;
    private boolean avoidsWater;
    private EntityLiving bannerBearerTarget;

    public LOTREntityAIFollowHiringPlayer(LOTREntityNPC entity) {
        this.theNPC = entity;
        this.isBannerBearer = entity instanceof LOTRBannerBearer;
        double entityMoveSpeed = entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
        this.moveSpeed = 1.0 / entityMoveSpeed * 0.37;
        this.minFollowDist = 8.0f;
        this.maxNearDist = 6.0f;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        if (!this.theNPC.hiredNPCInfo.isActive) {
            return false;
        }
        EntityPlayer entityplayer = this.theNPC.hiredNPCInfo.getHiringPlayer();
        if (entityplayer == null) {
            return false;
        }
        this.theHiringPlayer = entityplayer;
        if (!this.theNPC.hiredNPCInfo.shouldFollowPlayer()) {
            return false;
        }
        if (this.isBannerBearer) {
            ArrayList<EntityLiving> alliesToFollow = new ArrayList<EntityLiving>();
            List nearbyEntities = this.theNPC.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.theNPC.boundingBox.expand(16.0, 16.0, 16.0));
            for (int i = 0; i < nearbyEntities.size(); ++i) {
                EntityLiving entity = (EntityLiving)nearbyEntities.get(i);
                if (entity == this.theNPC || LOTRMod.getNPCFaction((Entity)entity) != this.theNPC.getFaction()) continue;
                if (entity instanceof LOTREntityNPC) {
                    LOTREntityNPC npc = (LOTREntityNPC)entity;
                    if (!npc.hiredNPCInfo.isActive || npc.hiredNPCInfo.getHiringPlayer() != entityplayer) continue;
                }
                alliesToFollow.add(entity);
            }
            EntityLiving entityToFollow = null;
            double d = Double.MAX_VALUE;
            for (int i = 0; i < alliesToFollow.size(); ++i) {
                EntityLiving entity = (EntityLiving)alliesToFollow.get(i);
                double dist = this.theNPC.getDistanceSqToEntity((Entity)entity);
                if (!(dist < d) || !(dist > (double)(this.minFollowDist * this.minFollowDist))) continue;
                d = dist;
                entityToFollow = entity;
            }
            if (entityToFollow != null) {
                this.bannerBearerTarget = entityToFollow;
                return true;
            }
        }
        return !(this.theNPC.getDistanceSqToEntity((Entity)entityplayer) < (double)(this.minFollowDist * this.minFollowDist));
    }

    public boolean continueExecuting() {
        if (this.theNPC.hiredNPCInfo.isActive && this.theNPC.hiredNPCInfo.getHiringPlayer() != null && this.theNPC.hiredNPCInfo.shouldFollowPlayer() && !this.theNPC.getNavigator().noPath()) {
            EntityLiving target = this.bannerBearerTarget != null ? this.bannerBearerTarget : this.theHiringPlayer;
            return this.theNPC.getDistanceSqToEntity((Entity)target) > (double)(this.maxNearDist * this.maxNearDist);
        }
        return false;
    }

    public void startExecuting() {
        this.followTick = 0;
        this.avoidsWater = this.theNPC.getNavigator().getAvoidsWater();
        this.theNPC.getNavigator().setAvoidsWater(false);
    }

    public void resetTask() {
        this.theHiringPlayer = null;
        this.bannerBearerTarget = null;
        this.theNPC.getNavigator().clearPathEntity();
        this.theNPC.getNavigator().setAvoidsWater(this.avoidsWater);
    }

    public void updateTask() {
        EntityLiving target = this.bannerBearerTarget != null ? this.bannerBearerTarget : this.theHiringPlayer;
        this.theNPC.getLookHelper().setLookPositionWithEntity((Entity)target, 10.0f, (float)this.theNPC.getVerticalFaceSpeed());
        if (this.theNPC.hiredNPCInfo.shouldFollowPlayer() && --this.followTick <= 0) {
            this.followTick = 10;
            if (!this.theNPC.getNavigator().tryMoveToEntityLiving((Entity)target, this.moveSpeed) && this.theNPC.hiredNPCInfo.teleportAutomatically) {
                double d = this.theNPC.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
                d = Math.max(d, 24.0);
                if (this.theNPC.getDistanceSqToEntity((Entity)this.theHiringPlayer) > d * d) {
                    this.theNPC.hiredNPCInfo.tryTeleportToHiringPlayer(false);
                }
            }
        }
    }
}

