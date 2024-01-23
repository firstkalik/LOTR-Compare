/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathNavigate
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRNPCMount;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;

public class LOTREntityAIHiredHorseRemainStill
extends EntityAIBase {
    private LOTRNPCMount theHorse;
    private EntityCreature livingHorse;

    public LOTREntityAIHiredHorseRemainStill(LOTRNPCMount entity) {
        this.theHorse = entity;
        this.livingHorse = (EntityCreature)this.theHorse;
        this.setMutexBits(5);
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
        if (this.livingHorse.isInWater()) {
            return false;
        }
        if (!this.livingHorse.onGround) {
            return false;
        }
        return ridingNPC.hiredNPCInfo.isHalted() && (ridingNPC.getAttackTarget() == null || !ridingNPC.getAttackTarget().isEntityAlive());
    }

    public void startExecuting() {
        this.livingHorse.getNavigator().clearPathEntity();
    }
}

