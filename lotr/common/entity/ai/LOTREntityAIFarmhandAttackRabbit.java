/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 */
package lotr.common.entity.ai;

import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.entity.EntityCreature;

public class LOTREntityAIFarmhandAttackRabbit
extends LOTREntityAINearestAttackableTargetBasic {
    private LOTREntityNPC theNPC;

    public LOTREntityAIFarmhandAttackRabbit(LOTREntityNPC npc) {
        super(npc, LOTREntityRabbit.class, 0, false);
        this.theNPC = npc;
    }

    @Override
    public boolean shouldExecute() {
        if (this.theNPC.hiredNPCInfo.isActive && !this.theNPC.hiredNPCInfo.isGuardMode()) {
            return false;
        }
        return super.shouldExecute();
    }

    protected double getTargetDistance() {
        return 8.0;
    }
}

