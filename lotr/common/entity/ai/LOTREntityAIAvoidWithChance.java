/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 */
package lotr.common.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class LOTREntityAIAvoidWithChance
extends EntityAIAvoidEntity {
    private EntityCreature theEntity;
    private float chance;
    private String soundEffect;

    public LOTREntityAIAvoidWithChance(EntityCreature entity, Class avoidClass, float f, double d, double d1, float c) {
        this(entity, avoidClass, f, d, d1, c, null);
    }

    public LOTREntityAIAvoidWithChance(EntityCreature entity, Class avoidClass, float f, double d, double d1, float c, String s) {
        super(entity, avoidClass, f, d, d1);
        this.theEntity = entity;
        this.chance = c;
        this.soundEffect = s;
    }

    public boolean shouldExecute() {
        return this.theEntity.getRNG().nextFloat() < this.chance && super.shouldExecute();
    }

    public void startExecuting() {
        super.startExecuting();
        if (this.soundEffect != null) {
            this.theEntity.playSound(this.soundEffect, 0.5f, (this.theEntity.getRNG().nextFloat() - this.theEntity.getRNG().nextFloat()) * 0.2f + 1.0f);
        }
    }
}

