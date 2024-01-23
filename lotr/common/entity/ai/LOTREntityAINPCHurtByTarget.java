/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.util.ChunkCoordinates
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.ChunkCoordinates;

public class LOTREntityAINPCHurtByTarget
extends EntityAIHurtByTarget {
    public LOTREntityAINPCHurtByTarget(LOTREntityNPC npc, boolean flag) {
        super((EntityCreature)npc, flag);
    }

    protected boolean isSuitableTarget(EntityLivingBase entity, boolean flag) {
        if (entity == this.taskOwner.ridingEntity || entity == this.taskOwner.riddenByEntity) {
            return false;
        }
        int homeX = this.taskOwner.getHomePosition().posX;
        int homeY = this.taskOwner.getHomePosition().posY;
        int homeZ = this.taskOwner.getHomePosition().posZ;
        int homeRange = (int)this.taskOwner.func_110174_bM();
        this.taskOwner.detachHome();
        boolean superSuitable = super.isSuitableTarget(entity, flag);
        this.taskOwner.setHomeArea(homeX, homeY, homeZ, homeRange);
        return superSuitable;
    }
}

