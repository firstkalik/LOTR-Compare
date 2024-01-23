/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAITarget
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.entity.ai;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class LOTREntityAIHiringPlayerHurtTarget
extends EntityAITarget {
    private LOTREntityNPC theNPC;
    private EntityLivingBase theTarget;
    private int playerLastAttackerTime;

    public LOTREntityAIHiringPlayerHurtTarget(LOTREntityNPC entity) {
        super((EntityCreature)entity, false);
        this.theNPC = entity;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (!this.theNPC.hiredNPCInfo.isActive || this.theNPC.hiredNPCInfo.isHalted()) {
            return false;
        }
        EntityPlayer entityplayer = this.theNPC.hiredNPCInfo.getHiringPlayer();
        if (entityplayer == null) {
            return false;
        }
        this.theTarget = entityplayer.getLastAttacker();
        int i = entityplayer.getLastAttackerTime();
        if (i == this.playerLastAttackerTime) {
            return false;
        }
        return LOTRMod.canNPCAttackEntity(this.theNPC, this.theTarget, true) && this.isSuitableTarget(this.theTarget, false);
    }

    public void startExecuting() {
        this.theNPC.setAttackTarget(this.theTarget);
        this.theNPC.hiredNPCInfo.wasAttackCommanded = true;
        EntityPlayer entityplayer = this.theNPC.hiredNPCInfo.getHiringPlayer();
        if (entityplayer != null) {
            this.playerLastAttackerTime = entityplayer.getLastAttackerTime();
        }
        super.startExecuting();
    }
}

