/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathNavigate
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;

public class LOTREntityAINPCFollowSpouse
extends EntityAIBase {
    private LOTREntityNPC theNPC;
    private LOTREntityNPC theSpouse;
    private double moveSpeed;
    private int followTick;

    public LOTREntityAINPCFollowSpouse(LOTREntityNPC npc, double d) {
        this.theNPC = npc;
        this.moveSpeed = d;
    }

    public boolean shouldExecute() {
        LOTREntityNPC spouse = this.theNPC.familyInfo.getSpouse();
        if (spouse == null) {
            return false;
        }
        if (!spouse.isEntityAlive() || this.theNPC.getDistanceSqToEntity((Entity)spouse) < 36.0 || this.theNPC.getDistanceSqToEntity((Entity)spouse) >= 256.0) {
            return false;
        }
        this.theSpouse = spouse;
        return true;
    }

    public boolean continueExecuting() {
        if (!this.theSpouse.isEntityAlive()) {
            return false;
        }
        double d = this.theNPC.getDistanceSqToEntity((Entity)this.theSpouse);
        return d >= 36.0 && d <= 256.0;
    }

    public void startExecuting() {
        this.followTick = 200;
    }

    public void resetTask() {
        this.theSpouse = null;
    }

    public void updateTask() {
        --this.followTick;
        if (this.theNPC.getDistanceSqToEntity((Entity)this.theSpouse) > 144.0 || this.followTick <= 0) {
            this.followTick = 200;
            this.theNPC.getNavigator().tryMoveToEntityLiving((Entity)this.theSpouse, this.moveSpeed);
        }
    }
}

