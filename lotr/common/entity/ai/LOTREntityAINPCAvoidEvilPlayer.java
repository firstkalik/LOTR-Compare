/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
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

import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityAINPCAvoidEvilPlayer
extends EntityAIBase {
    private LOTREntityNPC theNPC;
    private double farSpeed;
    private double nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    private PathEntity entityPathEntity;
    private PathNavigate entityPathNavigate;

    public LOTREntityAINPCAvoidEvilPlayer(LOTREntityNPC npc, float f, double d, double d1) {
        this.theNPC = npc;
        this.distanceFromEntity = f;
        this.farSpeed = d;
        this.nearSpeed = d1;
        this.entityPathNavigate = npc.getNavigator();
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        ArrayList<EntityPlayer> validPlayers = new ArrayList<EntityPlayer>();
        List list = this.theNPC.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.theNPC.boundingBox.expand((double)this.distanceFromEntity, (double)this.distanceFromEntity / 2.0, (double)this.distanceFromEntity));
        if (list.isEmpty()) {
            return false;
        }
        for (int i = 0; i < list.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer)list.get(i);
            if (entityplayer.capabilities.isCreativeMode) continue;
            float alignment = LOTRLevelData.getData(entityplayer).getAlignment(this.theNPC.getFaction());
            if (!(this.theNPC.familyInfo.getAge() < 0 && alignment < 0.0f) && (!(this.theNPC instanceof LOTREntityHobbit) || !(alignment <= -100.0f))) continue;
            validPlayers.add(entityplayer);
        }
        if (validPlayers.isEmpty()) {
            return false;
        }
        this.closestLivingEntity = (Entity)validPlayers.get(0);
        Vec3 fleePath = RandomPositionGenerator.findRandomTargetBlockAwayFrom((EntityCreature)this.theNPC, (int)16, (int)7, (Vec3)Vec3.createVectorHelper((double)this.closestLivingEntity.posX, (double)this.closestLivingEntity.posY, (double)this.closestLivingEntity.posZ));
        if (fleePath == null) {
            return false;
        }
        if (this.closestLivingEntity.getDistanceSq(fleePath.xCoord, fleePath.yCoord, fleePath.zCoord) < this.closestLivingEntity.getDistanceSqToEntity((Entity)this.theNPC)) {
            return false;
        }
        this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(fleePath.xCoord, fleePath.yCoord, fleePath.zCoord);
        return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(fleePath);
    }

    public boolean continueExecuting() {
        return !this.entityPathNavigate.noPath();
    }

    public void startExecuting() {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
    }

    public void resetTask() {
        this.closestLivingEntity = null;
    }

    public void updateTask() {
        if (this.theNPC.getDistanceSqToEntity(this.closestLivingEntity) < 49.0) {
            this.theNPC.getNavigator().setSpeed(this.nearSpeed);
        } else {
            this.theNPC.getNavigator().setSpeed(this.farSpeed);
        }
    }
}

