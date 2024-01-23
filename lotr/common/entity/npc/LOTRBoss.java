/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.boss.IBossDisplayData
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import net.minecraft.entity.boss.IBossDisplayData;

public interface LOTRBoss
extends IBossDisplayData {
    public LOTRAchievement getBossKillAchievement();

    public float getBaseChanceModifier();

    public void onJumpAttackFall();
}

