/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.fac.LOTRFaction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityAIOrcAvoidGoodPlayer
extends EntityAIBase {
    private LOTREntityOrc theOrc;
    private double speed;
    private EntityLivingBase closestEnemyPlayer;
    private float distanceFromEntity;
    private PathEntity entityPathEntity;
    private PathNavigate entityPathNavigate;

    public LOTREntityAIOrcAvoidGoodPlayer(LOTREntityOrc orc, float f, double d) {
        this.theOrc = orc;
        this.distanceFromEntity = f;
        this.speed = d;
        this.entityPathNavigate = orc.getNavigator();
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (!this.theOrc.isWeakOrc || this.theOrc.hiredNPCInfo.isActive) {
            return false;
        }
        if (this.theOrc.getFaction() == LOTRFaction.MORDOR) {
            return false;
        }
        if (this.theOrc.currentRevengeTarget != null || this.anyNearbyOrcsAttacked()) {
            return false;
        }
        List validPlayers = this.theOrc.worldObj.selectEntitiesWithinAABB(EntityPlayer.class, this.theOrc.boundingBox.expand((double)this.distanceFromEntity, (double)this.distanceFromEntity / 2.0, (double)this.distanceFromEntity), new IEntitySelector(){

            public boolean isEntityApplicable(Entity entity) {
                EntityPlayer entityplayer = (EntityPlayer)entity;
                if (entityplayer.capabilities.isCreativeMode || LOTREntityAIOrcAvoidGoodPlayer.access$000((LOTREntityAIOrcAvoidGoodPlayer)LOTREntityAIOrcAvoidGoodPlayer.this).currentRevengeTarget == entityplayer) {
                    return false;
                }
                float alignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTREntityAIOrcAvoidGoodPlayer.this.theOrc.getFaction());
                return alignment <= -500.0f;
            }
        });
        if (validPlayers.isEmpty()) {
            return false;
        }
        this.closestEnemyPlayer = (EntityLivingBase)validPlayers.get(0);
        Vec3 fleePath = RandomPositionGenerator.findRandomTargetBlockAwayFrom((EntityCreature)this.theOrc, (int)16, (int)7, (Vec3)Vec3.createVectorHelper((double)this.closestEnemyPlayer.posX, (double)this.closestEnemyPlayer.posY, (double)this.closestEnemyPlayer.posZ));
        if (fleePath == null) {
            return false;
        }
        if (this.closestEnemyPlayer.getDistanceSq(fleePath.xCoord, fleePath.yCoord, fleePath.zCoord) < this.closestEnemyPlayer.getDistanceSqToEntity((Entity)this.theOrc)) {
            return false;
        }
        this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(fleePath.xCoord, fleePath.yCoord, fleePath.zCoord);
        return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(fleePath);
    }

    private boolean anyNearbyOrcsAttacked() {
        List nearbyAllies = this.theOrc.worldObj.selectEntitiesWithinAABB(EntityLiving.class, this.theOrc.boundingBox.expand((double)this.distanceFromEntity, (double)this.distanceFromEntity / 2.0, (double)this.distanceFromEntity), new IEntitySelector(){

            public boolean isEntityApplicable(Entity entity) {
                if (entity != LOTREntityAIOrcAvoidGoodPlayer.this.theOrc) {
                    return LOTRMod.getNPCFaction(entity).isGoodRelation(LOTREntityAIOrcAvoidGoodPlayer.this.theOrc.getFaction());
                }
                return false;
            }
        });
        for (Object obj : nearbyAllies) {
            EntityLiving ally = (EntityLiving)obj;
            if (ally instanceof LOTREntityOrc) {
                if (!(((LOTREntityOrc)ally).currentRevengeTarget instanceof EntityPlayer)) continue;
            } else if (!(ally.getAttackTarget() instanceof EntityPlayer)) continue;
            return true;
        }
        return false;
    }

    public boolean continueExecuting() {
        return !this.entityPathNavigate.noPath() && this.theOrc.getAITarget() != this.closestEnemyPlayer && !this.anyNearbyOrcsAttacked();
    }

    public void startExecuting() {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.speed);
    }

    public void resetTask() {
        this.closestEnemyPlayer = null;
    }

}

