/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 */
package lotr.common.entity.ai;

import java.util.List;
import java.util.Random;
import lotr.common.entity.npc.LOTRBoss;
import lotr.common.entity.npc.LOTRBossInfo;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class LOTREntityAIBossJumpAttack
extends EntityAIBase {
    private LOTREntityNPC theBoss;
    private double jumpSpeed;
    private float jumpChance;

    public LOTREntityAIBossJumpAttack(LOTREntityNPC boss, double d, float f) {
        this.theBoss = boss;
        this.jumpSpeed = d;
        this.jumpChance = f;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        if (this.theBoss.getAttackTarget() == null) {
            return false;
        }
        if (this.theBoss.bossInfo.jumpAttack) {
            return false;
        }
        if (this.theBoss.getRNG().nextInt(20) == 0) {
            float f;
            float f2 = ((LOTRBoss)((Object)this.theBoss)).getBaseChanceModifier();
            f2 *= this.jumpChance;
            int enemies = this.theBoss.bossInfo.getNearbyEnemies().size();
            if ((float)enemies > 1.0f) {
                f2 *= (float)enemies * 4.0f;
            }
            float distance = this.theBoss.getDistanceToEntity((Entity)this.theBoss.getAttackTarget());
            distance = 8.0f - distance;
            distance /= 2.0f;
            if (f > 1.0f) {
                f2 *= distance;
            }
            return this.theBoss.getRNG().nextFloat() < f2;
        }
        return false;
    }

    public void startExecuting() {
        this.theBoss.bossInfo.doJumpAttack(this.jumpSpeed);
    }
}

