/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetHuorn;
import lotr.common.entity.npc.LOTREntityHuornBase;
import lotr.common.fac.LOTRFaction;
import net.minecraft.world.World;

public class LOTREntityHuorn
extends LOTREntityHuornBase {
    public LOTREntityHuorn(World world) {
        super(world);
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetHuorn.class);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.FANGORN;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killHuorn;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}

