/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityGollum;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class LOTREntityAIGollumAvoidEntity
extends EntityAIAvoidEntity {
    private LOTREntityGollum theGollum;

    public LOTREntityAIGollumAvoidEntity(LOTREntityGollum gollum, Class entityClass, float f, double d, double d1) {
        super((EntityCreature)gollum, entityClass, f, d, d1);
        this.theGollum = gollum;
    }

    public void startExecuting() {
        super.startExecuting();
        this.theGollum.setGollumFleeing(true);
    }

    public void resetTask() {
        super.resetTask();
        this.theGollum.setGollumFleeing(false);
    }
}

