/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTREntityAIHobbitChildFollowGoodPlayer
extends EntityAIBase {
    private LOTREntityHobbit theHobbit;
    private EntityPlayer playerToFollow;
    private float range;
    private double speed;
    private int followDelay;

    public LOTREntityAIHobbitChildFollowGoodPlayer(LOTREntityHobbit hobbit, float f, double d) {
        this.theHobbit = hobbit;
        this.range = f;
        this.speed = d;
    }

    public boolean shouldExecute() {
        if (this.theHobbit.familyInfo.getAge() >= 0) {
            return false;
        }
        List list = this.theHobbit.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.theHobbit.boundingBox.expand((double)this.range, 3.0, (double)this.range));
        EntityPlayer entityplayer = null;
        double distanceSq = Double.MAX_VALUE;
        for (EntityPlayer playerCandidate : list) {
            double d;
            if (LOTRLevelData.getData(playerCandidate).getAlignment(this.theHobbit.getFaction()) < 200.0f) continue;
            double d2 = this.theHobbit.getDistanceSqToEntity((Entity)playerCandidate);
            if (d > distanceSq) continue;
            distanceSq = d2;
            entityplayer = playerCandidate;
        }
        if (entityplayer == null) {
            return false;
        }
        if (distanceSq < 9.0) {
            return false;
        }
        this.playerToFollow = entityplayer;
        return true;
    }

    public boolean continueExecuting() {
        if (!this.playerToFollow.isEntityAlive() || this.theHobbit.familyInfo.getAge() >= 0) {
            return false;
        }
        double distanceSq = this.theHobbit.getDistanceSqToEntity((Entity)this.playerToFollow);
        return distanceSq >= 9.0 && distanceSq <= 256.0;
    }

    public void startExecuting() {
        this.followDelay = 0;
    }

    public void resetTask() {
        this.playerToFollow = null;
    }

    public void updateTask() {
        if (--this.followDelay <= 0) {
            this.followDelay = 10;
            this.theHobbit.getNavigator().tryMoveToEntityLiving((Entity)this.playerToFollow, this.speed);
        }
    }
}

