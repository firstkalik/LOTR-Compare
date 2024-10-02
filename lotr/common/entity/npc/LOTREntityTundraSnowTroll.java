/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.entity.npc.LOTREntitySnowTroll;
import net.minecraft.world.World;

public class LOTREntityTundraSnowTroll
extends LOTREntitySnowTroll {
    public LOTREntityTundraSnowTroll(World world) {
        super(world);
        this.isChilly = false;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killSnowTroll;
    }
}

