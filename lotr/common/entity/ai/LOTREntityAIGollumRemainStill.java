/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathNavigate
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityGollum;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;

public class LOTREntityAIGollumRemainStill
extends EntityAIBase {
    private LOTREntityGollum theGollum;

    public LOTREntityAIGollumRemainStill(LOTREntityGollum entity) {
        this.theGollum = entity;
        this.setMutexBits(5);
    }

    public boolean shouldExecute() {
        if (this.theGollum.getGollumOwner() == null) {
            return false;
        }
        if (this.theGollum.isInWater()) {
            return false;
        }
        if (!this.theGollum.onGround) {
            return false;
        }
        return this.theGollum.isGollumSitting();
    }

    public void startExecuting() {
        this.theGollum.getNavigator().clearPathEntity();
    }
}

