/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIPanic
 */
package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityGollum;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIPanic;

public class LOTREntityAIGollumPanic
extends EntityAIPanic {
    private LOTREntityGollum theGollum;

    public LOTREntityAIGollumPanic(LOTREntityGollum gollum, double d) {
        super((EntityCreature)gollum, d);
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

